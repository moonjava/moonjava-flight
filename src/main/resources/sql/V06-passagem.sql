set foreign_key_checks=0;

drop table if exists FLIGHT.PASSAGEM;

create table FLIGHT.PASSAGEM(
ID integer(11) not null auto_increment,
VOO_ID integer(11) not null,
PESSOAFISICA_ID integer(11) not null,
COD_BILHETE integer(11) not null,
ASSENTO varchar(5) not null,

primary key(ID),
unique key(VOO_ID),
unique key(PESSOAFISICA_ID),

constraint foreign key FK_VOO_PASSAGEM (VOO_ID)
references FLIGHT.VOO (ID),

constraint foreign key FK_PESSOAFISICA_PASSAGEM (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;