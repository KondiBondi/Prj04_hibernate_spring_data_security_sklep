<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edycja towaru</title>
    <link rel="stylesheet" type="text/css" href="/styl.css">
</head>
<body>

<c:choose>
    <c:when test="${not empty(product.productId)}">
        <h1>Edycja produktu nr ${product.productId}</h1>
    </c:when>
    <c:otherwise>
        <h1>Edycja nowego produktu</h1>
    </c:otherwise>
</c:choose>

<f:form id="product-form" method="post" modelAttribute="product">
    <table class="form-tab">
        <tr>
            <td><f:label path="productId">Numer:</f:label></td>
            <td><f:input path="productId" placeholder="brak" type="number" readonly="true"/></td>
        </tr>
        <tr>
            <td><f:label path="productName">Nazwa towaru:</f:label></td>
            <td><f:input path="productName" placeholder="nazwa..." type="text"/>
                <f:errors path="productName" cssClass="form-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td><f:label path="price">Cena:</f:label></td>
            <td><f:input path="price" placeholder="12.90" type="number" step="0.01"/>
                <f:errors path="price" cssClass="form-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td><f:label path="vat">Stawka VAT:</f:label></td>
            <td><f:input path="vat" placeholder="0.23" type="number" step="0.01"/>
                <f:errors path="vat" cssClass="form-error" element="div"/>
            </td>
        </tr>
        <tr>
            <td><f:label path="description">Opis:</f:label></td>
            <td><f:textarea path="description" rows="10"/></td>
        </tr>
        <tr>
            <td><f:button>Zapisz</f:button></td>
        </tr>
    </table>
</f:form>

<p><a href="/products">przejdź do listy produktów</a></p>

</body>
</html>



