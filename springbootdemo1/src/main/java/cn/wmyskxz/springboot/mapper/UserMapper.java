package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.pojo.UserToken;
import cn.wmyskxz.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {
    User getUserByToken(String var1);
    UserToken getTimeByToken(String var1);
    void updateTimeByToken(UserToken var1);

}
