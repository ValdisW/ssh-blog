<%--
  Created by IntelliJ IDEA.
  User: Valdis
  Date: 2019/6/7
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <title>Home | <s:property value="#session.Username" default="未登录" /></title>
  <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
  <link href="./style/myStyle.css" rel="stylesheet">
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
      <s:a action="list" class="nav-link">文章</s:a>
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
Home
</div>
</body>
</html>
