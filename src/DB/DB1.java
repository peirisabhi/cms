/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.Db.logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author abhi
 */
public class DB1 {

    
    static private final String URL = "jdbc:mysql://192.185.82.141:3306/abhipeir_cms";
    static private final String UN = "abhipeir_sms";
    static private final String PW = "abhi@123";
    static private final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn;
    
    static Logger logger = Logger.getLogger("CMS");

    static {
        FileHandler fh = null;
        try {
            fh = new FileHandler("CMS.log", true);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (SecurityException ex) {
            System.out.println(ex);
        }
        logger.addHandler(fh);

        SimpleFormatter f = new SimpleFormatter();
        fh.setFormatter(f);
    }

    static private Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, UN, PW);
        } catch (Exception ex) {
            System.out.println(ex);
            logger.warning(ex.toString());
        }
        return conn;
    
    }
    
    public static int iudOnline(String q) {
        int r = 0;
        try {
            r = getConnection().createStatement().executeUpdate(q);
        } catch (Exception e) {
            System.out.println(e);
            logger.warning(e.toString());
        }
        return r;
    }

    public static ResultSet searchOnline(String q){
        ResultSet rs =null;
        try {
            rs = getConnection().createStatement().executeQuery(q);
        } catch (Exception e) {
            System.out.println(e);
            logger.warning(e.toString());
        }
        return rs;
    }

    
    
//    public static void main(String[] args) {
//        System.out.println(getConnection());
//    }
    
}
