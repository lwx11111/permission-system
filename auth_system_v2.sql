/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : auth_system_v2

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 12/09/2024 10:10:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_account_change_password
-- ----------------------------
DROP TABLE IF EXISTS `log_account_change_password`;
CREATE TABLE `log_account_change_password`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `old_pass` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '旧密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_LOG_ACCO_REFERENCE_SYS_ACC2`(`account_id` ASC) USING BTREE,
  CONSTRAINT `FK_LOG_ACCO_REFERENCE_SYS_ACC2` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '密码修改日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_account_change_password
-- ----------------------------
INSERT INTO `log_account_change_password` VALUES ('1790095103491055617', '1', '3b3e29f65b5b010906851d7aad5d7859', '2024-05-14 03:01:46', NULL, NULL, NULL);
INSERT INTO `log_account_change_password` VALUES ('1790095612717322241', '1', '0', '2024-05-14 03:03:47', NULL, NULL, NULL);
INSERT INTO `log_account_change_password` VALUES ('1826504730227023873', '1', 'c4ca4238a0b923820dcc509a6f75849b', '2024-08-22 14:20:37', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for log_account_lock
-- ----------------------------
DROP TABLE IF EXISTS `log_account_lock`;
CREATE TABLE `log_account_lock`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `lock_type` int NULL DEFAULT NULL COMMENT '1-锁定 0-解锁',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_60`(`account_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_60` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_account_lock
-- ----------------------------
INSERT INTO `log_account_lock` VALUES ('1790090109043879938', '1', 1, '2024-05-14 02:41:55', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1790091630955581441', '1', 0, '2024-05-14 02:47:58', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826169352387330049', '1', 0, '2024-08-21 16:07:57', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826169400084955137', '1', 0, '2024-08-21 16:08:08', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826170172818411521', '1', 0, '2024-08-21 16:11:12', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826170318566281217', '1', 0, '2024-08-21 16:11:47', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826504701873528833', '1', 0, '2024-08-22 14:20:30', NULL, NULL, NULL);
INSERT INTO `log_account_lock` VALUES ('1826504709645574145', '1', 1, '2024-08-22 14:20:32', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for log_platform_oper
-- ----------------------------
DROP TABLE IF EXISTS `log_platform_oper`;
CREATE TABLE `log_platform_oper`  (
  `log_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户ID',
  `type` int NULL DEFAULT NULL COMMENT '1增加 2删除 3修改 4查询 5其他 6登录',
  `sensitive_level` int NULL DEFAULT NULL COMMENT '4极敏感级 3敏感级 2	较敏感级 1低敏感级',
  `operation` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '操作内容',
  `time` decimal(11, 0) NULL DEFAULT NULL COMMENT '执行时间（毫秒）',
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名',
  `params` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '参数',
  `server_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务端IP',
  `client_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '客户端IP',
  `create_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `success_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0:成功  1:失败',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `FK_Reference_59`(`account_id` ASC) USING BTREE,
  INDEX `FK_Reference_63`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_platform_oper
-- ----------------------------
INSERT INTO `log_platform_oper` VALUES ('1825821025179299841', '', NULL, 4, 1, '生成验证码图片', 450, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"MaksxwZeSlILmuyJAd\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:03:49', '0', NULL, NULL);
INSERT INTO `log_platform_oper` VALUES ('1825821063943057409', '1', '1', 6, 4, '验证码账号登陆', 276, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:03:58', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825821071517970434', '1', '1', 3, 1, '分页查询', 49, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:04:00', '0', NULL, NULL);
INSERT INTO `log_platform_oper` VALUES ('1825825244816953345', '1', '1', 3, 1, '分页查询', 698, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:20:35', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825256099631106', '1', '1', 3, 1, '分页查询', 24, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:20:38', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825310868852738', '1', '1', 3, 1, '分页查询', 34, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:20:51', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825681410445313', '1', '1', 3, 1, '分页查询', 10, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:22:19', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825772435230721', '1', '1', 3, 1, '分页查询', 19, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:22:41', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825875128569858', '1', '1', 3, 1, '分页查询', 7, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:23:05', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825825979000508418', '1', '1', 3, 1, '分页查询', 9, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:23:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825826023795675138', '1', '1', 3, 1, '分页查询', 10, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:23:41', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825826182550081537', '1', '1', 3, 1, '分页查询', 8, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"6\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:24:19', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829084287029249', '1', '1', 3, 1, '分页查询', 23, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:35:51', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829173323714561', '1', '1', 3, 1, '分页查询', 6, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:36:12', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829475221327874', '1', '1', 3, 1, '分页查询', 7, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:37:24', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829485941968898', '1', '1', 3, 1, '分页查询', 11, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:37:26', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829557261914113', '1', '1', 3, 1, '分页查询', 8, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:37:43', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829578820636673', '1', '1', 3, 1, '分页查询', 27, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"2024-08-20 12:00:00\" endTime: \"2024-08-21 12:00:00\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:37:49', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829711905902593', '1', '1', 3, 1, '分页查询', 11, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:20', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829715139710977', '1', '1', 3, 1, '分页查询', 46, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829734664196097', '1', '1', 3, 1, '分页查询', 4, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"2024-08-20 05:38:22\" endTime: \"2024-08-22 12:00:00\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:26', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829745019932674', '1', '1', 3, 1, '分页查询', 6, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: null endTime: \"2024-08-22 12:00:00\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:28', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829775051149314', '1', '1', 3, 1, '分页查询', 6, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:35', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825829788804272130', '1', '1', 3, 1, '分页查询', 7, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:38:39', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825830434659979265', '1', '1', 3, 1, '分页查询', 9, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:41:13', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825830467895644161', '1', '1', 3, 1, '分页查询', 13, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:41:20', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825830495527718913', '1', '1', 3, 1, '分页查询', 8, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:41:27', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825831764174577666', '1', '1', 3, 1, '分页查询', 477, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:46:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825833717629837314', '1', '1', 3, 1, '分页查询', 522, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 17:54:15', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825835816920911873', '1', '1', 3, 1, '分页查询', 480, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:02:36', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825835849523236866', '', NULL, 4, 1, '生成验证码图片', 249, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"qKwkVgXemTbqtrpZVQ\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:02:44', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1825836368169897985', '', NULL, 4, 1, '生成验证码图片', 16, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"gWwPuNXDwbmSrNkkpB\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:04:47', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1825836400558313474', '1', '1', 6, 4, '验证码账号登陆', 185, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:04:55', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825836412667269122', '1', '1', 3, 1, '分页查询', 9, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:04:58', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825836647929974785', '1', '1', 3, 1, '分页查询', 11, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:05:54', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825837083860766721', '1', '1', 3, 1, '分页查询', 10, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"2024-08-21 12:00:00\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:07:38', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1825837102781272065', '1', '1', 3, 1, '分页查询', 57, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: null endTime: \"2024-08-19 12:00:00\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-20 18:07:42', '1', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826057128880762881', '1', '1', 3, 1, '分页查询', 99, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:42:01', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826057745326010370', '1', '1', 3, 1, '分页查询', 31, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:44:28', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826057968253267969', '1', '1', 3, 1, '分页查询', 25, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:45:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826058090101993473', '1', '1', 3, 1, '分页查询', 15, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:45:50', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826058106942124033', '1', '1', 3, 1, '分页查询', 25, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:45:54', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826058233756905473', '1', '1', 3, 1, '分页查询', 9, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:46:24', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826058553996210178', '1', '1', 3, 1, '分页查询', 10, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:47:40', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826059292994826241', '1', '1', 3, 1, '分页查询', 22, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:50:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826059544204275714', '1', '1', 3, 1, '分页查询', 8, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' accountId: \"\" userId: \"\" type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:51:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826059971293474818', '1', '1', 3, 1, '分页查询', 13, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" sensitiveLevel: \"\" operation: \"\" time: \"\" method: \"\" params: \"\" operator: \"\" serverIp: \"\" clientIp: \"\" createTime: \"\" location: \"\" successFlag: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:53:18', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826060037970325505', '1', '1', 3, 1, '分页查询', 7, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operator: \"\" createTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:53:34', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826060149081632769', '1', '1', 3, 1, '分页查询', 7, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operator: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:54:01', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826060485469007874', '1', '1', 3, 1, '分页查询', 12, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operation: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 08:55:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826061953739649025', '1', '1', 3, 1, '分页查询', 11, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operation: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 09:01:11', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826094390301429761', '1', '1', 3, 1, '分页查询', 80, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 11:10:04', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826098687646896130', '1', '1', 3, 1, '分页查询', 18, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operation: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 11:27:09', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826098838818000898', '1', '1', 3, 1, '分页查询', 17, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operation: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 11:27:45', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826116170382577666', '1', '1', 3, 1, '分页查询', 28, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"\" operation: \"\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 12:36:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826175345368322050', '', NULL, 4, 1, '生成验证码图片', 557, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"JQHklQMplaCjkQrnJv\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:31:46', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826175479837708290', '', NULL, 4, 1, '生成验证码图片', 12, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"eaufhWbUXUlqQorvMZ\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:32:18', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826175499668377601', '', NULL, 4, 1, '生成验证码图片', 11, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"gyFDDLVYXlmBgvTXoJ\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:32:22', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826175549706424322', NULL, NULL, 6, 4, '验证码账号登陆', 112, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:32:34', '1', NULL, NULL);
INSERT INTO `log_platform_oper` VALUES ('1826175675443269634', '1', '1', 6, 4, '验证码账号登陆', 141, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:33:04', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826177043918835713', '1', '1', 3, 1, '分页查询', 26, 'org.example.controller.monitor.LogPlatformOperController.selectPage()', ' type: \"6\" createTime: \"\" endTime: \"\" pageIndex: \"1\" pageSize: \"10\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-21 16:38:31', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826498059282980865', '', NULL, 4, 1, '生成验证码图片', 801, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"QGmwtErROJCBqdTTRa\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-22 13:54:07', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826498754719555585', '', NULL, 4, 1, '生成验证码图片', 33, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"WIKCwplrbhPlSXKBLz\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-22 13:56:52', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826498833178206209', '1', '1', 6, 4, '验证码账号登陆', 447, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-22 13:57:11', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1826810354747944962', '', NULL, 4, 1, '生成验证码图片', 825, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"ofulAtZLBcOFYppMSq\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-23 10:35:04', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1826810397152358401', NULL, NULL, 6, 4, '验证码账号登陆', 228, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-23 10:35:14', '1', NULL, NULL);
INSERT INTO `log_platform_oper` VALUES ('1826810491620667394', '1', '1', 6, 4, '验证码账号登陆', 218, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-23 10:35:36', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1829398113379430402', '', NULL, 4, 1, '生成验证码图片', 1009, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"KJbLwdlaqiFVpGNAPE\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-30 13:57:53', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1829398430301040642', '1', '1', 6, 4, '验证码账号登陆', 354, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-08-30 13:59:09', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1830408666222747649', '', NULL, 4, 1, '生成验证码图片', 803, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"xlLQGyYkONHejbHSAl\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-02 08:53:28', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1830408692068048898', '', NULL, 4, 1, '生成验证码图片', 10, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"XoKdOuBIhagpLYsWlz\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-02 08:53:34', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1830409242947936257', '1', '1', 6, 4, '验证码账号登陆', 352, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-02 08:55:46', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1830802161362120706', '', NULL, 4, 1, '生成验证码图片', 1017, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"yfgkOgVnvCdFEVyYxJ\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-03 10:57:05', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1830802242492542977', '1', '1', 6, 4, '验证码账号登陆', 361, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-03 10:57:24', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1831129578639204353', '', NULL, 4, 1, '生成验证码图片', 880, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"fAnqWIwhrpyPsekZCx\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-04 08:38:07', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1831129883598659585', '1', '1', 6, 4, '验证码账号登陆', 464, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-04 08:39:20', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1831191736190308354', '', NULL, 4, 1, '生成验证码图片', 644, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"KkWbBzwhcCHGugpvaR\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-04 12:45:06', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1831193022298095617', '1', '1', 6, 4, '验证码账号登陆', 660, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-04 12:50:13', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1831530739980566529', '', NULL, 4, 1, '生成验证码图片', 614, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"ZkxEcrwNhHzYPtqQdA\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-05 11:12:11', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1831530803104841729', '1', '1', 6, 4, '验证码账号登陆', 346, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-05 11:12:26', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1831855808799956994', '', NULL, 4, 1, '生成验证码图片', 681, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"EOtKtSqIoieENWfmrU\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-06 08:43:54', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1831855891763290113', '1', '1', 6, 4, '验证码账号登陆', 367, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-06 08:44:13', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832215807837368322', '', NULL, 4, 1, '生成验证码图片', 730, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"NIZRTHgUAErrnTWdjB\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 08:34:24', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1832220648701689857', '1', '1', 6, 4, '验证码账号登陆', 694, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 08:53:38', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832247164810629121', '1', '1', 5, 1, '查询账号对应的用户信息', 98, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:39:00', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832247165422997505', '1', '1', 5, 1, '查询账号对应组信息', 86, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:39:00', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832247281441640450', '1', '1', 5, 1, '查询账号对应的用户信息', 9, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:39:28', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832247281642967042', '1', '1', 5, 1, '查询账号对应组信息', 5, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:39:28', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832247281642967043', '1', '1', 5, 1, '查询账号对应角色信息', 7, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:39:28', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250319233421313', '1', '1', 5, 1, '查询账号对应的用户信息', 180, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:51:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250319367639041', '1', '1', 5, 1, '查询账号对应组信息', 5, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:51:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250319367639042', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:51:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250736432451586', '1', '1', 5, 1, '查询账号对应的用户信息', 7, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:12', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250736629583874', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:12', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250736629583875', '1', '1', 5, 1, '查询账号对应组信息', 4, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:12', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250839289368578', '1', '1', 5, 1, '查询账号对应的用户信息', 8, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:36', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250839482306562', '1', '1', 5, 1, '查询账号对应的用户信息', 9, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:36', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250839679438850', '1', '1', 5, 1, '查询账号对应的用户信息', 6, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:36', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250847292100609', '1', '1', 5, 1, '查询账号对应组信息', 9, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:38', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250847292100610', '1', '1', 5, 1, '查询账号对应角色信息', 12, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:38', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250854338531329', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:40', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250854338531330', '1', '1', 5, 1, '查询账号对应组信息', 8, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:40', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250861636620290', '1', '1', 5, 1, '查询账号对应组信息', 10, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:42', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250861636620291', '1', '1', 5, 1, '查询账号对应角色信息', 10, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:42', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250894087950337', '1', '1', 5, 1, '查询账号对应的用户信息', 7, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:49', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250894289276930', '1', '1', 5, 1, '查询账号对应组信息', 4, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:49', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832250894289276931', '1', '1', 5, 1, '查询账号对应角色信息', 4, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 10:53:49', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258580338720769', '1', '1', 5, 1, '查询账号对应的用户信息', 426, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:24:22', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258580745568257', '1', '1', 5, 1, '查询账号对应角色信息', 13, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:24:22', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258580745568258', '1', '1', 5, 1, '查询账号对应组信息', 14, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:24:22', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258866658689026', '1', '1', 5, 1, '查询账号对应的用户信息', 14, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:25:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258866927124481', '1', '1', 5, 1, '查询账号对应角色信息', 8, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:25:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832258866927124482', '1', '1', 5, 1, '查询账号对应组信息', 8, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:25:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832259350496821250', '1', '1', 5, 1, '查询账号对应的用户信息', 17, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:27:26', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832259350702342146', '1', '1', 5, 1, '查询账号对应组信息', 5, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:27:26', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832259350702342147', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:27:26', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832260335608807425', '1', '1', 5, 1, '查询账号对应的用户信息', 45, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:31:20', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832260335810134017', '1', '1', 5, 1, '查询账号对应角色信息', 7, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:31:20', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832260335877242881', '1', '1', 5, 1, '查询账号对应组信息', 9, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:31:20', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261661684154370', '1', '1', 5, 1, '查询账号对应的用户信息', 7, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:36:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261661948395522', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:36:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261661948395523', '1', '1', 5, 1, '查询账号对应组信息', 7, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:36:37', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261781821603841', '1', '1', 5, 1, '查询账号对应的用户信息', 5, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:37:05', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261782014541826', '1', '1', 5, 1, '查询账号对应角色信息', 4, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:37:05', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832261782014541827', '1', '1', 5, 1, '查询账号对应组信息', 5, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:37:05', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262407062306818', '1', '1', 5, 1, '查询账号对应的用户信息', 9, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:39:34', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262407259439105', '1', '1', 5, 1, '查询账号对应组信息', 4, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:39:34', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262407259439106', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:39:34', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262706913099777', '1', '1', 5, 1, '查询账号对应的用户信息', 8, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:40:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262707177340929', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:40:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832262707177340930', '1', '1', 5, 1, '查询账号对应组信息', 7, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 11:40:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832274942796775425', '1', '1', 5, 1, '查询账号对应的用户信息', 68, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:29:23', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832274943056822274', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:29:23', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832274943056822275', '1', '1', 5, 1, '查询账号对应组信息', 6, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:29:23', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275360947912705', '1', '1', 5, 1, '查询账号对应的用户信息', 15, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:03', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275361186988033', '1', '1', 5, 1, '查询账号对应组信息', 7, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:03', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275361186988034', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:03', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275409362763777', '1', '1', 5, 1, '查询账号对应的用户信息', 4, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:14', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275409496981505', '1', '1', 5, 1, '查询账号对应角色信息', 6, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:14', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275409496981506', '1', '1', 5, 1, '查询账号对应组信息', 6, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:14', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275452455043074', '1', '1', 5, 1, '查询账号对应的用户信息', 4, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:25', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275452647981057', '1', '1', 5, 1, '查询账号对应角色信息', 3, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:25', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832275452647981058', '1', '1', 5, 1, '查询账号对应组信息', 3, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:31:25', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276365869596674', '1', '1', 5, 1, '查询账号对应的用户信息', 11, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:02', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276366129643522', '1', '1', 5, 1, '查询账号对应角色信息', 8, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:02', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276366129643523', '1', '1', 5, 1, '查询账号对应组信息', 8, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:02', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276548212768770', '1', '1', 5, 1, '查询账号对应的用户信息', 6, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276548414095361', '1', '1', 5, 1, '查询账号对应组信息', 6, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276548414095362', '1', '1', 5, 1, '查询账号对应角色信息', 8, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:35:46', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276742920749057', '1', '1', 5, 1, '查询账号对应的用户信息', 6, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:36:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276743054966785', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:36:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832276743054966786', '1', '1', 5, 1, '查询账号对应组信息', 7, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:36:32', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832277338910375938', '1', '1', 5, 1, '查询账号对应的用户信息', 45, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:38:54', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832277339107508225', '1', '1', 5, 1, '查询账号对应组信息', 4, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:38:54', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832277339107508226', '1', '1', 5, 1, '查询账号对应角色信息', 5, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:38:54', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832278458911281154', '1', '1', 5, 1, '查询账号对应的用户信息', 443, 'org.example.controller.management.SysAccountController.getUserAndAccountByAccountId()', ' id: \"1826146683977732097\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:43:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832278459251019778', '1', '1', 5, 1, '查询账号对应组信息', 13, 'org.example.controller.management.SysAccountController.listGroupByAccountId()', ' params: ListGroupByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:43:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832278459251019779', '1', '1', 5, 1, '查询账号对应角色信息', 14, 'org.example.controller.management.SysAccountController.getRoleByAccountId()', ' params: GetRoleByAccountIdParams(accountId=1826146683977732097, appId=1)', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:43:21', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832278496982978561', '1', '1', 1, 4, '保存账号权限', 112, 'org.example.controller.management.SysAccountController.savePerm()', ' params: SavePermParams(accountId=1826146683977732097, checkRoles=[1377130454041325569, 1733530847514062850], checkGroups=[1, 1830481496067112962], privateMenus=[1325435968834256897, 1325435968838451201, 1325435968842645506, 1325435968846839809, 1325435968851034113, 1325435968851034114, 1325435968855228418, 1325435968859422721, 1325435968859422722, 1325435968863617025, 1325435968867811330, 1325435968892977153, 1325435968897171457, 1325435968901365761, 1325435968901365762, 1325435968909754369, 1325435968913948674, 1325435968913948675, 1325435968918142977, 1325435968922337281, 1325435968926531585, 1325435968926531586, 1325435968930725890, 1325435968930725891, 1325435968934920194, 1325435968939114498, 1325435968939114499, 1325435968943308802, 1325435968947503105, 1325435968947503106, 1325435968951697410, 1325435968955891713, 1325435968955891714, 1325435968960086018, 1325435968964280322, 1325435968981057537, 1325435968981057538, 1325435968985251841, 1325435968989446145, 1325435968989446146, 1325435968993640450, 1325435968997834753, 1325435969002029058, 1325435969002029059, 1325435969006223361, 1325435969010417665, 1325435969010417666, 1325435969014611969, 1325435969018806274, 1325435969023000578, 1325435969027194882, 1325435969027194883, 1325435969031389186, 1325435969035583490, 1325435969039777794, 1325435969039777795, 1325435969043972097, 1325435969048166402, 1325435969048166403, 1325435969052360705, 1325435969056555009, 1325435969056555010, 1325435969060749314, 1325435969064943618, 1325435969064943619, 1325435969069137922, 1325435969073332225, 1325435969077526530, 1325435969077526531, 1325435969081720834, 1325435969085915137, 1325435969085915138, 1325435969094303746, 1325435969098498050, 1325435969098498051, 1325435969102692354, 1325435969106886658])', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 12:43:30', '0', NULL, 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832306802092433410', '', NULL, 4, 1, '生成验证码图片', 673, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"ZCplFQoaRxYgZGKHlq\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 14:35:59', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1832307041260036098', '1', '1', 6, 4, '验证码账号登陆', 326, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-07 14:36:56', '0', 'admin', 'admin');
INSERT INTO `log_platform_oper` VALUES ('1832942984425914369', '', NULL, 4, 1, '生成验证码图片', 937, 'org.example.controller.management.SysVerificationCodeController.createCodeImage()', ' uuid: \"CZzLRSQVCePvvAXzBJ\" width: \"105\" height: \"35\"', '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-09 08:43:57', '0', NULL, '');
INSERT INTO `log_platform_oper` VALUES ('1832943042022096897', '1', '1', 6, 4, '验证码账号登陆', 363, 'org.example.controller.management.SysAccountController.loginWithCode()', NULL, '192.168.214.1', '0:0:0:0:0:0:0:1', '2024-09-09 08:44:10', '0', 'admin', 'admin');

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account`  (
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号ID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用ID',
  `account_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号名',
  `login_pass` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态0正常1锁定2逻辑删除',
  `login_fail_times` int NOT NULL COMMENT '登录失败次数',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `initial_password_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '初始密码标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `allow_week` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `allow_begin_time` datetime NULL DEFAULT NULL,
  `allow_end_time` datetime NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`) USING BTREE,
  INDEX `FK_Reference_46`(`app_id` ASC) USING BTREE,
  INDEX `FK_Reference_62`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_46` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_62` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户账号信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1', '1', 'admin', '1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', '0', 0, '2024-09-09 08:44:10', '0', '2024-08-22 14:19:45', 'lwx', NULL, NULL, 'http://127.0.0.1:9000/test/e3e8a0912836bf1e0ba1e4d107265db6_.jpg', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_account` VALUES ('1826146683977732097', '1826146683910623233', '11', '1', 'ttt', '123456', '2', 5, NULL, '1', NULL, NULL, 'admin', '2024-08-21 14:37:52', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_account_function_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_function_rela`;
CREATE TABLE `sys_account_function_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `fun_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_SYS_ACCO_REFERENCE_SYS_ACC3`(`account_id` ASC) USING BTREE,
  INDEX `FK_Reference_39`(`fun_id` ASC) USING BTREE,
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`fun_id`) REFERENCES `sys_function` (`fun_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_SYS_ACCO_REFERENCE_SYS_ACC3` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account_function_rela
-- ----------------------------
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434243', '1826146683977732097', '1325435968834256897', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434244', '1826146683977732097', '1325435968838451201', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434245', '1826146683977732097', '1325435968842645506', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434246', '1826146683977732097', '1325435968846839809', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434247', '1826146683977732097', '1325435968851034113', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434248', '1826146683977732097', '1325435968851034114', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434249', '1826146683977732097', '1325435968855228418', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496647434250', '1826146683977732097', '1325435968859422721', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543105', '1826146683977732097', '1325435968859422722', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543106', '1826146683977732097', '1325435968863617025', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543107', '1826146683977732097', '1325435968867811330', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543108', '1826146683977732097', '1325435968892977153', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543109', '1826146683977732097', '1325435968897171457', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543110', '1826146683977732097', '1325435968901365761', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543111', '1826146683977732097', '1325435968901365762', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543112', '1826146683977732097', '1325435968909754369', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543113', '1826146683977732097', '1325435968913948674', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543114', '1826146683977732097', '1325435968913948675', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543115', '1826146683977732097', '1325435968918142977', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543116', '1826146683977732097', '1325435968922337281', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543117', '1826146683977732097', '1325435968926531585', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543118', '1826146683977732097', '1325435968926531586', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543119', '1826146683977732097', '1325435968930725890', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543120', '1826146683977732097', '1325435968930725891', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543121', '1826146683977732097', '1325435968934920194', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543122', '1826146683977732097', '1325435968939114498', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543123', '1826146683977732097', '1325435968939114499', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543124', '1826146683977732097', '1325435968943308802', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543125', '1826146683977732097', '1325435968947503105', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543126', '1826146683977732097', '1325435968947503106', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543127', '1826146683977732097', '1325435968951697410', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543128', '1826146683977732097', '1325435968955891713', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543129', '1826146683977732097', '1325435968955891714', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496714543130', '1826146683977732097', '1325435968960086018', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651969', '1826146683977732097', '1325435968964280322', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651970', '1826146683977732097', '1325435968981057537', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651971', '1826146683977732097', '1325435968981057538', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651972', '1826146683977732097', '1325435968985251841', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651973', '1826146683977732097', '1325435968989446145', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651974', '1826146683977732097', '1325435968989446146', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651975', '1826146683977732097', '1325435968993640450', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651976', '1826146683977732097', '1325435968997834753', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651977', '1826146683977732097', '1325435969002029058', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651978', '1826146683977732097', '1325435969002029059', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651979', '1826146683977732097', '1325435969006223361', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651980', '1826146683977732097', '1325435969010417665', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651981', '1826146683977732097', '1325435969010417666', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651982', '1826146683977732097', '1325435969014611969', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651983', '1826146683977732097', '1325435969018806274', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651984', '1826146683977732097', '1325435969023000578', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651985', '1826146683977732097', '1325435969027194882', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651986', '1826146683977732097', '1325435969027194883', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651987', '1826146683977732097', '1325435969031389186', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651988', '1826146683977732097', '1325435969035583490', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651989', '1826146683977732097', '1325435969039777794', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651990', '1826146683977732097', '1325435969039777795', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651991', '1826146683977732097', '1325435969043972097', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651992', '1826146683977732097', '1325435969048166402', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651993', '1826146683977732097', '1325435969048166403', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651994', '1826146683977732097', '1325435969052360705', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651995', '1826146683977732097', '1325435969056555009', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651996', '1826146683977732097', '1325435969056555010', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651997', '1826146683977732097', '1325435969060749314', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651998', '1826146683977732097', '1325435969064943618', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781651999', '1826146683977732097', '1325435969064943619', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652000', '1826146683977732097', '1325435969069137922', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652001', '1826146683977732097', '1325435969073332225', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652002', '1826146683977732097', '1325435969077526530', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652003', '1826146683977732097', '1325435969077526531', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652004', '1826146683977732097', '1325435969081720834', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652005', '1826146683977732097', '1325435969085915137', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652006', '1826146683977732097', '1325435969085915138', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652007', '1826146683977732097', '1325435969094303746', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652008', '1826146683977732097', '1325435969098498050', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652009', '1826146683977732097', '1325435969098498051', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652010', '1826146683977732097', '1325435969102692354', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_function_rela` VALUES ('1832278496781652011', '1826146683977732097', '1325435969106886658', NULL, '2024-09-07 12:43:30');

-- ----------------------------
-- Table structure for sys_account_role_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role_rela`;
CREATE TABLE `sys_account_role_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色ID',
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_SYS_ACCO_REFERENCE_SYS_ROL2`(`role_id` ASC) USING BTREE,
  INDEX `FK_SYS_ACCO_REFERENCE_SYS_ACC2`(`account_id` ASC) USING BTREE,
  CONSTRAINT `FK_SYS_ACCO_REFERENCE_SYS_ACC2` FOREIGN KEY (`account_id`) REFERENCES `sys_account` (`account_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_SYS_ACCO_REFERENCE_SYS_ROL2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '账号角色对应关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_account_role_rela
-- ----------------------------
INSERT INTO `sys_account_role_rela` VALUES ('1', '1377130454041325569', '1', NULL, NULL);
INSERT INTO `sys_account_role_rela` VALUES ('1832278496584519682', '1377130454041325569', '1826146683977732097', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_account_role_rela` VALUES ('1832278496584519683', '1733530847514062850', '1826146683977732097', NULL, '2024-09-07 12:43:30');

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app`  (
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用ID',
  `app_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用编码',
  `app_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用名称',
  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `fixed` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0-不固定1-固定',
  `secret_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接入密码',
  `app_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接入密钥',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`app_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '应用项目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', 'delivery_system', 'delivery', NULL, NULL, 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, NULL, NULL, '1');
INSERT INTO `sys_app` VALUES ('2', 'food_app', 'food', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
  `company_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司名',
  `abbreviation` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '缩写',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司描述',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态 0-正常 1-删除',
  `postal_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮政编码',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES ('10360', NULL, '中国通信建设第四工程局有限公司', '代维公司', '代维公司', '管理员', '2016-03-31 00:00:00', '管理员', '2016-03-31 00:00:00', '0', '1', '1');

-- ----------------------------
-- Table structure for sys_credential_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_credential_rule`;
CREATE TABLE `sys_credential_rule`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rule_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '规则名称',
  `user_define` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户自定义 1自定义 0 不自定义',
  `user_define_exp` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '自定义表达式',
  `specific_char_num` smallint NULL DEFAULT NULL COMMENT '特殊字符个数',
  `big_letter_num` smallint NULL DEFAULT NULL COMMENT '大写字母个数',
  `small_letter_num` smallint NULL DEFAULT NULL COMMENT '小写字母个数',
  `number_num` smallint NULL DEFAULT NULL COMMENT '数字个数',
  `min_length` smallint NULL DEFAULT NULL COMMENT '最小长度',
  `active_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态 0-不生效 1-生效',
  `history_repeat_times` int NULL DEFAULT NULL COMMENT '历史密码不重复次数 0无限制',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统密码规则' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_credential_rule
-- ----------------------------
INSERT INTO `sys_credential_rule` VALUES ('1292073262721875970', '默认规则', '0', NULL, 1, 1, 1, 1, 8, '1', 0, 'admin', '2020-08-08 07:20:46', 'admin', '2020-08-08 07:20:46', '1');

-- ----------------------------
-- Table structure for sys_dataset
-- ----------------------------
DROP TABLE IF EXISTS `sys_dataset`;
CREATE TABLE `sys_dataset`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ds_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源id',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用id',
  `dset_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `dset_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `fetch_data_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '获取数据内容',
  `fetch_data_type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '获取数据方式 1 REST接口 0数据源',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `res_table` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表名',
  `primarykey_col` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主键列名',
  `resname_col` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '显示列名',
  `where_clause` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '过滤条件',
  `order_col` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排序列名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dset_code`(`app_id` ASC, `dset_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据集管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dataset
-- ----------------------------
INSERT INTO `sys_dataset` VALUES ('1831158231175553026', NULL, '1', '1', '1', NULL, '1', NULL, NULL, NULL, NULL, '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  `ds_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源名称',
  `ds_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源url',
  `driver_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源驱动名',
  `ds_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源用户名',
  `ds_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据源密码',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据源管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_datasource
-- ----------------------------
INSERT INTO `sys_datasource` VALUES ('1831144532599656449', NULL, '1', '1', '1', '1', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `dict_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典编码',
  `dict_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典名称',
  `dict_val` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典值',
  `dict_order` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排序编码',
  `dict_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`, `dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function`  (
  `fun_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `fun_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类别 0-菜单 1-按钮      ',
  `fun_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能编码',
  `fun_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `extend_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拓展链接',
  `query` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `perm_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限标识',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上级路由id',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用标识',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态 1正常 0禁用',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `order_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排序编码',
  `leaf` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `inherent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`fun_id`) USING BTREE,
  INDEX `FK_AUTH_MENU_PARENT`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '功能路由表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('1292353706138877953', '0', '100', '系统管理', '#', '', '', '', '', '0', '1', '1', 'admin', '2020-08-08 12:55:09', '管理员', '2020-09-20 09:07:49', '100', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968834256897', '0', '100', '权限管理', '#', '', '', '', '', '0', '1', '1', 'admin', '2020-11-06 13:52:15', '管理员', '2021-11-02 06:56:51', '99999', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968838451201', '0', '100010', '用户管理', '/management/user/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100010', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968842645506', '1', NULL, '锁定/解锁', '#', NULL, NULL, '', 'auth:user_lock', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968846839809', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:user_add', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968851034113', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:user_del', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968851034114', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:user_edit', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968855228418', '1', NULL, '复制用户', NULL, NULL, NULL, '', 'auth:user_copy', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968859422721', '1', NULL, '添加账号', NULL, NULL, NULL, '', 'auth:user_add_account', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968859422722', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:user_query', '1325435968838451201', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968863617025', '0', '100020', '账号管理', '/management/account/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100020', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968867811330', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:account_add', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968892977153', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:account_edit', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968897171457', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:account_del', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968901365761', '1', NULL, '锁定/解锁', NULL, NULL, NULL, '', 'auth:account_lock', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968901365762', '1', NULL, '重置密码', NULL, NULL, NULL, '', 'auth:account_reset_pass', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968909754369', '1', NULL, '修改密码', NULL, NULL, NULL, '', 'auth:account_modify_pass', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968913948674', '1', NULL, '设置权限', NULL, NULL, NULL, '', 'auth:account_perm', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968913948675', '1', NULL, '导出', NULL, NULL, NULL, '', 'auth:account_export', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968918142977', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:account_query', '1325435968863617025', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968922337281', '0', '100030', '角色管理', '/management/role/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100030', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968926531585', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:role_add', '1325435968922337281', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968926531586', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:role_edit', '1325435968922337281', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968930725890', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:role_del', '1325435968922337281', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968930725891', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:role_query', '1325435968922337281', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968934920194', '0', '100040', '组管理', '/management/group/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100040', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968939114498', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:group_add', '1325435968934920194', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968939114499', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:group_edit', '1325435968934920194', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968943308802', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:group_del', '1325435968934920194', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968947503105', '1', NULL, '复制', NULL, NULL, NULL, '', 'auth:group_copy', '1325435968934920194', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968947503106', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:group_query', '1325435968934920194', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968951697410', '0', '100050', '功能管理', '/management/function/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100050', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968955891713', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:function_add', '1325435968951697410', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968955891714', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:function_edit', '1325435968951697410', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968960086018', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:function_del', '1325435968951697410', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968964280322', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:function_query', '1325435968951697410', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968981057537', '0', '100060', '公司管理', '/management/company/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100060', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968981057538', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:company_add', '1325435968981057537', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968985251841', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:company_edit', '1325435968981057537', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968989446145', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:company_del', '1325435968981057537', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968989446146', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:company_query', '1325435968981057537', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435968993640450', '0', '100070', '部门管理', '/management/org/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100070', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435968997834753', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth_org_add', '1325435968993640450', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969002029058', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth_org_edit', '1325435968993640450', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969002029059', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth_org_del', '1325435968993640450', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969006223361', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:org_query', '1325435968993640450', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969010417665', '0', '100080', '数据源管理', '/management/dataSource/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100080', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435969010417666', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:datasource_add', '1325435969010417665', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969014611969', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:datasource_edit', '1325435969010417665', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969018806274', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:datasource_del', '1325435969010417665', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969023000578', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:datasource_query', '1325435969010417665', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969027194882', '0', '100081', '数据集管理', '/management/sysDataset/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100081', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435969027194883', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:dataset_add', '1325435969027194882', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969031389186', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:dataset_edit', '1325435969027194882', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969035583490', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:dataset_del', '1325435969027194882', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969039777794', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:dataset_query', '1325435969027194882', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969039777795', '0', '100090', '密码规则', '/management/credentialRule/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100090', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969043972097', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:rule_add', '1325435969039777795', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969048166402', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:rule_edit', '1325435969039777795', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969048166403', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:rule_del', '1325435969039777795', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969052360705', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:rule_query', '1325435969039777795', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969056555009', '0', '100100', '公告管理', '/management/sysNotice/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100100', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435969056555010', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:notice_add', '1325435969056555009', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969060749314', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:notice_edit', '1325435969056555009', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969064943618', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:notice_del', '1325435969056555009', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969064943619', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:notice_query', '1325435969056555009', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969069137922', '0', '100110', '系统配置', '/management/sysSetting/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100110', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969073332225', '1', NULL, '新增', NULL, NULL, NULL, '', 'auth:setting_add', '1325435969069137922', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969077526530', '1', NULL, '修改', NULL, NULL, NULL, '', 'auth:setting_edit', '1325435969069137922', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969077526531', '1', NULL, '删除', NULL, NULL, NULL, '', 'auth:setting_del', '1325435969069137922', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969081720834', '1', NULL, '查询', NULL, NULL, NULL, '', 'auth:setting_query', '1325435969069137922', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', NULL, '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969085915137', '0', '100120', '应用管理', '/management/sysApp/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100120', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435969085915138', '0', '100130', '字典管理', '/management/sysDict/index', NULL, NULL, '', NULL, '1325435968834256897', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100130', '0', NULL);
INSERT INTO `sys_function` VALUES ('1325435969090109442', '0', '100140', '默认功能', '/manager/default-function/list', NULL, NULL, '', NULL, '1325435968834256897', '1', '0', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '100140', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969094303746', '0', '200', '权限系统监控', '#', '', '', '', '', '0', '1', '1', 'admin', '2020-11-07 17:52:15', '管理员', '2021-01-17 22:29:00', '99991', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969098498050', '0', '200010', '登录日志', '/monitor/loginLogs/index', NULL, NULL, '', NULL, '1325435969094303746', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '200010', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969098498051', '0', '200020', '操作日志', '/monitor/logs/index', NULL, NULL, '', NULL, '1325435969094303746', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '200020', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969102692354', '0', '200030', '密码日志', '/monitor/passwordLogs/index', NULL, NULL, '', NULL, '1325435969094303746', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '200030', '1', NULL);
INSERT INTO `sys_function` VALUES ('1325435969106886658', '0', '200040', '锁定日志', '/monitor/lockLogs/index', NULL, NULL, '', NULL, '1325435969094303746', '1', '1', 'admin', '2020-11-08 07:52:15', 'admin', '2020-11-08 07:52:15', '200040', '1', NULL);

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`  (
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  `group_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组编码',
  `group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组名称',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组描述',
  `order_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排序编码',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('1', '1', '1', '1', '1', '1', '1', '2024-09-02 08:58:57', '1', '2024-09-02 08:58:59');
INSERT INTO `sys_group` VALUES ('1830481496067112962', '1', '2', '2', '2', '2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_group` VALUES ('1832954064959275009', NULL, '3', '3', '3', '3', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_group_dataset_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_dataset_rela`;
CREATE TABLE `sys_group_dataset_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `dset_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据集ID',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组ID',
  `data_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组数据集关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_group_dataset_rela
-- ----------------------------
INSERT INTO `sys_group_dataset_rela` VALUES ('1832954065072521223', '1831158231175553026', '1832954064959275009', '', 'admin', '2024-09-09 09:27:58');

-- ----------------------------
-- Table structure for sys_group_function_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_function_rela`;
CREATE TABLE `sys_group_function_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组ID',
  `fun_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组功能关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_group_function_rela
-- ----------------------------
INSERT INTO `sys_group_function_rela` VALUES ('1832954065072521218', '1832954064959275009', '1325435969094303746', 'admin', '2024-09-09 09:27:58');
INSERT INTO `sys_group_function_rela` VALUES ('1832954065072521219', '1832954064959275009', '1325435969098498050', 'admin', '2024-09-09 09:27:58');
INSERT INTO `sys_group_function_rela` VALUES ('1832954065072521220', '1832954064959275009', '1325435969098498051', 'admin', '2024-09-09 09:27:58');
INSERT INTO `sys_group_function_rela` VALUES ('1832954065072521221', '1832954064959275009', '1325435969102692354', 'admin', '2024-09-09 09:27:58');
INSERT INTO `sys_group_function_rela` VALUES ('1832954065072521222', '1832954064959275009', '1325435969106886658', 'admin', '2024-09-09 09:27:58');

-- ----------------------------
-- Table structure for sys_group_members
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_members`;
CREATE TABLE `sys_group_members`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组成员关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_group_members
-- ----------------------------
INSERT INTO `sys_group_members` VALUES ('1830481496117444609', '1', '1830481496067112962', 'admin', '2024-09-02 13:42:52');
INSERT INTO `sys_group_members` VALUES ('1832278496584519684', '1826146683977732097', '1', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_group_members` VALUES ('1832278496647434242', '1826146683977732097', '1830481496067112962', NULL, '2024-09-07 12:43:30');
INSERT INTO `sys_group_members` VALUES ('1832954065009606657', '1', '1832954064959275009', 'admin', '2024-09-09 09:27:58');
INSERT INTO `sys_group_members` VALUES ('1832954065009606658', '1826146683977732097', '1832954064959275009', 'admin', '2024-09-09 09:27:58');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  `notice_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告标题',
  `notice_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告内容',
  `influence_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '影响范围',
  `notice_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告原因',
  `contact_person` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系人姓名',
  `contact_person_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系人电话',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公告管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2024-09-06 13:42:24', '1', '2024-09-06 13:42:27');
INSERT INTO `sys_notice` VALUES ('1831947601256243202', NULL, '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1831963536415264769', NULL, '1', '1', '1', '1', '1', '1', '1', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice_group_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_group_rela`;
CREATE TABLE `sys_notice_group_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `notice_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公共ID',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公共接受组' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice_group_rela
-- ----------------------------
INSERT INTO `sys_notice_group_rela` VALUES ('1831963536532705282', '1831963536415264769', '1');
INSERT INTO `sys_notice_group_rela` VALUES ('1831963536532705283', '1831963536415264769', '1830481496067112962');

-- ----------------------------
-- Table structure for sys_notice_receiver
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_receiver`;
CREATE TABLE `sys_notice_receiver`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `notice_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '通知ID',
  `account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号ID',
  `read_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否已读',
  `read_time` datetime NULL DEFAULT NULL COMMENT '已读时间',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_SYS_NOTI_REFERENCE_SYS_NOTI`(`notice_id` ASC) USING BTREE,
  CONSTRAINT `FK_SYS_NOTI_REFERENCE_SYS_NOTI` FOREIGN KEY (`notice_id`) REFERENCES `sys_notice` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知接收表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice_receiver
-- ----------------------------
INSERT INTO `sys_notice_receiver` VALUES ('1831963536729837569', '1831963536415264769', '1', '0', NULL, NULL, '2024-09-06 15:51:58');
INSERT INTO `sys_notice_receiver` VALUES ('1831963536729837570', '1831963536415264769', '1826146683977732097', '0', NULL, NULL, '2024-09-06 15:51:58');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门编码',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司ID',
  `org_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门名称',
  `style` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门形态 1公司 2部门 3外部组织',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态 0正常 1锁定',
  `org_manager` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门负责人',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `parent_org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级编码',
  `org_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门全路径',
  `org_order` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排序编码',
  `responsibility` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '业务职责',
  `supervisor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上级主管领导的UID',
  `telephone_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门电话',
  `facsimile_telephone_number` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '传真电话',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`org_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('10363', '10360', '中通四局伊犁维护中心', '1', '0', NULL, '', '0', NULL, NULL, NULL, NULL, '13598086673', NULL, '管理员', '2016-03-31 00:00:00', '管理员', '2016-03-31 00:00:00', '1');
INSERT INTO `sys_org` VALUES ('10420', '10360', '中通四局伊犁维护中心伊宁市维护站', '1', '0', NULL, '', '10363', NULL, NULL, NULL, NULL, '13598086673', NULL, '管理员', '2016-04-01 00:00:00', '管理员', '2016-04-01 00:00:00', '1');
INSERT INTO `sys_org` VALUES ('10422', '10360', '中通四局伊犁维护中心霍城县维护站', '1', '0', NULL, '', '10363', NULL, NULL, NULL, NULL, '13598086673', NULL, '管理员', '2016-04-01 00:00:00', '管理员', '2016-04-01 00:00:00', '1');
INSERT INTO `sys_org` VALUES ('10607', '10360', '中通四局克州维护中心阿图什市维护站', '1', '0', NULL, '', '14790', NULL, NULL, NULL, NULL, '123456', NULL, '管理员', '2016-04-01 00:00:00', '管理员', '2016-04-01 00:00:00', '1');
INSERT INTO `sys_org` VALUES ('14790', '10360', '中通四局克拉玛依维护中心', '1', '0', NULL, '', '0', NULL, NULL, NULL, NULL, '11', NULL, '管理员', '2018-12-24 00:00:00', '管理员', '2018-12-24 00:00:00', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色编码',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用标识',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `order_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1377130454041325569', 'super', '1', '超级管理员', '最高角色，拥有一切权限', 'admin', '2021-03-31 00:27:41', '管理员', '2021-08-30 04:42:17', NULL);
INSERT INTO `sys_role` VALUES ('1733530847514062850', 'management', '1', '管理员', '', 'admin', '2023-12-10 00:55:36', 'admin', '2023-12-23 21:02:19', NULL);
INSERT INTO `sys_role` VALUES ('1832340435142443010', '1', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dataset_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dataset_rela`;
CREATE TABLE `sys_role_dataset_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `dset_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据集id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色id',
  `data_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色数据集关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dataset_rela
-- ----------------------------
INSERT INTO `sys_role_dataset_rela` VALUES ('1832340435209551878', '1831158231175553026', '1832340435142443010', '', '2024-09-07 16:49:38', 'admin');

-- ----------------------------
-- Table structure for sys_role_function_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function_rela`;
CREATE TABLE `sys_role_function_rela`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色ID',
  `fun_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能ID',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色功能关系对应表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_function_rela
-- ----------------------------
INSERT INTO `sys_role_function_rela` VALUES ('1733492248064077825', '1733482683901718529', '1733490336858157058', 'admin', '2023-12-09 22:22:13');
INSERT INTO `sys_role_function_rela` VALUES ('1733492270071590913', '1733485391450779649', '1733490752236859393', 'admin', '2023-12-09 22:22:19');
INSERT INTO `sys_role_function_rela` VALUES ('1733492270071590914', '1733485391450779649', '1733490911410696194', 'admin', '2023-12-09 22:22:19');
INSERT INTO `sys_role_function_rela` VALUES ('1733492270071590915', '1733485391450779649', '1733491050766446593', 'admin', '2023-12-09 22:22:19');
INSERT INTO `sys_role_function_rela` VALUES ('1733492289625436162', '1733485597898616833', '1733490492005462018', 'admin', '2023-12-09 22:22:23');
INSERT INTO `sys_role_function_rela` VALUES ('173845569921499144', '1733485391450779649', '1738545111111111114', 'admin', '2024-03-05 21:44:50');
INSERT INTO `sys_role_function_rela` VALUES ('173845569921499145', '1733485391450779649', '1738545111111111115', 'admin', '2024-03-07 13:38:33');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499137', '1733530847514062850', '1733529751982829570', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499138', '1733530847514062850', '1733530115448631297', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499139', '1733530847514062850', '1733530308583747586', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499140', '1733530847514062850', '1733530423532843009', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499141', '1733530847514062850', '1733530518919704578', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499142', '1733530847514062850', '1738544925558964225', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499143', '1733530847514062850', '1738545111123361793', 'admin', '2023-12-23 21:02:19');
INSERT INTO `sys_role_function_rela` VALUES ('1738545569921499144', '1733530847514062850', '1738545111123361794', 'admin', '2024-03-11 16:23:39');
INSERT INTO `sys_role_function_rela` VALUES ('1832340435163414530', '1832340435142443010', '1325435969094303746', 'admin', '2024-09-07 16:49:38');
INSERT INTO `sys_role_function_rela` VALUES ('1832340435209551874', '1832340435142443010', '1325435969098498050', 'admin', '2024-09-07 16:49:38');
INSERT INTO `sys_role_function_rela` VALUES ('1832340435209551875', '1832340435142443010', '1325435969098498051', 'admin', '2024-09-07 16:49:38');
INSERT INTO `sys_role_function_rela` VALUES ('1832340435209551876', '1832340435142443010', '1325435969102692354', 'admin', '2024-09-07 16:49:38');
INSERT INTO `sys_role_function_rela` VALUES ('1832340435209551877', '1832340435142443010', '1325435969106886658', 'admin', '2024-09-07 16:49:38');

-- ----------------------------
-- Table structure for sys_role_inherit
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_inherit`;
CREATE TABLE `sys_role_inherit`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色ID',
  `inherit_role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '继承角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色继承表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_inherit
-- ----------------------------
INSERT INTO `sys_role_inherit` VALUES ('1832340435276660738', '1832340435142443010', '1377130454041325569');
INSERT INTO `sys_role_inherit` VALUES ('1832340435276660739', '1832340435142443010', '1733530847514062850');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `setting_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设置编码',
  `setting_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设置名称',
  `setting_val` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设置值',
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '应用ID',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设置描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES ('1', 'login-fail-max-times', NULL, '5', '1', NULL);
INSERT INTO `sys_setting` VALUES ('1831968957553201154', '1', '1', '1', NULL, '1');
INSERT INTO `sys_setting` VALUES ('2', 'password-encrypt', NULL, 'aes', '1', NULL);

-- ----------------------------
-- Table structure for sys_token_black_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_token_black_list`;
CREATE TABLE `sys_token_black_list`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `token` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'token黑名单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_token_black_list
-- ----------------------------
INSERT INTO `sys_token_black_list` VALUES ('1767755366130552834', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMDI5OTg0NCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEwMzg2MjQ0LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.KASg7FTUtukou6NSlMhcuhVXneZAiOR22wqDv79XjjoK4pxIsJtmLY-DKeLNBil_pwhgpPFD9wzZf6hUy4DMzUnh-ep2-A1aY3PMuvEIkdMcz1te7SrzzFre3NPzM17DpaePHnSrX9wuLNhf07_JxjICH_nRV3eXMTxdWTI0wTqNiD1dha6vkgHV12Qb5O9iUrIOI1b8fF6lH3tNtli_b6oCBxrWkeqpJl9UnQ5UITajcfXfTvpHaGJfwmDBC6kNTpXHw8Q-eULgrjRZ8Tca3_3BCf553mYnxdVJks-U-u0l3aLkHd5Kqs02hh-y6XE1YhQPHE36BiZtub9FM0pe3g', '2024-03-13 11:31:37');
INSERT INTO `sys_token_black_list` VALUES ('1767756753279160322', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1NTQ0NzI0MDAwMzU4NiIsIm5iZiI6MTcxMDMwMDczNCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTU0NDczMDcxMTI0NDkiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0IiwibG9naW5fYWNjb3VudF9uYW1lIjoidGVzdCIsImV4cCI6MTcxMDM4NzEzNCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJ0ZXN0In0.LafgXknF1QaFgmfUi0EADtwEybPftzcAtIuZMAnIgq2hWR1dOd2qePF0-elIAZmBnB-QRAM1-dK8t_QGXK282hWth5LAxZCgML8hC6IE-wfYJU_NuHESAoDxa7KpJP_82-t8cYORQP7c7HU26uzxFAQgYqAhILK-KR9GzN39-U-BOKkqhGFCCVL8bGsBGwZ9Emuab1CgCA5qG_JNLioJZpYBZJ0UDNbwaT52Pkyu4q5zXHukSQe-tYVYuCxiMu8jf9Vl6VTatSBbfJmYMUuEZWWsGINNGcVvaxQcsOsO4ZiaSnDo_hnIGA9aZ2z0e4zJcrAAIUTpFsGI5FOr5uONYA', '2024-03-13 11:37:08');
INSERT INTO `sys_token_black_list` VALUES ('1767757117399273473', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1NTQ0NzI0MDAwMzU4NiIsIm5iZiI6MTcxMDMwMTAzNSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTU0NDczMDcxMTI0NDkiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0IiwibG9naW5fYWNjb3VudF9uYW1lIjoidGVzdCIsImV4cCI6MTcxMDM4NzQzNSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJ0ZXN0In0.aRCxAHB-bhw7d896On55_tKkTxGrSLFUqdw-x563nxHD0W9ohy7ghaDnNnjQn4qDXqqe38M0ir0z2AeszizNrIAoQD4VnzHAdtaRDtUDzLsGUQGFivTxV3Ed9K9_QomA_pREb0NBRgGMitRQjwWr9wIFTJvwht2JExagAGIztrMaQzO28jKYenVjj0Bij6uzUkkdrFJ_51-MuPlkuMHZkFAWOWxNhd8MpRX_-DYNIDlKv4LB4CUG8rFqxNFwzCZJTunWx6ODZugoBPQfjx-1dlPMyvKBBNnrw4FA07nDYltf8IWbmo0XBRfhJjMaVVijzzTfMTDHEV7NiThe1KBVfA', '2024-03-13 11:38:35');
INSERT INTO `sys_token_black_list` VALUES ('1767757667708735489', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1NzE3NDQwODI1MzQ0MSIsIm5iZiI6MTcxMDMwMTEzNCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTcxNzQ0MDgyNTM0NDIiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0MSIsImxvZ2luX2FjY291bnRfbmFtZSI6InRlc3QxIiwiZXhwIjoxNzEwMzg3NTM0LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6InRlc3QxIn0.W9807PK9cfx7sHCOT2gLIzXsLOFi-cS_aCcTPu9La3ud-iSnHU0lzAO9x-PobexwpE4WNtQMHJTw_yZsVodZ6NnqmhWOccUmlMol3Cx_QQ0SAcdZYEjK49bn1ihesoaC6Me2DeAlNofpdtEbrkb44DvmsfhQuxuHptrgzBf5R38D_jUQXIDVpWtbUO_O-WRl8hCDNv7iGsH3kJBuAzn_wfhMldGkl6Q_UyuMLwFfcFVYtyeCzqC2B0mUpnuK1nTVZ0AOvMqD7Nlga6tMQSNOHc53nI55skQtk4KNZBqQJn5AdvMW1TL-t4W56IWQV-dShA2qe-Q0t3qfcJbstjbwfw', '2024-03-13 11:40:46');
INSERT INTO `sys_token_black_list` VALUES ('1767758545786277890', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1NzE3NDQwODI1MzQ0MSIsIm5iZiI6MTcxMDMwMTI1NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTcxNzQ0MDgyNTM0NDIiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0MSIsImxvZ2luX2FjY291bnRfbmFtZSI6InRlc3QxIiwiZXhwIjoxNzEwMzg3NjU0LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6InRlc3QxIn0.EhprWWu6IQBA28XpJiummvEHdLTMkCi0qP5nihhq8MYclXlC7HXpiNpNJRtyGCMJtyqCd5e1Ue2RddO8oaTOaiIFNo1zuDtVvagih4xDz-51V5cEk5WivadKJe7jaWmwX3OSwyOwyeexLPEYEeeEN_FFKcrqwXnsUN3qwLbsEtt1ZfGKUhghghbRtZ_GFD-LRG5Na2tDA7BlJklVLNGus5Pmx1LZu3rxtLrCO1PnkLSgweWglz6-5YeGwspF0wkcfUja0rPvuseWCWfGqb1zVAcKkbuA4bnRTLu0KGjZHCDyDFrcZ2xa9h4XVb9sC7wyQPQwecp3N2OACE9vRK0g6w', '2024-03-13 11:44:15');
INSERT INTO `sys_token_black_list` VALUES ('1767759545125343234', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1ODYyMzIxNzMyNDAzMyIsIm5iZiI6MTcxMDMwMTQ4MSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTg2MjMyODAyMzg1OTQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0MiIsImxvZ2luX2FjY291bnRfbmFtZSI6InRlc3QyIiwiZXhwIjoxNzEwMzg3ODgxLCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6InRlc3QyIn0.GG2FAwxm_TNkAttrmoqh2rw_DUnwzujtvN9yEcN9meAtb95JKX717xMIlimB-L2opBqAhR6n2bvV0O5EFnOm2XOHtqodSejpmoqAzd7YdmBcWKNw0PbzzyXpiCvDAndfaHOBLh81WTwYzBREPjrkJQ41W9W8WxiQxWkU5YJRU6csg-lj4F6lNsLV9CCtvrqrdTwge3CyG53J2L4Spm_fpxhQBXGMzUuTtxnWdkruCuknr8_2L8xbsT5Fcwz3KFbdLmtEnfZmqSF4qIIi3lP2l5aya3yMH8sNt2VzqousgI1CgrfRgxEGH2rpGTVU05sNvBc7efK6nJjhoyPcCd0IDg', '2024-03-13 11:48:14');
INSERT INTO `sys_token_black_list` VALUES ('1767761832375783426', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMDMwMTcxNSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTAzODgxMTUsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.NKDX4dTDs-Lb789xpEXgRB-E3CzteYgT0guVrVWipxnQcSE73U8hFdI2mf-1BA_jp1GT_LQrOF6kFPr6RAFvld6uVwNILS92Qd8_M1q2hOgUXEsUahVHX6yy-9PMyqJ6M0l-hMomswp715xXx9O38ZlqaSFPStR2xBTW-pXeTj7EZkDmvGZnUTfmY1fX9Ev805X8sASRsCH-9ByTPK14SpV1-lsxswJjDB_5QDrQzEvFCqxJ8-0bFt0aFDfijKmr6-XNbvMasZSePWrT3ew52TgNZLkd7tShlZfwFxG6CK5-S1wcEpm2fdsj9agKDjO6hIEyyVZFeRqOFX842CuJUQ', '2024-03-13 11:57:19');
INSERT INTO `sys_token_black_list` VALUES ('1767763483614547969', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MjA1MzAwMDM2ODEzMCIsIm5iZiI6MTcxMDMwMjMwMywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjIwNTMwMDAzNjgxMzEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM4ODcwMywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.JgBU_hpnNdfY_E7zctMwWuCUjO2S2g4_wvoarrQH7ht38WBYDaN1WAaoS5-xaSzrcGSTMsfGF2rw7xc5EwYwaUkkOF79j7z_UYIK7YqCIskuB06NNeptLLyGjKnVVTEQVk3K2mGHqCbXB0FIXRCi5FsatNOLrKjxMgELj3rgPB4r1obLdBFoCuleHp_VlDPXv-OrSxfvSccdyWPAbbU37FoNukpru-W1qNJeyxmHtEQsIdgI16q-_O6WD0w8u0DIu9mIWoycMNgc4bXsiplh6QztEpyFhdDDoopOo9F12g3KPllCUiKfiYrHipTa9FtDVQC7rSp7_KIwitiFEEx2oA', '2024-03-13 12:03:53');
INSERT INTO `sys_token_black_list` VALUES ('1767763650245857282', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzU0ODU0NjU2ODE5MyIsIm5iZiI6MTcxMDMwMjY1NSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM1NDg2MTM2NzcwNTgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0MiIsImxvZ2luX2FjY291bnRfbmFtZSI6InRlc3QyIiwiZXhwIjoxNzEwMzg5MDU1LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6InRlc3QyIn0.fynN7crw-kxjXmAwxsu2fzGrkvCU27BcrOs2w48PIEJcpWvJTqf74CocB3MkqJp0Cntqq4VG2sj7q7IiYWc4tEfTMsK7gSiJTK-lvYIEtDXwtKIEgOCAnW7zRMimF12DHzvIrdGrh3k3sMoq5qCzvlmdMR967o2LrhSJrY63mutkBKgHIPWO20doAWwiNKADdMEZ8piky6PpPzDKvcWzRS2zOCG1ExsTncS6Rw79PWwtrOLQescH0qpSq4GUDNaTq8git6k-tpvWPfAYQ5tYy1oCe7TElUNxRP5e64G_uLLPj7lO4jKFHAegFuDkswK2ef5OfI8qfvFF8etQC5IDDw', '2024-03-13 12:04:32');
INSERT INTO `sys_token_black_list` VALUES ('1767763793326149634', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MjA1MzAwMDM2ODEzMCIsIm5iZiI6MTcxMDMwMjY4NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjIwNTMwMDAzNjgxMzEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM4OTA4NCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.Ro1rbAoMSgfS-GKEI9Fs2Dao0sbqbHn-6-OD_14AvdCVDgLmM-bojG9yoG-L7LscDDZa9asPHuggVNsfsZ6CeERGp60MRx2zdSugVrmYYz-P08G6-FvNoniRCYlWvJc-5bgvKMr9Ihoi3lDg6EWUCtRpXis_ym0yzhl15noEmF5OIkXdpsEzOyVyZqJiI3lNOVkaoYgCEt9xjAFbwn8GWOwsLVsJnm5-uNraEaJd-Z1fsfseEmdbR7ZcvIv2GnQfxnq89ivWTUBjZjG9RldQrwGBxr4hZxW5smQVxLGGsoaOyLq6wOj3fMXrdPRX5I2_RWCzGydhRDdLu3-lAgojXA', '2024-03-13 12:05:06');
INSERT INTO `sys_token_black_list` VALUES ('1767764004630990850', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMDMwMjc0NiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzEwMzg5MTQ2LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.edm7GBpSD12_aCrmIdCrFyOq6FyAOogJXVSnes1YQ3dMUf98LHQIuslAj49Cv6lqTw4ZaKSeKRQxC5scQ677Aj9BXuPlZiktTc2CVt8F2H1G517JxCHsqAGmml0U7lMaFKvJNwvwGQ0FCBSJFIQfnOc2VLJk7w4AXFgPCwo3SoOd6AixqY6kOiJU4j4RrzTGfBpoteJ1Y46wcTTqtWMfACWUcS2fvnVDLW-6NkOYffzByGzAcaILo3jx6Ok2cpGZ1yaTwHmverqifTUR4N-KFYK9D1PyM-5iKTGgCBVFDtrXY3Fm4GjAoBJ2CRpYrYOPDOf4kJxxM5xglFUXtXax3A', '2024-03-13 12:05:57');
INSERT INTO `sys_token_black_list` VALUES ('1767764106380611585', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMDMwMjc2NywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTAzODkxNjcsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.HMzxWy4cetcm-kVt5bm6fQGo-ukVuq9abGnSJpGfL_A5GP-w2yB5e-1hzVj4dKybkRjQPzYP72zzUCQW63SGrPR6prKuteREKwuWPN2vRd-tX9tA2x65FYnP0bbK8hU-FNe-7isv7wa5OQmYiOHrOgc5zo6Jcm0OHkjxDkUr7W6WxcLGw5FMzDUbBUxBLQ06hMVHINxSBAU2o9ZxBrX-n685yjx7IvFr9CQKoskaP2bznWyM3llBvMcec9SDlARG4FrXv9k4L70pxC3xpEnT_ZWZWe3khrE6wYjSFByBQpMElPj7d2WFtzxp0CO6i0pzHuhbzMsKglZLNMeYt2X4sw', '2024-03-13 12:06:21');
INSERT INTO `sys_token_black_list` VALUES ('1767764181534150658', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MjA1MzAwMDM2ODEzMCIsIm5iZiI6MTcxMDMwMjc5MCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjIwNTMwMDAzNjgxMzEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM4OTE5MCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.XU26xTsSl3u14zzpffhqQ92bX-PX6ilYJKBRE3-lR_w7boBu_i9BDWC0ZneHxnwfy1NaywadyaW3AZttOCeOLJV7ZynQLZbUW5_-jq3N2iXrBsMcxaNVYrnkIowKT7UR9B6mWM0f4Id1N8gKcECLp6DNqE2kpTYd6nDvSSPx-Ej7BWqJKiAdVWS5Omh9GdXWV4FC29jfJcDHGHIMhFMPs4dPqPtbwRym7iVe_MHkK7mPtwIxgtLCJhKJYuTDBmyE5LfAn-ju2lWZ7dJ1jn-X49VBKtuoqI42QkL_Rjf7PFrPUp9UvxrzsH4ylzkEjdPlsKHJcewTPj9Wwek3b3tJ1w', '2024-03-13 12:06:39');
INSERT INTO `sys_token_black_list` VALUES ('1767765291099525121', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMDMwMjgwOSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzEwMzg5MjA5LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.fnkeWWYNbZb_O1FUl_SHvo9JMmjbBMaoSI1MI78Xfs9NZdAaapsKmR8M2LthyDVsVBTndpsjeGrFrP6hW7haDKaxzu5AEAUpPfEOLCGVyXqHot7J2XGlOltrM6zA9err-P_l6hSA9Sx7wnPNcjvUFYi7OF94QEpwZ1OAoQ7UlSzyRGMnngQqDIZ4bK76lS281_TZjfxcz8flc9RKGGxLWvT0Tz-jhaDIiRIAZ5mZAKheg_i6pF96mSZj0-ZHXsQRjFjxg-u4uTO9H51RaLU0lEH_TgMpw8N51zX5qZdS8AwQR0QxX8eGuHJUQpxwePswktLgf95LVzGLBrKJS4R16A', '2024-03-13 12:11:04');
INSERT INTO `sys_token_black_list` VALUES ('1767766893529174018', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NTc2ODE4MDYzMzYwMSIsIm5iZiI6MTcxMDMwMzE4NiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjU3NjgxODA2MzM2MDIiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM4OTU4NiwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.cJK44elKa5-AKV5OEIEAQlvoDQo0DM-FPzDTliReKk5bNokXjZTbN5CIF8mWs1gWF8djLv0Y5m_aafIEEYmgsIfRdzR_nRQzi3V3oCvRhynNAM0IbZ9WuJmY-Rfr1HecmP4PsT1iJUmW9K4PD0gdoJftwg6fsvpJsz4pZDG354c9DNsl7w7JgPeiS0wjYjTN5wpOe_bMJ0_jTqOTS8aJzpiS9pc4jnQKHe1PCujij9uAWMAGCQQseNPrb4ifyyJeVTkHnAfdIE_6Ex38sdhg0457eRyxiGZad6Adqw-mmGCp5ipXwBunpbpbyIs1FAoLnMaxH-cvVK8-T2HXn1QKWw', '2024-03-13 12:17:26');
INSERT INTO `sys_token_black_list` VALUES ('1767767098223792129', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2Njk0ODY3MTY4ODcwNSIsIm5iZiI6MTcxMDMwMzQ2MywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjY5NDg2NzE2ODg3MDYiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiIxIiwibG9naW5fYWNjb3VudF9uYW1lIjoiMSIsImV4cCI6MTcxMDM4OTg2MywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiIxIn0.Hm8klATVUJdrW7Si6IiqgYx5aGOwrd9W4dYY0LBIcmIrLaWrbCPbD6v1Fq03u0RQZxBGTaAEfEnhV2R0TFkN-Wi3tjI5SRHXd5mqlXYHhI63o6OJr2Q3_D-Gj_intaP2UpxVtc9nRczjnsEvTr9yAsZ_l7GXIlUfsdcQ-J-h5t0JvnC4ZX3BRGb8ZAarvMyn5p-Edn9Ig8LYLVO9_A1CTBcLHGAQWe-7kx3ix-HEYrLkwxkJdplzj0uuek96GnTgZ4INUL83oJu7sHMIF9OR3JUxU3VLJxtoB3ksDrRRvUR16DdAvT4M8AfIgk5X1MqCiLRjdasjOD73VecoHXyWVw', '2024-03-13 12:18:14');
INSERT INTO `sys_token_black_list` VALUES ('1767767761859153922', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMDMwMzU4NywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM4OTk4NywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.NuyNh93EGX_FzHW2yO5uGATjWn5rdF4nbRURgmbeCkiMQZbFs7Ct6IwNPqUfrL5cXKboMM1jsBifpN5LbUn1dyGyPLoyyM-3waxzZeg01wj0Mq1JCiaK34AuspqxsT7Zaj6jURAs0UujyPh5jUAV_NEX740bMO9vElLx8eKyuTxo8Vd0nM8AM5WR7KsohJ9cLVAkC8fv_b0RJWqk20XOmryo0tT_TiW7t5r5ZqYa8bVajnYZB3XomKVC_CDXfgrDf4lt4REziabUlfQ54WLGVcFF66jo0mpFm-MgybQR2nUa-ZARQyvGhENnHSTwRksKl2dXz4wwNooZ-E0N5LlxFw', '2024-03-13 12:20:53');
INSERT INTO `sys_token_black_list` VALUES ('1767767875705147394', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMDMwMzY1OSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTAzOTAwNTksImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.X316D3E-p9CsH0flvwOIuZguI7QsV_YJsuagVgY1BhRattnHH0ivMVzdiLrtOLy5y156dUbZw5AyU_eIAEm1aFbARxsm9_0REhCgfe8IOUksKG-RsYkDQ7PW9DiWM_9NMh4fdebruqdG-VxoqMO02ltK8PnVlJ__KRiskXDWj-Y-dd0lJ-SIgZnU1jpCCPQSSCbXMrsxyZZN5KEnL9lb9lpR3jbMnQZeYGZG1V9Z7p9rjBG7kv9sOwJPzCPbCd6Q1s8wV2da3ldEQJTsOsMAUMOpAN71oE8uxvTdiGLKNVEqAKaD0TmPrvX9v58RKpEHFRwMeECl4cwYZUjLEhhO3w', '2024-03-13 12:21:20');
INSERT INTO `sys_token_black_list` VALUES ('1767767959851274241', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMDMwMzY4OCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDM5MDA4OCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.Zpre7Z4LZEdrTnzoW6jOY4rbHeep8AUibBBJ9ppvMya1qG26Gm-2ON4bB0oauYAkMePYR-yP6-sXSKXzN2Fhd7eKARo-YYaidOlPoSZDXK043-NBRtvFujAOKFj4yal2on7L4ibl0Su1eEovObQbYx5iBqqyEejx0nISr9Lj6jS7e-Ylow7xnBrrU1CM9refadpdeJ0sYwjKpZFZeEgIKjoT8e2HvGlGjQ2GzRJ3n_Zh-WldPeTmHehtPWD5PIrkql2wFXslNngXlBut8FSfI39xG5SuJqPwg_MqXXaVHb1puNFUWAoqhfw9lrG5UP0zjVVUSluZQ8CyemMd0Mt0MA', '2024-03-13 12:21:40');
INSERT INTO `sys_token_black_list` VALUES ('1767768074380939266', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMDMwMzcxNiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzEwMzkwMTE2LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.EWhonn7TNVKG1ru90X1cpTYPUDjMLwW7MVK8q_nhByhCdQMuvjD8eOghZ_X0CmS0m5Echjf4cbs9s5Mm-hCTFWkvfT1f36LDPmtm9q3tlAzvPZY8ShHDYIh8kVdTBRyHA6018eH9uyX9-Ulxtrl9-707aFlGwNwqgvZhwkh0JVRHLnq4smqVm4Gy7z2boUnPjQ5eXtwXiTdMYy8gIlD4ns1rSOuAo7-pvVuyKlREkNMJ3wdsvEphQqqdm9eAtXEsVt5w2y8di1zGOTnJ2qp1LA-wCWqnWACMkIslcJkO9OSB5ruRK92dA0GihG69i4VH9r-pKo0wG9ABu5E3o89N0g', '2024-03-13 12:22:07');
INSERT INTO `sys_token_black_list` VALUES ('1767886694180216834', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMDMwMzczNSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTAzOTAxMzUsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.bY-kv4rD44cNr7MH3eoGFR9AlyMpVdbT0GR4WG-u9j2RKptx21NdEcTIP1aC_Vz5QPKy7kcydwfiqG8C8mx-j1UnvyDYe4uEighC90I2XT3KZGFUjKxzm0Nkrof8But3z9_GMdfM_oa8IBYNfAmA35vTTDLHNtLDExvlWBcHpN3qtcKM13cqbTRYX29nkXhVmyLwM1YTV1ccQkMqe1FPCZQjaNRBLr-TBuhGPhE6dLu4gG_BswuKxhYYwsfhZSNjmj7fYmz-lDvbIy8CQflLxQGOVgsiVhFPAhgO6hGXgO_2jBJqy3VGMkyWcVTWiWSCuoTHTIh86QP27yj5z2Rnaw', '2024-03-13 20:13:28');
INSERT INTO `sys_token_black_list` VALUES ('1768270255152091137', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMDQxODc3OCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEwNTA1MTc4LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.f4u7SnS81Ubt70gv85nGq_QnGG_WPJ7AEG1aDzMDT8zaejtmSH2VnldDlJStFqN0xUUZQGNbROgS8NDHY7pEGxyYdBROLpkysG3JevsmxDQAJeCxqasYI3baVZtFlRVgqYV7a_BpiOq5JA-BtAQmgQHzvSyA-J9zW0iiiwWMCbNoTbRMuRSTVE177xYGfWmlz1g6acTt0DIOEKOfyVRFm-djTM1YIDstDuRNateClcTuzoojWfaIMwQpqBGFf0bYc6o_iupKtv-OrA7RuYT5aifQCseiorg1X9RQMX2JCclZreeeYeUfcnXmPp1TG9kQNCmWf7M_UfkspzIpNbj6tg', '2024-03-14 21:37:36');
INSERT INTO `sys_token_black_list` VALUES ('1769555273438650370', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMDcyOTgyNCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEwODE2MjI0LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.C4zs0wmz8unX__FLaecldJoQGLzBf4beXIgmo4o1CcewsnsnfR6Cm1BKn0fVOCloZIkFycnz0MHxP5sKTCzUL--qAVe82pTjo44zXj3kelv2oLWLRTJhUrlL2DL8Nu2KKpxQOm54mG9K-vp38pqpfflLVYN46FVFqulER4kpgnZX_0VZfM24S2xNnt_F4b6M55sMBPgEoVWJCXCdxAaRzYm1hpYDL79O24ZTG_XmfCifcIBm7cjC3e32_ZJR7WtnQPmg-ME77F9S6P4l_-r0tL_AVm3x77n196nuggzs_wcZbOaLN82Grsgdb2yDBdWQt33JxhWYWBi533gHnLtXfw', '2024-03-18 10:43:49');
INSERT INTO `sys_token_black_list` VALUES ('1769657019860377601', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMDc1MzY5MCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTA4NDAwOTAsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.gg2SK6JpMeS6JhUDa8Ub6bkaLhN1EjT6pcnY-zkkdtKqWy5S3CNkhJ-EVDbz5ycUBduuhQauvCYFWMOUdcd0XmSrKTpJ8D1dW_5KCDsJXPK7wM-_yS8JX-HsP4AXAewfcuMwmemtMuJFfQaiaqebMJYVFaGOSkFvS3_9Xx4sRmPhinNhESgHYTlZru12a1GD-eOye9gPOSaTLabrBH4m-d-0aeFstmN5uPz8qbC9I8r1ok8SAeuAJNVNFjvE7qGdhVxwiO165HgLFE5JUMTn-rpIXsI5fT-Qt9tjqobm4zD0UKsFlBjn3ZlvqInjulq_eUeCHx7bO8qtPvkRFYzgmw', '2024-03-18 17:28:07');
INSERT INTO `sys_token_black_list` VALUES ('1769673176537456642', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2OTY3Mjc5MzY4OTc3NjEzMCIsIm5iZiI6MTcxMDc1NzkyMCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njk2NzI3OTM3MTkxMzYyNTgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJ0ZXN0IiwibG9naW5fYWNjb3VudF9uYW1lIjoidGVzdCIsImV4cCI6MTcxMDg0NDMyMCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJ0ZXN0In0.GMrFxjWO4BXeTSBa1MMuw77RULEPOYFHe2G2cuCwq-qSQoohg-iDu2ZsqS1VCOusjDozzomJXpVZy0FjsQRHWGYGmYdCqvBy0rWOy77Slg2qX2oKrmVLQUFfXv0sAfCfHjTwoEkeFbw7p2_p-G9841zw8Nveg7tZEJtd2nPsOFmvUdfb99xmYYwTQOQJmIq2xzogit9ZVV8QW7SZ-FOSZzY1aoaJ1onXnYXlr42pOSLq9F5bJXM_USbDDW8FAEdqWh2l1iwMQmDQfP11PeEKZ6mFadMytrxxIjsWtKQiOX65kTcXDIfS-LECKLJGVUW-TnPeZd9dXIz-rOF9GUqCtA', '2024-03-18 18:32:19');
INSERT INTO `sys_token_black_list` VALUES ('1769673480981012482', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2OTY3MzQwMjk5NjMxODIxMCIsIm5iZiI6MTcxMDc1Nzk5OSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njk2NzM0MDI5OTYzMTgyMTEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4cyIsImV4cCI6MTcxMDg0NDM5OSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzIn0.Wml0iTU24zvljO-9-t1mRRBcFb1pfawEE960zQB2rzlEtFiouDrf3iOeBGcIYgBBojWduQKTnxP31mBJf8pVc3Ol1QBAP86mrgjiO1yHmGzgmgfIbtWeYRcPcsAvXPKM8y_QQcinNHS9SgGVTbjIKDC8ZmXi5Xe14pr1CuhjbOmzbQyptr_XWdMrGtR3A86Er4NtU8uz3CHAocOct_nlF6RB6l3SmsZIlAngG7Ih9pQ-KA33DXeIaMNQEcaJ7MJsX2MFwgpgGWT5vPeNbuEpNbLjhwqLpPviqKGCd68UHWy4wMuSe30G3ZMmBKIPKh_KRS6oW8RtAenOw0uS2g9d9w', '2024-03-18 18:33:31');
INSERT INTO `sys_token_black_list` VALUES ('1769950031270199297', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2OTY3MzQwMjk5NjMxODIxMCIsIm5iZiI6MTcxMDc1ODI5OSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njk2NzM0MDI5OTYzMTgyMTEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4cyIsImV4cCI6MTcxMDg0NDY5OSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzIn0.WS-i44wwDewFUm64bglPXkfLys9RCSwQJS3ILsnjpzDxzTkfbZPQa0u4TOIf8KrUJrcYSHeCqkCzFmXXOa4qumNb4dY8o9rFyugDQ7qu11PIoFLO53Z0o-cKGK2J6-Fykt07fNPi8JetYsFAK8JuqEX8ENkKuX33hSl8VKvfTnWwJbR08bwJcuGGH_36spdO1TSrxg4mVEqGjVrEEnVAR7ZNpsLNMBTiR0r0Fh2ybuWkxnH2jM_4MnK17cupISPYaP85RjoJlPWy_0ltyxShYh23NFI97mpN-Uobr6H0FxpCyFh4RHlUgucDTnObsazczqVKceajWzrdYTxKSINcGA', '2024-03-19 12:52:26');
INSERT INTO `sys_token_black_list` VALUES ('1770313619187867649', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMDkwMTUyMywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMDk4NzkyMywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.LnuTTlgkpjcbZJGd_zoHDUDh_rEFf4vdcA7VXjGkV40EHTSpOyZTXXHZw5AcMhXFBNDJYFfF_pbKDGJEu8dTWhBJq3cTaO9DDbGxx1xdlgeg9hTk5NmK1mK69rxO3I4IJXzUXpF5orfoTOQuKVURHXfVc-WwV3MH8865fEBQO3-W4UgR5gA41YjR7YuBU8x0P2PXI0-Z1EeFezXYXZqPQUm0BRcIAW7Kozpd30oc6dqt7lh0rpZ4QGfVfgkpHY5fsgkPKjb3U5uuJgwO6gfe2unyL9Yt9Bmi0WBnvLOu57fQ50PLgiBBKLejSE9fhmBTEuAMXxSL0e4zsaX97d7tPg', '2024-03-20 12:57:12');
INSERT INTO `sys_token_black_list` VALUES ('1772131469900812290', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMTI5OTYxNywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTEzODYwMTcsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.Ps0Pr1RfDG430gbME0R0TvEVx0mOIGGDkjxZlOZv_kKkXl0uykZx4piSe0QL0xg2hEtJZZvLsE1kBnzPZBYp3OGaQm6Z9ahW7cKhbK8wBtBZw3OTFRqntaCmx31MvvpKpl4LzqfJI10BG2M1b74Yqaf2N4P991ScQF2n4BqsAxSlG3Y_rBvUerxAGheuG8TlqNZMJgFbh9XgV4ls6cJifFscKTcYTCWdvxL9UonAkPNk6rlSUp5Nssf6mv9DPY3l4oIlV8iGmScB-zDEnPuXdAcNW0mg25GrA_XaHqZPflLSecbRE1K0fMTAB7lk88GF6-0dSjNCepgAEs5GWN7Baw', '2024-03-25 13:20:42');
INSERT INTO `sys_token_black_list` VALUES ('1772131995702956033', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMTM0NDA1NywibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzExNDMwNDU3LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.cjNrx7YK087bU01acJUYPK000d9Z2OiXnTzYigHW3ZAyvXSG630lFdMmZdzOika3u0GaNz9iRMewc3LBU6W9E3di9YnZRrlZrsBbQ64QSow6Sk4zUBqZjPl5J6surnTtbL_qxxWMW9rFtHxwagOHE3JCu7g2LvcjMVSvJfhVJyzDuNomeCUK8w87hZ-znbX6oxzP4gJZbh0V49ledt7TCwSEzUnoyPm6NLu2JAEQPTC51FQvMKP68TTgROE2XjX_tfsPctvjg07TlqHB2m4p5uqtz0RcF3STe7Sf5qtMCrgi8ts4Aw0nuQSmOSi4WkJkZ136CRDtSVghywsCN_iseg', '2024-03-25 13:22:47');
INSERT INTO `sys_token_black_list` VALUES ('1772177326390022146', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMTM0NDE3OCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMTQzMDU3OCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.Xy3nN6ezziAhGTSRx_lZKC_o3omwtPppHhYJ1VOp029y3NvnHum5qhh7TmPecy5Zj5DFgzyLBz2lCMFrNlam793ev-pPQ_u8-VMmb8ik37SJKqn69QiFGpRZPgaKmJRpZkxtoCD3oz3TXeuE95q7XFFZxauYzYJ4ULyZHXXuVQn6tyvVomXS02WAWVINy3_3cC4akydWElmVqJk49GPcMbcZOp9yP6iBFAurRK_U5Bc3VmPchEkIXqYgSnLfPeu-6a9nXh0N4Edk0lUuDFf-Snh4sDC38VpxMxBtYy3wPFY87iMVrhfz52LVxVabIL3Rhy2c5g4qJHifxhV6NXPngQ', '2024-03-25 16:22:55');
INSERT INTO `sys_token_black_list` VALUES ('1772178025882488834', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMTM1NDk3OSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTE0NDEzNzksImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.gstzvOAf4sECoOxppY96BlfaYv_nS7OSw_pjYLzt9UjgVKFeSvWoO4-3szxALSHf1nLjruXijcKH-5iejjsgSuKw920iEmsxpiecKAHuNcquzlU6uZFmWg7ZwWNwTciEB45bMLYw-YVHKBq7RNWRCevP_4hJF1wdG5sbiN8EuJwnZixeG2z8HslbpPjnI4b9tXcI2pIqYyF-kUrhP3xCKCcxxkSsAYO-AF8V4DnQM9HOCPc3mZTjANAVrMRm6NNR4aPnf_pWsuLfRZ7SPEPghq-8Ar7d__GXeeOKG9uvBRnO68PwsakeTDYJf-Ulg0Vl10-3Ukowd--8GmbzsYxOjA', '2024-03-25 16:25:42');
INSERT INTO `sys_token_black_list` VALUES ('1772179331611267074', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMTM1NTE1NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMTQ0MTU1NCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.Gj3riXaoLGB1IBNghcWFcTtkvlHeJPftuSlx00RUou2DyTG8YR7bfYMUVmyRbWgcWsSKD7DCy8l2q_0zAjWEfHrGnWcn7T-4VBP2k7HM643xFzZ4hAGBLGZh6Qu9Z46NXOxdg_KWx_i7zTWOvpRXqBPRhDeIHAU2gpg2yNs6Yd7G7cf0k9nWY8qnyuyjGCxoheeQCU6VgXwa_vM2FWXeXzvZEOH0ilvSSFb6KqQTEAR0guDggEr8l831dnodGRUzW2c62sPHNbV8qstUnMeQDXJlzzyfGSZ0mgcqZxP8ksZbWkBhwFQj-6ugQnZEnbyWXOn2kVFpGwmji98S_m00Wg', '2024-03-25 16:30:53');
INSERT INTO `sys_token_black_list` VALUES ('1772179631348813826', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMTM1NTQ2NywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzExNDQxODY3LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.MDLlULtGE95K7ziaCVqpxERVjTtUSu5r7UY99L34Si1j4ohaEXUawxsCpPz_ASmYf1_UCwfm2sfjo7K47kqBJ4lveR0AwWbQS-dTfoSFh8No2YvxDsDIfkefirAhakkIgZQHXg-qKqR-A_dYoVR-3QP-KCFNEdpcHt4Epldlvj92jmml5SetVWIyDC2wvplf6VxdU7HHVCiI9rH38ztSRh_YSw47nbZ-lp7LIDQBIuydH4BOxiyqzc_6a1XiBVhz7bz0V_uqTEbpbkN-MlaulDmYITthyAQ3P1IOAZYRQwMo6tyZAFNN-rnvDFuFgThpnH-Fn8WqkgCNxWw5eq2KVw', '2024-03-25 16:32:04');
INSERT INTO `sys_token_black_list` VALUES ('1772232911596720129', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMTM1NTUzMCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzExNDQxOTMwLCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.PK4Ntaz4sep02dlvv04S9k5yh5oQuKcZq87SkViHzMzRqEdeEWBN0YnFdYvwem01H6XUJkxmHwIolUaWC0NbwHIkSygfbmgNXMv_8Xvt7oAeoiaieZ63JeqYy5I_KirHz_mudtVZD6WsfG_HeGZqfEkBUn0qcDlROI5YCHkdEgoPNU8mYMGrJ1Qyo-9ECMWH3MKIpyyNNWHYBY2W0YMM2dQ2tIxQPnAzRffBEh8txTKnd8VUDi0hvLlWIbwrXtyMs43S-gx1IdQtkgOpKLrd8lEi3N9GtfK54X5934B9IKBmpNBqQkMcT5IznnRDo7-1qFUsfabYPtpSvkxNsYcqGg', '2024-03-25 20:03:47');
INSERT INTO `sys_token_black_list` VALUES ('1776281083290165249', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc3NjI3OTY1NzkxODg2NTQwOSIsIm5iZiI6MTcxMjMzMzA2MywibG9naW5fYWNjb3VudF9pZCI6IjE3NzYyNzk2NTgwMzIxMTE2MTgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhaWxpIiwibG9naW5fYWNjb3VudF9uYW1lIjoiYWlsaSIsImV4cCI6MTcxMjQxOTQ2MywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJhaWxpIn0.MrrwrMssWJ9AprEqAAmQet4G0UL9DN2JYAYWiKVlGs-xdqMksrrb2mWo242128d0Q2lmh8slCVCwqGAgXEyblYPKBPwG2bQf_E-Hp33nQo4guwUVwqWTqqBuyLX3PUw-be2qIZayFhDiy4XyGvEe2OwO8wHMRjE8HQds7LPyOO5El8Z6_uBS-zQB62fS-XmeWOQAHCx9334BM3DXc2DfEufsQl0XHOqamoz_QHF_EMhqPFWwUuUcG-IiaOjmXJZmOYyI6GKjGZP2zCEij7-yShidcdblaYJ5EKxDZMjLM6AsqNZYgzW_dcvukewIUl27rFGrvDVp8IjGStETA08deQ', '2024-04-06 00:09:47');
INSERT INTO `sys_token_black_list` VALUES ('1776281182888108033', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMjMzMzQwMSwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEyNDE5ODAxLCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.VFQp0jwlhXFeRkbx0Ys2l2EbEnBujaAEH5X1AbDksiSDH9oyh7yNwB8Qb_RnBc4iDRA1sPTJlQCNsfalxE9_JoI5sm-TXjdtnGCkXBOVBQRS-sBArUw4Ij3nlhhxUFN5btEUIApEsfIy3XO1_XdBOtH4kBkItr_56thBjZP8gYnx7kD_im5Q0_tgk0vaE6o6UxKzq0U3s61ZZfcUCzcDZwLdb5kuj12ZcLoIXZKSedfNLuR5dNg8xLegYVEr7Tdd4GsQq3DnD-U7jqpeyRz5ETu5JS5XB5xFAJYi1ZI-1fB6G2IfOr1iuwV_Pqga5QlT3nJvsR4qXe7xxPulbvek8g', '2024-04-06 00:10:10');
INSERT INTO `sys_token_black_list` VALUES ('1776281283102613506', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMjMzMzQxOCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMjQxOTgxOCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.UC_u9F4Awu05ug2Ur3DuPGHlnEUQmsl673tJLKeeS0wS-JVffO71gns-FkXVO9Yx3IMiq5D2fnmyYCEEz0h01W9sXo0Mm59Usxllg4_JVpNaFn8T55pBMr4zO5EgYxtLuMmpOCxa-TZaZgWsg2MvsEcJwZ27MxMeavePfP6sgkBNjePtBMRqAAqEoMTEZiDnTo_ap4Ny4343bT78TCEHRnrXGcm3ILu0TYX9XeZIgj3G71ngpCJKauGpr25-PW7hC7sHkbLD2OYGl-X2vfHauD2W93NKYOC-q937PRbLPq5CXaLDtOaozJ9V4s_GxNaTjdfP3Q05z5K8YhqzeFqkjA', '2024-04-06 00:10:34');
INSERT INTO `sys_token_black_list` VALUES ('1776281501747486721', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMjMzMzQ0MCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEyNDE5ODQwLCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.Zyv8V4tGPtCcxf8VwDJ6MgR5FzVl905ZhMsistXNEZ2F4_ehKm0KxbaRuZgbkNxaN8o4-_A2Tvkp86xL6i2y47TrHl6k3ilhsYGmkCjXqRtPBv6LeWaF3U4r_zyxOIExRLJjR8XP0dLs0FIVtmI28gNtjE-P-uuKA5EvATqAiiNPAnkJlTqMRoIEV3ezl07nwZDF4H5yigZq9nez-O2V6UWyoi1DwJsYM8n2yC4MQu8ml1JaFMjvQ_I4BAww00fsWoOp9MBwfIAAA7-Dg1IL5GBUDe3k6twPvFzvniEqdo9fSSVZCN2igkEqCIKPLZXezF5TsfYy2089z8QMNaGWiA', '2024-04-06 00:11:26');
INSERT INTO `sys_token_black_list` VALUES ('1776282836794449922', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc3NjI4MTY0OTQwMzc2NDczNyIsIm5iZiI6MTcxMjMzMzUzMiwibG9naW5fYWNjb3VudF9pZCI6IjE3NzYyODE2NDk0MDM3NjQ3MzgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsaXV3ZW4iLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsaXV3ZW4iLCJleHAiOjE3MTI0MTk5MzIsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibGl1d2VuIn0.MMnXx3iyyRzOh0A1ojEtR9rwihqJbAdbCj_erTpxLWa9EYCi0KlS2vyTaTp3bdlAH5ptXYkqngGztxLkGF5LFcUAfZTnlU5ciGqLqwZCvwnzpaK8ri3Cwn5F4xpx3AF-ixZZP19FxZIbu34NfaXiDofu81hItOnbwyuGCmsHojXyV84H6PVT4hro9bxdbBMKV9H0DZMOBOsrPA1JNi9BvRpqbwA9pX8yTSr06u7NJ-AsAdwC2J5HzuYfyGtRPwTex5NjkdGwmIH1Zjen46R_IY5dFbrxUtL6b986KRDDvQhBv09WnJKZF_ek_hjd-apAsuVZDLpO3WGCXk5kZ60h6w', '2024-04-06 00:16:45');
INSERT INTO `sys_token_black_list` VALUES ('1776283109155774465', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc3NjI3OTY1NzkxODg2NTQwOSIsIm5iZiI6MTcxMjMzMzgxOSwibG9naW5fYWNjb3VudF9pZCI6IjE3NzYyNzk2NTgwMzIxMTE2MTgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhaWxpIiwibG9naW5fYWNjb3VudF9uYW1lIjoiYWlsaSIsImV4cCI6MTcxMjQyMDIxOSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJhaWxpIn0.cxSqOGNAWQAe-4GLT0wZTMafyJdct4KpqGm24WsV8I22GKNwfZ-oEgUhVZoGI3Uf_TUTl8LnyI8SKuFXd_2Pu04slPPcGCDHAYL50IntqrKwhpcT3RdR_-q93x0I5yi9WIGo1ecHtOIlfwvkyenM2ER99Iy7925xVP-2yZeuwjzQwqlvvR7riDecJptr8V4w8Thf0gzNC1r5SoN-BilILih3nb_y1KZygrmfUU9tbO9sYXwQhhOzqUDNr2KuQN9WvmmUFnz8lHoWv-EE9ezsorkCUMDCD0iFbfFpP_hBPAQquuMLjsE-OtZA9Z5lDkR-5g56Cesi1wdDtP2NfSgSHA', '2024-04-06 00:17:50');
INSERT INTO `sys_token_black_list` VALUES ('1776283457324949505', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc3NjI4MTY0OTQwMzc2NDczNyIsIm5iZiI6MTcxMjMzMzkyNSwibG9naW5fYWNjb3VudF9pZCI6IjE3NzYyODE2NDk0MDM3NjQ3MzgiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsaXV3ZW4iLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsaXV3ZW4iLCJleHAiOjE3MTI0MjAzMjUsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibGl1d2VuIn0.U-LFPwbFAQJmENafFIkjbAB_sorBu1UEkvAbEsfug18msA3rjGWLsDjdjV2WQEMyCvG7J_KeqrqC_vEpZKNPPPC-t0LScjj3vdeChChO9b7SoJJC6XnDaOrH3hSR819p4UiZD7An5VAb56LYUZwAFMJ7X3sSZFny2fmK_2kXipzwI4GRgx49yv3EY_ulUKinFCL7cwuccJ2CTfsdzRVTx97hHarE1F-GloTFfCCD8hYbKFtCN9sX0YzHfQBOZ2a6yFYsI59tU_0H5t5-9LcdgSXhezJpLktbCYnrnQpcSnc24M7V9-GwLi5Bojeks0gi9POTmxRlyz3GUUJeRgzcUw', '2024-04-06 00:19:13');
INSERT INTO `sys_token_black_list` VALUES ('1776492152319131649', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMjMzMzk3NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzEyNDIwMzc0LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.H8EOF3Xjk7mViD42vHbBX7Ko9FqfPH1wSnfIt0vw8a_KlZeXHqV0LNnEmSKnk1_qs8ZCEbKSkDCmOHCjYITgP1TOE1VR_9ZfpA4WdoLBjDfnpmQ6fVpi2YJsGBpMJ1pOtJMef5a8kIuui4b9Fw359G2dxiFqUV89tHUCWyvJv8f-sl6KvkJDO6i5vrXEe2kHX-iZeIoP1he7pQC9Nyc4R7-2n1dqtQWbQamA45d2nJd7V_1A3n1fRFokhNgaL-HuK3E3_3314VVvYN_V-0r7gZfrVhvpcTwReVazIa-5YUNzIuQdAfKepgijzAstGw-fvTuythn-8wMfCcx2Ck_n-Q', '2024-04-06 14:08:29');
INSERT INTO `sys_token_black_list` VALUES ('1776521145265176578', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMjM5MDM2NiwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEyNDc2NzY2LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.At7_sKnovFGyQ4LGfZnQEYC0hXulrkRoz_f7Q5H3rZsPI0lU2YBEBvlQbmotPlI62vzxjg8raEozFlZ8OmpIxQRHoXCegGSyq0qCG5IvAqyVUbqbfJWK22OcS_MhajGX0Hj9x6Yh9Q6w4Oe-sfULZS-zRdf4i3dMKyZJ2XvCvtF6L_-jepGUaJXr9ycz_zBWiXILtfCeFSMGes6oLhA_AMOuwA4W7MPQU-LsUGFtLNoARQ4EkvSMjYBWtwf5XScYVR_hjFATt4sMMfcOSPgHyn3s7Ja7bIHsSl4G4NkwXk-XuuRXJ_MrACOS1ZZ1Nd0tPK3y2cCGRCKI56xRPUOHBg', '2024-04-06 16:03:42');
INSERT INTO `sys_token_black_list` VALUES ('1776650974421598209', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc3NjUyMTU2NDUzNjE5MzAyNiIsIm5iZiI6MTcxMjM5MDcyNywibG9naW5fYWNjb3VudF9pZCI6IjE3NzY1MjE1NjQ2NDUyNDQ5MzAiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiIxMjMiLCJsb2dpbl9hY2NvdW50X25hbWUiOiIxMjMiLCJleHAiOjE3MTI0NzcxMjcsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoiMTIzIn0.asnlp764c8CsJOd3TJSd8IVHM_qTsOlGItrcEWbiQuHBTozNiz83FcYbu03PLzZv5QAVG_dvCurWavNJiA2ixC_jfKrDX-wSpw7ZJ1R851CJ_pcFT1aXg4IIIOq5NK3CIMe-s5wnTucpTa3txr2rj0NVUJtHJz2OcYr6diWDWvqvLAj_lwOlxEIXk7LDN5XvCJUOpfHx6vyMb0DkutzzN7kLSeGs1HF4yXyPw5evkxvBDcpm5Y7kFmcuwODM9P6JVBjpP8nBo2VuZwDjlt5AUJnHHIurTiU-NcRkwatnWDcOO1pluersFlS3RKajH_fl7LewdK0znGWD1N5f6KysEQ', '2024-04-07 00:39:36');
INSERT INTO `sys_token_black_list` VALUES ('1777713683347132417', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMjY0MjYzOCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEyNzI5MDM4LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.QS2-V3Z_JLLpYe6y116KTOozWUQvSU5gLk_hpR-aBOY2rTjIo-O6JtVKXMWhSOXb2GzPJ9Jl72uXlzq577JfKHjRUTdhv1PAuXjbxKskosEOnaaQMDnXPr8yWlylAP792SDatz_hoKDITcWU3RCLc9URfnGtb453IReKUCa-f6Gl2z8rGF8Cu8ms2FUYAZH6RpAQukk4aQB7MtlCzdS1Sg52B2ZQterv9HWfFghG5bKJZWXgNJMHPVh2KE_MDXjtPc-5keQopigbyW4n2wWmgS77pwuB66pXvqU5TJgztzcEW54UNfaRc-gYXFWgP7UVM_LHbpGIfFVjJXLe03O7RQ', '2024-04-09 23:02:25');
INSERT INTO `sys_token_black_list` VALUES ('1778464570675838977', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMjg1MjAyNiwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEyOTM4NDI2LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.YpGFMuKMI-SyWUvEfo8d5RrWMzyN07bUr1_VPrS6KZBuVMdklH57Yg1Mf7DYC47WLN_egh_Slt9NWyrQlKFURPuSH8x-N4yjU0GpaCplM37yni6nDIhc_kBTkwxoSXKIYK36-Z_XYaLziPDQdhWhfUeO_G7LmaxqU5szx2AlwIgWnbXzonEsMdeqnozbepBB19tnRXL1rGRn4PY-tsQ6KHQw20VLy_d10axN2FNYNCoLaXpwgQlI0kw086Yfqsnne_mmBsu4Y9ORzMyHEJc1VP9YwXsjE3Vn1nVW_2mhMuJHRQTlEh52EG40iu6pGQV2eIqMvf4v_DhTXI6WNsRTjQ', '2024-04-12 00:46:11');
INSERT INTO `sys_token_black_list` VALUES ('1780251412823826433', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzI3NzQyMSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTMzNjM4MjEsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.Kgo0Sp_MXj9kGWOLP5wQZhRiJBtF6z-DrD_hAnjhghVH-orrBPBKLu0qbZLpqeU4SmxMDasL962ZGt3nBw7-R7FvO6d1D7okBq8yy6dUOMUmhsdeCNcpGE-pjPPvHD191j5qQEXAYfpHqtcZMu6KyrdkekHb3Ee93aDlaLRQhuTZh8wYG43Ea-0tROEdgD658LVXuAP_0Gv4qSwbH1Hagw-XM_UIYkMoAa1GJUCUbNm25UT_PFQHfMWCt63ZA8o_bM1yZuFKVTqrmTG3MhPWr-nE-EgwEnKfUGLBBRSNc84Pn1lGkxM0W2aaggdoxmVzwQJPHzNCRYfyswV1DHlmkw', '2024-04-16 23:06:27');
INSERT INTO `sys_token_black_list` VALUES ('1780608381967405057', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzI4MDAwMywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMzM2NjQwMywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.MMlfKWZZBbkjXEcMfDcarZsPpX4lZhPHm_-WSdg_TpfJHqDd5cITFcO5yXoNgczg73X8OxkHDBixYmQmUZNU1bvjoNUciT3Yc5ZW0hU_5rXoFI3fON6K3veHxJMXkvdD8LXmGUamwqEFfLQ6lASMnR_aeaoZrm1j7oOdmGbVjlY3x0jNhxL_KtTsE3bjwvrkFasEal5yq3xMDQlWG0n5QnsHgUeEv6A74cUkkAkjfsfwDWAIL-RDpgMflpVyGQ2VtWmcBJbrZpIPgu0LYdM3Y__TNYuAH3WwgZn4kfUrU_ncMJBHl3k1nfd1kjc0ynra7Mf865kTWcoO7XU0negt-Q', '2024-04-17 22:44:55');
INSERT INTO `sys_token_black_list` VALUES ('1782804619596943361', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzg3ODM0MywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxMzk2NDc0MywianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.ZpZaKamRf5iexhR5g1xLMQxwImNKrZYQ8rQY08fGWtcQdkOvF-HB7_3-hxKYp587fnTgItKxB9kLqHRKaXiTPiZsP_QMljFGN08zKDrNcZmt9e08IYJFc33m1Zy2-wIVoHghVusrLTx62H-YlnivcNlj3jqeoiw3AInWJzDb211FhQv3Cag1rOvFAei1slIVMqYIMUylm9S6TfxqlRLFBH9Nm3OJCUsjrXqkxeQH2QaS0U6VjFuatc6bC1JJXcJkS9m3uBy_lnZBcIdROeO02cpOo1C5gPC9CSYe0ZiLoYrJSkWxyyz5Xqu46tYhrR5OrsBTq41unwZv1aZta4_4Mg', '2024-04-24 00:11:59');
INSERT INTO `sys_token_black_list` VALUES ('1782806675573460994', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMzg4ODczOCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzEzOTc1MTM4LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.Y2MDUrzBgim95k5T4gxhwecs5c-gS_QnnOMHckDg_hanwoMwinpXdKmVYSotKi0MGpCxNtHkLVvCAl7mbqhH0S_CXB65Mxp_Y6sDSDKzlW6eG00dC_ieOJsQqhX9WxrqOf4jHySXbHEqvyeufcI7Q3Rd4gAs4HYJrEuxPBZ7nZdUueSiCe52K36IrsBXWQwZyRFTmDOTfjat8T6o6c40qufKnjT0U1CCyqYxLTyWr9ui9ZhJ2im450-vDxokdMZTsqWeutuFTMuFUsEHegy7vUNcW9i6rWQX6o4E1bgcRmCWzwsOsQXNSvsZ1icVoNCytnMtz17IAh29W_2HzzJhIA', '2024-04-24 00:20:09');
INSERT INTO `sys_token_black_list` VALUES ('1783060713900625922', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk0ODY3NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzUwNzQsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.CMvz03k3IiKByfAG3hGF3Hw6QInKHQudq69Sz5fauN0cnzn1ocxpz9i7MpOP-mr_4vSFKFZD3rQvYTJN5dBnTgAMr25jnl2XrBNsbFa0ODO3FL-QoiCyWFBkLPAzubSZ_NUtQIhamhwaTXbLRT1sinsf1PU50-j0q9S3tRaI27IeFRlpBADxpmYT4TLyFADqIEPG_y72dnByjYXq6joFhSs0dBhEPvdljGinmjxatXkGbc8JsaBJ7tSsWpk-d43rNGsDQuF2GB4edgX4v_emLTpC5SYIcZSGIzOcgHnGC5WSH0wV8jN66th2HChwAizWIKJH07KCfKGrBjJrWEIdug', '2024-04-24 17:09:37');
INSERT INTO `sys_token_black_list` VALUES ('1783061733900832769', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk0OTk5NiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzYzOTYsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.F0ktLv7pujpC5yUlmywlsZv7iuo3RHC4hg2yEOrB8JvC255KfSkj4jHA_jNMgNyQHVQDr5CPeC0NK5ONl77tT9BAOgGobGl5-vnBhwQ4Kv8o5w5yphxP2v9WCyh0f9mv-YBwHQlOVTju7qBZGXgEJPbAt6oIEFl-7XzwRc_3KbCpZ-n7dypzhegr-u5vlW10IkLwcPoJrnaXo6kI9o1hHHfPC7VrQzr_rkQW_5j9pMTBVTVRES_a4BMtCvGSyDSSzaavSmtxc645KlTDgXnJ05VV95VuiP3ObowptGmcVK5XlG_66Gp8lxI76lU7OwKrda2UFagylZKAXSG-qULpCA', '2024-04-24 17:13:40');
INSERT INTO `sys_token_black_list` VALUES ('1783062604860641282', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk1MDIyMywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzY2MjMsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.TkfWQddCbNvYUz4t5haHJ6NhDmGCD6E2PI6mF4FPkVCscX80XZgodqfCSpBCLrmEUkCPoXopneoHqxy1ODeYIUvi-MJTPHCbfPyMHKjiI5hJM2Oy8X9R3ljLr4RdWJQNctNs_57JTE6UyXex6qLkyIC9P4psNTMtq1H6RiUew2X4qAHp5yvhb-2AhoUbq-CQ3qfKiGbZcvlVV9E5gOSnyb_akWjTHz-oMbX4gzXh0uoCBzmtQwx4VsbOWut4ganhb73MQWXCaBi__he_Fo2hGbtcw1lJUQbNXzxwAFQ2rV64HnpNPT2XuI8wxkVAMxhW2WXbop08KgE938_o7_MLTg', '2024-04-24 17:17:07');
INSERT INTO `sys_token_black_list` VALUES ('1783062749803204610', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk1MDI1OCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzY2NTgsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.S5dJle9ODbT1gNdtKGN7GQrO6kE2zd7iCGjPAigJ2dyLL6A-e4fUjbRvtERsHOF8yDuefNnryrKVqZ13djEwWm3ZwEWht06YWqfEncoIFPXeCCkyoX9f52kJghQZYrKlBIZCMYJmucjtVdZFg61rxwmKZphVfWsDxVwJxGltNoiSPP7Sf40zQ4NEHkj6Vw7uWrrV0KdY0i94Zo2I1kn-dngAEyopLX9UUs5Og_wUuFG4LpXgosGr13og9UNitPOJh2Kpig1nVAeLpOv8piGqV5SynqaYJRPsSFDMH9-LxT9lYQZuRdbPCSvvu_2UgKTufqdKeNGcRed4gT79JqiDvw', '2024-04-24 17:17:42');
INSERT INTO `sys_token_black_list` VALUES ('1783063037805088770', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk1MDMyNywibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzY3MjcsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.MrgGhsjLq8unYkb2ONp05-mErE93bFvTIzXdnaDOIc32duO7n278RINn9FUrxyAR3_2fs9QT8sgcaSmT5jfT25t5cIdt-9OZDVCMqZcSLCNnw01GwoFzofBnL_iZWqPgF1wa-awQ2mDuVm7jaC8IKDyoV_wpL_JBgSnBIHpvR4GiLmDbSJ4cUDcKZrTSVbdfRrsnFk9kJtgMV_32wCyb7GVrSooPl2cryx-JH2befO6XEUnKAxUXsxBGvhBcZG269NJFwV2wWAno3Dlw4iqYTFvzQW2I5qFRsxreZN3KR4LcU9xei-QMZhFxxZQXVc58R_V96ITIZkW5HDntiLDIgw', '2024-04-24 17:18:51');
INSERT INTO `sys_token_black_list` VALUES ('1783063529503346689', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk1MDQ0NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzY4NDQsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.DUTrkD6fCVwpVH5PD2qkQyH5iWnZfH3p7KtO-YehZkJQKhqWCNSqZNJU52o07sBedlR962fIEYxOqVDqVDLLVrtfOJYqjciQI8ol2udDec25TVFdNydCvO-5lNYSK2XhByhlVy3mRDBRaKXs_HTpZymJEf9R17bTh98oywPYzO0zp91B9qALepM5fnT55_E0rHSEOnlnX5CCg33hh2iLv2ZvlTwkMjiiJ4UqhoDrUjUkBaYwH1WL7G4g5VOOZIohsvM5GQJeUqJFxvQNV2rQpaQRoLBBoqOxUUncyns71EdNftMCTd_aWrSy8F-GpWCO19-ltr0Mefo0SV952er5rA', '2024-04-24 17:20:48');
INSERT INTO `sys_token_black_list` VALUES ('1783065576084623361', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzg4MTE2OCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTM5Njc1NjgsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.a1dtLc8IWz_gfoQji_KqLMc4L8NrUGzE4OXPErI7BN7JVMJx_LjpM4mZqZx3_C-mHXK_Umg6FbflUPxyJxgRxGJl8-c4dAdXzdP4ySpVNB7krEVDMABw6swtcrQUtfYIbHhJcgxyGC1R-BkOEsIxOwnuSX5QMQgEzoFG1GFiR7Xs70TCqO-tQJJJZdoeippge24H2kCKwGpf5Ku-NuYzWC85Ufc9iGsaOlZAbWb2L2F8wzb_2UiJXMxHpD9ci0-z66MzUSbc3jf__npM3qatp0iGW2vhiIU-BHIktTtsB-7DOXSUmboMyNZOssF1xzA43P9PF3nJPd5NENW9Dm8QxA', '2024-04-24 17:28:56');
INSERT INTO `sys_token_black_list` VALUES ('1783065706816884737', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzk1MDk0OCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxNDAzNzM0OCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.DvOe4BZ9kM6wxjX2hzSe2qRMqtQ3tXq8mYLOojvY0Q9Ut1B7IvQ1aLiGBvpygoB68RtiF37MfF9fCpHMnn3f_3Edt6oZrc2fOZcjg_kO7ZON1S-svJiPXW_93vlSC2d5DM7S2L9_oFKjQqW8JYjEheer0BUiUTxJJkSy381KJr_ISy3Mj9RSlJNUR5XVVRztvXNJ3vLcu62CYGdZb4g_s_UZrWKa1ubAiYc69M_cg1RXVO784yZDyyG8f5pXDVElJNwdWKlzUA2BL2bZqvYz2C7j6SkXdFuUnRVFeV-PFmnD4xdfVZfiha9rZkFZpyvqoEtkjq-d_7cT6eSgTtHHYA', '2024-04-24 17:29:27');
INSERT INTO `sys_token_black_list` VALUES ('1783067773983805441', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzk1MDk4NSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxNDAzNzM4NSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.QPnfHZGN-VvKmHt8YLcQU6C_HNwp2Cq21Cz5BRSOenGPiD4IrpUs8PzCB_k7xtqY_8vfE42gA9VszxGJQsqTgItYL7hH1td2f_P-gYNnO8kBEyuCgFRHUruhRsTuRWIg41-74lRKJe_VDbo41sN9pjA43zGEUOs7nUpBVqiPBu2k6WcbT7uDyEfbll1W0Qo9Rg6w79mGpq89KUzRatIZGnqjhDyifwKtkwAvV4jTyf3cmvuVp_56kuCXz_swCv7zGgmVIiU5tEiy8HNSOvgRLvER44SO3oPU5eYqO2nDGu24V-2fFcz59_KMug4k2DbyyQDdeKewQ_sGzNs5izTgwg', '2024-04-24 17:37:40');
INSERT INTO `sys_token_black_list` VALUES ('1783068023301623809', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMzk1MTQ3MiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzE0MDM3ODcyLCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.MelqkwK7vVA2aqc_tvBUrMFx9ELGxU1-ardRfGuF7xCMQSNEbjC-B5sPxl81nxO6DvpdUAzYo45YTUAEltrvEFqqw4NSpoFrEcMsr22WgZZ8fEahtajmrrV0i-zJxIblIeiWqyUMrYJ1cunEcic1aJ7RkQTXfa0thGjqEgBBx1Xvkn7rZhiAoAWS8VmO1mQfMlAFrXSAbkAv3rFLl61hYOHlkl27b7xk9h_iQRDORcLQxwi09Ton61cWCoKngj9A_SpzulPCWQpgaEOagBvHM2_MhnQnIeoZ3ZrpaRw8sxc-GtaSclibAIBkpSOZINCl1U8c7q5ygPyF_2_I2S4DMQ', '2024-04-24 17:38:39');
INSERT INTO `sys_token_black_list` VALUES ('1783113330999709697', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2MzkyMDAzNjA3MzQ3MyIsIm5iZiI6MTcxMzk1MTUyOSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NjM5MjAwOTg5ODgwMzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hyaWRlciIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHJpZGVyIiwiZXhwIjoxNzE0MDM3OTI5LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHJpZGVyIn0.EbCrrWH3E1YYvcMwvsHuDH1duAueIH4r23anqwzDtIq9LA2Exm3V38VpuLN6YC-268mRI9h2VxcL1Ia8o4EBrZNR3pffm4oFYJ8U4EoLINLbssMyVzVl3NVbs3c2VVpuhaydLVLOF1SuAT6S8omk5QHtpipOT8P6JXbBhjvUEoafAL4QJUoR9Yt9nYqart9ZXYZR-1GHKX43oKbdG6GKd4FxdaZ8sr8MZ0YW1S1_ot_KsrA7-SRaSAypWRK1JwEYQLT4Nko704CBWbbrTSsyZKauWopWEUB4GsyJEpunqmM5TjjpckgviohkDTTJ3ZlVQLj0tkSeM42ypp00w-DluQ', '2024-04-24 20:38:41');
INSERT INTO `sys_token_black_list` VALUES ('1783114510526074881', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzk2MjMzOCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxNDA0ODczOCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.bSZxNFA1np_VzeXhB7RVB7QgwG6CN5zILqO4CoHY0cWccTR60O25HhJnEccsXb0KDEH1DeiSGHlFMGQyGMKvZkPF1UufeAMzwqpprKpc95Q1gCxmdetgX04TV21V_WPc8EzhP8jX3QZmXhqMTpDSVX1ePC2Qw1VZ1WMHpbH4_tf0mQYP90ROrYp9hchVQ6RxFXQS_kFsTHq8b4nr8kxK7zcHENGQPaqol-R-IfNMOtmFxmbmj8SjsWj2v9qyeV2caPA57KuRbAHp2cGxE_VDSoWnQsvvy3MUpb3GZ2PQMFtw8R1a2smo6_fv6VyD3cv9eJncOgJV7pUP2Ttqz72mcA', '2024-04-24 20:43:23');
INSERT INTO `sys_token_black_list` VALUES ('1783182324888784898', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk1MDQ4NCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwMzY4ODQsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.JrkSpHnDe-P06MNpxS22Y32OqVnQTzs1Cg9H4DbyLVqkScibYzcffxqRMSjUIb_S_JgC3384Nhj_S8fh2TebDr9_GySzZQXw3pPpri-sV52HQk3NxSKUMA6LtDPngJ0dU_cahbUKb8KgM3kNKeW6m56CrGHIt77TwWUYjfDQXnSzbbfnsETqkQtZcgvK5kafRzJGjtCPmnVYTjSp-ZQJxVij5aI1dJX3xvnDppyo6cR8bs7Xavs1i4RXc7RqGxZq7IlM5cXLYkp2iHoh5a8cajfgzbzGMNQrZbAOv8cEZdGlKU4-obGuDtuZ5BPaPTUIhUaKQObEIdb3uYZM1G46Xw', '2024-04-25 01:12:51');
INSERT INTO `sys_token_black_list` VALUES ('1783182492769996801', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzk3ODc4MCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxNDA2NTE4MCwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.PJEkWZYNs3r12a4Le66rtDf-rHIOyumF--NlIGiEjja4VXkhlxps5U5RRxsX96KO3gcE0V1-Yya1cDeYxdj-a0dLaM1mFlEdkRigaK-9b16gLKzYkqVqxumW7cgS23gD76PPfoL-u10bI7-uVILpZkf9BzAw9lhq8sqmB51vP_CEuOJ_NPBSxMgkwaAnQMXbKp2NNHCrbThrbjNO7C5D0tiiw5xKwN6MToeKhDATNm5TxYWfuDI9CtAJ6A2iZfY4hwGyDaqB43wW9qwSg6wblPvMCD99tCjtWV976DB7Z37LINfK2Jl4cYs_ZFjrpXfliqtv0QLVc1H7mHZgA8zwjQ', '2024-04-25 01:13:31');
INSERT INTO `sys_token_black_list` VALUES ('1783182558779953154', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMzk3ODgxOCwibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzE0MDY1MjE4LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.QFZQuYCiJJXm254qZjRIyWZfOXqmY3ySAFSZmz4o4GgmA91sg8khx-QUGwf8SHbSCgpRKXJiVv980y_BAqaTQvhRKgrDwUCePFNzOxNGrNpj4q9LxL_rb0mr0ouJmKe6dTNsZOYT30UimuIZtNj8KUsOyO5gNAe0IWYTvFSpURRCoswXIRVixQyuSNAoExpd_fXUXh2Qt-L8C5lUWMl55XepkSZhRIzCWm1sEsXse5vcGUo7eLTEG4ylHeF7gIcr9vyIS58bDO8nI7iY-kW6owSk92QMWiZscKBdL-jmw18P0W-n38yaeyCLfD4U6xe1AcB6-NE-ZE7Uxy_-gsZOaw', '2024-04-25 01:13:47');
INSERT INTO `sys_token_black_list` VALUES ('1783182862267207681', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMSIsIm5iZiI6MTcxMzk3ODgzNywibG9naW5fYWNjb3VudF9pZCI6IjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJhZG1pbiIsImxvZ2luX2FjY291bnRfbmFtZSI6ImFkbWluIiwiZXhwIjoxNzE0MDY1MjM3LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6ImFkbWluIn0.BMqteDDrtkbIfl-u23rL-aGerHE9WYKDqL_etJKYcaCMnkNeIsk91YEIvuX17HaGuaVpOx2iYRK1trQwlerGXsc-WzYEHUKNp-ROJM-uDjcn-oUtIdFllmdSGvLLogSIHYUKdenb1mS1M2ONycffGSH7wSz_jcdWRgXHjEspCjjUe07un9gRGz16-ad4FeI2JBqiQXIln8copbMVXa4bV7-s2dY-6Ym4IsF-5Xb7GbHQF8dEJbnHok4mDVxFupsf6KOCdYpTiE8WdFhzq8-UJbPqJD0l-_4LRI7LIEUdDu9ZqlhjuxZi-yFmfEeBKvYG-vbxA_FXGZDEcbYhUxjrsg', '2024-04-25 01:14:59');
INSERT INTO `sys_token_black_list` VALUES ('1783183203620638721', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk3ODkwOCwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwNjUzMDgsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.NgJcV6wOI4n8agPR_ufiOpsydyplk2pvSkU_JpbEOqbyqxHvRQzfgGWD4_qg-_AWLQyM2dJMniwZ_8dgGJ1-T0ynwaHMfAu2_TD7FdChD11RXq-DjVOI5m7P5aOzcOwSGhrS99qDoZVctUcWm_uxUQMeqUgFU7dbyOgNdPksXy4GyI36qTsqxlXj6LNDDRW5n373zNTMtQvZSt4TDeZ5chSAh4r36O5-6rDY4B0T86DyTKBuZIWODJXrgDGjrImuQLhV0ipqFdwflPw63kzr7Xsh0qDf4U6hlaRtPV0FG9NydAwMwAsmFVJWX1As5aLk9-oid8INwaQq5Ii4HeLgCA', '2024-04-25 01:16:20');
INSERT INTO `sys_token_black_list` VALUES ('1783183819495460865', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc2NzQ1Mjg5ODMzMjY3MyIsIm5iZiI6MTcxMzk3ODk5MSwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3Njc0NTI4OTgzMzI2NzQiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wIiwibG9naW5fYWNjb3VudF9uYW1lIjoibHd4c2hvcCIsImV4cCI6MTcxNDA2NTM5MSwianRpIjoiMS4wIiwibG9naW5fdW5hbWUiOiJsd3hzaG9wIn0.J-hoeOQScNT8qv7_ZN_LpQIAjhk6H1h5SGtbJ56MGcMXaCmy6tRqiXHvuSkRwJCF3WeNHWb8HFXKxUw1ByWt6M2_a8EewpFMgFtmVkzGewMXqwPakB-wCfyanqely6yWK10p2PUif5vB91qs-otlGZQUSBm8pcmHyz1RAmupwSD40rz2pKTIPc7na0yshlTL7ej6ujTNwtlTzAQfQHc4veFlGP4GwdO6YlGksqd7RB2b2NPKx0rjkIhENVCdJROnHrzbHX7CdlQi_pGF4R7MrCUHk7H8SsH2PRn0psfM1mL-xbLdaiesdJlij4sIjJIOaTyakYG_3b30Ek-cjIBNJw', '2024-04-25 01:18:47');
INSERT INTO `sys_token_black_list` VALUES ('1783449624376303618', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc2Nzc1OTYxMDQ5MzU3MTA3MyIsIm5iZiI6MTcxMzk3OTIyNiwibG9naW5fYWNjb3VudF9pZCI6IjE3Njc3NTk2MTA1NDM5MDI3MjEiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3giLCJsb2dpbl9hY2NvdW50X25hbWUiOiJsd3giLCJleHAiOjE3MTQwNjU2MjYsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoibHd4In0.QqdPELOhYSWJ1rEeraMp9k10eXD_iHkJwz6TU9HA1GZ6zfuWoW3twaPzu3CmjkIx3ThAmCmmPDxIZJnHczNGis-p9Bn8GVhXWoiVgIr9y1oCzNVjFgA1DD-b_d36kFPRHC9pK4ZfbPZqATjYUVO1Pk92a4WmuY85_tckEowZwSHtWYUeE6xQrCs8rn-hC_Sl4Q3mJJSc4nOJS_r881PHZ_ZMjag_u1M2RasiFJqBNyQEdjClNKF0KxtM5uGzyTiZcVbRrwwp-oA7uLrbgUV4ajhHjgios7T-cdOvlWzGbox7dXYstYB0caXeWx6Rg6-2oQwkFShxkACdS8TLFxn8jw', '2024-04-25 18:55:00');
INSERT INTO `sys_token_black_list` VALUES ('1784509796137840642', 'eyJhbGciOiJSUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxIiwic3ViIjoiTFdYLUF1dGgtTWFuYWdlciIsImF1ZCI6bnVsbCwibG9naW5fdWlkIjoiMTc4MzQ0OTc0NjM5Njk5NTU4NSIsIm5iZiI6MTcxNDIzNjg1NywibG9naW5fYWNjb3VudF9pZCI6IjE3ODM0NDk3NDY0NTk5MTAxNDUiLCJpc3MiOiJMd3giLCJsb2dpbl9sb2dpbm5hbWUiOiJsd3hzaG9wMSIsImxvZ2luX2FjY291bnRfbmFtZSI6Imx3eHNob3AxIiwiZXhwIjoxNzE0MzIzMjU3LCJqdGkiOiIxLjAiLCJsb2dpbl91bmFtZSI6Imx3eHNob3AxIn0.TPx3QDgYJtx0KTYrAE6ydt4G1i_S7O62s479xmxd6bo5yPCndw90eIt4co74Lji-aJx7-tNqVu6RXJF2NqpDqMsJoD_UMrHXXcNjFEyCjP_QWdGpf4TGrwfRyPEa_n1jM52StwWmX3uxe445Hs25H_k3LrRvK2YUpZLyk_9-lKA3DsX7SzElfqFFqSiIHCTgG8PmV_vci8O6Zq3te7vovzGS5NJmxZcNbjtYvhwIdaFkWOkU0gWsWaCEwXbF31XrcBM0yeUGfu68LjVMZ1tNZjGYH2xsJL8z8pRiU-B5VF99yxvECL56gPB-nw7f0mIHetBLATr6igA5l6kp1xg3pg', '2024-04-28 17:07:45');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '多租户应用ID',
  `user_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `account_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号名',
  `employee_type` int NULL DEFAULT NULL COMMENT '用户类型 0内部 1外部',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态 0正常 1锁定 2删除',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮件',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '移动电话',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司ID',
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门ID',
  `company_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司名称',
  `org_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门名称',
  `province_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '省ID',
  `city_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '市ID',
  `county_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '县ID',
  `province_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '省名称',
  `city_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '市名称',
  `county_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '县名称',
  `created_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `account_number` int NOT NULL DEFAULT 1 COMMENT '账号数',
  `sn` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `facsimile_telephone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nation` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gender` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` datetime NULL DEFAULT NULL,
  `password_modified_date` datetime NULL DEFAULT NULL,
  `c` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `religion` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `telephone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `id_card_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `employee_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_level` int NULL DEFAULT NULL COMMENT '填写为数字，要求准确。例如：“12”表示12级',
  `level_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '岗位名称。如：IT规划，系统管理员等',
  `category` int NULL DEFAULT NULL COMMENT '定义员工套入职级，员工竞聘的岗位职级和实际职级会存在不一致，例如新员工入职后竞聘的岗位为10级，但是新员工实际职级可能只有7级，则7级为其套入职级。',
  `functions` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '定义用户的业务职责编码，可为多值。例如：“财务”',
  `display_order` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `duty` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `position_level` int NULL DEFAULT NULL,
  `supporter_corp_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `supporter_dept` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `supporter_corp_contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `supervisor` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `post` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_role_id` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `idx_sys_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_sys_user_appid`(`app_id` ASC) USING BTREE,
  INDEX `idx_sys_user_loginname`(`account_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '账号信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'admin', 1, '0', 'em', '123', '1', '10363', 'com', 'org', 'p', 'c', 'c', NULL, 'c', 'c', '4', '2024-05-13 18:46:11', '42', '2024-05-13 18:46:14', 1, 'sn', '212', 'des', 'na', 'ge', '2024-05-13 18:45:51', '2024-05-13 18:46:04', 'c', 're', '123', '2024-05-13 18:45:58', '2024-05-13 18:46:00', '12', '242', 424, '242', 242, '242', '24', 'du', 1, 'scn', 'sd', 'scc', 'sup', 'p', 'u', 'u', '1');
INSERT INTO `sys_user` VALUES ('1826146683910623233', '1', 'ttt', 'ttt', 1, '2', 'ttt', 'ttt', 'uuu', 'uuuu', NULL, NULL, 'ttt', 'ttt', 'ttt', NULL, NULL, NULL, 'admin', '2024-08-21 14:37:52', 'admin', '2024-08-21 14:37:52', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_verification_code_cache
-- ----------------------------
DROP TABLE IF EXISTS `sys_verification_code_cache`;
CREATE TABLE `sys_verification_code_cache`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_verification_code_cache
-- ----------------------------
INSERT INTO `sys_verification_code_cache` VALUES ('ANkjMcqUVtJmdSQzsG', 'avfr', '2024-03-13 11:40:46');
INSERT INTO `sys_verification_code_cache` VALUES ('ATckBWrkkYryHJxqlb', 'wkri', '2024-04-06 15:56:23');
INSERT INTO `sys_verification_code_cache` VALUES ('AyQXxtmLbLwLFUnGtt', 'yc7h', '2024-05-01 02:07:47');
INSERT INTO `sys_verification_code_cache` VALUES ('BBIsWXIbKjfaUUtnAz', 'vvpu', '2024-03-13 11:38:35');
INSERT INTO `sys_verification_code_cache` VALUES ('BZAHkYEgqtVhzOWeQj', 'yeft', '2024-04-24 17:37:40');
INSERT INTO `sys_verification_code_cache` VALUES ('BaRKGgiuXtlrrpknMt', 'fbre', '2024-04-17 22:44:55');
INSERT INTO `sys_verification_code_cache` VALUES ('CToxDdeZzUPiyQzuYV', 'pedd', '2024-04-06 00:09:51');
INSERT INTO `sys_verification_code_cache` VALUES ('CZzLRSQVCePvvAXzBJ', 'anxj', '2024-09-09 08:43:56');
INSERT INTO `sys_verification_code_cache` VALUES ('CcnIQPqwDZIdIUxamT', 'yhqd', '2024-03-13 11:31:37');
INSERT INTO `sys_verification_code_cache` VALUES ('CikuJAGkaZwPzIarky', 'ndmf', '2024-03-18 18:33:51');
INSERT INTO `sys_verification_code_cache` VALUES ('CqTGQxCuvZfBRsatPZ', 'exmn', '2024-04-06 15:41:03');
INSERT INTO `sys_verification_code_cache` VALUES ('CwtUGeDFNWNSGjfbqL', 'wzi4', '2024-03-13 11:57:54');
INSERT INTO `sys_verification_code_cache` VALUES ('DBTpjHjDDBmnyFLwWg', 'lrc3', '2024-04-06 14:59:03');
INSERT INTO `sys_verification_code_cache` VALUES ('DNqJxyMtBEHpyjotMd', 'vnnq', '2024-08-20 17:00:57');
INSERT INTO `sys_verification_code_cache` VALUES ('DOIoRnbonQyhKpctXQ', 'dai2', '2024-03-13 12:19:23');
INSERT INTO `sys_verification_code_cache` VALUES ('DPXGJZarhfifUmdgKP', 'dl5x', '2024-04-06 15:51:15');
INSERT INTO `sys_verification_code_cache` VALUES ('DQRZokORKdimrzFihJ', 'qyqi', '2024-03-13 12:03:53');
INSERT INTO `sys_verification_code_cache` VALUES ('DSVbCtdWzCiyCvsPaV', 'vz2q', '2024-03-25 00:33:08');
INSERT INTO `sys_verification_code_cache` VALUES ('DVigdCfknXPSgXolpk', 'wmjt', '2024-03-18 17:16:00');
INSERT INTO `sys_verification_code_cache` VALUES ('DolQkHUUHmlurbMnaz', 'rxtf', '2024-03-13 12:11:04');
INSERT INTO `sys_verification_code_cache` VALUES ('DsFQXZKLPtWzkvSIct', 'qk34', '2024-03-20 10:25:16');
INSERT INTO `sys_verification_code_cache` VALUES ('EOQDcjgFYnJzgyhzAN', 'hqbx', '2024-03-13 11:57:19');
INSERT INTO `sys_verification_code_cache` VALUES ('EOtKtSqIoieENWfmrU', 'elsx', '2024-09-06 08:43:53');
INSERT INTO `sys_verification_code_cache` VALUES ('EPebUOzrPKRDEYCVKT', 'qv3e', '2024-04-06 00:16:45');
INSERT INTO `sys_verification_code_cache` VALUES ('ESHyyxwfxnThYxESpB', '4ue5', '2024-04-25 01:13:31');
INSERT INTO `sys_verification_code_cache` VALUES ('EjkDxaeEHdctSmkAzj', 'zx43', '2024-04-25 18:55:29');
INSERT INTO `sys_verification_code_cache` VALUES ('ElkFuiXsDHVveOqLMO', 'bfnr', '2024-03-18 18:32:49');
INSERT INTO `sys_verification_code_cache` VALUES ('EwHtQGiXRbMHnTgEXf', 'j6vp', '2024-03-25 16:30:53');
INSERT INTO `sys_verification_code_cache` VALUES ('FTSFHUIniIKGDqBkoN', 'v2ls', '2024-03-13 12:17:26');
INSERT INTO `sys_verification_code_cache` VALUES ('FUcabLqnAyqPGTEjwT', 'cfqq', '2024-03-18 17:36:34');
INSERT INTO `sys_verification_code_cache` VALUES ('FUdPnksdlCWEexlRJI', 'qhux', '2024-04-24 17:17:25');
INSERT INTO `sys_verification_code_cache` VALUES ('FXSIqNPysiKJMctrqX', 'qyjg', '2024-03-13 12:19:39');
INSERT INTO `sys_verification_code_cache` VALUES ('FfYzNZJrNkLJKDBmzs', '3ean', '2024-05-01 02:07:23');
INSERT INTO `sys_verification_code_cache` VALUES ('GDbDqQLiAbUwzqhDBD', 'abl3', '2024-04-06 14:29:13');
INSERT INTO `sys_verification_code_cache` VALUES ('GRVkyvNvNYtFzvRBqC', 'fhxl', '2024-08-20 14:56:40');
INSERT INTO `sys_verification_code_cache` VALUES ('GTxCBHLDfodQkDovmx', 'ta3v', '2024-04-24 00:11:59');
INSERT INTO `sys_verification_code_cache` VALUES ('GYfwsjoxneqeEegTJr', 'bfqk', '2024-04-06 00:04:07');
INSERT INTO `sys_verification_code_cache` VALUES ('GpciAVBINHmCQNTbPc', 'ynkw', '2024-03-25 00:39:16');
INSERT INTO `sys_verification_code_cache` VALUES ('GtPJNijyomaSeOSEzX', 'kjqc', '2024-04-06 15:49:55');
INSERT INTO `sys_verification_code_cache` VALUES ('HClJuXkvrFkqiSoBNY', '2nsa', '2024-03-13 12:03:54');
INSERT INTO `sys_verification_code_cache` VALUES ('HJuZlhTVKyBMXFlXNm', 'yhkc', '2024-04-07 12:32:43');
INSERT INTO `sys_verification_code_cache` VALUES ('HOehTYvMKlqOYkHaAt', 'dqt7', '2024-04-16 23:06:29');
INSERT INTO `sys_verification_code_cache` VALUES ('HjHYysmqkRugqXyNbq', 'a8xl', '2024-04-06 00:10:34');
INSERT INTO `sys_verification_code_cache` VALUES ('HkdVxIuCyZqzvoYNjT', 'xxjx', '2024-03-18 18:25:35');
INSERT INTO `sys_verification_code_cache` VALUES ('HuVdBbnXXEpWuAvSfV', 'ytgb', '2024-04-05 23:58:47');
INSERT INTO `sys_verification_code_cache` VALUES ('HwMzmIFGOmyVaybmUD', 'wihf', '2024-03-13 12:11:36');
INSERT INTO `sys_verification_code_cache` VALUES ('IEWcMCxpdrnPmlFtMH', 'serq', '2024-04-24 17:28:56');
INSERT INTO `sys_verification_code_cache` VALUES ('IdoYFWekBfkFYkhGdA', 'xjiw', '2024-04-25 18:55:02');
INSERT INTO `sys_verification_code_cache` VALUES ('IpHYnXbhxeniBHhoHS', 'bkyp', '2024-08-20 15:00:22');
INSERT INTO `sys_verification_code_cache` VALUES ('JBfBZnRrMyfuomVIJq', 'lklw', '2024-05-01 02:08:46');
INSERT INTO `sys_verification_code_cache` VALUES ('JIbxrwURsFkmazdPtl', 'pzuy', '2024-04-08 00:58:00');
INSERT INTO `sys_verification_code_cache` VALUES ('JQHklQMplaCjkQrnJv', 'kp6n', '2024-08-21 16:31:45');
INSERT INTO `sys_verification_code_cache` VALUES ('JSASEWKUIlfhCdzTfr', 'acfp', '2024-05-01 02:10:08');
INSERT INTO `sys_verification_code_cache` VALUES ('JsFiYRSRoMKkIUHLUo', 'jtms', '2024-04-06 00:11:30');
INSERT INTO `sys_verification_code_cache` VALUES ('JwfBNzsSUYKwlBLKPd', '4kvf', '2024-03-18 17:30:04');
INSERT INTO `sys_verification_code_cache` VALUES ('KJbLwdlaqiFVpGNAPE', 'ydtx', '2024-08-30 13:57:52');
INSERT INTO `sys_verification_code_cache` VALUES ('KNciMbCVKplNeegjSC', 'swu3', '2024-04-24 20:43:23');
INSERT INTO `sys_verification_code_cache` VALUES ('KkWbBzwhcCHGugpvaR', 'jhdk', '2024-09-04 12:45:06');
INSERT INTO `sys_verification_code_cache` VALUES ('LSVOvjidRMJePsUZWP', 'avut', '2024-04-06 15:17:07');
INSERT INTO `sys_verification_code_cache` VALUES ('LaUogVKHKrfPoBRHeY', 'zwwb', '2024-03-25 16:32:04');
INSERT INTO `sys_verification_code_cache` VALUES ('LnJeIVvuvSneGhmqAr', '2n5u', '2024-03-18 18:18:56');
INSERT INTO `sys_verification_code_cache` VALUES ('LtQawMnYgXGdxEWiSC', 'yxaq', '2024-04-06 15:29:19');
INSERT INTO `sys_verification_code_cache` VALUES ('MMVmoKnIxCyuHKShRS', 'l8pe', '2024-04-08 01:01:23');
INSERT INTO `sys_verification_code_cache` VALUES ('MTQYzwRWCEFnDoJSsw', 'cuqr', '2024-04-06 14:14:25');
INSERT INTO `sys_verification_code_cache` VALUES ('MWzirwRyAMqQQYBszh', 'hmjv', '2024-03-13 12:21:40');
INSERT INTO `sys_verification_code_cache` VALUES ('MaksxwZeSlILmuyJAd', 'uls4', '2024-08-20 17:03:49');
INSERT INTO `sys_verification_code_cache` VALUES ('MmruvPPgoTHTDVMpeH', 'h6fe', '2024-04-24 17:20:48');
INSERT INTO `sys_verification_code_cache` VALUES ('MwdqvVFNxVopecILYp', 'nvcv', '2024-03-20 12:57:12');
INSERT INTO `sys_verification_code_cache` VALUES ('NIZRTHgUAErrnTWdjB', 'pnwd', '2024-09-07 08:34:23');
INSERT INTO `sys_verification_code_cache` VALUES ('OdqCNjMJyMzZxtgxRD', 'wfyn', '2024-03-18 10:43:29');
INSERT INTO `sys_verification_code_cache` VALUES ('OsibjFgrYoDPUufnPI', 'hhuh', '2024-03-14 20:19:21');
INSERT INTO `sys_verification_code_cache` VALUES ('PINSoJssltaIUpXuVR', 'apfr', '2024-04-24 17:38:39');
INSERT INTO `sys_verification_code_cache` VALUES ('PRmTcYfNqhkkRgminJ', 'wrav', '2024-03-13 12:05:09');
INSERT INTO `sys_verification_code_cache` VALUES ('PfHjsCCFkxpuoRqypm', 'x2qw', '2024-03-25 16:22:55');
INSERT INTO `sys_verification_code_cache` VALUES ('QGmwtErROJCBqdTTRa', 'i4fv', '2024-08-22 13:54:06');
INSERT INTO `sys_verification_code_cache` VALUES ('QQkLfmlxqIzTZNzdJb', 'nqb7', '2024-03-13 11:37:08');
INSERT INTO `sys_verification_code_cache` VALUES ('QUfbzPwYktCUgqoKwE', 'u8x5', '2024-04-07 00:39:36');
INSERT INTO `sys_verification_code_cache` VALUES ('QXLjdWvbszIMuSnIzj', 'vdic', '2024-04-06 15:30:02');
INSERT INTO `sys_verification_code_cache` VALUES ('QbqglLIeOxKQdFZOCz', 'vrew', '2024-03-13 12:17:27');
INSERT INTO `sys_verification_code_cache` VALUES ('QjWwSNJnYZqwbHgTxL', 'sv2p', '2024-05-04 00:08:18');
INSERT INTO `sys_verification_code_cache` VALUES ('QjlrCVwMhNLFcflZJB', 'h7zd', '2024-04-08 01:00:44');
INSERT INTO `sys_verification_code_cache` VALUES ('QuEvCxxNGDOBmPiDzy', 'bxxy', '2024-03-18 17:37:00');
INSERT INTO `sys_verification_code_cache` VALUES ('QurHbvzvdfUYiujwmo', 'fruv', '2024-03-19 12:52:29');
INSERT INTO `sys_verification_code_cache` VALUES ('RCMSxqvGQLHSUQhnuX', 'bu3h', '2024-03-13 11:58:12');
INSERT INTO `sys_verification_code_cache` VALUES ('RcpngjPesBOcaeZekF', 'wkuh', '2024-03-13 11:48:17');
INSERT INTO `sys_verification_code_cache` VALUES ('SAdcfgjittgBpxSgGQ', 'qvkl', '2024-04-08 01:00:48');
INSERT INTO `sys_verification_code_cache` VALUES ('SxHutsrtjJcpHGnIGP', 'lzr3', '2024-04-25 01:18:47');
INSERT INTO `sys_verification_code_cache` VALUES ('SyEluFXRcTIXTczcWG', 'svnn', '2024-04-24 17:18:38');
INSERT INTO `sys_verification_code_cache` VALUES ('TPYTnRfSGPQlkOtfmh', 'tf8h', '2024-03-13 12:04:08');
INSERT INTO `sys_verification_code_cache` VALUES ('TRcInEfhxSRROwUFaF', '3mhh', '2024-05-13 18:41:33');
INSERT INTO `sys_verification_code_cache` VALUES ('TYkuYUmecOphcLyXym', '8tbt', '2024-03-13 11:48:29');
INSERT INTO `sys_verification_code_cache` VALUES ('TnWfrDBLWQDBthtJMg', 'uutm', '2024-05-01 02:21:37');
INSERT INTO `sys_verification_code_cache` VALUES ('TwjPeYxoNjwwmWyPOK', '4526', '2024-04-12 00:13:40');
INSERT INTO `sys_verification_code_cache` VALUES ('UHriRjRPgldtyKVjEy', 'xfqm', '2024-04-06 14:11:59');
INSERT INTO `sys_verification_code_cache` VALUES ('UTquKhQAoeBiJUVOhc', 'ugce', '2024-04-06 15:16:51');
INSERT INTO `sys_verification_code_cache` VALUES ('UicorbLnOtfydtiXpz', 'shxi', '2024-04-24 17:13:11');
INSERT INTO `sys_verification_code_cache` VALUES ('UyzwwqoFiZgKsTPjxE', 'uwry', '2024-08-20 16:57:17');
INSERT INTO `sys_verification_code_cache` VALUES ('VcerBSRZuZkQskYgsD', 'aezz', '2024-05-01 02:17:45');
INSERT INTO `sys_verification_code_cache` VALUES ('VgVeryMlInnpfCfjxo', '6yhm', '2024-04-24 17:11:11');
INSERT INTO `sys_verification_code_cache` VALUES ('VnzBguWkhvlWVFdlhJ', 'mubv', '2024-03-13 11:09:17');
INSERT INTO `sys_verification_code_cache` VALUES ('VsBUDebkkeaxcgTlLm', 'xneb', '2024-05-01 02:17:20');
INSERT INTO `sys_verification_code_cache` VALUES ('VvYPuxMsEZesyGrxff', 'cnaq', '2024-04-06 16:03:55');
INSERT INTO `sys_verification_code_cache` VALUES ('WIKCwplrbhPlSXKBLz', 'j2dq', '2024-08-22 13:56:52');
INSERT INTO `sys_verification_code_cache` VALUES ('WtDjljELxXiqiYXqcL', 'jfit', '2024-03-18 18:19:54');
INSERT INTO `sys_verification_code_cache` VALUES ('WyOhttrwodsSmhtsKI', 'jtkl', '2024-04-16 22:23:32');
INSERT INTO `sys_verification_code_cache` VALUES ('WzJQkQRtqvwItpQfpX', 'tajn', '2024-04-05 23:59:36');
INSERT INTO `sys_verification_code_cache` VALUES ('XayoBaBnWTfKHBnSvJ', 'saev', '2024-03-13 12:12:04');
INSERT INTO `sys_verification_code_cache` VALUES ('XoKdOuBIhagpLYsWlz', 'mbqf', '2024-09-02 08:53:34');
INSERT INTO `sys_verification_code_cache` VALUES ('XxJdtfhvhMnvEQCcjM', 'dbks', '2024-05-01 02:19:46');
INSERT INTO `sys_verification_code_cache` VALUES ('YCahuQaKUzsguwYSoQ', '6efp', '2024-03-18 18:33:13');
INSERT INTO `sys_verification_code_cache` VALUES ('YDCkgZBNgvKCoOtcxG', '3bbc', '2024-03-13 11:38:48');
INSERT INTO `sys_verification_code_cache` VALUES ('YREvvEZeKTKInxWMln', 'np8d', '2024-04-06 16:05:22');
INSERT INTO `sys_verification_code_cache` VALUES ('YbXIcyHXmUcvdHNUht', '2xwc', '2024-04-06 00:00:31');
INSERT INTO `sys_verification_code_cache` VALUES ('YgYlzmqXgfovQIuXGS', 'czlu', '2024-04-24 17:29:27');
INSERT INTO `sys_verification_code_cache` VALUES ('ZCplFQoaRxYgZGKHlq', 'menb', '2024-09-07 14:35:58');
INSERT INTO `sys_verification_code_cache` VALUES ('ZUpRdPxhHIUzMPlrMg', 'tpkc', '2024-03-13 12:12:40');
INSERT INTO `sys_verification_code_cache` VALUES ('ZXgzVpFkEMSkDEzIAN', 'xhk2', '2024-04-08 01:11:13');
INSERT INTO `sys_verification_code_cache` VALUES ('ZZMVWkQDkxNanIEiTY', 'yumz', '2024-04-06 15:19:16');
INSERT INTO `sys_verification_code_cache` VALUES ('ZefPtxOSZxBxovaOQA', 'taxe', '2024-04-06 14:16:56');
INSERT INTO `sys_verification_code_cache` VALUES ('ZkxEcrwNhHzYPtqQdA', 'cznc', '2024-09-05 11:12:11');
INSERT INTO `sys_verification_code_cache` VALUES ('aJJCXnVYoydwaIIWfH', 'tpqn', '2024-04-06 15:54:43');
INSERT INTO `sys_verification_code_cache` VALUES ('aLXInpJKimgTWwgnYS', 'h8mn', '2024-03-13 12:05:37');
INSERT INTO `sys_verification_code_cache` VALUES ('acBHfyzqxcWaBgvtCV', '8rn7', '2024-04-25 18:55:00');
INSERT INTO `sys_verification_code_cache` VALUES ('aqUKVZUElYyRUmIhXl', 'rarq', '2024-04-06 00:19:13');
INSERT INTO `sys_verification_code_cache` VALUES ('avURpSflmUYOzLlWXc', 'fcim', '2024-04-06 00:01:17');
INSERT INTO `sys_verification_code_cache` VALUES ('bHFTLupiOLQEHWtECu', 'knva', '2024-03-13 20:13:28');
INSERT INTO `sys_verification_code_cache` VALUES ('bPmnDVKJvlFWWPXlgX', 't2c2', '2024-04-24 17:11:15');
INSERT INTO `sys_verification_code_cache` VALUES ('bfyXlDkhQclMIefaaF', 'kaer', '2024-04-06 00:01:41');
INSERT INTO `sys_verification_code_cache` VALUES ('bpUpBPGCguMMBkAdDP', '7dc8', '2024-03-13 12:20:53');
INSERT INTO `sys_verification_code_cache` VALUES ('cdudIoVyrzhqOIEyes', 'a2ke', '2024-04-23 22:05:50');
INSERT INTO `sys_verification_code_cache` VALUES ('crblCQcoahQLggpRDy', 'hy3a', '2024-04-08 01:02:07');
INSERT INTO `sys_verification_code_cache` VALUES ('czhJneugwNZjJUdrFS', '7ecl', '2024-04-25 01:12:51');
INSERT INTO `sys_verification_code_cache` VALUES ('dCezXeRykztSwzWTCr', 'qmtx', '2024-04-24 16:51:01');
INSERT INTO `sys_verification_code_cache` VALUES ('dGgaVGpMLnkjYlhEKy', 'btcs', '2024-04-24 20:38:41');
INSERT INTO `sys_verification_code_cache` VALUES ('dcoLWzHURraofKiNAx', 'ltlb', '2024-03-18 18:32:22');
INSERT INTO `sys_verification_code_cache` VALUES ('dtlVPNlVxJpqETspcU', 'ki43', '2024-04-06 16:03:42');
INSERT INTO `sys_verification_code_cache` VALUES ('dxhabtuInEffCBdbxa', 'wqc3', '2024-03-13 12:11:58');
INSERT INTO `sys_verification_code_cache` VALUES ('eHGjhLxZgNtMhazCpq', 'xkpc', '2024-04-12 00:46:11');
INSERT INTO `sys_verification_code_cache` VALUES ('eILxLsJpRCpHFTTTfE', 'hhuc', '2024-04-23 21:18:37');
INSERT INTO `sys_verification_code_cache` VALUES ('eWYpviPtXelRnfJFIq', 'dpnc', '2024-03-18 17:31:23');
INSERT INTO `sys_verification_code_cache` VALUES ('eaufhWbUXUlqQorvMZ', 'e8at', '2024-08-21 16:32:18');
INSERT INTO `sys_verification_code_cache` VALUES ('efDKPhgqqWDmlgbpJF', 'czle', '2024-04-06 16:05:07');
INSERT INTO `sys_verification_code_cache` VALUES ('fAnqWIwhrpyPsekZCx', 'jyuq', '2024-09-04 08:38:06');
INSERT INTO `sys_verification_code_cache` VALUES ('fGqPzbqyeQtMGYXoAs', 'wwdj', '2024-04-06 14:56:29');
INSERT INTO `sys_verification_code_cache` VALUES ('fTUhIlOlghStaTqlwQ', 'njwt', '2024-04-23 21:18:55');
INSERT INTO `sys_verification_code_cache` VALUES ('fUEyYjkkmbSxWsojrP', 'crp2', '2024-04-06 14:11:57');
INSERT INTO `sys_verification_code_cache` VALUES ('fcZxmHxqJqRnJPgKNh', 'lddp', '2024-04-28 00:53:09');
INSERT INTO `sys_verification_code_cache` VALUES ('fhJrrDHqGrTweHkdYI', 'nhvc', '2024-03-18 17:38:24');
INSERT INTO `sys_verification_code_cache` VALUES ('fsyHfNVQZAXtRercla', 'murv', '2024-04-24 17:28:58');
INSERT INTO `sys_verification_code_cache` VALUES ('gWwPuNXDwbmSrNkkpB', 'hg2v', '2024-08-20 18:04:47');
INSERT INTO `sys_verification_code_cache` VALUES ('gWzEJklPKVrMweaxzI', 'k7ub', '2024-04-06 00:12:02');
INSERT INTO `sys_verification_code_cache` VALUES ('gcssxcmdRKtotGsfPK', 'rg2b', '2024-03-13 12:05:57');
INSERT INTO `sys_verification_code_cache` VALUES ('gsiPVgUOecHLSkhvMV', 'xpdv', '2024-03-18 17:21:25');
INSERT INTO `sys_verification_code_cache` VALUES ('gyFDDLVYXlmBgvTXoJ', 'qycg', '2024-08-21 16:32:22');
INSERT INTO `sys_verification_code_cache` VALUES ('gypwQQameWoSDqwaYS', 'wyka', '2024-04-25 01:14:59');
INSERT INTO `sys_verification_code_cache` VALUES ('hKwTeKoJxKGDRlMnGX', 'mqdl', '2024-05-01 14:41:18');
INSERT INTO `sys_verification_code_cache` VALUES ('hLFafBMXvqIjpcPvNP', 'h5j2', '2024-04-24 00:20:09');
INSERT INTO `sys_verification_code_cache` VALUES ('hpymLzKtHtFuWdWwzF', '4kud', '2024-04-09 12:47:15');
INSERT INTO `sys_verification_code_cache` VALUES ('hswckKJyODGoSTtiiN', '5acc', '2024-04-06 00:11:26');
INSERT INTO `sys_verification_code_cache` VALUES ('hvkamzYyeskiRPiWsK', 'p8sl', '2024-08-20 14:55:22');
INSERT INTO `sys_verification_code_cache` VALUES ('iQCEruTzpxvKtkZomg', 'nqqp', '2024-05-01 02:15:49');
INSERT INTO `sys_verification_code_cache` VALUES ('iisoGbtObqNtmmxwjD', 'y2rn', '2024-03-13 12:19:13');
INSERT INTO `sys_verification_code_cache` VALUES ('ilNSozTFqMoLOwDJjx', '8t6g', '2024-08-20 15:11:08');
INSERT INTO `sys_verification_code_cache` VALUES ('izBaLpWURLpTndRwSG', 'hh2y', '2024-04-07 23:42:53');
INSERT INTO `sys_verification_code_cache` VALUES ('jHwpSzZXKVlQbpztUw', 'aywq', '2024-04-06 14:08:29');
INSERT INTO `sys_verification_code_cache` VALUES ('jQxZYphbjwJRYFtfda', 'mq5a', '2024-04-06 15:28:23');
INSERT INTO `sys_verification_code_cache` VALUES ('jmpzEmpEfIBsWMbhPf', 'bitr', '2024-04-24 17:21:05');
INSERT INTO `sys_verification_code_cache` VALUES ('kIrQmHoRlvPKdltGrf', 't5cg', '2024-03-25 01:00:07');
INSERT INTO `sys_verification_code_cache` VALUES ('kJgJSqwywujzSgCFYp', 'wj2d', '2024-03-13 12:04:32');
INSERT INTO `sys_verification_code_cache` VALUES ('kLHeCkkmNuCSyqqUKJ', 'nttu', '2024-04-06 15:41:06');
INSERT INTO `sys_verification_code_cache` VALUES ('kfddbvkPJcIsteKyNE', 'xhnq', '2024-03-13 12:12:57');
INSERT INTO `sys_verification_code_cache` VALUES ('khixSgCggkRpPJgGCp', 'lwrg', '2024-04-06 14:58:35');
INSERT INTO `sys_verification_code_cache` VALUES ('kuzWRNMztNlvDGlJil', 'tpph', '2024-04-28 17:07:45');
INSERT INTO `sys_verification_code_cache` VALUES ('lVdIhRNxZooexxCNJA', 'l3hy', '2024-03-13 11:48:14');
INSERT INTO `sys_verification_code_cache` VALUES ('llHOzsONlpzYjKaqBN', 'pdqh', '2024-03-13 12:06:21');
INSERT INTO `sys_verification_code_cache` VALUES ('lslFAFbeyFgnYBMZzL', 'cefu', '2024-03-13 12:12:26');
INSERT INTO `sys_verification_code_cache` VALUES ('mWAMPIlveDzDldPRVQ', 'bye4', '2024-04-06 00:18:01');
INSERT INTO `sys_verification_code_cache` VALUES ('mjlCNTLoqiSBdEIDru', 'r2ur', '2024-03-13 12:05:06');
INSERT INTO `sys_verification_code_cache` VALUES ('mvBZyAkWlbuGPLQQsE', 'zntp', '2024-03-25 20:03:48');
INSERT INTO `sys_verification_code_cache` VALUES ('nAWJLMbaCLYtjWDsFO', 'adwb', '2024-05-13 18:41:23');
INSERT INTO `sys_verification_code_cache` VALUES ('nNWgKbUnLhOYVVUbCM', 'bpcq', '2024-04-06 00:02:14');
INSERT INTO `sys_verification_code_cache` VALUES ('oLmVmWCfODWPUGzdVe', 'peqs', '2024-03-18 17:30:04');
INSERT INTO `sys_verification_code_cache` VALUES ('ocdzNsDXdBLcSQYwls', 'uuqx', '2024-04-07 23:43:02');
INSERT INTO `sys_verification_code_cache` VALUES ('ofulAtZLBcOFYppMSq', 'mke7', '2024-08-23 10:35:03');
INSERT INTO `sys_verification_code_cache` VALUES ('ogCSQIMytRDVglUOza', 'aumb', '2024-04-06 00:10:10');
INSERT INTO `sys_verification_code_cache` VALUES ('pOCGpLCFMzKqmeMkUl', 'nkuu', '2024-04-07 12:32:52');
INSERT INTO `sys_verification_code_cache` VALUES ('pQfxymdsLeLMrBeTZZ', '4kt4', '2024-03-13 12:06:39');
INSERT INTO `sys_verification_code_cache` VALUES ('pVAcFkuPwraKiKaBjI', 'z7vd', '2024-04-06 15:51:25');
INSERT INTO `sys_verification_code_cache` VALUES ('pzKTZUfsUIhzvfRAEt', 'wpje', '2024-03-18 10:43:49');
INSERT INTO `sys_verification_code_cache` VALUES ('qKwkVgXemTbqtrpZVQ', 'gmew', '2024-08-20 18:02:43');
INSERT INTO `sys_verification_code_cache` VALUES ('qNyfhzpffNwvrsqBYc', 'jkqg', '2024-03-13 11:31:39');
INSERT INTO `sys_verification_code_cache` VALUES ('qUJICcayBtnJMYIZnD', 'a7cx', '2024-04-06 15:26:59');
INSERT INTO `sys_verification_code_cache` VALUES ('qiHCIaPIkPKYOxnlQg', 'bsek', '2024-04-25 01:16:20');
INSERT INTO `sys_verification_code_cache` VALUES ('qxGMbJBSozFGWnPuQg', '84mr', '2024-04-25 01:13:47');
INSERT INTO `sys_verification_code_cache` VALUES ('rPzewdmAacMUkNGhiE', 'w4vf', '2024-03-25 13:22:47');
INSERT INTO `sys_verification_code_cache` VALUES ('rXusKsSUmRIBXdzSRb', 'jvvc', '2024-03-13 11:44:34');
INSERT INTO `sys_verification_code_cache` VALUES ('sQHJcZfkTqKiVQkjZJ', 'kw4v', '2024-03-18 18:22:19');
INSERT INTO `sys_verification_code_cache` VALUES ('sRSZuzwjWuWrcmCtWd', 'nvbk', '2024-04-06 14:14:08');
INSERT INTO `sys_verification_code_cache` VALUES ('sweRyvxorxsTJRNLbM', 'bdhz', '2024-04-06 15:56:01');
INSERT INTO `sys_verification_code_cache` VALUES ('tKYNVWDVNeEQQFBCyn', 'jhmj', '2024-03-13 11:44:15');
INSERT INTO `sys_verification_code_cache` VALUES ('tjcBBRfpEBGWIfBdBH', 'vawe', '2024-03-13 12:18:14');
INSERT INTO `sys_verification_code_cache` VALUES ('twsOHyuDPkAhhisESX', '63pu', '2024-04-07 00:39:37');
INSERT INTO `sys_verification_code_cache` VALUES ('txapJeaOIwBiUyAaRr', 'fzp3', '2024-04-28 00:51:35');
INSERT INTO `sys_verification_code_cache` VALUES ('uFrKgEZZvAXUAwRVwo', 'qaeb', '2024-04-09 23:02:26');
INSERT INTO `sys_verification_code_cache` VALUES ('uJvIzcrvbdvMLkCoqD', 'n6u7', '2024-03-13 12:21:20');
INSERT INTO `sys_verification_code_cache` VALUES ('utlQFanDEjstLNmlQP', '23m4', '2024-03-13 11:31:57');
INSERT INTO `sys_verification_code_cache` VALUES ('vOHJbmCdLoOzLdPZjt', 'jygt', '2024-03-18 17:28:19');
INSERT INTO `sys_verification_code_cache` VALUES ('vSCHOFpYjIMdwpVxCl', '6myr', '2024-04-08 00:55:19');
INSERT INTO `sys_verification_code_cache` VALUES ('vUoHmhzyDosRCnRvwM', 'vecl', '2024-03-25 16:25:44');
INSERT INTO `sys_verification_code_cache` VALUES ('wPxKgBnztrwjJjNeuF', 'xxlq', '2024-03-13 12:22:07');
INSERT INTO `sys_verification_code_cache` VALUES ('wReiUWGqgOgOBYBXel', 'hrhh', '2024-03-18 18:31:46');
INSERT INTO `sys_verification_code_cache` VALUES ('xTiUaVBZxstKTwLjEG', 'gnkj', '2024-04-24 17:16:55');
INSERT INTO `sys_verification_code_cache` VALUES ('xYMQPErukGZDTsCpnX', 'ftpt', '2024-03-13 11:44:17');
INSERT INTO `sys_verification_code_cache` VALUES ('xlLQGyYkONHejbHSAl', 'wkik', '2024-09-02 08:53:27');
INSERT INTO `sys_verification_code_cache` VALUES ('xqDmvXOKpxmHehKbjl', 'djvb', '2024-03-14 21:37:36');
INSERT INTO `sys_verification_code_cache` VALUES ('yREicXGJKdDkULTeOX', 'haty', '2024-04-24 17:20:35');
INSERT INTO `sys_verification_code_cache` VALUES ('yTzyrwzQrbjFsrCGDl', 'xsh7', '2024-03-25 13:20:48');
INSERT INTO `sys_verification_code_cache` VALUES ('yUMeqozgBqDfiBVqBC', 'fbbi', '2024-04-06 15:59:18');
INSERT INTO `sys_verification_code_cache` VALUES ('yYdlRXBSNYQTlyPzXM', 'fmbu', '2024-04-28 00:54:07');
INSERT INTO `sys_verification_code_cache` VALUES ('yczydfqmEloWgVWDgQ', 'h8nu', '2024-03-13 12:17:39');
INSERT INTO `sys_verification_code_cache` VALUES ('yfgkOgVnvCdFEVyYxJ', 'mdux', '2024-09-03 10:57:04');
INSERT INTO `sys_verification_code_cache` VALUES ('yholkruMbwOTyRyaSn', 'rnvv', '2024-04-06 00:03:12');
INSERT INTO `sys_verification_code_cache` VALUES ('yiBDnaeMipaTWqiaKe', 'armv', '2024-04-06 16:03:58');
INSERT INTO `sys_verification_code_cache` VALUES ('znhrNFIdOppOeajwxB', '6mbf', '2024-03-13 11:38:36');
INSERT INTO `sys_verification_code_cache` VALUES ('zuPRDQRSPMvqYsYcfm', 'nezm', '2024-05-03 19:06:42');

SET FOREIGN_KEY_CHECKS = 1;
