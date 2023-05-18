create table if not EXISTS `user`
(
    `idUser`        int          not null AUTO_INCREMENT,
    `userName`      varchar(255)  not null,
    `userPassword` varchar(255) not null,
    `userMail`     varchar(255)  not null,
    `userFirstName` varchar(30)  not null,
    `userLastName`  varchar(30)  not null,
    `nationaliter`  varchar(3)   not null,
    `birthday`      varchar(30)  not null,
    `adresse`       varchar(100) not null,
    `NumTel`        int          not null,
    `lang` varchar(30) not null ,
    `DateInscri` varchar(30) not null ,
    primary key (`idUser`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;