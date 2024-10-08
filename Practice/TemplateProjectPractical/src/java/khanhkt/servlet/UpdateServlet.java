package khanhkt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhkt.account.AccountDAO;
import khanhkt.utils.MyAppConstants;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //0. get context scope & get siteMaps attribute
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        
        //1.get all parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String ckAdmin = request.getParameter("ckAdmin");
        boolean isRole = false;
        if(ckAdmin != null){
            isRole = true;
        }
        
        String searchValue = request.getParameter("txtSearchValue");
        String url = siteMaps.getProperty(MyAppConstants.LoginFeatures.INVALID_PAGE);
        try {
            //2.call DAO 
            //2.1 new DAO Object
            AccountDAO dao = new AccountDAO();
            //2.2 call method of DAO
            boolean result = dao.updateAccount(username, password, isRole);
            //3.process result
            if (result) {
               //call previous function again using URL Rewriting technique
               url = "DispatchServlet"
                       + "?btnAction=Search"
                       + "&txtSearchValue=" + searchValue;
            }
            
        } catch (SQLException ex) {
            log("UpdateAccountServlet_SQL: " + ex.getMessage());  
        } catch (NamingException ex) {
            log("UpdateAccountServlet_Naming: " + ex.getMessage());
        }
        finally {
            response.sendRedirect(url);
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
