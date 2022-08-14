-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-08-2022 a las 23:18:24
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_camaras`
--
CREATE DATABASE IF NOT EXISTS `tienda_camaras` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `tienda_camaras`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camaras`
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
-- Volcado de datos para la tabla `camaras`
--

INSERT INTO `camaras` (`id`, `marca`, `lente`, `precio`, `rollo`, `visor`, `memoria`, `pantalla`) VALUES
('1', 'Nikon', '50mm', 1000, NULL, NULL, '32gb', '7\"'),
('2', 'Canon', '35mm', 2000, 'B/N', 'Optico', NULL, NULL),
('250', 'Lumix', '300mm', 50000, 'B/N', 'Optico', NULL, NULL),
('3', 'Sony', '125mm', 5000, NULL, NULL, '64gb', '10\"');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` varchar(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `telefono`) VALUES
('1000340920', 'Juan', '3108934920'),
('1001347100', 'Nicolas', '3118617627'),
('1007476109', 'Daniel', '3193744861'),
('52840472', 'Nelly', '3115457171');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `codigo` varchar(10) NOT NULL,
  `fecha` date NOT NULL,
  `id_cliente` varchar(11) NOT NULL,
  `id_camara` varchar(10) NOT NULL,
  `precio_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`codigo`, `fecha`, `id_cliente`, `id_camara`, `precio_total`) VALUES
('100', '2021-08-18', '1001347100', '3', 4750),
('101', '2022-08-02', '52840472', '1', 1000),
('102', '2022-08-01', '1000340920', '2', 2000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camaras`
--
ALTER TABLE `camaras`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_camara` (`id_camara`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `facturas_ibfk_2` FOREIGN KEY (`id_camara`) REFERENCES `camaras` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
