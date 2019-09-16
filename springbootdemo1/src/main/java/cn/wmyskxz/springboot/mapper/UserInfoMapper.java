package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserInfoMapper {

    List<UserManagerResponseMO> userManagerSelect(UserManagerReqoestMO userManagerReqoestMO);

    //通过username查找用户角色信息
    UserInfo findByUsername(@Param("username") String username);

    UserManagerResponseMO selectUserByid(Integer id);

    void deleteUserByid(Integer uid);

    void updateUserByid(UserManagerReqoestMO userManagerReqoestMO);

    List<UserManagerResponseMO> selectUser(UserManagerReqoestMO userManagerReqoestMO);

    void addUser(UserManagerReqoestMO userManagerReqoestMO);

    void adduserRole(UserManagerReqoestMO userManagerReqoestMO);

    void updateUserRole(UserManagerReqoestMO userManagerReqoestMO);

    void tresetPassword(UserManagerReqoestMO userManagerReqoestMO);


}