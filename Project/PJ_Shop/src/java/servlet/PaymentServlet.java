/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dal.PaymentDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.CartItem;
import model.Payment;
import model.PaymentDetail;
import model.Product;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            Account u = (Account) session.getAttribute("user");
            if (u != null && u.getRole() == 0) {
                String action = request.getParameter("action");
                if (action == null) {
                ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
                    if (cart == null || cart.isEmpty()) {
                        request.setAttribute("message", "Your cart is empty");
                    }
                } else if (action.equals("buy")) {
                    ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        double tmp = (double) session.getAttribute("totalPayment");
                        float totalPayment = (float) tmp;
                        String address = request.getParameter("address");
                        String phoneNumber = request.getParameter("phoneNumber");
                        String firstDigit = "";
                        if (!phoneNumber.isEmpty()) {
                           firstDigit = Character.toString(phoneNumber.trim().charAt(0)); 
                        }
                        PaymentDAO paymentDAO = new PaymentDAO();
                        ProductDAO productDAO = new ProductDAO();

                        String[] InputMes = {"", ""};
                        boolean validate = true;
                        if (address.trim().length() < 6 || address.isEmpty()) {
                            InputMes[0] = "Address must more than 6 characters";
                            validate = false;
                        }
                        if (phoneNumber.trim().length() != 10 || phoneNumber.isEmpty() ||
                                !firstDigit.equals("0") || firstDigit.equals("")) {
                            InputMes[1] = "Phone Number must have 10 digit and begin with 0";
                            validate = false;
                        }
                        if (validate == false) {
                            request.setAttribute("InputMes", InputMes);
                        } else {
                            String userId = u.getUserId();
                            Payment payment = new Payment(totalPayment, userId, address, phoneNumber);

                            if (paymentDAO.storePayment(payment) == true) {
                                int paymentId = paymentDAO.getPaymentId(payment);
                                for (CartItem i : cart) {
                                    PaymentDetail detail = new PaymentDetail(paymentId, i.getProduct(),
                                            i.getQuantity(), (i.getQuantity() * i.getProduct().getPrice()));
                                    paymentDAO.storePaymentDetail(detail);
                                    Product product = productDAO.getProductById(i.getProduct().getProductId());
                                    product.setQuantity(product.getQuantity() - i.getQuantity());
                                    productDAO.update(product);
                                }
                                request.setAttribute("message", "Buy successful");
                                session.removeAttribute("cart");
                            } else {
                                request.setAttribute("message", "Buy failed");
                            }
                        }
                    } else {
                        request.setAttribute("message", "Your cart is empty");
                    }
                }
                request.getRequestDispatcher("payment.jsp").forward(request, response);
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
