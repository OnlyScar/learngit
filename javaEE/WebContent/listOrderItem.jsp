<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center" >购物车</h1>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>小计</td>
        <td>删除</td>
    </tr>
 
    <c:forEach items="${ois}" var="oi" varStatus="st">
        <tr>
            <td>${oi.product.name}</td>
            <td>${oi.product.price}</td>
            <td>${oi.num}</td>
            <td>${oi.product.price*oi.num}</td>
            <td><a href="deleteOrderItem?pid=${oi.product.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
    		<td colspan="5" align="right">
    			<a href="listProduct">返回</a>
    		</td>
    </tr>
    <c:if test="${!empty ois}">
        <tr>
            <td colspan="5" align="right">
                <a href="createOrder">生成订单</a>
            </td>
        </tr>
    </c:if>
    	
</table>
</body>
</html>