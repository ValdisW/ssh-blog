package com.valdis.blog.service;

import com.valdis.blog.bean.User;

import java.sql.Timestamp;

public interface UserService {

    User checkLogin(String username, String password);

    boolean register(String username, String password, String password_repeat);

    boolean produce(String id, String title, String content, Timestamp time, String author);
}
