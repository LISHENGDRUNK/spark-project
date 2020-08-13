package com.spark.electricity.test;

import com.spark.electricity.dao.ITaskDAO;
import com.spark.electricity.dao.impl.DAOFactory;
import com.spark.electricity.domain.Task;

/**
 * @ClassName TaskDAOTest
 * @Time : 2020/8/13 0013 14:14
 * @Author : lisheng
 * @Description: TaskDAO测试类
 **/
public class TaskDAOTest {
    public static void main(String[] args) {

        ITaskDAO taskDAO = DAOFactory.getTaskDAO();
        Task task = taskDAO.findById(1);
        System.out.println(task.toString());
    }
}
