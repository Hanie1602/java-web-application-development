<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String rem = request.getParameter("rem");
  
    //Neu tick v�o rem th� tao cookie cho username v� password
    if(rem != null) {
        //Khoi tao cookie
        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        
        //Set thoi gian sang cho Cookie
        usernameCookie.setMaxAge(60*60*24*3*30);    //3 months
        passwordCookie.setMaxAge(60*60*24*3*30);    //3 months
        
        //Dua l�n tr�nh duyet Cokkie
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect("login-form.jsp");
    }
    
    //X�a Cookie di khi kh�ng tick v�o rem
    else {
        Cookie[] cookies = request.getCookies();
        
        //Duyet tat ca c�c cookie trong danh s�ch cookie
        //Thang n�o l� username v� password th� x�a n� di
        for(Cookie c : cookies) {
            if(c.getName().equals("username") || c.getName().equals("password"));
            //C�ch x�a nhu the n�o
            //Set cookie value = ""
            c.setValue("");
            //Set tuoi = 0
            c.setMaxAge(0);
            //Goi Cookie moi l�n cho tr�nh duyet
            response.addCookie(c);
    }
    }
%>