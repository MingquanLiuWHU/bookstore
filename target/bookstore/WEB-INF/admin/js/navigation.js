/**
 * 处理admin.jsp的导航栏
 */
// TODO 主页加载的地址
var basePath = $("#basePath").val();
var product = "";//图书管理页面
var order = "";//订单管理页面
var store = "";//店铺信息页面
$(function(){
    $("#main").load(basePath+product);//首页自动加载

    $("#product").click(function () {
        $("#main").load(basePath+product);
    });

    $("#order").click(function () {
        $("#main").load(basePath+order);
    });

    $("#store").click(function () {
        $("#main").load(basePath+store);
    });
});


