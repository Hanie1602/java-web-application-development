
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color:lightgreen">Hello World!</h1>
        The time on the server is <%= new java.util.Date() %>
        
        <!-- JSP Expression -->
        <br>
        25 * 4 = <%= 25 * 4 %>
        <br>
        3 < 5 ? <%= 3 < 5 %>
        <br>
        Converting a string to uppercase: <%= new String("Hello World").toUpperCase() %>
    </body>
</html>
