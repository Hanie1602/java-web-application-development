<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fantasy shopping</title>
    </head>
    <body>
        Load product below
        <!--your code here-->
        <form action="search-all">
            <input type="submit" value="Search all">
        </form>
        <form action="view-cart">
            <input type="submit" value="View cart">
        </form>
        <h2>${message}</h2>
        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Add</th>
            </tr>
            <tbody>
                <c:forEach var="item" items="${products}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.price}</td>
                        <td>
                            <form action="add-to-cart">
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="submit" value="Add">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
