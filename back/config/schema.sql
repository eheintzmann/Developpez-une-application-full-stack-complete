SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS `db_mdd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_mdd`;

CREATE TABLE IF NOT EXISTS `comments` (
    `comment_id` bigint NOT NULL AUTO_INCREMENT,
    `content` text,
    `created_at` datetime(6) DEFAULT NULL,
    `updated_at` datetime(6) DEFAULT NULL,
    `user_id` bigint NOT NULL,
    `post_id` bigint NOT NULL,
    PRIMARY KEY (`comment_id`),
    KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
    KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
    CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE  IF NOT EXISTS `posts` (
    `post_id` bigint NOT NULL AUTO_INCREMENT,
    `content` text,
    `created_at` datetime(6) DEFAULT NULL,
    `title` varchar(255) NOT NULL,
    `updated_at` datetime(6) DEFAULT NULL,
    `user_id` bigint NOT NULL,
    `topic_id` bigint NOT NULL,
    PRIMARY KEY (`post_id`),
    UNIQUE KEY `UK_mchce1gm7f6otpphxd6ixsdps` (`title`),
    KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`),
    KEY `FKrfchr8dax0kfngvvkbteh5n7h` (`topic_id`),
    CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `FKrfchr8dax0kfngvvkbteh5n7h` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `topics` (
    `topic_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) DEFAULT NULL,
    `description` text,
    `title` varchar(255) NOT NULL,
    `updated_at` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`topic_id`),
    UNIQUE KEY `UK_71rjsqaorlydivvwh8xgousre` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `user_topic` (
    `user_id` bigint NOT NULL,
    `topic_id` bigint NOT NULL,
    KEY `FK8a3sox8wsdmmpegof8fepfte7` (`topic_id`),
    KEY `FK3qiv7jhn37ne73gdqnyalqnwf` (`user_id`),
    CONSTRAINT `FK3qiv7jhn37ne73gdqnyalqnwf` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `FK8a3sox8wsdmmpegof8fepfte7` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `users` (
    `user_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) DEFAULT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `updated_at` datetime(6) DEFAULT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
    UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
