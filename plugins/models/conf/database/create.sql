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

##用户表
CREATE TABLE `app_subject_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `app_id` varchar(16) NOT NULL,
  `sub_appid` varchar(32) DEFAULT NULL,
  `role_type` tinyint(4) DEFAULT NULL,
  `organ_id` int(11) DEFAULT NULL,
  `organ_no` varchar(32) DEFAULT NULL,
  `realname` varchar(64) DEFAULT NULL,
  `deptname` varchar(64) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `deptno` varchar(32) DEFAULT NULL,
  `is_dept_admin` tinyint(4) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(64) DEFAULT NULL,
  `attache` varchar(128) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(64) DEFAULT '0',
  `is_sys_admin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_app_user_appid` (`app_id`)
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8

##数据库不重复自增表
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

## 角色菜单表
CREATE TABLE `app_role_func_tree` (
  `node_id` int(11) DEFAULT '0',
  `node_type` int(11) DEFAULT '0',
  `app_id` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;