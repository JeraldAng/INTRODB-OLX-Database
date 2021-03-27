-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: olxdb
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminid` varchar(45) NOT NULL,
  `useradmin` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('ADMOLX001','choco','late');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisments`
--

DROP TABLE IF EXISTS `advertisments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisments` (
  `idadvertisments` varchar(45) NOT NULL,
  `product` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `cond` varchar(45) DEFAULT NULL,
  `price` decimal(60,2) DEFAULT NULL,
  `userid` varchar(45) DEFAULT NULL,
  `sold` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `datesold` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idadvertisments`),
  KEY `olxusers_idx` (`userid`),
  CONSTRAINT `olxusers` FOREIGN KEY (`userid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisments`
--

LOCK TABLES `advertisments` WRITE;
/*!40000 ALTER TABLE `advertisments` DISABLE KEYS */;
INSERT INTO `advertisments` VALUES ('ADOLX001','cherry movi',1,'rusty but working well',127.20,'OLX001','yes','Cellphones','12-10-2018'),('ADOLX002','bracelet',2,'shiny',65.50,'OLX001','yes','Cellphones','12-09-2018'),('ADOLX003','dryer',1,'rusty but working',150.00,'OLX001','no','Accessories',NULL),('ADOLX004',NULL,0,NULL,0.00,'OLX001',NULL,NULL,NULL),('ADOLX005',NULL,0,NULL,0.00,'OLX001',NULL,NULL,NULL),('ADOLX006','PS3',1,'brand new',75.20,'OLX002','no','Game Consoles',NULL),('ADOLX007','chihuahua',2,'aged 3',50.00,'OLX002','no','Pets',NULL),('ADOLX008','PSP',1,'Working',107.20,'OLX002','yes','Game Consoles','12-10-2018'),('ADOLX009','Iphone 9',1,'Working',107.20,'OLX002','yes','Cellphones','12-10-2018'),('ADOLX010','Iphone 10',1,NULL,250.20,'OLX002','yes','Cellphones','12-10-2018'),('ADOLX011','Cherry hugger',1,'rusty',120.72,'OLX003','yes','Cellphones','12-10-2018'),('ADOLX012',NULL,0,NULL,0.00,'OLX003',NULL,NULL,NULL),('ADOLX013',NULL,0,NULL,0.00,'OLX003',NULL,NULL,NULL),('ADOLX014',NULL,0,NULL,0.00,'OLX003',NULL,NULL,NULL),('ADOLX015',NULL,0,NULL,0.00,'OLX003',NULL,NULL,NULL);
/*!40000 ALTER TABLE `advertisments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chathistory`
--

DROP TABLE IF EXISTS `chathistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chathistory` (
  `chat1userid` varchar(45) DEFAULT NULL,
  `chat2userid` varchar(45) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  KEY `olxuse1_idx` (`chat1userid`),
  KEY `olxuse2_idx` (`chat2userid`),
  CONSTRAINT `olxuse1` FOREIGN KEY (`chat1userid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `olxuse2` FOREIGN KEY (`chat2userid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chathistory`
--

LOCK TABLES `chathistory` WRITE;
/*!40000 ALTER TABLE `chathistory` DISABLE KEYS */;
INSERT INTO `chathistory` VALUES ('OLX001','OLX003','red: hello'),('OLX003','OLX001','blue: hi');
/*!40000 ALTER TABLE `chathistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `era`
--

DROP TABLE IF EXISTS `era`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `era` (
  `chatid` varchar(45) NOT NULL,
  `msg` varchar(45) DEFAULT NULL,
  `usercolid` varchar(45) NOT NULL,
  PRIMARY KEY (`chatid`),
  KEY `olxuser_idx` (`usercolid`),
  CONSTRAINT `olxuser` FOREIGN KEY (`usercolid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `era`
--

LOCK TABLES `era` WRITE;
/*!40000 ALTER TABLE `era` DISABLE KEYS */;
/*!40000 ALTER TABLE `era` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthlytransact`
--

DROP TABLE IF EXISTS `monthlytransact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthlytransact` (
  `Category` varchar(45) DEFAULT NULL,
  `M1` int(11) DEFAULT '0',
  `M2` int(11) DEFAULT '0',
  `M3` int(11) DEFAULT '0',
  `M4` int(11) DEFAULT '0',
  `M5` int(11) DEFAULT '0',
  `M6` int(11) DEFAULT '0',
  `M7` int(11) DEFAULT '0',
  `M8` int(11) DEFAULT '0',
  `M9` int(11) DEFAULT '0',
  `M10` int(11) DEFAULT '0',
  `M11` int(11) DEFAULT '0',
  `M12` int(11) DEFAULT '0',
  `Total` varchar(45) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthlytransact`
--

LOCK TABLES `monthlytransact` WRITE;
/*!40000 ALTER TABLE `monthlytransact` DISABLE KEYS */;
INSERT INTO `monthlytransact` VALUES ('Cellphones',0,0,0,0,0,0,0,0,0,0,0,4,'4'),('Game Consoles',0,0,0,0,0,0,0,0,0,0,0,1,'1'),('Furniture',0,0,0,0,0,0,0,0,0,0,0,0,'0'),('Pets',0,0,0,0,0,0,0,0,0,0,0,0,'0'),('Tools',0,0,0,0,0,0,0,0,0,0,0,0,'0'),('Accessories',0,0,0,0,0,0,0,0,0,0,0,0,'0'),('Others',0,0,0,0,0,0,0,0,0,0,0,0,'0');
/*!40000 ALTER TABLE `monthlytransact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_table`
--

DROP TABLE IF EXISTS `new_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_table` (
  `decimaldi` varchar(45) NOT NULL,
  `dec` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`decimaldi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_table`
--

LOCK TABLES `new_table` WRITE;
/*!40000 ALTER TABLE `new_table` DISABLE KEYS */;
INSERT INTO `new_table` VALUES ('e01',23.20),('e55',23.70),('e556',0.00);
/*!40000 ALTER TABLE `new_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `olxuser`
--

DROP TABLE IF EXISTS `olxuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `olxuser` (
  `olxuserid` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `mobileno` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `olxgold` decimal(60,2) DEFAULT NULL,
  `itemsbought` varchar(300) DEFAULT NULL,
  `itemssold` varchar(300) DEFAULT NULL,
  `transactnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`olxuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `olxuser`
--

LOCK TABLES `olxuser` WRITE;
/*!40000 ALTER TABLE `olxuser` DISABLE KEYS */;
INSERT INTO `olxuser` VALUES ('OLX001','red','M','asd','01-01-2000',18,'540','Manila','red@gmail.com',552.28,'Cherry hugger, Iphone 9, Iphone 10, PSP','cherry movi',8),('OLX002','yellow','F','hug','08-02-1998',20,'875','Las Pinas','yell@gmail.com',321.70,'cherry movi, bracelet, cherry movi','Iphone 9, Iphone 10, PSP',6),('OLX003','blue','M','jug','07-04-1995',23,'5678','Pasig','blue@gmail.com',903.60,NULL,'Cherry hugger',2);
/*!40000 ALTER TABLE `olxuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promoads`
--

DROP TABLE IF EXISTS `promoads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promoads` (
  `Packagename` varchar(45) DEFAULT NULL,
  `slotnum` int(11) DEFAULT NULL,
  `discount` varchar(45) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promoads`
--

LOCK TABLES `promoads` WRITE;
/*!40000 ALTER TABLE `promoads` DISABLE KEYS */;
INSERT INTO `promoads` VALUES ('Bronze Package',10,'7.5%',185.00),('Silver Package',20,'10%',360.00),('Gold Package',50,'12.5%',875.00),('Diamond Package',100,'7.5%',1700.00);
/*!40000 ALTER TABLE `promoads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `caseid` varchar(45) NOT NULL,
  `violatorid` varchar(45) DEFAULT NULL,
  `reporterid` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `violation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`caseid`),
  KEY `viol_idx` (`violatorid`),
  KEY `reporter_idx` (`reporterid`),
  CONSTRAINT `reporter` FOREIGN KEY (`reporterid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `viol` FOREIGN KEY (`violatorid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES ('COLX001','OLX003','OLX001','540','scam'),('COLX002','OLX003','OLX001','540','harrassment'),('COLX004','OLX003','OLX001','540','customer abuse'),('COLX009','OLX001','OLX003','5678','phishing'),('COLX013','OLX003','OLX001','540','cheating');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `advid` varchar(45) DEFAULT NULL,
  `buyerid` varchar(45) DEFAULT NULL,
  `sellerid` varchar(45) DEFAULT NULL,
  `accepted` varchar(45) DEFAULT NULL,
  KEY `adv_idx` (`advid`),
  KEY `userv_idx` (`buyerid`),
  KEY `users_idx` (`sellerid`),
  CONSTRAINT `adv` FOREIGN KEY (`advid`) REFERENCES `advertisments` (`idadvertisments`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `users` FOREIGN KEY (`sellerid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userv` FOREIGN KEY (`buyerid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES ('ADOLX001','OLX003','OLX001','no'),('ADOLX001','OLX002','OLX001','yes'),('ADOLX002','OLX002','OLX001','yes'),('ADOLX002','OLX003','OLX001','no'),('ADOLX011','OLX001','OLX003','yes'),('ADOLX011','OLX002','OLX003','no'),('ADOLX008','OLX001','OLX002','yes'),('ADOLX008','OLX003','OLX002','no'),('ADOLX009','OLX001','OLX002','yes'),('ADOLX010','OLX001','OLX002','yes'),('ADOLX010','OLX003','OLX002','no'),('ADOLX003','OLX003','OLX001','pending');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transactionid` varchar(45) NOT NULL,
  `product` varchar(45) DEFAULT NULL,
  `buyerid` varchar(45) DEFAULT NULL,
  `sellerid` varchar(45) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `buyerolx_idx` (`buyerid`),
  KEY `sellerolx_idx` (`sellerid`),
  CONSTRAINT `buyerolx` FOREIGN KEY (`buyerid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sellerolx` FOREIGN KEY (`sellerid`) REFERENCES `olxuser` (`olxuserid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('TROLX001','cherry movi','OLX002','OLX001',127.20,'12-09-2018','06:00 - 07:00','Manila'),('TROLX002','bracelet','OLX002','OLX001',65.50,'12-09-2018','05:00 - 06:00','Manila'),('TROLX003','Cherry hugger','OLX001','OLX003',120.72,'12-10-2018','05:01 - 06:00','Pasig'),('TROLX007','Cherry hugger','OLX001','OLX003',120.72,'12-10-2018','03:01 - 05:00','Pasig'),('TROLX011','Iphone 9','OLX001','OLX002',107.20,'12-10-2018','00:00 - 04:05','Las Pinas'),('TROLX012','cherry movi','OLX002','OLX001',127.20,'12-10-2018','05:00 - 06:00','Manila'),('TROLX025','Iphone 10','OLX001','OLX002',250.20,'12-10-2018','03:05 - 06:29','Las Pinas'),('TROLX026','PSP','OLX001','OLX002',107.20,'12-10-2018','03:05 - 06:29','Las Pinas');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-10 23:03:50
