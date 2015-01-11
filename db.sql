/*
SQLyog Community v11.25 (32 bit)
MySQL - 5.6.14 : Database - under
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`under` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `under`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `postedAt` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`content`,`postedAt`,`title`) values (1,'asdfasdfasdf','2014-12-30 22:49:47','ipsum lapsum'),(2,'asdfasdfasdf','2014-12-30 22:50:41',' asdfasdf'),(3,'zxcvzcxvzxcvxczv','2015-01-02 15:10:05',' sdfgsd');

/*Table structure for table `article_authorids` */

DROP TABLE IF EXISTS `article_authorids`;

CREATE TABLE `article_authorids` (
  `Article_id` bigint(20) NOT NULL,
  `authorIds` bigint(20) DEFAULT NULL,
  KEY `FK_f9ivk719aqb0rqd8my08loev7` (`Article_id`),
  CONSTRAINT `FK_f9ivk719aqb0rqd8my08loev7` FOREIGN KEY (`Article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article_authorids` */

insert  into `article_authorids`(`Article_id`,`authorIds`) values (2,1),(3,1);

/*Table structure for table `grouptopicture` */

DROP TABLE IF EXISTS `grouptopicture`;

CREATE TABLE `grouptopicture` (
  `groupId` bigint(20) NOT NULL DEFAULT '0',
  `pictureId` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`groupId`,`pictureId`),
  KEY `pictureId` (`pictureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `grouptopicture` */

insert  into `grouptopicture`(`groupId`,`pictureId`) values (1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(3,24),(3,25),(3,26),(3,27),(3,28),(2,29),(2,30),(2,31),(2,32),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52);

/*Table structure for table `memberofgroup` */

DROP TABLE IF EXISTS `memberofgroup`;

CREATE TABLE `memberofgroup` (
  `groupId` bigint(20) NOT NULL DEFAULT '0',
  `memberOfGroupId` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`groupId`,`memberOfGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `memberofgroup` */

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `file` varchar(255) DEFAULT NULL,
  `about` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `picture` */

insert  into `picture`(`id`,`name`,`created`,`file`,`about`) values (12,'Fish','2015-01-10 14:13:23','IMG_0013.jpg','egypt'),(13,'Grupper','2015-01-10 14:16:38','IMG_0024.jpg','Egypt'),(14,'Puffer','2015-01-10 14:17:25','IMG_0222.jpg','Egypt'),(15,'Mr Octopus','2015-01-10 14:18:31','IMG_0239.jpg','Egypt'),(16,'Family','2015-01-10 14:19:48','IMG_0635.jpg','Egypt'),(17,'Allot of fish','2015-01-10 14:20:24','IMG_0492.jpg',''),(18,'Puffer','2015-01-10 14:21:00','IMG_0647.jpg',''),(19,'Spanish Dancer','2015-01-10 14:21:21','IMG_0241.jpg',''),(20,'Eel','2015-01-10 14:21:45','IMG_0160.jpg',''),(21,'On the loose','2015-01-10 14:22:41','IMG_0665.jpg',''),(22,'Turtle','2015-01-10 14:23:11','IMG_0411.jpg',''),(23,'Cave','2015-01-10 14:24:05','IMG_0114.jpg',''),(24,'Davidsgja','2015-01-10 20:52:10','IMG_0048 (2).JPG',''),(25,'Thingvallavatn','2015-01-10 20:52:37','IMG_0035 (2).JPG',''),(26,'Between two worlds','2015-01-10 20:53:21','IMG_0081.JPG',''),(27,'Silfra','2015-01-10 20:53:38','IMG_0088.JPG',''),(28,'Silfra diver','2015-01-10 20:54:55','IMG_0055.jpg',''),(29,'Dozing','2015-01-10 22:01:49','20141220-IMG_0021.jpg',''),(30,'On the move','2015-01-10 22:02:16','20141220-IMG_0010.jpg',''),(31,'Stairs','2015-01-10 22:02:49','IMG_0096.JPG',''),(32,'Nails','2015-01-10 22:03:08','IMG_0054.JPG',''),(35,'Starship','2015-01-10 22:07:55','edited-2.jpg',''),(36,'Above and bellow','2015-01-10 22:09:04','20121229-Diving1 113.jpg',''),(37,'Gathering','2015-01-10 22:09:51','20121229-Diving1 072.jpg',''),(38,'Starfish','2015-01-10 22:11:09','20121231-Diving2 021.jpg',''),(39,'Mr lobster','2015-01-10 22:12:15','20130122-Dive5 018.jpg',''),(40,'Mr crab','2015-01-10 22:12:50','20130223-diving9 027.jpg',''),(41,'Fat star','2015-01-10 22:13:38','20130306-diving10 029.jpg',''),(42,'Flyndre','2015-01-10 22:14:02','20130316-diving11 047.jpg',''),(43,'Lion','2015-01-10 22:15:31','20130404-egypt 514.jpg',''),(44,'Lonely fish','2015-01-10 22:16:10','20130404-egypt 510.jpg',''),(45,'Fishhole','2015-01-10 22:16:57','20130406-egypt 835.jpg',''),(46,'Truck','2015-01-10 22:17:22','20130403-egypt 273.jpg',''),(47,'Lion up close','2015-01-10 22:18:02','20130402-egypt 168.jpg',''),(48,'Parrot','2015-01-10 22:18:38','20130406-egypt 885.jpg',''),(49,'Crocodile','2015-01-10 22:19:15','20130403-egypt 232.jpg',''),(50,'Island','2015-01-10 22:19:42','20130404-egypt 574.jpg',''),(51,'Blue ray','2015-01-10 22:20:03','20130404-egypt 479.jpg',''),(52,'Gypsi star','2015-01-10 22:21:13','IMG_0361.jpg','');

/*Table structure for table `picturegroup` */

DROP TABLE IF EXISTS `picturegroup`;

CREATE TABLE `picturegroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `picturegroup` */

insert  into `picturegroup`(`id`,`name`,`description`) values (1,'Egypt','Pics from Egypt'),(2,'Norway','Pics from Norway'),(3,'Iceland','Pics from Iceland');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `isAdmin` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`fullname`,`isAdmin`,`password`,`username`) values (1,'karl','','tummybird39','keygjones');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
