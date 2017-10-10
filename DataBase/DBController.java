/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 *
 * @author Obare
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBController {
   public static Connection Connect() {
        try {

            String url = "jdbc:mysql://localhost:3306/obare";
            String user = "root";
            String password = "kronos";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
