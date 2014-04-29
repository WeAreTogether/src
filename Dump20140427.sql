CREATE DATABASE  IF NOT EXISTS `algo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `algo`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: algo
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `building` (
  `buildingid` int(11) NOT NULL,
  `buildingName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`buildingid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (301,'atharveda'),(302,'samveda');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campus`
--

DROP TABLE IF EXISTS `campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campus` (
  `campusid` int(11) NOT NULL,
  `campusname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`campusid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campus`
--

LOCK TABLES `campus` WRITE;
/*!40000 ALTER TABLE `campus` DISABLE KEYS */;
INSERT INTO `campus` VALUES (201,'kothrud'),(202,'alandi');
/*!40000 ALTER TABLE `campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `classrromNo` varchar(5) NOT NULL,
  `Floor` int(11) DEFAULT '5',
  `capacity` int(11) DEFAULT '60',
  `Lab` int(11) DEFAULT NULL,
  `available` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`classrromNo`),
  UNIQUE KEY `classrromNo_UNIQUE` (`classrromNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES ('501',5,30,0,1),('504',5,30,0,1),('505',5,30,0,1),('506',5,30,0,1),('511',5,30,0,1),('512',5,30,1,1),('513',5,30,1,1),('515',5,30,0,1),('516',5,30,1,1),('517',5,30,1,1),('518',5,30,1,1),('519',5,30,1,1),('520',5,30,1,1);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `course_Abrv` varchar(45) DEFAULT NULL,
  `coursename` varchar(45) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `faculty` int(11) DEFAULT NULL,
  `studentno` int(11) DEFAULT '60',
  `academicHours` int(11) DEFAULT NULL,
  PRIMARY KEY (`courseid`),
  UNIQUE KEY `courseid_UNIQUE` (`courseid`),
  UNIQUE KEY `course_Abrv_UNIQUE` (`course_Abrv`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'AM-IV','Applied Mathematics IV',3,1,50,1),(2,'AOA','Analysis of Algorithms',3,1,50,1),(3,'COA','Computer Organization and Architecture	',3,1,50,1),(4,'DBMS','Database Management systems',3,1,50,1),(5,'TCS','Theory of Computer Science',3,1,50,1),(6,'CG','Computer Graphics',3,1,50,1),(7,'SPCC','System Programming Compiler Construction',5,1,50,1),(8,'OOSE','Object Oriented Software Engineering',5,1,50,1),(9,'AMP','Advance Microprocessor',5,1,50,1),(10,'DWM','Data Warehouse and Data Mining',5,1,50,1),(11,'ACN','Advanced Computer Network',5,1,50,1),(12,'DC','Distributed Computing',7,1,50,1),(13,'MSD','Multimedia System and Design',7,1,50,1),(14,'HCI','Human Computer Interaction',7,1,50,1),(15,'SA','Software Architecture',7,1,50,1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `facultyid` int(11) NOT NULL,
  `facultyname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`facultyid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'comp'),(2,'IT'),(3,'mechanical'),(4,'civil'),(5,'entc');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturer` (
  `lecid` int(11) NOT NULL AUTO_INCREMENT,
  `Acronym_In_TT` varchar(45) DEFAULT NULL,
  `lecname` varchar(90) DEFAULT NULL,
  `AllocatedCourse` int(11) DEFAULT NULL,
  `year_of_joining` int(11) DEFAULT NULL,
  `specialization` varchar(45) DEFAULT NULL,
  `experience` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`lecid`),
  UNIQUE KEY `lecid_UNIQUE` (`lecid`),
  UNIQUE KEY `Acronym_In_TT_UNIQUE` (`Acronym_In_TT`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES (1,'/MZ','Prof. M. Zende',1,2009,'database','2 yrs'),(2,'/ADG','Prof. Ashwini Gudewar	',2,2009,'testing','2 yrs'),(3,'/PNY','Prof. Poonam Yadav',3,2009,'java','2 yrs'),(4,'/SDB','Prof. Smita Bharne',4,2009,'networking','2 yrs'),(5,'/VMM','Prof. Vanita Mane',5,2009,'uml','2 yrs'),(6,'/KVK','Prof. Kriti Karanam',6,2009,'maths','2 yrs'),(7,'/STB','Prof. S. T. Bhangale',1,2009,'social','2 yrs'),(8,'/ENS','Prof. Ekta Sarda',2,2009,'networking','2 yrs'),(9,'/RAS','Prof.  Rajashree Shedge',4,2009,'database','2 yrs'),(10,'/SVB','Prof. Smita Bhoir',5,2009,'electronics','2 yrs'),(11,'/NS','Prof. Nazia S.',6,2009,'artificial intelligence','2 yrs'),(12,'/SPS','Prof. Shreya Solanki',7,2009,'database','2 yrs'),(13,'/SAA','Prof. Sheetal Ahir',8,2009,'java','2 yrs'),(14,'/SPG','Prof. Snehal Gaikwad',9,2009,'.NET','2 yrs'),(15,'/THG','Prof. Tushar Ghorpade',10,2009,'networking','2 yrs'),(16,'/VAJ','Prof. Vimla Jethani',8,2009,'security','2 yrs'),(17,'/SKS','Prof. Savita Sawant',9,2009,'wireless','2 yrs'),(18,'/PJB','Prof. Pramod Bide',10,2009,'security','2 yrs'),(19,'/NRM','Prof. Nilesh Marathe',11,2009,'microprocessor','2 yrs'),(20,'/AVV','Prof. A. V. Vidhate',12,2009,'embedded','2 yrs'),(21,'/PAP','Prof. Preeti Patil',13,2009,'networking','2 yrs'),(22,'/JKJ','Prof. Jyoti Joshi',14,2009,'database','2 yrs'),(23,'/TPP','Prof. Trupti Patil',15,2009,NULL,'2 yrs'),(24,'/HAS','Prof. Harsha Saxena',12,2009,'networking','2 yrs'),(25,'/jp','Asst. professor jai prakash',1,2009,'maths','Fresher');
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `loginid` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `auth_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loginid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'disha','disha','admin'),(2,'jaya','jaya','lect'),(3,'jai','jai','asst lect');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-27 22:19:02
