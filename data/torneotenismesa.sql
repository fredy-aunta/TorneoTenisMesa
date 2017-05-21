-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: torneotenismesa
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.16-MariaDB

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
-- Table structure for table `estructura`
--

DROP TABLE IF EXISTS `estructura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estructura` (
  `idEstructura` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstructura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estructura`
--

LOCK TABLES `estructura` WRITE;
/*!40000 ALTER TABLE `estructura` DISABLE KEYS */;
INSERT INTO `estructura` VALUES (1,'Arbol'),(2,'Cuadros');
/*!40000 ALTER TABLE `estructura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `idPartido` int(11) NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime NOT NULL,
  `idTorneo` int(11) NOT NULL,
  `idPartidoTorneo` int(11) NOT NULL,
  PRIMARY KEY (`idPartido`),
  KEY `fkPartidoTorneo_idx` (`idTorneo`),
  CONSTRAINT `fkPartidoTorneo` FOREIGN KEY (`idTorneo`) REFERENCES `torneo` (`idTorneo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (52,'2017-05-22 11:17:00',41,1),(53,'2017-05-22 11:47:00',41,2),(54,'2017-05-22 12:17:00',41,3),(55,'2017-05-22 12:47:00',41,4),(56,'2017-05-22 01:17:00',41,5),(57,'2017-05-22 01:47:00',41,6),(58,'2017-05-22 02:17:00',41,7),(59,'2017-05-22 11:23:00',42,1),(60,'2017-05-22 11:53:00',42,2),(61,'2017-05-22 12:23:00',42,3),(62,'2017-05-22 12:53:00',42,4),(63,'2017-05-22 01:23:00',42,5),(64,'2017-05-22 01:53:00',42,6),(65,'2017-05-22 02:23:00',42,7),(66,'2017-05-22 11:35:00',43,1),(67,'2017-05-22 12:05:00',43,2),(68,'2017-05-22 12:35:00',43,3),(69,'2017-05-22 01:05:00',43,4),(70,'2017-05-22 01:35:00',43,5),(71,'2017-05-22 02:05:00',43,6),(72,'2017-05-22 02:35:00',43,7);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `idTorneo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idEstructura` int(11) NOT NULL,
  `cantidadJugadores` int(11) NOT NULL,
  `cantidadMesas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTorneo`),
  KEY `fkEstructuraTorneo_idx` (`idEstructura`),
  CONSTRAINT `fkEstructuraTorneo` FOREIGN KEY (`idEstructura`) REFERENCES `estructura` (`idEstructura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
INSERT INTO `torneo` VALUES (41,'prueba 1',1,8,1),(42,'prueba 2',1,8,1),(43,'prueba 3',1,8,1);
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(64) NOT NULL,
  `apellido` varchar(64) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `tipo` enum('Administrador','Jugador','Arbitro','Apostador') NOT NULL,
  `telefono` varchar(64) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','12345678',1,'admin','admin','Administrador','1234565699','2000-04-12'),(2,'Fredy AA','Aunta','12345',1,'Fredy A Aunta M','12345','Administrador','1234567','2000-04-12'),(3,'Fredy AA','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(4,'','','123456789',3,'sdfgdfgd','123456789','Administrador','76543','2000-04-12'),(5,'','','45678',3,'dfghnjm','45678','Administrador','2123456','2000-04-12'),(6,'fghjk','vbnmk,','45678',4,'fghjkl','45678','Apostador','876543','2000-04-12'),(7,'rtyujdd','vbn','gbn',1,'fff','fff','Jugador','1234567','2000-04-12'),(8,'Arbitro S','Arbitro A','123456',1,'arb','123456','Arbitro','1234567','2000-04-12'),(9,'a','a','1',1,'11','11','Apostador','1','2000-04-12'),(10,'aa','aa','11',1,'qq','qq','Jugador','11','2000-04-12'),(11,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(12,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(13,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(14,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(15,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(16,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(17,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(18,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(19,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(20,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(21,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(22,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(23,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(24,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(25,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(26,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(27,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(28,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(29,'Fredy A','M','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(30,'Fredy A','F','123',1,'Fredy A Aunta M','123','Jugador','3163980851','2000-04-12'),(31,'Arbitro Sa','Arbitro A','123456',1,'arb','123456','Arbitro','1234567','2000-04-12'),(32,'rty','rtyu','12345',1,'test_d','123456','Jugador','45674','2000-04-12');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariopartido`
--

DROP TABLE IF EXISTS `usuariopartido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariopartido` (
  `idUsuarioPartido` int(11) NOT NULL AUTO_INCREMENT,
  `idPartido` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `resultado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuarioPartido`),
  KEY `fk_Partido_has_Usuario_Partido1_idx` (`idPartido`),
  CONSTRAINT `fk_Partido_has_Usuario_Partido1` FOREIGN KEY (`idPartido`) REFERENCES `partido` (`idPartido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariopartido`
--

LOCK TABLES `usuariopartido` WRITE;
/*!40000 ALTER TABLE `usuariopartido` DISABLE KEYS */;
INSERT INTO `usuariopartido` VALUES (1,52,32,NULL),(2,52,30,NULL),(3,52,29,NULL),(4,53,29,NULL),(5,53,28,NULL),(6,53,28,NULL),(7,54,27,NULL),(8,54,26,NULL),(9,54,28,NULL),(10,55,25,NULL),(11,55,24,NULL),(12,55,30,NULL),(13,56,0,NULL),(14,56,0,NULL),(15,56,32,NULL),(16,57,0,NULL),(17,57,0,NULL),(18,57,27,NULL),(19,58,0,NULL),(20,58,0,NULL),(21,58,32,NULL),(22,59,32,NULL),(23,59,30,NULL),(24,59,29,NULL),(25,60,29,NULL),(26,60,28,NULL),(27,60,32,NULL),(28,61,27,NULL),(29,61,26,NULL),(30,61,28,NULL),(31,62,25,NULL),(32,62,24,NULL),(33,62,27,NULL),(34,63,0,NULL),(35,63,0,NULL),(36,63,29,NULL),(37,64,0,NULL),(38,64,0,NULL),(39,64,24,NULL),(40,65,0,NULL),(41,65,0,NULL),(42,65,29,NULL),(43,66,32,NULL),(44,66,30,NULL),(45,66,31,NULL),(46,67,29,NULL),(47,67,28,NULL),(48,67,31,NULL),(49,68,27,NULL),(50,68,26,NULL),(51,68,31,NULL),(52,69,25,NULL),(53,69,24,NULL),(54,69,31,NULL),(55,70,0,NULL),(56,70,0,NULL),(57,70,31,NULL),(58,71,0,NULL),(59,71,0,NULL),(60,71,31,NULL),(61,72,0,NULL),(62,72,0,NULL),(63,72,31,NULL);
/*!40000 ALTER TABLE `usuariopartido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-21 11:27:47
