package com.spark.electricity.dao;

import com.spark.electricity.domain.Task;

public interface ITaskDAO {

    /*
     * 根据主键查询任务
     * @Param [taskid] 主键
     * @return com.spark.electricity.domain.Task
     **/
    Task findById(long taskid);
}
