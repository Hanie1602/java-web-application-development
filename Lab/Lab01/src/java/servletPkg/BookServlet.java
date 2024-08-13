package servletPkg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("txtId");
        String title = request.getParameter("txtTitle");
        String author = request.getParameter("txtPublisher");
        double price = Double.parseDouble(request.getParameter("txtPrice"));
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        double subTotal = price * quantity;
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            //create table
            StringBuilder strBookDetail = new StringBuilder();
            strBookDetail.append("<table border='1'>");
            strBookDetail.append("<head>");
            strBookDetail.append("<tr>");
            strBookDetail.append("<th>BookId</th>");
            strBookDetail.append("<th>Tittle</th>");
            strBookDetail.append("<th>Publisher</th>");
            strBookDetail.append("<th>Price</th>");
            strBookDetail.append("<th>Quantity</th>");
            strBookDetail.append("<th>SubTotal</th>");
            strBookDetail.append("<th>SubTotal</th>");
            strBookDetail.append("</tr>");
            strBookDetail.append("</head>");
            strBookDetail.append("<tbody>");
            strBookDetail.append("<tr>");
            strBookDetail.append("<td>" + id + ".</td>");
            strBookDetail.append("<td>" + title + ".</td>");
            strBookDetail.append("<td>" + author + "</td>");
            strBookDetail.append("<td>" + price + "</td>");
            strBookDetail.append("<td>" + quantity + "</td>");
            strBookDetail.append("<td>" + subTotal + "</td>");
            strBookDetail.append("</tr>");
            strBookDetail.append("</tbody>");
            strBookDetail.append("</table>");
            out.print(strBookDetail.toString());
            out.print("<a href='createBook.html'>Back</a></br>");
            strBookDetail.append("</tbody>");
            strBookDetail.append("</html>");
        } finally {
            
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
