<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019-7-17
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="goodQueryServlet" method="post" >
    <table>
        <tr>
            <td>商品编号：</td>
            <td>${good.goodId}</td>
        </tr>
        <tr>
            <td>商品名字：</td>
            <td>${good.goodName}</td>
        </tr>
        <tr>
            <td>商品价格：</td>
            <td>${good.goodPrice}</td>
        </tr>
        <tr>
            <td>商品库存:</td>
            <td>${good.goodStock}</td>
        </tr>
        <tr>
            <td>商品简介:</td>
            <td>${good.goodDescription}</td>
        </tr>
        <tr>
            <td>商品图片:</td>
            <td><img src="upload/${good.goodPic}" width="50px" height="50px"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="返回">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
