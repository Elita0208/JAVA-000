
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别: 1男，2女，0未知',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


DROP TABLE IF EXISTS `shop_products`;
CREATE TABLE `shop_products` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT 'code',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `sales_num` int(11) DEFAULT NULL COMMENT '销量',
  `show_sales_num` tinyint(4) DEFAULT '0' COMMENT '是否在商品列表页、详情页展示销量, 1是 0否',
  `freight_id` tinyint(4) DEFAULT NULL COMMENT '买家是否需要付运费，0否，非0表示运费规则id',
  `sell_status` tinyint(4) NOT NULL DEFAULT '2' COMMENT '销售状态 1 上架 2 下架 3 定时上架',
  `sell_at` datetime DEFAULT NULL COMMENT '上架时间',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `detail` text COMMENT '商品详情',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类 shop_product_category.id',
  `product_classification_id` bigint(20) DEFAULT NULL COMMENT '商品属性类型 shop_product_classification.id',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `seckill_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '秒杀状态(0:关闭;1:开启)',
  `seckill_start_time` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `seckill_end_time` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';


DROP TABLE IF EXISTS `shop_product_categories`;
CREATE TABLE `shop_product_categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父类id',
  `desc` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `img` varchar(255) DEFAULT NULL COMMENT '分类图片',
  `show_status` tinyint(4) DEFAULT '0' COMMENT '是否显示 0/1',
  `recommend_status` tinyint(4) DEFAULT '0' COMMENT '是否推荐 0/1',
  `numerous_sell_status` tinyint(4) DEFAULT '0' COMMENT '是否热卖 0/1',
  `display_order` int(11) DEFAULT '0' COMMENT '显示顺序',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';


DROP TABLE IF EXISTS `shop_product_and_category_relations`;
CREATE TABLE `shop_product_and_category_relations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT 'apps.id',
  `product_id` bigint(20) NOT NULL COMMENT 'products.id',
  `product_category_id` bigint(20) NOT NULL COMMENT 'product_categories.id',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `shop_product_classifications`;
CREATE TABLE `shop_product_classifications` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品类型名称',
  `show` tinyint(4) DEFAULT '1' COMMENT '是否显示',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性分类表';

DROP TABLE IF EXISTS `shop_product_specifications`;
CREATE TABLE `shop_product_specifications` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '规格属性名称',
  `product_classification_id` bigint(20) DEFAULT NULL COMMENT '商品规格类型id',
  `show` tinyint(4) DEFAULT '1' COMMENT '是否显示',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格属性';


DROP TABLE IF EXISTS `shop_product_specification_items`;
CREATE TABLE `shop_product_specification_items` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格属性id',
  `value` varchar(255) DEFAULT NULL COMMENT '属性值',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品-商品分类关系表';


DROP TABLE IF EXISTS `shop_product_images`;
CREATE TABLE `shop_product_images` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `img_type` tinyint(4) DEFAULT NULL COMMENT '图片类型，1主图，2详情页图，3缩略图',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品图片';


DROP TABLE IF EXISTS `shop_product_skus`;
CREATE TABLE `shop_product_skus` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `code` varchar(255) DEFAULT NULL COMMENT 'sku编号',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `price` decimal(8,2) DEFAULT NULL COMMENT '价格',
  `original_price` decimal(8,2) DEFAULT NULL COMMENT '原价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `stock_infinity` tinyint(4) NOT NULL DEFAULT '0' COMMENT '不限库存 1不限 0非不限',
  `detail` varchar(255) DEFAULT NULL COMMENT 'sku详细的设置',
  `properties` varchar(255) DEFAULT NULL COMMENT '规格id组合值，多个以逗号分隔',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存表';

DROP TABLE IF EXISTS `shop_orders`;
CREATE TABLE `shop_orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号唯一值，供客户查询',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态：1 未付款, 2 已付款(待发货), 3 已发货, 4 已签收, 5 退款申请, 6 退款中(待退款), 7 已退款, 8 取消交易\n',
  `product_count` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量, 商品项目数量，不是商品',
  `product_amount_total` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品总价',
  `order_amount_total` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额 实际付款金额',
  `logistics_fee` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `address_id` bigint(20) DEFAULT NULL COMMENT '地址 user_address.id',
  `order_logistics_id` int(11) DEFAULT NULL COMMENT '订单物流编号',
  `transaction_id` varchar(255) DEFAULT NULL COMMENT '第三方支付流水号',
  `user_id` bigint(20) NOT NULL COMMENT '用户 wechat_user.id',
  `paid_at` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_at` datetime DEFAULT NULL COMMENT '发货时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '客户备注',
  `complete_at` datetime DEFAULT NULL COMMENT '完成时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '评论状态 0未评论 1已评论',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(255) DEFAULT NULL COMMENT '收货人手机号',
  `receiver_address` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';


DROP TABLE IF EXISTS `shop_order_details`;
CREATE TABLE `shop_order_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT 'orders.id',
  `product_id` bigint(20) NOT NULL COMMENT 'products.id',
  `product_name` varchar(255) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_marque` varchar(255) NOT NULL COMMENT '商品型号',
  `product_mode_desc` varchar(255) NOT NULL COMMENT '商品型号信息 记录详细商品型号，如颜色、规格、包装等',
  `product_mode_params` varchar(255) NOT NULL COMMENT 'JSON格式，记录单位编号、颜色编号、规格编号等',
  `amount` int(11) NOT NULL COMMENT '购买数量',
  `subtotal` decimal(8,2) NOT NULL COMMENT '小计金额',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品是否有效',
  `memo` varchar(255) NOT NULL COMMENT '客户商品备注',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='订单快照表';

DROP TABLE IF EXISTS `shop_comments`;
CREATE TABLE `shop_comments` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '创建人',
  `product_id` bigint(20) NOT NULL COMMENT '商品',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品规格id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单',
  `content` longtext COMMENT '评论内容',
  `audit_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '审核状态：1未审核 2审核通过 3审核不通过',
  `audit_by` int(11) NOT NULL DEFAULT '0' COMMENT '审核人',
  `audit_at` datetime DEFAULT NULL COMMENT '审核时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论';

