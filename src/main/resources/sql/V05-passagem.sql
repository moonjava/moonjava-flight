set foreign_key_checks=0;

drop table if exists FLIGHT.PASSAGEM;

create table FLIGHT.PASSAGEM(
ID integer not null auto_increment,
VOO_ID integer null,
PESSOAFISICA_ID integer not null,
COD_BILHETE varchar(11) not null,
ASSENTO varchar(5) null,

primary key(ID),
unique key(COD_BILHETE),
unique key(VOO_ID, PESSOAFISICA_ID),

constraint foreign key FK_VOO_PASSAGEM (VOO_ID)
references FLIGHT.VOO (ID),

constraint foreign key FK_PESSOAFISICA_PASSAGEM (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;