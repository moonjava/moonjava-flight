set foreign_key_checks=0;

drop table if exists FLIGHT.VOO;

create table FLIGHT.VOO (
ID integer not null auto_increment,
AERONAVE_ID integer not null,
CODIGO varchar(40) not null,
ORIGEM varchar(40) not null,
DESTINO varchar(40) not null,
ESCALA varchar(40) null,
DATA_PARTIDA datetime not null,
DATA_CHEGADA datetime not null,
OBSERVACAO varchar(100) null,
STATUS tinyint not null,
ASSENTO_LIVRE integer not null,
PRECO double not null,

primary key (ID),
unique key (CODIGO),

constraint foreign key FK_AERONAVE_ID (AERONAVE_ID) 
references FLIGHT.AERONAVE (ID)

)Engine=InnoDB;