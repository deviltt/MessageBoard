package com.tt.jdbc.utils;

import java.sql.*;

public final class ConnectionUtil {
    private static String url = "jdbc:mysql://localhost:3306/message_board";
    private static String username = "root";
    private static String password = "123456";
    //类申明为final防止类被继承，此时构造器需要私有化
    private ConnectionUtil(){}

    static {

        try {
            //1.加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void release(ResultSet resultSet, Connection connection, Statement statement){
        try {
            if(resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