DROP TABLE IF EXISTS `shop_product_bargain`;
CREATE TABLE `shop_product_bargain` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT 'shop_products.id',
  `num` int(10) unsigned NOT NULL COMMENT '总份数',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价(元)',
  `price` decimal(10,2) NOT NULL COMMENT '底价(元)',
  `bargain_min_price` decimal(10,2) NOT NULL COMMENT '砍价最低金额(元)',
  `bargain_max_price` decimal(10,2) NOT NULL COMMENT '砍价最高金额(元)',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '截止时间',
  `status` tinyint(4) NOT NULL COMMENT '状态(0:正常;1:禁用;-1:删除)',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品-砍价配置';


DROP TABLE IF EXISTS `shop_favors`;
CREATE TABLE `shop_favors` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT 'users.id',
  `type` tinyint(4) NOT NULL COMMENT '收藏类型 1产品收藏 2内容收藏',
  `subject_id` bigint(20) NOT NULL COMMENT '被收藏者的id',
  `content` text NOT NULL COMMENT '内容',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否失效 1有效 0失效',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏表';

DROP TABLE IF EXISTS `shop_shoppingcart`;
CREATE TABLE `shop_shoppingcart` (
  `identifier` varchar(255) NOT NULL,
  `instance` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`identifier`,`instance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `shop_freights`;
CREATE TABLE `shop_freights` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `freight_type` tinyint(4) NOT NULL COMMENT '运费类型, 0 免运费 1满减运费',
  `base_value` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '基础运费',
  `trigger_value` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '不同运费类型对应的值',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '修改人',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='临时的运费设置表';

DROP TABLE IF EXISTS `shop_user_addresses`;
CREATE TABLE `shop_user_addresses` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `location` varchar(255) NOT NULL COMMENT '省市区code, 逗号分隔',
  `region_id` int(11) DEFAULT NULL COMMENT '省份id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户 wechat_users.id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `tel` varchar(20) DEFAULT NULL COMMENT '手机号',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认 1是 0不是',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户收货地址';



