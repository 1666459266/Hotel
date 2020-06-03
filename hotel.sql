/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 29/05/2020 09:26:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accommodation_record
-- ----------------------------
DROP TABLE IF EXISTS `accommodation_record`;
CREATE TABLE `accommodation_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '住宿记录id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '房间id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `room_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型',
  `room_number` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `room_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间单价',
  `total_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总价',
  `checkin_date` date NULL DEFAULT NULL COMMENT '入住日期',
  `departure_date` date NULL DEFAULT NULL COMMENT '离开日期',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for background
-- ----------------------------
DROP TABLE IF EXISTS `background`;
CREATE TABLE `background`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '背景图片id',
  `background_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图片',
  `background_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图片简介',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for copyright
-- ----------------------------
DROP TABLE IF EXISTS `copyright`;
CREATE TABLE `copyright`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '版权信息id',
  `contact_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `contact_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `coupon_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券图片',
  `coupon_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券价格',
  `coupon_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券详情',
  `usable_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用范围',
  `service_conditions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用条件',
  `get_date` datetime(0) NULL DEFAULT NULL COMMENT '领取日期',
  `expiration_date` datetime(0) NULL DEFAULT NULL COMMENT '到期日期',
  `coupon_states` int(11) NULL DEFAULT NULL COMMENT '优惠券状态(1=未使用,2=已使用)',
  `usage_states` int(11) NULL DEFAULT NULL COMMENT '使用状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for expense
-- ----------------------------
DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消费记录id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `consumption_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消费类型',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买数量',
  `trading_manner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易方式',
  `transaction_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易金额',
  `consumption_date` datetime(0) NULL DEFAULT NULL COMMENT '消费日期',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for features
-- ----------------------------
DROP TABLE IF EXISTS `features`;
CREATE TABLE `features`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酒店特色id',
  `feature_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特色图片',
  `feature_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特色简介',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fitness
-- ----------------------------
DROP TABLE IF EXISTS `fitness`;
CREATE TABLE `fitness`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '健身器材id',
  `equipment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材名称',
  `equipment_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材图片',
  `equipment_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材简介',
  `equipment_num` int(11) NULL DEFAULT NULL COMMENT '器材数量',
  `equipment_usage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '器材使用方法',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `equipment_states` int(11) NULL DEFAULT NULL COMMENT '器材状态(1=正常,2=维修)',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酒店历史id',
  `history_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '历史图片',
  `history_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '历史简介',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for opinion
