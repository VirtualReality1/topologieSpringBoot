-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 30. Jun 2021 um 16:13
-- Server-Version: 10.4.19-MariaDB
-- PHP-Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `topologie`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `channel`
--

CREATE TABLE `channel` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `channel`
--

INSERT INTO `channel` (`id`, `name`, `version`) VALUES
(0, 'FTP', '1.0'),
(1, 'HTTP', '2.0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `communication`
--

CREATE TABLE `communication` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `partner_software_id` int(11) NOT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `messagetype_id` int(11) DEFAULT NULL,
  `software_id` int(11) DEFAULT NULL,
  `comtype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `communication`
--

INSERT INTO `communication` (`id`, `name`, `partner_software_id`, `channel_id`, `messagetype_id`, `software_id`, `comtype_id`) VALUES
(0, 'communiction_1', 2, 0, 2, 0, 1),
(1, 'communication_2', 1, 1, 2, 0, 0),
(2, 'communication_3', 0, 1, 1, 1, 0),
(3, 'communication_4', 3, 1, 0, 0, 0),
(4, 'communication_5', 0, 1, 2, 3, 0),
(5, 'communication_6', 0, 1, 3, 4, 0),
(8, 'communication_9', 7, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `communication_comtypes`
--

CREATE TABLE `communication_comtypes` (
  `communications_id` bigint(20) NOT NULL,
  `comtypes_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `comtype`
--

CREATE TABLE `comtype` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `comtype`
--

INSERT INTO `comtype` (`id`, `name`) VALUES
(0, 'HL7 v3'),
(1, 'HL7 v2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `com_tr_trigger`
--

CREATE TABLE `com_tr_trigger` (
  `tr_trigger_id` int(11) DEFAULT NULL,
  `communication_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `com_tr_trigger`
--

INSERT INTO `com_tr_trigger` (`tr_trigger_id`, `communication_id`) VALUES
(1, 8),
(2, 1),
(3, 0),
(4, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `field`
--

CREATE TABLE `field` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `field`
--

INSERT INTO `field` (`id`, `content`, `name`) VALUES
(0, '1', 'Feldtrennzeichen'),
(1, '2', 'Weitere Trennzeichen'),
(2, '3', 'Sendende Applikation'),
(3, '4', 'Sendende Einrichtung des Bereichs'),
(4, '5', 'Emfangender Bereich');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hardware`
--

CREATE TABLE `hardware` (
  `id` int(11) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hardware`
--

INSERT INTO `hardware` (`id`, `ip`, `name`, `version`, `type`) VALUES
(0, '172.17.233.136', 'ixserv_cardio', '1.3', NULL),
(1, '172.17.233.60', 'HEYDMED1_APPL', '1.0', NULL),
(2, '172.17.233.138', 'V-BIT-MIR-0001', '1.5', NULL),
(3, '172.17.233.30', 'Cardiobase_SERVER', '1.2', NULL),
(4, '172.17.233.56', 'DEBIT01R01', '6', ''),
(5, '172.17.233.171', 'BITSRV171', '5', NULL),
(6, '172.17.217.10', 'opusGER_maris', '4', NULL),
(7, '172.17.233.12', 'opusBIT_maris', '3', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `messagetype`
--

CREATE TABLE `messagetype` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `comtype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `messagetype`
--

INSERT INTO `messagetype` (`id`, `name`, `comtype_id`) VALUES
(0, 'ADT', 0),
(1, 'ORM', 0),
(2, 'MDM', 0),
(3, 'ORU', 0),
(4, 'BAR', 1),
(5, 'ORU-LAB', 1),
(6, 'ORU-RIS', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `segment`
--

CREATE TABLE `segment` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `messagetype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `segment`
--

INSERT INTO `segment` (`id`, `name`, `messagetype_id`) VALUES
(0, 'EVN', 0),
(1, 'MSH', 1),
(2, 'PID', 1),
(3, 'PV1', 1),
(4, 'MSH', 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `segment_fields`
--

CREATE TABLE `segment_fields` (
  `segments_id` int(11) NOT NULL,
  `fields_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `segment_fields`
--

INSERT INTO `segment_fields` (`segments_id`, `fields_id`) VALUES
(1, 0),
(1, 1),
(1, 2),
(1, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `software`
--

CREATE TABLE `software` (
  `id` int(11) NOT NULL,
  `os_name` varchar(255) DEFAULT NULL,
  `os_version` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `software`
--

INSERT INTO `software` (`id`, `os_name`, `os_version`, `name`, `type`, `version`) VALUES
(0, 'Windows Server 2020', '10', 'Mirth', 'KommServer', '2.0'),
(1, 'Windows Server 2020', '10', 'IXSERV', 'LIS', '1,5'),
(2, 'Microsoft Windows Embedded', '10', 'Hydmedia', 'CMS', '3.6'),
(3, 'Windows Server 2020', '10', 'Cardiobase', 'CIS', '8'),
(4, 'Windows', '8', 'OPUS::L/BIT', 'LMS', '8'),
(7, 'Windows', '9', 'Maris', 'AIS', '8');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `software_hardwares`
--

CREATE TABLE `software_hardwares` (
  `softwares_id` int(11) NOT NULL,
  `hardwares_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `software_hardwares`
--

INSERT INTO `software_hardwares` (`softwares_id`, `hardwares_id`) VALUES
(1, 0),
(3, 3),
(0, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tr_trigger`
--

CREATE TABLE `tr_trigger` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `tr_trigger`
--

INSERT INTO `tr_trigger` (`id`, `name`) VALUES
(0, 'Timer abgelaufen'),
(1, 'Patientenaufnahme'),
(2, 'Bluttransfusion'),
(3, 'Bildgebungsauftrag'),
(4, 'Patientenentlassung');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `channel`
--
ALTER TABLE `channel`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `communication`
--
ALTER TABLE `communication`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2dhqdm7eb0u1u3xsclhh8vw2t` (`channel_id`),
  ADD KEY `FKl1y94kgdsesmlfq8ddcfejk9j` (`messagetype_id`),
  ADD KEY `FKa8o2puagoj4lin00hhk2whxin` (`software_id`),
  ADD KEY `FK3sl4ubkc0gdegkdvmhcvop74i` (`comtype_id`);

--
-- Indizes für die Tabelle `communication_comtypes`
--
ALTER TABLE `communication_comtypes`
  ADD KEY `FK9qpx97wtfs2ssldtss2uo05ta` (`comtypes_id`),
  ADD KEY `FK59clcjxv4qjvdynvt1g7vvdpw` (`communications_id`);

--
-- Indizes für die Tabelle `comtype`
--
ALTER TABLE `comtype`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `com_tr_trigger`
--
ALTER TABLE `com_tr_trigger`
  ADD PRIMARY KEY (`communication_id`),
  ADD KEY `FKtj0j7g1rmpoy016dxransmeye` (`tr_trigger_id`);

--
-- Indizes für die Tabelle `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `hardware`
--
ALTER TABLE `hardware`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `messagetype`
--
ALTER TABLE `messagetype`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9jdm3cmilqlu457ascusaajj6` (`comtype_id`);

--
-- Indizes für die Tabelle `segment`
--
ALTER TABLE `segment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbths1h43ma20lgcybh4f0qhao` (`messagetype_id`);

--
-- Indizes für die Tabelle `segment_fields`
--
ALTER TABLE `segment_fields`
  ADD KEY `FKnam3oyb1rgym59apos58r7etu` (`fields_id`),
  ADD KEY `FKtbk0d1wxhflq24k1nt2qy2bxb` (`segments_id`);

--
-- Indizes für die Tabelle `software`
--
ALTER TABLE `software`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `software_hardwares`
--
ALTER TABLE `software_hardwares`
  ADD KEY `FKm04jb0pq9i2yq0qv97t4y827k` (`hardwares_id`),
  ADD KEY `FKdiun0kxhiqdf602whg8k46009` (`softwares_id`);

--
-- Indizes für die Tabelle `tr_trigger`
--
ALTER TABLE `tr_trigger`
  ADD PRIMARY KEY (`id`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `communication`
--
ALTER TABLE `communication`
  ADD CONSTRAINT `FK2dhqdm7eb0u1u3xsclhh8vw2t` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  ADD CONSTRAINT `FK3sl4ubkc0gdegkdvmhcvop74i` FOREIGN KEY (`comtype_id`) REFERENCES `comtype` (`id`),
  ADD CONSTRAINT `FKa8o2puagoj4lin00hhk2whxin` FOREIGN KEY (`software_id`) REFERENCES `software` (`id`),
  ADD CONSTRAINT `FKl1y94kgdsesmlfq8ddcfejk9j` FOREIGN KEY (`messagetype_id`) REFERENCES `messagetype` (`id`);

--
-- Constraints der Tabelle `communication_comtypes`
--
ALTER TABLE `communication_comtypes`
  ADD CONSTRAINT `FK59clcjxv4qjvdynvt1g7vvdpw` FOREIGN KEY (`communications_id`) REFERENCES `communication` (`id`),
  ADD CONSTRAINT `FK9qpx97wtfs2ssldtss2uo05ta` FOREIGN KEY (`comtypes_id`) REFERENCES `comtype` (`id`);

--
-- Constraints der Tabelle `com_tr_trigger`
--
ALTER TABLE `com_tr_trigger`
  ADD CONSTRAINT `FKa5kb4qcqk17h275l7kp66iy8i` FOREIGN KEY (`communication_id`) REFERENCES `communication` (`id`),
  ADD CONSTRAINT `FKtj0j7g1rmpoy016dxransmeye` FOREIGN KEY (`tr_trigger_id`) REFERENCES `tr_trigger` (`id`);

--
-- Constraints der Tabelle `messagetype`
--
ALTER TABLE `messagetype`
  ADD CONSTRAINT `FK9jdm3cmilqlu457ascusaajj6` FOREIGN KEY (`comtype_id`) REFERENCES `comtype` (`id`);

--
-- Constraints der Tabelle `segment`
--
ALTER TABLE `segment`
  ADD CONSTRAINT `FKbths1h43ma20lgcybh4f0qhao` FOREIGN KEY (`messagetype_id`) REFERENCES `messagetype` (`id`);

--
-- Constraints der Tabelle `segment_fields`
--
ALTER TABLE `segment_fields`
  ADD CONSTRAINT `FKnam3oyb1rgym59apos58r7etu` FOREIGN KEY (`fields_id`) REFERENCES `field` (`id`),
  ADD CONSTRAINT `FKtbk0d1wxhflq24k1nt2qy2bxb` FOREIGN KEY (`segments_id`) REFERENCES `segment` (`id`);

--
-- Constraints der Tabelle `software_hardwares`
--
ALTER TABLE `software_hardwares`
  ADD CONSTRAINT `FKdiun0kxhiqdf602whg8k46009` FOREIGN KEY (`softwares_id`) REFERENCES `software` (`id`),
  ADD CONSTRAINT `FKm04jb0pq9i2yq0qv97t4y827k` FOREIGN KEY (`hardwares_id`) REFERENCES `hardware` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
