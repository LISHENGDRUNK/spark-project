package com.spark.electricity.test;

import com.spark.electricity.jdbc.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JDBCHelperTest
 * @Time : 2020/8/12 0012 17:54
 * @Author : lisheng
 * @Description:
 **/
public class JDBCHelperTest {
    public static void main(String[] args) {
        //获取JDBC的单例
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
//        //测试普通的增删改语句
//        jdbcHelper.executeUpdate("insert into test_user values(?,?)",new Object[]{"张三",25});
//        //测试查询语句
//        final Map<String,Object> testUser = new HashMap<String, Object>();
//        //设计一个内部接口QueryCallback，在执行查询语句的时候，我们就可以封装和指定自己的查询结果的处理逻辑
//        //封装在一个内部接口的匿名内部类对象中，传入JDBCHelper方法，在方法内部，可以回调我们定义的逻辑，处理查询结果。
//        //并将查询结果放入外部的变量中
//        jdbcHelper.executeQuery("select * from test_user where name=?", new Object[]{"张三"},
//                new JDBCHelper.QueryCallback() {
//            @Override
//            public void process(ResultSet rs) throws Exception {
//                if (rs.next()){
//                    String name = rs.getString("name");
//                    int age = rs.getInt("age");
//                    //匿名内部类的使用，有一个很重要的点，如果要访问外部类中的一些成员，比如方法内的局部变量，
//                    //必须将局部变量，声明为final类型，才可以访问，否则无法访问。
//                    testUser.put("name",name);
//                    testUser.put("age",age);
//                }
//            }
//        });
//        System.out.println(testUser.get("name")+":"+testUser.get("age"));

        //测试批量执行sql语句
        String sql = "insert into test_user values(?,?)";
        List<Object[]> paramList = new ArrayList<Object[]>();
        paramList.add(new Object[]{"李四",23});
        paramList.add(new Object[]{"麻子",25});
        jdbcHelper.executeBatch(sql,paramList);
    }
}