-- ----------------------------
DROP TABLE IF EXISTS `opinion`;
CREATE TABLE `opinion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '意见反馈id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `problem_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题类型',
  `problem_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题描述',
  `feedback_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `dispose_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限键',
  `permission_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `parent_permission_id` int(11) NULL DEFAULT NULL COMMENT '父权限id',
  `parent_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `permission_states` int(11) NULL DEFAULT NULL COMMENT '权限状态(0=删除,1=正常)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '主页面', '未登录可访问的页面', '/login.html', 'anon', NULL, 1, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (2, '登录请求', '未登录可访问的请求', '/users/login', 'anon', NULL, 2, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (3, '登出请求', '登出访问的请求', '/users/logout', 'anon', NULL, 3, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (4, 'root页面', 'root用户的页面', '/root.html', 'roles[root]', NULL, 4, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (5, 'admin页面', 'admin用户的页面', '/admin.html', 'roles[admin]', NULL, 5, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (6, 'user页面', 'user用户的页面', '/user.html', 'roles[user]', NULL, 6, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (7, 'root_list页面', '具备root,admin,user角色才能访问', '/rootList.html', 'roles[root,admin,user]', NULL, 7, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (8, 'admin_list页面', '具备admin,user角色才能访问', '/adminList.html', 'roles[admin,user]', NULL, 8, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (9, 'user_list', '具备user角色才能访问', '/userList.html', 'roles[user]', NULL, 9, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (10, '查询请求', '前端可访问的请求', '/select/**', 'anon', NULL, 10, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (11, '用户注册', '前端用户注册请求', '/register', 'anon', NULL, 11, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (12, '静态资源', '开放的静态资源', '/file/**', 'anon', NULL, 12, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (13, '用户中心', '', '/web', 'roles[admin,user]', 0, 13, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (14, '用户列表', NULL, '/web', 'roles[admin,user]', 13, 14, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (15, '黑名单列表', NULL, '/web', 'roles[admin,user]', 13, 15, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (16, '权限管理', NULL, '/web', 'roles[admin,user]', 0, 16, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (17, '角色管理', NULL, '/web', 'roles[admin,user]', 16, 17, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (18, '资源权限管理', NULL, '/web', 'roles[admin,user]', 16, 18, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (19, '订单中心', NULL, '/web', 'roles[admin,user]', 0, 19, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (20, '商品订单', NULL, '/web', 'roles[admin,user]', 19, 20, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (21, '房间订单', NULL, '/web', 'roles[admin,user]', 19, 21, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (22, '酒店中心', NULL, '/web', 'roles[admin,user]', 0, 22, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (23, '房间管理', NULL, '/web', 'roles[admin,user]', 22, 23, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (24, '商品管理', NULL, '/web', 'roles[admin,user]', 22, 24, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (25, '餐厅管理', NULL, '/web', 'roles[admin,user]', 22, 25, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (26, '器材管理', NULL, '/web', 'roles[admin,user]', 22, 26, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (27, '酒店特色', NULL, '/web', 'roles[admin,user]', 22, 27, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (28, '历史文化', NULL, '/web', 'roles[admin,user]', 22, 28, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (29, '精美壁纸', NULL, '/web', 'roles[admin,user]', 22, 29, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (30, '数据中心', NULL, '/web', 'roles[admin,user]', 0, 30, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (31, '消费统计', NULL, '/web', 'roles[admin,user]', 30, 31, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (32, '住宿统计', NULL, '/web', 'roles[admin,user]', 30, 32, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (33, '回收站', NULL, '/web', 'roles[admin,user]', 0, 33, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (34, '意见反馈', NULL, '/web', 'roles[admin,user]', 0, 34, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (35, '版权信息', NULL, '/web', 'roles[admin,user]', 0, 35, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (36, '用户注册', '', '/users/insertUser', 'anon', NULL, 36, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (37, '用户修改', NULL, '/users/updateUser', 'anon', NULL, 37, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (38, '人脸登录', '', '/face/faceLogin', 'anon', NULL, 38, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (39, '人脸添加', '', '/face/faceAdd', 'anon', NULL, 39, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (40, '人脸搜索', '', '/face/faceSearch', 'anon', NULL, 40, 1, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (41, '所有请求', '拦截所有请求', '/**', 'authc', NULL, 999, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简介',
  `product_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  `product_unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品单价',
  `product_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `product_popularity` int(11) NULL DEFAULT 0 COMMENT '产品热度',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `product_instock` int(11) NULL DEFAULT NULL COMMENT '是否有货(1=有货,2=无货)',
  `product_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐厅id',
  `restaurant_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类(早,中,晚)',
  `restaurant_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `restaurant_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `food_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品名字',
  `food_unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品单价',
  `food_popularity` int(11) NULL DEFAULT 0 COMMENT '物品热度',
  `food_num` int(11) NULL DEFAULT NULL COMMENT '物品数量',
  `register_date` date NULL DEFAULT NULL COMMENT '上架时间',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifier_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modifier_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `food_instock` int(11) NULL DEFAULT NULL COMMENT '是否有货(1=有货,2=无货)',
  `food_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_sort` int(11) NULL DEFAULT NULL COMMENT '角色排序',
  `role_states` int(11) NULL DEFAULT NULL COMMENT '角色状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'root', '超级管理员权限', NULL, 1, NULL, NULL, NULL);
