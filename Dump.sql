CREATE TABLE IF NOT EXISTS `category` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

INSERT INTO `category` (`id`, `name`) VALUES
(9, 'Kitchen Wares'),
(10, NULL);

CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` bigint(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

INSERT INTO `clients` (`id`, `address`, `email`, `mobile`, `name`, `phone`) VALUES
(4, 'Jorpati', 'bidahal@deerwalk.com', 9860805361, 'Vinaya Dahal', 14915904),
(6, 'Jorpati', 'bidahal@deerwalk.com', 9813536789, 'Vinaya Dahal', 149159040);


CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` smallint(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `purchasePrice` decimal(38,0) DEFAULT NULL,
  `sellingPrice` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

INSERT INTO `product` (`id`, `category_id`, `description`, `name`, `purchasePrice`, `sellingPrice`) VALUES
(19, 9, 'This is cooker thingy', 'Cooker', '300', '400');

CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `transaction_status` varchar(255) DEFAULT NULL,
  `transaction_with` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;


CREATE TABLE IF NOT EXISTS `transaction_detail` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `qty` int(11) DEFAULT NULL,
  `total_price` decimal(38,0) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_transaction_detail_transaction_id` (`transaction_id`),
  KEY `FK_transaction_detail_product_id` (`product_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;


CREATE TABLE IF NOT EXISTS `user` (
  `sn` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emp_id` varchar(255) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;


INSERT INTO `user` (`sn`, `address`, `dob`, `email`, `emp_id`, `f_name`, `gender`, `l_name`, `password`, `phone`, `role`, `username`) VALUES
(4, 'Kathmandu', '2014-01-01', 'admin@IMS.com', 'IMS_4', 'admin', 'male', 'admin', '21232f297a57a5a743894a0e4a801fc3', 49561381, 'admin', 'admin'),
(5, 'Kathmandu', '2014-01-02', 'user@ims.com', 'IMS_5', 'user', 'female', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 49156845, 'user', 'user');

CREATE TABLE IF NOT EXISTS `view_buying` (
`product_id` int(11)
,`total_qty` decimal(32,0)
);
CREATE TABLE IF NOT EXISTS `view_selling` (
`product_id` int(11)
,`total_qty` decimal(32,0)
);
DROP TABLE IF EXISTS `view_buying`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_buying` AS select distinct `td`.`product_id` AS `product_id`,sum(`td`.`qty`) AS `total_qty` from (`transaction_detail` `td` join `transaction` `t` on((`t`.`id` = `td`.`transaction_id`))) where (`t`.`transaction_status` = 'buying') group by `td`.`product_id`;
DROP TABLE IF EXISTS `view_selling`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_selling` AS select distinct `td`.`product_id` AS `product_id`,sum(`td`.`qty`) AS `total_qty` from (`transaction_detail` `td` join `transaction` `t` on((`t`.`id` = `td`.`transaction_id`))) where (`t`.`transaction_status` = 'selling') group by `td`.`product_id`;
