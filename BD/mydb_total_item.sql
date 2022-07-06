CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `total_item`
--

DROP TABLE IF EXISTS `total_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `total_item` (
  `idTotal_Item` int NOT NULL AUTO_INCREMENT,
  `valor_total` float DEFAULT NULL,
  `idPedido` int NOT NULL,
  `idItem` int NOT NULL,
  PRIMARY KEY (`idTotal_Item`,`idPedido`,`idItem`),
  KEY `fk_Total_Item_Pedido1_idx` (`idPedido`,`idItem`),
  CONSTRAINT `fk_Total_Item_Pedido1` FOREIGN KEY (`idPedido`, `idItem`) REFERENCES `pedido` (`idPedido`, `idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_item`
--

LOCK TABLES `total_item` WRITE;
/*!40000 ALTER TABLE `total_item` DISABLE KEYS */;
INSERT INTO `total_item` VALUES (1,25,1,1),(2,46,2,2),(3,14,3,6),(4,25,4,1),(5,23,5,2),(6,40,6,3),(7,450,7,1),(8,299,8,2),(9,480,9,3),(10,910,10,4),(11,350,11,5),(12,140,12,6),(13,440,13,3),(14,161,14,6),(15,70,15,6),(16,25,16,1),(17,23,17,2),(18,50,18,5),(19,50,19,1),(20,23,20,2),(21,14,21,6),(22,50,22,1),(23,23,23,2),(24,7,24,6),(25,50,25,1),(26,35,26,4),(27,7,27,6),(28,50,28,1),(29,70,29,4),(30,14,30,6),(31,63,31,6),(32,14,32,6),(33,25,33,1),(34,25,34,1),(35,23,35,2),(36,7,36,6),(37,275,37,5),(38,25,38,5),(39,25,39,1),(40,23,40,2),(41,7,41,6),(42,42,42,6),(43,25,43,1),(44,35,44,4),(45,35,45,6);
/*!40000 ALTER TABLE `total_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-23 11:01:40
