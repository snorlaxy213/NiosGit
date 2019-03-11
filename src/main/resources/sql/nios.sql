/*
 Navicat Premium Data Transfer

 Source Server         : vino
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : nios

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/03/2019 22:35:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `appointment_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appointment_time` datetime(0) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `doctor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  INDEX `FKdpwbysnn3ohblfovgj0tl21qx`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('APP0001', '2019-03-10 18:52:43', '耳朵', 'admin', '30', 'USR0001');

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic`  (
  `clinic_code` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`clinic_code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for table_identity
-- ----------------------------
DROP TABLE IF EXISTS `table_identity`;
CREATE TABLE `table_identity`  (
  `table_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identity_prefix` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_length` int(11) NOT NULL,
  `next_identity` bigint(20) NOT NULL,
  PRIMARY KEY (`table_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password_hash` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', 'admin', 1, '2019-03-08 15:53:31', 'admin', 1, '2019-03-08 15:53:38', '1658895307@qq.com', '16620375709', 'admin', '5f10a737f989ccb8fe5951ecf68a4e2b', '2019-03-08 15:54:10');
INSERT INTO `user_profile` VALUES ('USR0002', 'user', 1, '2019-03-08 17:27:49', '1', 1, '2019-03-10 18:29:13', '1658895307@qq.com', '1658895307', 'user', '123456', '2019-03-10 18:29:13');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `user_role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ROL0001', 'admin', 1, '2019-03-08 15:56:23', 'admin', 1, '2019-03-08 15:56:30', 'admin', 'A', '2019-03-08 15:56:36');
INSERT INTO `user_role` VALUES ('ROL0002', 'user', 1, '2019-03-08 17:28:56', 'user', 1, '2019-03-08 17:29:01', 'user', 'A', '2019-03-08 17:29:23');

-- ----------------------------
-- Table structure for user_user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_user_role`;
CREATE TABLE `user_user_role`  (
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FKak8topdb5d9ms6ml76er9vd3l`(`user_role_id`) USING BTREE,
  INDEX `FKrgnmroub6nysks1400e0scmev`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user_role
-- ----------------------------
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0001');

SET FOREIGN_KEY_CHECKS = 1;