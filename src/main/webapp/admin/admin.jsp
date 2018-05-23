<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>管理员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-fixed-top.css"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">导航栏</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">网上书城</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a id="product" href="javascript:void(0)">图书管理</a></li>
                <li><a id="order" href="javascript:void(0)">订单管理</a></li>
                <li><a id="store" href="javascript:void(0)">店铺信息</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">退出登录</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<!--主要显示区域-->
<div id="main" class="container"></div>
<!--传递给js文件的项目根路径-->
<input id="basePath"type="hidden" value="${pageContext.request.contextPath}"/>
<!--引入导航栏的js文件-->
<script src="${pageContext.request.contextPath}/admin/js/navigation.js"></script>
</body>
</html>
