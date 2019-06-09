package com.valdis.blog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.valdis.blog.bean.Essay;
import com.valdis.blog.bean.User;
import com.valdis.blog.service.UserService;
import javassist.bytecode.analysis.Type;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Autowired
    private UserService userService;

    // - 表单信息 -
    // 用户信息
    private String username;
    private String password;
    private String password_repeat;

    // 文章相关
    private String title;
    private String content;
    private String essayID;

    // - 标签专用 -
    private List<Essay> essayList;

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
        // 获取时间戳
        Timestamp ts = new Timestamp(new Date().getTime());

        // 获取作者用户名
        HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String n = (String)session.getAttribute("Username");

        if (userService.produce("essay" + ts.getTime(), title, content, ts, n)) return SUCCESS;
        return ERROR;
    }

    // 查看文章列表
    public String list() {
        essayList = userService.getEssays();
        return SUCCESS;
    }

    // 删除文章
    public String deleteEssay() {
        userService.deleteEssay(essayID);
        return SUCCESS;
    }

    // 收藏文章
    public String starEssay() {
        userService.starEssay(essayID);
        return SUCCESS;
    }

    public List<Essay> getEssayList() {
        return essayList;
    }

    public void setEssayList(List<Essay> essayList) {
        this.essayList = essayList;
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

    public String getEssayID() {
        return essayID;
    }

    public void setEssayID(String essayID) {
        this.essayID = essayID;
    }
}
