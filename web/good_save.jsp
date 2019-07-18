<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019-7-16
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="goodInsertServlet" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>商品名字</td>
            <td>
                <input type="text" name="goodName">
            </td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td>
                <input type="text" name="goodPrice">
            </td>
        </tr>
        <tr>
            <td>商品库存</td>
            <td>
                <input type="text" name="goodStock">
            </td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <input type="file" name="goodPic">
            </td>
        </tr>
        <tr>
            <td>商品简介</td>
            <td>
                <input type="text" name="goodDescription">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
