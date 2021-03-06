CREATE DATABASE `message_board_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `message_board_db`;

CREATE TABLE IF NOT EXISTS `messages` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `valid_period` int(11) NOT NULL,
  `created_datetime` datetime NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

