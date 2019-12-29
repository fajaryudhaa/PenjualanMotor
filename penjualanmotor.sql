/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : penjualanmotor

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-12-29 10:55:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `datamotor`
-- ----------------------------
DROP TABLE IF EXISTS `datamotor`;
CREATE TABLE `datamotor` (
  `kode` varchar(10) NOT NULL,
  `merek` varchar(20) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `jenis` varchar(20) DEFAULT NULL,
  `warna` varchar(20) DEFAULT NULL,
  `harga` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of datamotor
-- ----------------------------
INSERT INTO `datamotor` VALUES ('MTR-0001', 'Honda', 'Honda Beat', 'Matic', 'Hitam', '14500000');
INSERT INTO `datamotor` VALUES ('MTR-0002', 'Yamaha', 'Yamaha Mio', 'Matic', 'Biru', '15500000');
INSERT INTO `datamotor` VALUES ('MTR-0003', 'xxx', 'xxx', 'xxx', 'xx', '21000000');

-- ----------------------------
-- Table structure for `datapelanggan`
-- ----------------------------
DROP TABLE IF EXISTS `datapelanggan`;
CREATE TABLE `datapelanggan` (
  `nik` varchar(10) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `notlp` varchar(20) DEFAULT NULL,
  `jenkel` varchar(20) DEFAULT NULL,
  `alamat` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of datapelanggan
-- ----------------------------
INSERT INTO `datapelanggan` VALUES ('09012839', 'Gunawan', '213123', 'Laki-laki', 'Jakarta');
INSERT INTO `datapelanggan` VALUES ('98238990', 'Putri', '091929', 'Perempuan', 'Gedung');

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('unindra', 'unindra');
INSERT INTO `login` VALUES ('user', 'pass');

-- ----------------------------
-- Table structure for `transaksiangsuran`
-- ----------------------------
DROP TABLE IF EXISTS `transaksiangsuran`;
CREATE TABLE `transaksiangsuran` (
  `nobayar` varchar(10) NOT NULL,
  `tanggal` varchar(20) DEFAULT NULL,
  `kodebayar` varchar(20) DEFAULT NULL,
  `pelanggan` varchar(20) DEFAULT NULL,
  `pembayaran` varchar(30) DEFAULT NULL,
  `angsuran` varchar(20) DEFAULT NULL,
  `ket` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`nobayar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transaksiangsuran
-- ----------------------------
INSERT INTO `transaksiangsuran` VALUES ('BYR-0001', '31 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '2', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0002', '23 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '3', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0003', '31 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '4', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0004', '30 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '5', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0005', '31 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '6', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0006', '29 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '7', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0007', '24 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '8', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0008', '24 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '9', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0009', '24 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '10', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0010', '31 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '11', 'Belum Lunas');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0011', '31 Agustus 2019', 'KRD-0001', 'Putri', '1073333', '12', 'LUNAS');
INSERT INTO `transaksiangsuran` VALUES ('BYR-0012', '31 Agustus 2019', 'KRD-0002', 'Gunawan', '850694', '1', 'Belum Lunas');

-- ----------------------------
-- Table structure for `transaksicash`
-- ----------------------------
DROP TABLE IF EXISTS `transaksicash`;
CREATE TABLE `transaksicash` (
  `kode` varchar(10) NOT NULL,
  `tanggal` varchar(20) DEFAULT NULL,
  `pelanggan` varchar(20) DEFAULT NULL,
  `merek` varchar(20) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `harga` varchar(30) DEFAULT NULL,
  `bayar` varchar(30) DEFAULT NULL,
  `keterangan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transaksicash
-- ----------------------------
INSERT INTO `transaksicash` VALUES ('CASH-0001', '23 Agustus 2019', 'Agus', 'Honda', 'Honda Beat', '14500000', '14500000', 'Lunas');
INSERT INTO `transaksicash` VALUES ('CASH-0002', '23 Agustus 2019', 'Putri', 'Yamaha', 'Yamaha Mio', '15500000', '15500000', 'Lunas');

-- ----------------------------
-- Table structure for `transaksikredit`
-- ----------------------------
DROP TABLE IF EXISTS `transaksikredit`;
CREATE TABLE `transaksikredit` (
  `kode` varchar(10) NOT NULL,
  `tanggal` varchar(30) DEFAULT NULL,
  `pelanggan` varchar(30) DEFAULT NULL,
  `kodemtr` varchar(10) DEFAULT NULL,
  `uangmuka` varchar(30) DEFAULT NULL,
  `lamacicil` varchar(10) DEFAULT NULL,
  `cicilanbln` varchar(30) DEFAULT NULL,
  `sisa` varchar(30) DEFAULT NULL,
  `keterangan` varchar(30) DEFAULT NULL,
  `angsuran` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transaksikredit
-- ----------------------------
INSERT INTO `transaksikredit` VALUES ('KRD-0001', '23 Agustus 2019', 'Putri', 'MTR-0001', '3000000', '12', '1073333', '12880000', 'LUNAS', '12');
INSERT INTO `transaksikredit` VALUES ('KRD-0002', '31 Agustus 2019', 'Gunawan', 'MTR-0002', '3000000', '18', '850694', '15312500', 'Belum Lunas', '1');
INSERT INTO `transaksikredit` VALUES ('KRD-0003', '22 November 2019', 'Putri', 'MTR-0003', '6300000', '36', '702333', '25284000', 'Belum Lunas', '2');
