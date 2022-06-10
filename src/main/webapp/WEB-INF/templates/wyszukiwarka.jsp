<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Katalog towarów</title>
    <link rel="stylesheet" type="text/css" href="/styl.css"/>
</head>
<body>
<div><a href="/">Powrót do spisu treści</a></div>
<h1>Wyszukiwarka produktów</h1>

<form method="get" class="wyszukiwarka">
    <table>
        <tr><td><label for="name">Podaj nazwę:</label></td>
            <td><input type="text" name="name" value="${param.name}"></td></tr>
        <tr><td><label for="min">Cena minimalna:</label></td>
            <td><input type="number" name="min" value="${param.min}"></td></tr>
        <tr><td><label for="max">Cena maksymalna:</label></td>
            <td><input type="number" name="max" value="${param.max}"></td></tr>
        <tr><td><button>Szukaj</button></td></tr>
    </table>
</form>

<c:forEach var="product" items="${products}">
    <div class="product">
        <p>Towar <a href="/products/${product.productId}" class="product-name">${product.productName}</a></p>
        <p>Cena: <span class="product-price">${product.price}</span></p>
        <p class="product-description">${product.description}</p>
    </div>
</c:forEach>

</body>
</html>



