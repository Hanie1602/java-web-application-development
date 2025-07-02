
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Khai báo một method-->
        <%!
            String getString(String data){
            return data.toLowerCase();
        }
        %>
        
        Lower case "HELLO WORLD": <%= getString(" HELLO WORLD") %>
                
    </body>
</html>
