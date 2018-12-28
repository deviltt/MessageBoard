package com.tt.jdbc.service;

import com.tt.jdbc.dao.UserDao;
import com.tt.jdbc.entity.User;

public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }

    public User login(String username, String password){
        return userDao.login(username, password);
    }
}
