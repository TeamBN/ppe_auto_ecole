﻿DROP DATABASE IF EXISTS autoecole;
CREATE DATABASE autoecole CHARACTER SET 'utf8';

use autoecole

CREATE TABLE Client (
IdC int NOT NULL AUTO_INCREMENT Primary key
)
ENGINE = InnoDB;

CREATE TABLE ExamenP (
IdExaP int NOT NULL AUTO_INCREMENT Primary key,
Date_Inscri_Permis DATE NOT NULL,
Nb_Passage_Permis int(1) NOT NULL DEFAULT 0,
Resultat ENUM ('oui', 'non'),
IdC int NOT NULL, FOREIGN KEY (IdC) REFERENCES Client (IdC)
)
ENGINE = InnoDB;


CREATE TABLE ExamenC (
IdExaC int not null AUTO_INCREMENT Primary key,
Date_Inscri_Code DATE NOT NULL,
Nb_Passage_Code int DEFAULT 0,
Resultat ENUM ('oui', 'non'),
IdC int not null, FOREIGN KEY (IdC) REFERENCES Client (IdC)
)
ENGINE = InnoDB;

CREATE TABLE Moniteur (
NumM int(2) NOT NULL Auto_Increment Primary key,
NomM varchar(25) not null,
PrenomM varchar(25) not null
)
ENGINE = InnoDB;

CREATE TABLE Voiture (
IdV int(3) not null Auto_Increment Primary Key,
Immatriculation varchar(15) not null,
Modele varchar(20) ,
DateAchat DATETIME not null,
NbrKm int DEFAULT '0',
Conso DECIMAL (2,2)
)
Engine = InnoDB;

CREATE TABLE Lecon (
IdNumLE int(3) not null AUTO_INCREMENT Primary key,
NomLE varchar(20),
TypeLE int,
NumM int(11) not null, Foreign Key (NumM) References Moniteur (NumM),
IdC int not null, Foreign Key (IdC) References Client (IdC),
IdV int(3) not null, FOREIGN KEY (IdV) REFERENCES Voiture (IdV)
)
ENGINE = InnoDB;

CREATE TABLE Planning (
IdNumLE int(3), Foreign Key (IdNumLE) References Lecon (IdNumLE),
NumM int(2), Foreign Key (NumM) References Moniteur (NumM),
D_Debut DATETIME not null,
D_Fin DATETIME not null
)
Engine = InnoDB;

CREATE TABLE utiliservoiture (
IdV int(3) NOT NULL,
Mois DATE not null,
Primary Key (IdV, Mois), FOREIGN KEY (Idv) REFERENCES voiture (IdV)
)
ENGINE = InnoDB;

CREATE TABLE Etablissement (
IdEta int(3) not null Auto_Increment Primary key,
NomEta varchar(30),
AdresseEta varchar(128),
CpET char(5) NOT NULL,
VilleET varchar (20) NOT NULL
)
Engine = InnoDB;

CREATE TABLE Etudiant (
IdC int not null auto_increment PRIMARY KEY,
NomE varchar(25) NOT NULL,
PrenomE varchar (25) NOT NULL,
AdresseE varchar(128) NOT NULL,
CpE char(5) NOT NULL,
VilleE varchar (20) NOT NULL,
DateNaiss DATE,
Telephone char(10) NOT NULL,
nom_formation varchar (30),
taux_reduction int(3) DEFAULT 15,
IdEta int(3) not null, FOREIGN KEY (IdEta) REFERENCES Etablissement ( IdETA)
)
Engine = InnoDB;

CREATE TABLE Salarie (
IdC int not null auto_increment PRIMARY KEY,
NomS varchar(25) NOT NULL,
PrenomS varchar (25) NOT NULL,
AdresseS varchar(128) NOT NULL,
CpS char(5) NOT NULL,
VilleS varchar (20) NOT NULL,
DateNaiss DATE,
Telephone char(10) NOT NULL,
nom_entreprise varchar(30) default 'SansEmploi'
)
Engine = InnoDB;
