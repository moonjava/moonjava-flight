set foreign_key_checks=0;

drop table if exists FLIGHT.USUARIO; 

create table FLIGHT.USUARIO (
ID integer not null auto_increment,
PESSOAFISICA_ID integer not null,
CODIGO varchar(40) not null,
PERFIL tinyint not null,
LOGIN varchar(50) not null,
SENHA varchar(50) not null,

primary key (ID),
unique key(CODIGO),
unique key(PESSOAFISICA_ID),
unique key(LOGIN, SENHA),

constraint foreign key FK_PESSOAFISICA_USUARIO (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;