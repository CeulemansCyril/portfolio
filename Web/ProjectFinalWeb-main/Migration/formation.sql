create table if not exists `formation`(
    `idForma` int not null AUTO_INCREMENT,
    `nameForma` varchar(30) not null,
    `LVEtude` varchar(10) not null,
    `statue` tinyint DEFAULT NULL,
    `dateStart` varchar(30) not null,
    `dateEnd` varchar(30) not null,
    primary key (`idForma`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;