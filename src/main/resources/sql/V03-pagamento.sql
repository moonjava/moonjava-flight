set foreign_key_checks=0;

drop table if exists FLIGHT.PAGAMENTO;

create table FLIGHT.PAGAMENTO (
ID integer not null auto_increment,
VALOR double not null,
DATA_CRIACAO datetime not null,

primary key (ID)

)Engine=InnoDB;