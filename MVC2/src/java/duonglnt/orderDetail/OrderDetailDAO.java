/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonglnt.orderDetail;

import duonglnt.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author trung
 */
public class OrderDetailDAO {
    public boolean insertOrderDetail(OrderDetailDTO detailDTO)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. get Connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Insert Into OrderDetail("
                        + "name, quantity, price, total, productid, torderid"
                        + ") Values("
                        + "?, ?, ?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, detailDTO.getName());
                stm.setInt(2, detailDTO.getQuantity());
                stm.setFloat(3, detailDTO.getUnitPrice());
                stm.setFloat(4, detailDTO.getTotal());
                stm.setString(5, detailDTO.getProductDTO().getId());
                stm.setString(6, detailDTO.gettOrderDTO().getId());
                //4. Execute Query
                int effectRows = stm.executeUpdate(); // neu insert delete update thi la executeUpadate
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
}