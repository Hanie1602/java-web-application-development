<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="product" value="${requestScope.PRODUCTS}"/>
        <c:if test="${not empty product}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${product}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.description}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>
                                <input type="submit" value="Add Book to Your Cart" name="btAction" />
                                <input type="hidden" name="cboBook" value="${dto.id}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:url var="ViewCartLink" value="DispatchServlet">
            <c:param name="btAction" value="View Your Cart"/>
        </c:url>
        <a href="${ViewCartLink}">View Your Cart</a>
    </c:if>
    <c:if test="${empty product }">
        <h2>
            <font color="red">
            No product in here
            </font>
        </h2> 
    </c:if>
</body>
</html>