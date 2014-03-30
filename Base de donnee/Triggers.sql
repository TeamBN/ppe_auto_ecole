--------------------------------------------Table ExamenP---------------------------------------------

-- Vérifier l'obtention du code dans les 24 dernier mois avant le passage du permis, et la majorité du client.
drop trigger IF EXISTS Before_Insert_Permis;
DELIMITER //
CREATE TRIGGER Before_Insert_Permis
BEFORE INSERT ON ExamenP
FOR EACH ROW 
BEGIN
	DECLARE nb int;
	DECLARE msg varchar(100);
	SELECT COUNT(*) INTO nb FROM ExamenC WHERE IdC=new.IdC AND Resultat='oui';
	IF nb = 0 
			THEN SET msg = 'Le client n''est pas titulaire du code de la route';
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 
	ELSEIF DATE_ADD((SELECT DateNaiss FROM Etudiant WHERE Etudiant.IdC=Last_Insert_ID()), INTERVAL 18 YEAR) < (SELECT Date_Inscri_Permis FROM ExamenP WHERE IdC=Last_Insert_Id())
			THEN SET msg = 'L''etudiant ne sera pas majeur au moment du passage du permis';
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 
	ELSEIF DATE_ADD((SELECT DateNaiss FROM Salarie WHERE Salarie.IdC=Last_Insert_ID()), INTERVAL 18 YEAR) < ( SELECT Date_Inscri_Permis FROM ExamenP WHERE IdC=Last_Insert_Id())
			THEN SET msg = 'Le salarie ne sera pas majeur au moment du passage du permis';
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 
	ELSEIF DATE_ADD((SELECT Date_Inscri_Code FROM ExamenC WHERE ExamenC.IdC=Last_Insert_ID() AND ResultatC = 'oui' ), INTERVAL 2 YEAR) > ( SELECT Date_Inscri_Permis FROM ExamenP WHERE IdC=Last_Insert_Id())
			THEN SET msg = 'Le code aura expire a cette date, il faut le repasser avant de passer le permis';
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 

	END IF;
	
	END //
	
	DELIMITER ;
	
	--Rajouter +1 au nombre de passage de permis si un client est déjà sur la table, sinon 1.
	DECLARE nbexp int;
	SELECT COUNT(*) INTO nbexp FROM ExamenP WHERE new.IdC=IdC;
	IF nbexp <> 0 
	THEN
		SET new.Nb_Passage_Permis = nbexp + 1;
	ELSE
		SET new.Nb_Passage_Permis = 1;
	
	END IF;
	
END //

DELIMITER;

--------------------------------------------Table ExamenC---------------------------------------------

--Rajouter +1 au nombre de passage de code si un client est déjà sur la table, sinon 1.

DROP TRIGGER Before_Insert_ExamenC;
DELIMITER //
CREATE TRIGGER Before_Insert_ExamenC
BEFORE INSERT ON ExamenC
FOR EACH ROW
BEGIN

	DECLARE nbexc int;
	SELECT COUNT(*) INTO nbexc FROM ExamenC WHERE new.IdC=IdC;
	IF nbexc <> 0 
	THEN
		SET new.Nb_Passage_Code = nbexc + 1;
	ELSE
		SET new.Nb_Passage_Code = 1;
	
	END IF;
END //

DELIMITER;
	
	
	 
--------------------------------------------Table Etudiant---------------------------------------------

