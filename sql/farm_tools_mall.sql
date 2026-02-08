/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : farm_tools_mall

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 02/02/2026 21:58:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `county` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '乡镇',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `create_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `defaulted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为默认地址（0否 1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (23, 33, '张三', '13800138000', '广东省', '广州市', '天河区', '珠江新城 XXX 大厦', '2025-04-18 15:25:02', '2026-01-12 19:59:18', 0);
INSERT INTO `address` VALUES (24, 33, '阿甘', '18278327621', '北京市', '北京市', '东城区', '东城区', '2025-05-16 00:12:55', '2026-01-12 19:59:17', 0);
INSERT INTO `address` VALUES (25, 33, '千年老妖', '18367456721', '吉林省', '吉林市', '昌邑区', 'xxxx', '2025-05-16 00:20:45', '2025-06-05 19:29:35', 0);
INSERT INTO `address` VALUES (27, 33, '曹静w', '18373711111', '北京市', '北京市', '东城区', '11', '2025-05-16 00:28:12', '2026-01-12 19:59:18', 1);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `sku_id` bigint NULL DEFAULT NULL COMMENT 'SKU ID',
  `quantity` int NOT NULL DEFAULT 1 COMMENT '商品数量',
  `checked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否选中，1表示选中',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (14, 33, 45, 16, 2, 1, '2025-05-22 02:29:49', '2025-05-22 02:29:49', 0);
INSERT INTO `cart` VALUES (15, 33, 44, 12, 2, 1, '2025-05-22 15:25:46', '2025-05-22 15:25:46', 0);
INSERT INTO `cart` VALUES (16, 33, 46, 18, 3, 1, '2025-06-04 18:52:40', '2025-06-04 18:53:57', 1);
INSERT INTO `cart` VALUES (17, 33, 48, 21, 1, 1, '2025-06-05 10:14:22', '2026-01-13 09:40:26', 1);
INSERT INTO `cart` VALUES (18, 33, 46, 18, 3, 1, '2025-06-05 19:26:45', '2025-07-08 22:31:54', 1);
INSERT INTO `cart` VALUES (19, 33, 46, 19, 1, 1, '2026-01-13 09:28:08', '2026-01-13 09:40:26', 1);

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '类别表id\r\n',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类别名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '类别描述\r\n',
  `parent_id` int NULL DEFAULT NULL COMMENT '父类ID，用于构建多级分类',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES (1, '全部', '所有商品', NULL, '2025-04-15 22:24:18', '2025-04-15 22:25:08');
INSERT INTO `categories` VALUES (2, '农药', '各类农药的分类', NULL, '2024-11-03 14:40:24', '2024-11-03 14:40:24');
INSERT INTO `categories` VALUES (3, '种子', '各类种子的分类', NULL, '2024-11-03 14:40:24', '2024-11-03 14:40:24');
INSERT INTO `categories` VALUES (4, '机械工具', '各类农业机械工具的分类', NULL, '2024-11-03 14:40:24', '2024-11-03 14:40:24');
INSERT INTO `categories` VALUES (5, '肥料', '各类肥料的分类', NULL, '2024-11-03 14:40:24', '2025-04-15 22:25:06');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '反馈id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '反馈图片',
  `contact_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '反馈表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (3, NULL, '', '1', '2025-06-03 15:56:36', '2025-06-03 15:56:36');
