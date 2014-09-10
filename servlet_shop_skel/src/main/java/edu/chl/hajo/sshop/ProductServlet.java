package edu.chl.hajo.sshop;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to handle Product pages
 *
 * @author hajo
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ProductServlet.class.getName());
   
    private static final String TEMPLATE = "WEB-INF/jsp/template.jspx";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String view = request.getParameter("view");
        IShop shop = (IShop) getServletContext().getAttribute(Keys.SHOP.toString());

        String title = null;
        String content = null;
            
        // This is navigation only
        if (view != null) {
                      
            switch(view){
                case "add":
                    title = "Add products";
                    content = "products/addProduct";
                    break;
                    
                case "del":
                    title = "Delete products";
                    content = "products/delProduct";
                    
                    Long idDel = Long.parseLong(request.getParameter("id"));
                    request.setAttribute("PRODUCT", shop.getProductCatalogue().find(idDel));
                    break;
                    
                case "edit":
                    title = "Edit product";
                    content = "products/editProduct";
                                        
                    String idEdit = request.getParameter("id");        
                    request.setAttribute("name", shop.getProductCatalogue().find(Long.parseLong(idEdit)).getName());
                    request.setAttribute("price", shop.getProductCatalogue().find(Long.parseLong(idEdit)).getPrice());
                    request.setAttribute("id", idEdit);
                    break;
                    
                default:;
            }
           
        }
        // State change! Then Navigation
        if (action != null) {
            
            switch(action){
                
            case "add":
                String name1 = request.getParameter("name");
                String price1 = request.getParameter("price");
                
                shop.getProductCatalogue().create(new Product(name1, Double.parseDouble(price1)));
                
                response.sendRedirect("shop?view=products");                
                return;
                
            case "del":
                Long id2 = Long.parseLong(request.getParameter("id"));
                shop.getProductCatalogue().delete(id2);
                
                response.sendRedirect("shop?view=products"); 
                return;
                           
            case "edit":
                String name = request.getParameter("name");
                Double price = Double.parseDouble(request.getParameter("price"));
                Long id1 = Long.parseLong(request.getParameter("hidden"));
                
                Product p = new Product(id1, name, price);
                shop.getProductCatalogue().update(p);
              
                response.sendRedirect("shop?view=products");
                return;  
                       
            default:;
            }
            
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
