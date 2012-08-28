set foreign_key_checks=0;

drop table if exists FLIGHT.REEMBOLSO;

create table FLIGHT.REEMBOLSO (
ID integer not null auto_increment,
PASSAGEM_ID integer not null,
BANCO integer not null,
AGENCIA integer not null,
CONTA integer not null,

primary key(ID),
unique key(BANCO,AGENCIA,CONTA,PASSAGEM_ID),

constraint foreign key FK_PASSAGEM_REEMBOLSO (PASSAGEM_ID)
references FLIGHT.PASSAGEM (ID)

)Engine=InnoDB;