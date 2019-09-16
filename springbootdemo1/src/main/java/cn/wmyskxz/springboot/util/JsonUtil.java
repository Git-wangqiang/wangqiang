package cn.wmyskxz.springboot.util;


import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class JsonUtil {

    private static final Boolean SUCCESS = true;
    private static final Boolean FAILURE = false;
    private static final String IS_SUCCESS = "isSuccess";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String SYSDATE = "sysdate";
    private static final String DATA = "data";

    /**
     * 请求成功
     *
     * @param resultData
     * @return
     */
    public static JSONObject success(Object resultData) {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put(IS_SUCCESS, SUCCESS);
        resultJSON.put(DATA, resultData);
        resultJSON.put(SYSDATE, new Date());
       /* resultJSON.put("code",0);
        resultJSON.put("count",resultData.getTotal());*/

        return resultJSON;

    }

    public static JSONObject successTable(Object resultData, long total) {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put("code", 0);
        resultJSON.put("msg","");
        resultJSON.put("count", total);
        //resultJSON.put(IS_SUCCESS, SUCCESS);
        resultJSON.put(DATA, resultData);
        //resultJSON.put(SYSDATE, new Date());
        return resultJSON;

    }

    /**
     * 请求成功  无返回数据
     *
     * @return
     */
    public static JSONObject success() {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put(IS_SUCCESS, SUCCESS);
        resultJSON.put(DATA, "");
        resultJSON.put(SYSDATE, new Date());

        return resultJSON;

    }

    /**
     * 请求失败
     *
     * @param errorMessage
     * @return
     */
    public static JSONObject failure(String errorMessage) {

        JSONObject resultJSON = new JSONObject();

        resultJSON.put(IS_SUCCESS, FAILURE);
        resultJSON.put(ERROR_MESSAGE, errorMessage);
        resultJSON.put(SYSDATE, new Date());

        return resultJSON;

    }

    /**
     * 接口请求成功
     *
     * @return
     */
    public static JSONObject ifSuccess() {
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("responseCode", "000");
        resultJSON.put("responseMessage", "");
        resultJSON.put(SYSDATE, new Date());
        return resultJSON;
    }

    /**
     * 接口请求失败
     *
     * @param errorMessage
     * @return
     */
    public static JSONObject ifFailure(String errorMessage) {
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("responseCode", "101");
        resultJSON.put("responseMessage", errorMessage);
        resultJSON.put(SYSDATE, new Date());
        return resultJSON;
    }
}
