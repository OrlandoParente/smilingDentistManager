-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Gen 31, 2025 alle 15:25
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sdm_db`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `id_customer` bigint(20) DEFAULT NULL,
  `id_doctor` bigint(20) DEFAULT NULL,
  `id_treatment` bigint(20) DEFAULT NULL,
  `is_done` int(11) NOT NULL DEFAULT 0,
  `invoice_number` varchar(255) DEFAULT NULL,
  `teeth` varchar(100) DEFAULT NULL,
  `payment` double DEFAULT 0,
  `payment_method` varchar(50) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `appointment`
--

INSERT INTO `appointment` (`id`, `date`, `time`, `id_customer`, `id_doctor`, `id_treatment`, `is_done`, `invoice_number`, `teeth`, `payment`, `payment_method`, `notes`) VALUES
(1, '2024-11-22', '18:00:00', 1, 1, 1, 0, '', NULL, 0, NULL, 'Il dottore potrbbe fare ritardo'),
(2, '2024-11-22', '12:00:00', 2, 1, 1, 1, '', NULL, 0, NULL, NULL),
(3, '2023-10-30', '12:00:00', 6, 1, 1, 0, 'AAAAA', NULL, 100, NULL, NULL),
(8, '2023-11-22', '12:00:00', 5, 1, 1, 0, '', NULL, 0, NULL, 'Nuovo cliente'),
(10, '2023-12-25', '12:00:00', 7, 1, 1, 1, '', NULL, 0, NULL, 'Something new'),
(11, '2024-11-21', '11:00:00', 5, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL),
(13, '2024-12-14', '15:00:00', 3, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL),
(15, '2024-12-18', '12:00:00', 3, 5, 1, 0, NULL, NULL, 0, NULL, 'Rosso di sera, bel tempo si spera'),
(16, '2024-12-18', '15:00:00', 6, 18, 1, 0, NULL, NULL, 0, NULL, 'zsxdfcgjkbjvkgtyvujbukn'),
(18, '2024-12-19', '12:00:00', 6, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL),
(31, '2024-12-19', '11:00:00', 4, 3, NULL, 0, NULL, NULL, 0, NULL, 'effafvbgsfdvdfewv  f dfc xfd cxa'),
(32, '2024-12-19', '21:10:00', 6, 18, NULL, 0, NULL, NULL, 0, NULL, '1232e321e231'),
(42, '2024-12-20', '11:00:00', 1, 2, NULL, 0, NULL, NULL, 0, NULL, ''),
(43, '2024-12-20', '12:00:00', 4, 3, NULL, 0, NULL, NULL, 0, NULL, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
(44, '2024-12-21', '11:00:00', 4, 4, NULL, 0, NULL, NULL, 0, NULL, ''),
(46, '2024-12-23', '11:00:00', 2, 2, NULL, 0, NULL, NULL, 0, NULL, ''),
(48, '2024-12-23', '20:00:00', 3, 3, NULL, 0, NULL, NULL, 0, NULL, ''),
(51, '2024-12-24', '12:00:00', 6, 2, NULL, 0, NULL, NULL, 0, NULL, ''),
(56, '2024-12-26', '09:00:00', 7, NULL, NULL, 0, NULL, NULL, 0, NULL, ''),
(57, '2024-12-27', '09:00:00', 33, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(60, '2024-12-23', '12:00:00', 33, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(61, '2024-12-14', '11:00:00', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(70, '2024-12-29', '06:00:00', 1, 3, NULL, 0, NULL, NULL, NULL, NULL, ''),
(71, '2024-12-30', '11:00:00', 3, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(72, '2024-12-21', '06:00:00', 7, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(73, '2024-12-30', '06:00:00', 8, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(74, '2024-12-24', '20:00:00', 3, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(75, '2024-01-02', '20:00:00', 4, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(77, '2025-01-02', '12:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(79, '2025-01-04', '06:00:00', 4, 5, NULL, 0, NULL, NULL, NULL, NULL, ''),
(80, '2025-01-08', '12:00:00', 4, 4, NULL, 0, NULL, NULL, NULL, NULL, ''),
(82, '2025-01-11', '09:00:00', 6, 2, 4, 0, NULL, NULL, NULL, NULL, ''),
(83, '2025-01-11', '08:30:00', 2, 2, 1, 0, NULL, NULL, NULL, NULL, 'provadsvgzdxvxzcvxzcvxz'),
(85, '2025-01-12', '20:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(86, '2025-01-13', '06:00:00', 5, 3, NULL, 0, NULL, NULL, NULL, NULL, ''),
(87, '2025-01-13', '20:00:00', 45, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(88, '2025-01-13', '15:00:00', 43, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(89, '2025-01-14', '21:10:00', 7, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(90, '2025-01-15', '12:00:00', 6, 18, 1, 0, NULL, NULL, NULL, NULL, ''),
(91, '2025-01-15', '12:00:00', 6, 18, 1, 0, NULL, NULL, NULL, NULL, ''),
(92, '2025-12-21', '15:00:00', 6, 18, 7, 0, '43534534fdg', NULL, 120, NULL, '1232e321e231'),
(95, '2025-01-13', '06:00:00', 6, 3, 7, 0, NULL, NULL, NULL, NULL, 'jkhulkjljklkj'),
(96, '2025-01-11', '06:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(97, '2025-01-14', '06:00:00', 1, 1, NULL, 0, NULL, NULL, NULL, NULL, ''),
(98, '2025-01-30', '11:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(99, '2025-01-14', '20:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb'),
(100, '2025-01-13', '11:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, 'una mela al giorno toglie il medico di torno'),
(102, '2025-01-14', '12:00:00', 6, NULL, NULL, 0, '43534534fdg', NULL, 234, NULL, ''),
(103, '2025-01-14', '06:00:00', 7, NULL, NULL, 0, '43534534fdg', NULL, 3245, NULL, ''),
(104, '2025-12-28', '12:00:00', 4, NULL, NULL, 0, '43534534fdg', NULL, 23423, NULL, ''),
(105, '2025-12-28', '06:00:00', 43, NULL, NULL, 0, 'tttttttttt', NULL, 0, NULL, ''),
(107, '2025-12-28', '21:10:00', 4, NULL, NULL, 0, 'bbbbb', NULL, 570, NULL, 'dddddd'),
(108, '2025-12-28', '11:00:00', 45, NULL, NULL, 0, 'rrrrrr', NULL, 400, NULL, ''),
(109, '2025-12-28', '20:00:00', 45, NULL, NULL, 0, 'rrrrrr', NULL, 0, NULL, ''),
(110, '2025-01-13', '15:00:00', 3, NULL, NULL, 0, 'rgfdgfdsgfg', NULL, NULL, NULL, ''),
(111, '2025-12-28', '15:00:00', 5, NULL, NULL, 0, 'fsdfgdgdfgf', NULL, NULL, NULL, ''),
(112, '2025-12-30', '20:00:00', 6, 18, 1, 0, 'qqqqqqq', NULL, 20, NULL, 'aaaaaaaaaaaaaaaaaaaaaaaaaa'),
(113, '2025-12-29', '12:00:00', 6, 2, 1, 0, 'aaaaaaaa', NULL, 100, NULL, 'dddddddddddddddddddddddd'),
(114, '2025-01-14', '12:00:00', 4, 3, 1, 0, '7777777777777', NULL, 200, NULL, 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhh'),
(115, '2025-01-14', '06:00:00', 6, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(121, '2025-12-30', '21:10:00', 6, 5, 1, 0, 'fdhgfhfg', NULL, 500, NULL, 'una mela al giorno toglie il medico di torno'),
(122, '2025-01-14', '20:00:00', 5, 18, 1, 0, 'rrrrrr', NULL, 52, NULL, '1232e321e231'),
(123, '2025-01-16', '20:00:00', 4, 18, 1, 0, 'AAAAAAA', NULL, 0, NULL, ''),
(124, '2025-01-18', '12:00:00', 5, 1, 1, 0, 'AAAAAAA', NULL, 12, NULL, ''),
(125, '2035-01-14', '20:00:00', 7, NULL, NULL, 0, NULL, NULL, NULL, NULL, ''),
(126, '2025-12-30', '12:00:00', 6, NULL, NULL, 0, '43534534fdg', NULL, 234, NULL, '');

-- --------------------------------------------------------

--
-- Struttura della tabella `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `tax_id_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `birth_city` varchar(255) DEFAULT NULL,
  `birth_city_province` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `residence_street` varchar(255) DEFAULT NULL,
  `house_number` varchar(255) DEFAULT NULL,
  `residence_city` varchar(255) DEFAULT NULL,
  `residence_city_cap` varchar(255) DEFAULT NULL,
  `residence_province` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `phone_number2` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `e_mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(11) NOT NULL DEFAULT 0 COMMENT 'it defines what data the user can read, add and edit:\r\n0 = login not allowed; \r\n10 = admin.',
  `salary` double DEFAULT NULL,
  `birth_date_string` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `customer`
