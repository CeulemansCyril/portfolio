create table if not EXISTS `pays`
(
    `idPays` varchar(3) not null,
    `PaysName` varchar(20) not null,
    primary key (`idPays`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;