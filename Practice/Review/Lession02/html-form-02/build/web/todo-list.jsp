<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<form action="todo-list.jsp">
    Add new item <input type="text" name="item">
    <input type="submit" value="submit">
</form>

<!-- Java code to add new item into todo list -->
<%
    //Nhan du lieu các items có trong session object
    List<String> items =(List<String>) session.getAttribute("myToDoList");
    
    //items bang null thì session chua duoc tao ra
    if(items == null){
        items = new ArrayList<>();
        session.setAttribute("myToDoList", items);
    }
    //out.print("Check isNew session:" + session.isNew());  Kiem tra xem session vua duoc tao ra (true/false)
    out.print("Id session: " + session.getId() + "<br>" + "<br>");
    
    //Nhan du lieu cua item tu request goi lên
    String item = request.getParameter("item");
    if(item != null){
        items.add(item);
    }
    out.print("List to do:");
    
    for(String i : items) {
        out.print("<li>" + i + "</li>");
    }
%>

