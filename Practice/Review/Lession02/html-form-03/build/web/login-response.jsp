<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String rem = request.getParameter("rem");
  
    //Neu tick vào rem thì tao cookie cho username và password
    if(rem != null) {
        //Khoi tao cookie
        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        
        //Set thoi gian sang cho Cookie
        usernameCookie.setMaxAge(60*60*24*3*30);    //3 months
        passwordCookie.setMaxAge(60*60*24*3*30);    //3 months
        
        //Dua lên trình duyet Cokkie
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect("login-form.jsp");
    }
    
    //Xóa Cookie di khi không tick vào rem
    else {
        Cookie[] cookies = request.getCookies();
        
        //Duyet tat ca các cookie trong danh sách cookie
        //Thang nào là username và password thì xóa nó di
        for(Cookie c : cookies) {
            if(c.getName().equals("username") || c.getName().equals("password"));
            //Cách xóa nhu the nào
            //Set cookie value = ""
            c.setValue("");
            //Set tuoi = 0
            c.setMaxAge(0);
            //Goi Cookie moi lên cho trình duyet
            response.addCookie(c);
    }
    }
%>