set foreign_key_checks=0;

drop table if exists FLIGHT.AERONAVE; 

create table FLIGHT.AERONAVE (
ID integer not null auto_increment,
NOME varchar(40) not null,
CODIGO varchar(40) not null,
QTD_ASSENTO smallint not null,
MAPA tinyint not null,

primary key (ID),
unique key(CODIGO)

)Engine=InnoDB;