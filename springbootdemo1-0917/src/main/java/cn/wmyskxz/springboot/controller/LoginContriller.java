package cn.wmyskxz.springboot.controller;


import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mo.LoginfoRequestMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.mo.UserManagerResponseMO;
import cn.wmyskxz.springboot.service.LoginService;
import cn.wmyskxz.springboot.service.UserInfoService;
import cn.wmyskxz.springboot.util.JsonUtil;
import cn.wmyskxz.springboot.util.RandomValidateCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/hello/user")
public class LoginContriller {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody LoginfoRequestMO loginfoRequestMO) {
        JSONObject jsonObject = JsonUtil.success(loginService.login(loginfoRequestMO));
        System.out.println(jsonObject.get("data"));
        return jsonObject;
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 校验验证码
     */
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean checkVerify(@RequestParam String verifyInput, HttpSession session) {
        try {
            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return false;
            }
            if (random.equals(inputStr)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

}
