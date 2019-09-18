package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.SysPermission;
import cn.wmyskxz.springboot.pojo.SysRole;


import java.util.List;


public interface SysPermissionMapper {
    //根据角色ID查询角色对应的权限信息
    //@Param("roleId")
    List<SysPermission> findPermissionByRoleId(SysRole sysRole);

    void updatePerbyId(PermissionRequestMO permissionRequestMO);

    List<PermissionResponseMO> userManagerPermissionSelect();

    List<PermissionselectResponseMO> permissionselect(PermissionselectRequestMO permissionselectRequestMO);

    List<PermissionselectResponseMO> permissionchilderselect(PermissionselectRequestMO permissionselectRequestMO);

    void updateRole(UpdateRoleRequestMO updateRoleRequestMO);

    List<RolePermissionResponseMO> getRolePermissionByRoleID(UpdateRoleRequestMO updateRoleRequestMO);

    void delRolePermissionByRoleID(UpdateRoleRequestMO updateRoleRequestMO);

    void insertRolePermission(InsertRolePermisssionRequestMO insertRolePermisssionRequestMO);
}