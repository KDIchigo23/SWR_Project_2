/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Payment;
import model.PaymentDetail;
import model.Product;

/**
 *
 * @author 84969
 */
public class PaymentDAO extends DBContext {

    public boolean storePayment(Payment payment) throws SQLException {

        try {
            String sql = "Insert into Payment(userID, dateCreate, totalPayment, address, phoneNumber, status) "
                    + "Values(?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, payment.getUserId());
            stm.setDate(2, Date.valueOf(LocalDate.now()));
            stm.setFloat(3, payment.getTotalPayment());
            stm.setString(4, payment.getAddress());
            stm.setString(5, payment.getPhoneNumber());
            stm.setInt(6, payment.getStatus());
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean storePaymentDetail(PaymentDetail detail) throws SQLException {

        try {
            String sql = "Insert into PaymentDetail(paymentId, productId, quantity, subTotal) "
                    + "Values(?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, detail.getPaymentId());
            stm.setString(2, detail.getProduct().getProductId());
            stm.setInt(3, detail.getQuantity());
            stm.setFloat(4, detail.getSubTotal());
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getPaymentId(Payment payment) throws SQLException {
        try {
            String sql = "select paymentId from Payment where userID=? and dateCreate=? and totalPayment=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, payment.getUserId());
            stm.setDate(2, payment.getDateCreate());
            stm.setFloat(3, payment.getTotalPayment());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int paymentId = rs.getInt("paymentId");
                return paymentId;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public ArrayList<Payment> getPaymentByUserId(String userId) throws SQLException {
        ArrayList<Payment> list = null;
        try {
            String sql = "select * from Payment where userID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("paymentId"),
                        rs.getDate("dateCreate"),
                        rs.getFloat("totalPayment"), 
                        rs.getString("userID"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getInt("status"));
                    list.add(payment);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<PaymentDetail> getPaymentDetail(int paymentId) throws SQLException {
        ArrayList<PaymentDetail> list = null;
        try {
            String sql = "select * from PaymentDetail where paymentId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, paymentId);
            ResultSet rs = stm.executeQuery();
            ProductDAO dao = new ProductDAO();
                while (rs.next()) {
                    Product p = dao.getProductById(rs.getString("productId"));
                    PaymentDetail detail = new PaymentDetail(
                            paymentId, 
                            p,
                            rs.getInt("quantity"),
                            rs.getFloat("subTotal")
                    );
                    list.add(detail);
                }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Payment> getAllPayment() throws SQLException {
        ArrayList<Payment> list = null;
        try {
            String sql = "select * from Payment";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("paymentId"),
                        rs.getDate("dateCreate"),
                        rs.getFloat("totalPayment"), 
                        rs.getString("userID"),
                        rs.getString("address"),
                        rs.getString("phoneNumber"),
                        rs.getInt("status"));
                    list.add(payment);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public PaymentDetail checkProduct(String productId) throws Exception {
        try {String sql = "select * from PaymentDetail where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ProductDAO dao = new ProductDAO();
            while (rs.next()) {
                Product p = dao.getProductById(rs.getString("productId"));
                    PaymentDetail detail = new PaymentDetail(
                            rs.getInt("paymentId"), 
                            p,
                            rs.getInt("quantity"),
                            rs.getFloat("subTotal")
                    );
                    return detail;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
}
