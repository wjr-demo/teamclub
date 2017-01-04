# Host: localhost  (Version: 5.6.24)
# Date: 2017-01-04 23:49:36
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "admin_area_code"
#

DROP TABLE IF EXISTS `admin_area_code`;
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

INSERT INTO `admin_area_code` VALUES (900,'079','����',1,'2014-10-20 18:19:52',5,0),(901,'791','�ϲ���',1,'2014-10-20 18:19:52',1,900),(902,'792','�Ž���',1,'2014-10-20 18:19:52',1,900),(903,'793','������',1,'2014-10-20 18:19:52',1,900),(904,'794','�ٴ���',1,'2014-10-20 18:19:52',1,900),(905,'795','�˴���',1,'2014-10-20 18:19:52',1,900),(906,'796','������',1,'2014-10-20 18:19:52',1,900),(907,'797','������',1,'2014-10-20 18:19:52',1,900),(908,'798','������',1,'2014-10-20 18:19:52',1,900),(909,'799','Ƽ����',1,'2014-10-20 18:19:52',1,900),(910,'077','����',1,'2014-10-20 18:19:52',1,0),(911,'771','������',1,'2014-10-20 18:19:52',1,910),(912,'772','������',1,'2014-10-20 18:19:52',1,910),(913,'773','������',1,'2014-10-20 18:19:52',1,910),(914,'774','������',1,'2014-10-20 18:19:52',1,910),(915,'775','������',1,'2014-10-20 18:19:52',1,910),(916,'776','��ɫ��',1,'2014-10-20 18:19:52',1,910),(917,'777','������',1,'2014-10-20 18:19:52',1,910),(918,'778','�ӳ���',1,'2014-10-20 18:19:52',1,910),(919,'779','������',1,'2014-10-20 18:19:52',1,910),(920,'059','����',1,'2014-10-20 18:19:52',1,0),(921,'591','������',1,'2014-10-20 18:19:52',3,920),(922,'592','������',1,'2014-10-20 18:19:52',1,920),(923,'593','������',1,'2014-10-20 18:19:52',1,920),(924,'594','������',1,'2014-10-20 18:19:52',1,920),(925,'595','Ȫ����',1,'2014-10-20 18:19:52',1,920),(926,'596','������',1,'2014-10-20 18:19:52',1,920),(927,'597','������',1,'2014-10-20 18:19:52',1,920),(928,'598','������',1,'2014-10-20 18:19:52',1,920),(929,'599','��ƽ��',1,'2014-10-20 18:19:52',1,920),(930,'057','�㽭',1,'2014-10-20 18:19:52',1,0),(931,'571','������',1,'2014-10-20 18:19:52',1,930),(932,'572','������',1,'2014-10-20 18:19:52',1,930),(933,'573','������',1,'2014-10-20 18:19:52',1,930),(934,'574','������',1,'2014-10-20 18:19:52',1,930),(935,'575','������',1,'2014-10-20 18:19:52',1,930),(936,'576','̨����',1,'2014-10-20 18:19:52',1,930),(937,'577','������',1,'2014-10-20 18:19:52',1,930),(938,'578','��ˮ��',1,'2014-10-20 18:19:52',1,930),(939,'579','����',1,'2014-10-20 18:19:52',1,930),(940,'055','����',1,'2014-10-20 18:19:52',1,0),(941,'551','�Ϸ���',1,'2014-10-20 18:19:52',1,940),(942,'552','������',1,'2014-10-20 18:19:52',1,940),(943,'553','�ߺ���',1,'2014-10-20 18:19:52',1,940),(944,'554','������',1,'2014-10-20 18:19:52',1,940),(945,'555','��ɽ',1,'2014-10-20 18:19:52',1,940),(946,'556','������',1,'2014-10-20 18:19:52',1,940),(947,'557','������',1,'2014-10-20 18:19:52',1,940),(948,'558','������',1,'2014-10-20 18:19:52',1,940),(949,'559','��ɽ��',1,'2014-10-20 18:19:52',1,940),(950,'035','ɽ��',1,'2014-10-20 18:19:52',1,0),(951,'351','̫ԭ��',1,'2014-10-20 18:19:52',1,950),(952,'352','��ͬ��',1,'2014-10-20 18:19:52',1,950),(953,'353','��Ȫ��',1,'2014-10-20 18:19:52',1,950),(954,'354','�ܴ���',1,'2014-10-20 18:19:52',1,950),(955,'355','������',1,'2014-10-20 18:19:52',1,950),(956,'356','������',1,'2014-10-20 18:19:52',1,950),(957,'357','�ٷ���',1,'2014-10-20 18:19:52',1,950),(958,'358','��ʯ��',1,'2014-10-20 18:19:52',1,950),(959,'359','�˳���',1,'2014-10-20 18:19:52',1,950),(960,'037','����',1,'2014-10-20 18:19:52',1,0),(961,'371','֣����',1,'2014-10-20 18:19:52',1,960),(962,'372','������',1,'2014-10-20 18:19:52',1,960),(963,'373','������',1,'2014-10-20 18:19:52',1,960),(964,'374','�����',1,'2014-10-20 18:19:52',1,960),(965,'375','ƽ��ɽ',1,'2014-10-20 18:19:52',1,960),(966,'376','������',1,'2014-10-20 18:19:52',1,960),(967,'377','������',1,'2014-10-20 18:19:52',1,960),(968,'378','������',1,'2014-10-20 18:19:52',1,960),(969,'379','������',1,'2014-10-20 18:19:52',1,960),(970,'021','�Ϻ���',1,'2014-10-20 18:19:52',3,1541),(971,'043','����',1,'2014-10-20 18:19:52',1,0),(972,'431','������',1,'2014-10-20 18:19:52',1,971),(973,'432','������',1,'2014-10-20 18:19:52',1,971),(974,'433','�Ӽ���',1,'2014-10-20 18:19:52',1,971),(975,'434','��ƽ��',1,'2014-10-20 18:19:52',1,971),(976,'435','ͨ����',1,'2014-10-20 18:19:52',1,971),(977,'436','�׳���',1,'2014-10-20 18:19:52',1,971),(978,'437','��Դ��',1,'2014-10-20 18:19:52',1,971),(979,'438','��ԭ��',1,'2014-10-20 18:19:52',1,971),(980,'439','�뽭��',1,'2014-10-20 18:19:53',1,971),(981,'041','����',1,'2014-10-20 18:19:53',1,0),(982,'411','������',1,'2014-10-20 18:19:53',1,981),(983,'412','��ɽ��',1,'2014-10-20 18:19:53',1,981),(984,'413','��˳��',1,'2014-10-20 18:19:53',1,981),(985,'414','��Ϫ��',1,'2014-10-20 18:19:53',1,981),(986,'415','������',1,'2014-10-20 18:19:53',1,981),(987,'416','������',1,'2014-10-20 18:19:53',1,981),(988,'417','Ӫ����',1,'2014-10-20 18:19:53',1,981),(989,'418','������',1,'2014-10-20 18:19:53',1,981),(990,'419','������',1,'2014-10-20 18:19:53',1,981),(991,'081','�Ĵ�',1,'2014-10-20 18:19:53',1,0),(993,'812','��֦��',1,'2014-10-20 18:19:53',1,991),(994,'813','�Թ���',1,'2014-10-20 18:19:53',1,991),(995,'814','������',1,'2014-10-20 18:19:53',1,991),(996,'816','������',1,'2014-10-20 18:19:53',1,991),(997,'817','�ϳ���',1,'2014-10-20 18:19:53',1,991),(998,'818','������',1,'2014-10-20 18:19:53',1,991),(999,'819','������',1,'2014-10-20 18:19:53',1,991),(1000,'087','����',1,'2014-10-20 18:19:53',1,0),(1001,'871','������',1,'2014-10-20 18:19:53',1,1000),(1002,'872','������',1,'2014-10-20 18:19:53',1,1000),(1003,'873','������',1,'2014-10-20 18:19:53',1,1000),(1004,'874','������',1,'2014-10-20 18:19:53',1,1000),(1005,'875','��ɽ��',1,'2014-10-20 18:19:53',1,1000),(1006,'876','��ɽ��',1,'2014-10-20 18:19:53',1,1000),(1007,'877','��Ϫ��',1,'2014-10-20 18:19:53',1,1000),(1008,'878','������',1,'2014-10-20 18:19:53',1,1000),(1009,'879','˼é��',1,'2014-10-20 18:19:53',1,1000),(1010,'085','����',1,'2014-10-20 18:19:53',1,0),(1011,'851','������',1,'2014-10-20 18:19:53',1,1010),(1012,'852','������',1,'2014-10-20 18:19:53',1,1010),(1013,'853','��˳��',1,'2014-10-20 18:19:53',1,1010),(1014,'854','������',1,'2014-10-20 18:19:53',1,1010),(1015,'855','������',1,'2014-10-20 18:19:53',1,1010),(1016,'856','ͭ����',1,'2014-10-20 18:19:53',1,1010),(1017,'857','�Ͻ���',1,'2014-10-20 18:19:53',1,1010),(1018,'858','����ˮ',1,'2014-10-20 18:19:53',1,1010),(1019,'859','������',1,'2014-10-20 18:19:53',1,1010),(1020,'097','�ຣ',1,'2014-10-20 18:19:53',1,0),(1021,'971','������',1,'2014-10-20 18:19:53',1,1020),(1022,'972','������',1,'2014-10-20 18:19:53',1,1020),(1023,'973','ͬ����',1,'2014-10-20 18:19:53',1,1020),(1024,'974','������',1,'2014-10-20 18:19:53',1,1020),(1025,'975','������',1,'2014-10-20 18:19:53',1,1020),(1026,'976','������',1,'2014-10-20 18:19:53',1,1020),(1027,'977','�����',1,'2014-10-20 18:19:53',1,1020),(1028,'095','����',1,'2014-10-20 18:19:53',1,0),(1029,'951','������',1,'2014-10-20 18:19:53',1,1028),(1030,'952','ʯ��ɽ',1,'2014-10-20 18:19:53',1,1028),(1031,'953','������',1,'2014-10-20 18:19:53',1,1028),(1032,'954','��ԭ��',1,'2014-10-20 18:19:53',1,1028),(1033,'045','������',1,'2014-10-20 18:19:53',1,0),(1034,'451','������',1,'2014-10-20 18:19:53',1,1033),(1035,'452','�������',1,'2014-10-20 18:19:53',1,1033),(1036,'453','ĵ����',1,'2014-10-20 18:19:53',1,1033),(1037,'454','��ľ˹',1,'2014-10-20 18:19:53',1,1033),(1038,'455','�绯��',1,'2014-10-20 18:19:53',1,1033),(1039,'456','�ں���',1,'2014-10-20 18:19:53',1,1033),(1040,'457','�Ӹ����',1,'2014-10-20 18:19:53',1,1033),(1041,'458','������',1,'2014-10-20 18:19:53',1,1033),(1042,'459','������',1,'2014-10-20 18:19:53',1,1033),(1043,'093','����',1,'2014-10-20 18:19:53',1,0),(1044,'931','������',1,'2014-10-20 18:19:53',1,1043),(1045,'932','������',1,'2014-10-20 18:19:53',1,1043),(1046,'933','ƽ����',1,'2014-10-20 18:19:53',1,1043),(1047,'934','������',1,'2014-10-20 18:19:53',1,1043),(1048,'935','������',1,'2014-10-20 18:19:53',1,1043),(1049,'936','��Ҵ��',1,'2014-10-20 18:19:53',1,1043),(1050,'937','��Ȫ��',1,'2014-10-20 18:19:53',1,1043),(1051,'938','��ˮ��',1,'2014-10-20 18:19:53',1,1043),(1052,'047','���ɹ�',1,'2014-10-20 18:19:53',1,0),(1053,'471','���ͺ���',1,'2014-10-20 18:19:53',1,1052),(1054,'472','��ͷ��',1,'2014-10-20 18:19:53',1,1052),(1055,'473','�ں���',1,'2014-10-20 18:19:53',1,1052),(1056,'474','������',1,'2014-10-20 18:19:53',1,1052),(1057,'475','ͨ����',1,'2014-10-20 18:19:53',1,1052),(1058,'476','�����',1,'2014-10-20 18:19:53',1,1052),(1059,'477','��ʤ��',1,'2014-10-20 18:19:53',1,1052),(1060,'478','�ٺ���',1,'2014-10-20 18:19:53',1,1052),(1061,'479','���ֺ���',1,'2014-10-20 18:19:53',1,1052),(1062,'091','����',1,'2014-10-20 18:19:53',1,0),(1063,'911','�Ӱ���',1,'2014-10-20 18:19:53',1,1062),(1064,'912','������',1,'2014-10-20 18:19:53',1,1062),(1065,'913','μ����',1,'2014-10-20 18:19:53',1,1062),(1066,'914','������',1,'2014-10-20 18:19:53',1,1062),(1067,'915','������',1,'2014-10-20 18:19:53',1,1062),(1068,'916','������',1,'2014-10-20 18:19:53',1,1062),(1069,'917','������',1,'2014-10-20 18:19:53',1,1062),(1070,'919','ͭ����',1,'2014-10-20 18:19:53',2,1062),(1071,'022','�����',1,'2014-10-20 18:19:53',5,1542),(1072,'004','����',1,'2014-10-20 18:19:53',2,0),(1073,'010','������',1,'2014-10-20 18:19:53',3,1540),(1074,'031','�ӱ�',1,'2014-10-20 18:19:53',1,0),(1075,'311','ʯ��ׯ',1,'2014-10-20 18:19:53',1,1074),(1076,'312','������',1,'2014-10-20 18:19:53',1,1074),(1077,'313','�żҿ�',1,'2014-10-20 18:19:53',1,1074),(1078,'314','�е���',1,'2014-10-20 18:19:53',1,1074),(1079,'315','��ɽ��',1,'2014-10-20 18:19:53',1,1074),(1080,'316','�ȷ���',1,'2014-10-20 18:19:53',1,1074),(1081,'317','������',1,'2014-10-20 18:19:53',1,1074),(1082,'318','��ˮ��',1,'2014-10-20 18:19:53',1,1074),(1083,'319','��̨��',1,'2014-10-20 18:19:53',1,1074),(1084,'051','����',1,'2014-10-20 18:19:53',1,0),(1085,'511','����',1,'2014-10-20 18:19:54',1,1084),(1086,'512','������',1,'2014-10-20 18:19:54',1,1084),(1087,'513','��ͨ��',1,'2014-10-20 18:19:54',1,1084),(1088,'514','������',1,'2014-10-20 18:19:54',1,1084),(1089,'515','�γ���',1,'2014-10-20 18:19:54',1,1084),(1090,'516','������',1,'2014-10-20 18:19:54',1,1084),(1091,'517','������',1,'2014-10-20 18:19:54',1,1084),(1092,'518','���Ƹ�',1,'2014-10-20 18:19:54',1,1084),(1093,'519','������',1,'2014-10-20 18:19:54',1,1084),(1094,'071','����',1,'2014-10-20 18:19:54',1,0),(1095,'711','������',1,'2014-10-20 18:19:54',1,1094),(1096,'712','Т����',1,'2014-10-20 18:19:54',1,1094),(1097,'713','������',1,'2014-10-20 18:19:54',1,1094),(1098,'714','��ʯ��',1,'2014-10-20 18:19:54',1,1094),(1099,'715','������',1,'2014-10-20 18:19:54',1,1094),(1100,'716','��ɳ��',1,'2014-10-20 18:19:54',1,1094),(1101,'717','�˲���',1,'2014-10-20 18:19:54',1,1094),(1102,'718','��ʩ��',1,'2014-10-20 18:19:54',1,1094),(1103,'719','ʮ���� ',1,'2014-10-20 18:19:54',1,1094),(1104,'053','ɽ��',1,'2014-10-20 18:19:54',1,0),(1105,'531','������',1,'2014-10-20 18:19:54',1,1104),(1106,'532','�ൺ��',1,'2014-10-20 18:19:54',1,1104),(1107,'533','�Ͳ���',1,'2014-10-20 18:19:54',1,1104),(1108,'534','������',1,'2014-10-20 18:19:54',1,1104),(1109,'535','��̨��',1,'2014-10-20 18:19:54',1,1104),(1110,'536','������',1,'2014-10-20 18:19:54',1,1104),(1111,'537','������',1,'2014-10-20 18:19:54',1,1104),(1112,'538','̩����',1,'2014-10-20 18:19:54',1,1104),(1113,'539','������',1,'2014-10-20 18:19:54',1,1104),(1114,'073','����',1,'2014-10-20 18:19:54',1,0),(1115,'731','��ɳ��',1,'2014-10-20 18:19:54',1,1114),(1116,'732','��̶��',1,'2014-10-20 18:19:54',1,1114),(1117,'733','������',1,'2014-10-20 18:19:54',1,1114),(1118,'734','������',1,'2014-10-20 18:19:54',1,1114),(1119,'735','������',1,'2014-10-20 18:19:54',1,1114),(1120,'736','������',1,'2014-10-20 18:19:54',1,1114),(1121,'737','������',1,'2014-10-20 18:19:54',1,1114),(1122,'738','¦����',1,'2014-10-20 18:19:54',1,1114),(1123,'739','������',1,'2014-10-20 18:19:54',1,1114),(1124,'075','�㶫',1,'2014-10-20 18:19:54',1,0),(1125,'751','�ع���',1,'2014-10-20 18:19:54',1,1124),(1126,'752','������',1,'2014-10-20 18:19:54',1,1124),(1127,'753','÷����',1,'2014-10-20 18:19:54',1,1124),(1128,'754','��ͷ��',1,'2014-10-20 18:19:54',1,1124),(1129,'755','������',1,'2014-10-20 18:19:54',1,1124),(1130,'756','�麣��',1,'2014-10-20 18:19:54',1,1124),(1131,'757','��ɽ��',1,'2014-10-20 18:19:54',1,1124),(1132,'758','������',1,'2014-10-20 18:19:54',1,1124),(1133,'759','տ����',1,'2014-10-20 18:19:54',1,1124),(1134,'089','����',1,'2014-10-20 18:19:54',1,0),(1135,'898','������',1,'2014-10-20 18:19:54',1,1134),(1136,'899','������',1,'2014-10-20 18:19:54',1,1134),(1200,'570','������',1,'2014-10-22 13:50:21',1,930),(1201,'240','������',1,'2014-10-22 14:02:08',2,981),(1202,'270','�人��',1,'2014-10-22 14:02:08',2,1094),(1203,'250','�Ͼ���',1,'2014-10-22 14:02:08',2,1084),(1204,'470','������',1,'2014-10-22 14:02:08',1,1052),(1205,'790','������',1,'2014-10-22 14:02:08',1,900),(1206,'350','������',1,'2014-10-22 14:02:08',1,950),(1207,'930','������',1,'2014-10-22 14:02:08',1,1043),(1208,'530','������',1,'2014-10-22 14:02:08',1,1104),(1209,'450','������',1,'2014-10-22 14:02:08',1,1033),(1211,'200','������',1,'2014-10-22 14:02:08',2,1124),(1212,'280','�ɶ���',1,'2014-10-22 14:02:08',2,991),(1213,'730','������',1,'2014-10-22 14:02:08',1,1114),(1214,'370','������',1,'2014-10-22 14:02:08',1,960),(1215,'870','��ͨ��',1,'2014-10-22 14:02:08',1,1000),(1216,'550','������',1,'2014-10-22 14:02:08',1,940),(1260,'290','������',1,'2014-10-23 11:08:31',2,1062),(1280,'769','��ݸ��',1,'2014-10-27 16:43:48',1,1124),(1286,'310','������',1,'2014-10-27 16:49:18',1,1074),(1287,'335','�ػʵ�',1,'2014-10-27 16:49:40',1,1074),(1288,'580','��ɽ��',1,'2014-10-27 16:53:56',1,930),(1289,'410','������',1,'2014-10-27 16:55:43',1,981),(1290,'421','������',1,'2014-10-27 16:56:11',1,981),(1291,'427','�̽���',1,'2014-10-27 16:56:29',1,981),(1292,'429','��«��',1,'2014-10-27 16:56:46',1,981),(1293,'710','�����',1,'2014-10-27 16:57:37',1,1094),(1294,'722','������',1,'2014-10-27 16:58:11',1,1094),(1295,'724','������',1,'2014-10-27 16:58:27',1,1094),(1296,'728','������',1,'2014-10-27 16:58:43',1,1094),(1297,'510','������',1,'2014-10-27 16:59:17',1,1084),(1298,'523','̩����',1,'2014-10-27 16:59:37',1,1084),(1299,'482','��������',1,'2014-10-27 17:00:26',1,1052),(1300,'483','����������',1,'2014-10-27 17:00:42',1,1052),(1301,'701','ӥ̶��',1,'2014-10-27 17:01:15',1,900),(1302,'941','������',1,'2014-10-27 17:02:07',1,1043),(1303,'943','������',1,'2014-10-27 17:02:25',1,1043),(1304,'760','��ɽ��',1,'2014-10-27 17:03:56',1,1124),(1305,'762','��Դ��',1,'2014-10-27 17:04:11',1,1124),(1306,'763','��Զ��',1,'2014-10-27 17:04:26',1,1124),(1307,'765','˳����',1,'2014-10-27 17:04:46',1,1124),(1308,'766','�Ƹ���',1,'2014-10-27 17:05:00',1,1124),(1309,'768','������',1,'2014-10-27 17:05:17',1,1124),(1310,'660','��β��',1,'2014-10-27 17:05:35',1,1124),(1311,'661','������',1,'2014-10-27 17:06:00',1,1124),(1312,'662','������',1,'2014-10-27 17:06:20',1,1124),(1313,'663','������',1,'2014-10-27 17:06:37',1,1124),(1314,'810','������',1,'2014-10-27 17:07:26',1,991),(1315,'811','������',1,'2014-10-27 17:07:56',2,1072),(1316,'825','������',1,'2014-10-27 17:08:31',1,991),(1317,'826','�㰲��',1,'2014-10-27 17:08:45',1,991),(1318,'827','������',1,'2014-10-27 17:08:59',1,991),(1319,'830','������',1,'2014-10-27 17:09:15',1,991),(1320,'831','�˱���',1,'2014-10-27 17:09:35',1,991),(1321,'832','�ڽ���',1,'2014-10-27 17:09:47',1,991),(1322,'833','��ɽ��',1,'2014-10-27 17:10:00',1,991),(1323,'834','������',1,'2014-10-27 17:10:11',1,991),(1324,'835','�Ű���',1,'2014-10-27 17:10:24',1,991),(1325,'836','������',1,'2014-10-27 17:10:37',1,991),(1326,'837','�����',1,'2014-10-27 17:10:49',1,991),(1327,'838','������',1,'2014-10-27 17:11:01',1,991),(1328,'839','��Ԫ��',1,'2014-10-27 17:11:15',1,991),(1329,'743','������',1,'2014-10-27 17:12:01',1,1114),(1330,'744','�żҽ�',1,'2014-10-27 17:12:14',1,1114),(1331,'745','������',1,'2014-10-27 17:12:28',1,1114),(1332,'746','������',1,'2014-10-27 17:12:42',1,1114),(1333,'391','������',1,'2014-10-27 17:13:14',1,960),(1334,'392','�ױ���',1,'2014-10-27 17:13:26',1,960),(1335,'393','�����',1,'2014-10-27 17:13:42',1,960),(1336,'394','�ܿ���',1,'2014-10-27 17:13:53',1,960),(1337,'395','�����',1,'2014-10-27 17:14:08',1,960),(1338,'396','פ���',1,'2014-10-27 17:14:20',1,960),(1339,'398','����Ͽ',1,'2014-10-27 17:14:35',1,960),(1340,'691','������',1,'2014-10-27 17:15:08',1,1000),(1341,'692','º����',1,'2014-10-27 17:15:22',1,1000),(1342,'881','������',1,'2014-10-27 17:15:34',1,1000),(1343,'883','�ٲ���',1,'2014-10-27 17:15:48',1,1000),(1344,'886','������',1,'2014-10-27 17:16:01',1,1000),(1345,'887','�е���',1,'2014-10-27 17:16:14',1,1000),(1346,'888','������',1,'2014-10-27 17:16:25',1,1000),(1347,'561','������',1,'2014-10-27 17:16:54',1,940),(1348,'562','ͭ����',1,'2014-10-27 17:17:06',1,940),(1349,'563','������',1,'2014-10-27 17:17:19',1,940),(1350,'564','������',1,'2014-10-27 17:18:58',1,940),(1351,'565','������',1,'2014-10-27 17:19:11',1,940),(1352,'566','�����',1,'2014-10-27 17:19:22',1,940),(1353,'440','������',1,'2014-10-27 17:20:05',1,971),(1354,'770','���Ǹ�',1,'2014-10-27 17:20:25',1,910),(1355,'910','������',1,'2014-10-27 17:23:50',1,1062),(1356,'890','������',1,'2014-10-27 17:25:03',1,1134),(1440,'098','����',1,'2014-10-27 17:39:29',1,0),(1441,'891','������',1,'2014-10-27 17:39:53',1,1440),(1442,'892','�տ���',1,'2014-10-27 17:40:05',1,1440),(1443,'893','ɽ����',1,'2014-10-27 17:40:19',1,1440),(1540,'001','����',1,'2014-11-17 17:05:27',1,0),(1541,'002','�Ϻ�',1,'2014-11-17 17:05:35',1,0),(1542,'003','���',1,'2014-11-17 17:05:46',1,0),(1941,'13','zhzns',0,'2015-06-12 14:44:08',2,123),(1980,'111','��˹��',0,'2015-06-15 17:33:25',1,1104),(2007,'800','���̫',0,'2015-08-22 16:10:43',1,1542),(2021,'890','��ɳ��',1,'2014-10-27 17:25:03',1,1134),(2022,'898','����',1,'2014-10-27 17:25:03',1,1134),(2023,'898','�Ĳ���',1,'2014-10-27 17:25:03',1,1134),(2024,'890','������',1,'2014-10-27 17:25:03',1,1134),(2025,'890','������',1,'2014-10-27 17:25:03',1,1134),(2026,'890','��ָɽ��',1,'2014-10-27 17:25:03',1,1134);

