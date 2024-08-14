package duonglnt.servlet;

import duonglnt.cart.CartObject;
import duonglnt.orderDetail.OrderDetailDAO;
import duonglnt.orderDetail.OrderDetailDTO;
import duonglnt.product.ProductDAO;
import duonglnt.product.ProductDTO;
import duonglnt.tOrder.TOrderDAO;
import duonglnt.tOrder.TOrderDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String ERROR_PAGE = "errors.html";
    private final String SHOW_ORDER_PRODUCT = "showOrder.jsp";
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
        String url = ERROR_PAGE;
         try {
            //1. cust goes to the cart place 
            HttpSession session = request.getSession();
            //2. cust takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            //Check to see if the cart already has items
            if (cart != null) {
                if (!cart.getItems().isEmpty()) {
                    String username = request.getParameter("txtUsername");
                    float grandTotal = Float.parseFloat(request.getParameter("total"));
                    TOrderDAO dAO = new TOrderDAO();
                    TOrderDTO orderDTO = dAO.createTOrder(username, grandTotal);
                    if (orderDTO != null) {
                        session.setAttribute("USER_ORDER", orderDTO);
                        List<OrderDetailDTO> purchasedItems = new ArrayList<>();
                        for (Map.Entry<String, ProductDTO> entry : cart.getItems().entrySet()) {
                            ProductDTO productDTO = entry.getValue();
                            String name = productDTO.getName();
                            int quantity = productDTO.getQuantity();
                            float price = productDTO.getPrice();
                            float total = quantity * price;
                            OrderDetailDAO detailDAO = new OrderDetailDAO();
                            OrderDetailDTO detailDTO = new OrderDetailDTO(name, price, quantity, total, orderDTO, productDTO);
                            boolean result1 = detailDAO.insertOrderDetail(detailDTO);
                            if (result1) {
                                purchasedItems.add(detailDTO);
                                ProductDAO productDAO = new ProductDAO();
                                boolean result2 = productDAO.decreaseQuantity(productDTO.getId(), quantity);
                                if (result2) {
                                    url = SHOW_ORDER_PRODUCT;
                                }
                            }
                        }
                        session.setAttribute("PRODUCT_ORDER", purchasedItems);
                    }
                }
                cart.clear();
            }

        } catch (SQLException ex) {
            log("CheckOutServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_Naming: " + ex.getMessage());
        } finally {
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
