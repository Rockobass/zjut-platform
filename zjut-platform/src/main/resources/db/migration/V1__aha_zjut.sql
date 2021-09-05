-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aha_zjut
-- ------------------------------------------------------
-- Server version	8.0.12
UNLOCK TABLES;
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

/**
全量运行时注意外键约束，有外键约束的表首先删除
 */

DROP TABLE IF EXISTS `admin_academy_info`;
DROP TABLE IF EXISTS `flyway_schema_history`;
DROP TABLE IF EXISTS `judge_info`;
DROP TABLE IF EXISTS `student_info`;
drop table if exists role_permission;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `major`;
DROP TABLE IF EXISTS `academy`;


DROP TABLE IF EXISTS `project`;

DROP TABLE IF EXISTS `competition_static_tags`;
DROP TABLE IF EXISTS `competition_key_point`;
DROP TABLE IF EXISTS `competition_stage_status`;
DROP TABLE IF EXISTS `competition_track`;
DROP TABLE IF EXISTS `competition_stage_award`;

DROP TABLE IF EXISTS `competition_stage`;
DROP TABLE IF EXISTS `competition`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='学院表';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='校级管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_academy_info`
--

LOCK TABLES `admin_academy_info` WRITE;
/*!40000 ALTER TABLE `admin_academy_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_academy_info` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评委信息表';
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='专业表';
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role_name`)
VALUES (1,'student'),
(2,'judge'),
(3,'admin_academy'),
(4,'admin_school'),
(5,'super_admin');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `username`, `phone_number`, `password`, `disabled`, `untie_time`, `login_type`, `deleted`)
VALUES (1,'u1','11','$2a$10$cZ2i1.1RF2PPbqdCTSqONOWbUPZEZ.it8/TcOD7bCJrcb.0rd19qG',0,NULL,0,0),
(2,'u2','12','$2a$10$cZ2i1.1RF2PPbqdCTSqONOWbUPZEZ.it8/TcOD7bCJrcb.0rd19qG',1,'2021-07-27 17:03:47',1,0),
(6,NULL,'13067828119','$2a$10$pnAlWjYEcQ22mflDsFVxXuoYxQipb0yVWY7YUsbUFpG6LJVXKgXwW',0,NULL,0,0),
(7,NULL,'13588349740','$2a$10$UdkLuccpGp1pQXpY2YAo9.7FqJlEHWk8UVc/IOBBIql/xtK.FpaXS',0,NULL,0,0),
(null,'zjut','15677751219','$10$VfJAftAS72ndQBT3QjV52uVRgwgjKXwLNdPjXjd.dMcQO3i./QyVi',0,NULL,3,0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户id和角色id对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES (1,5);
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
insert into role_permission() values (null , '4', 'student-modify', now());
insert into role_permission() values (null, '5', 'auth', now());
insert into role_permission() values (null, '5', 'role-list', now());
insert into role_permission() values (null, '5', 'menu-list', now());
insert into role_permission() values (null, '5', 'admin-list', now());
insert into role_permission() values (null, '5', 'academy-admin-add', now());
insert into role_permission() values (null, '4', 'student-modify', now());
UNLOCK TABLES;

DROP TABLE IF EXISTS `competition`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`competition`  (
  `comp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '赛事id',
  `comp_type` int(1) NOT NULL COMMENT '赛事类型(1:运河杯立项 2:结题 3:竞赛 4:新苗立项 5:挑战杯专项赛)',
  `comp_year` int(4) NOT NULL COMMENT '举办年份',
  `comp_th` int(5) NOT NULL COMMENT '赛事届数',
  `comp_school_rec_amt` int(3) NOT NULL COMMENT '校团委推荐数量',
  `comp_info` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '赛事简介',
  `comp_judge_way` tinyint(1) NOT NULL DEFAULT 0 COMMENT '评委评分方式（0:打分/1:排序）',
  `comp_judge_standard` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '校级管理员指定评判标准',
 `comp_status` int(1) NOT NULL DEFAULT 1 COMMENT '赛事状态(0:未开始 1:进行中 2:已完结) ',
 UNIQUE INDEX `index_comp_th_unique`(`comp_th`) USING BTREE,
  PRIMARY KEY (`comp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
LOCK TABLES `competition` WRITE;
insert into competition() values (1, '1', 2021,11,12,'这个是赛事简介',0,'这个是评判标准',1);
insert into competition() values (NULL, '2', 2021,13,12,'这个是赛事简介',0,'这个是评判标准',1);
UNLOCK TABLES;

drop table if exists competition_type_info;
CREATE TABLE `aha_zjut`.`competition_type_info`  (
  `comp_type_id` int(1) NOT NULL COMMENT '竞赛类型ID',
  `comp_type_name` varchar(255) NULL COMMENT '竞赛类型Name',
  PRIMARY KEY (`comp_type_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
LOCK TABLES `competition_type_info` WRITE;
insert into competition_type_info() values (1,"运河杯立项结题");
insert into competition_type_info() values (2,"运河杯竞赛");
insert into competition_type_info() values (3,"浙江省新苗");
insert into competition_type_info() values (4,"挑战杯专项赛");
UNLOCK TABLES;


DROP TABLE IF EXISTS `competition_static_tags`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`competition_static_tags`  (
  `comp_type` int(255) NOT NULL COMMENT '竞赛类型',
  `comp_group` varchar(50) NULL DEFAULT NULL COMMENT '竞赛组别',
  `comp_tag` varchar(100) NULL DEFAULT NULL COMMENT '竞赛标签'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
LOCK TABLES `competition_static_tags` WRITE;
INSERT INTO `aha_zjut`.`competition_static_tags` (`comp_type`, `comp_group`, `comp_tag`) VALUES (1, '本科组', '机械和控制'),(1, '本科组', '信息技术'),(1, '本科组', '数理'),(1, '本科组', '生命科学'),(1, '本科组', '能源化工'),(1, '本科组', '社科'),(1, '本科组', '艺术设计'),(1, '本科组', '建筑工程'),(1, '硕研组', '机械和控制'),(1, '硕研组', '信息技术'),(1, '硕研组', '数理'),(1, '硕研组', '生命科学'),(1, '硕研组', '能源化工'),(1, '硕研组', '社科'),(1, '硕研组', '艺术设计'),(1, '硕研组', '建筑工程'),(1, '博研组', '机械和控制'),(1, '博研组', '信息技术'),(1, '博研组', '数理'),(1, '博研组', '生命科学'),(1, '博研组', '能源化工'),(1, '博研组', '社科'),(1, '博研组', '艺术设计'),(1, '博研组', '建筑工程'),(1, '留学生组', '机械和控制'),(1, '留学生组', '信息技术'),(1, '留学生组', '数理'),(1, '留学生组', '生命科学'),(1, '留学生组', '能源化工'),(1, '留学生组', '社科'),(1, '留学生组', '艺术设计'),(1, '留学生组', '建筑工程'),(2, '本科生组', '机械和控制'),(2, '本科生组', '信息技术'),(2, '本科生组', '数理'),(2, '本科生组', '生命科学'),(2, '本科生组', '能源化工'),(2, '本科生组', '社科'),(2, '本科生组', '艺术设计'),(2, '本科生组', '建筑工程'),(2, '研究生组', '机械和控制'),(2, '研究生组', '信息技术'),(2, '研究生组', '数理'),(2, '研究生组', '生命科学'),(2, '研究生组', '能源化工'),(2, '研究生组', '社科'),(2, '研究生组', '艺术设计'),(2, '研究生组', '建筑工程'),(3, '本科组', '机械和控制'),(3, '本科组', '信息技术'),(3, '本科组', '数理'),(3, '本科组', '生命科学'),(3, '本科组', '能源化工'),(3, '本科组', '社科'),(3, '本科组', '艺术设计'),(3, '本科组', '建筑工程'),(3, '硕研组', '机械和控制'),(3, '硕研组', '信息技术'),(3, '硕研组', '数理'),(3, '硕研组', '生命科学'),(3, '硕研组', '能源化工'),(3, '硕研组', '社科'),(3, '硕研组', '艺术设计'),(3, '硕研组', '建筑工程'),(3, '博研组', '机械和控制'),(3, '博研组', '信息技术'),(3, '博研组', '数理'),(3, '博研组', '生命科学'),(3, '博研组', '能源化工'),(3, '博研组', '社科'),(3, '博研组', '艺术设计'),(3, '博研组', '建筑工程'),(3, '留学生组', '机械和控制'),(3, '留学生组', '信息技术'),(3, '留学生组', '数理'),(3, '留学生组', '生命科学'),(3, '留学生组', '能源化工'),(3, '留学生组', '社科'),(3, '留学生组', '艺术设计'),(3, '留学生组', '建筑工程'),(4, '哲学社科类', '哲学'),(4, '哲学社科类', '经济'),(4, '哲学社科类', '社会'),(4, '哲学社科类', '法律'),(4, '哲学社科类', '教育'),(4, '哲学社科类', '管理'),(4, '自然科学类', '机械控制'),(4, '自然科学类', '信息技术'),(4, '自然科学类', '数理'),(4, '自然科学类', '生命科学'),(4, '自然科学类', '能源化工'),(4, '发明制作A类', '机械控制'),(4, '发明制作A类', '信息技术'),(4, '发明制作A类', '数理'),(4, '发明制作A类', '生命科学'),(4, '发明制作A类', '能源化工'),(4, '发明制作B类', '机械控制'),(4, '发明制作B类', '信息技术'),(4, '发明制作B类', '数理'),(4, '发明制作B类', '生命科学'),(4, '发明制作B类', '能源化工');
UNLOCK TABLES;

DROP TABLE IF EXISTS `competition_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
 CREATE TABLE `aha_zjut`.`competition_stage`  (
  `comp_id` int(11) NOT NULL COMMENT '赛事id',
  `stage_id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 阶段id',
  `stage_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阶段名',
  `stage_des` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阶段描述',
  `stage_order` int(2) NULL DEFAULT NULL COMMENT '赛事阶段顺序',
  `next_stage_num` int(3) NULL DEFAULT NULL COMMENT '可进入下一阶段数目',
  PRIMARY KEY (`stage_id`) USING BTREE,
  INDEX `stage_id`(`stage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
/*!40101 SET character_set_client = @saved_cs_client */;
LOCK TABLES `competition_stage` WRITE;
INSERT INTO `competition_stage` VALUES (1,1, '初赛', '别看了 初赛', 1, 15);
UNLOCK TABLES;
--
-- Dumping data for table `competition_stage`
--

