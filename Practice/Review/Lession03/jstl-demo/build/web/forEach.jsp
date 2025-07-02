<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<Student> students = new ArrayList<>();
    students.add(new Student("Nguyen Van", "A", true));
    students.add(new Student("Nguyen Van", "B", true));
    students.add(new Student("Tran Thi", "C", false));
    
    pageContext.setAttribute("myStudents", students);
%>

<table border="1px">
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tempStudent" items="${myStudents}">
            <tr>
                <td>${tempStudent.firstName}</td>
                <td>${tempStudent.lastName}</td>
                <td>
                    <!--Cach 1: Dung if-else
                    <c:if test="${tempStudent.gender}">
                        Nam
                    </c:if>
                        
                    <c:if test="${!tempStudent.gender}">
                        Nu
                    </c:if> 
                    -->
                    
                    
                    <!--Cach 2: Dung switch-case -->
                    <c:choose>
                        <c:when test="${tempStudent.gender}">
                            Nam
                        </c:when>
                        <c:otherwise>
                            Nu
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            
        </c:forEach>
    </tbody>
</table>
