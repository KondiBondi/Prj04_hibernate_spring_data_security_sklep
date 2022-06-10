<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aplikacja Sklep Spring</title>
    <link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<%-- Przykładowe działanie, aby sprawdzić, czy JSP faktycznie działa. --%>
<p>Wynik działania: ${2 + 3 * 4}</p>

<h1>Spis treści</h1>
<ul>
    <li><a href="/">Spis treści</a></li>
    <li><a href="/hello">Hello world</a></li>
    <li><a href="/time">Która godzina</a></li>
</ul>

<h2>Logowanie</h2>
<ul>
    <li><a href="/login">zaloguj się</a>
    <li><a href="/logout">wyloguj się</a>
    <li><a href="/whoami">sprawdź kim jesteś</a>
</ul>

<h2>Produkty - wersja właściwa</h2>
<ul>
    <li><a href="/products">Lista wszystkich produktów</a></li>
    <li><a href="/products/1">Jeden produkt</a></li>
    <li><a href="/products/find">Wyszukiwarka</a></li>
    <li><a href="/products/new">Dodaj nowy produkt</a></li>
    <hr>
    <li><a href="/customers">lista klientów</a>
    <li><a href="/customers/new">nowy klient</a>
    <li><a href="/customers/ala@example.com/edit">edycja klienta</a>
</ul>

<h2>Produkty - wersje robocze</h2>
<ul>
    <li><a href="/products1">1 lista</a></li>
    <li><a href="/products1/1">1 produkt</a></li>
    <li><a href="/products1/find">1 find</a></li>
    <li><a href="/products2">2 lista</a></li>
    <li><a href="/products2/1">2 produkt</a></li>
    <li><a href="/products2/find">2 find</a></li>
    <li><a href="/products3">3 lista</a></li>
    <li><a href="/products3/1">3 produkt</a></li>
    <li><a href="/products3/find">3 find</a></li>
    <li><a href="/products5">5 lista</a></li>
    <li><a href="/products5/1">5 produkt</a></li>
</ul>

<h2>REST</h2>
<ul>
    <li><a href="/rest/products">lista produktów</a></li>
    <li><a href="/rest/products/1">jeden produkt</a></li>
    <li><a href="/rest/products/1/price">cena</a></li>
    <li><a href="/rest/orders">zamówienia</a></li>
    <li><a href="/rest/orders/1">jedno zamówienie</a></li>
</ul>

</body>
</html>



