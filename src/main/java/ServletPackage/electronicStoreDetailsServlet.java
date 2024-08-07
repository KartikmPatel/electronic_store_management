/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServletPackage;

import EJBPackage.electronicStoreDetailsEJB;
import EntityPackage.ElectronicStoreDetails;
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
 * @author Admin
 */
@WebServlet(name = "electronicStoreDetailsServlet", urlPatterns = {"/electronicStoreDetailsServlet"})
public class electronicStoreDetailsServlet extends HttpServlet {
    @EJB electronicStoreDetailsEJB esd;
    
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
            out.println("<title>Servlet electronicStoreDetailsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet electronicStoreDetailsServlet at " + request.getContextPath() + "</h1>");
            
//            esd.addElectronicStoreDetails("vvebrgbrgb", "sdfdsfsdf", 65363364, "fffgfg", "grggtgt", "fefefef", "dsfssgrg");

//            esd.updateElectronicStoreDetails(1, "fffgff", "ffgffgfgf", 323213, "xvcvcvxcv", "dfsfdf", "vbvbvxbvxb", "SSDADS");

//            esd.deleteElectronicStoreDetails(1);
            Collection<ElectronicStoreDetails> electronicStoreDetails = esd.displayElectronicStoreDetails();
            
            for(ElectronicStoreDetails ed: electronicStoreDetails){
                out.println(ed.getStoreName() + " " + ed.getEmail() + " " + ed.getContactNo() + " " + ed.getPassword() + " " + ed.getStoreLogo() + " " + ed.getAddress() + " " + ed.getCountry());
            }
            
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
