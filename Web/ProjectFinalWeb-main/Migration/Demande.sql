create table if not exists Demande
(
    `idDemande` int not null AUTO_INCREMENT,
    `idUser`    int not null,
    `statue` tinyint default true,
    `idFormation` int not null,
    primary key (`idDemande`),
    foreign key (`idUser`) references user(`idUser`) on delete cascade ,
    foreign key (`idFormation`) references formation(`idForma`) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;