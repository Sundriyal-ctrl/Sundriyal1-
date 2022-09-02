/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ConnectionProvider implements ConnectionDetails{
    static Connection connection;
   private ConnectionProvider(){}
   public static Connection getInstance() throws ClassNotFoundException, SQLException 
   {
       if(connection==null)
       {
           Class.forName(driver);
           connection=DriverManager.getConnection(url,username,password);
       }
       return connection;
   }
}
