/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : spider

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 01/02/2019 15:29:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bilibili
-- ----------------------------
DROP TABLE IF EXISTS `bilibili`;
CREATE TABLE `bilibili`  (
  `id` int(11) NOT NULL,
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `author` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `pages` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `view` int(11) NULL DEFAULT NULL,
  `danmu` int(11) NULL DEFAULT NULL,
  `pubdate` int(11) NULL DEFAULT NULL,
  `pic` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `videos` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xbiquge_jobcache
-- ----------------------------
DROP TABLE IF EXISTS `xbiquge_jobcache`;
CREATE TABLE `xbiquge_jobcache`  (
  `jobtype` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `novel` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `chapter` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `jobid` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`jobid`(100)) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xbiquge_noveldump
-- ----------------------------
DROP TABLE IF EXISTS `xbiquge_noveldump`;
CREATE TABLE `xbiquge_noveldump`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `novel` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `chapter` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `url`(100)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 417 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xbiquge_novels
-- ----------------------------
DROP TABLE IF EXISTS `xbiquge_novels`;
CREATE TABLE `xbiquge_novels`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `novel` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `picture` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `progress` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `lastupdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `novel`(20)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 956 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
