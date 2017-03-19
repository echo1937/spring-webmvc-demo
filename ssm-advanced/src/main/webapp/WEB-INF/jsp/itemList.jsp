<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/js/jquery-1.4.4.min.js"></script>
    <title>查询商品列表</title>
</head>
<body>
<script type="text/javascript">
    function request_json() {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath }/item/sendJson.action",
            contentType: "application/json;charset=utf-8",
            data: '{"name":"测试商品","price":99.9}',
            success: function (data) {
                alert(data);
            }
        });
    }
</script>
<input type="button" value="sendJson" onclick="request_json()">
<form action="${pageContext.request.contextPath }/item/itemAdd.action" method="post">
    添加商品：
    <table width="100%" border=1>
        <tr>
            <!--如果Controller中接收的入参是QueryVo,那么页面上input框的name属性值要等于"属性.属性..."的形式-->
            <td>商品名称:<input type="text" name="name"></td>
            <td>商品价格:<input type="text" name="price"></td>
            <td>生产日期:<input type="text" name="createtime" value="2016-01-01 00:00:00"></td>
            <td>商品描述:<input type="text" name="detail"></td>
            <td><input type="submit" value="添加"/></td>
        </tr>
    </table>
</form>
<form action="${pageContext.request.contextPath }/item/delAll.action" method="post">
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td>选择</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${itemList }" var="item">
            <tr>
                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                <td>${item.name }</td>
                <td>${item.price }</td>
                <td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.detail }</td>
                <td><a href="${pageContext.request.contextPath }/itemEdit.action?id=${item.id}">修改</a></td>
            </tr>
        </c:forEach>
        <td><input type="submit" value="批量删除"></td>
    </table>

</form>
</body>

</html>