LOCK TABLES `competition_stage` WRITE;
/*!40000 ALTER TABLE `competition_stage` DISABLE KEYS */;
/*!40000 ALTER TABLE `competition_stage` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `competition_key_point`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`competition_key_point`  (
  `comp_id` int(11) NOT NULL COMMENT '外键 竞赛id',
  `stage_id` int(11) NOT NULL COMMENT '外键 竞赛阶段id',
  `comp_key_point_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关键时间节点名',
  `comp_key_time` datetime(0) NOT NULL COMMENT '关键时间节点',
  `comp_need_alert` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要消息提醒（0不需要 1需要）',
  `comp_user_type` int(1) NULL DEFAULT NULL COMMENT '提醒人员类型',
  CONSTRAINT `competition_key_point_comp_id` FOREIGN KEY (`comp_id`) REFERENCES `aha_zjut`.`competition` (`comp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `competition_key_point_stage_id` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;
LOCK TABLES `competition_key_point` WRITE;
INSERT INTO `competition_key_point` VALUES (1,1, '开始上传', '2021-09-03 19:23:55', 1, 1);
INSERT INTO `competition_key_point` VALUES (1,1, '评委评审', '2021-09-11 19:24:21', 0, NULL);
INSERT INTO `competition_key_point` VALUES (1,1, '校级管理员评价', '2021-09-14 19:24:43', 0, NULL);
INSERT INTO `competition_key_point` VALUES (2,1, '开始上传', '2021-09-03 19:24:57', 1, 1);
UNLOCK TABLES;

--
-- Table structure for table `competition_stage`
--


DROP TABLE IF EXISTS `competition_stage_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
CREATE TABLE `aha_zjut`.`competition_stage_status`  (
  `stage_id` int(11) NOT NULL COMMENT '关联阶段id',
  `status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小阶段id',
  `status_name` varchar(50)  NOT NULL COMMENT '小阶段name',
  `status_order` varchar(2)  NOT NULL COMMENT '小阶段顺序',
  `status_desc` varchar(255) NOT NULL COMMENT '状态描述',
  `status_end_time` timestamp(6) NULL DEFAULT NULL COMMENT '状态结束时间',
  PRIMARY KEY (`status_id`) USING BTREE,
  INDEX `competition_stage_status_stage_id`(`stage_id`) USING BTREE,
  CONSTRAINT `competition_stage_status_stage_id` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
--
-- Dumping data for table `competition_stage_status`
--

LOCK TABLES `competition_stage_status` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `competition_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
CREATE TABLE `aha_zjut`.`competition_track`  (
  `comp_id` int(11) NOT NULL COMMENT '赛事id',
  `stage_id` int(11) NOT NULL COMMENT '赛事阶段id',
  `track_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通道id',
  `track_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通道名',
  `track_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 通道描述',
  PRIMARY KEY (`track_id`) USING BTREE,
  INDEX `comp_track_comp_id`(`comp_id`) USING BTREE,
  CONSTRAINT `comp_track_comp_id` FOREIGN KEY (`comp_id`) REFERENCES `aha_zjut`.`competition` (`comp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comp_track_stage_id` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `competition_track` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `academy_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
CREATE TABLE `aha_zjut`.`academy_competition`  (
  `comp_id` int(11) NOT NULL COMMENT '赛事id',
  `stage_id` int(11) NOT NULL COMMENT '赛事阶段id',
  `academy_id` int(11) NOT NULL COMMENT '学院id',
  `academy_rec_amt` int(4) NOT NULL COMMENT '学院剩余推荐数目',
  `academy_province_rec_amt` int(3) NOT NULL COMMENT '学院直推省级名额',
  `third_prize_amt` int(3) NOT NULL COMMENT '学院自评三等奖数目',
  INDEX `academy_competition_comp_id`(`comp_id`) USING BTREE,
  INDEX `academy_competition_academy_id`(`academy_id`) USING BTREE,
  CONSTRAINT `academy_competition_academy_id` FOREIGN KEY (`academy_id`) REFERENCES `aha_zjut`.`academy` (`academy_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `academy_competition_comp_id` FOREIGN KEY (`comp_id`) REFERENCES `aha_zjut`.`competition` (`comp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `academy_competition_stage_id` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
LOCK TABLES `competition_track` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `competition_stage_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
ALTER TABLE `aha_zjut`.`competition_stage`ADD INDEX(`comp_id`);

CREATE TABLE `aha_zjut`.`competition_stage_award`  (
  `comp_id` int(11) NOT NULL COMMENT '赛事id',
  `stage_id` int(11) NOT NULL COMMENT '阶段id',
  `award_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 赛事奖励名称(特等  一等  二等   三等     鼓励 )',
  `award_percent` int(2) NULL DEFAULT NULL COMMENT '获奖百分比',
  `award_amt` int(3) NULL DEFAULT NULL COMMENT '奖项数量',
  PRIMARY KEY (`comp_id`, `stage_id`, `award_name`) USING BTREE,
  INDEX `comp_stage_comp_id`(`comp_id`) USING BTREE,
  INDEX `comp_stage_stage_id`(`stage_id`) USING BTREE,
  CONSTRAINT `comp_stage_comp_id` FOREIGN KEY (`comp_id`) REFERENCES `aha_zjut`.`competition_stage` (`comp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comp_stage_stage_id` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `competition_stage_award` WRITE;

UNLOCK TABLES;



DROP TABLE IF EXISTS `project`;
 SET character_set_client = utf8mb4 ;

CREATE TABLE `aha_zjut`.`project`  (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `comp_id` int(11) NOT NULL COMMENT '竞赛id',
  `comp_track` int(11) NOT NULL COMMENT '竞赛通道',
  `comp_stage` int(11) NOT NULL COMMENT '竞赛阶段',
  `project_name` varchar(255) NOT NULL COMMENT '项目名称',
  `project_leader_id` int(11) NOT NULL COMMENT '项目负责人',
  `project_create_time` datetime(6) NOT NULL COMMENT '项目创建时间',
  `project_score` decimal(8,2) NULL COMMENT '项目得分',
  `project_rank` int(3) NULL COMMENT '项目排名',
  `project_academy_status` int(1) NULL DEFAULT 1 COMMENT '项目状态(1:已审核、2:退回修改、3:未审核) 院级管理员视角',
  `project_student_status` int(1) NULL DEFAULT 1 COMMENT '项目状态(1:待提交、2:已提交3:已退回、4:已完结) 学生视角',
  `project_group` varchar(255) NOT NULL COMMENT '项目组别',
  `project_tag` varchar(255) NOT NULL COMMENT '项目标签',
  `project_academy_id` int(11) NOT NULL COMMENT '项目学院id',
  `project_award_id` int(11) NOT NULL DEFAULT 4 COMMENT '赛事奖励id(0:特等  1:一等  2：二等   3：三等',
  `project_province_rec` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否省级推荐 0：否、1：是',
  `project_delete_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0:未删除 1:已删除)',
  PRIMARY KEY (`project_id`),
  CONSTRAINT `project_comp_id` FOREIGN KEY (`comp_id`) REFERENCES `aha_zjut`.`competition` (`comp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_academy_id` FOREIGN KEY (`project_academy_id`) REFERENCES `aha_zjut`.`academy` (`academy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_competition_track` FOREIGN KEY (`comp_track`) REFERENCES `aha_zjut`.`competition_track` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_comp_stage` FOREIGN KEY (`comp_stage`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project` WRITE;

UNLOCK TABLES;

DROP TABLE IF EXISTS `project_apply_judge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `aha_zjut`.`project_apply_judge`  (
  `project_apply_id` int(11) NOT NULL COMMENT '申请ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `comp_stage` int(11) NOT NULL COMMENT '赛事阶段',
  `apply_status` int(1)  NOT NULL DEFAULT 1 COMMENT '申请状态（1：待审核，2:未通过，3:已通过）',
  `apply_comment` varchar(255) COMMENT '审批意见',
  `update_time` datetime(6) NOT NULL COMMENT '最近修改时间',
  INDEX `apply_judge_project_id`(`project_id`) USING BTREE,
  INDEX `apply_judge_comp_stage`(`comp_stage`) USING BTREE,
  PRIMARY KEY (`project_apply_id`),
  CONSTRAINT `apply_judge_comp_stage` FOREIGN KEY (`comp_stage`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `apply_judge_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_apply_judge` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `project_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `aha_zjut`.`project_member`  (
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `member_id` int(11) NOT NULL COMMENT '项目成员id',
  `project_member_rank` int(2) NOT NULL COMMENT '成员排位,1队长',
  `project_member_job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员职务',
  INDEX `project_member_project_id`(`project_id`) USING BTREE,
  INDEX `project_member_member_id`(`member_id`) USING BTREE,
  CONSTRAINT `project_member_member_id` FOREIGN KEY (`member_id`) REFERENCES `aha_zjut`.`user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_member_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_member` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `project_member_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `aha_zjut`.`project_member_apply`  (
  `project_member_apply_id` int(11) NOT NULL COMMENT '申请ID',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `apply_user_id` int(11) NOT NULL COMMENT '申请人id',
  `apply_status` int(1) NOT NULL DEFAULT 1 COMMENT '申请状态（1:待审核，2:未通过，3:已通过）',
  `apply_comment` varchar(255) COMMENT '审批意见',
  `update_time` datetime(6) NULL COMMENT '更新时间',
   PRIMARY KEY (`project_member_apply_id`),
  CONSTRAINT `member_apply_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member_apply_user_id` FOREIGN KEY (`apply_user_id`) REFERENCES `aha_zjut`.`user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_member_apply` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `project_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `aha_zjut`.`project_teacher`  (
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `academy_id` int(11) NOT NULL COMMENT '学院ID',
  `teacher_name` varchar(255) NOT NULL COMMENT '项目老师名称',
  `teacher_rank` varchar(255) NOT NULL COMMENT '项目老师职称(1:讲师，2：副教授 3：教授)',
  CONSTRAINT `project_teacher_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_teacher_academy_id` FOREIGN KEY (`academy_id`) REFERENCES `aha_zjut`.`academy` (`academy_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_teacher` WRITE;

UNLOCK TABLES;

DROP TABLE IF EXISTS `project_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
CREATE TABLE `aha_zjut`.`project_resource`  (
  `project_resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目资源id',
  `project_id` int(11) NULL COMMENT '项目id',
  `comp_stage` int(11) NULL COMMENT '竞赛阶段',
  `project_resource_name` varchar(255) NULL COMMENT '项目资源名称',
  `cos_path` varchar(255) NULL COMMENT '资源存放在cos的路径',
  PRIMARY KEY (`project_resource_id`),
  CONSTRAINT `project_resource_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_resource` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `project_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`project_score`  (
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `stage_id` int(11) NOT NULL COMMENT '阶段id',
  `score` decimal(8, 0) NULL DEFAULT NULL COMMENT '分数',
  `rank` int(3) NULL DEFAULT NULL COMMENT '排名',
  `judge_id` int(11) NOT NULL COMMENT '评委id',
  `judge_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '评委的名字',
  `judge_comment` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '评委评论',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  INDEX `project_score_project_id`(`project_id`) USING BTREE,
  INDEX `project_score_comp_stage`(`stage_id`) USING BTREE,
  INDEX `project_score_judge_id`(`judge_id`) USING BTREE,
  CONSTRAINT `project_score_comp_stage` FOREIGN KEY (`stage_id`) REFERENCES `aha_zjut`.`competition_stage` (`stage_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_score_judge_id` FOREIGN KEY (`judge_id`) REFERENCES `aha_zjut`.`judge_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_score_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

LOCK TABLES `project_score` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `canal_cup_cf_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`canal_cup_cf_info`  (
  `project_id` int(11) NOT NULL COMMENT '项目ID/编号',
  `type` tinyint(1) NOT NULL COMMENT ' 0:A类 1:B类',
  `summary` text NOT NULL COMMENT '作品摘要 富文本',
  `logic` text NOT NULL COMMENT '作品思路',
  `uniq` text NOT NULL COMMENT '作品独特性',
  `apply` text NOT NULL COMMENT '作品应用',
  `award_info` text NULL COMMENT '作品奖励',
  `research_way` text NULL COMMENT '作品调查方式',
  `tech_trans` text NULL COMMENT ' 作品转让方式',
  `show_way` text NULL COMMENT '作品展示形式',
  `review` text NULL COMMENT '研究综述',
  PRIMARY KEY (`project_id`),
  CONSTRAINT `canal_cup_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
LOCK TABLES `canal_cup_cf_info` WRITE;

UNLOCK TABLES;


DROP TABLE IF EXISTS `canal_cup_sp_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aha_zjut`.`canal_cup_sp_info`  (
  `project_id` int(11) NOT NULL COMMENT '项目ID/编号',
  `type` tinyint(1) NOT NULL COMMENT ' 0:A类 1:B类',
  `summary` text NOT NULL COMMENT '作品摘要 富文本',
  `logic` text NOT NULL COMMENT '作品思路',
  `uniq` text NOT NULL COMMENT '作品独特性',
  `apply` text NOT NULL COMMENT '作品应用',
  `award_info` text NULL COMMENT '作品奖励',
  `research_way` text NULL COMMENT '作品调查方式',
  `tech_trans` text NULL COMMENT ' 作品转让方式',
  `show_way` text NULL COMMENT '作品展示形式',
  `review` text NULL COMMENT '研究综述',
  PRIMARY KEY (`project_id`),
  CONSTRAINT `canal_cup_project_id` FOREIGN KEY (`project_id`) REFERENCES `aha_zjut`.`project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
LOCK TABLES `canal_cup_sp_info` WRITE;

UNLOCK TABLES;