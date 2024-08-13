package Controllers.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    private final String loginPage = "Login.jsp";
    private static final String searchController = "SearchUser";
    private static final String userDetailsController = "UserDetailsController";
    private static final String createController = "CreateController";
    private static final String deleteController = "DeleteUser";
    private static final String updateController = "UpdateUser";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = null;
        try {
            HttpSession session = request.getSession(false);
            boolean isLoggedIn = (session.getAttribute("UserLoggedIn") != null);
            if (action.equalsIgnoreCase("Create")) {
                url = createController;
            } else if (isLoggedIn) {
                if (action.equalsIgnoreCase("Delete")) {
                    url = deleteController;
                } else if (action.equalsIgnoreCase("Update")) {
                    url = updateController;
                } else if (action.equalsIgnoreCase("Search")) {
                    url = searchController;
                } else if (action.equalsIgnoreCase("Details")) {
                    url = userDetailsController;
                }
            } else {
                url = loginPage;
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
