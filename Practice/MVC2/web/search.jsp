<%@page import="duonglnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USERINFO.fullname}          <!-- Copy ben LoginServlet-->
        </font>
        <!-- Chuc nang Logout -->
        <c:choose>
            <c:when test="${not empty sessionScope.USERINFO}">
                <a href="DispatchServlet?btAction=logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="login.html">Login</a>
            </c:otherwise>
        </c:choose>

        <h1>Search Page</h1>
        <form action = "DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>                            <!--Ctrl + Shift + mui ten len-->
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>                                          <!--Ctrl + Shift + mui ten len-->
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername"
                                           value="${dto.username}" />           <!-- Copy ben UpdateAccountServlet -->
                                </td>
                                <td>
                                    <input type="text" name="txtPassword"
                                           value="${dto.password}" />           <!-- Copy ben UpdateAccountServlet -->
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />    <!-- Copy ben UpdateAccountServlet -->
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete" />                  <!-- Copy ben DispatchServlet -->
                                        <c:param name="pk" value="${dto.username}" />               <!-- Copy ben DeleteAccountServlet -->
                                        <c:param name="lastSearchValue" value="${searchValue}" />   <!-- Copy ben DeleteAccountServlet, value copy o var (ben tren) -->
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />         <!-- Copy ben UpdateAccountServlet -->
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color ="red">
                No record is matched!!!
                </font>
            </h2>
        </c:if>
    </c:if>

    <%-- <%
        HttpSession s = request.getSession();
        String username = (String) s.getAttribute("username");
        if (username == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                username = lastCookie.getName();
                s.getAttribute("username");
    %> 
    <span>
        <h3 style="color:red" display: inline" >Welcome, <%= username%></h3>
        <span style="margin: 0 5px;"></span>
        <a href="login.html" style="display: inline">Logout</a>
    </span>
    <%
            } //login is ok  Scriptlet
        }
    %>

        <h1>Search Page</h1>
        <form action = "DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");       //lay ten tham chieu ben SearchLastnameServlet, 
                if (result != null) {   //information is found
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>             <!--Ctrl + Shift + mui ten len-->
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                               } // end role is an admin
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>
            <%
                } //end get each dto in result
            %>
        </tbody>
    </table>

    <%
    } else {    //no record is matched
    %>
    <h2>
        <font color ="red">
        No record is matched!!!
        </font>
    </h2>
    <%
            } //end no record is matched
        } //end search value is called second times or request from user
    %>
    --%>

</body>
</html>
