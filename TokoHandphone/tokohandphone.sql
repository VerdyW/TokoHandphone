-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2022 at 02:46 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokohandphone`
--

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaksi`
--

CREATE TABLE `detailtransaksi` (
  `IDDetailTransaksi` int(11) NOT NULL,
  `IDTransaksi` int(11) NOT NULL,
  `IDHandphone` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `TotalHarga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailtransaksi`
--

INSERT INTO `detailtransaksi` (`IDDetailTransaksi`, `IDTransaksi`, `IDHandphone`, `Quantity`, `TotalHarga`) VALUES
(1, 1, 3, 1, 5000000),
(2, 2, 1, 1, 5000000),
(3, 2, 5, 1, 5500000);

-- --------------------------------------------------------

--
-- Table structure for table `handphone`
--

CREATE TABLE `handphone` (
  `IDHandphone` int(11) NOT NULL,
  `IDSupplier` int(11) NOT NULL,
  `MerekHandphone` varchar(255) NOT NULL,
  `TypeHandphone` varchar(255) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `handphone`
--

INSERT INTO `handphone` (`IDHandphone`, `IDSupplier`, `MerekHandphone`, `TypeHandphone`, `Harga`, `Stock`) VALUES
(1, 1, 'Samsung', 'S7', 5000000, 15),
(2, 2, 'Samsung', 'S8', 6500000, 25),
(3, 2, 'Apple', 'Iphone 8', 5000000, 15),
(4, 2, 'Apple', 'Iphone 7', 4500000, 10),
(5, 3, 'Xiaomi', 'Redmi 9', 5500000, 20),
(6, 3, 'Xiaomi', 'Redmi 10', 7500000, 14);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `IDMember` int(10) NOT NULL,
  `NamaMember` varchar(255) NOT NULL,
  `AlamatMember` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`IDMember`, `NamaMember`, `AlamatMember`) VALUES
(1, 'Andre Boaz', '72954 Reinger Brooks Apt. 203'),
(2, 'Madeline Satterfield', '2085 Willms Viaduct West Jacqueshaven'),
(3, 'Trudie Doyle Jr', '207 Leone Orchard Jermainside'),
(4, 'Brendan Barrows', '1005 Tierra Glen South Jermey');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `IDSupplier` int(11) NOT NULL,
  `NamaSupplier` varchar(255) NOT NULL,
  `AlamatSupplier` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`IDSupplier`, `NamaSupplier`, `AlamatSupplier`) VALUES
(1, 'Selena Lebsack', 'Minnie Brooks Apt. 378 Bartellberg, ND'),
(2, 'Elwyn Luettgen Parisian', 'Jerrod Lake Apt. 345 North Tia'),
(3, 'Alexandro Schinner', 'Roberts Radial Apt. 667 Waelchibury');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `IDTransaksi` int(10) NOT NULL,
  `IDMember` int(11) NOT NULL,
  `TanggalTransaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`IDTransaksi`, `IDMember`, `TanggalTransaksi`) VALUES
(1, 1, '2022-02-06'),
(2, 2, '2022-02-06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD PRIMARY KEY (`IDDetailTransaksi`),
  ADD KEY `IDTransaksi` (`IDTransaksi`),
  ADD KEY `IDHandphone` (`IDHandphone`);

--
-- Indexes for table `handphone`
--
ALTER TABLE `handphone`
  ADD PRIMARY KEY (`IDHandphone`),
  ADD KEY `IDSupplier` (`IDSupplier`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`IDMember`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`IDSupplier`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`IDTransaksi`),
  ADD KEY `IDMember` (`IDMember`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  MODIFY `IDDetailTransaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `handphone`
--
ALTER TABLE `handphone`
  MODIFY `IDHandphone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `IDMember` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `IDSupplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `IDTransaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD CONSTRAINT `detailtransaksi_ibfk_1` FOREIGN KEY (`IDTransaksi`) REFERENCES `transaksi` (`IDTransaksi`),
  ADD CONSTRAINT `detailtransaksi_ibfk_2` FOREIGN KEY (`IDHandphone`) REFERENCES `handphone` (`IDHandphone`);

--
-- Constraints for table `handphone`
--
ALTER TABLE `handphone`
  ADD CONSTRAINT `handphone_ibfk_1` FOREIGN KEY (`IDSupplier`) REFERENCES `supplier` (`IDSupplier`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`IDMember`) REFERENCES `member` (`IDMember`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
