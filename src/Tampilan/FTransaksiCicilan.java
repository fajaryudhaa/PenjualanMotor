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
public class FTransaksiCicilan extends javax.swing.JInternalFrame {
private Connection conn = new koneksi().connect();
private DefaultTableModel tabmode;
private ResultSet RsBook;
String pelanggann,nikk,keterangan;
String kodee,merk,nma,hrga;
double Htotal,total;
double penguranganDP;

    /**
     * Creates new form FTransaksiCicilan
     */
    public FTransaksiCicilan() {
        initComponents();
        jam();
        datatable();
        autonumber();
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
        Object[] Baris = {"Kode Kredit", "Tanggal", "Pelanggan","Kode Motor", "Uang Muka","Lama Cicilan","Cicilan Perbulan","Sisa","Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();
        tabel.setModel(tabmode);
        try {
        String sql = "SELECT * FROM transaksikredit where kode like '%"+cariitem+"%' or pelanggan like '%"+cariitem+"%' order by kode asc";
        
            //java.sql.Statement stat = conn.createStatement();
            RsBook = KoneksiDB.getRS(sql);
            while(RsBook.next()){
                String a = RsBook.getString("kode");
                String b = RsBook.getString("tanggal");
                String c = RsBook.getString("pelanggan");
                String d = RsBook.getString("kodemtr");
                String e = RsBook.getString("uangmuka");
                String f = RsBook.getString("lamacicil");
                String g = RsBook.getString("cicilanbln");
                String h = RsBook.getString("sisa");
                String i = RsBook.getString("keterangan");
                
                String[] data = {a, b, c, d, e, f, g, h, i};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
        }
    }

private void autonumber(){
        try
        {
            String sql="select * from transaksikredit order by kode desc";
            RsBook = KoneksiDB.getRS(sql); 
            if (RsBook.next()) {
                String nolok = RsBook.getString("kode").substring(4);
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
                txtkode.setText("KRD-"+Nol+AN);
            }
            else {
                txtkode.setText("KRD-0001");
            }
                        
        }catch(Exception ex){
                System.out.println(ex);
        }
    }

    public void emptyText(){
        txttgl.setDate(null);
        txtpelanggan.setText("");
        txtnik.setText("");
        txtkodemtr.setText("");
        txtmerek.setText("");
        txtnama.setText("");
        txtharga.setText("");
        cbcicil.setSelectedItem("--Pilih--");
        txtuangmuka.setText("");
        txtcicilan.setText("");
        txtsisa.setText("");
        txtketerangan.setText("");
    }
    
    public void itemTerpilih(){
        TabelPelanggan1 Pp = new TabelPelanggan1();
        Pp.plg = this;
        txtpelanggan.setText(pelanggann);
        txtnik.setText(nikk);
    }
    
    public void itemTerpilih1(){
        TabelMotor1 Pp = new TabelMotor1();
        Pp.plg1 = this;
        txtkodemtr.setText(kodee);
        txtmerek.setText(merk);
        txtnama.setText(nma);
        txtharga.setText(hrga);
    }
    
    private void hargasewa(){
        
                if (cbcicil.getSelectedItem() == "12"){
                    double a = Double.valueOf(txtharga.getText());
                    double b = Integer.parseInt(txtuangmuka.getText());
                    penguranganDP = a-b;
                    
                    double d = (penguranganDP*1/100);
                    double e =  d*12;
                    Htotal = penguranganDP+e;
                    
                    double a1=Htotal;
                    int b1=12;
                     total=a1/b1;
                    //txtcicilan.setText(Integer.toString(total));
                }else if(cbcicil.getSelectedItem() == "18"){
                    int a = Integer.parseInt(txtharga.getText());
                    int b = Integer.parseInt(txtuangmuka.getText());
                    penguranganDP = a-b;
                    
                    double d = (penguranganDP*1.25/100);
                    double e =  d*18;
                    Htotal = penguranganDP+e;
                    
                    double a1=Htotal;
                    int b1=18;
                     total=a1/b1;
                }else if(cbcicil.getSelectedItem() == "24"){
                    int a = Integer.parseInt(txtharga.getText());
                    int b = Integer.parseInt(txtuangmuka.getText());
                    penguranganDP = a-b;
                    
                    double d = (penguranganDP*1.5/100);
                    double e =  d*24;
                    Htotal = penguranganDP+e;
                    
                    double a1=Htotal;
                    int b1=24;
                     total=a1/b1;
                }else if(cbcicil.getSelectedItem() == "30"){
                    int a = Integer.parseInt(txtharga.getText());
                    int b = Integer.parseInt(txtuangmuka.getText());
                    penguranganDP = a-b;
                    
                    double d = (penguranganDP*1.75/100);
                    double e =  d*30;
                    Htotal = penguranganDP+e;
                    
                    double a1=Htotal;
                    int b1=30;
                     total=a1/b1;
                }else if(cbcicil.getSelectedItem() == "36"){
                    int a = Integer.parseInt(txtharga.getText());
                    int b = Integer.parseInt(txtuangmuka.getText());
                    penguranganDP = a-b;
                    
                    double d = (penguranganDP*2/100);
                    double e =  d*36;
                    Htotal = penguranganDP+e;
                    
                    double a1=Htotal;
                    int b1=36;
                     total=a1/b1;


           }    
                String tot = String.format("%.0f", Htotal);
        txtsisa.setText(tot);
        String hsl = String.format("%.0f", total);
        txtcicilan.setText(hsl);
                if (Htotal >= 0) {
                    keterangan="Belum Lunas";
                } else{
                    keterangan="Lunas";
                }
                txtketerangan.setText(keterangan);
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtpelanggan = new javax.swing.JTextField();
        rSButtonHover6 = new rojerusan.RSButtonHover();
        txtnik = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jam = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        rSButtonHover9 = new rojerusan.RSButtonHover();
        tambah1 = new rojerusan.RSButtonHover();
        rSButtonHover10 = new rojerusan.RSButtonHover();
        jLabel15 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        Jenis3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtmerek = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        rSButtonHover11 = new rojerusan.RSButtonHover();
        jLabel18 = new javax.swing.JLabel();
        txtkodemtr = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        Jenis4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtuangmuka = new javax.swing.JTextField();
        txtcicilan = new javax.swing.JTextField();
        txtketerangan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbcicil = new javax.swing.JComboBox<>();
        txtsisa = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        rSButtonHover12 = new rojerusan.RSButtonHover();
        jLabel23 = new javax.swing.JLabel();
        txttgl = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        rSButtonHover7 = new rojerusan.RSButtonHover();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Transaksi Kredit");

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Data Pelanggan"));

        jLabel7.setText("NIK");

        jLabel8.setText("Pelanggan");

        txtpelanggan.setEditable(false);
        txtpelanggan.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonHover6.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Plus_16px.png"))); // NOI18N
        rSButtonHover6.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover6ActionPerformed(evt);
            }
        });

