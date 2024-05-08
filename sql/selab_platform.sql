/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : selab_platform

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 08/05/2024 18:04:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_borrow
-- ----------------------------
DROP TABLE IF EXISTS `book_borrow`;
CREATE TABLE `book_borrow`  (
  `borrow_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键唯一标识',
  `book_id` bigint NOT NULL COMMENT 'book_id',
  `borrow_user` bigint NOT NULL COMMENT '借阅用户id',
  `borrow_duration` int NOT NULL COMMENT '借阅时长(已天为单位)',
  `status` int NOT NULL COMMENT '状态(0为未归还 1为已归还)',
  `borrow_time` datetime NULL DEFAULT NULL COMMENT '借阅时间',
  `return_time` datetime NOT NULL COMMENT '归还时间',
  PRIMARY KEY (`borrow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书籍借阅表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_borrow
-- ----------------------------

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `book_id` int NOT NULL AUTO_INCREMENT COMMENT '数据唯一标识',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书名称',
  `book_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书籍作者',
  `book_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书介绍',
  `price` int NOT NULL COMMENT '价格',
  `owner` bigint NOT NULL COMMENT '书籍拥有者',
  `status` int NOT NULL COMMENT '书籍状态(0为可借阅 1为借阅 2为不可借阅)',
  `create_time` datetime NOT NULL COMMENT '书籍添加时间',
  `update_time` datetime NOT NULL COMMENT '书籍信息修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `book_ref` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书籍编号(定位书籍位置)',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '书籍信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------

-- ----------------------------
-- Table structure for registration_form
-- ----------------------------
DROP TABLE IF EXISTS `registration_form`;
CREATE TABLE `registration_form`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `interviewees_id` int NOT NULL COMMENT '面试者id',
  `interviewees` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '面试者名字',
  `email` int NULL DEFAULT NULL COMMENT '邮箱地址',
  `phone` int NULL DEFAULT NULL COMMENT '联系方式',
  `intent_department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '意向部门',
  `class` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属班级',
  `interview_time` time NULL DEFAULT NULL COMMENT '面试时间',
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '个人介绍',
  `purpose` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '加入目的',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `init_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of registration_form
-- ----------------------------

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `group_id` int NOT NULL COMMENT '唯一标识',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小组名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` int NOT NULL COMMENT '执行修改/添加操作的用户',
  `del_flag` int NULL DEFAULT NULL COMMENT '删除标识(0为正常1为删除)',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '小组信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logout
-- ----------------------------
DROP TABLE IF EXISTS `sys_logout`;
CREATE TABLE `sys_logout`  (
  `logout_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` int NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `admin_id` int NULL DEFAULT NULL COMMENT '操作管理员用户id',
  `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`logout_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logout
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色介绍',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL COMMENT '主键id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `group_id` int NOT NULL COMMENT '用户所属小组编号',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `sex` int NOT NULL COMMENT '用户性别',
  `del_flag` int NOT NULL COMMENT '删除标识( 0 为正常 1 为删除)',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for task_manager
-- ----------------------------
DROP TABLE IF EXISTS `task_manager`;
CREATE TABLE `task_manager`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `publisher_id` int NULL DEFAULT NULL COMMENT '发布者id',
  `updater_id` int NULL DEFAULT NULL COMMENT '更新者id',
  `group_id` int NULL DEFAULT NULL COMMENT '任务小组id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '任务内容',
  `down_status` int NULL DEFAULT NULL COMMENT '完成状态（1/完成，0/未完成）',
  `down_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `report_status` int NULL DEFAULT NULL COMMENT '汇报状态:（ 1/已完成、2/已红温）',
  `report_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '汇报备注信息',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_manager
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
