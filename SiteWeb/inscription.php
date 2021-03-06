﻿<?php
session_start();
session_destroy();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" >
<html style="height:100%">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<link rel="stylesheet" href="styleinscription.css" />
	<link rel="shortcut icon" href="images/favicon.png" >
	<title>CastellaneAuto - Inscription</title>
</head>

<body>
	<header>
		<div id="bloc_header">
			<a href="accueil.php">
				<img src="images/logo.png" alt="Logo CastellaneAuto" id="logo" border="0"/>
			</a>
			<?php 
				
					require("includes/noob.php");
				
				
			?> 
		</div>
	</header>
	<div id="contenu_body" class="reprezent">
		<?php			
					require("includes/Navquidam.php");
		
		?>
		
		<section id="menu_droite">
			<div id="bloc_noir">			
				<div class="bloc-title">
					<div class="titreBorderG"></div>
					<h3>Les photocopies obligatoires</h3>
					<div class="titreBorderD"></div>
				</div>
				<div class="bloc-content">
					<div class="bloc-content-4col">
						<h2># Pièce d'identité (recto-verso) en cours de validité. <br />
						<br /># Certificat de participation à la JAPD si vous avez entre 18 et 25 ans.<br />
						<br /># ASSR2 ou ASR (né(e) après le 01/01/1988). <br />
						</h2>
					</div>
					
				</div>
				<div class="clear"></div>			
			</div>
			
			<div id="bloc_noir">			
				<div class="bloc-title">
					<div class="titreBorderG"></div>
					<h3>Les autres documents à fournir</h3>
					<div class="titreBorderD"></div>
				</div>
				<div class="bloc-content">
					<div class="bloc-content-4col">
						<h2>
						# 2 à 5 photographies d'identité récentes sur fond blanc, de face, tête nue format 3.5 x 4.5 (non scannées, non découpées)<br />
						<br /># Des enveloppes A4 ou A5 vierges sans adresse <br />
						<br /># Des timbres (tarif en vigueur)<br />
						<br /># Des enveloppes lettre MAX PRIORITAIRE 50 g (courrier suivi)<br />
						</h2>
					</div>
					
				</div>
				<div class="clear"></div>			
			</div>
			
		</section>
		
		
		
		<div class="clear"></div>
	</div>
	<?php include("includes/footer.php"); ?>
</body>
</html>