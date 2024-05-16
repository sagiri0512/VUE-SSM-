/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.36 : Database - firstjob
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`firstjob` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `firstjob`;


/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UID` int NOT NULL AUTO_INCREMENT,
  `UName` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UPWD` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UPhoneNmber` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Usex` int DEFAULT NULL,
  `Uage` int DEFAULT NULL,
  `UHeadImage` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://img.ixintu.com/download/jpg/20200901/3e9ce3813b7199ea9588eeb920f41208_512_512.jpg',
  `UMailBox` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `UNickName` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`UID`,`UName`,`UPWD`,`UPhoneNmber`,`Usex`,`Uage`,`UHeadImage`,`UMailBox`,`UNickName`) values 
(1,'sagiri','123456','21212121212',1,16,'images/1.png','sagiri@qq.com','sagiri'),
(2,'sbzcy','123456',NULL,NULL,NULL,'https://img.ixintu.com/download/jpg/20200901/3e9ce3813b7199ea9588eeb920f41208_512_512.jpg',NULL,NULL),
(3,'sbzyj','123456',NULL,NULL,NULL,'https://img.ixintu.com/download/jpg/20200901/3e9ce3813b7199ea9588eeb920f41208_512_512.jpg',NULL,NULL);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `TID` int NOT NULL AUTO_INCREMENT,
  `TName` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `type` */

insert  into `type`(`TID`,`TName`) values 
(1,'水果'),
(2,'饮品'),
(3,'快餐'),
(4,'零食');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `PID` int NOT NULL AUTO_INCREMENT,
  `PName` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PPrice` double NOT NULL,
  `PImg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PSales` int NOT NULL,
  `TID` int NOT NULL,
  `PIntroduce` varchar(500) COLLATE utf8mb4_general_ci NOT NULL,
  `PInventory` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`PID`),
  KEY `product_type_TID` (`TID`),
  CONSTRAINT `product_type_TID` FOREIGN KEY (`TID`) REFERENCES `type` (`TID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `product` */

insert  into `product`(`PID`,`PName`,`PPrice`,`PImg`,`PSales`,`TID`,`PIntroduce`,`PInventory`) values 
(1,'梨(一个)',2,'https://sagirinoinu.top/img/product/pear/pear.png',110,1,'梨是一种口感柔软、多汁甜美的水果，外皮通常呈现出绿色、黄色或红色。梨富含维生素C和纤维，有助于促进消化和维持健康。它可以生吃，也可用于烹饪和制作果汁。在烹饪中，梨可用来制作甜点、沙拉或果酱，与奶酪搭配也颇受喜爱。为了保持梨的新鲜度，应选择外观完整、无瑕疵的果实，并储存于阴凉通风处。总之，梨是一种美味多用途的水果，不仅口感可口，而且营养丰富，适合作为健康的零食或烹饪食材。',0),
(2,'可口可乐(一罐)',3,'https://sagirinoinu.top/img/product/coca-cola/coca-cola.png',100,2,'可口可乐是一种含有气泡的软饮料，以其甜味和爽口感闻名。它的主要成分包括碳酸水、高果糖玉米糖浆（或蔗糖）、焦糖色素、香料和咖啡因。这些成分使得可口可乐有了独特的口味，略带甜味但不过分甜腻，让人回味无穷。',0),
(3,'茄皇(一桶)',5,'https://sagirinoinu.top/img/product/qiehuang/qiehuang.png',22,3,'茄皇泡面是一种以番茄为主要风味的方便面，口感酸甜可口。制作简便，将面条煮熟后加入番茄调味料包即可享用。茄皇泡面受到许多人的喜爱，成为快捷美食选择之一。选择时可根据口味偏好和配料选择不同款式。',88),
(4,'雪碧(一罐)',3,'https://sagirinoinu.top/img/product/sprite/sprite.png',1,2,'雪碧是一种知名的碳酸饮料，以清爽的柠檬-莱姆味而闻名。它的口感清凉、气泡丰富，带有微甜的味道，成为许多人喜爱的饮品之一。雪碧是无咖啡因的，适合任何时候饮用。它可单独饮用，也可用于调制鸡尾酒。雪碧有许多不同的包装和口味变种，如经典雪碧、雪碧无糖和其他水果口味。总的来说，雪碧是一种受欢迎的碳酸饮料，适合在休闲时享用，给人带来清新爽口的口感体验。',99),
(5,'苹果(一个)',2,'https://sagirinoinu.top/img/product/apple/apple.png',100,1,'苹果是一种常见且广受喜爱的水果，有多种品种如富士、红星、嘎拉等。苹果外皮光滑，呈现不同颜色，如红、黄、绿等。果肉细腻多汁，口感清脆或软糯取决于品种。它富含维生素C、纤维和抗氧化剂，有助于增强免疫力和消化系统健康。苹果可生食，也可用于烹饪，如制作果酱、沙拉或烘焙食品。选择苹果时，应选外观完整、无软烂或明显划伤的果实。存放时避免阳光直射，可延长保鲜时间。总的来说，苹果是一种营养丰富、口感美味的水果，适合作为健康的零食或用于各种美食烹饪中。',0);


