package duonglnt.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import duonglnt.util.DBHelper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDAO implements Serializable {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void uploadProductsFromDatabase()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL Statement String
                String sql = "select id, name, description, unitprice, quantity, status "
                        + "From tbl_Product "
                        + "where quantity > 0 and status = 1";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float unitPrice = rs.getFloat("unitprice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");

                    //5.2 set data to DTO properties
                    ProductDTO product = new ProductDTO(id, name, description, unitPrice, quantity, status);
                    
                    //add to account list
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    this.products.add(product);
                }
            } //end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public boolean decreaseQuantity(String productId, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Update tbl_Product "
                        + "SET quantity = (select quantity "
                        + "From tbl_Product "
                        + "where id = ? ) - ? "
                        + "where id = ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, productId);
                stm.setInt(2, quantity);
                stm.setString(3, productId);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0) {
                    result = true;
                }
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public ProductDTO getProductDetail(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select name, description, unitprice, status "
                        + "From tbl_Product "
                        + "Where id = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //5.1 get data from ResultSet
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("unitprice");
                    boolean role = rs.getBoolean("status");
                    //5.2 set data to DTO properties
                    result = new ProductDTO(
                            id, name, description, price, 0, role);
                    //end account have NOT existed
                }//end product has existed
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int getProductQuantity(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select quantity "
                        + "From tbl_Product "
                        + "Where id = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    //5.1 get data from ResultSet
                    quantity = rs.getInt("quantity");
                    //end account have NOT existed
                }//end product has existed
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return quantity;
    }

}
