package com.hsbc.service;

import com.hsbc.beans.User;
import com.hsbc.dao.UserDao;
import com.hsbc.util.DaoFactory;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getUserDao();
    }

    @Override
    public User authenticate(String username, String password) {
        return userDao.authenticate(username, password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