#
# Structure for table "admin_global_config"
#

DROP TABLE IF EXISTS `admin_global_config`;
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

INSERT INTO `admin_global_config` VALUES (2,0,NULL,NULL,NULL,NULL,'ORDER_PAY_STYLE','���ʽ','select',NULL,NULL,'[{\"id\":1,\"name\":\"�����\"},{\"id\":2,\"name\":\"Ԥ���ܻ����30%������ǰ�������\"},{\"id\":3,\"name\":\"Ԥ���ܻ����30%����������\"},{\"id\":4,\"name\":\"������һ���ڸ���\"},{\"id\":5,\"name\":\"���ǰ����ػ���\"},{\"id\":6,\"name\":\"����ǰ�������\"},{\"id\":7,\"name\":\"����5��ǰ���嵱�»���\"},{\"id\":8,\"name\":\"����10��ǰ���嵱�»���\"},{\"id\":9,\"name\":\"����15��ǰ���嵱�»���\"},{\"id\":10,\"name\":\"����20��ǰ���嵱�»���\"},{\"id\":11,\"name\":\"����10��ǰ���ˣ�20��ǰ���嵱�»���\"},{\"id\":12,\"name\":\"����30��ǰ���嵱�»���\"},{\"id\":13,\"name\":\"��������\"}]',NULL,5,0,0,'����ķ�ʽ',NULL,'dongkaiyue'),(3,0,NULL,NULL,NULL,NULL,'CUSTOMER_LEVEL','�ͻ��ȼ�','select',NULL,NULL,'[{\"id\":1,\"name\":\"A+\"},{\"id\":2,\"name\":\"A\"},{\"id\":3,\"name\":\"B\"},{\"id\":4,\"name\":\"C\"},{\"id\":5,\"name\":\"D\"},{\"id\":6,\"name\":\"E\"}]',NULL,3,0,0,'�ͻ��ĵȼ�',NULL,'dongkaiyue'),(4,0,NULL,NULL,NULL,NULL,'FILE_TRANSFER_STYLE','�ļ����䷽ʽ','select',NULL,NULL,'[{\"id\":1,\"name\":\"���淽ʽ\"},{\"id\":2,\"name\":\"�ʼ���ʽ\"},{\"id\":3,\"name\":\"Q Q ��ʽ\"},{\"id\":4,\"name\":\"�绰��ʽ\"},{\"id\":5,\"name\":\"ɨ�跽ʽ\"}]',NULL,2,0,0,'�ļ�����ķ�ʽ',NULL,'dongkaiyue'),(5,0,NULL,NULL,NULL,NULL,'STUDY_LEVEL','ѧ���ȼ�','select',NULL,NULL,'[{\"id\":1,\"name\":\"Сѧ\"},{\"id\":2,\"name\":\"������ѧ\"},{\"id\":3,\"name\":\"�߼���ѧ\"},{\"id\":4,\"name\":\"��ѧר��\"},{\"id\":5,\"name\":\"��ѧ����\"},{\"id\":6,\"name\":\"˶ʿ�о���\"},{\"id\":7,\"name\":\"��ʿ�о���\"}]',NULL,1,0,0,'ѧ��',NULL,'dongkaiyue'),(6,0,NULL,NULL,NULL,NULL,'USER_PAY_STYLE','Ա��֧����ʽ','select',NULL,NULL,'[{\"id\":1,\"name\":\"��ʱ\"},{\"id\":2,\"name\":\"�Ǽ�\"},{\"id\":3,\"name\":\"��ʱ+�Ǽ�\"}]',NULL,1,0,0,'֧��Ա�����ʵķ�ʽ',NULL,'dongkaiyue');

