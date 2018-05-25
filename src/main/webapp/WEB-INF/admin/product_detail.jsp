<%--
  Created by IntelliJ IDEA.
  User: LMQ
  Date: 2018/5/25
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>图书详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="<s:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<s:url value="/css/navbar-fixed-top.css"/>"/>
</head>
<body>
<!--导航栏-->
<jsp:include page="navigation.jsp"/>
<!--主界面-->
<div class="container">
    <form action="<s:url action="product_edit.action"/>" method="post">
        <input type="hidden" value="<s:property value="product.id"/>" name="product.id"/>
        <input type="submit" value="修改图书信息" class="btn btn-primary"/>
    </form>
    <hr/>
    <div class="panel panel-default">
        <div class="panel-heading"><span>图书信息</span>
        </div>
        <div class="panel-body lead">
            <div class="col-lg-4">
                <p>名称：<s:property value="product.book.bookName"/></p>
                <p>价格：￥<s:property value="product.price"/></p>
                <p>剩余数量:<s:property value="product.quantity"/>件</p>
                <p>是否在架：<s:if test="product.onsale">是</s:if><s:else>否</s:else></p>
                <p>作者：<s:property value="product.book.author"/></p>
                <p>ISBN：<s:property value="product.book.isbn"/></p>
            </div>
            <div class="col-lg-6">
                <p>图片:</p>
                <img class="img-thumbnail img-responsive" src="<s:property value="product.imageUrl"/>"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
