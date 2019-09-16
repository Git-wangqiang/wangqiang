package cn.wmyskxz.springboot.util;

import com.alibaba.fastjson.JSONObject;
public class BindTest {
    public static void print(Object param) {
        JSONObject.toJSON(param);
    }
}