
package duonglnt.servlet;

import duonglnt.product.ProductDAO;
import duonglnt.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductDisplayServlet", urlPatterns = {"/ProductDisplayServlet"})
public class ProductDisplayServlet extends HttpServlet {
    private String PRODUCT_PAGE =  "product.html";
    private String SHOW_PRODUCT_PAGE = "product.jsp";

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
        
        //1. get all Client information
        String url = SHOW_PRODUCT_PAGE;
        
        try {
            // Đoạn code để lấy dữ liệu từ database và đặt nó vào danh sách sản phẩm
            //2. call Model/DAO
            //2.1 New DAO obj
            ProductDAO productDAO = new ProductDAO();
            //2.2 Call method of DAO
            productDAO.uploadProductsFromDatabase();
            List<ProductDTO> products = productDAO.getProducts();
            
            // Đặt danh sách sản phẩm vào session để sử dụng ở các trang khác
            //3. Process result
            request.setAttribute("PRODUCTS", products);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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
