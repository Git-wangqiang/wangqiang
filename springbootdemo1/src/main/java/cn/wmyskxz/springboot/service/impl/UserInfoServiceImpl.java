package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.mapper.SysPermissionMapper;
import cn.wmyskxz.springboot.mapper.SysRoleMapper;
import cn.wmyskxz.springboot.mapper.UserInfoMapper;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.UserInfo;
import cn.wmyskxz.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoMapper.findByUsername(username);
    }

    @Override
    public UserManagerResponseMO selectUserByid(Integer id) {
        return userInfoMapper.selectUserByid(id);
    }

    @Override
    public List<UserManagerResponseMO> selectUser(UserManagerReqoestMO userManagerReqoestMO) {
        return userInfoMapper.selectUser(userManagerReqoestMO);
    }

    @Override
    @Transactional
    public void deleteUserByid(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.deleteUserByid(userManagerReqoestMO.getId());
    }

    @Override
    @Transactional
    public void updateUserByid(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.updateUserByid(userManagerReqoestMO);
    }

    @Override
    public List<UserManagerResponseMO> userManagerSelect(UserManagerReqoestMO userManagerReqoestMO) {
        return userInfoMapper.userManagerSelect(userManagerReqoestMO);
    }

    @Override
    public void addUser(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.addUser(userManagerReqoestMO);
    }

    @Override
    public List<RoleResponseMO> selectRole(UserManagerReqoestMO userManagerReqoestMO) {
        return sysRoleMapper.selectRole(userManagerReqoestMO);
    }

    @Override
    public Integer lastInsertId() {
        return sysRoleMapper.lastInsertId();
    }

    @Override
    public void adduserRole(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.adduserRole(userManagerReqoestMO);
    }

    @Override
    public void updateUserRole(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.updateUserRole(userManagerReqoestMO);
    }

    @Override
    public void tresetPassword(UserManagerReqoestMO userManagerReqoestMO) {
        userInfoMapper.tresetPassword(userManagerReqoestMO);
    }

    @Override
    public List<PermissionResponseMO> userManagerPermissionSelect() {
        return sysPermissionMapper.userManagerPermissionSelect();
    }

    @Override
    public void updatePerbyId(PermissionRequestMO permissionRequestMO) {
        sysPermissionMapper.updatePerbyId(permissionRequestMO);
    }

    @Override
    public List<PermissionselectResponseMO> permissionselect(PermissionselectRequestMO permissionselectRequestMO) {
        return sysPermissionMapper.permissionselect(permissionselectRequestMO);
    }

    @Override
    public List<PermissionselectResponseMO> permissionchilderselect(PermissionselectRequestMO permissionselectRequestMO) {
        return sysPermissionMapper.permissionchilderselect(permissionselectRequestMO);
    }

    @Override
    public void updateRole(UpdateRoleRequestMO updateRoleRequestMO) {
        //通过roleid先查询是否有分配有效权限
        List<RolePermissionResponseMO> rolePermissionResponseMOlist = sysPermissionMapper.getRolePermissionByRoleID(updateRoleRequestMO);
        //如果有就先删除此角色的所有权限后在重新插入
        if (rolePermissionResponseMOlist != null & rolePermissionResponseMOlist.size() > 0) {
            sysPermissionMapper.delRolePermissionByRoleID(updateRoleRequestMO);
        }
        InsertRolePermisssionRequestMO insertRolePermisssionRequestMO = null;
        for (Integer id : updateRoleRequestMO.getIds()) {
            insertRolePermisssionRequestMO = new InsertRolePermisssionRequestMO();
            insertRolePermisssionRequestMO.setPerid(id);
            insertRolePermisssionRequestMO.setRoleid(updateRoleRequestMO.getRoleid());
            sysPermissionMapper.insertRolePermission(insertRolePermisssionRequestMO);
        }
    }

    @Override
    public void addRole(AddRoleRequestMO addRoleRequestMO) {
        sysRoleMapper.addRole(addRoleRequestMO);
    }

    @Override
    public void delroleByid(AddRoleRequestMO addRoleRequestMO) {
        sysRoleMapper.delroleByid(addRoleRequestMO);
    }

    @Override
    public void updateRolebyid(AddRoleRequestMO addRoleRequestMO) {
        sysRoleMapper.updateRolebyid(addRoleRequestMO);
    }


}