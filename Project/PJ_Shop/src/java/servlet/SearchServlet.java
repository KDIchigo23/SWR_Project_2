/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Product;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final float MIN_PRICE = 1;
    private final float MAX_PRICE = 10000;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            Account a = (Account) session.getAttribute("user");
            String cid = request.getParameter("cid");
            String txtSearch = request.getParameter("txtSearch");
            String min = request.getParameter("min");
            String max = request.getParameter("max");

            float minPrice = MIN_PRICE;
            float maxPrice = MAX_PRICE;

            if (min != null && !min.isEmpty()) {
                try {
                    minPrice = Float.parseFloat(min);
                } catch (NumberFormatException ex) {
                    minPrice = MIN_PRICE;
                }
                if (minPrice < MIN_PRICE) {
                    minPrice = MIN_PRICE;
                }
            }

            if (max != null && !max.isEmpty()) {
                try {
                    maxPrice = Float.parseFloat(max);
                } catch (NumberFormatException ex) {
                    maxPrice = MAX_PRICE;
                }
                if (maxPrice > MAX_PRICE) {
                    maxPrice = MAX_PRICE;
                }
            }
            List<Product> listProduct = null;
            if (a != null) {
                if (a.getRole() == 0) {
                    if (min != null && max != null) {
                        listProduct = productDAO.getProductByPrice(minPrice, maxPrice);
                        request.setAttribute("listProduct", listProduct);
                    } else if (txtSearch != null) {
                        listProduct = productDAO.getProductByName(txtSearch);
                        if (!listProduct.isEmpty()) {
                            request.setAttribute("listProduct", listProduct);
                        } else {
                            request.setAttribute("message", "Can not found product!");
                        }
                    }
                    request.setAttribute("min", min);
                    request.setAttribute("max", max);
                    request.setAttribute("txtSearch", txtSearch);
                    request.setAttribute("index", 1);
                    request.setAttribute("endPage", 1);
                    request.setAttribute("listCategory", categoryDAO.getAllCategories());
                    request.setAttribute("tag", cid);
                    request.getRequestDispatcher("user.jsp").forward(request, response);
                } else {
                    if (min != null && max != null) {
                        listProduct = productDAO.getProductByPrice(minPrice, maxPrice);
                        request.setAttribute("listProduct", listProduct);
                    } else if (txtSearch != null) {
                        Product b = productDAO.getProductById(txtSearch);
                        listProduct = productDAO.getProductByName(txtSearch);
                        if (b != null) {
                            listProduct = new ArrayList<>();
                            listProduct.add(b);
                            request.setAttribute("listProduct", listProduct);
                        } else if (!listProduct.isEmpty()) {
                            request.setAttribute("listProduct", listProduct);
                        } else {
                            request.setAttribute("message", "Can not found product!");
                        }
                    }
                    request.setAttribute("min", min);
                    request.setAttribute("max", max);
                    request.setAttribute("txtSearch", txtSearch);
                    request.setAttribute("index", 1);
                    request.setAttribute("endPage", 1);
                    request.setAttribute("listCategory", categoryDAO.getAllCategories());
                    request.setAttribute("tag", cid);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
