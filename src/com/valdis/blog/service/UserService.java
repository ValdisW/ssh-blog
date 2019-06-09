package com.valdis.blog.service;

import com.valdis.blog.bean.User;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    // 验证登录
    User checkLogin(String username, String password);

    // 验证注册
    boolean register(String username, String password, String password_repeat);

    // 验证发表文章
    boolean produce(String id, String title, String content, Timestamp time, String author);

    // 获取所有文章
    List getEssays();

    // 删除文章
    boolean deleteEssay(String essayID);

    // 收藏文章
    boolean starEssay(String essayID);
}
