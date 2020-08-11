package com.spark.electricity.test;

import com.spark.electricity.conf.ConfigurationManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName ConfigurationManagerTEST
 * @Time : 2020/8/11 0011 16:33
 * @Author : lisheng
 * @Description:
 **/
public class ConfigurationManagerTest {
    public static void main(String[] args) {

        String testkey1 = ConfigurationManager.getProperty("testkey1");

        System.out.println(testkey1);
    }
}