-- Héritage Client / Etudiant, empêcher l'insert si client déjà salarié
-- Empêcher de recréer un même étudiant avec un id différent (qu'il soit créé en Salarié ou Etudiant)
-- Si l'étudiant a plus de 25 ans, pas de taux de réduction.
drop trigger Verif_Etu;
DELIMITER // 

CREATE TRIGGER Verif_Etu 
BEFORE INSERT ON Etudiant
FOR EACH ROW
BEGIN

DECLARE nbe int;

SELECT MAX(IdC) into nbe FROM Client;

IF nbe is NULL 
THEN
SET nbe=0;
end if;

IF new.IdC = 0 
then 
	SET new.IdC = nbe + 1;
END IF;



SELECT COUNT(IdC) into nbe
FROM Client
WHERE IdC=new.IdC;

IF nbe = 0
	then 
			INSERT INTO Client(IdC) VALUES (new.IdC);
END IF;



SELECT COUNT(IdC) INTO nbe FROM Salarie WHERE IdC=new.IdC;

if nbe > 0

THEN 
	DELETE FROM Etudiant WHERE 2=0;

end if;

	IF YEAR(CURDATE() - YEAR(new.Datenaiss) > 25)
	THEN 
		SET new.taux_reduction=0;
	
	END IF;
	
SELECT COUNT(*) INTO nbe FROM Etudiant WHERE new.NomE=NomE
		AND new.PrenomE=PrenomE AND new.AdresseE=AdresseE AND new.CpE=CpE
		AND new.VilleE=VilleE AND new.DateNaiss=DateNaiss
		AND new.Telephone=Telephone AND new.nom_formation=nom_formation
		AND new.NomEta=NomEta;
		
	IF nbe > 0 
		THEN	
		DELETE FROM Etudiant WHERE 2=0;
		
	END IF;



SELECT COUNT(*) INTO nbe FROM Salarie WHERE new.NomE=Salarie.NomS
		AND new.PrenomE=Salarie.PrenomS AND new.AdresseE=Salarie.AdresseS
		AND new.CpE=Salarie.CpS AND new.VilleE=Salarie.VilleS AND new.DateNaiss=Salarie.DateNaiss
		AND new.Telephone=Salarie.Telephone;
		
	IF nbe > 0 
		THEN	
		DELETE FROM Etudiant WHERE 2=0;
		
	END IF;
	

END //
	
DELIMITER ;
	




-- Suppression dans etudiant = suppression dans client

DROP TRIGGER After_Delete_Etudiant;
DELIMITER //
CREATE TRIGGER After_Delete_Etudiant
AFTER DELETE ON Etudiant
FOR EACH ROW
BEGIN 

	DELETE FROM Client Where Client.IdC = old.IdC;
	
END //
DELIMITER ;



-- Modification dans etudiant = modification dans client









--------------------------------------------Table Salarié---------------------------------------------

-- Héritage Client / Salarié, empêcher l'insert si client déjà étudiant 
-- Vérifie l'existance du client dans la table salarié et étudiant afin de ne pas le dupliquer

DROP TRIGGER Verif_Salarie;
DELIMITER // 
CREATE TRIGGER Verif_Salarie 
BEFORE INSERT ON Salarie
FOR EACH ROW
BEGIN

DECLARE nbs int;

SELECT MAX(IdC) into nbs FROM Client;

	IF nbs is NULL 
		THEN
			SET nbs=0;
	END IF;

	IF new.IdC = 0 
		THEN
			SET new.IdC = nbs + 1;
	END IF;


SELECT COUNT(IdC) into nbs FROM Client WHERE IdC=new.IdC;

	IF nbs = 0
		THEN 
			INSERT INTO Client(IdC) VALUES (new.IdC);
	END IF;



SELECT COUNT(IdC) INTO nbs FROM Etudiant WHERE IdC=new.IdC;

	IF nbs > 0
		THEN 
			DELETE FROM Salarie WHERE 2=0;

	END IF;


	
SELECT COUNT(*) INTO nbs FROM Salarie WHERE new.NomS=NomS
		AND new.PrenomS=PrenomS AND new.AdresseS=AdresseS AND new.CpS=CpS
		AND new.VilleS=VilleS AND new.DateNaiss=DateNaiss
		AND new.Telephone=Telephone AND new.nom_entreprise=nom_entreprise;
		
	IF nbs > 0 
		THEN	
			DELETE FROM Salarie WHERE 2=0;
		
	END IF;



SELECT COUNT(*) INTO nbs FROM Etudiant WHERE new.NomS=Etudiant.NomE
		AND new.PrenomS=Etudiant.PrenomE AND new.AdresseS=Etudiant.AdresseE
		AND new.CpS=Etudiant.CpE AND new.VilleS=Etudiant.VilleE AND new.DateNaiss=Etudiant.DateNaiss
		AND new.Telephone=Etudiant.Telephone;
		
	IF nbs > 0 
		THEN	
			DELETE FROM Salarie WHERE 2=0;
		
	END IF;
	

END //
	
DELIMITER ;
	






-- Suppression dans salarié = suppression dans client

DROP TRIGGER After_Delete_Salarie;
DELIMITER //
CREATE TRIGGER After_Delete_Salarie
AFTER DELETE ON Salarie
FOR EACH ROW
BEGIN 

	DELETE FROM Client Where Client.IdC = old.IdC;
	
END //
DELIMITER ;



-- Modification dans salarié = modification dans client

DROP TRIGGER After_Update_Salarie;
DELIMITER //
CREATE TRIGGER After_Update_Salarie
AFTER UPDATE ON Salarie
FOR EACH ROW
BEGIN

	UPDATE client SET Client.IdC = new.IdC where Client.IdC=old.IdC;
	
END //
Delimiter ;






--------------------------------------------Table Planning---------------------------------------------
-- Un moniteur ne peut donner une









---------------------------------------------Client---------------------------------------

-- INVERSE HERITAGE 
drop trigger Herit_Client;
DELIMITER //
CREATE TRIGGER Herit_Client
AFTER DELETE ON Client
FOR EACH ROW
BEGIN

DECLARE nbc int;
DECLARE nbc2 int;

SELECT COUNT(*) INTO nbc FROM Salarie WHERE old.IdC=IdC;
SELECT COUNT(*) INTO nbc2 FROM Etudiant WHERE old.IdC=IdC;

IF nbc > 0 or nbc2 > 0
	THEN
		IF nbc > 0
			THEN
				DELETE FROM Salarie WHERE old.IdC=IdC;
		ELSE
				DELETE FROM Etudiant WHERE old.IdC=IdC;
		END IF;
		
ELSE		
	INSERT INTO Archives_Client VALUES(old.IdC);
END IF;

END //

DELIMITER ;