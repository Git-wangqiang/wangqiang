package cn.wmyskxz.springboot.exceptions;


import cn.wmyskxz.springboot.util.JsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;


@ControllerAdvice
public class QADHandlerExceptionResolver {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject resolveException(Exception ex) {
        String uuid = UUID.randomUUID().toString();
        if (ex.toString().indexOf("BusinessException") != -1 || ex.toString().indexOf("WorkFlowEngineException") != -1) {
            return JsonUtil.failure(ex.getMessage().toString());
        } else {
            return JsonUtil.failure("系统出错，请联系运维人员，错误代码：" + uuid);
        }
    }
}