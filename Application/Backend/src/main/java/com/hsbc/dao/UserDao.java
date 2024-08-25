package com.hsbc.dao;

import com.hsbc.beans.User;

public interface UserDao {
    void addUser(User user);
    User authenticate(String username, String password);
}
