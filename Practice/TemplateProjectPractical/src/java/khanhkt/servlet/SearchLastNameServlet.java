package khanhkt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhkt.account.AccountDAO;
import khanhkt.account.AccountDTO;
import khanhkt.utils.MyAppConstants;

@WebServlet(name = "SearchLastName", urlPatterns = {"/SearchLastName"})
public class SearchLastNameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. get context scope & get siteMaps attribute
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");

        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(MyAppConstants.SearchAccountFeatures.SEARCH_PAGE);

        try {
            System.out.println("Controller");
            if (!searchValue.trim().isEmpty()) {
                System.out.println("Bat dau Search");
                //2. call Model/DAO
                //2.1 New DAO obj
                AccountDAO dao = new AccountDAO();
                //2.2 Call method of DAO
                dao.searchAccountByFullName(searchValue);
                List<AccountDTO> result = dao.getAccounts();
                //3. Process result
                url = siteMaps.getProperty(MyAppConstants.SearchAccountFeatures.SEARCH_HOME);
                request.setAttribute("SEARCH_RESULT", result);
            } //end searchValue has inputted valid value

        } catch (SQLException ex) {
            log("SearchLastnameServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastnameServlet_Naming: " + ex.getMessage());
        } finally {
            //4. Send to View (+ request scope)
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
