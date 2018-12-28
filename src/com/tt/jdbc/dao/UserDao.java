package com.tt.jdbc.dao;

import com.tt.jdbc.entity.User;
import com.tt.jdbc.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User login(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("real_name"),
                        resultSet.getTimestamp("birthday"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(resultSet, connection, preparedStatement);
        }
        return user;
    }
}
