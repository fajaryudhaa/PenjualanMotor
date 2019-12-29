/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DaunBG
 */
public class koneksi {
    private Connection koneksi_db;
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        }catch (ClassNotFoundException ex){
            System.out.println("Gagal koneksi "+ex);
        }
        String url = "jdbc:mysql://localhost/penjualanmotor";
        try{
            koneksi_db = DriverManager.getConnection(url, "root", "");
        }catch (SQLException ex){
            System.out.println("Gagal koneksi "+ex);
        }
        return koneksi_db;
    }
}
