set foreign_key_checks=0;

drop table if exists FLIGHT.AERONAVE; 

create table FLIGHT.AERONAVE (
ID int(11) not null auto_increment,
NOME varchar(40) not null,
CODIGO int(11) not null,
QTD_ASSENTO smallint not null,
MAPA tinyint not null,

primary key (ID),
unique key(CODIGO)

)Engine=InnoDB;