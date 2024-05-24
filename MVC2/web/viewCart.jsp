<%--<%@page import="java.util.Map"%>
<%@page import="duonglnt.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1> 
    <c:if test="${not empty sessionScope.CART}">
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart.items}">
            <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:set var="total" value="0" />
                    <c:forEach var="item" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${item.value.name}</td>
                            <td>${item.value.quantity}</td>
                            <td>${item.value.price}</td>
                            <td>${String.format("%.2f", item.value.quantity * item.value.price)}</td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${item.key}" />
                            </td>
                        </tr>
                        <c:set var="total" value="${total + (item.value.quantity * item.value.price)}" />
                    </c:forEach>
                    <tr>
                    <c:url var="GoToShoppingLink" value="DispatchServlet">
                        <c:param name="btAction" value="Go to shopping"/>
                    </c:url>
                    <td colspan="4">
                        <a href="${GoToShoppingLink}">Add More Clothes to Your Cart</a>
                    </td>
                    <td>
                        ${String.format("%.2f",total)}
                    </td>
                    <td>
                        <input type="submit" value="Remove Selected Items" name="btAction" />
                    </td>
                    </tr>
                    </tbody>
                </table>
                Name <input type="text" name="txtUsername" value=""/>
                <input type="submit" value="CheckOut" name="btAction" />
                <input type="hidden" name="total" value="${total}" />
            </form>
        </c:if>
        <c:if test="${empty cart.items}">
            <h2>
                <font color="red">
                No cart is existed!!!!!
                </font>
            </h2>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.CART}">
        <h2>
            <font color="red">
            No cart is existed!!!!!
            </font>
        </h2>    
    </c:if>

</body>

<%--
<h1>Book Store</h1>
<%
    //1. Customer goes to his/her cart place
        //Session co san roi boi vi no co trong session object
    if (session != null) {
        //2. Customer takes his/her cart
        CartObject cart = (CartObject) session.getAttribute("CART");
        if (cart != null) {
            //3. Customer gets items
            Map<String, Integer> items = cart.getItems();
            if (items != null) {
                //4. Custommer shows all item
                %> 
                <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            int count = 0;
                            for (String key : items.keySet()){
                                %>
                        <tr>
                            <td>
                                <%= ++count %> 
                            .</td>
                            <td>
                                <%= key %>
                            </td>
                            <td>
                                <%= items.get(key) %>
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" 
                                       value="<%= key %>" />
                            </td>
                        </tr>
                        <%
                            } //traverse items
                        %>
                        <tr>
                            <td colspan="3">
                                <a href="product.html">Add More Books to Your Cart</a>
                            </td>
                            <td>
                                <input type="submit" 
                                       value="Remove Selected Items" 
                                       name="btAction" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>

        <%
                        return;
                    } //items have existed
                } // cart has existed
            } //Session has existed
        %>

        <h2>
            <font color="red">
            No cart is existed!!!!!
            <font/>
        </h2> --%>
</html>

<!-- Gio hang duoc dat trong Attribute -->
