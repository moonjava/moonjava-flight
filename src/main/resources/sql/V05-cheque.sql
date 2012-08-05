set foreign_key_checks=0;

drop table if exists FLIGHT.CHEQUE;

create table FLIGHT.CHEQUE (
ID integer not null,
TITULAR varchar(40) not null,
NUMERO integer not null,
BANCO integer not null,
AGENCIA integer not null,
CONTA integer not null,

constraint foreign key FK_PAGAMENTO_ID (ID) 
references FLIGHT.PAGAMENTO (ID)

)Engine=InnoDB;