#
# Structure for table "app_department"
#

DROP TABLE IF EXISTS `app_department`;
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

INSERT INTO `app_department` VALUES (1,'����',' ',4,'dongkaiyue','BUSINESS'),(2,'������',' ',7,'dongkaiyue','ADMINISTRATOR'),(3,'����',' ',8,'dongkaiyue','FINANCE'),(5,'���첿',' ',3,'dongkaiyue','0'),(6,'�ɹ���',' ',2,'dongkaiyue','0'),(7,'������',' ',1,'dongkaiyue','0'),(8,'�ֿⲿ',' ',1,'dongkaiyue','0'),(9,'�ܾ���',' ',1,'dongkaiyue','0');

#
# Structure for table "app_domain"
#

DROP TABLE IF EXISTS `app_domain`;
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

INSERT INTO `app_domain` VALUES (9,'backend','backend','��̨Ӧ��',NULL,NULL,1,NULL,NULL,'2017-01-01 21:15:18','15121001@15121001',15),(11,'dongkaiyue','dongkaiyue','����Խ����ƽ̨',NULL,NULL,1,NULL,NULL,'2016-12-27 09:53:55','15121001@15121001',2);

#
# Structure for table "app_func_tree"
#

DROP TABLE IF EXISTS `app_func_tree`;
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

