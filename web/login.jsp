<%--
  Created by IntelliJ IDEA.
  User: Valdis
  Date: 2019/6/7
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>登录</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link active" href="index.jsp">首页</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">文章</a>
        </li>
        <li class="nav-item ml-md-auto">
            <s:if test="#session.Username==null">
                <a class="nav-link" href="login.jsp">登录</a>
            </s:if>
            <s:else>
                <a class="nav-link" href="userHomepage.jsp"><s:property value="#session.Username" /></a>
            </s:else>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form action="login.action" method="post">
                <div class="form-group">
                    <label for="username">用户名：</label>
                    <input class="form-control" id="username" type="text" name="username" placeholder="输入用户名" />
                </div>
                <div class="form-group">
                    <label for="password">密码：</label>
                    <input class="form-control" id="password" type="password" name="password" placeholder="输入密码" />
                </div>
                <div class="form-group">
                    <input class="btn btn-primary" type="submit" value="登录">
                </div>
                <div class="form-group">
                    <span>没有账号?<a href="signup.jsp">注册</a></span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>