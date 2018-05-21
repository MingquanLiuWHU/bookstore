
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>

<html>
<head>
  <title>登录测试</title>
</head>
<body>
<form action="/WebBookstore/user_login"  method="post" >

帐号：
<input type="text" name="account"/><br/>
密码：
<input type="password" name="password"/><br/>
<input type="submit" value="登录"/>
</form>
${actionErrors[0] }

</body>
</html>