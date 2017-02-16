/*
SQLyog Enterprise v12.09 (32 bit)
MySQL - 5.6.28-log : Database - stockmgnt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values (7,'Stationery Item'),(8,'Electronics Item'),(9,'Kitchen Wares'),(10,'Test1'),(11,'test2'),(12,'Testing 3'),(13,'test4'),(14,'test5'),(15,'qwweere6');

/*Table structure for table `clients` */

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `clients` */

insert  into `clients`(`id`,`address`,`email`,`mobile`,`name`,`phone`) values (4,'add','vinadakjdlkajals',45634534,'ASd14dfd',121312323),(5,'add','vinadakjdlkajals',45634534,'ASd',121312323);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` smallint(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `purchasePrice` decimal(38,0) DEFAULT NULL,
  `sellingPrice` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`id`,`category_id`,`description`,`name`,`purchasePrice`,`sellingPrice`) values (19,8,'A cheap stuff.','PenDrive','500','900'),(20,8,'A cheap phone','Mobile Phone','5000','7000'),(21,9,'A cheap spoon','Spoon','10','30');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `transaction` */

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `transaction_status` varchar(255) DEFAULT NULL,
  `transaction_with` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `transaction` */

/*Table structure for table `transaction_detail` */

DROP TABLE IF EXISTS `transaction_detail`;

CREATE TABLE `transaction_detail` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `qty` int(11) DEFAULT NULL,
  `total_price` decimal(38,0) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_transaction_detail_transaction_id` (`transaction_id`),
  KEY `FK_transaction_detail_product_id` (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `transaction_detail` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`address`,`dob`,`email`,`first_name`,`gender`,`last_name`,`phone`) values (4,'Kathmandu','2014-01-01','admin@IMS.com','admin','male','admin',49561381),(5,'Kathmandu','2014-01-02','user@ims.com','user','female','user',49156845);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*Table structure for table `view_buying` */

DROP TABLE IF EXISTS `view_buying`;

/*!50001 DROP VIEW IF EXISTS `view_buying` */;
/*!50001 DROP TABLE IF EXISTS `view_buying` */;

/*!50001 CREATE TABLE  `view_buying`(
 `product_id` int(11) ,
 `total_qty` decimal(32,0) 
)*/;

/*Table structure for table `view_selling` */

DROP TABLE IF EXISTS `view_selling`;

/*!50001 DROP VIEW IF EXISTS `view_selling` */;
/*!50001 DROP TABLE IF EXISTS `view_selling` */;

/*!50001 CREATE TABLE  `view_selling`(
 `product_id` int(11) ,
 `total_qty` decimal(32,0) 
)*/;

/*View structure for view view_buying */

/*!50001 DROP TABLE IF EXISTS `view_buying` */;
/*!50001 DROP VIEW IF EXISTS `view_buying` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_buying` AS select distinct `td`.`product_id` AS `product_id`,sum(`td`.`qty`) AS `total_qty` from (`transaction_detail` `td` join `transaction` `t` on((`t`.`id` = `td`.`transaction_id`))) where (`t`.`transaction_status` = 'buying') group by `td`.`product_id` */;

/*View structure for view view_selling */

/*!50001 DROP TABLE IF EXISTS `view_selling` */;
/*!50001 DROP VIEW IF EXISTS `view_selling` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_selling` AS select distinct `td`.`product_id` AS `product_id`,sum(`td`.`qty`) AS `total_qty` from (`transaction_detail` `td` join `transaction` `t` on((`t`.`id` = `td`.`transaction_id`))) where (`t`.`transaction_status` = 'selling') group by `td`.`product_id` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
