<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty userName}">
        <p>Jesteś zalogowany jako <strong>${userName}</strong>.</p>
        <p>Twoje uprawnienia (<i>authorities</i>):</p>
        <ul>
            <c:forEach var="auth" items="${authorities}">
                <li>${auth}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>Nie jesteś zalogowany.</p>
    </c:otherwise>
</c:choose>

<div><a href="/">Powrót do spisu treści</a></div>
</body>
</html>