INSERT INTO `role` VALUES (2, 'admin', '管理员权限', NULL, 1, NULL, NULL, NULL);
INSERT INTO `role` VALUES (3, 'user', '用户权限', NULL, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 13, 'root', '超级管理员权限', '用户中心', '', NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (2, 1, 14, 'root', '超级管理员权限', '用户列表', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (3, 1, 15, 'root', '超级管理员权限', '黑名单列表', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (4, 1, 16, 'root', '超级管理员权限', '权限管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (5, 1, 17, 'root', '超级管理员权限', '角色管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (6, 1, 18, 'root', '超级管理员权限', '资源权限管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (7, 1, 19, 'root', '超级管理员权限', '订单中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (8, 1, 20, 'root', '超级管理员权限', '商品订单', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (9, 1, 21, 'root', '超级管理员权限', '房间订单', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (10, 1, 22, 'root', '超级管理员权限', '酒店中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (11, 1, 23, 'root', '超级管理员权限', '房间管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (12, 1, 24, 'root', '超级管理员权限', '商品管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (13, 1, 25, 'root', '超级管理员权限', '餐厅管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (14, 1, 26, 'root', '超级管理员权限', '器材管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (15, 1, 27, 'root', '超级管理员权限', '酒店特色', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (16, 1, 28, 'root', '超级管理员权限', '历史文化', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (17, 1, 29, 'root', '超级管理员权限', '精美壁纸', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (18, 1, 30, 'root', '超级管理员权限', '数据中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (19, 1, 31, 'root', '超级管理员权限', '消费统计', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (20, 1, 32, 'root', '超级管理员权限', '住宿统计', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (21, 1, 33, 'root', '超级管理员权限', '回收站', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (22, 1, 34, 'root', '超级管理员权限', '意见反馈', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (23, 1, 35, 'root', '超级管理员权限', '版权信息', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (24, 2, 13, 'admin', '管理员权限', '用户中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (25, 2, 14, 'admin', '管理员权限', '用户列表', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (26, 2, 15, 'admin', '管理员权限', '黑名单列表', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (27, 2, 16, 'admin', '管理员权限', '权限管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (28, 2, 17, 'admin', '管理员权限', '角色管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (29, 2, 18, 'admin', '管理员权限', '资源权限管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (30, 2, 19, 'admin', '管理员权限', '订单中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (31, 2, 20, 'admin', '管理员权限', '商品订单', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (32, 2, 21, 'admin', '管理员权限', '房间订单', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (33, 2, 22, 'admin', '管理员权限', '酒店中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (34, 2, 23, 'admin', '管理员权限', '房间管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (35, 2, 24, 'admin', '管理员权限', '商品管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (36, 2, 25, 'admin', '管理员权限', '餐厅管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (37, 2, 26, 'admin', '管理员权限', '器材管理', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (38, 2, 27, 'admin', '管理员权限', '酒店特色', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (39, 2, 28, 'admin', '管理员权限', '历史文化', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (40, 2, 29, 'admin', '管理员权限', '精美壁纸', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (41, 2, 30, 'admin', '管理员权限', '数据中心', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (42, 2, 31, 'admin', '管理员权限', '消费统计', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (43, 2, 32, 'admin', '管理员权限', '住宿统计', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (44, 2, 33, 'admin', '管理员权限', '回收站', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (45, 2, 34, 'admin', '管理员权限', '意见反馈', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (46, 2, 35, 'admin', '管理员权限', '版权信息', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间id',
  `room_floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间楼层',
  `room_number` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `room_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间图片',
  `room_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间简介',
  `room_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型',
  `room_max` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间最大人数',
  `room_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间占地面积',
  `room_bed_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间床类型',
  `room_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间单价',
  `creater_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `creater_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人用户名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `modify_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人用户名',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `room_states` int(11) NULL DEFAULT NULL COMMENT '房间状态(1=无人,2=有人,3=已预定)',
  `exist_states` int(11) NULL DEFAULT NULL COMMENT '存在状态(1=正常,2=删除)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_face_info
-- ----------------------------
DROP TABLE IF EXISTS `user_face_info`;
CREATE TABLE `user_face_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NULL DEFAULT NULL,
  `group_id` int(11) NULL DEFAULT NULL COMMENT '分组id',
  `face_id` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人脸唯一Id',
  `room_number` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(3) NULL DEFAULT NULL COMMENT '年纪',
  `gender` smallint(1) NULL DEFAULT NULL COMMENT '性别，1=男，2=女',
  `face_feature` blob NULL COMMENT '人脸特征',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `face_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片路径',
  `test1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `GROUP_ID`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_product
-- ----------------------------
DROP TABLE IF EXISTS `user_product`;
CREATE TABLE `user_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户商品id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `order_number` int(11) NULL DEFAULT NULL COMMENT '订单编号',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `product_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品价格',
  `product_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `order_overdue_states` int(11) NULL DEFAULT NULL COMMENT '订单是否过期(1=正常,2=过期)',
  `order_states` int(11) NULL DEFAULT NULL COMMENT '订单状态(1=已支付,2=未支付)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_restaurant
-- ----------------------------
DROP TABLE IF EXISTS `user_restaurant`;
CREATE TABLE `user_restaurant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户餐厅id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `restaurant_id` int(11) NULL DEFAULT NULL COMMENT '餐厅id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `food_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品名称',
  `food_unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品单价',
  `food_num` int(11) NULL DEFAULT NULL COMMENT '物品数量',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `order_overdue_states` int(255) NULL DEFAULT NULL COMMENT '订单是否过期(1=正常,2=过期)',
  `order_states` int(255) NULL DEFAULT NULL COMMENT '订单状态(1=已支付,2=未支付)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, 'root', '超级管理员', '超级管理员权限', NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (2, 1, 2, 'root', '管理员', '管理员权限', NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (3, 1, 3, 'root', '用户', '用户权限', NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (4, 2, 2, 'admin', '管理员', '管理员权限', NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (5, 2, 3, 'admin', '用户', '用户权限', NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (6, 3, 3, 'user', '用户', '用户权限', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_room
-- ----------------------------
DROP TABLE IF EXISTS `user_room`;
CREATE TABLE `user_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户房间id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '房间id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `room_number` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `room_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间类型',
  `room_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间价格',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '入店时间',
  `leave_time` datetime(0) NULL DEFAULT NULL COMMENT '离店时间',
  `order_overdue_states` int(11) NULL DEFAULT NULL COMMENT '订单是否过期(1=正常,2=过期)',
  `order_states` int(11) NULL DEFAULT NULL COMMENT '订单状态(1=已支付,2=未支付)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `truename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '绑定手机',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `microblog_account_states` int(11) NULL DEFAULT NULL COMMENT '是否关联微博账号(0=未关联,1=已关联)',
  `qq_account_states` int(11) NULL DEFAULT NULL COMMENT '是否关联qq账号(0=未关联,1=已关联)',
  `wechat_account_states` int(11) NULL DEFAULT NULL COMMENT '是否关联微信账号(0=未关联,1=已关联)',
  `alipay_account_states` int(11) NULL DEFAULT NULL COMMENT '是否关联支付宝账号(0=未关联,1=已关联)',
  `baidu_account_states` int(11) NULL DEFAULT NULL COMMENT '是否关联百度账号(0=未关联,1=已关联)',
  `idcard_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照',
  `idcard_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面照',
  `idcard_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `registration_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `membership_score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员积分',
  `membership_level` int(11) NULL DEFAULT NULL COMMENT '会员等级',
  `realname_authentication_states` int(11) NULL DEFAULT NULL COMMENT '实名认证(0=未认证,1=已认证)',
  `states` int(11) NULL DEFAULT NULL COMMENT '用户状态(1=正常,2=删除,3=锁定,999=隐藏账户)',
  `test1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'root', 'd57e3867a8e25cc04a7c801914dba3a22bbe6e97196454105c09a73f00f10d3a', '这个需求很简单', '', '产品经理', '男', '汉族', '浙江省绍兴市上虞区', '2000-04-28', '13359427436', '13359427436@outlool.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 8, 0, 1, NULL, NULL, NULL);
INSERT INTO `users` VALUES (2, 'admin', '8b64db1b8cb9f9c2b2ae41c65b7f2c4b1456f68dd1235b8527234fae5e40bce5', '这个需求做不了', '', '前端工程师', '男', '汉族', '浙江省绍兴市上虞区', '2000-05-17', '18064740570', '2514035965@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 8, 0, 1, NULL, NULL, NULL);
INSERT INTO `users` VALUES (3, 'user', '73d3b6cfc9d611fcd7744a97ed693d1c7b8b64c23587bcc6a4151ccae4bbea5a', '这个需求没法做', '', '后端工程师', '男', '汉族', '浙江省绍兴市上虞区', '2000-06-06', '18919167032', '1666459266@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 1, 0, 1, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
