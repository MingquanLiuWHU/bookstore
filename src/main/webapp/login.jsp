
<%@ page  contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>登录测试</title>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
</head>
<body>
<div class="container">

  <form class="form-signin">
    <h2 class="form-signin-heading">登录</h2>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" type="email">
    <label for="inputPassword" class="sr-only">Password</label>
    <input id="inputPassword" class="form-control" placeholder="Password" required="" type="password">
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
  </form>

</div>

</body>
</html>