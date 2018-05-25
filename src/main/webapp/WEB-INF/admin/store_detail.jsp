<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>店铺信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="<s:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<s:url value="/css/navbar-fixed-top.css"/>"/>
</head>
<body>
<!--导航栏-->
<jsp:include page="navigation.jsp"/>
<!--主界面-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">店铺信息</div></div>
        <div class="panel-body lead">
            <div class="col-lg-offset-2 ">
                <%--<p>名称：${requestScope.store.storeName}</p>
                <p>描述：${requestScope.store.description}</p>
                <p>发货地址：${requestScope.store.address}</p>
                <p>收款账户：${requestScope.store.receiveAccount}</p>--%>
                <p>名称：<s:property value="storeName"/></p>
                <p>描述：<s:property value="description"/></p>
                <p>发货地址：<s:property value="address"/></p>
                <p>收款账户：<s:property value="receiveAccount"/></p>
                <a href="<s:url action="store_edit.action"/>" class="btn btn-primary">修改信息</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
