set foreign_key_checks=0;

drop table if exists FLIGHT.CARTAO;

create table FLIGHT.CARTAO (
ID integer not null,
TITULAR varchar(40) not null,
DATA_VALIDADE date not null,
BANDEIRA tinyint not null,

constraint foreign key FK_PAGAMENTO_ID (ID) 
references FLIGHT.PAGAMENTO (ID)

)Engine=InnoDB;