/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `AID` int NOT NULL AUTO_INCREMENT,
  `UID` int NOT NULL,
  `AText` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `APhonenNmber` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `AName` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Did` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`AID`),
  KEY `address_user_UID` (`UID`),
  CONSTRAINT `address_user_UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `address` */

/*Table structure for table `carousel` */

DROP TABLE IF EXISTS `carousel`;

CREATE TABLE `carousel` (
  `CID` int NOT NULL AUTO_INCREMENT,
  `CUrl` varchar(500) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `carousel` */

insert  into `carousel`(`CID`,`CUrl`) values 
(1,'https://sagirinoinu.top/img/carousel/lb1.jpg'),
(2,'https://sagirinoinu.top/img/carousel/lb2.jpg');


/*Table structure for table `detail` */

DROP TABLE IF EXISTS `detail`;

CREATE TABLE `detail` (
  `DID` int NOT NULL AUTO_INCREMENT,
  `PID` int NOT NULL,
  `DImg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`DID`),
  KEY `detail_product_PID` (`PID`),
  CONSTRAINT `detail_product_PID` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `detail` */

insert  into `detail`(`DID`,`PID`,`DImg`) values 
(1,1,'https://sagirinoinu.top/img/product/pear/pear1.png'),
(2,1,'https://sagirinoinu.top/img/product/pear/pear2.png'),
(3,1,'https://sagirinoinu.top/img/product/pear/pear3.png'),
(4,2,'https://sagirinoinu.top/img/product/coca-cola/coca-cola1.png'),
(5,2,'https://sagirinoinu.top/img/product/coca-cola/coca-cola2.png'),
(6,2,'https://sagirinoinu.top/img/product/coca-cola/coca-cola3.png'),
(7,3,'https://sagirinoinu.top/img/product/qiehuang/qiehuang1.png'),
(8,3,'https://sagirinoinu.top/img/product/qiehuang/qiehuang2.png'),
(9,3,'https://sagirinoinu.top/img/product/qiehuang/qiehuang3.png'),
(10,5,'https://sagirinoinu.top/img/product/apple/apple1.png'),
(11,5,'https://sagirinoinu.top/img/product/apple/apple2.png'),
(12,5,'https://sagirinoinu.top/img/product/apple/apple3.png'),
(13,4,'https://sagirinoinu.top/img/product/sprite/sprite1.png'),
(14,4,'https://sagirinoinu.top/img/product/sprite/sprite2.png'),
(15,4,'https://sagirinoinu.top/img/product/sprite/sprite3.png');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `PAID` int NOT NULL AUTO_INCREMENT,
  `UID` int NOT NULL,
  `PayPrice` double NOT NULL,
  `AID` int NOT NULL,
  `PID` int NOT NULL,
  `Number` int NOT NULL,
  `State` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`PAID`),
  KEY `payment_user_UID` (`UID`),
  KEY `payment_address_AID` (`AID`),
  KEY `payment_product_PID` (`PID`),
  CONSTRAINT `payment_address_AID` FOREIGN KEY (`AID`) REFERENCES `address` (`AID`),
  CONSTRAINT `payment_product_PID` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`),
  CONSTRAINT `payment_user_UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `payment` */

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `SID` int NOT NULL AUTO_INCREMENT,
  `UID` int NOT NULL,
  `PID` int NOT NULL,
  `SNum` int NOT NULL,
  PRIMARY KEY (`SID`),
  KEY `shoppingcart_product_PID` (`PID`),
  KEY `shoppingcart_user_UID` (`UID`),
  CONSTRAINT `shoppingcart_product_PID` FOREIGN KEY (`PID`) REFERENCES `product` (`PID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shoppingcart_user_UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `shoppingcart` */



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
