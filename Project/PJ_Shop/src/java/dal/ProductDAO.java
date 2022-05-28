/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 *
 * @author 84969
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "Select * from Product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                list.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(String pid) {

        try {
            String sql = "Select * From Product where productId= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pid.toUpperCase());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                return p;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Product> getProductByCategoryId(String cid) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "Select * From Product where categoryID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                list.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Product> getProductByName(String searchValue) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "Select * From Product where productName like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                list.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Product> getProductByPrice(float p1, float p2) {
        ArrayList<Product> list = new ArrayList<>();
        if (p1 > p2) {
            float temp = p1;
            p1 = p2;
            p2 = temp;
        }
        try {
            String sql = "Select * From Product where (price >= ? and price <= ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setFloat(1, p1);
            stm.setFloat(2, p2);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                list.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean update(Product p) throws SQLException {

        try {
            String sql = "Update Product set description=?, price=? "
                    + ",productName=? ,unit=? ,yearOfProduction=? ,quantity=? "
                    + ",productImgURL=? ,categoryID=? where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getDescription());
            stm.setFloat(2, p.getPrice());
            stm.setString(3, p.getProductName());
            stm.setString(4, p.getUnit());
            stm.setInt(5, p.getYear());
            stm.setInt(6, p.getQuantity());
            stm.setString(7, p.getImg());
            stm.setString(8, p.getCategoryID().getId());
            stm.setString(9, p.getProductId());
            stm.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean delete(String id) throws SQLException {

        try {
            String sql = "Delete From Product where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insert(Product p) throws SQLException {

        try {
            String sql = "INSERT INTO [PJDatabase].[dbo].[Product]\n"
                    + "           ([productId]\n"
                    + "           ,[description]\n"
                    + "           ,[price]\n"
                    + "           ,[productName]\n"
                    + "           ,[unit]\n"
                    + "           ,[yearOfProduction]\n"
                    + "           ,[quantity]\n"
                    + "           ,[productImgURL]\n"
                    + "           ,[categoryID])\n"
                    + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getProductId());
            stm.setString(2, p.getDescription());
            stm.setFloat(3, p.getPrice());
            stm.setString(4, p.getProductName());
            stm.setString(5, p.getUnit());
            stm.setInt(6, p.getYear());
            stm.setInt(7, p.getQuantity());
            stm.setString(8, p.getImg());
            stm.setString(9, p.getCategoryID().getId());
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<Product> getTop3Product(int count) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from (select ROW_NUMBER() over (order by productId asc) as rn, * from Product) as b\n"
                    + "                            where rn >= (?*3-2) and rn <= (?*3)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, count);
            stm.setInt(2, count);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                CategoryDAO dao = new CategoryDAO();
                Category cat = dao.getCategoryById(categoryID);

                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("productName"),
                        rs.getString("unit"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getString("productImgURL"),
                        cat
                );

                list.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int count() throws Exception {
        try {
            String sql = "SELECT count(*) From Product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
