package duonglnt.servlet;

import duonglnt.cart.CartObject;
import duonglnt.product.ProductDTO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

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
        try {
            //1. Customer goes to his/her cart placce
            HttpSession session = request.getSession(false);            //Chon False: check xem no co ton tai hay khong
            if(session != null) {                                       //Check session co ton tai, boi vi dang lam chuc nang xoa
                //2. Customer takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null){
                    //3. Customer gets items (Cust lay ngan chua do)
                    Map <String, ProductDTO> items = cart.getItems();
                    if(items != null){
                        //4. Customer removes item from items           (Cust lay do ra khoi ngan chua do)
                        String [] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null){
                            for(String item : selectedItems) {
                                cart.removeItemFromCart(item);
                            }
                        } //user must check atleast one item
                        //Cap nhat lai Gio hang (Attribute)de Set Attribute
                        session.setAttribute("CART", cart);
                    }
                } //cart has existed
            } //session has existed
        } finally {
            //refresh --> call previous function again using URL rewriting technique
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting);        //Khong dung forward, boi vi trung btAction
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
