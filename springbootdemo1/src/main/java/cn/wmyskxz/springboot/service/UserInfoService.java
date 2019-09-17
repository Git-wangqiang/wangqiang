package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserInfoService {
    /**
     * 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);

    UserManagerResponseMO selectUserByid(Integer id);

    List<UserManagerResponseMO> selectUser(UserManagerReqoestMO userManagerReqoestMO);

    void deleteUserByid(UserManagerReqoestMO userManagerReqoestMO);

    void updateUserByid(UserManagerReqoestMO userManagerReqoestMO);

    List<UserManagerResponseMO> userManagerSelect(UserManagerReqoestMO userManagerReqoestMO);

    void addUser(UserManagerReqoestMO userManagerReqoestMO);

    List<RoleResponseMO> selectRole(UserManagerReqoestMO userManagerReqoestMO);

    Integer lastInsertId();

    void adduserRole(UserManagerReqoestMO userManagerReqoestMO);

    void updateUserRole(UserManagerReqoestMO userManagerReqoestMO);

    void tresetPassword(UserManagerReqoestMO userManagerReqoestMO);

    List<PermissionResponseMO> userManagerPermissionSelect();

    void updatePerbyId(PermissionRequestMO permissionRequestMO);

    List<PermissionselectResponseMO> permissionselect(PermissionselectRequestMO permissionselectRequestMO);

    List<PermissionselectResponseMO> permissionchilderselect(PermissionselectRequestMO permissionselectRequestMO);

}