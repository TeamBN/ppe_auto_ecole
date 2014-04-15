/* Création Table ArchiveVoiture et Création Procédure Stockée remplissant ArchiveV avec les données supprimées de old.voiture
*/
drop table ArchiveV;
CREATE Table ArchiveV 
AS Select *, curdate() date_archive
FROM voiture
WHERE 2=0;

drop procedure histovoiture;
DELIMITER //
CREATE Procedure HistoVoiture ()
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
