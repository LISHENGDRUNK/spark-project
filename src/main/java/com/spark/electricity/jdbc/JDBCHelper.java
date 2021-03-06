package com.spark.electricity.jdbc;

import akka.io.Tcp;
import com.spark.electricity.conf.ConfigurationManager;
import com.spark.electricity.constant.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName JDBCHelper
 * @Time : 2020/8/12 0012 15:53
 * @Author : lisheng
 * @Description: JDBC辅助组件
 * 在正式的项目的代码编写过程中，是完全严格按照大公司的coding标准来的
 * 也就是说，在代码中，是不能出现任何hard code（硬编码）的字符
 * 比如“张三”、“com.mysql.jdbc.Driver”
 * 所有这些东西，都需要通过常量来封装和使用
 **/
public class JDBCHelper {
    //第一步，在静态代码快中，直接加载数据库的驱动，不是简单使用com.mysql.jdbc.Driver就可以了，即不能硬编码
    //
    //com.mysql.jdbc.Driver只代表了mysql的驱动，如果需要切换为其他数据库的驱动，就必须找到所有硬编码的数据库驱动的地方，
    //逐一进行修改，这样代码的维护成本较高，所以不能硬编码
    //
    //通常我们都是用一个常量接口中的常量值，来代表一个值，需要改变这个值得时候只需要改变常量接口中常量对应的值就可以了
    //
    // 项目，要尽量做成可配置的
    // 就是说，我们的这个数据库驱动，更进一步，也不只是放在常量接口中就可以了
    // 最好的方式，是放在外部的配置文件中，跟代码彻底分离
    // 常量接口中，只是包含了这个值对应的key的名字
    static {
        String driver = ConfigurationManager.getProperty(Constants.JDBC_DIRVER);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //第二步，实现JDBCHelper的单例化
    //为什么要实现单例化呢，以为它的内部要封装一个简单的内部的数据库连接池
    //为了保证数据库连接池有且仅有一份，所以就通过单例的方式
    //保证JDBCHelper只有一个实例，实例中只有一份数据库连接池
    private static JDBCHelper instance = null;

    /**
     * 获取单例，
     *
     * @return 单例
     **/
    public static JDBCHelper getInstance() {
        if (instance == null) {
            synchronized (JDBCHelper.class) {
                if (instance == null) {
                    instance = new JDBCHelper();
                }
            }
        }
        return instance;
    }

    //数据库连接池
    private LinkedList<Connection> dataSource = new LinkedList<Connection>();

    /**
     * 第三步：实现单例的过程中，创建唯一的数据库连接池
     * <p>
     * 私有化构造方法
     * JDBCHelper在整个程序运行周期中，只会创建一次实例，在这一次创建实例的过程中
     * 就会调用JDBCHelper方法，此时，就可以在构造方法中，区创建自己唯一的一个数据库连接池
     **/
    private JDBCHelper() {
        //首先第一步，获取数据库连接池的大小，就是数据库连接池中要放多少个数据库连接
        //这个，可以通过在配置文件中配置的方式，来灵活的设定
        int size = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
        for (int i = 0; i < size; i++) {
            String url = ConfigurationManager.getProperty(Constants.JDBC_URL);
            String user = ConfigurationManager.getProperty(Constants.JDBC_USER);
            String pwd = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);
            try {
                Connection conn = DriverManager.getConnection(url, user, pwd);
                dataSource.push(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 第四步，提供获取数据库连接的方法
     * 有可能获取的时候，连接被用光了，暂时获取不到数据库连接
     * 此时我们需要自己编码实现一个 简单的等待机制，去等待获取数据库连接     *
     **/
    public synchronized Connection getConnection() {
        while (dataSource.size() == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dataSource.poll();
    }
    /*
     * 第五步：开发增删改查的方法
     * 1、执行增删改SQL语句的方法
     * 2、执行查询SQL语句的方法
     * 3、批量执行SQL语句的方法
     **/
    /**
     * @Description //TODO 执行增删改SQL语句，
     * @Date 17:34 2020/8/12 0012
     * @Param [sql, params]
     * @return int 影响的行数
     **/
    public int executeUpdate(String sql,Object[] params){
        int rtn = 0;
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1,params[i]);
            }
            psmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                dataSource.push(conn);
            }
        }
        return rtn;
    }

    /**
     * @Author lisheng
     * @Description //TODO 执行查询SQL语句
     * @Date 17:36 2020/8/12 0012
     * @Param [sql, params, callback]
     * @return int
     **/
    public void executeQuery(String sql,Object[] params,QueryCallback callback){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1,params[i]);
            }
            rs = pstmt.executeQuery();
            callback.process(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                dataSource.push(conn);
            }
        }
            }
    /**
     * 批量执行SQL语句
     *
     * 批量执行SQL语句，是JDBC中的一个高级功能
     * 默认情况下，每次执行一条SQL语句，就会通过网络连接，向MySQL发送一次请求
     *
     * 但是，如果在短时间内要执行多条结构完全一模一样的SQL，只是参数不同
     * 虽然使用PreparedStatement这种方式，可以只编译一次SQL，提高性能，但是，还是对于每次SQL
     * 都要向MySQL发送一次网络请求
     *
     * 可以通过批量执行SQL语句的功能优化这个性能
     * 一次性通过PreparedStatement发送多条SQL语句，比如100条、1000条，甚至上万条
     * 执行的时候，也仅仅编译一次就可以
     * 这种批量执行SQL语句的方式，可以大大提升性能
     *
     * @param sql
     * @param paramsList
     * @return 每条SQL语句影响的行数
     */
    public int[] executeBatch(String sql, List<Object[]> paramsList){

        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = getConnection();
            //第一步使用Connection对象取消自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            //第二步，使用PresparedStatement.addBatch()方法加入批量sql的参数
            for (Object[] params:paramsList) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1,params[i]);
                }
                pstmt.addBatch();
            }
            //第三步，使用PreparedStatement.executeBatch()方法，执行批量的sql语句
            rtn = pstmt.executeBatch();
            //最后一步，使用Connection对象，提交批量的sql语句
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                dataSource.push(conn);
            }
        }
        return rtn;
    }

    /**
     * @Author lisheng
     * @Description //TODO 静态内部类，查询回掉接口
     * @Date 17:50 2020/8/12 0012
     * @Param
     * @return
     **/
    public static interface QueryCallback{
        /*
         * 处理查询结果
         * @Param [rs]
         * @return void
         **/
        void process(ResultSet rs) throws Exception;
    }
}
