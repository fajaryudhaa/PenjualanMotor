/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualanmotor;

import Koneksi.KoneksiDB;

/**
 *
 * @author PC-USER
 */
public class PenjualanMotor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KoneksiDB.koneksiMySQL("penjualanmotor","root","");
        new Tampilan.FLogin().setVisible(true);
    }
    
}
