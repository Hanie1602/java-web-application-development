<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="myDate" value="<%= new java.util.Date() %>"/>

Time on the server : ${myDate}