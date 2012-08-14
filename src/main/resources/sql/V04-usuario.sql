set foreign_key_checks=0;

drop table if exists FLIGHT.USUARIO; 

create table FLIGHT.USUARIO (
ID integer(11) not null auto_increment,
PESSOAFISICA_ID integer(11) not null,
CODIGO integer(11) not null,
CARGO tinyint not null,
LOGIN varchar(50) not null,
SENHA varchar(50) not null,

primary key (ID),
unique key(CODIGO),
unique key(PESSOAFISICA_ID),

constraint foreign key FK_PESSOAFISICA_USUARIO (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;