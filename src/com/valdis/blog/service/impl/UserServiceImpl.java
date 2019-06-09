package com.valdis.blog.service.impl;

import com.valdis.blog.bean.Essay;
import com.valdis.blog.bean.User;
import com.valdis.blog.dao.BaseDAO;
import com.valdis.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDAO baseDAO;

    @Override
    public User checkLogin(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        List users = baseDAO.find(u);
        if (users.size() != 0) return (User)users.get(0);
        return null;
    }

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

    @Override
    public List getEssays() {
        return baseDAO.getAllEssays();
    }

    @Override
    public boolean deleteEssay(String essayID) {
        Essay essayResult = (Essay)baseDAO.findByHQL("from Essay where essayId=\'" + essayID + "\'").get(0);
        return baseDAO.delete(essayResult);
    }

    @Override
    public boolean starEssay(String essayID) {
        return false;
    }
}