INSERT INTO `app_func_tree` VALUES (2,'Ӧ�ù���','backend',NULL,NULL,NULL,'js/business/views/basic/appmanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(4,'ϵͳ����','backend',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(5,'��ɫ����','backend',NULL,NULL,NULL,'js/business/views/basic/rolemanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(6,'Ա������','backend',NULL,NULL,NULL,'js/business/views/basic/operatormanager',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(13,'�ֵ����','backend',NULL,NULL,NULL,'js/business/views/basic/adminglobalconfig',NULL,4,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(14,'ϵͳ����','dongkaiyue',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(15,'��ɫ����','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/rolemanager',NULL,14,1,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(16,'���Ź���','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/appdepartment',NULL,14,2,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(17,'Ա������','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/operatormanager',NULL,14,3,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(18,'�ֵ����','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/adminglobalconfig',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(19,'��������','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/adminareacode',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(20,'Ӧ�ù���','dongkaiyue',NULL,NULL,NULL,'js/business/views/basic/appmanager',NULL,14,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL),(22,'1','2',NULL,NULL,NULL,' ',NULL,14,10,NULL,NULL,NULL,0,0,NULL,NULL,NULL);

#
# Structure for table "app_role"
#

DROP TABLE IF EXISTS `app_role`;
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

INSERT INTO `app_role` VALUES (1,'������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-31 12:52:54','15121001@15121001',NULL,NULL,15,'EXAMINE'),(12,'�����µ�',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-31 13:22:36','15121001@15121001',NULL,NULL,6,'EXAMINE'),(13,'�������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:04:48','15121001@15121001',NULL,NULL,1,NULL),(14,'����������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:05:17','15121001@15121001',NULL,NULL,1,NULL),(17,'�ɹ�����',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:06:37','15121001@15121001',NULL,NULL,1,NULL),(18,'�ɹ���Ա',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:06:55','15121001@15121001',NULL,NULL,1,NULL),(19,'ǰ̨��Ա',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:07:16','15121001@15121001',NULL,NULL,1,NULL),(20,'������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:07:54','15121001@15121001',NULL,NULL,1,NULL),(21,'���',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:08:01','15121001@15121001',NULL,NULL,1,NULL),(22,'�ֹ�',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:08:22','15121001@15121001',NULL,NULL,1,NULL),(34,'�ܾ���',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:33','15121001@15121001',NULL,NULL,2,NULL),(35,'���ܾ���',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:42','15121001@15121001',NULL,NULL,1,NULL),(36,'����',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:49','15121001@15121001',NULL,NULL,1,NULL),(37,'����',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:16:57','15121001@15121001',NULL,NULL,1,NULL),(38,'��Ա',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:17:28','15121001@15121001',NULL,NULL,1,NULL),(39,'�������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:17:52','15121001@15121001',NULL,NULL,1,NULL),(40,'����ʦ��',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:07','15121001@15121001',NULL,NULL,1,NULL),(41,'����ѧͽ',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:23','15121001@15121001',NULL,NULL,1,NULL),(42,'�������',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:31','15121001@15121001',NULL,NULL,1,NULL),(43,'����ʦ��',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:46','15121001@15121001',NULL,NULL,1,NULL),(44,'����ѧͽ',' ',NULL,'dongkaiyue',NULL,NULL,NULL,NULL,'2016-12-30 00:18:59','15121001@15121001',NULL,NULL,1,NULL);

#
# Structure for table "app_role_func_tree"
#

DROP TABLE IF EXISTS `app_role_func_tree`;
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

DROP TABLE IF EXISTS `app_subject_user`;
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
  `leave_time` datetime DEFAULT NULL,
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
  `award_record` varchar(255) DEFAULT NULL,
  `break_rule_record` varchar(255) DEFAULT NULL,
  `examine_status` tinyint(3) DEFAULT '0',
  `gender` tinyint(3) DEFAULT '0',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  KEY `idx_app_user_appid` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Data for table "app_subject_user"
#

INSERT INTO `app_subject_user` VALUES (1,'15121000@15121000','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','backend',NULL,1,NULL,'15121000','����',NULL,0,NULL,0,NULL,0,0,1,NULL,NULL,NULL,NULL,'0',1,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(2,'admin@15121000','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','backend',NULL,12,NULL,NULL,'����',NULL,0,NULL,1,NULL,0,0,1,NULL,NULL,NULL,NULL,'0',0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(4,'15121001','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','dongkaiyue',NULL,17,0,'15121001','����',NULL,2,NULL,0,'123',0,0,1,NULL,NULL,NULL,'2017-01-04 23:47:29','4',1,' 15919492724','2016-12-14 08:00:00','2016-12-07 08:00:00',NULL,'13112719911011003X','2016-12-01 08:00:00',1,3,' �����Ʋ�����',1541,970,' ����ƽ��������',' ����',' �ɻ�',' 5165156112','2016-12-15 08:00:00','3','33333333',' ',' ','3',' ',' ','3','3',' 4.3465354','���˺ܶ����1\n123\n1\n23\n123\n12\n31\n23\n123','���˺ܶ໵��',1,0,'files/52f55a6a-7402-4b28-8512-72701f376db0.jpg'),(6,'1234@15121001','43598aa623e403c8d62f50d1aeaa9dee401a170f59eb016930ea5c0ee421894e2142ff548c8d3c3f64995605585e833c','dongkaiyue',NULL,39,0,'15121001','���',NULL,5,NULL,1,' 0733-23134123',0,0,1,NULL,NULL,NULL,'2017-01-04 23:08:16','4',0,' ','2009-08-15 08:00:00',NULL,'2017-01-03 08:00:00','6541564566565111565','2017-01-03 08:00:00',2,3,' ',991,1320,' ',' 11',' ',' ',NULL,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,' ');

#
# Structure for table "org_entity_info"
#

DROP TABLE IF EXISTS `org_entity_info`;
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

INSERT INTO `org_entity_info` VALUES (1,'15121001','DKY',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'���ǵط�',NULL,NULL,0,'backend','755',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "pay_operation_logger"
#

DROP TABLE IF EXISTS `pay_operation_logger`;
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

DROP TABLE IF EXISTS `user_depart_change`;
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
  `com_qq_num` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `com_qq_passwd` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `com_qq_permit` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_ip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_speed` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_permit` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `computer_no` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `computer_passwd` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `computer_config` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `examine_status` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "user_depart_change"
#

INSERT INTO `user_depart_change` VALUES (4,'2016-12-30 15:30:04',4,1,1,'0755-3133245',10001,2,'�ܺ�',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,'2016-12-30 15:31:14',4,2,1,'0731-12325453',100000,2,' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(6,'2016-12-30 15:43:21',4,3,12,'1231-234123',200000,2,'10000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(10,'2016-12-30 15:59:03',4,8,13,'0766-2255555',1500000,1,' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(11,'2016-12-30 16:20:38',4,1,1,'0755-234234123',200000,2,' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(12,'2016-12-30 16:22:49',4,1,12,'0834-34233441',200000,2,' ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(13,'2017-01-01 11:16:26',4,3,20,'0731-555555',100000,2,'1111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(14,'2017-01-01 11:54:28',4,1,1,'0933-3333311',200000,2,'3000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(15,'2017-01-04 23:14:56',6,5,39,' 0733-23134123',10000,0,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',NULL,NULL,NULL,NULL,0),(16,'2017-01-02 14:50:47',4,2,1,'1',300,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(17,'2017-01-04 23:47:47',4,2,17,'123',30000,2,'3333','3','33333333',' ',' ','3',' ',' ','3','3',NULL,NULL,'2017-01-04 23:47:47','4',0),(18,'2017-01-04 23:47:35',4,1,1,' ',0,0,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',NULL,NULL,'2017-01-04 23:47:35','4',0);

#
# Structure for table "weixin_counter"
#

DROP TABLE IF EXISTS `weixin_counter`;
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
