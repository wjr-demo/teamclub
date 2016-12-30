# Host: 172.17.20.180  (Version: 5.5.53-0ubuntu0.14.04.1)
# Date: 2016-12-30 16:42:03
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin_area_code"
#

CREATE TABLE `admin_area_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_code` char(3) NOT NULL,
  `area_name` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `parent_code` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2027 DEFAULT CHARSET=utf8;

#
# Data for table "admin_area_code"
#

INSERT INTO `admin_area_code` VALUES (900,'079','江西',1,'2014-10-20 18:19:52',5,0),(901,'791','南昌市',1,'2014-10-20 18:19:52',1,900),(902,'792','九江市',1,'2014-10-20 18:19:52',1,900),(903,'793','上饶市',1,'2014-10-20 18:19:52',1,900),(904,'794','临川市',1,'2014-10-20 18:19:52',1,900),(905,'795','宜春市',1,'2014-10-20 18:19:52',1,900),(906,'796','吉安市',1,'2014-10-20 18:19:52',1,900),(907,'797','赣州市',1,'2014-10-20 18:19:52',1,900),(908,'798','景德镇',1,'2014-10-20 18:19:52',1,900),(909,'799','萍乡市',1,'2014-10-20 18:19:52',1,900),(910,'077','广西',1,'2014-10-20 18:19:52',1,0),(911,'771','南宁市',1,'2014-10-20 18:19:52',1,910),(912,'772','柳州市',1,'2014-10-20 18:19:52',1,910),(913,'773','桂林市',1,'2014-10-20 18:19:52',1,910),(914,'774','梧州市',1,'2014-10-20 18:19:52',1,910),(915,'775','玉林市',1,'2014-10-20 18:19:52',1,910),(916,'776','百色市',1,'2014-10-20 18:19:52',1,910),(917,'777','钦州市',1,'2014-10-20 18:19:52',1,910),(918,'778','河池市',1,'2014-10-20 18:19:52',1,910),(919,'779','北海市',1,'2014-10-20 18:19:52',1,910),(920,'059','福建',1,'2014-10-20 18:19:52',1,0),(921,'591','福州市',1,'2014-10-20 18:19:52',3,920),(922,'592','厦门市',1,'2014-10-20 18:19:52',1,920),(923,'593','宁德市',1,'2014-10-20 18:19:52',1,920),(924,'594','莆田市',1,'2014-10-20 18:19:52',1,920),(925,'595','泉州市',1,'2014-10-20 18:19:52',1,920),(926,'596','漳州市',1,'2014-10-20 18:19:52',1,920),(927,'597','龙岩市',1,'2014-10-20 18:19:52',1,920),(928,'598','三明市',1,'2014-10-20 18:19:52',1,920),(929,'599','南平市',1,'2014-10-20 18:19:52',1,920),(930,'057','浙江',1,'2014-10-20 18:19:52',1,0),(931,'571','杭州市',1,'2014-10-20 18:19:52',1,930),(932,'572','湖州市',1,'2014-10-20 18:19:52',1,930),(933,'573','嘉兴市',1,'2014-10-20 18:19:52',1,930),(934,'574','宁波市',1,'2014-10-20 18:19:52',1,930),(935,'575','绍兴市',1,'2014-10-20 18:19:52',1,930),(936,'576','台州市',1,'2014-10-20 18:19:52',1,930),(937,'577','温州市',1,'2014-10-20 18:19:52',1,930),(938,'578','丽水市',1,'2014-10-20 18:19:52',1,930),(939,'579','金华市',1,'2014-10-20 18:19:52',1,930),(940,'055','安徽',1,'2014-10-20 18:19:52',1,0),(941,'551','合肥市',1,'2014-10-20 18:19:52',1,940),(942,'552','蚌埠市',1,'2014-10-20 18:19:52',1,940),(943,'553','芜湖市',1,'2014-10-20 18:19:52',1,940),(944,'554','淮南市',1,'2014-10-20 18:19:52',1,940),(945,'555','马鞍山',1,'2014-10-20 18:19:52',1,940),(946,'556','安庆市',1,'2014-10-20 18:19:52',1,940),(947,'557','宿州市',1,'2014-10-20 18:19:52',1,940),(948,'558','阜阳市',1,'2014-10-20 18:19:52',1,940),(949,'559','黄山市',1,'2014-10-20 18:19:52',1,940),(950,'035','山西',1,'2014-10-20 18:19:52',1,0),(951,'351','太原市',1,'2014-10-20 18:19:52',1,950),(952,'352','大同市',1,'2014-10-20 18:19:52',1,950),(953,'353','阳泉市',1,'2014-10-20 18:19:52',1,950),(954,'354','榆次市',1,'2014-10-20 18:19:52',1,950),(955,'355','长治市',1,'2014-10-20 18:19:52',1,950),(956,'356','晋城市',1,'2014-10-20 18:19:52',1,950),(957,'357','临汾市',1,'2014-10-20 18:19:52',1,950),(958,'358','离石市',1,'2014-10-20 18:19:52',1,950),(959,'359','运城市',1,'2014-10-20 18:19:52',1,950),(960,'037','河南',1,'2014-10-20 18:19:52',1,0),(961,'371','郑州市',1,'2014-10-20 18:19:52',1,960),(962,'372','安阳市',1,'2014-10-20 18:19:52',1,960),(963,'373','新乡市',1,'2014-10-20 18:19:52',1,960),(964,'374','许昌市',1,'2014-10-20 18:19:52',1,960),(965,'375','平顶山',1,'2014-10-20 18:19:52',1,960),(966,'376','信阳市',1,'2014-10-20 18:19:52',1,960),(967,'377','南阳市',1,'2014-10-20 18:19:52',1,960),(968,'378','开封市',1,'2014-10-20 18:19:52',1,960),(969,'379','洛阳市',1,'2014-10-20 18:19:52',1,960),(970,'021','上海市',1,'2014-10-20 18:19:52',3,1541),(971,'043','吉林',1,'2014-10-20 18:19:52',1,0),(972,'431','长春市',1,'2014-10-20 18:19:52',1,971),(973,'432','吉林市',1,'2014-10-20 18:19:52',1,971),(974,'433','延吉市',1,'2014-10-20 18:19:52',1,971),(975,'434','四平市',1,'2014-10-20 18:19:52',1,971),(976,'435','通化市',1,'2014-10-20 18:19:52',1,971),(977,'436','白城市',1,'2014-10-20 18:19:52',1,971),(978,'437','辽源市',1,'2014-10-20 18:19:52',1,971),(979,'438','松原市',1,'2014-10-20 18:19:52',1,971),(980,'439','浑江市',1,'2014-10-20 18:19:53',1,971),(981,'041','辽宁',1,'2014-10-20 18:19:53',1,0),(982,'411','大连市',1,'2014-10-20 18:19:53',1,981),(983,'412','鞍山市',1,'2014-10-20 18:19:53',1,981),(984,'413','抚顺市',1,'2014-10-20 18:19:53',1,981),(985,'414','本溪市',1,'2014-10-20 18:19:53',1,981),(986,'415','丹东市',1,'2014-10-20 18:19:53',1,981),(987,'416','锦州市',1,'2014-10-20 18:19:53',1,981),(988,'417','营口市',1,'2014-10-20 18:19:53',1,981),(989,'418','阜新市',1,'2014-10-20 18:19:53',1,981),(990,'419','辽阳市',1,'2014-10-20 18:19:53',1,981),(991,'081','四川',1,'2014-10-20 18:19:53',1,0),(993,'812','攀枝花',1,'2014-10-20 18:19:53',1,991),(994,'813','自贡市',1,'2014-10-20 18:19:53',1,991),(995,'814','永川市',1,'2014-10-20 18:19:53',1,991),(996,'816','绵阳市',1,'2014-10-20 18:19:53',1,991),(997,'817','南充市',1,'2014-10-20 18:19:53',1,991),(998,'818','达县市',1,'2014-10-20 18:19:53',1,991),(999,'819','万县市',1,'2014-10-20 18:19:53',1,991),(1000,'087','云南',1,'2014-10-20 18:19:53',1,0),(1001,'871','昆明市',1,'2014-10-20 18:19:53',1,1000),(1002,'872','大理市',1,'2014-10-20 18:19:53',1,1000),(1003,'873','个旧市',1,'2014-10-20 18:19:53',1,1000),(1004,'874','曲靖市',1,'2014-10-20 18:19:53',1,1000),(1005,'875','保山市',1,'2014-10-20 18:19:53',1,1000),(1006,'876','文山市',1,'2014-10-20 18:19:53',1,1000),(1007,'877','玉溪市',1,'2014-10-20 18:19:53',1,1000),(1008,'878','楚雄市',1,'2014-10-20 18:19:53',1,1000),(1009,'879','思茅市',1,'2014-10-20 18:19:53',1,1000),(1010,'085','贵州',1,'2014-10-20 18:19:53',1,0),(1011,'851','贵阳市',1,'2014-10-20 18:19:53',1,1010),(1012,'852','遵义市',1,'2014-10-20 18:19:53',1,1010),(1013,'853','安顺市',1,'2014-10-20 18:19:53',1,1010),(1014,'854','都均市',1,'2014-10-20 18:19:53',1,1010),(1015,'855','凯里市',1,'2014-10-20 18:19:53',1,1010),(1016,'856','铜仁市',1,'2014-10-20 18:19:53',1,1010),(1017,'857','毕节市',1,'2014-10-20 18:19:53',1,1010),(1018,'858','六盘水',1,'2014-10-20 18:19:53',1,1010),(1019,'859','兴义市',1,'2014-10-20 18:19:53',1,1010),(1020,'097','青海',1,'2014-10-20 18:19:53',1,0),(1021,'971','西宁市',1,'2014-10-20 18:19:53',1,1020),(1022,'972','海东市',1,'2014-10-20 18:19:53',1,1020),(1023,'973','同仁市',1,'2014-10-20 18:19:53',1,1020),(1024,'974','共和市',1,'2014-10-20 18:19:53',1,1020),(1025,'975','玛沁市',1,'2014-10-20 18:19:53',1,1020),(1026,'976','玉树市',1,'2014-10-20 18:19:53',1,1020),(1027,'977','德令哈',1,'2014-10-20 18:19:53',1,1020),(1028,'095','宁夏',1,'2014-10-20 18:19:53',1,0),(1029,'951','银川市',1,'2014-10-20 18:19:53',1,1028),(1030,'952','石嘴山',1,'2014-10-20 18:19:53',1,1028),(1031,'953','吴忠市',1,'2014-10-20 18:19:53',1,1028),(1032,'954','固原市',1,'2014-10-20 18:19:53',1,1028),(1033,'045','黑龙江',1,'2014-10-20 18:19:53',1,0),(1034,'451','哈尔滨',1,'2014-10-20 18:19:53',1,1033),(1035,'452','齐齐哈尔',1,'2014-10-20 18:19:53',1,1033),(1036,'453','牡丹江',1,'2014-10-20 18:19:53',1,1033),(1037,'454','佳木斯',1,'2014-10-20 18:19:53',1,1033),(1038,'455','绥化市',1,'2014-10-20 18:19:53',1,1033),(1039,'456','黑河市',1,'2014-10-20 18:19:53',1,1033),(1040,'457','加格达奇',1,'2014-10-20 18:19:53',1,1033),(1041,'458','伊春市',1,'2014-10-20 18:19:53',1,1033),(1042,'459','大庆市',1,'2014-10-20 18:19:53',1,1033),(1043,'093','甘肃',1,'2014-10-20 18:19:53',1,0),(1044,'931','兰州市',1,'2014-10-20 18:19:53',1,1043),(1045,'932','定西市',1,'2014-10-20 18:19:53',1,1043),(1046,'933','平凉市',1,'2014-10-20 18:19:53',1,1043),(1047,'934','西峰市',1,'2014-10-20 18:19:53',1,1043),(1048,'935','武威市',1,'2014-10-20 18:19:53',1,1043),(1049,'936','张掖市',1,'2014-10-20 18:19:53',1,1043),(1050,'937','酒泉市',1,'2014-10-20 18:19:53',1,1043),(1051,'938','天水市',1,'2014-10-20 18:19:53',1,1043),(1052,'047','内蒙古',1,'2014-10-20 18:19:53',1,0),(1053,'471','呼和浩特',1,'2014-10-20 18:19:53',1,1052),(1054,'472','包头市',1,'2014-10-20 18:19:53',1,1052),(1055,'473','乌海市',1,'2014-10-20 18:19:53',1,1052),(1056,'474','集宁市',1,'2014-10-20 18:19:53',1,1052),(1057,'475','通辽市',1,'2014-10-20 18:19:53',1,1052),(1058,'476','赤峰市',1,'2014-10-20 18:19:53',1,1052),(1059,'477','东胜市',1,'2014-10-20 18:19:53',1,1052),(1060,'478','临河市',1,'2014-10-20 18:19:53',1,1052),(1061,'479','锡林浩特',1,'2014-10-20 18:19:53',1,1052),(1062,'091','陕西',1,'2014-10-20 18:19:53',1,0),(1063,'911','延安市',1,'2014-10-20 18:19:53',1,1062),(1064,'912','榆林市',1,'2014-10-20 18:19:53',1,1062),(1065,'913','渭南市',1,'2014-10-20 18:19:53',1,1062),(1066,'914','商洛市',1,'2014-10-20 18:19:53',1,1062),(1067,'915','安康市',1,'2014-10-20 18:19:53',1,1062),(1068,'916','汉中市',1,'2014-10-20 18:19:53',1,1062),(1069,'917','宝鸡市',1,'2014-10-20 18:19:53',1,1062),(1070,'919','铜川市',1,'2014-10-20 18:19:53',2,1062),(1071,'022','天津市',1,'2014-10-20 18:19:53',5,1542),(1072,'004','重庆',1,'2014-10-20 18:19:53',2,0),(1073,'010','北京市',1,'2014-10-20 18:19:53',3,1540),(1074,'031','河北',1,'2014-10-20 18:19:53',1,0),(1075,'311','石家庄',1,'2014-10-20 18:19:53',1,1074),(1076,'312','保定市',1,'2014-10-20 18:19:53',1,1074),(1077,'313','张家口',1,'2014-10-20 18:19:53',1,1074),(1078,'314','承德市',1,'2014-10-20 18:19:53',1,1074),(1079,'315','唐山市',1,'2014-10-20 18:19:53',1,1074),(1080,'316','廊坊市',1,'2014-10-20 18:19:53',1,1074),(1081,'317','沧州市',1,'2014-10-20 18:19:53',1,1074),(1082,'318','衡水市',1,'2014-10-20 18:19:53',1,1074),(1083,'319','邢台市',1,'2014-10-20 18:19:53',1,1074),(1084,'051','江苏',1,'2014-10-20 18:19:53',1,0),(1085,'511','镇江市',1,'2014-10-20 18:19:54',1,1084),(1086,'512','苏州市',1,'2014-10-20 18:19:54',1,1084),(1087,'513','南通市',1,'2014-10-20 18:19:54',1,1084),(1088,'514','扬州市',1,'2014-10-20 18:19:54',1,1084),(1089,'515','盐城市',1,'2014-10-20 18:19:54',1,1084),(1090,'516','徐州市',1,'2014-10-20 18:19:54',1,1084),(1091,'517','淮阴市',1,'2014-10-20 18:19:54',1,1084),(1092,'518','连云港',1,'2014-10-20 18:19:54',1,1084),(1093,'519','常州市',1,'2014-10-20 18:19:54',1,1084),(1094,'071','湖北',1,'2014-10-20 18:19:54',1,0),(1095,'711','鄂州市',1,'2014-10-20 18:19:54',1,1094),(1096,'712','孝感市',1,'2014-10-20 18:19:54',1,1094),(1097,'713','黄州市',1,'2014-10-20 18:19:54',1,1094),(1098,'714','黄石市',1,'2014-10-20 18:19:54',1,1094),(1099,'715','咸宁市',1,'2014-10-20 18:19:54',1,1094),(1100,'716','荆沙市',1,'2014-10-20 18:19:54',1,1094),(1101,'717','宜昌市',1,'2014-10-20 18:19:54',1,1094),(1102,'718','恩施市',1,'2014-10-20 18:19:54',1,1094),(1103,'719','十堰市 ',1,'2014-10-20 18:19:54',1,1094),(1104,'053','山东',1,'2014-10-20 18:19:54',1,0),(1105,'531','济南市',1,'2014-10-20 18:19:54',1,1104),(1106,'532','青岛市',1,'2014-10-20 18:19:54',1,1104),(1107,'533','淄博市',1,'2014-10-20 18:19:54',1,1104),(1108,'534','德州市',1,'2014-10-20 18:19:54',1,1104),(1109,'535','烟台市',1,'2014-10-20 18:19:54',1,1104),(1110,'536','淮坊市',1,'2014-10-20 18:19:54',1,1104),(1111,'537','济宁市',1,'2014-10-20 18:19:54',1,1104),(1112,'538','泰安市',1,'2014-10-20 18:19:54',1,1104),(1113,'539','临沂市',1,'2014-10-20 18:19:54',1,1104),(1114,'073','湖南',1,'2014-10-20 18:19:54',1,0),(1115,'731','长沙市',1,'2014-10-20 18:19:54',1,1114),(1116,'732','湘潭市',1,'2014-10-20 18:19:54',1,1114),(1117,'733','株州市',1,'2014-10-20 18:19:54',1,1114),(1118,'734','衡阳市',1,'2014-10-20 18:19:54',1,1114),(1119,'735','郴州市',1,'2014-10-20 18:19:54',1,1114),(1120,'736','常德市',1,'2014-10-20 18:19:54',1,1114),(1121,'737','益阳市',1,'2014-10-20 18:19:54',1,1114),(1122,'738','娄底市',1,'2014-10-20 18:19:54',1,1114),(1123,'739','邵阳市',1,'2014-10-20 18:19:54',1,1114),(1124,'075','广东',1,'2014-10-20 18:19:54',1,0),(1125,'751','韶关市',1,'2014-10-20 18:19:54',1,1124),(1126,'752','惠州市',1,'2014-10-20 18:19:54',1,1124),(1127,'753','梅州市',1,'2014-10-20 18:19:54',1,1124),(1128,'754','汕头市',1,'2014-10-20 18:19:54',1,1124),(1129,'755','深圳市',1,'2014-10-20 18:19:54',1,1124),(1130,'756','珠海市',1,'2014-10-20 18:19:54',1,1124),(1131,'757','佛山市',1,'2014-10-20 18:19:54',1,1124),(1132,'758','肇庆市',1,'2014-10-20 18:19:54',1,1124),(1133,'759','湛江市',1,'2014-10-20 18:19:54',1,1124),(1134,'089','海南',1,'2014-10-20 18:19:54',1,0),(1135,'898','海口市',1,'2014-10-20 18:19:54',1,1134),(1136,'899','三亚市',1,'2014-10-20 18:19:54',1,1134),(1200,'570','衢州市',1,'2014-10-22 13:50:21',1,930),(1201,'240','沈阳市',1,'2014-10-22 14:02:08',2,981),(1202,'270','武汉市',1,'2014-10-22 14:02:08',2,1094),(1203,'250','南京市',1,'2014-10-22 14:02:08',2,1084),(1204,'470','海拉尔',1,'2014-10-22 14:02:08',1,1052),(1205,'790','新余市',1,'2014-10-22 14:02:08',1,900),(1206,'350','忻州市',1,'2014-10-22 14:02:08',1,950),(1207,'930','临夏市',1,'2014-10-22 14:02:08',1,1043),(1208,'530','菏泽市',1,'2014-10-22 14:02:08',1,1104),(1209,'450','阿城市',1,'2014-10-22 14:02:08',1,1033),(1211,'200','广州市',1,'2014-10-22 14:02:08',2,1124),(1212,'280','成都市',1,'2014-10-22 14:02:08',2,991),(1213,'730','岳阳市',1,'2014-10-22 14:02:08',1,1114),(1214,'370','商丘市',1,'2014-10-22 14:02:08',1,960),(1215,'870','昭通市',1,'2014-10-22 14:02:08',1,1000),(1216,'550','滁州市',1,'2014-10-22 14:02:08',1,940),(1260,'290','西安市',1,'2014-10-23 11:08:31',2,1062),(1280,'769','东莞市',1,'2014-10-27 16:43:48',1,1124),(1286,'310','邯郸市',1,'2014-10-27 16:49:18',1,1074),(1287,'335','秦皇岛',1,'2014-10-27 16:49:40',1,1074),(1288,'580','舟山市',1,'2014-10-27 16:53:56',1,930),(1289,'410','铁岭市',1,'2014-10-27 16:55:43',1,981),(1290,'421','朝阳市',1,'2014-10-27 16:56:11',1,981),(1291,'427','盘锦市',1,'2014-10-27 16:56:29',1,981),(1292,'429','葫芦岛',1,'2014-10-27 16:56:46',1,981),(1293,'710','襄城市',1,'2014-10-27 16:57:37',1,1094),(1294,'722','随枣市',1,'2014-10-27 16:58:11',1,1094),(1295,'724','荆门市',1,'2014-10-27 16:58:27',1,1094),(1296,'728','江汉市',1,'2014-10-27 16:58:43',1,1094),(1297,'510','无锡市',1,'2014-10-27 16:59:17',1,1084),(1298,'523','泰州市',1,'2014-10-27 16:59:37',1,1084),(1299,'482','乌兰浩特',1,'2014-10-27 17:00:26',1,1052),(1300,'483','阿拉善左旗',1,'2014-10-27 17:00:42',1,1052),(1301,'701','鹰潭市',1,'2014-10-27 17:01:15',1,900),(1302,'941','甘南州',1,'2014-10-27 17:02:07',1,1043),(1303,'943','白银市',1,'2014-10-27 17:02:25',1,1043),(1304,'760','中山市',1,'2014-10-27 17:03:56',1,1124),(1305,'762','河源市',1,'2014-10-27 17:04:11',1,1124),(1306,'763','清远市',1,'2014-10-27 17:04:26',1,1124),(1307,'765','顺德市',1,'2014-10-27 17:04:46',1,1124),(1308,'766','云浮市',1,'2014-10-27 17:05:00',1,1124),(1309,'768','潮州市',1,'2014-10-27 17:05:17',1,1124),(1310,'660','汕尾市',1,'2014-10-27 17:05:35',1,1124),(1311,'661','潮阳市',1,'2014-10-27 17:06:00',1,1124),(1312,'662','阳江市',1,'2014-10-27 17:06:20',1,1124),(1313,'663','揭西市',1,'2014-10-27 17:06:37',1,1124),(1314,'810','涪陵市',1,'2014-10-27 17:07:26',1,991),(1315,'811','重庆市',1,'2014-10-27 17:07:56',2,1072),(1316,'825','遂宁市',1,'2014-10-27 17:08:31',1,991),(1317,'826','广安市',1,'2014-10-27 17:08:45',1,991),(1318,'827','巴中市',1,'2014-10-27 17:08:59',1,991),(1319,'830','泸州市',1,'2014-10-27 17:09:15',1,991),(1320,'831','宜宾市',1,'2014-10-27 17:09:35',1,991),(1321,'832','内江市',1,'2014-10-27 17:09:47',1,991),(1322,'833','乐山市',1,'2014-10-27 17:10:00',1,991),(1323,'834','西昌市',1,'2014-10-27 17:10:11',1,991),(1324,'835','雅安市',1,'2014-10-27 17:10:24',1,991),(1325,'836','康定市',1,'2014-10-27 17:10:37',1,991),(1326,'837','马尔康',1,'2014-10-27 17:10:49',1,991),(1327,'838','德阳市',1,'2014-10-27 17:11:01',1,991),(1328,'839','广元市',1,'2014-10-27 17:11:15',1,991),(1329,'743','吉首市',1,'2014-10-27 17:12:01',1,1114),(1330,'744','张家界',1,'2014-10-27 17:12:14',1,1114),(1331,'745','怀化市',1,'2014-10-27 17:12:28',1,1114),(1332,'746','永州冷',1,'2014-10-27 17:12:42',1,1114),(1333,'391','焦作市',1,'2014-10-27 17:13:14',1,960),(1334,'392','鹤壁市',1,'2014-10-27 17:13:26',1,960),(1335,'393','濮阳市',1,'2014-10-27 17:13:42',1,960),(1336,'394','周口市',1,'2014-10-27 17:13:53',1,960),(1337,'395','漯河市',1,'2014-10-27 17:14:08',1,960),(1338,'396','驻马店',1,'2014-10-27 17:14:20',1,960),(1339,'398','三门峡',1,'2014-10-27 17:14:35',1,960),(1340,'691','景洪市',1,'2014-10-27 17:15:08',1,1000),(1341,'692','潞西市',1,'2014-10-27 17:15:22',1,1000),(1342,'881','东川市',1,'2014-10-27 17:15:34',1,1000),(1343,'883','临沧市',1,'2014-10-27 17:15:48',1,1000),(1344,'886','六库市',1,'2014-10-27 17:16:01',1,1000),(1345,'887','中甸市',1,'2014-10-27 17:16:14',1,1000),(1346,'888','丽江市',1,'2014-10-27 17:16:25',1,1000),(1347,'561','淮北市',1,'2014-10-27 17:16:54',1,940),(1348,'562','铜陵市',1,'2014-10-27 17:17:06',1,940),(1349,'563','宣城市',1,'2014-10-27 17:17:19',1,940),(1350,'564','六安市',1,'2014-10-27 17:18:58',1,940),(1351,'565','巢湖市',1,'2014-10-27 17:19:11',1,940),(1352,'566','贵池市',1,'2014-10-27 17:19:22',1,940),(1353,'440','珲春市',1,'2014-10-27 17:20:05',1,971),(1354,'770','防城港',1,'2014-10-27 17:20:25',1,910),(1355,'910','咸阳市',1,'2014-10-27 17:23:50',1,1062),(1356,'890','儋州市',1,'2014-10-27 17:25:03',1,1134),(1440,'098','西藏',1,'2014-10-27 17:39:29',1,0),(1441,'891','拉萨市',1,'2014-10-27 17:39:53',1,1440),(1442,'892','日喀则',1,'2014-10-27 17:40:05',1,1440),(1443,'893','山南市',1,'2014-10-27 17:40:19',1,1440),(1540,'001','北京',1,'2014-11-17 17:05:27',1,0),(1541,'002','上海',1,'2014-11-17 17:05:35',1,0),(1542,'003','天津',1,'2014-11-17 17:05:46',1,0),(1941,'13','zhzns',0,'2015-06-12 14:44:08',2,123),(1980,'111','阿斯顿',0,'2015-06-15 17:33:25',1,1104),(2007,'800','天津太',0,'2015-08-22 16:10:43',1,1542),(2021,'890','三沙市',1,'2014-10-27 17:25:03',1,1134),(2022,'898','琼海市',1,'2014-10-27 17:25:03',1,1134),(2023,'898','文昌市',1,'2014-10-27 17:25:03',1,1134),(2024,'890','万宁市',1,'2014-10-27 17:25:03',1,1134),(2025,'890','东方市',1,'2014-10-27 17:25:03',1,1134),(2026,'890','五指山市',1,'2014-10-27 17:25:03',1,1134);

