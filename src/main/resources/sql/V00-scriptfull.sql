CREATE DATABASE FLIGHT;
USE FLIGHT;
SET STORAGE_ENGINE=InnoDB;
SET NAMES 'utf8';

set foreign_key_checks=0;

drop table if exists FLIGHT.AERONAVE;
drop table if exists FLIGHT.VOO;
drop table if exists FLIGHT.PESSOAFISICA;
drop table if exists FLIGHT.USUARIO; 
drop table if exists FLIGHT.REEMBOLSO;
drop table if exists FLIGHT.PASSAGEM;
 

create table FLIGHT.AERONAVE (
ID integer not null auto_increment,
NOME varchar(40) not null,
CODIGO varchar(40) not null,
QTD_ASSENTO smallint not null,
MAPA tinyint not null,

primary key (ID),
unique key(CODIGO)

)Engine=InnoDB;


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


create table FLIGHT.PESSOAFISICA(
ID integer not null auto_increment,
NOME varchar(30) not null,
SOBRENOME varchar(80) not null,
DATA_NASCIMENTO date not null,
CPF bigint not null,
RG varchar(15) not null,
ENDERECO varchar(100) not null,
TEL_RESIDENCIAL integer not null,
TEL_CELULAR integer null,
EMAIL varchar(100) null,

primary key (ID),
unique key(CPF)
	
)Engine=InnoDB;


create table FLIGHT.USUARIO (
ID integer not null auto_increment,
PESSOAFISICA_ID integer not null,
CODIGO varchar(40) not null,
PERFIL tinyint not null,
LOGIN varchar(50) not null,
SENHA varchar(50) not null,

primary key (ID),
unique key(CODIGO),
unique key(PESSOAFISICA_ID),
unique key(LOGIN, SENHA),

constraint foreign key FK_PESSOAFISICA_USUARIO (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;


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


create table FLIGHT.PASSAGEM(
ID integer not null auto_increment,
VOO_ID integer not null,
PESSOAFISICA_ID integer not null,
COD_BILHETE varchar(11) not null,
ASSENTO varchar(5) not null,

primary key(ID),
unique key(COD_BILHETE),
unique key(PESSOAFISICA_ID,ASSENTO),

constraint foreign key FK_VOO_PASSAGEM (VOO_ID)
references FLIGHT.VOO (ID),

constraint foreign key FK_PESSOAFISICA_PASSAGEM (PESSOAFISICA_ID)
references FLIGHT.PESSOAFISICA (ID)

)Engine=InnoDB;

CREATE USER 'usjt'@'localhost' IDENTIFIED BY 'usjt';
GRANT ALL PRIVILEGES ON FLIGHT.* TO 'usjt'@'localhost' WITH GRANT OPTION;