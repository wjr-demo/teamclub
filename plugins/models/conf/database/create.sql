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