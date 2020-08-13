package com.spark.electricity.dao.impl;

import com.spark.electricity.dao.ITaskDAO;

/**
 * @ClassName DAOFactory
 * @Time : 2020/8/13 0013 14:10
 * @Author : lisheng
 * @Description: DAO工厂类
 **/
public class DAOFactory {

    /**
     * 获取任务管理DAO
     * @Param []
     * @return com.spark.electricity.dao.ITaskDAO
     **/
    public static ITaskDAO getTaskDAO(){

        return new TaskDAOImpl();
    }
}
