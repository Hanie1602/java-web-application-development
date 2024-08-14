package pe.fall23.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.fall23.dao.ProductDAO;
import pe.fall23.shopping.Cart;

public class ViewCart extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("carts") == null) {
            List<Cart> carts = new ArrayList<>();
            session.setAttribute("carts", carts);
        }
        List<Cart> carts = (ArrayList<Cart>) session.getAttribute("carts");
        float amount = 0;
        for (int i = 0; i < carts.size(); ++i) {
            amount += carts.get(i).getProduct().getPrice() * 1.0 * carts.get(i).getQuantity();
        }
        request.setAttribute("carts", carts);
        request.setAttribute("amount", amount);
        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
