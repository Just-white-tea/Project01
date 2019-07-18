<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019-7-16
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onUpdateUrl(goodId) {
            window.location.href="goodLoadDataServlet?a=2&&goodId="+goodId;
        }
        function onDeleteUrl(goodId) {
            window.location.href="goodDeleteServlet?goodId="+goodId;
        }
        function onInsertGoods() {
            window.location.href="good_save.jsp";
        }
        function onLookUrl(goodId) {
            window.location.href="goodLoadDataServlet?a=1&&goodId="+goodId;
        }
        function onDownLoad(goodPic) {
            window.location.href="goodDownLoadServlet?fileName="+goodPic;
        }
    </script>
</head>
<body>
<table border="1" cellspacing="0">
    <caption>商品信息查询</caption>
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名字</th>
        <th>商品价格</th>
        <th>商品库存</th>
        <th>商品图片</th>
        <th>商品简介</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="good">
        <tr>
            <td>${good.goodId}</td>
            <td>${good.goodName}</td>
            <td>${good.goodPrice}</td>
            <td>${good.goodStock}</td>
            <td>
                <img src="upload/${good.goodPic}" width="50px" height="50px">
            </td>
            <td>${good.goodDescription}</td>
            <td>
                <input type="button" value="修改" onclick="onUpdateUrl('${good.goodId}')"  />&nbsp;
                <input type="button" value="删除" onclick="onDeleteUrl('${good.goodId}')"  />&nbsp;
                <input type="button" value="详情" onclick="onLookUrl('${good.goodId}')"  />&nbsp;
                <input type="button" value="下载" onclick="onDownLoad('${good.goodPic}')"  />
            </td>
        </tr>
    </c:forEach>

    </tbody>
    <tfoot style="text-align: center">
    <tr>
        <td colspan="7">
            <input type="button" value="增加商品" onclick="onInsertGoods()">
        </td>
    </tr>

    </tfoot>
</table>
</body>
</html>
