-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: demo_project
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `payload` varchar(255) DEFAULT NULL,
  `no_resi` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2023-03-30 10:01:48.640119','maker',NULL,'Tax-20230330100148','CHECKER'),(2,'2023-03-30 10:03:23.804978','maker','{\"id\":6,\"resi\":\"Tax-20230330100323\",\"tanggal\":\"2023-03-30T10:03:23.714676\",\"status\":\"CHECKER\",\"createdBy\":\"maker\"}','Tax-20230330100323','CHECKER'),(3,'2023-03-30 10:11:10.171492','null','{\"id\":7,\"resi\":\"Tax-20230330101110\",\"status\":\"CHECKER\"}','Tax-20230330101110','CHECKER'),(4,'2023-03-30 10:17:45.536830','john_checker','{\"id\":7,\"resi\":\"Tax-20230330101110\",\"status\":\"APPROVE\",\"createdBy\":\"john_checker\"}','Tax-20230330101110','APPROVE');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_delete` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,_binary '\0','ADMIN'),(2,_binary '\0','MAKER'),(3,_binary '\0','CHECKER'),(4,_binary '\0','APPROVER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resi` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_update` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES (1,'Tax-20230329224155','APPROVE','2023-03-29 22:41:55.200426',NULL,NULL),(2,'Tax-20230329230501','CHECKER','2023-03-29 23:05:01.562742',NULL,NULL),(3,'Tax-20230330093406','CHECKER','2023-03-30 09:34:06.330670',NULL,NULL),(4,'Tax-20230330094716','CHECKER','2023-03-30 09:47:16.408783','maker',NULL),(5,'Tax-20230330100148','CHECKER','2023-03-30 10:01:48.493111','maker',NULL),(6,'Tax-20230330100323','CHECKER','2023-03-30 10:03:23.714676','maker',NULL),(7,'Tax-20230330101110','APPROVE','2023-03-30 10:11:10.036295','john_checker','2023-03-30 10:17:45.447886');
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `pass` varchar(255) NOT NULL,
  `role_id` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@mail.com',_binary '\0','$2a$10$frq/VE1UenADZqXu3NmJaOy5Txd5wLlS.tWZO9jCW6FzdAw1XXzWa',1,'john_admin'),(2,'checker@mail.com',_binary '\0','$2a$10$hFMd78v5AaksPIWIZvP5EO3RQW/XBDWUIziOZAeddWqYB6ahz3h1m',3,'john_checker'),(3,'maker@mail.com',_binary '\0','$2a$10$3POL4OoHPK.2EbFafQJdbe8FRHt1LNZd/.3hnZJMqXPHrk0g23uGS',2,'john_maker'),(4,'approver@mail.com',_binary '\0','$2a$10$s5ulY64RiYmhEvjV1dfE7e.eo8oAbcOVNQxPI2es6ofSGhCgGyGnO',4,'John_approver');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-30 11:12:05
