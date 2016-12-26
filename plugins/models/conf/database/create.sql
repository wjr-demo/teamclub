##配置表
CREATE TABLE `admin_global_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(16) NOT NULL,
  `domain_id` int(11) DEFAULT '0',
  `func_code` varchar(64) DEFAULT NULL,
  `func_name` varchar(255) DEFAULT NULL,
  `model_code` varchar(64) DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `key_code` varchar(64) NOT NULL,
  `cfg_name` varchar(60) NOT NULL,
  `cfg_type` varchar(10) DEFAULT NULL,
  `store_range` varchar(255) DEFAULT NULL,
  `store_dir` varchar(255) DEFAULT NULL,
  `value` varchar(2048) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `update_version` int(11) DEFAULT NULL,
  `is_terrace` tinyint(4) DEFAULT '0',
  `is_must` tinyint(4) DEFAULT '0',
  `des` varchar(512) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_code_unique` (`domain_id`,`key_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


##应用表
CREATE TABLE `app_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(16) NOT NULL,
  `appkey` varchar(64) NOT NULL,
  `appname` varchar(64) NOT NULL,
  `description` varchar(64) DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT NULL,
  `update_version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_app_domain_appid` (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##菜单表
CREATE TABLE `app_func_tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `app_id` varchar(16) NOT NULL,
  `sub_appid` varchar(32) DEFAULT NULL,
  `iframe` varchar(255) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `authcode` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `ordered` bigint(20) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `show_tree` tinyint(4) DEFAULT NULL,
  `update_version` bigint(20) DEFAULT '0',
  `is_sys_node` tinyint(4) DEFAULT '0',
  `icon_class` varchar(50) DEFAULT NULL,
  `role_type` varchar(64) DEFAULT NULL,
  `extend_field` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##角色表
CREATE TABLE `app_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(64) NOT NULL,
  `description` varchar(64) DEFAULT NULL,
  `parent_ids` varchar(512) DEFAULT NULL,
  `app_id` varchar(16) NOT NULL,
  `sub_appid` varchar(32) DEFAULT NULL,
  `organ_id` int(11) DEFAULT NULL,
  `organ_no` varchar(32) DEFAULT NULL,
  `buildin` tinyint(4) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT NULL,
  `update_version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

## 角色菜单关联表
CREATE TABLE `app_role_func_tree` (
  `node_id` int(11) NOT NULL,
  `node_type` tinyint(4) DEFAULT '0',
  `app_id` varchar(16) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##用户表
CREATE TABLE `app_subject_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `app_id` varchar(16) NOT NULL,
  `sub_appid` varchar(32) DEFAULT NULL,
  `role_type` int(11) DEFAULT '0',
  `organ_id` int(11) DEFAULT '0',
  `organ_no` varchar(32) DEFAULT NULL,
  `realname` varchar(64) DEFAULT NULL,
  `deptname` varchar(64) DEFAULT NULL,
  `deptid` int(11) DEFAULT '0',
  `deptno` varchar(32) DEFAULT NULL,
  `is_dept_admin` tinyint(4) DEFAULT '0',
  `phone` varchar(32) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT '0',
  `is_admin` tinyint(4) DEFAULT '0',
  `enabled` tinyint(4) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `attache` varchar(128) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT '0',
  `is_sys_admin` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_app_user_appid` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##组织机构表
CREATE TABLE `org_entity_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organ_no` varchar(32) DEFAULT NULL,
  `organ_name` varchar(128) NOT NULL,
  `organ_type` varchar(8) DEFAULT NULL,
  `com_address` varchar(128) DEFAULT NULL,
  `website` varchar(512) DEFAULT NULL,
  `cont_name` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `bus_name` varchar(64) DEFAULT NULL,
  `bus_idno` varchar(64) DEFAULT NULL,
  `bus_expir` varchar(64) DEFAULT NULL,
  `linence_photo` varchar(128) DEFAULT NULL,
  `indentity_photo` varchar(128) DEFAULT NULL,
  `protocol_photo` varchar(128) DEFAULT NULL,
  `card_photo` varchar(128) DEFAULT NULL,
  `other_doc` varchar(128) DEFAULT NULL,
  `province` varchar(6) DEFAULT NULL,
  `province_name` varchar(128) DEFAULT NULL,
  `city` varchar(6) DEFAULT NULL,
  `city_name` varchar(128) DEFAULT NULL,
  `address` varchar(512) NOT NULL,
  `industry` varchar(6) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `update_version` int(11) DEFAULT '0',
  `app_id` varchar(16) DEFAULT NULL,
  `addr_code` varchar(3) NOT NULL DEFAULT '755',
  `is_delete` tinyint(4) DEFAULT NULL,
  `p_cloud_key` varchar(32) DEFAULT NULL,
  `indentity_back_photo` varchar(128) DEFAULT NULL,
  `bank_account_photo` varchar(128) DEFAULT NULL,
  `pepole_indentity_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

## 数据库不重复自增表
CREATE TABLE `weixin_counter` (
  `id` varchar(128) NOT NULL,
  `hash_code` int(11) NOT NULL,
  `counter` int(11) DEFAULT '0',
  `update_at` int(11) DEFAULT '0',
  `create_at` int(11) DEFAULT '0',
  `loop_type` int(11) DEFAULT '0',
  `counter_type` varchar(32) DEFAULT NULL,
  `update_versoin` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `weixin_counter_hash` (`hash_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;