        txtnik.setEditable(false);
        txtnik.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(44, 44, 44)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtnik, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tabel Data Cicilan"));

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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );

        tambah1.setBackground(new java.awt.Color(0, 0, 0));
        tambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Plus_16px.png"))); // NOI18N
        tambah1.setText("Tambah");
        tambah1.setColorHover(new java.awt.Color(204, 204, 204));
        tambah1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });

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

        jLabel15.setText("Kode");

        txtkode.setEditable(false);
        txtkode.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Data Motor"));

        Jenis3.setText("Merek");

        jLabel16.setText("Nama");

        jLabel17.setText("Harga");

        txtmerek.setEditable(false);
        txtmerek.setBackground(new java.awt.Color(255, 255, 255));

        txtnama.setEditable(false);
        txtnama.setBackground(new java.awt.Color(255, 255, 255));

        txtharga.setEditable(false);
        txtharga.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonHover11.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Plus_16px.png"))); // NOI18N
        rSButtonHover11.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover11ActionPerformed(evt);
            }
        });

        jLabel18.setText("Kode");

        txtkodemtr.setEditable(false);
        txtkodemtr.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jenis3)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtkodemtr)
                            .addComponent(txtmerek)
                            .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtkodemtr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jenis3)
                    .addComponent(txtmerek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Cicilan"));

        Jenis4.setText("Uang Muka");

        jLabel19.setText("Cicilan Perbulan");

        jLabel20.setText("Sisa");

        txtcicilan.setEditable(false);
        txtcicilan.setBackground(new java.awt.Color(255, 255, 255));

        txtketerangan.setEditable(false);
        txtketerangan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("Lama Cicilan");

        cbcicil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "12", "18", "24", "30", "36" }));

        txtsisa.setEditable(false);
        txtsisa.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setText("Status Keterangan");

        rSButtonHover12.setBackground(new java.awt.Color(0, 0, 0));
        rSButtonHover12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Submit for Approval_16px.png"))); // NOI18N
        rSButtonHover12.setText("CEK");
        rSButtonHover12.setColorHover(new java.awt.Color(204, 204, 204));
        rSButtonHover12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSButtonHover12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover12ActionPerformed(evt);
            }
        });

        jLabel23.setText("Bulan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jenis4)
                    .addComponent(jLabel21))
                .addGap(65, 65, 65)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtuangmuka)
                            .addComponent(cbcicil, javax.swing.GroupLayout.Alignment.LEADING, 0, 117, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(txtcicilan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtketerangan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(txtsisa, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbcicil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcicilan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jenis4)
                    .addComponent(txtuangmuka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtsisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))))))
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
                        .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24))
                    .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        TabelPelanggan1 Pp = new TabelPelanggan1();
        Pp.plg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        try {
        
        DefaultTableModel Model = (DefaultTableModel)tabel.getModel();
        int bar = tabel.getSelectedRow();
        String cb = tabel.getValueAt(bar, 7).toString();
        Date date = new SimpleDateFormat("dd MMMMM yyyy", Locale.forLanguageTag("ID")).parse((String)Model.getValueAt(bar, 1));
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        
        txtkode.setText(a);
        txttgl.setDate(date);
        txtpelanggan.setText(c);
        txtkodemtr.setText(d);
        txtuangmuka.setText(e);
        cbcicil.setSelectedItem(cb);
        txtcicilan.setText(g);
        txtsisa.setText(h);
        txtketerangan.setText(i);
    } catch (ParseException ex) {
        Logger.getLogger(FTransaksiCash.class.getName()).log(Level.SEVERE, null, ex);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy", Locale.forLanguageTag("ID"));
        String tgl = sdf.format(txttgl.getDate());
        String cb = cbcicil.getModel().getSelectedItem().toString();
        String sql = "insert into transaksikredit values (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtkode.getText());
            stat.setString(2, tgl);
            stat.setString(3, txtpelanggan.getText());
            stat.setString(4, txtkodemtr.getText());
            stat.setString(5, txtuangmuka.getText());
            stat.setString(6, cb);
            stat.setString(7, txtcicilan.getText());
            stat.setString(8, txtsisa.getText());
            stat.setString(9, txtketerangan.getText());
            stat.setString(10,"0");
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            emptyText();
            txtkode.requestFocus();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
        autonumber();
        emptyText();
        datatable();
    }//GEN-LAST:event_tambah1ActionPerformed

    private void rSButtonHover10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover10ActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "delete from transaksikredit where kode = '" +txtkode.getText()+ "'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                emptyText();
                txtkode.requestFocus();
                datatable();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
            autonumber();
            emptyText();
            datatable();
        }
    }//GEN-LAST:event_rSButtonHover10ActionPerformed

    private void rSButtonHover11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover11ActionPerformed
        TabelMotor1 Pp = new TabelMotor1();
        Pp.plg1 = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_rSButtonHover11ActionPerformed

    private void rSButtonHover12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover12ActionPerformed
        hargasewa();
    }//GEN-LAST:event_rSButtonHover12ActionPerformed

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
        try{
            String Logo = new File("src\\Gambar\\Logo.png").getAbsolutePath();
            String NamaFile = "./src/Laporan/TransaksiPengajuan.jasper";
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
    private javax.swing.JLabel Jenis3;
    private javax.swing.JLabel Jenis4;
    private javax.swing.JComboBox<String> cbcicil;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jam;
    private rojerusan.RSButtonHover rSButtonHover10;
    private rojerusan.RSButtonHover rSButtonHover11;
    private rojerusan.RSButtonHover rSButtonHover12;
    private rojerusan.RSButtonHover rSButtonHover6;
    private rojerusan.RSButtonHover rSButtonHover7;
    private rojerusan.RSButtonHover rSButtonHover9;
    private javax.swing.JTable tabel;
    private rojerusan.RSButtonHover tambah1;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcicilan;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtketerangan;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtkodemtr;
    private javax.swing.JTextField txtmerek;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnik;
    private javax.swing.JTextField txtpelanggan;
    private javax.swing.JTextField txtsisa;
    private com.toedter.calendar.JDateChooser txttgl;
    private javax.swing.JTextField txtuangmuka;
    // End of variables declaration//GEN-END:variables
}
