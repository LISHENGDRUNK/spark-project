package com.spark.electricity.util;

import com.fasterxml.jackson.core.JsonParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName ParamUtils
 * @Time : 2020/8/11 0011 14:56
 * @Author : lisheng
 * @Description: 参数工具类
 **/
public class ParamUtils {

    /**
     * @return java.lang.Long 任务id
     * @Author lisheng
     * @Description //TODO 从命令行参数中提取任务id
     * @Date 14:59 2020/8/11 0011
     * @Param [args]
     **/
    public static Long getTaskIdFromArgs(String[] args) {

        try {
            if (args != null && args.length > 0) {
                return Long.valueOf(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return java.lang.String 参数
     * @Author lisheng
     * @Description //TODO 从json对象中提取参数
     * @Date 15:03 2020/8/11 0011
     * @Param [jsonObject, field]
     **/
    public static String getParam(JSONObject jsonObject, String field) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray(field);
            if (jsonArray != null && jsonArray.length() > 0) {
                return jsonArray.getString(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
