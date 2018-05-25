<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新增图书</title>
    <link rel="stylesheet" href="<s:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<s:url value="/css/navbar-fixed-top.css"/>"/>
</head>
<body>
<!--引入导航栏-->
<jsp:include page="navigation.jsp"/>
<!--主界面-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">填写图书信息</div>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" action="<s:url action="product_save.action"/>">
                <div class="form-group">
                    <label for="bookName" class="col-lg-2 control-label">图书名称</label>
                    <div class="col-lg-6">
                        <input type="text" id="bookName" class="form-control" name="product.book.bookName"/></div>
                </div>
                <div class="form-group">
                    <label for="author" class="col-lg-2 control-label">作者</label>
                    <div class="col-lg-6">
                        <input type="text" id="author" class="form-control" name="product.book.author"/></div>
                </div>
                <div class="form-group">
                    <label for="isbn" class="col-lg-2 control-label">ISBN</label>
                    <div class="col-lg-6">
                        <input type="text" id="isbn" class="form-control" name="product.book.isbn"/></div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-lg-2 control-label">图书描述</label>
                    <div class="col-lg-6">
                        <textarea id="description" class="form-control" rows="3" name="product.description"></textarea></div>
                </div>
                <div class="form-group">
                    <label for="image" class="col-lg-2 control-label">图片</label>
                    <div class="col-lg-6">
                        <input type="file" id="image" name="image" class="btn btn-default" accept="image/*"/></div>

                </div>
                <div class="form-group">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-4"><img src="#" id="imgPreview" class="img-responsive"/></div>
                </div>

                <div class="form-group">
                    <label for="quantity" class="col-lg-2 control-label">上架数量</label>
                    <div class="col-lg-6">
                        <input type="text" id="quantity" class="form-control" name="product.quantity"/></div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-lg-2 control-label">定价</label>
                    <div class="col-lg-6">
                        <input type="text" id="price" class="form-control" name="product.price"/></div>
                </div>
                <div class="form-group">
                    <label class="sr-only col-lg-2 control-label">操作按钮</label>
                    <div class="col-lg-6">
                        <div>
                            <button class="btn btn-default" type="button" style="margin-right:20px">取消</button>
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
