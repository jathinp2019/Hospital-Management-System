package com.hsbc.service;

import com.hsbc.beans.User;

public interface UserService {
    User authenticate(String username, String password);
    void addUser(User user);
}
