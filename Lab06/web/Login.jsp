<%-- 
    Document   : Login
    Created on : Mar 10, 2024, 7:01:23 PM
    Author     : truon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to website</title>
    </head>
    <body>
        <h1>Login to website</h1>
        <form action="LoginController" method="POST">
            UserName <input type="text" name="txtUserName" /><br/>
            Password <input type="password" name="txtPassword" /><br/>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="Reset" /><br/>
            <a href="CreateUser.jsp">Click here to Sign up</a><br/>
        </form>
        <c:set var="message" value="${requestScope.message}" />
        <c:if test="${message != null}">
            <text style="color: red">${message}</text>
        </c:if>
    </body>
</html>
