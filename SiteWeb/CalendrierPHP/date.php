<?php

	ini_set('display_errors', 1);
	error_reporting(E_ALL);		
	
	class Date{
	
	
		var $days = array('Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche');
		var $months = array('Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre');

		public function getEvents($year){
			
			global $bdd;
			$req = $bdd->query("SELECT Titre_lecon, Date_lecon, H_debut, H_fin FROM lecon WHERE YEAR(Date_lecon)='$year' AND IdM=".$_SESSION['IDM']);
			$r = array();
			while($d = $req->fetch(PDO::FETCH_OBJ)){
				$r[strtotime($d->Date_lecon)][$d->H_debut][$d->H_fin] = $d->Titre_lecon;

			}
			return $r;
		}
		
		public function getAll($year){
		
			$r = array();
			
			/*
			$date = strtotime($year.'-01-01');
			
			while(date('Y', $date) <= $year){
				// $r[ANNEE][MOIS][JOUR] = JOUR DE LA SEMAINE			
				
				
				$y = date('Y', $date);
				$m = date('n', $date);
				$d = date('j', $date);
				$w = str_replace('0', '7', date('w', $date));
				$r[$y][$m][$d] = $w;
				$date = strtotime(date('Y-m-d', $date). ' +1 DAY');
				
			}*/
			
			$date = new DateTime($year.'-01-01');
			
			while($date->format('Y') <= $year){		
				
				$y = $date->format('Y');
				$m = $date->format('n');
				$d = $date->format('j');
				$w = str_replace('0', '7', $date->format('w'));
				$r[$y][$m][$d] = $w;
				$date->add(new DateInterval('P1D'));
				
			
			}
			return $r;
		}
		
		
		
	}

?>