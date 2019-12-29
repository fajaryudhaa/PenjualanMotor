/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tampilan;

import Koneksi.KoneksiDB;
import Koneksi.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC-USER
 */
public class FTransaksiAngsuran extends javax.swing.JInternalFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
private ResultSet RsBook;
String kodee,pelanggann,kodemtr,pembayaran,ket,angsuran1,angsuran2;
double Htotal,total;
double penguranganDP; 
int cicilan,ttl;
int telahbyr;


    /**
     * Creates new form FTransaksiAngsuran
     */
    public FTransaksiAngsuran() {
        initComponents();
        jam();
        datatable();
        autonumber();
    }
    
    public void itemTerpilih(){
        TabelCicilan Pp = new TabelCicilan();
        Pp.plg = this;
        txtkodebyr.setText(kodee);
        txtpelanggan.setText(pelanggann);
        txtkodemtr.setText(kodemtr);
        txtpembayaran.setText(pembayaran);
        txtket.setText(ket);
        txtangsurantotal.setText(angsuran1);
        txtangsuranpls.setText(angsuran2);
    }
    
    void jam(){
        Date D = new Date();
        SimpleDateFormat S = new SimpleDateFormat("EEEEE, dd MMMMM yyyy");
        tanggal.setText(S.format(D));

        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date D = new Date();
                SimpleDateFormat S = new SimpleDateFormat("hh:mm:ss a");
                jam.setText(S.format(D).toUpperCase());
            }    
        }).start();
    }
    
    protected void datatable(){
        Object[] Baris = {"No Bayar", "Tanggal", "Kode Bayar","Pelanggan", "Pembayaran","Angsuran","Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();
        tabel.setModel(tabmode);
        try {
        String sql = "SELECT * FROM transaksiangsuran where nobayar like '%"+cariitem+"%' or kodebayar like '%"+cariitem+"%' order by nobayar asc";
        
            //java.sql.Statement stat = conn.createStatement();
            RsBook = KoneksiDB.getRS(sql);
            while(RsBook.next()){
                String a = RsBook.getString("nobayar");
                String b = RsBook.getString("tanggal");
                String c = RsBook.getString("kodebayar");
                String d = RsBook.getString("pelanggan");
                String e = RsBook.getString("pembayaran");
                String f = RsBook.getString("angsuran");
                String g = RsBook.getString("ket");
                String[] data = {a, b, c, d, e, f, g};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
        }
    }
    
    private void autonumber(){
        try
        {
            String sql="select * from transaksiangsuran order by nobayar desc";
            RsBook = KoneksiDB.getRS(sql); 
            if (RsBook.next()) {
                String nolok = RsBook.getString("nobayar").substring(4);
                String AN = ""+ (Integer.parseInt(nolok) + 1);
                String Nol = "";
                if (AN.length()==1)
                {Nol="000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if (AN.length()==3)
                {Nol="0";}
                else if (AN.length()==4)
                {Nol="";}
                txtnobayar.setText("BYR-"+Nol+AN);
            }
            else {
                txtnobayar.setText("BYR-0001");
            }
                        
        }catch(Exception ex){
                System.out.println(ex);
        }
    }
    
    public void emptyText(){
        txttgl.setDate(null);
        txtkodebyr.setText("");
        txtpelanggan.setText("");
        txtkodemtr.setText("");
        txtangsurantotal.setText("");
        txtangsuranpls.setText("");
        txtangsuranke.setText("");
        txtpembayaran.setText("");
        txtket.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtkodebyr = new javax.swing.JTextField();
        rSButtonHover6 = new rojerusan.RSButtonHover();
        txtpelanggan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtkodemtr = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtangsurantotal = new javax.swing.JTextField();
        txtangsuranpls = new javax.swing.JTextField();
        rSButtonHover11 = new rojerusan.RSButtonHover();
        jPanel9 = new javax.swing.JPanel();
        jam = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        rSButtonHover9 = new rojerusan.RSButtonHover();
        rSButtonHover10 = new rojerusan.RSButtonHover();
        jLabel15 = new javax.swing.JLabel();
        txtnobayar = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtket = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtpembayaran = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtangsuranke = new javax.swing.JTextField();
        tambah1 = new rojerusan.RSButtonHover();
        txttgl = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        rSButtonHover7 = new rojerusan.RSButtonHover();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Transaksi Angsuran");

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cicilan"));

        jLabel7.setText("Pelanggan");

        jLabel8.setText("Kode Bayar");

        txtkodebyr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtkodebyrKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtkodebyrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtkodebyrKeyTyped(evt);
            }
        });

        rSButtonHover6.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Plus_16px.png"))); // NOI18N
        rSButtonHover6.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover6ActionPerformed(evt);
            }
        });

        jLabel9.setText("Kode Motor");

        jLabel10.setText("Angsuran Yang Diambil");

        jLabel11.setText("Angsuran Yang Telah Dibayar");

        txtangsurantotal.setEditable(false);
        txtangsurantotal.setBackground(new java.awt.Color(204, 204, 204));

        txtangsuranpls.setEditable(false);
        txtangsuranpls.setBackground(new java.awt.Color(204, 204, 204));

        rSButtonHover11.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Plus_16px.png"))); // NOI18N
        rSButtonHover11.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtpelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkodebyr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txtkodemtr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtangsuranpls, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(txtangsurantotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtkodebyr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkodemtr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtangsurantotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtangsuranpls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jam.setText("Jam");

        tanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tanggal.setText("Tanggal");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jam)
                    .addComponent(tanggal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tabel Data Angsuran"));

        tabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabel);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        rSButtonHover9.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Search_16px.png"))); // NOI18N
        rSButtonHover9.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSButtonHover10.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Trash_16px.png"))); // NOI18N
        rSButtonHover10.setText("Hapus");
        rSButtonHover10.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover10ActionPerformed(evt);
            }
        });

        jLabel15.setText("No Bayar");

        txtnobayar.setEditable(false);
        txtnobayar.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Angsuran"));

        jLabel16.setText("Keterangan");

        txtket.setEditable(false);
        txtket.setBackground(new java.awt.Color(204, 204, 204));
        txtket.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel18.setText("Pembayaran");

        txtpembayaran.setEditable(false);
        txtpembayaran.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Angsuran Ke");

        txtangsuranke.setEditable(false);
        txtangsuranke.setBackground(new java.awt.Color(204, 204, 204));

        tambah1.setBackground(new java.awt.Color(0, 0, 0));
        tambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Coins_16px.png"))); // NOI18N
        tambah1.setText("Bayar");
        tambah1.setColorHover(new java.awt.Color(204, 204, 204));
        tambah1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtangsuranke, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(txtpembayaran)
                            .addComponent(txtket))
                        .addGap(46, 46, 46))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txtangsuranke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txttgl.setDateFormatString("dd MMMMM yyyy");

        jLabel24.setText("Tanggal");

        rSButtonHover7.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Print_16px.png"))); // NOI18N
        rSButtonHover7.setText("Cetak");
        rSButtonHover7.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnobayar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtnobayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24))
                    .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 31, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        TabelCicilan Pp = new TabelCicilan();
        Pp.plg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
        txtket.setVisible(false);
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        try {
        
        DefaultTableModel Model = (DefaultTableModel)tabel.getModel();
        int bar = tabel.getSelectedRow();
        Date date = new SimpleDateFormat("dd MMMMM yyyy", Locale.forLanguageTag("ID")).parse((String)Model.getValueAt(bar, 1));
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        
        
        txtnobayar.setText(a);
        txttgl.setDate(date);
        txtkodebyr.setText(c);
        txtpelanggan.setText(d);
         txtpembayaran.setText(e);
        txtangsuranke.setText(f);
        txtket.setText(g);
    } catch (ParseException ex) {
        Logger.getLogger(FTransaksiAngsuran.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_tabelMouseClicked

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void rSButtonHover9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover9ActionPerformed
        datatable();
    }//GEN-LAST:event_rSButtonHover9ActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        cicilan = Integer.parseInt(txtangsurantotal.getText());
        telahbyr = Integer.parseInt(txtangsuranke.getText());
        
        if(telahbyr == cicilan){
            JOptionPane.showMessageDialog(null, "Pengkreditan Sudah Lunas");
            txtket.setText("LUNAS");
            }else {
        }  
              
        txtket.setVisible(true);


        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy", Locale.forLanguageTag("ID"));
        String tgl = sdf.format(txttgl.getDate());
        String sql = "insert into transaksiangsuran values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnobayar.getText());
            stat.setString(2, tgl);
            stat.setString(3, txtkodebyr.getText());
            stat.setString(4, txtpelanggan.getText());
            stat.setString(5, txtpembayaran.getText());
            stat.setString(6, txtangsuranke.getText());
            stat.setString(7, txtket.getText());
                        
            stat.executeUpdate();
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        String zsql = "update transaksikredit set pelanggan=?,kodemtr=?,lamacicil=?,angsuran=?,cicilanbln=?,keterangan=? where kode=?";
        try{
            PreparedStatement stat = conn.prepareStatement(zsql);
            stat.setString(1, txtpelanggan.getText());
            stat.setString(2, txtkodemtr.getText());
            stat.setString(3, txtangsurantotal.getText());
            stat.setString(4, txtangsuranke.getText());
            stat.setString(5, txtpembayaran.getText());
            stat.setString(6, txtket.getText());
            stat.setString(7, txtkodebyr.getText());
            
            stat.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Data Berhasil DiSimpan");
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
               
        autonumber();
        emptyText();
        datatable();
    }//GEN-LAST:event_tambah1ActionPerformed

    private void rSButtonHover10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover10ActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "delete from transaksiangsuran where nobayar = '" +txtnobayar.getText()+ "'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                emptyText();
                txtkodebyr.requestFocus();
                datatable();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
            autonumber();
            emptyText();
            datatable();
        }
    }//GEN-LAST:event_rSButtonHover10ActionPerformed

    private void txtkodebyrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkodebyrKeyReleased
        
    }//GEN-LAST:event_txtkodebyrKeyReleased

    private void txtkodebyrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkodebyrKeyPressed
        
    }//GEN-LAST:event_txtkodebyrKeyPressed

    private void txtkodebyrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkodebyrKeyTyped
        
    }//GEN-LAST:event_txtkodebyrKeyTyped

    private void rSButtonHover11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover11ActionPerformed
        cicilan = Integer.parseInt(txtangsurantotal.getText());
        telahbyr = Integer.parseInt(txtangsuranpls.getText());
        
        if(telahbyr == cicilan){
            //txtangsuranke.setText("");
            txtket.setText("LUNAS");
        }else {
            ttl=telahbyr+1;
        }
        txtangsuranke.setText(String.valueOf(ttl));
        txtket.setVisible(true);
    }//GEN-LAST:event_rSButtonHover11ActionPerformed

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
        try{
            String Logo = new File("src\\Gambar\\Logo.png").getAbsolutePath();
            String NamaFile = "./src/Laporan/TransaksiKredit.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/penjualanmotor","root","");
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("gambar",Logo);
            param.put("KODE",txtcari.getText());

            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);

        }
    }//GEN-LAST:event_rSButtonHover7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jam;
    private rojerusan.RSButtonHover rSButtonHover10;
    private rojerusan.RSButtonHover rSButtonHover11;
    private rojerusan.RSButtonHover rSButtonHover6;
    private rojerusan.RSButtonHover rSButtonHover7;
    private rojerusan.RSButtonHover rSButtonHover9;
    private javax.swing.JTable tabel;
    private rojerusan.RSButtonHover tambah1;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txtangsuranke;
    private javax.swing.JTextField txtangsuranpls;
    private javax.swing.JTextField txtangsurantotal;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtket;
    private javax.swing.JTextField txtkodebyr;
    private javax.swing.JTextField txtkodemtr;
    private javax.swing.JTextField txtnobayar;
    private javax.swing.JTextField txtpelanggan;
    private javax.swing.JTextField txtpembayaran;
    private com.toedter.calendar.JDateChooser txttgl;
    // End of variables declaration//GEN-END:variables
}
