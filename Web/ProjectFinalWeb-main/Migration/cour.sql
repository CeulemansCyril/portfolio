create table if not exists `cour`
(
    `idCour`   int         not null AUTO_INCREMENT,
    `courName` varchar(25) not null,
    `statut`   tinyint default true,
    `teacher`  varchar(25),
    primary key (`idCour`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;