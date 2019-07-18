<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019-7-17
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onResetImg(obj) {
            var td=obj.parentElement
            var content='<input type="file" name="goodPic">';
            content+='<input type="button" value="取消上传" onclick="onCancelImg(this)">'

            td.innerHTML=content;
        }
        function onCancelImg(obj) {
            var td=obj.parentElement;
            var content='<img src="upload/${good.goodPic}" width="50px" height="50px">';
            content+='<input type="button" value="重新上传" onclick="onResetImg(this)">';
            content+='<input type="hidden" name="goodPic" value="${good.goodPic}">';
            td.innerHTML=content;
        }
    </script>
</head>
<body>
<form action="goodUpdateServlet" method="post" enctype="multipart/form-data">
    <table>
        <input type="hidden"  name="goodId" value="${good.goodId}">
        <tr>
            <td>商品名字：</td>
            <td>
                <input type="text"  name="goodName" value="${good.goodName}">
            </td>
        </tr>
        <tr>
            <td>商品价格：</td>
            <td>
                <input type="text" name="goodPrice" value="${good.goodPrice}">
            </td>
        </tr>
        <tr>
            <td>商品库存:</td>
            <td>
                <input type="text" name="goodStock" value="${good.goodStock}">
            </td>
        </tr>
        <tr>
            <td>商品简介:</td>
            <td>
                <input type="text" name="goodDescription" value="${good.goodDescription}">
            </td>
        </tr>
        <tr>
            <td>商品图片:</td>
            <td>
                <!--
                如果picPath不为空就显示图片<img>
                如果为空就显示<input type="file">
                -->
                <c:choose>
                    <c:when test="${good.goodPic!=null}">
                        <img src="upload/${good.goodPic}" width="50px" height="50px">
                        <input type="button" value="重新上传" onclick="onResetImg(this)">
                        <input type="hidden" name="goodPic" value="${good.goodPic}">
                    </c:when>
                    <c:otherwise>
                        <!-- 上传控件是不能通过脚本去设置value值 -->
                        <input type="file" name="goodPic">
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
