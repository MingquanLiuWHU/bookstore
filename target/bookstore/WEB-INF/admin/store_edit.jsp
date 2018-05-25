<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>修改店铺信息</title>
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
            <div class="panel-title">修改店铺信息</div>
        </div>
        <div class="panel-body">

            <form class="form-horizontal" action="<s:url action="store_saveOrUpdate.action"/>" method="post">
                <input type="hidden" value="<s:property value="id"/>" name="id"/>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="storeName">名称</label>
                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="storeName" name="storeName"
                               value="<s:property value="storeName"/>"/></div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="description">描述</label>
                    <div class="col-lg-6">
                        <textarea class="form-control" rows="3" id="description" name="description"><s:property
                                value="description"/></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="address">地址</label>
                    <div class="col-lg-6">
                        <textarea class="form-control" rows="2" id="address" name="address"><s:property
                                value="address"/></textarea></div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label" for="account">收款账户</label>
                    <div class="col-lg-6">
                        <input type="text" class="form-control " id="account" name="receiveAccount"
                               value="<s:property value="receiveAccount"/>"/></div>
                </div>
                <div class="form-group">
                    <label class="sr-only col-lg-3 control-label">操作按钮</label>
                    <div class="col-lg-6">
                        <div>
                            <button class="btn btn-default" type="button" style="margin-right:20px"
                                    onclick="history.back()">取消
                            </button>
                            <button class="btn btn-primary" type="submit">确定</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
