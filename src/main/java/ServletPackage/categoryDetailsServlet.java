/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServletPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kartik Patel
 */
@WebServlet(name = "categoryDetailsServlet", urlPatterns = {"/categoryDetailsServlet"})
public class categoryDetailsServlet extends HttpServlet {

    @EJB
    EJBPackage.categoryDetailsEJB cde;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet categoryDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");

//            cde.addcategorydetails("Mobile", 1);
//            Collection<CategoryDetails> categorydetails = cde.getAllCategory();
//            for (CategoryDetails cd : categorydetails) {
//                out.println(cd.getCategoryName() + " " + cd.getCompanyId().getCompanyName());
//            }

//            Collection<CompanyDetails> cd = cde.getAllCompany();
//            out.println("<select>");
//            for (CompanyDetails c : cd) {
//                out.println("<option value=" + c.getCompanyId() + ">" + c.getCompanyName() + "</option>");
//            }
//            out.println("</select>");

//               cde.updateCategory(1, "TV", 2);
//                cde.deleteCategory(1);
            out.println("</body>");
            out.println("</html>");
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