--

INSERT INTO `customer` (`id`, `tax_id_code`, `name`, `surname`, `birth_city`, `birth_city_province`, `birth_date`, `residence_street`, `house_number`, `residence_city`, `residence_city_cap`, `residence_province`, `phone_number`, `phone_number2`, `language`, `e_mail`, `password`, `permission`, `salary`, `birth_date_string`) VALUES
(1, '1AAAAAAAAAAAAAAA', 'Mario', 'Rossi', 'Cosmo', 'RM', '1990-10-10', 'Leonardo Da Vinci', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'en', 'mariorossi@email.it', NULL, 0, 0, NULL),
(2, '1BBBBBBBBBBBBBBB', 'Giancarlo', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'en', 'giancarloversi@email.it', NULL, 0, 0, NULL),
(3, '1AAAAACCCAAAAAAA', 'Giovanni', 'Bianchi', 'Viterbo', 'RM', '1980-10-10', 'Michelangelo', '2', 'Cosenza', '09091', 'CS', '1234500000', '2349998901', 'en', 'giovannib@email.it', NULL, 0, 0, NULL),
(4, '1BBBBBBBBBBDDDDD', 'Giusy', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '20', 'Cosenza', '11088', 'CS', '9888776111', '2132762111', 'en', 'giusy@email.it', NULL, 0, 0, NULL),
(5, '1AAAAAAAAARRRRRE', 'Billy', 'Levi', 'Napoli', 'NA', '1990-10-10', 'Garibaldi', '5', 'Modena', '09091', 'MO', '1111167890', '2222228901', 'en', 'billy@email.it', NULL, 0, 0, NULL),
(6, '1BBBRRRFBBBBBBBB', 'Giovanni', 'Esposito', 'Roma', 'RM', '1990-10-10', 'Cavour', '15', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'en', 'hello@email.it', NULL, 1, 0, '1990-10-10'),
(7, '1AKKKAAGAAAAAAAA', 'Milo', 'Isi', 'Cosmo', 'RM', '1994-07-10', 'Vittorio Emanuele', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'en', 'milo@email.it', NULL, 0, 0, NULL),
(8, '12222BBHBBBBBBBB', 'Ciro', 'Adi', 'Roma', 'RM', '1989-07-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'en', 'ciro@email.it', NULL, 0, 0, NULL),
(33, '123456781234567', 'Giorgio', 'Vanni', 'Roma', 'FR', '1980-09-10', 'via garibaldi', '23', 'Milano', '20019', 'MI', '1212343423', NULL, 'en', 'g.vanni@email.com', NULL, 0, 0, NULL),
(39, 'aaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaa', NULL, 'rm', '1990-03-04', 'fdgfdgsdf', '1', 'fdgfdgfd', '3432', 'sgv', '111111111111111', '11111111111111', NULL, 'aaaaaaaaaaaaaaaa', NULL, 0, NULL, '1990-03-04'),
(40, 'bbbbbbbbbbbbbb', 'bbbbbbbbbbbbbb', 'bbbbbbbbbbbb', 'bbbbbbbbbbbbbbb', 'bbbb', '1990-03-04', 'bbbbbbbbbb', '22', 'bbbbbbbbbb', '2222', 'vv', '222222222222222', '2222222222222222', NULL, 'bbbbbbbbbbbbbbbb', NULL, 1, NULL, '1990-03-04'),
(43, 'ASDFGHERT3456', 'Matteo', 'Renzi', 'Firenze', 'FI', '1975-02-02', 'Dante', '34', 'Firenze', '2222', 'FI', '123456534', '3245443534', NULL, 'metteo@email.it', NULL, 0, NULL, '1975-02-02'),
(45, 'CCCDDDEWWREW3', 'Checco', 'Zalone', 'Firenze', 'FI', '1990-10-10', 'Cavour', '1', 'Firenze', '09088', 'FI', '2134324', NULL, NULL, 'checco@email.it', NULL, 1, NULL, '1990-10-10');

-- --------------------------------------------------------

--
-- Struttura della tabella `dental_material`
--

CREATE TABLE `dental_material` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `dental_material`
--

INSERT INTO `dental_material` (`id`, `quantity`, `name`, `description`, `cost`) VALUES
(1, 6, 'Anestesia', 'Provatta da 100 ml', 20),
(2, 50, 'Trapano', 'Attrezzo infernale', 50),
(3, 77, 'Cemento', 'Cemento armato per ristrutturazione denti ', 20),
(4, 145, 'Computer ', 'Computer aziendale per postazione assistenza clienti ', 1000.5),
(5, 12, 'Cacciavite', 'Attrezzo minaccioso ', 12),
(6, 35678, 'Pinze', 'Strumenti di terrore ', 29.8),
(11, 0, 'bbbb', ' ', 12.56);

-- --------------------------------------------------------

--
-- Struttura della tabella `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `salary` double DEFAULT 0,
  `phone_number` varchar(255) DEFAULT NULL,
  `phone_number_2` varchar(15) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `e_mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(11) NOT NULL DEFAULT 0 COMMENT 'it defines what data the user can read, add and edit:\r\n0 = login not allowed; \r\n10 = admin.',
  `phone_number2` varchar(255) DEFAULT NULL,
  `birth_date_string` varchar(255) DEFAULT NULL,
  `salary_string` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `employee`
--

INSERT INTO `employee` (`id`, `name`, `surname`, `title`, `birth_date`, `salary`, `phone_number`, `phone_number_2`, `language`, `e_mail`, `password`, `permission`, `phone_number2`, `birth_date_string`, `salary_string`) VALUES
(1, 'Gianni', 'Esposito', 'Dott.', '1979-01-01', 0, '1212122334', '', 'en', 'giannimarca@email.com', '1234', 0, '', '1979-01-01', '0.0'),
(2, 'Ernesto', 'Bianchi', 'Sig.', '1979-02-10', 0, '1254122334', '', 'en', 'ernesto@email.com', '', 0, '', '1979-02-10', '0.0'),
(3, 'Carla', 'Catogno', 'Sig.ra', '1989-02-10', 1000, '1254124314', '', 'en', 'mila@email.com', '', 0, '', '1989-02-10', '1000.0'),
(4, 'Giusy', 'Frocca', 'Dott.ssa', '1982-02-10', 234324, '1254120301', '', 'en', 'giusyfrocca@email.com', '', 0, NULL, '1982-02-10', '234324.0'),
(5, 'Matteo', 'Gianni', 'Dott.', '1979-03-12', 0, '1222220301', '4445556634', 'en', 'mattih@email.com', '', 0, NULL, NULL, NULL),
(18, 'Carlo', 'Conti', 'Sig.', '1980-08-11', 2000, '1223341223', NULL, 'it', 'c.conti@email.it', '$2a$10$UsbIHaJu.0nBIoa4129s.eDJIm4EhnqvQjqDZOKMkRR23FEAJdUQ2', 10, '3445234512', '1980-08-11', '2000.0');

-- --------------------------------------------------------

--
-- Struttura della tabella `expense`
--

CREATE TABLE `expense` (
  `id` bigint(20) NOT NULL,
  `id_customer` bigint(20) DEFAULT NULL,
  `id_employee` bigint(20) DEFAULT NULL,
  `id_dental_material` bigint(20) DEFAULT NULL,
  `amount` double NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tag` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `expense`
--

INSERT INTO `expense` (`id`, `id_customer`, `id_employee`, `id_dental_material`, `amount`, `date`, `description`, `tag`) VALUES
(1, NULL, NULL, 3, 788, '2025-01-14', '500 x Cemento', 'acquisto merce'),
(2, NULL, NULL, 3, 680, '2025-01-14', '800 x Cemento', 'acquisto merce'),
(3, NULL, NULL, 5, 144, '2025-12-30', '12 x Cacciavite', 'dental material purchase'),
(5, NULL, NULL, 1, 500, '2025-01-14', '25 x Anestesia', 'acquisto merce'),
(8, NULL, NULL, 4, 90, '2023-10-23', '180 x Computer ', 'acquisto merce'),
(9, NULL, NULL, 1, 360, '2025-01-14', '18 x Anestesia', 'acquisto merce'),
(10, NULL, NULL, NULL, 6, '2025-01-14', 'rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 'acquisto merce'),
(12, NULL, NULL, NULL, 15, '2025-01-14', NULL, 'acquisto merce'),
(14, NULL, NULL, NULL, 1, '2025-12-30', NULL, ''),
(15, NULL, NULL, NULL, 2, '2025-12-28', 'jkbkjjknkjnjk', 'acquisto merce'),
(16, 45, NULL, NULL, 123, '2025-12-30', 'Rimborso Zalone Checco CCCDDDEWWREW3', 'rimborso cliente'),
(17, NULL, NULL, NULL, 2, '2025-12-30', 'oooooooooooooooooooooooo', ''),
(18, NULL, NULL, NULL, 1500, '2025-12-28', 'Pagamento stipendio  Gianni Matteo', 'pagamento salario'),
(19, 1, NULL, NULL, 2323, '2025-12-30', 'Rimborso Anestesia x 23', 'acquisto merce'),
(20, 4, NULL, NULL, 1, '2025-12-28', 'Acquisto  Computer x 111111111', 'acquisto merce'),
(22, NULL, NULL, NULL, 1200, '2025-01-14', NULL, 'bolletta'),
(24, NULL, NULL, 4, 5002.5, '2026-12-30', '5 x Computer ', 'acquisto merce'),
(25, NULL, NULL, NULL, 1234, '2027-01-14', NULL, 'affitto');

-- --------------------------------------------------------

--
-- Struttura della tabella `has_medical_history`
--

CREATE TABLE `has_medical_history` (
  `id` bigint(20) NOT NULL,
  `id_customer` bigint(20) NOT NULL,
  `id_medical_history` bigint(20) NOT NULL,
  `notes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `has_medical_history`
--

INSERT INTO `has_medical_history` (`id`, `id_customer`, `id_medical_history`, `notes`) VALUES
(1, 1, 11, 'aaaaaaaaaa'),
(2, 1, 12, 'dddddd'),
(3, 5, 19, 'weqwewq'),
(4, 5, 15, 'dsfdsf'),
(5, 2, 16, 'dsfdsf'),
(6, 4, 12, 'dsfdsf'),
(8, 33, 30, 'etrgretrsd'),
(11, 6, 16, 'una mela al giorno toglie il medico di torno'),
(12, 6, 39, NULL),
(14, 6, 41, 'bbbbbbbbbbbbbbcccccccccccccccccccccccccc'),
(15, 3, 21, ''),
(16, 3, 27, ''),
(17, 3, 39, ''),
(18, 3, 31, 'eeeee'),
(19, 3, 10, ''),
(20, 3, 12, 'eeeeeeeeeeeeeeeeee'),
(21, 3, 43, 'bbbbbbbbbbbbbbbbbbbbbbbbbbbb'),
(22, 3, 11, 'czxczxcxzcxz'),
(23, 3, 20, 'bbbbbbbbbbbbbbb'),
(24, 3, 36, 'una mela al giorno toglie il medico di torno'),
(30, 6, 20, ''),
(31, 6, 18, 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');

-- --------------------------------------------------------

--
-- Struttura della tabella `has_professional_role`
--

CREATE TABLE `has_professional_role` (
  `id` bigint(20) NOT NULL,
  `id_employee` bigint(20) DEFAULT NULL,
  `id_professional_role` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `has_professional_role`
--

INSERT INTO `has_professional_role` (`id`, `id_employee`, `id_professional_role`) VALUES
(7, 4, 5),
(8, 5, 6),
(9, 5, 7),
(110, 3, 3),
(111, 3, 1),
(112, 3, 4),
(170, 2, 3),
(171, 1, 4),
(172, 1, 1),
(180, 18, 3),
(181, 18, 6);

-- --------------------------------------------------------

--
-- Struttura della tabella `medical_history`
--

CREATE TABLE `medical_history` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_customer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `medical_history`
--

INSERT INTO `medical_history` (`id`, `type`, `category`, `name`, `description`, `id_customer`) VALUES
(10, 'Generale', 'Malattia', 'Affezioni cardiache', '', NULL),
(11, 'Generale', 'Malattia', 'Affezzioni pressione sanguigna', '', NULL),
(12, 'Generale', 'Malattia', 'Affezioni renali', '', NULL),
(13, 'Generale', 'Malattia', 'Malattie del sangue', '', NULL),
(14, 'Generale', 'Malattia', 'Malattie infettive', '', NULL),
(15, 'Generale', 'Malattia', 'Malattie oculari', '', NULL),
(16, 'Generale', 'Malattia', 'Diabete tipo1', '', NULL),
(17, 'Generale', 'Malattia', 'Diabete tipo2', '', NULL),
(18, 'Generale', 'Malattia', 'Diabete tipo3', '', NULL),
(19, 'Generale', 'Malattia', 'Malattie gastrointestinali', '', NULL),
(20, 'Generale', 'Malattia', 'Asma', '', NULL),
(21, 'Generale', 'Intolleranza alimentale', 'Intelleranza al lattosio', '', NULL),
(22, 'Generale', 'Intolleranza alimentale', 'Intolleranza al glutine', '', NULL),
(23, 'Generale', 'Intolleranza alimentale', 'Intolleranza al Fodmap', 'I FODMAP sono carboidrati fermentabili naturalmente presenti in molti cibi e per alcune persone sono poco digeribil', NULL),
(24, 'Generale', 'Allergia farmaci', 'farmaco 1 ', '', NULL),
(25, 'Generale', 'Allergia farmaci', 'farmaco 2 ', '', NULL),
(26, 'Generale', 'Assunzione farmaci', 'farmaco 1', '', NULL),
(27, 'Generale', 'Stato', 'Fumatore ', '', NULL),
(28, 'Generale', 'Stato', 'È  in gravidanza ', '', NULL),
(29, 'Generale', 'Intervento chirurgo', 'intervento 1', '', NULL),
(30, 'Odontoiatrica', 'Sintomi', 'Sanguinamento gengivale ricorrente ', '', NULL),
(31, 'Odontoiatrica', 'Sintomi', 'Digrigna i denti', '', NULL),
(32, 'Odontoiatrica', 'Sintomi', 'Stanchezza muscolare al risveglio ', '', NULL),
(33, 'Odontoiatrica', 'Igiene domiciliare', 'lava i denti Non Sempre', '', NULL),
(34, 'Odontoiatrica', 'Igiene domiciliare', 'lava i denti 1 volta al giorno ', '', NULL),
(35, 'Odontoiatrica', 'Igiene domiciliare', 'lava i denti 2 volte al giorno ', '', NULL),
(36, 'Odontoiatrica', 'Igiene domiciliare', 'lava i denti 3 volte al giorno ', '', NULL),
(37, 'Odontoiatrica', 'Igiene domiciliare', 'usa spazzolino manuale', '', NULL),
(38, 'Odontoiatrica', 'Igiene domiciliare', 'usa spazzolino elettrico ', '', NULL),
(39, 'Odontoiatrica', 'Igiene domiciliare', 'Filo interdentale ', '', NULL),
(40, 'Odontoiatrica', 'Igiene domiciliare', 'Scovolino', '', NULL),
(41, 'Odontoiatrica', 'Igiene domiciliare', 'Collutorio ', '', NULL),
(43, 'Odontoiatrica', 'Sintomi', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `professional_role`
--

CREATE TABLE `professional_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `professional_role`
--

INSERT INTO `professional_role` (`id`, `name`, `description`) VALUES
(1, 'Direttore Sanitario', 'Il capo supremo dello studio'),
(2, 'Medico chirurgo odontoiatra', 'Odontoiatra plus'),
(3, 'Amministratore unico', 'Comanda tutto lui'),
(4, 'Igienista dentale', 'igienizza'),
(5, 'Assistente alla poltrona', 'Gestisce le prenotazioni e i clienti'),
(6, 'Odontotecnico', 'Costruisce le dentiere'),
(7, 'Odontoiatra', 'Si occupa di interno cavo orale'),
(22, 'fdshsfdhdf', 'fdhfdhfgfhg'),
(23, 'hello world', 'jkbkjjknkjnjk');

-- --------------------------------------------------------

--
-- Struttura della tabella `treatment`
--

CREATE TABLE `treatment` (
  `id` bigint(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `treatment`
--

INSERT INTO `treatment` (`id`, `name`, `description`, `cost`) VALUES
(1, 'Pulizia dei denti', 'Toglie il tartaro', 50),
(4, 'Robe che si fanno dal dentista', '', 100.45),
(7, 'Estrazione dente', '', 100);

-- --------------------------------------------------------

--
-- Struttura della tabella `work_period`
--

CREATE TABLE `work_period` (
  `id` bigint(20) NOT NULL,
  `id_employee` bigint(20) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `working_agreement` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `work_period`
--

INSERT INTO `work_period` (`id`, `id_employee`, `start_date`, `end_date`, `working_agreement`, `notes`) VALUES
(32, 1, '2030-03-04', '2029-02-01', '', ''),
(33, 2, '2002-12-12', '2029-02-01', '', ''),
(34, 1, '2030-03-04', '2049-02-01', '', ''),
(35, 1, '2030-03-04', '2035-02-01', '', ''),
(37, 2, '2002-12-12', NULL, '', ''),
(38, 5, '2030-03-04', NULL, '', ''),
(39, 1, '2040-02-01', '2045-02-01', '', ''),
(40, 1, '2046-01-01', '2050-01-01', '', '');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_appointment_to_customer` (`id_customer`),
  ADD KEY `fk_appointment_to_treatment` (`id_treatment`),
  ADD KEY `fk_appointment_to_doctor` (`id_doctor`);

--
-- Indici per le tabelle `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `dental_material`
--
ALTER TABLE `dental_material`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `to_customer` (`id_customer`),
  ADD KEY `to_dental_materials` (`id_dental_material`),
  ADD KEY `to_employee` (`id_employee`);

--
-- Indici per le tabelle `has_medical_history`
--
ALTER TABLE `has_medical_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_customer` (`id_customer`),
  ADD KEY `id_medical_history` (`id_medical_history`);

--
-- Indici per le tabelle `has_professional_role`
--
ALTER TABLE `has_professional_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_professional_role` (`id_professional_role`) USING BTREE,
  ADD KEY `fk_employee` (`id_employee`) USING BTREE;

--
-- Indici per le tabelle `medical_history`
--
ALTER TABLE `medical_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1qj29sucrojlr34vibp1a8cq6` (`id_customer`);

--
-- Indici per le tabelle `professional_role`
--
ALTER TABLE `professional_role`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `work_period`
--
ALTER TABLE `work_period`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_employee` (`id_employee`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT per la tabella `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT per la tabella `dental_material`
--
ALTER TABLE `dental_material`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT per la tabella `expense`
--
ALTER TABLE `expense`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT per la tabella `has_medical_history`
--
ALTER TABLE `has_medical_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT per la tabella `has_professional_role`
--
ALTER TABLE `has_professional_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- AUTO_INCREMENT per la tabella `medical_history`
--
ALTER TABLE `medical_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT per la tabella `professional_role`
--
ALTER TABLE `professional_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT per la tabella `treatment`
--
ALTER TABLE `treatment`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `work_period`
--
ALTER TABLE `work_period`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_appointment_to_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_appointment_to_doctor` FOREIGN KEY (`id_doctor`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `fk_appointment_to_treatment` FOREIGN KEY (`id_treatment`) REFERENCES `treatment` (`id`);

--
-- Limiti per la tabella `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `to_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `to_dental_materials` FOREIGN KEY (`id_dental_material`) REFERENCES `dental_material` (`id`),
  ADD CONSTRAINT `to_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`);

--
-- Limiti per la tabella `has_medical_history`
--
ALTER TABLE `has_medical_history`
  ADD CONSTRAINT `has_medical_history_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `has_medical_history_ibfk_2` FOREIGN KEY (`id_medical_history`) REFERENCES `medical_history` (`id`);

--
-- Limiti per la tabella `has_professional_role`
--
ALTER TABLE `has_professional_role`
  ADD CONSTRAINT `fk_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `fk_professional_role` FOREIGN KEY (`id_professional_role`) REFERENCES `professional_role` (`id`);

--
-- Limiti per la tabella `medical_history`
--
ALTER TABLE `medical_history`
  ADD CONSTRAINT `FK1qj29sucrojlr34vibp1a8cq6` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`);

--
-- Limiti per la tabella `work_period`
--
ALTER TABLE `work_period`
  ADD CONSTRAINT `fk_work_period_to_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
