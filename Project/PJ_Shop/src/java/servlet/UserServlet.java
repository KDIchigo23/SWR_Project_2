/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int i = 1;
            if (request.getParameter("i") != null) {
                i = Integer.parseInt(request.getParameter("i"));
            }
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            String cid = request.getParameter("cid");
            int count = productDAO.count();
            int endPage = count / 3;
            if (count % 3 != 0) {
                endPage++;
            }
            String message = request.getParameter("message");
            if (message != null) {
                request.setAttribute("message", message);
            }
            request.setAttribute("listProduct", productDAO.getTop3Product(i));
            request.setAttribute("listCategory", categoryDAO.getAllCategories());
            request.setAttribute("tag", cid);
            request.setAttribute("index", i);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
