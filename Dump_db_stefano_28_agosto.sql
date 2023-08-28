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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `first_name` (`first_name`),
  UNIQUE KEY `surname` (`surname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4,'stefano.dibisceglie@gmail.com','Stefano','Di Bisceglie','1234',0),(5,'giuseppe.sorbello@gmail.com','Giuseppe','Sorbello','1234',0),(6,'riccardo.buongiovanni@gmail.com','Riccardo','Buongiovanni','1234',1);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hourly_rate` double NOT NULL,
  `daily_rate` double NOT NULL,
  `two_days_rate` double NOT NULL,
  `weekly_rate` double NOT NULL,
  `monthly_rate` double NOT NULL,
  `extra_hourly_rate` double NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,8,25,40,100,300,12,0),(3,7,20,35,90,280,10,0),(4,5,18,30,80,250,9,0),(5,4,16,28,70,220,8,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_category` int DEFAULT NULL,
  `make_and_model` varchar(50) NOT NULL,
  `boot_capacity` int DEFAULT NULL,
  `average_consumption` double DEFAULT NULL,
  `for_new_drivers` tinyint(1) NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,3,'Toyota Aygo',380,6.2,1,0),(2,NULL,'Mercedes-Benz S-Class',500,9.8,0,0),(3,5,'Ford Fiesta',300,5.2,1,0),(4,2,'Honda Civic',400,6.8,1,0),(6,NULL,'BMW 7 Series',515,10.2,0,0),(7,3,'Nissan Altima',430,7,1,0),(8,5,'Fiat 500',250,4.8,1,0),(9,2,'Audi A4',410,7.5,0,0),(10,4,'Renault Clio',320,5.9,1,0),(11,5,'Fiat DODICI',380,6.2,1,0);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recharge`
--

DROP TABLE IF EXISTS `recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recharge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `amount` double NOT NULL,
  `date_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `recharge_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recharge`
--

LOCK TABLES `recharge` WRITE;
/*!40000 ALTER TABLE `recharge` DISABLE KEYS */;
INSERT INTO `recharge` VALUES (5,8,50,'2023-07-25 09:00:00',_binary '\0'),(6,11,75,'2023-07-25 10:30:00',_binary '\0'),(7,7,100,'2023-07-25 12:15:00',_binary '\0'),(8,10,30,'2023-07-25 14:45:00',_binary '\0'),(9,6,60,'2023-07-25 16:00:00',_binary '\0');
/*!40000 ALTER TABLE `recharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `id_vehicle` int DEFAULT NULL,
  `id_admin` int DEFAULT NULL,
  `date_time_start_rental` datetime NOT NULL,
  `date_time_end_rental` datetime DEFAULT NULL,
  `type_rental` varchar(10) DEFAULT NULL,
  `deleted` bit(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user_idx` (`id_user`),
  KEY `id_vehicle_idx` (`id_vehicle`),
  KEY `id_admin_idx` (`id_admin`),
  CONSTRAINT `id_admin` FOREIGN KEY (`id_admin`) REFERENCES `administrator` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `id_vehicle` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicle` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (6,6,5,NULL,'2023-07-06 08:00:00','2023-07-06 08:00:00','Daily',_binary '\0'),(7,11,4,NULL,'2023-07-06 08:00:00','2023-07-06 08:00:00','Weekly',_binary '\0');
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_rental` int DEFAULT NULL,
  `date_time_review` datetime DEFAULT NULL,
  `text_review` text,
  `valutation` smallint NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rental` (`id_rental`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`id_rental`) REFERENCES `rental` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (6,NULL,'2023-07-26 15:00:00','Great experience, would rent again!',5,NULL),(7,NULL,'2023-07-27 10:30:00','Car was clean and well-maintained.',4,NULL),(8,NULL,'2023-07-28 14:15:00','Smooth process, friendly staff.',5,NULL),(9,NULL,'2023-07-29 11:45:00','Car had some issues, but overall okay.',3,NULL),(10,NULL,'2023-07-30 09:00:00','Excellent service, highly recommend.',5,NULL);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `piva_or_cf` varchar(16) NOT NULL,
  `id_driving_license` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `email_is_verified` tinyint(1) NOT NULL,
  `url_profile_picture` varchar(100) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `piva_or_cf_UNIQUE` (`piva_or_cf`),
  UNIQUE KEY `id_driving_license_UNIQUE` (`id_driving_license`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'765867','8765858','fggsdgf','gfdsg','fgds','dfgs',1,'fdsfsafsad',_binary '\0'),(7,'ABC12345','DL123456','john.doe@example.com','John','Doe','password123',1,'profile.jpg',_binary '\0'),(8,'XYZ67890','DL987654','jane.smith@example.com','Jane','Smith','password456',1,'profile.jpg',_binary '\0'),(9,'DEF54321','DL654321','james.brown@example.com','James','Brown','password789',1,'profile.jpg',_binary '\0'),(10,'GHI98765','DL123789','emma.jones@example.com','Emma','Jones','passwordabc',1,'profile.jpg',_binary '\0'),(11,'JKL54321','DL987321','michael.smith@example.com','Michael','Smith','passworddef',1,'profile.jpg',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(15) NOT NULL,
  `id_model` int DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `house_number` varchar(50) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `booked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_plate` (`license_plate`),
  KEY `id_model` (`id_model`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`id_model`) REFERENCES `model` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'AB123CD',1,'Italia','Lombardia','Milano','Via Roma','123',0,0),(2,'EF456GH',2,'Italia','Lazio','Roma','Via Nazionale','456',1,0),(3,'IJ789KL',3,'Germania','Lombardia','Napoli','Via Toledo','789',0,0),(4,'MN012OP',4,'Italia','Piemonte','Torino','Corso Vittorio Emanuele','101',0,0),(5,'QR345ST',NULL,'Italia','Veneto','Venezia','Piazza San Marco','234',0,0),(6,'UV678WX',6,'Italia','Sicilia','Palermo','Via Maqueda','567',0,0),(7,'YZ901AB',7,'Italia','Toscana','Firenze','Piazza del Duomo','890',0,0),(8,'CD234EF',8,'Italia','Emilia-Romagna','Bologna','Via dell\'Indipendenza','1234',0,0),(9,'GH567IJ',9,'Italia','Calabria','Reggio Calabria','Corso Garibaldi','5678',0,0);
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

-- Dump completed on 2023-08-28 10:53:36
