package duonglnt.registration;

import duonglnt.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class RegistrationDAO implements Serializable {

    //public boolean checkLogin (String username, password)
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;              //Khai bao Connection
        PreparedStatement stm = null;       //Cau lenh co' truyen tham so dang tham so dong
        ResultSet rs = null;
        //boolean result = false;
        RegistrationDTO result = null;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();         //Luu y: khi co Connection nhung khong dam bao co ket noi voi database duoc khong
            if (con != null) {                       //Check Connection co ton tai hay khong
                //2. create SQL Statement String
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ? ";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    //map --> get data from result, set data to DTO's property
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullname, role);
                } //end username and passsword are verified
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
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;              //Khai bao Connection
        PreparedStatement stm = null;       //Cau lenh co' truyen tham so dang tham so dong
        ResultSet rs = null;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();         //Luu y: khi co Connection nhung khong dam bao co ket noi voi database duoc khong
            if (con != null) {                       //Check Connection co ton tai hay khong
                //2. create SQL Statement String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ? ";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, role);
                    //add to account list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } //account have not EXSISTED
                    this.accounts.add(dto);
                } //end rs has more than one record
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

    public boolean deleteAccount(String username)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;              //Khai bao Connection
        PreparedStatement stm = null;       //Cau lenh co' truyen tham so dang tham so dong
        boolean result = false;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();                         //Luu y: khi co Connection nhung khong dam bao co ket noi voi database duoc khong
            if (con != null) {                                      //Check Connection co ton tai hay khong
                //2. create SQL Statement String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection has been available
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

    public boolean updateAccount(String username, String password, boolean isRole)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;              //Khai bao Connection
        PreparedStatement stm = null;       //Cau lenh co' truyen tham so dang tham so dong
        boolean result = false;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();                         //Luu y: khi co Connection nhung khong dam bao co ket noi voi database duoc khong
            if (con != null) {                                      //Check Connection co ton tai hay khong
                //2. create SQL Statement String
                String sql = "Update Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "Where username = ? ";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isRole);
                stm.setString(3, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection has been available
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
    
    public boolean createAccount(RegistrationDTO dto) 
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        if (dto == null) {
            return false;
        } //end dto is not existed
        Connection con = null;              //Khai bao Connection
        PreparedStatement stm = null;       //Cau lenh co' truyen tham so dang tham so dong
        boolean result = false;

        try {
            //1. get Connection DB
            con = DBHelper.getConnection();                         //Luu y: khi co Connection nhung khong dam bao co ket noi voi database duoc khong
            if (con != null) {                                      //Check Connection co ton tai hay khong
                //2. create SQL Statement String
                String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                //3. Create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
            } //end connection has been available
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

//Khai bao theo chieu thuan
//Dong theo chieu nguoc

/*
    Tra ra 1 dong thi dung: if
    Tra ra nhieu dong thi dung: white

 */
 /*
    1. Khai bao Connection
       Dung thu vien de ho tro ket noi (try...finally)
 */
