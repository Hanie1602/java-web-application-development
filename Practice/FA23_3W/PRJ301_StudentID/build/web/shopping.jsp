<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fantasy shopping</title>
    </head>
    <body>
        Load product below
        <!--your code here-->
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Search All" />
            <input type="submit" name="action" value="View cart" />
        </form>
        <c:if test="${not empty requestScope.SEARCH_RESULT}">
            <c:set var="searchResult" value="${requestScope.SEARCH_RESULT}"/>
        </c:if>
        <c:if test="${not empty searchResult}">

            <table border="1">
                <thead>
                <th>productID</th>
                <th>productName</th>
                <th>description</th>
                <th>price</th>
                <th>action</th>
            </thead>
            <tbody>
                <c:forEach var="product" items="${searchResult}">
                <form action="MainController" method="POST">
                    <tr>
                        <td>${product.productID}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>   
                            <input type="hidden" name="productId" value="${product.productID}" />
                            <input type="submit" name="action" value="Add" />                                    
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<c:set var="message" value="${requestScope.MESSAGE}" />
<c:if test="${not empty message}">
    ${message}
</c:if>
</body>
</html>
