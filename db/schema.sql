-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名',
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '商品编号',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `purchase_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '采购时间',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '是否有效（1=是；0=否）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('6', 'Java编程思想', 'book10010', '1000', '2020-05-18 21:11:23', '1', '2020-05-18 21:11:23', '2020-05-18 21:11:23');
INSERT INTO `product` VALUES ('7', 'Spring实战第四版', 'book10011', '2000', '2020-05-18 21:11:23', '1', '2020-05-18 21:11:23', '2020-05-18 21:11:23');
INSERT INTO `product` VALUES ('8', '深入分析JavaWeb', 'book10012', '2000', '2019-05-18', '1', '2019-05-18 21:11:23', '2020-05-18 21:11:23');

-- ----------------------------
-- Table structure for seckill_session
-- ----------------------------
DROP TABLE IF EXISTS `seckill_session`;
CREATE TABLE `seckill_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品id',
  `total` int(11) NOT NULL DEFAULT 0 COMMENT '秒杀商品数',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  `status` tinyint(11) NOT NULL DEFAULT 1 COMMENT '是否有效（1=是；0=否）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='秒杀活动表';

-- ----------------------------
-- Records of seckill_session
-- ----------------------------
INSERT INTO `seckill_session` VALUES ('1', '6', '10', '2020-06-08 21:59:07', '2020-06-15 21:59:11', '1', '2020-05-20 21:59:41');
INSERT INTO `seckill_session` VALUES ('2', '7', '0', '2020-06-01 21:59:19', '2020-06-30 21:59:11', '1', '2020-05-20 21:59:41');
INSERT INTO `seckill_session` VALUES ('3', '8', '97', '2020-07-01 18:58:26', '2020-07-30 21:59:07', '1', '2020-05-20 21:59:41');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `code` varchar(50) NOT NULL COMMENT '订单编号',
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品id',
  `num` int(11) NOT NULL DEFAULT 0 COMMENT '商品数量',
  `seckill_id` int(11) NOT NULL DEFAULT 0 COMMENT '秒杀场次id',
  `user_id` varchar(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT '秒杀结果: -1无效  0成功(未付款)  1已付款  2已取消',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for random_code
-- ----------------------------
DROP TABLE IF EXISTS `random_code`;
CREATE TABLE `random_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of random_code
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '邮箱',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效(1=是；0=否)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', 'yymuhua', '80bab46abb7b1c4013f9971b8bec3868', '17816861105', '469268632@qq.com', '1', DEFAULT, DEFAULT);
