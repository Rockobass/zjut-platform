-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aha_zjut
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academy`
--

DROP TABLE IF EXISTS `academy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `academy` (
  `academy_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `academy_name` varchar(32) DEFAULT NULL COMMENT '学院名',
  UNIQUE KEY `academy_academy_id_uindex` (`academy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学院表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academy`
--

LOCK TABLES `academy` WRITE;
/*!40000 ALTER TABLE `academy` DISABLE KEYS */;
INSERT INTO `academy` (`academy_id`, `academy_name`) VALUES (1,'计算机学院'),(2,'马克思主义学院');
/*!40000 ALTER TABLE `academy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_academy_info`
--

DROP TABLE IF EXISTS `admin_academy_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin_academy_info` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '登陆账号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '绑定手机号',
  `academy_name` varchar(32) DEFAULT NULL COMMENT '所属学院',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `admin_academy_info_user_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='校级管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_academy_info`
--

LOCK TABLES `admin_academy_info` WRITE;
/*!40000 ALTER TABLE `admin_academy_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_academy_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_school_info`
--

DROP TABLE IF EXISTS `admin_school_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin_school_info` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '登陆账号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '绑定手机号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `admin_school_info_user_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='校级管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_school_info`
--

LOCK TABLES `admin_school_info` WRITE;
/*!40000 ALTER TABLE `admin_school_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_school_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2021-08-05 05:57:57',0,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `judge_info`
--

DROP TABLE IF EXISTS `judge_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `judge_info` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(64) DEFAULT NULL COMMENT '登录账号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '绑定手机号',
  `judge_name` varchar(32) DEFAULT NULL COMMENT '评委姓名',
  `academy_name` varchar(32) DEFAULT NULL COMMENT '学院名',
  `group` tinyint(1) DEFAULT NULL COMMENT '0为本科 1为研究生',
  `expire_time` datetime DEFAULT NULL COMMENT '账号过期时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `judge_info_user_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评委信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `judge_info`
--

LOCK TABLES `judge_info` WRITE;
/*!40000 ALTER TABLE `judge_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `judge_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `major` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `major_name` varchar(32) DEFAULT NULL,
  `academy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`major_id`),
  UNIQUE KEY `major_major_id_uindex` (`major_id`),
  KEY `major_academy_id_index` (`academy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` (`major_id`, `major_name`, `academy_id`) VALUES (1,'软件工程',1),(2,'计算机科学与技术',1),(3,'网络工程',1),(4,'历史学',2),(5,'政治学',2);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`),
  KEY `role_role_id_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role_name`) VALUES (1,'student'),(2,'judge'),(3,'admin_academy'),(4,'admin_school');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_info` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `student_number` varchar(16) DEFAULT NULL COMMENT '学号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0:男/1:女)',
  `degree` int(1) DEFAULT NULL COMMENT '学历(0:本科生/1:研究生) ',
  `grade` varchar(4) DEFAULT NULL COMMENT '年级',
  `academy_id` int(11) DEFAULT NULL COMMENT '学院id',
  `major_id` int(11) DEFAULT NULL COMMENT '专业id',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `admission_time` date DEFAULT NULL COMMENT '入学时间',
  `school_name` varchar(32) DEFAULT NULL COMMENT '学校名',
  `class_name` varchar(16) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `student_info_student_id_uindex` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_info`
--

LOCK TABLES `student_info` WRITE;
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名或学号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `disabled` tinyint(1) DEFAULT '0' COMMENT '是否被封禁',
  `untie_time` datetime DEFAULT NULL COMMENT '解封时间',
  `login_type` tinyint(2) DEFAULT NULL COMMENT '登陆类型0,1,2,3',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '1为删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_uindex` (`user_id`),
  UNIQUE KEY `user_phone_number_uindex` (`phone_number`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `username`, `phone_number`, `password`, `disabled`, `untie_time`, `login_type`, `deleted`) VALUES (1,'u1','11','$2a$10$cZ2i1.1RF2PPbqdCTSqONOWbUPZEZ.it8/TcOD7bCJrcb.0rd19qG',0,NULL,0,0),(2,'u2','12','$2a$10$cZ2i1.1RF2PPbqdCTSqONOWbUPZEZ.it8/TcOD7bCJrcb.0rd19qG',1,'2021-07-27 17:03:47',1,0),(6,NULL,'13067828119','$2a$10$pnAlWjYEcQ22mflDsFVxXuoYxQipb0yVWY7YUsbUFpG6LJVXKgXwW',0,NULL,0,0),(7,NULL,'13588349740','$2a$10$UdkLuccpGp1pQXpY2YAo9.7FqJlEHWk8UVc/IOBBIql/xtK.FpaXS',0,NULL,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  KEY `user_role_user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户id和角色id对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-05 19:09:46

-- 角色权限对应表
drop table if exists role_permission;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` int(1) DEFAULT NULL COMMENT '角色ID ',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '权限项ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限中间表';

LOCK TABLES `role_permission` WRITE;
insert into role_permission() values (0, '4', 'student-modify', now());

insert into role_permission() values (0, '5', 'auth', now());
insert into role_permission() values (0, '5', 'role-list', now());
insert into role_permission() values (0, '5', 'menu-list', now());
insert into role_permission() values (0, '5', 'admin-list', now());
insert into role_permission() values (0, '5', 'academy-admin-add', now());
insert into role_permission() values (0, '4', 'student-modify', now());
UNLOCK TABLES;