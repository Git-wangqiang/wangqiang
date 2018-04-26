package com.wangqiang.demo.controll;

import com.wangqiang.demo.pojo.WangQiang;
import com.wangqiang.demo.util.JsonAndStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PropertiesTest {
    @Autowired
    private WangQiang wang;


    @RequestMapping("/getResult")
    public JsonAndStatus getResult(){
        WangQiang w = new WangQiang();
        BeanUtils.copyProperties(wang,w);
        return JsonAndStatus.ok(w);

    }
}
