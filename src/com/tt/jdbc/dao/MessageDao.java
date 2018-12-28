package com.tt.jdbc.dao;

import com.tt.jdbc.entity.Message;
import com.tt.jdbc.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public List<Message> getMessages(int page, int pageSie){
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from message order by create_time limit ?, ?";   //limit m, n 从第m条记录开始取出总共n条记录
        List<Message> messageList = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * pageSie);
            preparedStatement.setInt(2, pageSie);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                messageList.add(new Message(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(resultSet, connection, preparedStatement);
        }
        return messageList;
    }

    //返回所有留言数量
    public int countMessages(){
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select count(*) total from message_board.message";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
