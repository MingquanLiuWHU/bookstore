<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>管理员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
<form class="form-login" action="<s:url action="admin_login.action"/>" method="post">
    <h2 class="form-login-heading">管理员</h2>
    <label for="account" class="sr-only">账号</label>
    <input id="account" class="form-control" placeholder="请输入账号" type="text" name="account">
    <label for="password" class="sr-only">密码</label>
    <input id="password" class="form-control" placeholder="请输入密码"  type="password" name="password">
    <label><s:property value="fieldErrors.accountError"/></label>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
</form>

</body>
</html>
