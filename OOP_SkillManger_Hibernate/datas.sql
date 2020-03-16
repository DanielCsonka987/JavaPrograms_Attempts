-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Már 03. 14:54
-- Kiszolgáló verziója: 10.1.29-MariaDB
-- PHP verzió: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `datas`
--
CREATE DATABASE IF NOT EXISTS `datas` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `datas`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `normlevels`
--

CREATE TABLE `normlevels` (
  `lvlid` int(11) NOT NULL,
  `skillid` int(11) DEFAULT NULL,
  `lvlmark` int(11) DEFAULT NULL,
  `lvlcost` int(11) DEFAULT NULL,
  `lvlbenef` binary(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `normlevels`
--

INSERT INTO `normlevels` (`lvlid`, `skillid`, `lvlmark`, `lvlcost`, `lvlbenef`) VALUES
(1, 1, 1, 400, 0x30),
(2, 1, 2, 600, 0x30),
(3, 1, 2, 800, 0x31),
(4, 2, 1, 400, 0x30),
(5, 3, 1, 340, 0x31),
(6, 4, 1, 420, 0x30);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `skillbody`
--

CREATE TABLE `skillbody` (
  `skillid` int(4) NOT NULL,
  `skillidentif` int(4) DEFAULT NULL,
  `skillattrib` int(4) DEFAULT NULL,
  `skillcostbenef` binary(1) DEFAULT NULL,
  `skillname` varchar(15) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `skillnote` varchar(30) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `skillrequir` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `skillbody`
--

INSERT INTO `skillbody` (`skillid`, `skillidentif`, `skillattrib`, `skillcostbenef`, `skillname`, `skillnote`, `skillrequir`) VALUES
(1, 41, 2, 0x30, 'nev1', 'valami', NULL),
(2, 121, 7, 0x30, 'Kovácsolás', 'mert jó dolog', NULL),
(3, 11, 6, 0x31, 'nev3', 'valamsdfsfi', 12),
(4, 71, 3, 0x30, 'nev4', 'vsdfsalami', NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `specbody`
--

CREATE TABLE `specbody` (
  `specid` int(11) NOT NULL,
  `skillid` int(11) DEFAULT NULL,
  `specrequir` int(11) DEFAULT NULL,
  `specnote` varchar(35) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `specname` varchar(15) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `specidentif` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `specbody`
--

INSERT INTO `specbody` (`specid`, `skillid`, `specrequir`, `specnote`, `specname`, `specidentif`) VALUES
(1, 1, 23, 'a new spec', 'encrypting', 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `speclevels`
--

CREATE TABLE `speclevels` (
  `lvlid` int(11) NOT NULL,
  `specid` int(11) DEFAULT NULL,
  `lvlmark` int(11) DEFAULT NULL,
  `lvlcost` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `speclevels`
--

INSERT INTO `speclevels` (`lvlid`, `specid`, `lvlmark`, `lvlcost`) VALUES
(1, 1, 1, 700);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `normlevels`
--
ALTER TABLE `normlevels`
  ADD PRIMARY KEY (`lvlid`);

--
-- A tábla indexei `skillbody`
--
ALTER TABLE `skillbody`
  ADD PRIMARY KEY (`skillid`);

--
-- A tábla indexei `specbody`
--
ALTER TABLE `specbody`
  ADD PRIMARY KEY (`specid`),
  ADD KEY `FKpn21usb4ouv1wghm077j4sje5` (`skillid`);

--
-- A tábla indexei `speclevels`
--
ALTER TABLE `speclevels`
  ADD PRIMARY KEY (`lvlid`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `normlevels`
--
ALTER TABLE `normlevels`
  MODIFY `lvlid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT a táblához `skillbody`
--
ALTER TABLE `skillbody`
  MODIFY `skillid` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT a táblához `specbody`
--
ALTER TABLE `specbody`
  MODIFY `specid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT a táblához `speclevels`
--
ALTER TABLE `speclevels`
  MODIFY `lvlid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `specbody`
--
ALTER TABLE `specbody`
  ADD CONSTRAINT `FKpn21usb4ouv1wghm077j4sje5` FOREIGN KEY (`skillid`) REFERENCES `skillbody` (`skillid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
