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
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendas` (
  `idVenda` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(17) NOT NULL,
  `valor_total` decimal(7,2) DEFAULT NULL,
  `status_pedido` varchar(45) DEFAULT NULL,
  `Data_venda` date DEFAULT NULL,
  `eco_10` float DEFAULT NULL,
  PRIMARY KEY (`idVenda`,`cpf`),
  KEY `fk_Vendas_Cadastro1_idx` (`cpf`),
  CONSTRAINT `fk_Vendas_Cadastro1` FOREIGN KEY (`cpf`) REFERENCES `cadastro` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (1,'884.538.440-38',164.35,'Pago','2022-06-18',17.3),(2,'884.538.440-38',2497.55,'Pago','2022-06-18',262.9),(3,'884.538.440-38',570.95,'Pago','2022-06-18',60.1),(4,'884.538.440-38',112.10,'Pago','2022-06-20',11.8),(5,'494.816.420-89',50.00,'Pago','2022-06-20',5),(6,'494.816.420-89',246.05,'Pago','2022-06-21',25.9),(7,'226.635.380-27',187.15,'Pago','2022-06-21',19.7),(8,'226.635.380-27',13.30,'Pago','2022-06-21',1.4),(9,'166.806.990-37',23.75,'Pago','2022-06-21',2.5),(10,'792.646.110-53',313.50,'Pago','2022-06-21',33),(11,'792.646.110-53',25.00,'Pago','2022-06-21',2.5),(12,'177.173.130-30',149.15,'Pago','2022-06-21',15.7),(13,'177.173.130-30',35.00,'Pago','2022-06-21',3.5);
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
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
