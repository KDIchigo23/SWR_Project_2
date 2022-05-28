/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;


@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

    private final String loginPage = "login.jsp";
    private final String adminPage = "admin.jsp";
    private final String userPage = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                Account a = (Account) session.getAttribute("user");
                String cid = request.getParameter("cid");
                ProductDAO productDAO = new ProductDAO();
                CategoryDAO categoryDAO = new CategoryDAO();
                List<Product> listProduct = productDAO.getProductByCategoryId(cid);
                List<Category> listCategory = categoryDAO.getAllCategories();

                request.setAttribute("listProduct", listProduct);
                request.setAttribute("listCategory", listCategory);
                request.setAttribute("tag", cid);
                if (a.getRole() == 1) {
                    request.getRequestDispatcher(adminPage).forward(request, response);
                } else {
                    request.getRequestDispatcher(userPage).forward(request, response);
                }
            } else {
                request.setAttribute("message", "login first!");
                request.getRequestDispatcher(loginPage).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
