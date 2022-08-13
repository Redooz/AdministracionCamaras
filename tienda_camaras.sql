-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2022 at 11:32 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tienda_camaras`
--

-- --------------------------------------------------------

--
-- Table structure for table `camaras`
--

CREATE TABLE `camaras` (
  `id` varchar(10) NOT NULL,
  `marca` varchar(10) NOT NULL,
  `lente` varchar(5) NOT NULL,
  `precio` double NOT NULL,
  `rollo` varchar(10) DEFAULT NULL,
  `visor` varchar(10) DEFAULT NULL,
  `memoria` varchar(5) DEFAULT NULL,
  `pantalla` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `camaras`
--

INSERT INTO `camaras` (`id`, `marca`, `lente`, `precio`, `rollo`, `visor`, `memoria`, `pantalla`) VALUES
('1', 'Nikon', '50mm', 1000, NULL, NULL, '32gb', '7\"'),
('2', 'Canon', '35mm', 2000, 'B/N', 'Optico', NULL, NULL),
('3', 'Sony', '125mm', 5000, NULL, NULL, '64gb', '10\"');

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

CREATE TABLE `clientes` (
  `id` varchar(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `telefono`) VALUES
('1000340920', 'Juan', '3108934920'),
('1001347100', 'Nicolas', '3118617627'),
('52840472', 'Nelly', '3115457171');

-- --------------------------------------------------------

--
-- Table structure for table `facturas`
--

CREATE TABLE `facturas` (
  `codigo` varchar(10) NOT NULL,
  `fecha` date NOT NULL,
  `id_cliente` varchar(11) NOT NULL,
  `id_camara` varchar(10) NOT NULL,
  `precio_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facturas`
--

INSERT INTO `facturas` (`codigo`, `fecha`, `id_cliente`, `id_camara`, `precio_total`) VALUES
('100', '2021-08-18', '1001347100', '3', 4750),
('101', '2022-08-02', '52840472', '1', 1000),
('102', '2022-08-01', '1000340920', '2', 2000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `camaras`
--
ALTER TABLE `camaras`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_camara` (`id_camara`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `facturas_ibfk_2` FOREIGN KEY (`id_camara`) REFERENCES `camaras` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
