set foreign_key_checks=0;

drop table if exists FLIGHT.PESSOAFISICA;

create table FLIGHT.PESSOAFISICA(
ID integer not null auto_increment,
NOME varchar(30) not null,
SOBRENOME varchar(80) not null,
DATA_NASCIMENTO date not null,
CPF bigint not null,
RG varchar(15) not null,
ENDERECO varchar(100) not null,
TEL_RESIDENCIAL integer not null,
TEL_CELULAR integer null,
EMAIL varchar(100) null,

primary key (ID),
unique key(CPF)

)Engine=InnoDB;