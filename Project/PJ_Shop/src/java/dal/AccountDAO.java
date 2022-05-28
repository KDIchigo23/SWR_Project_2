/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author 84969
 */
public class AccountDAO extends DBContext {

    public Account checkLogin(String userId, String password) throws ClassNotFoundException, SQLException {
        try {
            String sql = "Select * from Account where userID=? and password=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userId);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(userId, password, rs.getString("fullName"), rs.getInt("roleID"));
                return a;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountById(String userId) throws ClassNotFoundException, SQLException {
        try {
            String sql = "Select * from Account where userID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm = connection.prepareStatement(sql);
            stm.setString(1, userId.toUpperCase());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(userId, rs.getString("password"), rs.getString("fullName"), rs.getInt("roleID"));
                return a;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account signUp(Account acc) throws ClassNotFoundException, SQLException {
        try {
            String sql = "insert into Account(userID, password, fullName, roleID)"
                    + "Values(?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, acc.getUserId());
            stm.setString(2, acc.getPassword());
            stm.setString(3, acc.getFullname());
            stm.setInt(4, acc.getRole());
            int row = stm.executeUpdate();
            if (row > 0) {
                return acc;
            }

        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean update(Account acc) throws ClassNotFoundException, SQLException {
        try {
            String sql = "update Account set fullName=?, password=? where userID= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, acc.getFullname());
                stm.setString(2, acc.getPassword());
                stm.setString(3, acc.getUserId());
                stm.executeUpdate();
                return true;
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
