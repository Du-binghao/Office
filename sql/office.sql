/*
 Navicat Premium Data Transfer

 Source Server         : 数据库连接
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : office

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/05/2022 16:37:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `posts_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '聊天室编号',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `comment_info` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '发布内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '聊天室消息编号',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COMMENT='聊天室消息表';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES ('1', '1', '啦啦啦', '2022-05-27 22:20:41', 10);
INSERT INTO `comment` VALUES ('1', '2', '哦哦哦', '2022-05-27 22:20:51', 11);
INSERT INTO `comment` VALUES ('1', '1', '呃呃呃', '2022-05-27 22:21:01', 12);
COMMIT;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `daily_id` int NOT NULL AUTO_INCREMENT COMMENT '日报编号',
  `daily_info` text COMMENT '日报内容',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `dep_id` int DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`daily_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='日报表';

-- ----------------------------
-- Records of daily
-- ----------------------------
BEGIN;
INSERT INTO `daily` VALUES (1, '这是日报的内容！！！', 4, '2022-05-26 21:18:38', 1);
INSERT INTO `daily` VALUES (5, 'zxcvzxvafaf=', 4, '2022-05-27 00:00:00', 1);
COMMIT;

-- ----------------------------
-- Table structure for dep
-- ----------------------------
DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep` (
  `dep_id` int NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dep_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门信息',
  `dep_info` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '部门名',
  `dep_manager` varchar(255) DEFAULT NULL COMMENT '部门经理',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COMMENT='部门表\n';

-- ----------------------------
-- Records of dep
-- ----------------------------
BEGIN;
INSERT INTO `dep` VALUES (1, '人事部', '<p><font color=\"#000000\"><b style=\"\">&nbsp; &nbsp; &nbsp;人事部是一个企业或者团体的</b>人力资源<b style=\"\">和企业公共事务的管理部门 。现在为强化人的重要性，分离出人力资源部门。</b><br/></font></p><p><b><font color=\"#000000\">&nbsp; &nbsp; &nbsp; 一般意义上人力资源部门应该是人事部的发展，但不能说人事部本身具有缺陷，只是一般意义上的解说歪曲了人事部的意义。人力资源的意义发展是其本身职能的发展而非部门名称的原因，因此人事部并没有过时之说。其本身职能的变化和发展应该具有时代意义。</font></b></p>', '2');
INSERT INTO `dep` VALUES (2, '产品部', '<h2><font color=\"#000000\">建设团队</font></h2><p><font color=\"#000000\">公司价值观和制度部门内监督落实</font></p><p><font color=\"#000000\">部门岗位职责拟定和查核</font></p><p><font color=\"#000000\">产品团队的核心能力结构图制定</font></p><p><font color=\"#000000\">产品梯队的建设和选拔</font></p><p><font color=\"#000000\">产品团队的能力提升落实</font></p>', '1');
COMMIT;

-- ----------------------------
-- Table structure for dep_task
-- ----------------------------
DROP TABLE IF EXISTS `dep_task`;
CREATE TABLE `dep_task` (
  `task_id` int NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `task_info` text COMMENT '任务描述',
  `task_title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `dep_id` int DEFAULT NULL COMMENT '部门编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dep_task
-- ----------------------------
BEGIN;
INSERT INTO `dep_task` VALUES (8, '<h1 id=\"u14cy\">这是一个给财务部的任务</h1>', '测试的发布任务', 3, '2022-05-26 20:49:00');
COMMIT;

-- ----------------------------
-- Table structure for dep_user
-- ----------------------------
DROP TABLE IF EXISTS `dep_user`;
CREATE TABLE `dep_user` (
  `dep_user_id` int NOT NULL AUTO_INCREMENT COMMENT '部门用户编号',
  `dep_id` int DEFAULT NULL COMMENT '部门编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`dep_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='部门用户表';

-- ----------------------------
-- Records of dep_user
-- ----------------------------
BEGIN;
INSERT INTO `dep_user` VALUES (1, 1, 4);
INSERT INTO `dep_user` VALUES (2, 2, 5);
INSERT INTO `dep_user` VALUES (6, 1, 6);
COMMIT;

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `finance_id` int NOT NULL AUTO_INCREMENT COMMENT '财务审核编号',
  `finance_title` varchar(255) DEFAULT NULL COMMENT '财务审核标题',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `money` float DEFAULT NULL COMMENT '金额',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  `code` varchar(255) DEFAULT NULL COMMENT '审核状态',
  `start` datetime DEFAULT NULL COMMENT '消费时间',
  `finance_info` text COMMENT '消费内容',
  PRIMARY KEY (`finance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='财务审核表';

-- ----------------------------
-- Records of finance
-- ----------------------------
BEGIN;
INSERT INTO `finance` VALUES (1, '测试提交', '2022-05-26 21:33:30', 188, 4, 'dengdai', '2022-05-26 21:33:39', '就是想消费');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `parent_id` int NOT NULL COMMENT '父级菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` int NOT NULL COMMENT '类型（0为主菜单，1为子菜单，2为按钮）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, 0, '首页', 'dashboard', '/', '', 0, 0, '2020-07-13 20:14:26', '2022-05-26 17:39:42');
INSERT INTO `menu` VALUES (2, 0, '系统管理', 'system', '/system', '', 1, 0, '2020-07-13 20:19:02', '2020-07-13 20:19:08');
INSERT INTO `menu` VALUES (3, 2, '用户管理', 'peoples', '/system/user', 'user:list', 2, 1, '2020-07-10 09:34:17', '2020-07-10 09:34:20');
INSERT INTO `menu` VALUES (4, 2, '角色管理', 'role', '/system/role', 'role:list', 3, 1, '2020-07-10 09:34:50', '2020-07-10 09:34:53');
INSERT INTO `menu` VALUES (5, 2, '菜单管理', 'menu', '/system/menu', 'menu:list', 4, 1, '2020-07-10 09:34:50', '2020-07-10 09:34:53');
INSERT INTO `menu` VALUES (6, 3, '用户新增', NULL, NULL, 'user:add', 1, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (7, 3, '用户修改', NULL, NULL, 'user:edit', 2, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (8, 3, '用户删除', NULL, NULL, 'user:del', 3, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (9, 4, '角色添加', NULL, NULL, 'role:add', 1, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (10, 4, '角色修改', NULL, NULL, 'role:edit', 2, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (11, 4, '角色权限配置', NULL, NULL, 'role:menu:change', 3, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (12, 4, '角色删除', NULL, NULL, 'role:del', 3, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (13, 5, '菜单添加', NULL, NULL, 'menu:add', 1, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (14, 5, '菜单修改', NULL, NULL, 'menu:edit', 2, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (15, 5, '菜单删除', NULL, NULL, 'menu:del', 3, 2, '2022-01-22 01:43:16', '2022-01-22 01:43:19');
INSERT INTO `menu` VALUES (51, 50, '日志监控', 'log', '/monitor/logs', 'log:monitor', 51, 1, '2022-02-01 08:06:00', '2022-02-01 10:08:13');
INSERT INTO `menu` VALUES (52, 50, '服务监控', 'codeConsole', '/monitor/server', 'server:monitor', 52, 1, '2022-01-27 08:33:35', '2022-02-01 08:08:58');
INSERT INTO `menu` VALUES (53, 50, 'SQL监控', 'sqlMonitor', '/monitor/sql', 'sql:monitor', 53, 1, '2022-01-31 07:29:44', '2022-02-01 08:09:01');
INSERT INTO `menu` VALUES (54, 51, '日志删除', '', '', 'logs:del', 1, 2, '2022-02-01 10:45:42', '2022-02-01 14:29:05');
INSERT INTO `menu` VALUES (61, 60, '公告管理', 'gonggao', '/utils/notice', 'notice:list', 61, 1, '2022-02-01 03:14:35', '2022-02-02 17:44:19');
INSERT INTO `menu` VALUES (62, 60, '接口文档', 'swagger', '/utils/swagger', 'swagger:list', 62, 1, '2022-01-31 09:34:33', '2022-02-02 13:36:16');
INSERT INTO `menu` VALUES (63, 61, '公告添加', '', '', 'notice:add', 1, 2, '2022-02-01 15:16:59', '2022-02-01 15:19:16');
INSERT INTO `menu` VALUES (64, 61, '公告修改', '', '', 'notice:edit', 2, 2, '2022-02-01 15:17:31', '2022-02-01 15:19:19');
INSERT INTO `menu` VALUES (65, 61, '公告删除', '', '', 'notice:del', 3, 2, '2022-02-01 15:18:25', '2022-02-01 17:31:33');
INSERT INTO `menu` VALUES (66, 60, '支付宝工具', 'alipay', '/utils/alipay', 'ali:config', 63, 1, '2022-02-02 12:56:24', '2022-02-02 14:47:42');
INSERT INTO `menu` VALUES (67, 0, '企业管理', 'icon', '/man_enterprise', '', 10, 0, '2022-05-26 17:23:14', '2022-05-26 17:35:32');
INSERT INTO `menu` VALUES (68, 67, '任务管理', 'tools', '/man_enterprise/task', 'task:list', 11, 1, '2022-05-26 17:24:06', NULL);
INSERT INTO `menu` VALUES (69, 68, '添加', '', '', 'task:add', 1, 2, '2022-05-26 17:24:25', NULL);
INSERT INTO `menu` VALUES (70, 68, '删除', '', '', 'task:del', 2, 2, '2022-05-26 17:24:51', NULL);
INSERT INTO `menu` VALUES (71, 67, '部门管理', 'peoples', '/man_enterprise/dep', 'dep:list', 12, 1, '2022-05-26 17:25:44', NULL);
INSERT INTO `menu` VALUES (72, 71, '添加', '', '', 'dep:add', 1, 2, '2022-05-26 17:26:12', NULL);
INSERT INTO `menu` VALUES (73, 71, '修改', '', '', 'dep:edit', 2, 2, '2022-05-26 17:26:35', NULL);
INSERT INTO `menu` VALUES (74, 71, '删除', '', '', 'dep:del', 3, 2, '2022-05-26 17:26:52', NULL);
INSERT INTO `menu` VALUES (75, 67, '部门用户管理', 'user', '/man_enterprise/depUser', 'depUser:list', 13, 1, '2022-05-26 17:27:54', NULL);
INSERT INTO `menu` VALUES (76, 75, '添加', '', '', 'depUser:add', 1, 2, '2022-05-26 17:28:19', NULL);
INSERT INTO `menu` VALUES (77, 75, '删除', '', '', 'depUser:del', 2, 2, '2022-05-26 17:28:42', NULL);
INSERT INTO `menu` VALUES (78, 0, '部门管理', 'peoples', '/man_dep', '', 20, 0, '2022-05-26 17:32:30', NULL);
INSERT INTO `menu` VALUES (79, 78, '成员列表', 'user', '/man_dep/depUser', 'depUser:list', 21, 1, '2022-05-26 17:33:26', NULL);
INSERT INTO `menu` VALUES (80, 78, '成员日报', 'message', '/man_dep/daily', 'daily:list', 22, 1, '2022-05-26 17:34:42', NULL);
INSERT INTO `menu` VALUES (81, 67, '财务审核', 'anq', '/man_enterprise/finance', 'finance:list', 24, 1, '2022-05-26 21:30:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `posts_id` int NOT NULL AUTO_INCREMENT COMMENT '聊天室编号',
  `users` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户编号',
  `posts_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '聊天室名',
  `pwd` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '聊天室密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`posts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='聊天室表';

-- ----------------------------
-- Records of posts
-- ----------------------------
BEGIN;
INSERT INTO `posts` VALUES (1, '{1}{2}{3}{4}', '测试的聊天室', '123456', '2022-05-27 22:20:19');
COMMIT;

-- ----------------------------
-- Table structure for res
-- ----------------------------
DROP TABLE IF EXISTS `res`;
CREATE TABLE `res` (
  `res_id` int NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `res_address` varchar(255) DEFAULT NULL COMMENT '资源地址',
  `res_title` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `res_download` int DEFAULT '0' COMMENT '下载次数',
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='资源文件表';

-- ----------------------------
-- Records of res
-- ----------------------------
BEGIN;
INSERT INTO `res` VALUES (1, '1', 'src/main/res/201305151_高峰_任务书.doc', '201305151_高峰_任务书.doc', 1);
INSERT INTO `res` VALUES (2, '1', 'src/main/res/201305151_高峰_开题报告.doc', '201305151_高峰_开题报告.doc', 6);
INSERT INTO `res` VALUES (6, '1', 'src/main/res/1.doc', '1.doc', 4);
INSERT INTO `res` VALUES (8, '1', 'src/main/res/啊啊啊.docx', '啊啊啊.docx', 2);
INSERT INTO `res` VALUES (10, '4', 'src/main/res/大连旅游攻略系统(3).doc', '大连旅游攻略系统(3).doc', 3);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT 'roleID',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'ADMIN', '超级管理员', '2021-12-27 00:00:00', '2021-12-27 00:00:00');
INSERT INTO `role` VALUES (2, 'MERCHANTS', '部门经理', '2022-01-29 14:07:42', '2022-05-26 17:31:15');
INSERT INTO `role` VALUES (3, 'USER', '用户', '2022-01-29 15:41:53', '2022-01-29 15:42:09');
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int NOT NULL COMMENT '规则ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (1, 9);
INSERT INTO `role_menu` VALUES (1, 10);
INSERT INTO `role_menu` VALUES (1, 11);
INSERT INTO `role_menu` VALUES (1, 12);
INSERT INTO `role_menu` VALUES (1, 13);
INSERT INTO `role_menu` VALUES (1, 14);
INSERT INTO `role_menu` VALUES (1, 15);
INSERT INTO `role_menu` VALUES (1, 51);
INSERT INTO `role_menu` VALUES (1, 52);
INSERT INTO `role_menu` VALUES (1, 53);
INSERT INTO `role_menu` VALUES (1, 54);
INSERT INTO `role_menu` VALUES (1, 61);
INSERT INTO `role_menu` VALUES (1, 62);
INSERT INTO `role_menu` VALUES (1, 63);
INSERT INTO `role_menu` VALUES (1, 64);
INSERT INTO `role_menu` VALUES (1, 65);
INSERT INTO `role_menu` VALUES (1, 66);
INSERT INTO `role_menu` VALUES (1, 67);
INSERT INTO `role_menu` VALUES (1, 68);
INSERT INTO `role_menu` VALUES (1, 69);
INSERT INTO `role_menu` VALUES (1, 70);
INSERT INTO `role_menu` VALUES (1, 71);
INSERT INTO `role_menu` VALUES (1, 72);
INSERT INTO `role_menu` VALUES (1, 73);
INSERT INTO `role_menu` VALUES (1, 74);
INSERT INTO `role_menu` VALUES (1, 75);
INSERT INTO `role_menu` VALUES (1, 76);
INSERT INTO `role_menu` VALUES (1, 77);
INSERT INTO `role_menu` VALUES (1, 81);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 61);
INSERT INTO `role_menu` VALUES (2, 66);
INSERT INTO `role_menu` VALUES (2, 78);
INSERT INTO `role_menu` VALUES (2, 79);
INSERT INTO `role_menu` VALUES (2, 80);
INSERT INTO `role_menu` VALUES (3, 1);
INSERT INTO `role_menu` VALUES (4, 1);
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '规则ID',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (1, 1);
INSERT INTO `role_user` VALUES (2, 2);
INSERT INTO `role_user` VALUES (3, 2);
INSERT INTO `role_user` VALUES (4, 3);
INSERT INTO `role_user` VALUES (5, 3);
INSERT INTO `role_user` VALUES (6, 3);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `sex` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '头像',
  `status` int DEFAULT NULL COMMENT '状态',
  `create_user` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$hWB2BGZnd9eSRwCLlGjt2e71Tb6DNn2IegHlHO4VQIjAIkupwPVeu', '超级管理员', '0', '18888888888', '123456@qq.com', '507a2aa7-344a-41eb-beec-62c045ad78d2.jpeg', 1, 'admin', 'admin', '2022-01-01 02:39:40', '2022-05-13 13:12:47');
INSERT INTO `user` VALUES (2, 'test', '$2a$10$2fUxxn89LdzugrEJIMBLm.gHqLu92iutIr4jeCJF1dQ/lGSVACL26', 'test', '0', '13740250781', '123456@qq.com', '297bd065-60c6-439a-afdd-190f3d72a32b.JPG', 1, 'admin', 'admin', '2022-01-29 08:55:48', '2022-05-13 13:14:20');
INSERT INTO `user` VALUES (3, 'aaa', '$2a$10$sL9mO62VjZmvybFlXWWZ.uTBxZvtoEmf1AjUypA/ofYyZbDQMSX9q', 'aaa123', '1', '13940250784', '123456789@qq.com', 'avatar.gif', 1, 'admin', 'admin', '2022-02-01 01:49:41', '2022-02-03 11:40:50');
INSERT INTO `user` VALUES (4, 'asda', '$2a$10$n8o2ckUTR.ge3MF.f4Ynxe4PmHtYBXEyiMa2F0Qa58kt7Ciyhktu.', 'asdad', NULL, '12312312312', 'aaa', 'b4527028-2e71-470c-9557-fb4861b1f96a.JPG', 1, 'admin', NULL, '2022-05-26 19:54:21', '2022-05-27 21:18:55');
INSERT INTO `user` VALUES (5, 'asdad', '$2a$10$Sj5NYuAv.ha6zaXIikwjtOzPZ//9.MK4ez5EPgl7cSdJUAFMimxl6', 'asdad', NULL, '12312312312', 'asdad', '/assets/admin/avatar.gif', 1, 'admin', NULL, '2022-05-26 20:00:45', NULL);
INSERT INTO `user` VALUES (6, 'asdfafgzcvb', '$2a$10$eCcJYjUhmi5EcxrJon2CZ.OMhRh9Jx44Y79O6iwy75b/30bw9H9oi', '12314', NULL, '12312312312', '123azc', '/assets/admin/avatar.gif', 1, 'admin', NULL, '2022-05-26 20:01:05', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
