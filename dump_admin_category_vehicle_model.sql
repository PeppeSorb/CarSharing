-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: car_sharing
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'giada.marzi@gmail.com','Giada','Marzi','1234'),(2,'luca.galiero@gmail.com','Luca','Galiero','1234'),(3,'orazio.squinzi@gmail.com','Orazio','Squinzi','1234'),(4,'stefano.dibisceglie@gmail.com','Stefano','Di Bisceglie','1234'),(5,'giuseppe.sorbello@gmail.com','Giuseppe','Sorbello','1234');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,10,30,50,120,400,15),(2,8,25,40,100,300,12),(3,6,20,35,90,280,10),(4,5,18,30,80,250,9),(5,4,16,28,70,220,8);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,3,'Toyota Corolla',450,6.5,0),(2,1,'Mercedes-Benz S-Class',500,9.8,0),(3,5,'Ford Fiesta',300,5.2,1),(4,2,'Honda Civic',400,6.8,1),(5,4,'Volkswagen Golf',380,6.2,0),(6,1,'BMW 7 Series',515,10.2,0),(7,3,'Nissan Altima',430,7,1),(8,5,'Fiat 500',250,4.8,1),(9,2,'Audi A4',410,7.5,0),(10,4,'Renault Clio',320,5.9,1);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'AB123CD',1,'Italia','Lombardia','Milano','Via Roma','123'),(2,'EF456GH',2,'Italia','Lazio','Roma','Via Nazionale','456'),(3,'IJ789KL',3,'Italia','Campania','Napoli','Via Toledo','789'),(4,'MN012OP',4,'Italia','Piemonte','Torino','Corso Vittorio Emanuele','101'),(5,'QR345ST',5,'Italia','Veneto','Venezia','Piazza San Marco','234'),(6,'UV678WX',6,'Italia','Sicilia','Palermo','Via Maqueda','567'),(7,'YZ901AB',7,'Italia','Toscana','Firenze','Piazza del Duomo','890'),(8,'CD234EF',8,'Italia','Emilia-Romagna','Bologna','Via dell\'Indipendenza','1234'),(9,'GH567IJ',9,'Italia','Calabria','Reggio Calabria','Corso Garibaldi','5678'),(10,'KL890MN',10,'Italia','Abruzzo','Pescara','Via XX Settembre','9012');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-08 11:01:11
