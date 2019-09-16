package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.pojo.UserToken;
import cn.wmyskxz.springboot.mapper.UserMapper;
import cn.wmyskxz.springboot.pojo.User;
import cn.wmyskxz.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByToken(String token) throws Exception {
        if (token == null) {
            throw new Exception("请先登录");
            //System.out.println("请先登录");
        } else {
            User user = this.userMapper.getUserByToken(token);
            if (user == null) {
                throw new Exception("请重新登录");
            } else {
                return user;
            }
        }
    }

    public UserToken getTimeByToken(String token) {
        return this.userMapper.getTimeByToken(token);
    }

    public void updateTimeByToken(String token) {
        UserToken userToken = new UserToken();
        userToken.setTokenId(token);
        userToken.setUpdatetime(new Date());
        this.userMapper.updateTimeByToken(userToken);
    }
}