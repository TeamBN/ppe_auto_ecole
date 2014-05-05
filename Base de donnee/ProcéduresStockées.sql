/* Création Table ArchiveVoiture et Création Procédure Stockée remplissant ArchiveV avec les données supprimées de old.voiture
*/
drop table ArchiveV;
CREATE Table ArchiveV 
AS Select *, curdate() date_archive
FROM voiture
WHERE 2=0;

drop procedure histovoiture;
DELIMITER //
CREATE Procedure HistoVoiture()
BEGIN 
DECLARE fini int default 0;
DECLARE HIdV int(3);
DECLARE HImmatriculation varchar(15);
DECLARE HModele varchar(20);
DECLARE HDateAchat DATE;
DECLARE HNbrKm float (12,2) DEFAULT '0';
Declare HConso DECIMAL (10,2);
DECLARE CV cursor for select * 
	from voiture
	where datediff(curdate(), DateAchat)/365 > 2 AND NbrKm > 50000;
	
DECLARE Continue HANDLER for not found set fini=1;
OPEN CV;
Fetch CV INTO HIdV, HImmatriculation, HModele, HDateAchat, HNbrKm, HConso;
WHILE Fini <> 1 
DO 
INSERT INTO ArchiveV VALUES (
HIdV, HImmatriculation, HModele, HDateAchat, HNbrKm, HConso, curdate());
Fetch CV INTO HIdV, HImmatriculation, HModele, HDateAchat, HNbrKm, HConso;
END WHILE;
CLOSE CV;
END //

DELIMITER ;


/* Set Resultat=non sur tous les examens anterieurs aux oui et archiver toutes les informations concernant l'étudiant qui a eu son permis
  nom, prenom, date examen, nombre de passage pour l'avoir */
drop procedure if exists update_archive_etudiant;
DELIMITER //
CREATE Procedure update_archive_etudiant()
BEGIN

DECLARE Fini int default 0;
DECLARE NumC int;
DECLARE NomC, prenomC varchar (25);
DECLARE Date_Obtention_Code DATE;
DECLARE Date_Obtention_Permis DATE;
/*   + les variables nécessaires */

DECLARE CP cursor FOR Select Client.IdC, NomE, PrenomE, Date_Inscri_Permis, Date_Inscri_Code
FROM Client, Etudiant, ExamenP, ExamenC
WHERE Client.IdC = Etudiant.IdC AND ExamenP.IdC=Client.IdC AND ExamenC.IdC=Client.IdC AND ResultatP='oui' AND ResultatC='oui';


DECLARE Continue HANDLER for not found set fini=1; 
/* Declare une variable continue qui va faire executer la procedure jusqu'à ce que fini soit = à 1 sans se préoccuper des erreurs */ 

Open CP;
Fetch CP INTO NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code; 
/* Positionne chaque variable déclarée dans la declaration du CV dans chaque champ NumC,etc... */
	WHILE Fini <> 1 
	DO 
		INSERT INTO ArchiveClient /* nom des tables archives */
		VALUES ( NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code);
	Fetch CP INTO NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code;
	END WHILE;
Close CP;
call update_archive_salarie();
END //
DELIMITER ;

-- même chose pour salarie

drop procedure if exists update_archive_salarie;
DELIMITER //
CREATE Procedure update_archive_salarie()
BEGIN

DECLARE Fini int default 0;
DECLARE NumC int;
DECLARE NomC, prenomC varchar (25);
DECLARE Date_Obtention_Code DATE;
DECLARE Date_Obtention_Permis DATE;
/*   + les variables nécessaires */

DECLARE CP cursor FOR Select Client.IdC, NomS, PrenomS, Date_Inscri_Permis, Date_Inscri_Code
FROM Client, Salarie, ExamenP, ExamenC
WHERE Client.IdC = Salarie.IdC AND ExamenP.IdC=Client.IdC AND ExamenC.IdC=Client.IdC AND ResultatP='oui' AND ResultatC='oui';


DECLARE Continue HANDLER for not found set fini=1; 
/* Declare une variable continue qui va faire executer la procedure jusqu'à ce que fini soit = à 1 sans se préoccuper des erreurs */ 

Open CP;
Fetch CP INTO NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code; 
/* Positionne chaque variable déclarée dans la declaration du CV dans chaque champ NumC,etc... */
	WHILE Fini <> 1 
	DO 
		INSERT INTO ArchiveClient /* nom des tables archives */
		VALUES ( NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code);
	Fetch CP INTO NumC, NomC, PrenomC, Date_Obtention_Permis, Date_Obtention_Code;
	END WHILE;
Close CP;
END //
DELIMITER ;

