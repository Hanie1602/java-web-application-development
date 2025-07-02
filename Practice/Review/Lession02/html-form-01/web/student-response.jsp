<!-- Expression Language -->
<h1>Confirmed Information</h1>
<p>Student name: ${param.name}</p>

<!-- Java code -->
<p>Student age: <%= request.getParameter("age") %> </p>

<!-- Drop down, radio button, checkbox -->
<p>Student country: ${param.country}</p>

<p>Student favorite language: ${param.favoriteLanguage}</p>

<p>Fruits that students like:</p>
<% 
    String[] fruits = request.getParameterValues("check");
    out.print("<ul>");
    for(String item : fruits){
        out.print("<li>" + item + "</li>");
    }
    out.print("</ul>");
%>