#
# Structure for table "admin_global_config"
#

CREATE TABLE `admin_global_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `app_id` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_code_unique` (`domain_id`,`key_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "admin_global_config"
#

INSERT INTO `admin_global_config` VALUES (2,0,NULL,NULL,NULL,NULL,'ORDER_PAY_STYLE','付款方式','select',NULL,NULL,'[{\"id\":1,\"name\":\"款到发货\"},{\"id\":2,\"name\":\"预付总货款的30%，发货前付清余款\"},{\"id\":3,\"name\":\"预付总货款的30%，货到付款\"},{\"id\":4,\"name\":\"货到后一周内付款\"},{\"id\":5,\"name\":\"提货前付款（控货）\"},{\"id\":6,\"name\":\"发货前付清货款\"},{\"id\":7,\"name\":\"次月5号前付清当月货款\"},{\"id\":8,\"name\":\"次月10号前付清当月货款\"},{\"id\":9,\"name\":\"次月15号前付清当月货款\"},{\"id\":10,\"name\":\"次月20号前付清当月货款\"},{\"id\":11,\"name\":\"次月10号前对账，20号前付清当月货款\"},{\"id\":12,\"name\":\"次月30号前付清当月货款\"},{\"id\":13,\"name\":\"货到付款\"}]',NULL,5,0,0,'付款的方式',NULL,'dongkaiyue'),(3,0,NULL,NULL,NULL,NULL,'CUSTOMER_LEVEL','客户等级','select',NULL,NULL,'[{\"id\":1,\"name\":\"A+\"},{\"id\":2,\"name\":\"A\"},{\"id\":3,\"name\":\"B\"},{\"id\":4,\"name\":\"C\"},{\"id\":5,\"name\":\"D\"},{\"id\":6,\"name\":\"E\"}]',NULL,3,0,0,'客户的等级',NULL,'dongkaiyue'),(4,0,NULL,NULL,NULL,NULL,'FILE_TRANSFER_STYLE','文件传输方式','select',NULL,NULL,'[{\"id\":1,\"name\":\"传真方式\"},{\"id\":2,\"name\":\"邮件方式\"},{\"id\":3,\"name\":\"Q Q 方式\"},{\"id\":4,\"name\":\"电话方式\"},{\"id\":5,\"name\":\"扫描方式\"}]',NULL,2,0,0,'文件传输的方式',NULL,'dongkaiyue'),(5,0,NULL,NULL,NULL,NULL,'STUDY_LEVEL','学历等级','select',NULL,NULL,'[{\"id\":1,\"name\":\"小学\"},{\"id\":2,\"name\":\"初级中学\"},{\"id\":3,\"name\":\"高级中学\"},{\"id\":4,\"name\":\"大学专科\"},{\"id\":5,\"name\":\"大学本科\"},{\"id\":6,\"name\":\"硕士研究生\"},{\"id\":7,\"name\":\"博士研究生\"}]',NULL,1,0,0,'学历',NULL,'dongkaiyue'),(6,0,NULL,NULL,NULL,NULL,'USER_PAY_STYLE','员工支付方式','select',NULL,NULL,'[{\"id\":1,\"name\":\"计时\"},{\"id\":2,\"name\":\"记件\"},{\"id\":3,\"name\":\"计时+记件\"}]',NULL,1,0,0,'支付员工工资的方式',NULL,'dongkaiyue');

#
# Structure for table "app_department"
#

CREATE TABLE `app_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `depart_desc` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_version` int(11) DEFAULT '0',
  `app_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `attach_code` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "app_department"
#

INSERT INTO `app_department` VALUES (1,'商务部',' ',4,'dongkaiyue','BUSINESS'),(2,'行政部',' ',7,'dongkaiyue','ADMINISTRATOR'),(3,'财务部',' ',8,'dongkaiyue','FINANCE'),(5,'制造部',' ',3,'dongkaiyue','0'),(6,'采购部',' ',2,'dongkaiyue','0'),(7,'技术部',' ',1,'dongkaiyue','0'),(8,'仓库部',' ',1,'dongkaiyue','0'),(9,'总经办',' ',1,'dongkaiyue','0');

#
# Structure for table "app_domain"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Data for table "app_domain"
#

INSERT INTO `app_domain` VALUES (9,'backend','backend','后台应用',NULL,NULL,1,NULL,NULL,'2016-12-19 15:19:36','wjr',13),(11,'dongkaiyue','dongkaiyue','东凯越管理平台',NULL,NULL,1,NULL,NULL,'2016-12-27 09:53:55','15121001@15121001',2);

#
# Structure for table "app_func_tree"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "app_func_tree"
#

INSERT INTO `app_func_tree` VALUES (2,'应用管理','backend',NULL,NULL,NULL,'js/business/views/basic/appmanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(4,'系统管理','backend',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(5,'角色管理','backend',NULL,NULL,NULL,'js/business/views/basic/rolemanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(6,'员工管理','backend',NULL,NULL,NULL,'js/business/views/basic/operatormanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(13,'字典管理','backend',NULL,NULL,NULL,'js/business/views/basic/adminglobalconfig',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(14,'系统管理','dongkaiyue',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(15,'角色管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/rolemanager',NULL,14,1,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(16,'部门管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/appdepartment',NULL,14,2,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(17,'员工管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/operatormanager',NULL,14,3,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(18,'字典管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/adminglobalconfig',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(19,'地区管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/adminareacode',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(20,'应用管理','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/appmanager',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(22,'1','2',NULL,NULL,NULL,' ',NULL,14,10,NULL,NULL,NULL,0,0,NULL,NULL,NULL);

#
# Structure for table "app_role"
#

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
  `attach_code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

#
# Data for table "app_role"
#

INSERT INTO `app_role` VALUES (1,'商务经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:02:08','15121001@15121001',NULL,NULL,14,NULL),(12,'商务下单',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:04:54','15121001@15121001',NULL,NULL,5,NULL),(13,'商务跟单',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:04:48','15121001@15121001',NULL,NULL,1,NULL),(14,'技术部经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:05:17','15121001@15121001',NULL,NULL,1,NULL),(17,'采购经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:06:37','15121001@15121001',NULL,NULL,1,NULL),(18,'采购文员',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:06:55','15121001@15121001',NULL,NULL,1,NULL),(19,'前台文员',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:07:16','15121001@15121001',NULL,NULL,1,NULL),(20,'财务经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:07:54','15121001@15121001',NULL,NULL,1,NULL),(21,'会计',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:08:01','15121001@15121001',NULL,NULL,1,NULL),(22,'仓管',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:08:22','15121001@15121001',NULL,NULL,1,NULL),(34,'总经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:33','15121001@15121001',NULL,NULL,2,NULL),(35,'副总经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:42','15121001@15121001',NULL,NULL,1,NULL),(36,'经理',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:49','15121001@15121001',NULL,NULL,1,NULL),(37,'主管',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:57','15121001@15121001',NULL,NULL,1,NULL),(38,'文员',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:17:28','15121001@15121001',NULL,NULL,1,NULL),(39,'数冲领班',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:17:52','15121001@15121001',NULL,NULL,1,NULL),(40,'数冲师傅',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:07','15121001@15121001',NULL,NULL,1,NULL),(41,'数冲学徒',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:23','15121001@15121001',NULL,NULL,1,NULL),(42,'折弯领班',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:31','15121001@15121001',NULL,NULL,1,NULL),(43,'折弯师傅',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:46','15121001@15121001',NULL,NULL,1,NULL),(44,'折弯学徒',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:59','15121001@15121001',NULL,NULL,1,NULL);

#
# Structure for table "app_role_func_tree"
#

CREATE TABLE `app_role_func_tree` (
  `node_id` int(11) NOT NULL,
  `node_type` tinyint(4) DEFAULT '0',
  `app_id` varchar(16) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "app_role_func_tree"
#

INSERT INTO `app_role_func_tree` VALUES (4,0,'backend',12),(2,0,'backend',12),(16,0,'dongkaiyue',1);

#
# Structure for table "app_subject_user"
#

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
  `telephone` varchar(12) DEFAULT NULL,
  `entry_time` datetime DEFAULT NULL,
  `positive_time` datetime DEFAULT NULL,
  `identify_no` varchar(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `marriage_status` tinyint(4) DEFAULT '0',
  `education_level` tinyint(4) DEFAULT '0',
  `strong_point` varchar(128) DEFAULT NULL,
  `native_place_prov` int(11) DEFAULT '0',
  `native_place_city` int(11) DEFAULT '0',
  `native_place_detail` varchar(256) DEFAULT NULL,
  `family_name` varchar(32) DEFAULT NULL,
  `family_relation` varchar(32) DEFAULT NULL,
  `family_phone` varchar(32) DEFAULT NULL,
  `expected_leave` datetime DEFAULT NULL,
  `com_qq_num` varchar(16) DEFAULT NULL,
  `com_qq_passwd` varchar(32) DEFAULT NULL,
  `com_qq_permit` varchar(128) DEFAULT NULL,
  `net_ip` varchar(32) DEFAULT NULL,
  `net_speed` varchar(32) DEFAULT NULL,
  `net_permit` varchar(128) DEFAULT NULL,
  `computer_no` varchar(32) DEFAULT NULL,
  `computer_passwd` varchar(32) DEFAULT NULL,
  `computer_config` varchar(256) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_app_user_appid` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "app_subject_user"
#

INSERT INTO `app_subject_user` VALUES (1,'15121000@15121000','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','backend',NULL,1,NULL,'15121000','哈哈',NULL,0,NULL,0,NULL,0,0,1,NULL,NULL,NULL,NULL,'0',1,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'admin@15121000','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','backend',NULL,12,NULL,NULL,'土豆',NULL,0,NULL,1,NULL,0,0,1,NULL,NULL,NULL,NULL,'0',0,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'15121001@15121001','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','dongkaiyue',NULL,12,0,NULL,'玩友',NULL,1,NULL,0,'0755-33132342',0,0,1,NULL,NULL,NULL,NULL,'0',1,' 15919492724','2016-12-14 08:00:00','2016-12-07 08:00:00','13112719911011003X','2016-12-01 08:00:00',1,3,' 在盱瞧不起睦',1541,970,' 天真平步青云在',' 三眄',' 旧话',' 5165156112','2016-12-15 08:00:00',' 325435463',' 3542356436',' 3654365',' 3654365',' 3564365',' 4534563',' 365436',' .564',' 3654646',' 4.3465354'),(6,' ','5bedfa012cc59078d8307629f9ac0c6e0e66894dbdeeffac45f3b8d8ce4fae9e2142ff548c8d3c3f64995605585e833c','dongkaiyue',NULL,1,0,NULL,'杨建秋',NULL,9,NULL,1,NULL,0,0,1,NULL,NULL,NULL,NULL,'0',1,NULL,'2009-08-15 08:00:00',NULL,'6541564566565111565','1985-07-17 08:00:00',2,3,' ',991,1320,' ',' ',' ',' ',NULL,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ');

#
# Structure for table "org_entity_info"
#

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "org_entity_info"
#

INSERT INTO `org_entity_info` VALUES (1,'15121001','DKY',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'地是地方',NULL,NULL,0,'backend','755',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "pay_operation_logger"
#

CREATE TABLE `pay_operation_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `real_name` varchar(64) DEFAULT NULL,
  `class_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `ipaddress` varchar(64) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `operation_type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `input` varchar(4000) DEFAULT NULL,
  `output` varchar(4000) DEFAULT NULL,
  `add_time` datetime NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "pay_operation_logger"
#


#
# Structure for table "user_depart_change"
#

CREATE TABLE `user_depart_change` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `change_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT '0',
  `depart_id` int(11) DEFAULT '0',
  `role_id` int(11) DEFAULT '0',
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `wages` int(11) DEFAULT '0',
  `calcu_style` int(11) DEFAULT '0',
  `remark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user_depart_change"
#

INSERT INTO `user_depart_change` VALUES (4,'2016-12-30 15:30:04',4,1,1,'0755-3133245',10001,2,'很好'),(5,'2016-12-30 15:31:14',4,2,1,'0731-12325453',100000,2,' '),(6,'2016-12-30 15:43:21',4,3,12,'1231-234123',200000,2,'10000'),(10,'2016-12-30 15:59:03',4,8,13,'0766-2255555',1500000,1,' '),(11,'2016-12-30 16:20:38',4,1,1,'0755-234234123',200000,2,' '),(12,'2016-12-30 16:22:49',4,1,12,'0834-34233441',200000,2,' ');

#
# Structure for table "weixin_counter"
#

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

#
# Data for table "weixin_counter"
#

INSERT INTO `weixin_counter` VALUES ('hello',99162322,92,1482143642,1482138934,0,NULL,92),('organNum',1316363189,1,1482803630,1482803630,0,NULL,1);
