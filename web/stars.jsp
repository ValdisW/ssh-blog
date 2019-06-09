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
    <title>收藏夹 | <s:property value="#session.Username" default="未登录" /></title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="./style/myStyle.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .essayBlock{
            border: 2px dashed #666;
            margin: 20px 30px;
            padding: 15px;
        }
        .essay-props{
            color: #666;
            font-size: 12px;
        }
        .option {font-size: 12px;}
        .option-unstar {
            color: rgba(147, 134, 0, 0.87);
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link active" href="index.jsp">首页</a>
        </li>
        <li class="nav-item">
            <s:a action="list" class="nav-link" href="#">文章</s:a>
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
<%--    <s:set var="list" value="%{getAllStars()}" />--%>

<%--    <h1><s:property value="#list" default="err"/></h1>--%>
    <s:iterator value="starList" var="staredEssay">

        <div class="essayBlock">
            <%-- 标题 --%>
            <h4 class="essay-title">
                <s:property value="#staredEssay.getTitle()" default="err" />
            </h4>

            <%-- 文章属性 --%>
            <p class="essay-props">
                <span><s:property value="#staredEssay.getTime()" default="err" /></span>
                <span>&nbsp;&nbsp;|&nbsp;&nbsp;by&nbsp;</span>
                <span><s:property value="#staredEssay.getAuthor()" default="err" /></span>
            </p>

            <%-- 正文 --%>
            <p class="essay-content">
                <s:property value="#staredEssay.getContent()" default="err" />
            </p>

            <%-- 操作 --%>
            <p>
                <a href="
                    <s:url action="delete">
                        <s:param name="essayID" value="#staredEssay.getEssayId()" />
                    </s:url>
                " class="option option-unstar">取消收藏</a>
            </p>
        </div>
    </s:iterator>
</div>
</body>
</html>
