-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 18, 2024 alle 10:19
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
  `is_done` int(11) NOT NULL,
  `bill_number` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `appointment`
--

INSERT INTO `appointment` (`id`, `date`, `time`, `id_customer`, `id_doctor`, `id_treatment`, `is_done`, `bill_number`, `notes`) VALUES
(1, '2024-11-22', '18:00:00', 1, 1, 1, 0, '', 'Il dottore potrbbe fare ritardo'),
(2, '2024-11-22', '12:00:00', 2, 1, 1, 1, '', NULL),
(3, '2023-10-26', '12:00:00', 3, 1, 1, 0, '', NULL),
(4, '2023-10-20', '18:00:00', 4, 1, 1, 0, '', NULL),
(5, '2023-10-25', '12:00:00', 1, 1, 1, 1, '', NULL),
(6, '2023-10-22', '18:00:00', 2, 1, 1, 0, '', 'Una mela al giorno toglie il medito di torno'),
(7, '2023-10-30', '12:00:00', 2, 1, 1, 1, '', NULL),
(8, '2023-11-22', '12:00:00', 5, 1, 1, 0, '', 'Nuovo cliente'),
(9, '2023-12-20', '18:00:00', 6, 1, 1, 0, '', NULL),
(10, '2023-12-25', '12:00:00', 7, 1, 1, 1, '', 'Something new'),
(11, '2024-11-21', '11:00:00', 5, NULL, NULL, 0, NULL, NULL),
(13, '2024-12-14', '15:00:00', 3, NULL, NULL, 0, NULL, NULL);

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
  `e_mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(11) NOT NULL DEFAULT 0 COMMENT 'it defines what data the user can read, add and edit:\r\n0 = login not allowed; \r\n10 = admin.',
  `salary` double DEFAULT NULL,
  `birth_date_string` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `customer`
--

INSERT INTO `customer` (`id`, `tax_id_code`, `name`, `surname`, `birth_city`, `birth_city_province`, `birth_date`, `residence_street`, `house_number`, `residence_city`, `residence_city_cap`, `residence_province`, `phone_number`, `phone_number2`, `e_mail`, `password`, `permission`, `salary`, `birth_date_string`) VALUES
(1, '1AAAAAAAAAAAAAAA', 'Mario', 'Rossi', 'Cosmo', 'RM', '1990-10-10', 'Leonardo Da Vinci', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'mariorossi@email.it', NULL, 0, 0, NULL),
(2, '1BBBBBBBBBBBBBBB', 'Giancarlo', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'giancarloversi@email.it', NULL, 0, 0, NULL),
(3, '1AAAAACCCAAAAAAA', 'Giovanni', 'Bianchi', 'Viterbo', 'RM', '1980-10-10', 'Michelangelo', '2', 'Cosenza', '09091', 'CS', '1234500000', '2349998901', 'giovannib@email.it', NULL, 0, 0, NULL),
(4, '1BBBBBBBBBBDDDDD', 'Giusy', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '20', 'Cosenza', '11088', 'CS', '9888776111', '2132762111', 'giusy@email.it', NULL, 0, 0, NULL),
(5, '1AAAAAAAAARRRRRE', 'Billy', 'Levi', 'Napoli', 'NA', '1990-10-10', 'Garibaldi', '5', 'Modena', '09091', 'MO', '1111167890', '2222228901', 'billy@email.it', NULL, 0, 0, NULL),
(6, '1BBBRRRFBBBBBBBB', 'Leo', 'Biaggi', 'Roma', 'RM', '1990-10-10', 'Cavour', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'giro@email.it', NULL, 0, 0, NULL),
(7, '1AKKKAAGAAAAAAAA', 'Milo', 'Isi', 'Cosmo', 'RM', '1994-07-10', 'Vittorio Emanuele', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'milo@email.it', NULL, 0, 0, NULL),
(8, '12222BBHBBBBBBBB', 'Ciro', 'Adi', 'Roma', 'RM', '1989-07-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'ciro@email.it', NULL, 0, 0, NULL),
(33, '123456781234567', 'Giorgio', 'Vanni', 'Roma', 'FR', '1980-09-10', 'via garibaldi', '23', 'Milano', '20019', 'MI', '1212343423', NULL, 'g.vanni@email.com', NULL, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `dental_material`
--

CREATE TABLE `dental_material` (
  `id` bigint(20) NOT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `one_piece_cost` double DEFAULT NULL,
  `cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `e_mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(11) NOT NULL DEFAULT 0 COMMENT 'it defines what data the user can read, add and edit:\r\n0 = login not allowed; \r\n10 = admin.',
  `phone_number2` varchar(255) DEFAULT NULL,
  `birth_date_string` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `employee`
--

INSERT INTO `employee` (`id`, `name`, `surname`, `title`, `birth_date`, `salary`, `phone_number`, `phone_number_2`, `e_mail`, `password`, `permission`, `phone_number2`, `birth_date_string`) VALUES
(1, 'Gianni', 'Marca', 'Dott.', '1979-01-01', 0, '1212122334', '', 'giannimarca@email.com', '1234', 0, NULL, NULL),
(2, 'Eddy', 'Vetri', 'Sig.', '1979-02-10', 0, '1254122334', '', 'eddyvetri@email.com', '', 0, NULL, NULL),
(3, 'Mila', 'Catogno', 'Sig.na', '1989-02-10', 0, '1254124314', '', 'mila@email.com', '', 0, NULL, NULL),
(4, 'Giusy', 'Frocca', 'Dott.ssa', '1982-02-10', 0, '1254120301', '', 'giusyfrocca@email.com', '', 0, NULL, NULL),
(5, 'Matteo', 'Gianni', 'Dott.', '1979-03-12', 0, '1222220301', '4445556634', 'mattih@email.com', '', 0, NULL, NULL),
(18, 'Carlo', 'Conti', 'Dott.', '1980-08-11', 0, '1223341223', NULL, 'c.conti@email.it', '$2a$10$N.HjH/etkqHxsOY6UnNbhOpeGeFpWpVSwicvGtUN3DS42.yZtXWmu', 10, '3445234512', NULL);

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
  `tag` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 3, 3),
(5, 3, 1),
(6, 3, 4),
(7, 4, 5),
(8, 5, 6),
(9, 5, 7),
(29, 18, 1),
(30, 18, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `medical_history`
--

CREATE TABLE `medical_history` (
  `id` bigint(20) NOT NULL,
  `id_customer` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(7, 'Odontoiatra', 'Si occupa di interno cavo orale');

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
(2, 'Pulizia dei denti', 'Toglie il tartaro', 50),
(3, 'Pulizia dei denti', 'Toglie il tartaro', 50);

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
  ADD KEY `fk_customer` (`id_customer`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT per la tabella `dental_material`
--
ALTER TABLE `dental_material`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT per la tabella `expense`
--
ALTER TABLE `expense`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `has_professional_role`
--
ALTER TABLE `has_professional_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT per la tabella `medical_history`
--
ALTER TABLE `medical_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `professional_role`
--
ALTER TABLE `professional_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT per la tabella `treatment`
--
ALTER TABLE `treatment`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `work_period`
--
ALTER TABLE `work_period`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

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
-- Limiti per la tabella `has_professional_role`
--
ALTER TABLE `has_professional_role`
  ADD CONSTRAINT `fk_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `fk_professional_role` FOREIGN KEY (`id_professional_role`) REFERENCES `professional_role` (`id`);

--
-- Limiti per la tabella `medical_history`
--
ALTER TABLE `medical_history`
  ADD CONSTRAINT `fk_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`);

--
-- Limiti per la tabella `work_period`
--
ALTER TABLE `work_period`
  ADD CONSTRAINT `fk_work_period_to_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
