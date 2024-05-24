
package duonglnt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBHelper implements Serializable{
    public static Connection getConnection()
        throws /*ClassNotFoundException,*/ SQLException, NamingException {
        
        //1. Get current context
        Context currentContext = new InitialContext();
        //2. Lookup tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. Lookup DS from Container
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");          //Copy name o ben context.xml
        //4. Open connection Data Source
        Connection con = ds.getConnection();
        return con;
        
//        //1. Load Driver (Driver is available)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"                                  //Xac dinh url truy cap Database
//                + "localhost:1433;"
//                + "databaseName=text";        
//        
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "Duong0989902912");
//        
//        return con;
        
    }
}
