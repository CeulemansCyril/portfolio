CREATE TABLE if not EXISTS `migration`
(
    `id`         int(10) not null AUTO_INCREMENT,
    `lastUpdate` date,
    primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;