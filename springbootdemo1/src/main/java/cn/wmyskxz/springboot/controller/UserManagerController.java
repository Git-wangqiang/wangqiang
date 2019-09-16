package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.service.UserInfoService;
import cn.wmyskxz.springboot.util.JsonUtil;
import cn.wmyskxz.springboot.util.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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


    @RequestMapping(value = "/updateperbyid", method = RequestMethod.POST)
    @ResponseBody
    private Object updatePerbyId(@RequestBody PermissionRequestMO permissionRequestMO) {
        userInfoService.updatePerbyId(permissionRequestMO);
        return JsonUtil.success();
    }

}
