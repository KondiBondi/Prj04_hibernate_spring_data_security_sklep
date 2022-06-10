<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.productName}</title>
    <link rel="stylesheet" type="text/css" href="/styl.css"/>
</head>
<body>

<h1>Informacje o produkcie ${product.productId}</h1>

<div class="product">
    <p>Towar <a href="/products/${product.productId}" class="product-name">${product.productName}</a></p>
    <p>Cena: <span class="product-price">${product.price}</span></p>
    <p>Stawka VAT: <span class="product-price">${product.vat * 100}%</span></p>
    <p class="product-description">${product.description}</p>
    <div class="action"><a href="/products/${product.productId}/add-to-basket">Dodaj do koszyka</a></div>
    <div class="action"><a href="/products/${product.productId}/edit">Edytuj</a></div>
</div>

<p><a href="/products">Przejdź do listy produktów</a></p>

</body>
</html>



