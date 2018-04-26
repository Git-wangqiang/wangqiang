package com.wangqiang.demo.controll;

import com.wangqiang.demo.pojo.WangQiang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ftl")
public class FreemarkerController {
    @Autowired
    private WangQiang wangqiang;

    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("resource" ,wangqiang);
        return "freemarker/index";

    }

    @RequestMapping("center")
    public String centre(){

        return "freemarker/center/center";
    }

}
