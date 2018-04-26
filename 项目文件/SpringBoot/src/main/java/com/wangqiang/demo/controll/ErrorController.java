package com.wangqiang.demo.controll;

import com.wangqiang.demo.util.JsonAndStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("err")
public class ErrorController {

    @RequestMapping("/error")
    public String  error(){

        int a = 1/0;
        return "thymeleaf/error";
    }

    @RequestMapping("ajaxerror")
    public  String ajaxError(){

        return "thymeleaf/ajaxerror";
    }

    @RequestMapping("/getAjaxerror")
    @ResponseBody
    public JsonAndStatus getAjaxerror(){

        int a = 1/0;
        return JsonAndStatus.ok();
    }
}
