/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : tdemo

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-17 16:35:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(240) DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(15) NOT NULL COMMENT '电话号',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `creation_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT '1' COMMENT '创建人',
  `last_update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_updated_by` bigint(20) DEFAULT '1' COMMENT '最后更新人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
