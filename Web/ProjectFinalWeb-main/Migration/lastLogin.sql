create table if not exists LastLogin(
    `id` int not null auto_increment,
    `idUser` int not NULL,
    `date` varchar(30) not null,
    primary key (`id`)   ,
    foreign key (`idUser`) references user(`idUser`) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;