INSERT INTO `feedback` VALUES (4, NULL, NULL, '22', '2025-06-03 20:21:50', '2025-06-03 20:21:50');
INSERT INTO `feedback` VALUES (5, NULL, NULL, '2222', '2025-06-03 20:28:53', '2025-06-03 20:28:53');
INSERT INTO `feedback` VALUES (6, NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-06-03/22275ad2-0c8f-4e19-baa4-ceafc3934a13.png', '22', '2025-06-03 21:00:28', '2025-06-03 21:00:28');
INSERT INTO `feedback` VALUES (7, NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-06-05/ab709043-95f7-44ed-9ce4-14ea994187b3.png', '1919828111', '2025-06-05 19:28:39', '2025-06-05 19:28:39');

-- ----------------------------
-- Table structure for fertilizers
-- ----------------------------
DROP TABLE IF EXISTS `fertilizers`;
CREATE TABLE `fertilizers`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '化肥表id',
  `product_id` int NULL DEFAULT NULL COMMENT '关联到products表',
  `nutrient_composition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '营养成分',
  `application_method` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '使用方法',
  `suitable_crops` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '使用作物',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品描述表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fertilizers
-- ----------------------------
INSERT INTO `fertilizers` VALUES (1, 1, '46% N', '均匀撒施或沟施，每亩用量10-20公斤。', '小麦、玉米、水稻等', '2024-11-03 15:16:29', '2024-11-03 15:16:29');
INSERT INTO `fertilizers` VALUES (2, 2, '52% P2O5, 34% K2O', '叶面喷施或根部施肥，每亩用量5-10公斤。', '蔬菜、水果、花卉等', '2024-11-03 15:16:29', '2024-11-03 15:16:29');
INSERT INTO `fertilizers` VALUES (3, 3, '50% K2O', '基肥或追肥，每亩用量10-15公斤。', '土豆、甘蔗、烟草等', '2024-11-03 15:16:29', '2024-11-03 15:16:29');
INSERT INTO `fertilizers` VALUES (4, 10, '多种微生物', '混合作物土壤中，每亩用量1-2公斤。', '各种作物', '2024-11-03 15:16:29', '2024-11-03 15:16:29');
INSERT INTO `fertilizers` VALUES (5, 11, '多种绿肥植物', '直接种植在田间，每亩种植密度为10-15株。', '各种作物', '2024-11-03 15:16:29', '2024-11-03 15:16:29');
INSERT INTO `fertilizers` VALUES (6, 12, '多种营养元素', '基肥或追肥，每亩用量10-15公斤。', '各种作物', '2024-11-03 15:16:29', '2024-11-03 15:16:29');

-- ----------------------------
-- Table structure for inventory_logs
-- ----------------------------
DROP TABLE IF EXISTS `inventory_logs`;
CREATE TABLE `inventory_logs`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '库存记录变动表id',
  `product_id` int NULL DEFAULT NULL COMMENT '关联到products表',
  `change_type` int NULL DEFAULT NULL COMMENT '1：’入库‘，2：’出库‘',
  `quantity` int NULL DEFAULT NULL COMMENT '变动数量',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '便用原因',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory_logs
-- ----------------------------
INSERT INTO `inventory_logs` VALUES (1, 1, 1, 100, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (2, 1, 2, 20, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (3, 2, 1, 50, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (4, 2, 2, 10, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (5, 3, 1, 80, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (6, 3, 2, 30, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (7, 4, 1, 30, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (8, 4, 2, 10, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (9, 5, 1, 40, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (10, 5, 2, 20, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (11, 7, 1, 100, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (12, 7, 2, 50, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (13, 8, 1, 150, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (14, 8, 2, 100, '销售出库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');
INSERT INTO `inventory_logs` VALUES (15, 9, 1, 10, '采购入库', '2024-11-03 14:34:03', '2024-11-03 14:34:03');

-- ----------------------------
-- Table structure for machinery
-- ----------------------------
DROP TABLE IF EXISTS `machinery`;
CREATE TABLE `machinery`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '机械工具表id',
  `product_id` int NULL DEFAULT NULL COMMENT '关联到products表',
  `model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `power` decimal(10, 2) NULL DEFAULT NULL COMMENT '功率',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '重量',
  `dimensions` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '尺寸',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机械表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of machinery
-- ----------------------------
INSERT INTO `machinery` VALUES (1, 9, '小四轮拖拉机', 20.00, 1000.00, '3000x1500x1200', '2024-11-03 14:33:39', '2024-11-03 14:33:39');
INSERT INTO `machinery` VALUES (2, 10, '联合收割机', 100.00, 5000.00, '6000x2000x2500', '2024-11-03 14:33:39', '2024-11-03 14:33:39');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由路径',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端组件路径',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID（0 表示顶级菜单）',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识（如 user:add）',
  `type` enum('DIRECTORY','MENU','BUTTON') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单类型（目录、菜单、按钮）',
  `visible_to` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可见用户类型（用逗号分隔，如 ADMIN,MERCHANT）',
  `sort` int NULL DEFAULT 0 COMMENT '排序字段，值越小越靠前',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标（前端可用）',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏（0 否，1 是）',
  `create_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '仪表盘', '/home', '../pages/home/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR', 0, 'app', 0, '2025-04-12 19:05:31', '2025-04-12 19:05:31');
INSERT INTO `menu` VALUES (2, '订单管理', '/order', '../pages/order/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR,MERCHANT', 2, 'task', 0, '2025-04-12 19:53:32', '2025-04-12 19:53:32');
INSERT INTO `menu` VALUES (3, '用户管理', '/user', '../pages/user/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR', 10, 'user-circle', 0, '2025-04-12 18:56:46', '2025-04-13 15:45:58');
INSERT INTO `menu` VALUES (4, '商家审核', '/merchcantReview', '../pages/audit/merchantReview.vue', 6, NULL, 'DIRECTORY', 'ADMINISTRATOR', 1, 'home', 0, '2025-04-12 19:05:55', '2025-04-15 04:16:29');
INSERT INTO `menu` VALUES (5, '商品审核', '/productReview', '../pages/audit/productReview.vue', 6, NULL, 'DIRECTORY', 'ADMINISTRATOR', 2, 'control-platform', 0, '2025-04-13 07:40:00', '2025-04-15 04:16:48');
INSERT INTO `menu` VALUES (6, '审核管理', NULL, NULL, 0, NULL, 'MENU', 'ADMINISTRATOR', 1, NULL, 0, '2025-04-12 19:05:34', '2025-04-12 19:05:34');
INSERT INTO `menu` VALUES (7, '商品管理', '', '', 0, NULL, 'MENU', 'MERCHANT', 20, 'control-platform', 0, '2025-04-13 18:34:28', '2025-04-13 18:59:37');
INSERT INTO `menu` VALUES (8, '我的商品', '/commodity', '../pages/merchant/myProduct.vue', 7, NULL, 'DIRECTORY', 'MERCHANT', 1, 'control-platform', 0, '2025-04-13 18:59:16', '2025-04-13 19:10:33');
INSERT INTO `menu` VALUES (9, '审核列表', '/myReview', '../pages/merchant/myReview.vue', 7, NULL, 'DIRECTORY', 'MERCHANT', 2, 'system-application', 0, '2025-04-13 19:00:48', '2025-04-13 19:10:19');
INSERT INTO `menu` VALUES (10, '评价管理', '/reviews', '../pages/reviews/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR,MERCHANT', 4, 'tips-double', 0, '2025-04-14 21:38:54', '2025-04-14 21:41:10');
INSERT INTO `menu` VALUES (11, '商品管理', '/productManagement', '../pages/productManagement/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR', 0, 'control-platform', 0, '2025-04-15 00:36:52', '2025-04-15 00:37:10');
INSERT INTO `menu` VALUES (12, '反馈管理', '/feedback', '../pages//feedback/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR', 6, 'view-module', 0, '2025-06-03 14:15:48', '2025-06-03 14:19:37');
INSERT INTO `menu` VALUES (13, '菜单管理', '/menu', '../pages/menu/index.vue', 0, NULL, 'DIRECTORY', 'ADMINISTRATOR', 100, 'menu-application', 0, '2025-06-18 09:14:00', '2025-06-18 09:17:09');

-- ----------------------------
-- Table structure for merchant_review
-- ----------------------------
DROP TABLE IF EXISTS `merchant_review`;
CREATE TABLE `merchant_review`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant_review
-- ----------------------------
INSERT INTO `merchant_review` VALUES (1, '11', 'b05004cbc0badc65d3db340fae8dc74f', 'user005889', '1938675899', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/images/admin/yingtezhihzao.png', 'APPROVED', '', '2025-04-12 04:02:22', '2025-04-12 04:02:22');
INSERT INTO `merchant_review` VALUES (7, '测试商家', '7a122ca4b332f2b3923a585b6dceb178', 'user09990', '18383818881', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-12/2b32af83-ea38-479a-b272-95c6ccd5327d.png', 'APPROVED', NULL, '2025-04-12 14:39:04', '2025-04-12 14:39:04');
INSERT INTO `merchant_review` VALUES (8, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user00939983', '18333837337', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/6b184a31-d630-4d4d-88ce-6b5597c048d5.png', 'APPROVED', NULL, '2025-04-14 23:39:11', '2025-04-14 23:39:11');
INSERT INTO `merchant_review` VALUES (9, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user00939983', '18333837337', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/6b184a31-d630-4d4d-88ce-6b5597c048d5.png', 'PENDING', NULL, '2025-04-14 23:39:11', '2025-04-14 23:39:11');
INSERT INTO `merchant_review` VALUES (10, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user0909888', '18387371771', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/2a8ffbb7-0faa-49de-937b-73e12b58fd73.png', 'REJECTED', '2332', '2025-04-14 23:44:35', '2025-04-14 23:44:35');
INSERT INTO `merchant_review` VALUES (11, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user0909888', '18387371771', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/2a8ffbb7-0faa-49de-937b-73e12b58fd73.png', 'REJECTED', '3232', '2025-04-14 23:44:38', '2025-04-14 23:44:38');
INSERT INTO `merchant_review` VALUES (12, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user0909888', '18387371771', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/2a8ffbb7-0faa-49de-937b-73e12b58fd73.png', 'REJECTED', '444', '2025-04-14 23:44:51', '2025-04-14 23:44:51');
INSERT INTO `merchant_review` VALUES (13, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user0909888', '18387371771', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/2a8ffbb7-0faa-49de-937b-73e12b58fd73.png', 'REJECTED', '444', '2025-04-14 23:44:56', '2025-04-14 23:44:56');
INSERT INTO `merchant_review` VALUES (14, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user0909888', '18387371771', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/2a8ffbb7-0faa-49de-937b-73e12b58fd73.png', 'REJECTED', '444', '2025-04-14 23:45:00', '2025-04-14 23:45:00');
INSERT INTO `merchant_review` VALUES (15, 'shangjia', 'e65b3fe07224a92cd3041c46dcd0af74', 'user098083', '19383717111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-14/12224588-7890-4c7e-b11d-1d1c0e143a8f.png', 'APPROVED', NULL, '2025-04-14 23:46:06', '2025-04-14 23:46:06');
INSERT INTO `merchant_review` VALUES (16, '', 'd41d8cd98f00b204e9800998ecf8427e', '', '', NULL, '', 'PENDING', NULL, '2025-04-15 18:31:57', '2025-04-15 18:31:57');
INSERT INTO `merchant_review` VALUES (17, '商家', '7a122ca4b332f2b3923a585b6dceb178', 'user0909333', '18373718111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-15/54ed2bdd-dc62-4f8e-87f7-b834bcb5aa7e.png', 'APPROVED', NULL, '2025-04-15 20:01:51', '2025-04-15 20:01:51');
INSERT INTO `merchant_review` VALUES (18, '11111', '7a122ca4b332f2b3923a585b6dceb178', '11111', '19338173331', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-15/12adba16-76fb-4fcb-93a4-06372ab4d6d9.png', 'REJECTED', '不具备入驻资格', '2025-04-15 20:08:56', '2025-04-15 20:08:56');
INSERT INTO `merchant_review` VALUES (19, '测试商家111', '7a122ca4b332f2b3923a585b6dceb178', 'user0098876', '18277299111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/c2ebb6e3-7aeb-4455-93a6-745aad311ce6.png', 'APPROVED', NULL, '2025-04-17 14:37:27', '2025-04-17 14:37:27');
INSERT INTO `merchant_review` VALUES (20, '测试商家222', '7a122ca4b332f2b3923a585b6dceb178', 'user1189222', '18373171711', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/57230b11-78e8-4859-80fa-d14cc8d766ec.png', 'APPROVED', NULL, '2025-04-17 15:29:36', '2025-04-17 15:29:36');
INSERT INTO `merchant_review` VALUES (21, '测试商家', '7a122ca4b332f2b3923a585b6dceb178', 'user0098822', '18473811111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/6d07624b-0bda-42ad-96c8-5314c4291903.png', 'APPROVED', NULL, '2025-04-17 15:50:30', '2025-04-17 15:50:30');
INSERT INTO `merchant_review` VALUES (22, '测试商家111', '7a122ca4b332f2b3923a585b6dceb178', 'user009393', '19383818111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/3e7cc7a5-9687-40c5-8418-5642d0d3e9b9.png', 'APPROVED', NULL, '2025-04-17 15:53:41', '2025-04-17 15:53:41');
INSERT INTO `merchant_review` VALUES (23, '测试商家账号', '7a122ca4b332f2b3923a585b6dceb178', 'user000933312', '19831811333', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/9d1d785c-6bee-4df0-816b-412ca104662c.png', 'APPROVED', NULL, '2025-04-17 16:00:32', '2025-04-17 16:00:32');
INSERT INTO `merchant_review` VALUES (24, '商家测试账号', '7a122ca4b332f2b3923a585b6dceb178', 'user0238311', '19282112121', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/ff3e52db-7ff3-4515-9183-8052c62fd82d.jpeg', 'PENDING', NULL, '2025-05-22 16:30:04', '2025-05-22 16:30:04');
INSERT INTO `merchant_review` VALUES (25, '测试商家11', '7a122ca4b332f2b3923a585b6dceb178', 'user282', '18783731111', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-06-03/54971452-c4c1-4c44-bac0-92c356138daa.jpeg', 'PENDING', NULL, '2025-06-03 22:53:12', '2025-06-03 22:53:12');
INSERT INTO `merchant_review` VALUES (30, '2133123', '7a122ca4b332f2b3923a585b6dceb178', '3213123', '19822737218', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-06-04/38f1e862-32c6-425f-b0f5-f380454e0a25.jpeg', 'APPROVED', NULL, '2025-06-04 11:30:25', '2025-06-04 11:30:25');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint NULL DEFAULT NULL COMMENT '关联到orders表',
  `product_id` bigint NULL DEFAULT NULL COMMENT '关联到products表',
  `quantity` int NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价\r\n\r\n单个商品总价格',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  `commented` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 246 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (167, 135, 42, 1, 2.00, NULL, '2025-05-21 15:35:51', '2025-05-22 13:44:59', NULL, NULL, 1, 0);
INSERT INTO `order_detail` VALUES (168, 136, 41, 1, 49.99, 'https://example.com/images/main.jpg', '2025-05-21 15:37:09', '2025-05-22 13:44:57', '绿色有机大米', '精选优质稻谷，绿色有机种植，颗粒饱满，口感香糯。', 1, 1);
INSERT INTO `order_detail` VALUES (169, 137, 8, 1, 50000.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/images/store/tuolaji.png', '2025-05-21 15:51:20', '2025-05-21 20:07:12', '拖拉机', '用于耕地的小型拖拉机。', 1, 0);
INSERT INTO `order_detail` VALUES (170, 138, 42, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/f673656e-3fbb-41cf-a818-e7b15ccd5e1e.png', '2025-05-21 16:04:13', '2025-05-22 13:45:01', '1', '11', 1, 1);
INSERT INTO `order_detail` VALUES (171, 139, 42, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/f673656e-3fbb-41cf-a818-e7b15ccd5e1e.png', '2025-05-21 16:04:52', '2025-05-21 20:06:55', '1', '11', 1, 0);
INSERT INTO `order_detail` VALUES (172, 140, 42, 4, 888.00, NULL, '2025-05-21 16:08:19', '2025-05-21 20:06:52', NULL, NULL, 1, 0);
INSERT INTO `order_detail` VALUES (173, 141, 42, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/f673656e-3fbb-41cf-a818-e7b15ccd5e1e.png', '2025-05-21 16:10:24', '2025-05-21 18:17:25', '1', '11', 1, 0);
INSERT INTO `order_detail` VALUES (174, 142, 42, 1, 222.00, NULL, '2025-05-21 16:12:08', '2025-05-21 18:18:11', NULL, NULL, 1, 0);
INSERT INTO `order_detail` VALUES (175, 143, 42, 1, 222.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/f673656e-3fbb-41cf-a818-e7b15ccd5e1e.png', '2025-05-21 16:23:32', '2025-05-21 18:16:49', '22', '11', 1, 0);
INSERT INTO `order_detail` VALUES (176, 144, 11, 1, 39.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/55fde7de-5dd6-46b6-9d98-5701b56b1a95.png', '2025-05-21 19:35:22', '2025-05-22 13:45:09', '新疆哈密瓜', '好吃', 1, 0);
INSERT INTO `order_detail` VALUES (177, 144, 10, 4, 12.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-04-17/55fde7de-5dd6-46b6-9d98-5701b56b1a95.png', '2025-05-21 19:35:22', '2025-05-22 13:45:09', '新疆哈密瓜', '好吃', 1, 0);
INSERT INTO `order_detail` VALUES (178, 145, 46, 1, 25.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-22 13:45:23', '2025-05-22 13:47:40', '100ml/瓶', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 1, 0);
INSERT INTO `order_detail` VALUES (179, 146, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-22 13:47:48', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (180, 147, 44, 1, 5.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-22 14:13:39', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (181, 148, 43, 1, 10.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/f7f51f1e-b990-4383-9344-a83637b837d6.png', '2025-05-22 14:17:19', NULL, '高效氮肥', '适用于各种农作物的优质氮肥，促进作物生长。', 0, 0);
INSERT INTO `order_detail` VALUES (182, 149, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-22 15:25:55', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (183, 150, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 20:42:04', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (184, 151, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 20:44:43', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (185, 152, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 20:47:57', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (186, 153, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 20:48:49', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (187, 154, 15, 1, 10.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 20:53:13', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (188, 155, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 20:55:14', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (189, 156, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 20:56:39', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (190, 157, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 21:01:19', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (191, 158, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 21:02:18', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (192, 159, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 21:02:44', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (193, 159, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 21:02:44', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (194, 160, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 21:03:08', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (195, 161, 44, 1, 5.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 21:49:30', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (196, 162, 15, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 21:55:03', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (197, 162, 14, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 21:55:03', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (198, 163, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 21:57:55', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (199, 163, 45, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 21:57:55', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (200, 164, 44, 1, 10.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-25 22:03:54', '2025-06-03 23:54:50', '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (201, 164, 45, 1, 26.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-25 22:03:54', '2025-06-03 23:54:53', '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (202, 165, 46, 1, 25.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-25 22:12:37', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (203, 166, 46, 1, 25.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-25 22:12:41', '2025-06-03 23:55:18', '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 1);
INSERT INTO `order_detail` VALUES (204, 167, 46, 1, 25.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-26 16:18:32', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (205, 168, 46, 1, 25.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-26 16:18:32', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (206, 169, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-04 18:51:33', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (207, 170, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-04 18:52:50', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (208, 170, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-04 18:52:50', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (209, 171, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-04 18:59:15', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (210, 172, 44, 1, 10.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-04 19:58:04', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (211, 173, 44, 1, 10.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-04 20:03:24', '2025-06-05 10:14:50', '小型套装（适用50㎡）', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 1);
INSERT INTO `order_detail` VALUES (212, 174, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-05 10:14:27', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (213, 175, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-05 19:26:54', '2025-06-05 19:30:43', '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 1);
INSERT INTO `order_detail` VALUES (214, 175, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-05 19:26:54', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (216, 177, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 22:35:55', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (217, 178, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-06-22 22:37:00', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (218, 179, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 22:48:21', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (219, 179, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 22:48:21', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (220, 180, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 22:51:10', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (221, 180, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 22:51:10', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (222, 181, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 22:52:58', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (223, 181, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 22:52:58', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (224, 182, 45, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-06-22 22:57:05', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (225, 183, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 22:58:24', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (226, 183, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 22:58:24', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (227, 184, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 23:01:30', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (228, 184, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-22 23:01:30', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (229, 184, 45, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-06-22 23:01:30', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (230, 185, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 23:06:17', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (231, 186, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-22 23:10:53', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (232, 187, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-06-23 11:02:37', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (233, 188, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-23 11:09:36', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (234, 189, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-23 11:11:01', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (235, 190, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-23 14:48:26', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (236, 191, 46, 3, 75.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-07-08 21:31:39', NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (237, 192, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-07-08 23:00:24', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (238, 192, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-07-08 23:00:24', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);
INSERT INTO `order_detail` VALUES (239, 192, 45, 2, 52.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-07-08 23:00:24', NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 0, 0);
INSERT INTO `order_detail` VALUES (240, 193, 46, 1, 55.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2026-01-13 09:28:11', NULL, '250ml/瓶', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 0, 0);
INSERT INTO `order_detail` VALUES (241, 194, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2026-01-13 09:29:18', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (242, 195, 48, 4, 4.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2026-01-13 09:29:36', NULL, '10kg/袋', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (243, 196, 48, 1, 2.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2026-01-13 09:34:27', NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 0, 0);
INSERT INTO `order_detail` VALUES (244, 197, 49, 1, NULL, NULL, '2026-01-13 09:37:10', NULL, '11', NULL, 0, 0);
INSERT INTO `order_detail` VALUES (245, 198, 44, 2, 20.00, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2026-01-13 09:40:28', NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 0, 0);

-- ----------------------------
-- Table structure for order_notify
-- ----------------------------
DROP TABLE IF EXISTS `order_notify`;
CREATE TABLE `order_notify`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知类型，例如NEW_ORDER',
  `message_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '订单消息内容（JSON 格式）',
  `status` tinyint NULL DEFAULT 0 COMMENT '通知状态（0-未读，1-已读）',
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家订单通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_notify
-- ----------------------------
INSERT INTO `order_notify` VALUES (1, 189, '1936985318267949056', 'NEW_ORDER', '[OrderItemDTO(productId=48, quantity=1, price=2, image=https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png, name=高产优质小麦种子, description=精选优质小麦品种，适合华北地区种植，抗病性强，产量高。)]', 0, '2025-06-23 11:11:02', '2025-06-23 11:11:02');
INSERT INTO `order_notify` VALUES (2, 190, '1937040030484795392', 'NEW_ORDER', '[OrderItemDTO(productId=48, quantity=1, price=2.00, image=https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png, name=高产优质小麦种子, description=精选优质小麦品种，适合华北地区种植，抗病性强，产量高。)]', 0, '2025-06-23 14:48:59', '2025-06-23 14:48:59');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键，自增',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `order_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单日期，默认为当前时间',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `status` enum('WAIT_PAYMENT','WAIT_DELIVER','WAIT_RECEIVE','FINISHED','CANCELED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'WAIT_PAYMENT' COMMENT '订单状态',
  `payment_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付方式',
  `shipping_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '配送地址',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单编号',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认为当前时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新为当前时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 199 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (135, 33, '2025-05-21 15:35:51', 2.00, 'FINISHED', NULL, '北京市 北京市 东城区 11', '1925093163651960832', '2025-05-21 15:35:51', '2025-06-03 13:39:30', 1);
INSERT INTO `orders` VALUES (136, 33, '2025-05-21 15:37:09', 49.99, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1925093491847860224', '2025-05-21 15:37:09', '2025-05-22 13:44:57', 1);
INSERT INTO `orders` VALUES (137, 33, '2025-05-21 15:51:20', 50000.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925097061372530688', '2025-05-21 15:51:20', '2025-05-21 20:07:12', 1);
INSERT INTO `orders` VALUES (138, 33, '2025-05-21 16:04:13', 2.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1925100304664236032', '2025-05-21 16:04:13', '2025-05-22 13:45:01', 1);
INSERT INTO `orders` VALUES (139, 33, '2025-05-21 16:04:52', 2.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925100464916008960', '2025-05-21 16:04:52', '2025-05-21 20:06:55', 1);
INSERT INTO `orders` VALUES (140, 33, '2025-05-21 16:08:19', 888.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925101334978236416', '2025-05-21 16:08:19', '2025-05-21 20:06:52', 1);
INSERT INTO `orders` VALUES (141, 33, '2025-05-21 16:10:24', 2.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1925101857399771136', '2025-05-21 16:10:24', '2025-05-21 18:17:25', 1);
INSERT INTO `orders` VALUES (142, 33, '2025-05-21 16:12:08', 222.00, 'CANCELED', NULL, NULL, '1925102293108264960', '2025-05-21 16:12:08', '2025-05-21 18:18:11', 1);
INSERT INTO `orders` VALUES (143, 33, '2025-05-21 16:23:32', 222.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925105162033172480', '2025-05-21 16:23:32', '2025-05-21 18:16:49', 1);
INSERT INTO `orders` VALUES (144, 33, '2025-05-21 19:35:22', 51.00, 'FINISHED', NULL, '北京市 北京市 东城区 11', '1925153441190121472', '2025-05-21 19:35:22', '2025-06-03 13:39:32', 1);
INSERT INTO `orders` VALUES (145, 33, '2025-05-22 13:45:23', 25.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925427753604222976', '2025-05-22 13:45:23', '2025-05-22 13:47:40', 1);
INSERT INTO `orders` VALUES (146, 33, '2025-05-22 13:47:48', 52.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1925428358318002176', '2025-05-22 13:47:48', '2025-05-22 13:47:48', 0);
INSERT INTO `orders` VALUES (147, 33, '2025-05-22 14:13:39', 5.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925434866070065152', '2025-05-22 14:13:39', '2025-05-22 14:17:29', 0);
INSERT INTO `orders` VALUES (148, 33, '2025-05-22 14:17:19', 10.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1925435790540804096', '2025-05-22 14:17:19', '2025-05-25 20:47:28', 0);
INSERT INTO `orders` VALUES (149, 33, '2025-05-22 15:25:55', 20.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1925453051116785664', '2025-05-22 15:25:55', '2025-05-22 15:25:55', 0);
INSERT INTO `orders` VALUES (150, 33, '2025-05-25 20:42:04', 52.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926619778395541504', '2025-05-25 20:42:04', '2025-05-25 22:12:12', 0);
INSERT INTO `orders` VALUES (151, 33, '2025-05-25 20:44:43', 52.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926620443205308416', '2025-05-25 20:44:43', '2025-05-25 22:12:13', 0);
INSERT INTO `orders` VALUES (152, 33, '2025-05-25 20:47:57', 52.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926621258263433216', '2025-05-25 20:47:57', '2025-05-25 22:10:22', 0);
INSERT INTO `orders` VALUES (153, 33, '2025-05-25 20:48:49', 52.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926621477659086848', '2025-05-25 20:48:49', '2025-05-25 20:48:49', 0);
INSERT INTO `orders` VALUES (154, 33, '2025-05-25 20:53:13', 10.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926622583248261120', '2025-05-25 20:53:13', '2025-05-25 20:53:13', 0);
INSERT INTO `orders` VALUES (155, 33, '2025-05-25 20:55:14', 20.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926623091023286272', '2025-05-25 20:55:14', '2025-05-25 20:55:14', 0);
INSERT INTO `orders` VALUES (156, 33, '2025-05-25 20:56:39', 20.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926623446700265472', '2025-05-25 20:56:39', '2025-05-25 20:56:39', 0);
INSERT INTO `orders` VALUES (157, 33, '2025-05-25 21:01:19', 20.00, 'FINISHED', NULL, '北京市 北京市 东城区 11', '1926624623907180544', '2025-05-25 21:01:19', '2025-06-03 13:48:41', 0);
INSERT INTO `orders` VALUES (158, 33, '2025-05-25 21:02:18', 52.00, 'FINISHED', NULL, '北京市 北京市 东城区 11', '1926624869454319616', '2025-05-25 21:02:18', '2025-06-03 13:39:37', 0);
INSERT INTO `orders` VALUES (159, 33, '2025-05-25 21:02:44', 72.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926624979387027456', '2025-05-25 21:02:44', '2025-05-25 22:12:10', 0);
INSERT INTO `orders` VALUES (160, 33, '2025-05-25 21:03:08', 52.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926625080411033600', '2025-05-25 21:03:08', '2025-05-25 21:37:35', 0);
INSERT INTO `orders` VALUES (161, 33, '2025-05-25 21:49:30', 5.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926636748419502080', '2025-05-25 21:49:30', '2025-05-25 21:49:38', 0);
INSERT INTO `orders` VALUES (162, 33, '2025-05-25 21:55:03', 72.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926638142429990912', '2025-05-25 21:55:03', '2025-05-25 22:12:08', 0);
INSERT INTO `orders` VALUES (163, 33, '2025-05-25 21:57:55', 72.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926638866777903104', '2025-05-25 21:57:55', '2025-05-25 22:10:02', 0);
INSERT INTO `orders` VALUES (164, 33, '2025-05-25 22:03:54', 36.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1926640369907404800', '2025-05-25 22:03:54', '2025-05-25 22:03:54', 0);
INSERT INTO `orders` VALUES (165, 33, '2025-05-25 22:12:37', 25.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1926642566409883648', '2025-05-25 22:12:37', '2025-05-29 04:20:45', 0);
INSERT INTO `orders` VALUES (166, 33, '2025-05-25 22:12:40', 25.00, 'FINISHED', 'ALIPAY', '广东省 广州市 天河区 珠江新城 XXX 大厦', '1926642579915542528', '2025-05-25 22:12:40', '2025-05-25 22:12:45', 0);
INSERT INTO `orders` VALUES (167, 41, '2025-05-26 16:18:32', 25.00, 'FINISHED', NULL, NULL, '1926915846689656832', '2025-05-26 16:18:32', '2025-06-03 13:48:38', 0);
INSERT INTO `orders` VALUES (168, 41, '2025-05-26 16:18:32', 25.00, 'FINISHED', NULL, '', '1926915846685462528', '2025-05-26 16:18:32', '2025-06-03 23:53:05', 0);
INSERT INTO `orders` VALUES (169, 33, '2025-06-04 18:51:33', 2.00, 'WAIT_PAYMENT', NULL, NULL, '1930215843866742784', '2025-06-04 18:51:33', '2025-06-04 18:51:33', 0);
INSERT INTO `orders` VALUES (170, 33, '2025-06-04 18:52:50', 95.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1930216165519527936', '2025-06-04 18:52:50', '2025-06-19 12:21:08', 0);
INSERT INTO `orders` VALUES (171, 33, '2025-06-04 18:59:15', 2.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1930217783233220608', '2025-06-04 18:59:15', '2025-06-19 12:21:08', 0);
INSERT INTO `orders` VALUES (172, 33, '2025-06-04 19:58:04', 10.00, 'CANCELED', NULL, '北京市 北京市 东城区 11', '1930232582222516224', '2025-06-04 19:58:04', '2025-06-19 12:21:08', 0);
INSERT INTO `orders` VALUES (173, 33, '2025-06-04 20:03:24', 10.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1930233924294938624', '2025-06-04 20:03:24', '2025-06-04 20:03:24', 0);
INSERT INTO `orders` VALUES (174, 33, '2025-06-05 10:14:27', 20.00, 'CANCELED', NULL, '北京市 北京市 东城区 东城区', '1930448098950254592', '2025-06-05 10:14:27', '2025-06-19 12:21:08', 0);
INSERT INTO `orders` VALUES (175, 33, '2025-06-05 19:26:54', 22.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1930587127263924224', '2025-06-05 19:26:54', '2025-06-05 19:27:49', 0);
INSERT INTO `orders` VALUES (177, 33, '2025-06-22 22:35:55', 2.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936795290908626944', '2025-06-22 22:35:55', '2025-06-22 22:35:56', 0);
INSERT INTO `orders` VALUES (178, 33, '2025-06-22 22:37:00', 20.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936795561583841280', '2025-06-22 22:37:00', '2025-06-22 22:37:00', 0);
INSERT INTO `orders` VALUES (179, 33, '2025-06-22 22:48:21', 77.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936798419532255232', '2025-06-22 22:48:21', '2025-06-22 22:48:21', 0);
INSERT INTO `orders` VALUES (180, 33, '2025-06-22 22:51:10', 77.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936799126574469120', '2025-06-22 22:51:10', '2025-06-22 22:51:10', 0);
INSERT INTO `orders` VALUES (181, 33, '2025-06-22 22:52:58', 77.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936799580813398016', '2025-06-22 22:52:58', '2025-06-22 22:52:58', 0);
INSERT INTO `orders` VALUES (182, 33, '2025-06-22 22:57:05', 52.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936800617427243008', '2025-06-22 22:57:05', '2025-06-22 22:57:05', 0);
INSERT INTO `orders` VALUES (183, 33, '2025-06-22 22:58:24', 77.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936800947535745024', '2025-06-22 22:58:24', '2025-06-22 22:58:24', 0);
INSERT INTO `orders` VALUES (184, 33, '2025-06-22 23:01:30', 129.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936801725612691456', '2025-06-22 23:01:30', '2025-06-22 23:01:30', 0);
INSERT INTO `orders` VALUES (185, 33, '2025-06-22 23:06:17', 75.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936802932284592128', '2025-06-22 23:06:17', '2025-06-22 23:06:17', 0);
INSERT INTO `orders` VALUES (186, 33, '2025-06-22 23:10:53', 75.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936804090390646784', '2025-06-22 23:10:53', '2025-06-22 23:10:54', 0);
INSERT INTO `orders` VALUES (187, 33, '2025-06-23 11:02:37', 75.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936983203432763392', '2025-06-23 11:02:37', '2025-06-23 11:02:37', 0);
INSERT INTO `orders` VALUES (188, 33, '2025-06-23 11:09:36', 2.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936984961244598272', '2025-06-23 11:09:36', '2025-06-23 11:09:36', 0);
INSERT INTO `orders` VALUES (189, 33, '2025-06-23 11:11:01', 2.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1936985318267949056', '2025-06-23 11:11:01', '2025-06-23 14:48:00', 0);
INSERT INTO `orders` VALUES (190, 33, '2025-06-23 14:48:26', 2.00, 'FINISHED', 'ALIPAY', '北京市 北京市 东城区 11', '1937040030484795392', '2025-06-23 14:48:26', '2025-06-23 14:48:26', 0);
INSERT INTO `orders` VALUES (191, 33, '2025-07-08 21:31:39', 75.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1942577323333259264', '2025-07-08 21:31:39', '2025-07-08 21:31:39', 0);
INSERT INTO `orders` VALUES (192, 33, '2025-07-08 23:00:24', 74.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '1942599657578434560', '2025-07-08 23:00:24', '2025-07-08 23:00:24', 0);
INSERT INTO `orders` VALUES (193, 33, '2026-01-13 09:28:11', 55.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '2010886561729744896', '2026-01-13 09:28:11', '2026-01-13 09:28:11', 0);
INSERT INTO `orders` VALUES (194, 33, '2026-01-13 09:29:18', 2.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '2010886840936173568', '2026-01-13 09:29:18', '2026-01-13 09:29:18', 0);
INSERT INTO `orders` VALUES (195, 33, '2026-01-13 09:29:36', 16.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '2010886918518214656', '2026-01-13 09:29:36', '2026-01-13 09:30:15', 0);
INSERT INTO `orders` VALUES (196, 33, '2026-01-13 09:34:27', 2.00, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '2010888139014541312', '2026-01-13 09:34:27', '2026-01-13 09:34:27', 0);
INSERT INTO `orders` VALUES (197, 33, '2026-01-13 09:37:10', NULL, 'WAIT_PAYMENT', NULL, '北京市 北京市 东城区 11', '2010888822581235712', '2026-01-13 09:37:10', '2026-01-13 09:37:10', 0);
INSERT INTO `orders` VALUES (198, 33, '2026-01-13 09:40:28', 20.00, 'WAIT_PAYMENT', NULL, '广东省 广州市 天河区 珠江新城 XXX 大厦', '2010889654945058816', '2026-01-13 09:40:28', '2026-01-13 09:40:31', 0);

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '支付表id',
  `order_id` int NULL DEFAULT NULL COMMENT '关联到orders表',
  `payment_method` int NULL DEFAULT NULL COMMENT '1：‘银行转账\',2： \'支付宝\',3： \'微信支付\')',
  `transaction_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易号',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `status` int NULL DEFAULT NULL COMMENT '(1：\'成功\',2： \'失败\',3： \'处理中\')支付状态',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, 1, 2, 'TX1234567890', 250.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (2, 2, 3, 'TX1234567891', 300.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (3, 3, 1, 'TX1234567892', 400.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (4, 4, 2, 'TX1234567893', 500.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (5, 5, 3, 'TX1234567894', 600.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (6, 6, 1, 'TX1234567895', 700.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (7, 7, 2, 'TX1234567896', 800.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (8, 8, 3, 'TX1234567897', 900.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (9, 9, 1, 'TX1234567898', 1000.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (10, 10, 2, 'TX1234567899', 1100.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (11, 1, 2, 'TX1234567890', 250.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (12, 2, 3, 'TX1234567891', 300.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (13, 3, 1, 'TX1234567892', 400.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (14, 4, 2, 'TX1234567893', 500.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (15, 5, 3, 'TX1234567894', 600.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (16, 6, 1, 'TX1234567895', 700.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (17, 7, 2, 'TX1234567896', 800.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (18, 8, 3, 'TX1234567897', 900.00, 2, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (19, 9, 1, 'TX1234567898', 1000.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');
INSERT INTO `payment` VALUES (20, 10, 2, 'TX1234567899', 1100.00, 1, '2024-11-03 15:18:56', '2024-11-03 15:18:56');

-- ----------------------------
-- Table structure for pesticides
-- ----------------------------
DROP TABLE IF EXISTS `pesticides`;
CREATE TABLE `pesticides`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '农药表id',
  `product_id` int NULL DEFAULT NULL COMMENT '关联products表',
  `activa_ingredient` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '有效成分',
  `target_pests` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '目标害虫',
  `usage_frequency` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '使用频率',
  `precautions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '注意事项',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '农药表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pesticides
-- ----------------------------
INSERT INTO `pesticides` VALUES (1, 4, '敌敌畏', '蚜虫、螨虫等', '每周一次', '避免接触皮肤和眼睛，使用后洗手。', '2024-11-03 15:17:35', '2024-11-03 15:17:35');
INSERT INTO `pesticides` VALUES (2, 5, '多菌灵', '白粉病、锈病等', '每两周一次', '避免在开花期使用，以免影响授粉。', '2024-11-03 15:17:35', '2024-11-03 15:17:35');
INSERT INTO `pesticides` VALUES (3, 13, '高效杀虫剂', '多种害虫', '每周一次', '避免高温时段使用，以免影响作物生长。', '2024-11-03 15:17:35', '2024-11-03 15:17:35');
INSERT INTO `pesticides` VALUES (4, 14, '高效杀菌剂', '多种病害', '每两周一次', '避免与酸性物质混合使用。', '2024-11-03 15:17:35', '2024-11-03 15:17:35');

-- ----------------------------
-- Table structure for product_image_review
-- ----------------------------
DROP TABLE IF EXISTS `product_image_review`;
CREATE TABLE `product_image_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_review_id` int NULL DEFAULT NULL COMMENT '商品审核id',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品图片审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_image_review
-- ----------------------------
INSERT INTO `product_image_review` VALUES (40, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/34f26e68-b448-4392-8ea9-bc4efa011d6b.png', 24, '2025-05-19 15:01:10', '2025-05-19 15:01:10');
INSERT INTO `product_image_review` VALUES (41, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-19/746ca52c-e376-4d4b-860d-fe7f42abbd68.png', 25, '2025-05-19 15:55:26', '2025-05-19 15:55:26');
INSERT INTO `product_image_review` VALUES (42, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/4caf388e-3748-4bc9-a53a-fdd5240d3d5f.png', 26, '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `product_image_review` VALUES (43, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/87dd7a92-2430-4d37-826a-66f578f085ad.png', 26, '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `product_image_review` VALUES (44, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/0c5e2174-b338-4f3e-a931-d696ee530795.jpeg', 27, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `product_image_review` VALUES (45, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/551bfd3a-3137-46f1-afdb-91e3c6add7e0.jpeg', 27, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `product_image_review` VALUES (46, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/c8f9c8ef-5033-45a6-8890-8c589f78bbc0.jpeg', 27, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `product_image_review` VALUES (47, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/85ddf69e-62e6-43a5-8716-4add6667b1a1.jpeg', 28, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `product_image_review` VALUES (48, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/906c5fdd-6ae2-4212-af86-da2d57611316.jpeg', 28, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `product_image_review` VALUES (49, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/a9b2522a-9749-4693-8f9a-caa4b94b609e.jpeg', 28, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `product_image_review` VALUES (50, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/472c5060-34bb-4b99-a49b-9c7c31c8f57a.jpeg', 29, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `product_image_review` VALUES (51, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/e589d77c-010e-4610-8216-a34ec2e1b52c.jpeg', 29, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `product_image_review` VALUES (52, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/95f59d9c-d626-41ad-8a09-ee80a94b45a1.jpeg', 29, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `product_image_review` VALUES (53, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/9827b7fa-7d7a-404c-b739-81d369f77348.png', 30, '2025-05-22 16:34:21', '2025-05-22 16:34:21');
INSERT INTO `product_image_review` VALUES (54, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/7d41fc64-e8ae-4bd5-95d1-d1c1232c132c.png', 30, '2025-05-22 16:34:21', '2025-05-22 16:34:21');
INSERT INTO `product_image_review` VALUES (55, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/1a6ed9e2-7522-41f1-a195-a363cf7967c9.jpeg', 30, '2025-05-22 16:34:21', '2025-05-22 16:34:21');

-- ----------------------------
-- Table structure for product_images
-- ----------------------------
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_id` int NULL DEFAULT NULL COMMENT '商品id',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_images
-- ----------------------------
INSERT INTO `product_images` VALUES (56, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/4caf388e-3748-4bc9-a53a-fdd5240d3d5f.png', 43, '2025-05-22 00:16:23', '2025-05-22 00:16:23');
INSERT INTO `product_images` VALUES (57, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/87dd7a92-2430-4d37-826a-66f578f085ad.png', 43, '2025-05-22 00:16:23', '2025-05-22 00:16:23');
INSERT INTO `product_images` VALUES (58, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/472c5060-34bb-4b99-a49b-9c7c31c8f57a.jpeg', 44, '2025-05-22 00:27:55', '2025-05-22 00:27:55');
INSERT INTO `product_images` VALUES (59, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/e589d77c-010e-4610-8216-a34ec2e1b52c.jpeg', 44, '2025-05-22 00:27:55', '2025-05-22 00:27:55');
INSERT INTO `product_images` VALUES (60, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/95f59d9c-d626-41ad-8a09-ee80a94b45a1.jpeg', 44, '2025-05-22 00:27:55', '2025-05-22 00:27:55');
INSERT INTO `product_images` VALUES (61, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/85ddf69e-62e6-43a5-8716-4add6667b1a1.jpeg', 45, '2025-05-22 00:27:57', '2025-05-22 00:27:57');
INSERT INTO `product_images` VALUES (62, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/906c5fdd-6ae2-4212-af86-da2d57611316.jpeg', 45, '2025-05-22 00:27:57', '2025-05-22 00:27:57');
INSERT INTO `product_images` VALUES (63, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/a9b2522a-9749-4693-8f9a-caa4b94b609e.jpeg', 45, '2025-05-22 00:27:57', '2025-05-22 00:27:57');
INSERT INTO `product_images` VALUES (64, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/0c5e2174-b338-4f3e-a931-d696ee530795.jpeg', 46, '2025-05-22 00:27:59', '2025-05-22 00:27:59');
INSERT INTO `product_images` VALUES (65, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/551bfd3a-3137-46f1-afdb-91e3c6add7e0.jpeg', 46, '2025-05-22 00:27:59', '2025-05-22 00:27:59');
INSERT INTO `product_images` VALUES (66, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/c8f9c8ef-5033-45a6-8890-8c589f78bbc0.jpeg', 46, '2025-05-22 00:27:59', '2025-05-22 00:27:59');
INSERT INTO `product_images` VALUES (67, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/9827b7fa-7d7a-404c-b739-81d369f77348.png', 48, '2025-06-03 13:51:10', '2025-06-03 13:51:10');
INSERT INTO `product_images` VALUES (68, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/7d41fc64-e8ae-4bd5-95d1-d1c1232c132c.png', 48, '2025-06-03 13:51:10', '2025-06-03 13:51:10');
INSERT INTO `product_images` VALUES (69, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/1a6ed9e2-7522-41f1-a195-a363cf7967c9.jpeg', 48, '2025-06-03 13:51:10', '2025-06-03 13:51:10');

-- ----------------------------
-- Table structure for product_review
-- ----------------------------
DROP TABLE IF EXISTS `product_review`;
CREATE TABLE `product_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原始价格',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `stock` int NULL DEFAULT NULL COMMENT '库存量',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主图',
  `status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '审核状态',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '拒绝理由',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品品牌',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品产地',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_review
-- ----------------------------
INSERT INTO `product_review` VALUES (26, 31, '高效氮肥', '适用于各种农作物的优质氮肥，促进作物生长。', 20.00, 10.00, 800, '袋', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/f7f51f1e-b990-4383-9344-a83637b837d6.png', 'APPROVED', NULL, ' 绿源农业', '山东省潍坊市', '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `product_review` VALUES (27, 31, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 50.00, 25.00, 200, '瓶', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', 'APPROVED', NULL, '金农科技', '江苏省南京市', '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `product_review` VALUES (28, 31, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 80.00, 20.00, 180, '袋', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', 'APPROVED', NULL, '先锋种业', '河南省郑州市', '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `product_review` VALUES (29, 31, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 10.00, 5.00, 312, '㎡', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', 'APPROVED', NULL, '农田宝', '浙江省杭州市', '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `product_review` VALUES (30, 31, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 50.00, 2.00, 422, '袋', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', 'APPROVED', NULL, '丰收农资', '东北', '2025-05-22 16:34:21', '2025-05-22 16:34:21');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '农用物资表id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `category_id` int NULL DEFAULT NULL COMMENT '关联到categories表',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物资名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '物资描述',
  `original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `stock` int NULL DEFAULT NULL COMMENT '库存量',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位（如公斤、升、件等）',
  `sold` int UNSIGNED NULL DEFAULT 0 COMMENT '已售数量',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '首图链接',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产地',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (43, 31, NULL, '高效氮肥', '适用于各种农作物的优质氮肥，促进作物生长。', 20.00, 10.00, 800, '袋', 8, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/f7f51f1e-b990-4383-9344-a83637b837d6.png', '2025-05-22 00:16:23', '2025-05-25 15:36:40', ' 绿源农业', '山东省潍坊市');
INSERT INTO `products` VALUES (44, 31, NULL, '滴灌设备套装', '节水型滴灌系统，安装方便，适合大棚、田间等多场景灌溉使用。', 10.00, 5.00, 312, '㎡', 6, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/b80cd610-f666-43d5-bb21-eb54066045e3.jpeg', '2025-05-22 00:27:55', '2025-05-25 15:36:40', '农田宝', '浙江省杭州市');
INSERT INTO `products` VALUES (45, 31, NULL, '玉米种子-先锋系列', '高产抗病玉米种子，出苗率高，适合多种气候条件', 80.00, 20.00, 180, '袋', 7, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/826c7d09-4e7a-45cf-b295-34294ccb9f0c.jpeg', '2025-05-22 00:27:57', '2025-05-25 15:36:41', '先锋种业', '河南省郑州市');
INSERT INTO `products` VALUES (46, 31, NULL, '杀虫剂-绿盾', '广谱高效杀虫剂，低毒环保，有效预防多种害虫。', 50.00, 25.00, 200, '瓶', 2, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/61f2423e-86fc-44b0-b630-b7e1cc0a2ef5.jpeg', '2025-05-22 00:27:59', '2025-05-25 15:36:42', '金农科技', '江苏省南京市');
INSERT INTO `products` VALUES (48, 31, NULL, '高产优质小麦种子', '精选优质小麦品种，适合华北地区种植，抗病性强，产量高。', 50.00, 2.00, 422, '袋', 2, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-22/d0aa7880-3818-4b98-9825-a7bf9749c67a.png', '2025-06-03 13:51:10', '2025-06-03 13:51:10', '丰收农资', '东北');
INSERT INTO `products` VALUES (49, 31, NULL, '11', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2025-06-03 13:51:59', '2025-06-03 13:51:59', NULL, NULL);

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评价表id',
  `product_id` bigint NULL DEFAULT NULL COMMENT '关联到products表',
  `user_id` bigint NULL DEFAULT NULL COMMENT '关联到users表',
  `rating` tinyint NULL DEFAULT NULL COMMENT '评分（1-5星）',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评价内容',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES (19, 46, 33, 5, '质量好', '2025-06-03 13:47:35', '2025-06-03 13:47:35');
INSERT INTO `reviews` VALUES (20, 44, 33, 5, '好商品', '2025-06-03 13:48:05', '2025-06-03 13:48:05');
INSERT INTO `reviews` VALUES (21, 45, 33, 1, '质量有问题', '2025-06-03 13:48:13', '2025-06-03 13:48:13');
INSERT INTO `reviews` VALUES (22, 45, 33, 4, '1211', '2025-06-03 13:49:18', '2025-06-03 13:49:28');
INSERT INTO `reviews` VALUES (23, 45, 33, 5, '111', '2025-06-03 13:49:37', '2025-06-03 13:49:37');
INSERT INTO `reviews` VALUES (24, 46, 33, 5, '131', '2025-06-03 13:49:53', '2025-06-03 13:49:53');
INSERT INTO `reviews` VALUES (25, 46, 31, 4, '好产品！！', '2025-06-03 23:55:18', '2025-06-03 23:55:18');
INSERT INTO `reviews` VALUES (26, 44, 31, 5, '···', '2025-06-05 10:14:50', '2025-06-05 10:14:50');
INSERT INTO `reviews` VALUES (27, 48, 31, 4, '2221', '2025-06-05 19:30:43', '2025-06-05 19:30:43');

-- ----------------------------
-- Table structure for seeds
-- ----------------------------
DROP TABLE IF EXISTS `seeds`;
CREATE TABLE `seeds`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '种子表id',
  `product_id` int NULL DEFAULT NULL COMMENT '关联到products表',
  `variety` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品种',
  `germination_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '发芽率',
  `suitable_soil` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '适宜土壤',
  `planting_season` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '播种季节',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '种子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seeds
-- ----------------------------
INSERT INTO `seeds` VALUES (1, 6, '西红柿', 95.00, '沙壤土', '春季', '2024-11-03 15:18:08', '2024-11-03 15:18:08');
INSERT INTO `seeds` VALUES (2, 7, '小麦', 90.00, '黏壤土', '秋季', '2024-11-03 15:18:08', '2024-11-03 15:18:08');
INSERT INTO `seeds` VALUES (3, 11, '绿肥植物', 98.00, '各种土壤', '全年', '2024-11-03 15:18:08', '2024-11-03 15:18:08');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'SKU 主键',
  `product_id` bigint NOT NULL COMMENT '对应的商品 SPU ID',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 名称（如10kg装）',
  `sku_price` decimal(10, 2) NULL DEFAULT NULL COMMENT 'SKU单价',
  `stock` int NOT NULL DEFAULT 0 COMMENT '该 SKU 独立库存（如果有差异）',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 SKU 表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 30, ' 20/ kg ', 58.98, 10, '2025-04-16 14:47:48', '2026-01-13 07:51:09', 4);
INSERT INTO `sku` VALUES (2, 30, '40/ kg', 69.98, 8, '2025-04-16 14:48:10', '2026-01-13 07:51:10', 2);
INSERT INTO `sku` VALUES (3, 32, '小个  10kg', 39.00, 20, '2025-04-17 15:58:10', '2026-01-13 07:51:11', 1);
INSERT INTO `sku` VALUES (4, 32, '大个  20kg', 32.00, 10, '2025-04-17 15:58:26', '2026-01-13 07:51:13', 2);
INSERT INTO `sku` VALUES (5, 32, '超大个  50kg', 3.00, 4, '2025-04-17 15:58:39', '2026-01-13 07:51:14', 4);
INSERT INTO `sku` VALUES (6, 41, 'hh', 22.22, 100, '2025-05-19 14:17:23', '2026-01-13 07:51:13', 2);
INSERT INTO `sku` VALUES (7, 42, '22', 222.00, 2, '2025-05-19 15:56:14', '2026-01-13 07:51:15', 4);
INSERT INTO `sku` VALUES (8, 42, '33', 33333.00, 33, '2025-05-19 15:56:14', '2026-01-13 07:51:16', 1);
INSERT INTO `sku` VALUES (9, 43, '10kg/袋', 5.00, 200, '2025-05-22 00:16:23', '2025-05-22 00:16:23', 0);
INSERT INTO `sku` VALUES (10, 43, '25kg/袋', 10.00, 150, '2025-05-22 00:16:23', '2025-05-22 00:16:23', 0);
INSERT INTO `sku` VALUES (11, 43, '50kg/袋', 20.00, 200, '2025-05-22 00:16:23', '2025-05-22 00:16:23', 0);
INSERT INTO `sku` VALUES (12, 44, '小型套装（适用50㎡）', 10.00, 33211, '2025-05-22 00:27:55', '2026-01-13 07:51:16', 4);
INSERT INTO `sku` VALUES (13, 44, '中型套装（适用100㎡）', 20.00, 33, '2025-05-22 00:27:55', '2025-05-22 00:27:55', 0);
INSERT INTO `sku` VALUES (14, 44, '大型套装（适用200㎡）', 30.00, 1311, '2025-05-22 00:27:55', '2026-01-13 07:51:22', 6);
INSERT INTO `sku` VALUES (15, 45, '5kg/袋', 20.00, 199, '2025-05-22 00:27:57', '2025-05-22 00:27:57', 0);
INSERT INTO `sku` VALUES (16, 45, '10kg/袋', 26.00, 213, '2025-05-22 00:27:57', '2025-05-22 00:27:57', 0);
INSERT INTO `sku` VALUES (17, 45, '20kg/袋', 40.00, 122, '2025-05-22 00:27:57', '2025-05-22 00:27:57', 0);
INSERT INTO `sku` VALUES (18, 46, '100ml/瓶', 25.00, 300, '2025-05-22 00:27:59', '2026-01-13 07:51:17', 4);
INSERT INTO `sku` VALUES (19, 46, '250ml/瓶', 55.00, 200, '2025-05-22 00:27:59', '2026-01-13 07:51:24', 9);
INSERT INTO `sku` VALUES (20, 46, '500ml/瓶', 100.00, 120, '2025-05-22 00:27:59', '2025-05-22 00:27:59', 0);
INSERT INTO `sku` VALUES (21, 48, '5kg/袋', 2.00, 133, '2025-06-03 13:51:10', '2025-06-03 13:51:10', 0);
INSERT INTO `sku` VALUES (22, 48, '10kg/袋', 4.00, 31, '2025-06-03 13:51:10', '2026-01-13 07:51:19', 1);
INSERT INTO `sku` VALUES (23, 48, '15kg/袋', 4.00, 229, '2025-06-03 13:51:10', '2025-06-03 13:51:10', 0);

-- ----------------------------
-- Table structure for sku_review
-- ----------------------------
DROP TABLE IF EXISTS `sku_review`;
CREATE TABLE `sku_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'SKU 主键',
  `product_review_id` bigint NOT NULL COMMENT '对应的商品 SPU ID',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 名称（如10kg装）',
  `sku_price` decimal(10, 2) NULL DEFAULT NULL COMMENT 'SKU单价',
  `stock` int NOT NULL DEFAULT 0 COMMENT '该 SKU 独立库存（如果有差异）',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 SKU 表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sku_review
-- ----------------------------
INSERT INTO `sku_review` VALUES (6, 23, 'hh', 22.22, 100, '2025-05-19 14:04:05', '2025-05-19 14:04:05');
INSERT INTO `sku_review` VALUES (7, 24, 'sds', 1212.00, 12, '2025-05-19 15:01:10', '2025-05-19 15:01:10');
INSERT INTO `sku_review` VALUES (8, 25, '22', 222.00, 2, '2025-05-19 15:55:26', '2025-05-19 15:55:26');
INSERT INTO `sku_review` VALUES (9, 25, '33', 33333.00, 33, '2025-05-19 15:55:26', '2025-05-19 15:55:26');
INSERT INTO `sku_review` VALUES (10, 26, '10kg/袋', 5.00, 200, '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `sku_review` VALUES (11, 26, '25kg/袋', 10.00, 150, '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `sku_review` VALUES (12, 26, '50kg/袋', 20.00, 200, '2025-05-22 00:16:05', '2025-05-22 00:16:05');
INSERT INTO `sku_review` VALUES (13, 27, '100ml/瓶', 25.00, 300, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `sku_review` VALUES (14, 27, '250ml/瓶', 55.00, 200, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `sku_review` VALUES (15, 27, '500ml/瓶', 100.00, 120, '2025-05-22 00:21:34', '2025-05-22 00:21:34');
INSERT INTO `sku_review` VALUES (16, 28, '5kg/袋', 20.00, 199, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `sku_review` VALUES (17, 28, '10kg/袋', 26.00, 213, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `sku_review` VALUES (18, 28, '20kg/袋', 40.00, 122, '2025-05-22 00:24:39', '2025-05-22 00:24:39');
INSERT INTO `sku_review` VALUES (19, 29, '小型套装（适用50㎡）', 10.00, 33211, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `sku_review` VALUES (20, 29, '中型套装（适用100㎡）', 20.00, 33, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `sku_review` VALUES (21, 29, '大型套装（适用200㎡）', 30.00, 1311, '2025-05-22 00:27:34', '2025-05-22 00:27:34');
INSERT INTO `sku_review` VALUES (22, 30, '5kg/袋', 2.00, 133, '2025-05-22 16:34:21', '2025-05-22 16:34:21');
INSERT INTO `sku_review` VALUES (23, 30, '10kg/袋', 4.00, 31, '2025-05-22 16:34:21', '2025-05-22 16:34:21');
INSERT INTO `sku_review` VALUES (24, 30, '15kg/袋', 4.00, 229, '2025-05-22 16:34:21', '2025-05-22 16:34:21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `type` enum('MEMBER','MERCHANT','ADMINISTRATOR') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('ADMINISTRATOR', 1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '19387646764', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/client/2025-04-17/7e3c91a6-976b-4eb8-9f9c-464ad2f71660.png', NULL, '女', '2025-04-15 00:12:32', '2025-04-17 02:55:21', NULL);
INSERT INTO `user` VALUES ('MERCHANT', 31, 'shangjia', '7a122ca4b332f2b3923a585b6dceb178', 'user098083', '19383717111', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/client/2025-04-17/7e3c91a6-976b-4eb8-9f9c-464ad2f71660.png', NULL, '女', '2025-04-14 23:48:04', '2025-04-17 02:55:22', NULL);
INSERT INTO `user` VALUES ('MEMBER', 33, 'chengyuan', 'b5980a43ca75df406ebfc797e26109e4', 'user0092211', '13381847122', 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-06-07/052cabbd-7333-4d92-ba6f-555617e2c3ab.png', NULL, '男', '2025-04-15 00:17:28', '2025-06-07 10:45:16', '');
INSERT INTO `user` VALUES ('MEMBER', 40, 'user009333', 'b05004cbc0badc65d3db340fae8dc74f', NULL, NULL, NULL, NULL, NULL, '2025-05-26 14:33:59', '2025-05-26 15:37:49', '');
INSERT INTO `user` VALUES ('MEMBER', 41, '用户测试账号', 'b5980a43ca75df406ebfc797e26109e4', 'user434', NULL, 'https://shop-1325336690.cos.ap-guangzhou.myqcloud.com/manager/2025-05-26/76afa522-3a24-4ee6-bfcb-3a8c3f0e232c.png', NULL, '男', '2025-05-26 16:15:51', '2025-06-04 19:03:13', '');
INSERT INTO `user` VALUES ('MEMBER', 42, '测试用户11', 'b05004cbc0badc65d3db340fae8dc74f', '', NULL, NULL, NULL, NULL, '2025-06-05 19:21:43', '2025-06-07 14:46:31', '');
INSERT INTO `user` VALUES ('MEMBER', 43, 'yonghu11', 'b05004cbc0badc65d3db340fae8dc74f', NULL, NULL, NULL, NULL, NULL, '2025-06-05 19:26:12', '2025-06-07 14:46:28', '');
INSERT INTO `user` VALUES ('MERCHANT', 44, '2133123', '7a122ca4b332f2b3923a585b6dceb178', '3213123', '19822737218', NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
