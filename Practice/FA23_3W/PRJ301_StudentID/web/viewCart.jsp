<%-- 
    Document   : viewCart
    Created on : Dec 7, 2023, 4:07:48 PM
    Author     : hd
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>You shopping cart</title>
    </head>
    <body>
        <h1>You shopping cart</h1>
        <c:set var="message" value="${requestScope.MESSAGE}" />
        <c:if test="${not empty message}">
            ${message}
        </c:if>
        <c:if test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart.items}">
                <table border="1">
                    <thead>
                    <th>No</th>
                    <th>productID</th>
                    <th>productName</th>
                    <th>description</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>amount</th>
                </thead>

                <c:set var="total" value="0"/>
                <c:forEach var="item" items="${cart.items}" varStatus="counter">
                    <form action="MainController" method="POST">
                        <c:forEach var="product" items="${cart.productsInCart}">
                            <c:if test="${product.productID eq item.key}">
                                <c:set var="itemInfor" value="${product}"/>
                            </c:if>
                        </c:forEach>
                        <c:set var="total" value="${total + itemInfor.price*item.value}"/>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${itemInfor.productID}</td>
                            <td>${itemInfor.productName}</td>
                            <td>${itemInfor.description}</td>
                            <td>${itemInfor.price}</td>
                            <td>
                                <input type="number" name="quantity" min="1" value="${item.value}" />
                                <input type="hidden" name="productID" value="${item.key}" />
                            </td>
                            <td>${itemInfor.price*item.value}</td>
                            <td><input type="submit" name="action" value="Update" /></td>
                        </tr>
                    </form>
                </c:forEach>
            </table>              
            <h2>Total: ${total}</h2>
        </c:if>
    </c:if>
    <c:url var="GoToShoppingLink" value="MainController">
        <c:param name="action" value="Back to shopping"/>
    </c:url>
    <a href="${GoToShoppingLink}">Back to shopping</a>
</body>
</html>
