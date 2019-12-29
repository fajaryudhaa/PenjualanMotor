/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fajar Yuda Saputra
 */
public class KoneksiDB {
    
    private Connection con;
    private static Statement st;
    private static ResultSet rs;

    public KoneksiDB(String driver, String dburl, String user, String pass) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dburl, user, pass);
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void koneksiMySQL(String db, String user, String pass) {
        String url = "jdbc:mysql://localhost:3306/"+db;
        new KoneksiDB("com.mysql.jdbc.Driver", url, user, pass);
    }

    public static ResultSet getRS(String strSQL) {
        try {
            rs = st.executeQuery(strSQL);
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static int execSQL(String strSQL) {
        int n=0;
        try {
            n = st.executeUpdate(strSQL);
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
  }
}