<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

fn:length : Lay do dai cua 1 sau <br>
fn:upperCase: chuyen viet thuong thanh viet hoa <br>
fn:split: tach sau thanh 1 mang cac string <br>
<br>

<c:set var="text" value="Twincode"></c:set>
<c:set var="str" value="Java|C|C++|NodeJS"></c:set>
<c:set var="splitStr" value="${fn:split(str,'|')}"></c:set>
<c:set var="joinedStr" value="${fn:join(splitStr,', ')}"></c:set>

In chu ra man hinh: <c:out value="${text}"></c:out> <br>
Length: ${fn:length(text)} <br>
Uppercase: ${fn:toUpperCase(text)} <br>
Before join: <c:out value="${str}"></c:out> <br>
After join: <c:out value="${joinedStr}"></c:out>