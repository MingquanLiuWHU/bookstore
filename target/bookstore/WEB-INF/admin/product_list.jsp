<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>图书管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<s:url value="/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<s:url value="/css/navbar-fixed-top.css"/>"/>
</head>
<body>
<!--引入导航栏-->
<jsp:include page="navigation.jsp"/>
<!--图书表格-->
<div class="container">
    <!--新增和排序-->
    <div>
        <span class="text-left"><a class="btn btn-primary" href="#">新增商品</a> </span>
        <form action="#" class="form-inline" style="float:right">
            <label for="search" class="sr-only"></label>
            <input id="search" type="text" class="form-control">
            <button type="submit" class="btn btn-primary">搜索</button>
        </form>
    </div>
    <hr/>
    <table class="table table-bordered text-center ">
        <thead>
        <tr>
            <th>图书名称</th>
            <th>图片</th>
            <th>剩余数量</th>
            <th>价格</th>
            <th>销量</th>
            <th>操作</th>
        </tr>
        </thead>
        <s:if test="pageBean.list.size<=0">
            <tbody>
            <tr>
                <td colspan="6">还没有图书</td>
            </tr>
            </tbody>
        </s:if>
        <s:else>
            <tbody>
            <s:iterator value="pageBean.list">
                <tr>
                    <td><s:property value="book.bookName"/></td>
                    <td class="col-lg-1"><img class="img-responsive"
                                              src="${pageContext.request.contextPath}/<s:property value="imageUrl"/>"/>
                    </td>
                    <td><s:property value="quantity"/></td>
                    <td><s:property value="price"/></td>
                    <td><s:property value="soldQuantity"/></td>
                    <td>
                        <!--操作按钮组-->
                        <form>
                            <!--该项的id-->
                            <input type="hidden" name="id" value="<s:property value="id"/>"/>
                            <button type="button" class="btn btn-primary" name="detail">详情
                            </button>
                            <button type="button" class="btn btn-primary" name="edit">修改
                            </button>
                            <button type="button" class="btn btn-primary" name="undercarriage">下架
                            </button>
                            <button type="button" class="btn btn-primary" name="delete">删除
                            </button>
                        </form>
                    </td>
                </tr>
            </s:iterator>
            </tbody>


        </s:else>
    </table>
    <s:if test="pageBean.list.size > 0">
        <div class="text-right">

            <ul class="pagination ">
                <s:if test="pageBean.currentPage!=1">
                    <li><a href="#">&laquo;</a></li>
                </s:if>
                <s:iterator begin="(pageBean.currentPage/5)*5 +1" end="(pageBean.currentPage/5)*5 +5" var="i">
                    <s:if test="#i<=pageBean.totalPages">
                        <s:if test="#i==pageBean.currentPage">
                            <li class="active"><span><s:property value="i"/></span></li>
                        </s:if>
                        <s:else>
                            <li><a><s:property value="i"/></a></li>
                        </s:else>
                    </s:if>
                </s:iterator>
                <s:if test="pageBean.currentPage!=pageBean.totalPages">
                    <li><a href="#">&raquo;</a></li>
                </s:if>

            </ul>
        </div>
        <div class="text-right lead">总共<s:property value="pageBean.totalPages"/>页，
            <s:property value="pageBean.count"/>条记录
        </div>
    </s:if>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script>
    //操作按钮的提交
    function submitForm($button, url) {
        var $form = $button.parent();
        $form.attr("action", url);
        $form.attr("method", "post");
        $form.submit();
    }

    //详情按钮
    $("[name='detail']").click(function () {
        submitForm($(this), "product_detail")
    });

    //编辑按钮
    $("[name='edit']").click(function () {
        submitForm($(this), "product_edit")
    });

    //下架按钮
    $("[name='undercarriage']").click(function () {
        submitForm($(this), "product_undercarriage");
    });

    //删除按钮
    $("[name='delete']").click(function () {
        submitForm($(this), "product_delete");
    });
</script>
</body>
</html>
