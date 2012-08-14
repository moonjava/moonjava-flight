set foreign_key_checks=0;

drop table if exists FLIGHT.BANCO;

create table FLIGHT.BANCO (
ID integer(11) not null auto_increment,
PESSOAFISICA_ID integer(11) not null,
BANCO integer(6) not null,
AGENCIA integer(6) not null,
CONTA integer(10) not null,

primary key(ID),
unique key(CONTA),
unique key(PESSOAFISICA_ID),

constraint foreign key FK_PESSOAFISICA_BANCO (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;