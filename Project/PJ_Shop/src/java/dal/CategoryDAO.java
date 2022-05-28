/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author 84969
 */
public class CategoryDAO extends DBContext{
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "Select * from Category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getString("categoryID"),
                        rs.getString("categoryName")
                );

                list.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Category getCategoryById(String id) {
        try {
            String sql = "Select * from Category where categoryID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        id,
                        rs.getString("categoryName")
                );

                return c;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
