CREATE DATABASE  IF NOT EXISTS `shopping_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopping_app`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: shopping_app
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` char(2) NOT NULL,
  `zip_code` varchar(10) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `street_zip` (`street`,`zip_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'34 Erwin Park Rd','Montclair','NJ','07042'),(2,'1329 Carroll Ave','Los Angeles','CA','90026'),(3,'123 street','Midland','TX','12349'),(4,'11111 street','city','ST','00000'),(5,'1111 street','city','ST','00000');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `full_name` varchar(255) GENERATED ALWAYS AS (concat(`first_name`,_utf8mb4' ',`last_name`)) VIRTUAL,
  `email` varchar(50) NOT NULL,
  `phone_number` char(16) NOT NULL,
  `address_id` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `address_id_idx` (`address_id`),
  CONSTRAINT `customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `password`, `first_name`, `last_name`, `email`, `phone_number`, `address_id`) VALUES ('dude420','Surfer-Br0','Oscar','Brown','oscar.brown@gmail.com','908-445-0424',1),('flowergirl1','lily_of_the_valley99','Prudence','Halliwell','prudence.halliwell@gmail.com','830-775-9185',2),('KellyFlowers','password12E%','Kelly','Flowers','kelly.flowers@gmail.com','704-888-0699',3),('violet99','Hello!55','Jane','Brown','laura.brown@gmail.com','732-367-5260',1),('violetrose','Javahome$2','Jane','Doe','jane.doe@gmail.com','000-000-0000',5);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(50) NOT NULL,
  `order_date` datetime NOT NULL,
  `sub_total` double DEFAULT '0',
  `sales_tax` double NOT NULL,
  `tax` double GENERATED ALWAYS AS ((`sub_total` * `sales_tax`)) VIRTUAL,
  `total` double GENERATED ALWAYS AS ((`sub_total` + `tax`)) VIRTUAL,
  PRIMARY KEY (`invoice_id`),
  UNIQUE KEY `invoice_id_UNIQUE` (`invoice_id`),
  KEY `customer_invoice` (`customer_id`),
  CONSTRAINT `customer_invoice` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`invoice_id`, `customer_id`, `order_date`, `sub_total`, `sales_tax`) VALUES (1,'violet99','2020-09-23 17:58:30',10,0.066),(2,'dude420','2020-09-15 17:58:30',20,0.066),(3,'violet99','2020-09-15 17:58:30',5,0.066),(4,'dude420','2020-09-23 17:58:30',20,0.066);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_product`
--

