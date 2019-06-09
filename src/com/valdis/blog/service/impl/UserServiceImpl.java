package com.valdis.blog.service.impl;

import com.valdis.blog.bean.Essay;
import com.valdis.blog.bean.User;
import com.valdis.blog.bean.Userstar;
import com.valdis.blog.dao.BaseDAO;
import com.valdis.blog.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDAO baseDAO;

    // 验证登录
    @Override
    public User checkLogin(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        List users = baseDAO.find(u);

        if (users.size() != 0)
            return (User)users.get(0);
        else
            return null;
    }

    // 注册
    @Override
    public boolean register(String username, String password, String password_repeat) {
        if (password.equals(password_repeat)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setType("user");
            List users = baseDAO.find(newUser);
            if (users.size() == 0) {
                return baseDAO.add(newUser);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    // 发布文章
    @Override
    public boolean produce(String id, String title, String content, Timestamp time, String author) {
        //System.out.println("ID: " + id + "\ntitle: " + title + "\nContent: " + content);
        if (title.equals("") || content.equals("")) {
            return false;
        }else {
            Essay newEssay = new Essay();
            newEssay.setEssayId(id);
            newEssay.setTitle(title);
            newEssay.setContent(content);
            newEssay.setTime(time);
            newEssay.setAuthor(author);
            return baseDAO.add(newEssay);
        }
    }

    // 获取所有文章
    @Override
    public List getEssays() {
        return baseDAO.getAllEssays();
    }

    // 获取所有收藏的文章
    @Override
    public List getAllStars() {

        // 获取当前登录的用户名
        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String n = (String)session.getAttribute("Username");

        // 所有收藏文章的ID
        List essayIDs = baseDAO.findByHQL("select essayID from Userstar us where us.username=" + n);

        Essay[] resultList = new Essay[essayIDs.size()];

        for (int i = 0; i < resultList.length; i++) {
            System.out.println(essayIDs.get(i));
        }
        return null;
    }

    // 删除自己的文章
    @Override
    public boolean deleteEssay(String essayID) {
        Essay essayResult = (Essay)baseDAO.findByHQL("from Essay where essayId=\'" + essayID + "\'").get(0);
        return baseDAO.delete(essayResult);
    }

    // 判断是否收藏
    @Override
    public boolean ifStar(String essayID) {
        // 获取当前登录的用户名
        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String n = (String)session.getAttribute("Username");

        Userstar r = new Userstar();
        r.setUsername(n);
        r.setEssayId(essayID);

        return baseDAO.find(r).size() != 0;
    }

    // 收藏
    @Override
    public boolean starEssay(String essayID) {
        // 获取当前登录的用户名
        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String n = (String)session.getAttribute("Username");

        // 构建收藏关系对象
        Userstar us = new Userstar();
        us.setrId("" + new Timestamp(new Date().getTime()));
        us.setUsername(n);
        us.setEssayId(essayID);

        return baseDAO.add(us);
    }

    // 取消收藏
    @Override
    public boolean unstarEssay(String essayID) {
        Userstar userstarResult = (Userstar)baseDAO.findByHQL("from Userstar where essayId=\'" + essayID + "\'").get(0);
        return baseDAO.delete(userstarResult);
    }
}
