package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is for overall navigation between 
 * major parts of application. No state change
 *
 * @author hajo
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IShop shop = (IShop) getServletContext().getAttribute(Keys.SHOP.toString());
        HttpSession session = request.getSession();

        String view = request.getParameter("view");
        String title = null;
        String content = null;
        switch (view) {
            case "products":
                title = "Products";
                content = "products/products";
                // Below should be common to all pages so later refactor...
                // Fill in data for page
                String page = request.getParameter("page");
                int currPage = 0;
                if (page != null) {
                    currPage = Integer.valueOf(page);
                }          
                session.setAttribute(Keys.CURRENT_PAGE.toString(), currPage);
                int pageSize = (int) session.getAttribute(Keys.PAGE_SIZE.toString());
                List<Product> ps = shop.getProductCatalogue().findRange(currPage * pageSize, pageSize);
                request.setAttribute(Keys.PRODUCT_LIST.toString(), ps);
                request.setAttribute(Keys.COUNT.toString(), shop.getProductCatalogue().count());

                break;
            case "orders":
                title = "Orders";
                content = "orders/orders";
                break;
            case "customers":
                title = "Customers";
                content = "customers/customers";
            default:;

        }
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("WEB-INF/jsp/template.jspx").forward(request, response);
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
