//Controller: Servlet dieu phoi
package duonglnt.servlet;

import duonglnt.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javafx.application.Application;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})       //name mapping cho Javaclass nay va mapping cho urlPatterns
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";                                 //Copy tu value cua login ben login.html
    private final String LOGIN_CONTROLLER = "loginServlet";                         //Copy tu <url-pattern> ben web.xml
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_CONTROLLER = "StartupServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";
    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String PRODUCT_DISPLAY_CONTROLLER = "ProductDisplayServlet";
    private final String CHECKOUT_CONTROLLER = "CheckOutServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. Which button did user click?                                       Thong tin duoc truyen tu clien chuyen ve phia server goi la request parameter
        String button = request.getParameter("btAction");                       //Copy name(login.html) dan vao getParameter
        String url = LOGIN_PAGE;

        try {
            if (button == null) {                       //first time or app starts up
                //transfer login page/do nothing
                //check Cookies to determine which page is tranfered
                url = STARTUP_CONTROLLER;
            } else if (button.equals("Login")) {        //user clicked Login
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {       //user clicked Search
                url = SEARCH_LASTNAME_CONTROLLER;
                System.out.println("Da di toi day");
            } else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            } else if (button.equals("Create New Account")) {
                url = CREATE_NEW_ACCOUNT_CONTROLLER;
            } else if (button.equals("logout")) {           //Logout
                HttpSession session = request.getSession();
                session.removeAttribute("USERINFO");
                url = LOGIN_PAGE;
            } else if (button.equals("Go to shopping")) {
                url = PRODUCT_DISPLAY_CONTROLLER;
            } else if (button.equals("CheckOut")) {
                url = CHECKOUT_CONTROLLER;
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
