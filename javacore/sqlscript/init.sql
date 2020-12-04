CREATE DATABASE IF NOT EXISTS `test_auth`;
USE `test_auth`;
CREATE TABLE IF NOT EXISTS `cat`(
`id` BIGINT(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主子id，主键',
`name` VARCHAR(64) NOT NULL DEFAULT '喵喵' COMMENT '主子名',
`owner_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '铲屎官id',
`sex` CHAR(1) NOT NULL DEFAULT 'F' COMMENT '性别：F-母，M-公',
`breed` SMALLINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品种',
`color`	TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '颜色',
`weight` DECIMAL(4,2) NOT NULL DEFAULT 5.20 COMMENT '体重',
`length` DECIMAL(4,2) NOT NULL DEFAULT 10.24 COMMENT '体长',
`age` TINYINT(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '年龄',
`birthday` DATE NOT NULL DEFAULT '1970-1-1' COMMENT '生日',
`deathday` DATE NOT NULL DEFAULT '9999-9-9' COMMENT '忌日',
`memo` VARCHAR(255) NOT NULL DEFAULT '无' COMMENT '备注',
`gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
`gmt_update` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
INDEX idx_owner(`owner_id`))ENGINE=INNODB DEFAULT CHARSET=utf8;