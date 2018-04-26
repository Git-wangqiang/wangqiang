package com.wangqiang.demo.controll;


import com.wangqiang.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("th")
public class ThymeleafController {


    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("name","wangqiang");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center(){
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map) throws ParseException {

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date date = d.parse("1994-07-31");
        User user = new User();
        user.setName("wang");
        user.setBirthDay(date);
        user.setAge(23);
        user.setDesc("<font color='green'><b>hello<b/><font/>");
        map.addAttribute("user",user);
        return "thymeleaf/test";
    }

    @RequestMapping("postForm")
    public String postForm(User u){

        System.out.println(u.getName());
        return "redirect:/th/test";
    }
}
