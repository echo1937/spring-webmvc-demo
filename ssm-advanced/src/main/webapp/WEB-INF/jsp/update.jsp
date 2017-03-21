<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/item/itemAdd" method="post">
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
<form action="${pageContext.request.contextPath }/item/updateAll" method="post">
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
        <c:forEach items="${itemList }" var="item" varStatus="vs">
            <%--如果批量更新, 可以用 List<pojo> pojoList 来接收, input框的name属性值 = pojoList[${varStatus.index}].属性名称 --%>
            <tr>
                <td>
                    <input name="ids" value="${item.id}" type="checkbox">
                    <input name="itemsList[${vs.index}].id" value="${item.id}" type="hidden"></td>
                <td><input name="itemsList[${vs.index}].name" value="${item.name}" type="text"></td>
                <td><input name="itemsList[${vs.index}].price" value="${item.price}" type="text"></td>
                <td><input name="itemsList[${vs.index}].createtime" type="text"
                           value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
                <td><input name="itemsList[${vs.index}].detail" value="${item.detail}" type="text"></td>

                <td><a href="${pageContext.request.contextPath }/itemEdit?id=${item.id}">修改</a></td>
            </tr>
        </c:forEach>
        <td><input type="submit" value="批量更新"></td>
    </table>

</form>
</body>

</html>