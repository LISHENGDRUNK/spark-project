package com.spark.electricity.constant;

/**
 * @ClassName Constants
 * @Time : 2020/8/12 0012 15:53
 * @Author : lisheng
 * @Description: 常量接口
 **/
public interface Constants {

    /**
     *项目配置相关常量
     **/
    String JDBC_DIRVER = "jdbc.driver";
    String JDBC_DATASOURCE_SIZE = "jdbc.datasource.size";
    String JDBC_URL = "jdbc.url";
    String JDBC_USER = "jdbc.user";
    String JDBC_PASSWORD = "jdbc.password";
    String SPARK_LOCAL = "spark.local";

    /*
     *spark作业相关常量
     **/
    String SPARK_APP_NAME_SESSION = "UserVisitSessionAnalyzeSpark";
    /*
     * 任务相关常量
     **/
    String PARAM_START_DATE = "startDate";
    String PARAM_END_DATE = "endDate";

    String FIELD_SESSION_ID = "";
    String FIELD_SEARCH_KEYWORDS = "";
    String FIELD_CLICK_CATEGORY_IDS = "";


    String FIELD_AGE = "";
    String FIELD_PROFESSIONAL = "";
    String FIELD_CITY = "";
    String FIELD_SEX = "";


}
