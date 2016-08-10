
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";




CREATE TABLE IF NOT EXISTS `category` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;


INSERT INTO `category` (`id`, `name`) VALUES
(9, 'Kitchen Wares'),
(10, 'Electronics'),
(11, 'Grocery');



CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` bigint(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;


INSERT INTO `product` (`id`, `category_id`, `description`, `name`, `purchasePrice`, `sellingPrice`) VALUES
(19, 9, 'This is cooker thingy', 'Cooker', '3000', '4000'),
(20, 10, 'Electronics item', 'Mobile Phone', '5000', '6000');



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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` bigint(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


INSERT INTO `user` (`id`, `first_name`, `last_name`, `phone`, `email`) VALUES
(1, 'Binaya', 'Dahal', 9813536789, 'bidahal@deerwalk.com'),
(2, 'Vinaya', 'Dahal', 9860805361, 'vinayadahal@gmail.com');



CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


INSERT INTO `user_role` (`id`, `username`, `password`, `role`, `user_id`) VALUES
(1, 'admin', 'admin', 'admin', 1);


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


ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
