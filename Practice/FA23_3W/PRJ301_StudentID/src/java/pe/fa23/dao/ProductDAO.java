/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.fa23.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.fall23.shopping.Product;
import pe.fall23.utils.DBUtils;

/**
 *
 * @author truon
 */
public class ProductDAO {

    public List<Product> getProductList() throws SQLException, Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productID, productName, description;
        float price;
        boolean status;
        List<Product> productList = new ArrayList();
        Product product;
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT productID, productName, description, price, status FROM tblProducts WHERE status = 1";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productID = rs.getString(1);
                productName = rs.getString(2);
                description = rs.getString(3);
                price = rs.getFloat(4);
                status = rs.getBoolean(5);
                product = new Product(productID, productName, description, price, status);
                productList.add(product);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return productList;
    }

    public Product getProductByID(String productID) throws SQLException, Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String productName, description;
        float price;
        boolean status;
        Product product = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT productName, description, price, status FROM tblProducts WHERE productID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, productID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productName = rs.getString(1);
                description = rs.getString(2);
                price = rs.getFloat(3);
                status = rs.getBoolean(4);
                product = new Product(productID, productName, description, price, status);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return product;
    }
}
