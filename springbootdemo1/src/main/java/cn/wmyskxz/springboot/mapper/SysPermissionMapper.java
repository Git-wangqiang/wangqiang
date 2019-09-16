package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.PermissionRequestMO;
import cn.wmyskxz.springboot.mo.PermissionResponseMO;
import cn.wmyskxz.springboot.pojo.SysPermission;
import cn.wmyskxz.springboot.pojo.SysRole;


import java.util.List;


public interface SysPermissionMapper {
    //根据角色ID查询角色对应的权限信息
    //@Param("roleId")
    List<SysPermission> findPermissionByRoleId(SysRole sysRole);

    void updatePerbyId(PermissionRequestMO permissionRequestMO);
    List<PermissionResponseMO> userManagerPermissionSelect();
}