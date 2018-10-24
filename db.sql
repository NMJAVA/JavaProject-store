CREATE DATABASE  IF NOT EXISTS `store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `store`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: store
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,2,1),(2,3,0),(3,4,0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,1,0);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inventory` (
  `product_sku` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('100',0),('101',0),('102',20),('200',0),('201',0),('202',0),('300',0),('301',20),('302',0),('400',20),('401',20),('402',20),('500',0),('501',0),('502',0),('600',17),('601',16),('602',20),('700',20),('701',20),('702',20),('800',20),('801',20),('802',20),('900',20),('901',20),('902',20);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `members` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Niv','Noiman','Rishon Lezzion, , 0','0524011331','niv@gmail.com','GABmZnsfMq5sN12NKnbKhBjpeOSDOpzVzERkOdNwkoo='),(2,'luke','skywalker','nabu, , 0','0524011331','luke@gmail.com','GABmZnsfMq5sN12NKnbKhBjpeOSDOpzVzERkOdNwkoo='),(3,'dave','man','Holon, zalman, 2','0547574412','dave@store.com','wVz2T+3RlmM1F78VcdpSQdf1kT51ZQjjf9ywaKGRxGU='),(4,'lee','wu','Tel aviv, Jab, 12','05964165422','lee@gmail.com','wVz2T+3RlmM1F78VcdpSQdf1kT51ZQjjf9ywaKGRxGU=');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `product_sku` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'201','2018-10-21 00:00:00',1,1,20),(2,'101','2018-10-21 00:00:00',1,1,20),(3,'200','2018-10-23 00:00:00',1,2,20),(4,'100','2018-10-23 00:00:00',1,2,20),(5,'300','2018-10-23 00:00:00',1,2,20),(6,'500','2018-10-23 00:00:00',1,2,20),(7,'202','2018-10-23 00:00:00',1,2,20),(8,'302','2018-10-23 00:00:00',1,2,20),(9,'501','2018-10-23 00:00:00',1,2,3),(10,'502','2018-10-23 00:00:00',1,2,4),(11,'600','2018-10-23 00:00:00',1,2,3),(12,'601','2018-10-23 00:00:00',1,2,4),(13,'201','2018-10-23 00:00:00',1,2,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  `price` float DEFAULT '0',
  PRIMARY KEY (`ID`,`sku`),
  UNIQUE KEY `SKU_UNIQUE` (`sku`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'100','Jeans unisex','trousers','M',100),(2,'101','Jeans male','trousers','L',50),(3,'102','Jeans female','trousers','S',40),(4,'200','Pants unisex','trousers','M',100),(5,'201','Pants male','trousers','L',50),(6,'202','Pants female','trousers','S',40),(7,'300','Bermuda unisex','trousers','M',100),(8,'301','Bermuda male','trousers','L',50),(9,'302','Bermuda female','trousers','S',40),(10,'400','Undershirt unisex','shirts','M',500),(11,'401','Undershirt male','shirts','L',500),(12,'402','Undershirt female','shirts','S',500),(13,'500','T-shirt unisex','shirts','M',500),(14,'501','T-shirt male','shirts','L',500),(15,'502','T-shirt female','shirts','S',500),(16,'600','Shoe unisex','footwear','M',500),(17,'601','Shoe male','footwear','L',500),(18,'602','Shoe female','footwear','S',500),(19,'700','Flip flops unisex','footwear','M',500),(20,'701','Flip flops male','footwear','L',500),(21,'702','Flip flops female','footwear','S',500),(22,'800','Underpants unisex','lingerie','M',500),(23,'801','Underpants male','lingerie','L',500),(24,'802','Underpants female','lingerie','S',500),(25,'900','Socks unisex','lingerie','M',500),(26,'901','Socks male','lingerie','L',500),(27,'902','Socks female','lingerie','S',500);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'store'
--

--
-- Dumping routines for database 'store'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-24 21:31:55
