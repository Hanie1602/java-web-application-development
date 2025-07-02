<form action="login-response.jsp">
    Username <input type="text" name="username" value="${cookie.username.getValue()}">   <br>
    Password: <input type="text" name="password" value="${cookie.password.getValue()}">     <br>
    Remember me: <input type="checkbox" name="rem" value="checked">     <br>
    <input type="submit" value="submit">
</form>