package com.wangqiang.demo.controll;

import com.wangqiang.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*@Controller*/
@RestController  // @RestController = @Controller +  @ResponseBody
@RequestMapping ("/user")
public class UserController {

    @RequestMapping ("/getUser")
   /* @ResponseBody*/
    public User getUser() throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-DD");
        Date b =  date.parse("1994-07-31");
        User user = new User();
        user.setName("王强");
        user.setPassword("11111111111");
        user.setAge(23);
        user.setBirthDay(b);
        return user;
    }

}
