package com.spark.electricity.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName FastJsonTest
 * @Time : 2020/8/13 0013 14:30
 * @Author : lisheng
 * @Description: FastJson测试类
 **/
public class FastJsonTest {
    public static void main(String[] args) {
        String json = "[{'学生':'张三', '班级':'一班', '年级':'大一', '科目':'高数', '成绩':90}, {'学生':'李四', '班级':'二班', '年级':'大一', '科目':'高数', '成绩':80}]";
        JSONArray jsonArray = JSONArray.parseArray(json);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        System.out.printf(jsonObject.getString("学生"));
    }
}
