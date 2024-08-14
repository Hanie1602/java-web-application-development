package pe.fall23.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.fall23.dao.ProductDAO;
import pe.fall23.shopping.Cart;
import pe.fall23.shopping.Product;

public class AddToCart extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    private final SearchAll searchAll = new SearchAll();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        if (session.getAttribute("carts") == null) {
            List<Cart> carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        Product p = productDAO.getProductById(id);
        Cart cart = null;
        for (int i = 0; i < carts.size(); ++i) {
            if (carts.get(i).getProduct().getId().equals(id)) {
                cart = carts.get(i);
                break;
            }
        }
        if (cart == null) {
            cart = new Cart(p, 1);
            carts.add(cart);
        } else {
            cart.setQuantity(cart.getQuantity() + 1);
        }
        request.setAttribute("message", "Add product with id " + id + " to cart successfully! New quantity: " + cart.getQuantity());
        searchAll.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
