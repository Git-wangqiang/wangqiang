package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.SysPermission;
import cn.wmyskxz.springboot.service.UserInfoService;
import cn.wmyskxz.springboot.util.JsonUtil;
import cn.wmyskxz.springboot.util.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class UserManagerController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "/usermanagerselect", method = RequestMethod.POST)
    @ResponseBody
    public Object userManagerSelect(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        PageHelper.startPage(userManagerReqoestMO.getPage(), userManagerReqoestMO.getLimit());
        List<UserManagerResponseMO> data = userInfoService.userManagerSelect(userManagerReqoestMO);        //查询
        PageInfo<UserManagerResponseMO> page = new PageInfo<UserManagerResponseMO>(data);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        System.out.println(page);
        Object object = JsonUtil.successTable(page.getList(), page.getTotal());
        System.out.println(object);
        return object;
    }

    @RequestMapping(value = "/selectuserbyid", method = RequestMethod.POST)
    @ResponseBody
    private Object selectUserByid(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        UserManagerResponseMO userManagerResponseMO = userInfoService.selectUserByid(userManagerReqoestMO.getId());
        return JsonUtil.success(userManagerResponseMO);
    }

 /*   @RequestMapping(value = "/selectuser", method = RequestMethod.POST)
    @ResponseBody
    private Object selectUser(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        UserManagerResponseMO userManagerResponseMO = userInfoService.selectUser(userManagerReqoestMO);
        return JsonUtil.success(userManagerResponseMO);
    }
*/

    @RequestMapping(value = "/deleteuserbyid", method = RequestMethod.POST)
    @ResponseBody
    private Object deleteUserByid(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        userInfoService.deleteUserByid(userManagerReqoestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/updateuserbyid", method = RequestMethod.POST)
    @ResponseBody
    private Object updateUserByid(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        userInfoService.updateUserByid(userManagerReqoestMO);
        if (userManagerReqoestMO.getFlag() != null) {
            if ("update".equals(userManagerReqoestMO.getFlag())) {
                userInfoService.updateUserRole(userManagerReqoestMO);
            } else {
                userInfoService.adduserRole(userManagerReqoestMO);
            }
        }
        return JsonUtil.success();
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    private Object addUser(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        if (userManagerReqoestMO.getPassword() != null) {
            userManagerReqoestMO.setPassword(MD5.MD5(userManagerReqoestMO.getPassword()));
        }
        userInfoService.addUser(userManagerReqoestMO);
        Integer seq = userInfoService.lastInsertId();
        userManagerReqoestMO.setId(seq);
        userInfoService.adduserRole(userManagerReqoestMO);
        //新增sys_user_role表
        return JsonUtil.success();
    }

    @RequestMapping(value = "/selectrole", method = RequestMethod.POST)
    @ResponseBody
    private Object selectRole(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        return JsonUtil.success(userInfoService.selectRole(userManagerReqoestMO));
    }

    @RequestMapping(value = "/selectrolepage", method = RequestMethod.POST)
    @ResponseBody
    private Object selectRolePage(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        PageHelper.startPage(userManagerReqoestMO.getPage(), userManagerReqoestMO.getLimit());
        List<RoleResponseMO> data = userInfoService.selectRole(userManagerReqoestMO);      //查询
        PageInfo<RoleResponseMO> page = new PageInfo<RoleResponseMO>(data);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        System.out.println(page);
        Object object = JsonUtil.successTable(page.getList(), page.getTotal());
        System.out.println(object);
        return object;
    }

    @RequestMapping(value = "/tresetpassword", method = RequestMethod.POST)
    @ResponseBody
    private Object tresetPassword(@RequestBody UserManagerReqoestMO userManagerReqoestMO) {
        if (userManagerReqoestMO.getPassword() != null) {
            userManagerReqoestMO.setPassword(MD5.MD5(userManagerReqoestMO.getPassword()));
            userInfoService.tresetPassword(userManagerReqoestMO);
        } else {
            throw new BusinessException("密码为空");
        }
        return JsonUtil.success();
    }

    @RequestMapping(value = "/usermanagerpermissionselect", method = RequestMethod.GET)
    @ResponseBody
//@RequestBody UserManagerReqoestMO userManagerReqoestMO
    private Object userManagerPermissionSelect() {
        PageHelper.startPage(1, 10);
        List<PermissionResponseMO> data = userInfoService.userManagerPermissionSelect();
        PageInfo<PermissionResponseMO> page = new PageInfo<PermissionResponseMO>(data);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        System.out.println(page);
        Object object = JsonUtil.successTable(page.getList(), page.getTotal());
        System.out.println(object);
        return object;
    }


    @RequestMapping(value = "/permissionselect", method = RequestMethod.POST)
    @ResponseBody
    private Object permissionselect(@RequestBody PermissionselectRequestMO permissionselectRequestMO) {
        //查询所有的菜单list
        List<PermissionselectResponseMO> permissionselect = userInfoService.permissionselect(permissionselectRequestMO);
        //根据Parentid将集合分类成多个
        Map<Integer, List<PermissionselectResponseMO>> listMap = permissionselect.stream().collect(Collectors.groupingBy(PermissionselectResponseMO::getParentid));
        //拿到主菜单后循环
        List<PermissionselectResponseMO> permianmenulist = listMap.get(-1);
        //返回树形组件json
        List<ResponseTreeMO> responseTreeMOList = new ArrayList<>();
        ResponseTreeMO responseTreeMO = null;
        if (permianmenulist != null && permianmenulist.size() > 0) {
            for (PermissionselectResponseMO permianmenu : permianmenulist) {
                responseTreeMO = new ResponseTreeMO();
                responseTreeMO.setTitle(permianmenu.getName());
                responseTreeMO.setId(permianmenu.getPerid());
                responseTreeMO.setSpread(true);
                List<PermissionselectResponseMO> perchildlist = listMap.get(permianmenu.getPerid());
                if (perchildlist != null && perchildlist.size() > 0) {
                    List<ResponseTreeChildrenMO> responseTreeChildrenMOList = new ArrayList<>();
                    ResponseTreeChildrenMO responseTreeChildrenMO = null;
                    Map<Integer, String> id2Name = new HashMap<>();
                    //如果是选择特定的角色，就查询到拥有的菜单权限，然后确定是否选择
                    if (permissionselectRequestMO.getId() != null) {
                        //查询拥有的菜单权限
                        List<PermissionselectResponseMO> permissionselectchenckedlist = userInfoService.permissionchilderselect(permissionselectRequestMO);
                        if (permissionselectchenckedlist != null && permissionselectchenckedlist.size() > 0) {
                            //将list转map
                            id2Name = permissionselectchenckedlist.stream()
                                    .collect(Collectors.toMap(PermissionselectResponseMO::getPerid, PermissionselectResponseMO::getName));
                        }
                    }
                    for (PermissionselectResponseMO perchild : perchildlist) {
                        responseTreeChildrenMO = new ResponseTreeChildrenMO();
                        responseTreeChildrenMO.setId(perchild.getPerid());
                        responseTreeChildrenMO.setTitle(perchild.getName());
                        //如果有权限就默认勾选
                        if (id2Name != null) {
                            if (id2Name.get(perchild.getPerid()) != null) {
                                responseTreeChildrenMO.setChecked(true);
                            } else {
                                responseTreeChildrenMO.setChecked(false);
                            }
                        }
                        responseTreeChildrenMOList.add(responseTreeChildrenMO);

                    }
                    responseTreeMO.setChildren(responseTreeChildrenMOList);
                }
                responseTreeMOList.add(responseTreeMO);
            }
        }
        return JsonUtil.success(responseTreeMOList);
    }


    @RequestMapping(value = "/updateperbyid", method = RequestMethod.POST)
    @ResponseBody
    private Object updatePerbyId(@RequestBody PermissionRequestMO permissionRequestMO) {
        userInfoService.updatePerbyId(permissionRequestMO);
        return JsonUtil.success();
    }

}
