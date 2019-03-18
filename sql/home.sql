
DROP database if exists home;
CREATE database home DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE home;

DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) NOT NULL COMMENT '流转钱数，花费是正，收入是负',
  `name` varchar(255) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `prepaid` int(11) NOT NULL DEFAULT '0' COMMENT '预花费 0默认正常花费 1储值卡等预付费 ',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型 0未分类 1个人消费 2共同消费 3家庭成员消费(如代缴话费等)',
  `mark` varchar(255) NOT NULL COMMENT '收支备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
