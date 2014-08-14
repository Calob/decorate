-- 导出 decoration 的数据库结构
DROP DATABASE IF EXISTS `decoration`;
CREATE DATABASE IF NOT EXISTS `decoration` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `decoration`;


-- 导出  表 decoration.customer 结构
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int NOT NULL,
  `customer_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `id_number` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `lastupdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into customer values(1001, 'zhouxx', '111111', '1234567890', 'zhouxx@xx.com', '2014-08-13 18:02:01');