package pe.fall23.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.fall23.shopping.Product;
import pe.fall23.utils.DBUtils;

public class ProductDAO {

    public Product getProductById(String id) {
        String sql = "select * from tblProducts where productId = ?";
        try (Connection cn = DBUtils.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("productName");
                    String des = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Boolean status = rs.getBoolean("status");
                    Product p = new Product(id, name, des, price, status);
                    return p;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> searchAll() {
        String sql = "select * from tblProducts";
        try (Connection cn = DBUtils.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("productName");
                    String des = rs.getString("description");
                    Float price = rs.getFloat("price");
                    Boolean status = rs.getBoolean("status");
                    Product p = new Product(id, name, des, price, status);
                    products.add(p);
                }
                return products;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
