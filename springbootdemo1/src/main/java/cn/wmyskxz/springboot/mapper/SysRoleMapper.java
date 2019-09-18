package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.AddRoleRequestMO;
import cn.wmyskxz.springboot.mo.RoleResponseMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleMapper {
    //通过username查找用户角色信息
    List<SysRole> findRoleByUsername(@Param("role") String role);

    List<RoleResponseMO> selectRole(UserManagerReqoestMO userManagerReqoestMO);

    Integer lastInsertId();

    void addRole(AddRoleRequestMO addRoleRequestMO);

    void delroleByid(AddRoleRequestMO addRoleRequestMO);

    void updateRolebyid(AddRoleRequestMO addRoleRequestMO);
}