<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>图书管理</title>
</head>
<body>
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
    <s:if test="${empty requestScope.pageBean}">
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
            <td><s:property value="bookName"/></td>
            <td class="col-lg-1"><img class="" src="${pageContext.request.contextPath}/<s:property value="imageUrl"/>"/>
            </td>
            <td><s:property value="quantity"/></td>
            <td><s:property value="price"/></td>
            <td><s:property value="soldQuantity"/></td>
            <td>
                <div>
                    <button type="button" class="btn btn-primary" name="detail" data-value="<s:property value="id"/>">
                        详情
                    </button>
                    <button type="button" class="btn btn-primary" name="edit" data-value="<s:property value="id"/>">修改
                    </button>
                    <button type="button" class="btn btn-primary" name="undercarriage"
                            data-value="<s:property value="id"/>">下架
                    </button>
                    <button type="button" class="btn btn-primary" name="delete" data-value="<s:property value="id"/>">
                        删除
                    </button>
                </div>
            </td>
        </tr>
    </s:iterator>
    </tbody>
    <div class="text-right">
        <ul class="pagination ">
            <s:if test="pageBean.currentPage!=1">
                <li><a href="#">&laquo;</a></li>
            </s:if>

            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
    </s:else>
</body>
</html>
