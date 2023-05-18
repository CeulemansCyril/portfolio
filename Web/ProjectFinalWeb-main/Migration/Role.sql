create table if not EXISTS `role`
(
    `idRole`   int         not null AUTO_INCREMENT,
    `NameRole` varchar(15) not null,
    primary key (`idRole`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;