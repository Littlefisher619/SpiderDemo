package com.littlefisher.spider.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DbUtil {
    public static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String URL = "jdbc:mysql://localhost:3306/spider?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF8";
    private static final String USER = "spider";
    private static final String PASSWORD = "spider";
    private static final Logger logger = LogManager.getLogger(DbUtil.class);
    private static Connection conn = null;
    static{
        logger.info("Init mysql driver...");
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    public static Connection getConnection(){
        return conn;
    }


}
