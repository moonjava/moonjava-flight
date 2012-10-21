drop database if exists FLIGHT;
create database FLIGHT;
use FLIGHT;
set STORAGE_ENGINE=InnoDB;
set NAMES 'utf8';


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


create table FLIGHT.REEMBOLSO (
ID integer not null auto_increment,
PASSAGEM_ID integer not null,
TITULAR varchar(80) not null,
CPF bigint not null,
BANCO integer not null,
AGENCIA integer not null,
CONTA integer not null,
VALOR double(10,2) not null,

primary key(ID),
unique key(PASSAGEM_ID),

constraint foreign key FK_PASSAGEM_REEMBOLSO (PASSAGEM_ID)
references FLIGHT.PASSAGEM (ID)

)Engine=InnoDB;


insert into FLIGHT.PESSOAFISICA
(NOME, SOBRENOME, DATA_NASCIMENTO, CPF, RG, ENDERECO,
TEL_RESIDENCIAL, TEL_CELULAR, EMAIL)
values('FLIGHT', 'FLIGHT', '01/01/0001', 11111111111, '00000000',
 'localhost', '00000000', '00000000', 'contato@moonjava.com.br');
 

insert into FLIGHT.USUARIO
(PESSOAFISICA_ID, CODIGO, PERFIL, LOGIN, SENHA)
values((select ID from FLIGHT.PESSOAFISICA ), 'U0000', 1, 'admin', 'ISMvKXpXpadDiUoOSoAfww==');


CREATE USER 'usjt'@'localhost' IDENTIFIED BY 'usjt';
GRANT ALL PRIVILEGES ON FLIGHT.* TO 'usjt'@'localhost' WITH GRANT OPTION;