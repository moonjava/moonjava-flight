set foreign_key_checks=0;

drop table if exists FLIGHT.REEMBOLSO;

create table FLIGHT.REEMBOLSO (
ID integer not null auto_increment,
PASSAGEM_ID integer not null,
TITULAR varchar(80) not null,
CPF bigint not null,
BANCO integer not null,
AGENCIA integer not null,
CONTA integer not null,
VALOR double(10,2) not null,

primary key(ID),
unique key(PASSAGEM_ID),

constraint foreign key FK_PASSAGEM_REEMBOLSO (PASSAGEM_ID)
references FLIGHT.PASSAGEM (ID)

)Engine=InnoDB;