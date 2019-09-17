package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mapper.SysPermissionMapper;
import cn.wmyskxz.springboot.mapper.SysRoleMapper;
import cn.wmyskxz.springboot.mapper.UserInfoMapper;
import cn.wmyskxz.springboot.mo.LoginfoRequestMO;
import cn.wmyskxz.springboot.pojo.SysPermission;
import cn.wmyskxz.springboot.pojo.SysRole;
import cn.wmyskxz.springboot.pojo.UserInfo;
import cn.wmyskxz.springboot.service.LoginService;
import cn.wmyskxz.springboot.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "loginService")
public class LoginServiceimpl implements LoginService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Override
    public Map<String, List<SysPermission>> login(LoginfoRequestMO loginfoRequestMO) {
        UserInfo userInfo = userInfoMapper.findByUsername(loginfoRequestMO.getUsername());
        String md5User = MD5.MD5(loginfoRequestMO.getPassword());
        if (userInfo != null) {
            if (userInfo.getPassword().equals(md5User)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userInfo);
                Cookie c = new Cookie("loginInfo", "logininfo");
                c.setMaxAge(60 * 60);
                c.setPath("/");
                response.addCookie(c);
                String role = userInfo.getUsername();
                if (role != null) {
                    List<SysRole> sysRoles = sysRoleMapper.findRoleByUsername(role);
                    if (sysRoles != null && sysRoles.size() > 0) {
                        List<SysPermission> sysPermissions = new ArrayList<>();
                        for (SysRole s : sysRoles) {
                            System.out.println(s);
                            List<SysPermission> sysPermissionList = sysPermissionMapper.findPermissionByRoleId(s);
                            if (sysPermissionList != null && sysPermissionList.size() > 0) {
                                sysPermissions.addAll(sysPermissionList);
                            } else {
                                throw new BusinessException("用户" + userInfo.getUsername() + "没有任何权限");
                            }
                        }
                        Map<String, List<SysPermission>> listMap = sysPermissions.stream().collect(Collectors.groupingBy(SysPermission::getParentmenu));
                        return listMap;
                    } else {
                        throw new BusinessException("用户" + userInfo.getUsername() + "没有角色属性");
                    }
                } else {
                    throw new BusinessException("用户" + userInfo.getUsername() + "没有角色属性");
                }
                //如果密码正确，查询菜单权限
                //return "success";
            } else {
                throw new BusinessException("密码不正确");
            }
        } else {
            throw new BusinessException("用户名不存在");
        }

    }
}
