package com.valdis.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.valdis.blog.bean.User;
import com.valdis.blog.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    // 用户信息
    private String username;
    private String password;
    private String password_repeat;

    // 文章相关
    private String title;
    private String content;

    // 登录
    public String login() {
        User user = userService.checkLogin(username, password);
        if (user != null) {
            HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
            HttpSession session=request.getSession();
            session.setAttribute("Username", username);
            return SUCCESS;
        }
        return ERROR;
    }

    // 注册
    public String register() {
        if (userService.register(username, password, password_repeat)) return SUCCESS;
        return ERROR;
    }

    // 添加文章
    public String produce() {
        Timestamp ts = new Timestamp(new Date().getTime());
        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String n = (String)session.getAttribute("username");
        if (userService.produce("2333"+n, title, content, ts, n)) return SUCCESS;
        return ERROR;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_repeat() {
        return password_repeat;
    }

    public void setPassword_repeat(String password_repeat) {
        this.password_repeat = password_repeat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
