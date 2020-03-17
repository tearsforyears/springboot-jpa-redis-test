package com.example.service;

import com.example.DAO.UserDAO;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO; // JPA帮我们实现了基本的CRUD工作

    public List<User> pullUsers() {
        return userDAO.findAll();
    }

    @Transactional
    public void register(String username, String password) {
        userDAO.save(new User(username, password));
    }

    public boolean login(String username, String password) {
        return userDAO.existsUserByUserNameAndPassWord(username, password);
    }

    public List<User> loginAndGetUserName(String username, String password) {
        return userDAO.findUserByUserName(username);
    }

    public Long userCount() {
        return userDAO.count();
    }

}