DROP TABLE IF EXISTS `invoice_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_product` (
  `invoice_product_id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `can_return` tinyint(1) DEFAULT '1',
  `requested_return` tinyint(1) DEFAULT '0',
  `amount_requested_return` int DEFAULT '0',
  PRIMARY KEY (`invoice_product_id`),
  UNIQUE KEY `invoice_product_id_UNIQUE` (`invoice_product_id`),
  KEY `product_inx_idx` (`product_id`),
  KEY `invoice_inx` (`invoice_id`),
  CONSTRAINT `invoice_inx` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`),
  CONSTRAINT `product_inx` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_product`
--

LOCK TABLES `invoice_product` WRITE;
/*!40000 ALTER TABLE `invoice_product` DISABLE KEYS */;
INSERT INTO `invoice_product` VALUES (1,1,'Je1',1,1,0,0),(2,2,'Ja1',1,0,0,0),(3,3,'Sh1',1,0,0,0),(4,4,'Je1',2,1,0,0),(5,4,'Ja1',0,0,0,0);
/*!40000 ALTER TABLE `invoice_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `invoice_product_AFTER_INSERT` AFTER INSERT ON `invoice_product` FOR EACH ROW BEGIN
	IF NEW.quantity > 0 then
		UPDATE product
			SET stock = stock - NEW.quantity
			WHERE product_id = NEW.product_id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT 'N/A',
  `price` double NOT NULL,
  `stock` int NOT NULL DEFAULT '1',
  `in_stock` tinyint(1) GENERATED ALWAYS AS ((`stock` > 0)) VIRTUAL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`product_id`, `name`, `description`, `price`, `stock`) VALUES ('Ja1','Jacket','Basic Jacket',20,4),('Je1','Jeans','Basic pair of Jeans',10,7),('Sh1','Shirt','Basic Shirt',5,14);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_tax_rates`
--

DROP TABLE IF EXISTS `sales_tax_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_tax_rates` (
  `sales_tax_rates_id` int NOT NULL AUTO_INCREMENT,
  `state` char(2) NOT NULL,
  `rate` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`sales_tax_rates_id`),
  UNIQUE KEY `sales_tax_rates_id_UNIQUE` (`sales_tax_rates_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_tax_rates`
--

LOCK TABLES `sales_tax_rates` WRITE;
/*!40000 ALTER TABLE `sales_tax_rates` DISABLE KEYS */;
INSERT INTO `sales_tax_rates` VALUES (1,'AL',0.0922),(2,'AK',0.0176),(3,'AR',0.0947),(4,'AZ',0.084),(5,'CA',0.0866),(6,'CO',0.0765),(7,'CT',0.0635),(8,'DC',0.06),(9,'DE',0),(10,'FL',0.0705),(11,'GA',0.0731),(12,'HI',0.0444),(13,'IA',0.0694),(14,'ID',0.0603),(15,'IL',0.0908),(16,'IN',0.07),(17,'KS',0.0868),(18,'KY',0.06),(19,'LA',0.0952),(20,'MA',0.0625),(21,'ME',0.055),(22,'MD',0.06),(23,'MI',0.06),(24,'MN',0.0746),(25,'MO',0.0818),(26,'MS',0.0707),(27,'MT',0),(28,'NC',0.0697),(29,'ND',0.0686),(30,'NE',0.0693),(31,'NH',0),(32,'NJ',0.066),(33,'NM',0.0882),(34,'NV',0.0832),(35,'NY',0.0852),(36,'OH',0.0717),(37,'OK',0.0894),(38,'OR',0),(39,'PA',0.0634),(40,'RI',0.07),(41,'SC',0.0746),(42,'SD',0.064),(43,'TN',0.0953),(44,'TX',0.0819),(45,'UT',0.0718),(46,'VA',0.0565),(47,'VT',0.0622),(48,'WA',0.0921),(49,'WI',0.0546),(50,'WV',0.0641),(51,'WY',0.0534);
/*!40000 ALTER TABLE `sales_tax_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'shopping_app'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `event_return_expire` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `event_return_expire` ON SCHEDULE EVERY 1 DAY STARTS '2020-10-02 20:55:49' ON COMPLETION PRESERVE ENABLE DO call return_expire() */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'shopping_app'
--
/*!50003 DROP PROCEDURE IF EXISTS `ok_return` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ok_return`(IN invo_pro_id int)
BEGIN
	select product_id into @pro from invoice_product where invoice_product_id = invo_pro_id and requested_return = 1;
    select amount_requested_return into @amount from invoice_product where invoice_product_id = invo_pro_id and requested_return = 1;
    select invoice_id into @invo from invoice_product where invoice_product_id = invo_pro_id and requested_return = 1;
    
	update invoice_product set quantity = quantity - @amount where invoice_product_id = invo_pro_id and requested_return = 1;
    update invoice_product set can_return = 0 where invoice_product_id = invo_pro_id and requested_return = 1 and quantity = 0;
    update product set stock = stock + @amount where product_id = @pro;
    
    update invoice_product set requested_return = 0, amount_requested_return = 0 where invoice_product_id = invo_pro_id and requested_return = 1;
    
    call set_sub_total(@invo);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `return_expire` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `return_expire`()
BEGIN
	update invoice_product
	set can_return = 0 
    where invoice_id in (select invoice_id from invoice where invoice_id in (SELECT invoice_id FROM invoice WHERE datediff(now(), order_date) > 15));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_sub_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sub_total`(IN invo_id int)
BEGIN
	update invoice,
    (SELECT sum(price * quantity) as cost from product left join invoice_product on invoice_product.product_id = product.product_id where invoice_id = invo_id) as items
	set sub_total = items.cost where invoice_id = invo_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_tax_rate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_tax_rate`(IN user_id varchar(50))
BEGIN
	update invoice,
    (select rate from sales_tax_rates where state in (select state from address where address_id in (select address_id from customer where customer_id = user_id))) as tax_rate
	set sales_tax = tax_rate.rate where customer_id = user_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-03 20:14:20
