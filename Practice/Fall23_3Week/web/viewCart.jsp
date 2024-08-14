<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>You shopping cart</title>
    </head>
    <body>
        <h1>You shopping cart</h1>
        <a href="${pageContext.request.contextPath}/">Back to shopping page</a>
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Amount</th>
            </tr>
            <tbody>
            <c:forEach var="item" items="${carts}">
                <tr>
                    <td>${item.product.id}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.description}</td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.product.price * 1.0 * item.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
        <h3>Amount: ${amount}</h3>
    </table>
</body>
</html>
