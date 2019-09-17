package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.pojo.UserToken;
import cn.wmyskxz.springboot.pojo.User;

public interface UserService {
    User getUserByToken(String var1) throws Exception;

    UserToken getTimeByToken(String var1);
    void updateTimeByToken(String var1);
}