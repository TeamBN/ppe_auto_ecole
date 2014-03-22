public class Voiture {
	
	protected String immatriculation, modele, dateachat;
	protected int idv, nbrkm;
	protected float conso;
	
	public Voiture ()
	{
		//initialise les attibuts de la classe aux valeurs par défaut
		this.immatriculation="";
		this.modele="";
		this.dateachat="";
		this.nbrkm = 0;
		this.conso = 0;
	}

	public Voiture (String immatriculation, String modele, String dateachat, int nbrkm, int conso){
		//initialise les attributs de la classe avec les valeurs en arguments
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.dateachat = dateachat;
		this.nbrkm = nbrkm;
		this.conso = conso;
	}
	
	public void saisir(){
		//saisie au clavier de l'ensemble des attibuts
		this.modele = Console.saisirChaine("Saisir la modele");
		this.immatriculation = Console.saisirChaine("Saisir l'immatriculation");
		this.dateachat = Console.saisirChaine("Saisir la date d'achat");
		this.nbrkm = Console.saisirEntier("Saisir le nombre de km");
		this.conso = Console.saisirEntier("Saisir la consommation");
	}
	
	public void afficher(){
		//affiche l'ensemble des attibuts
		System.out.println("le modèle :" +this.modele);
		System.out.println("l'immatriculation :" +this.immatriculation);
		System.out.println("La date d'achat :" +this.dateachat);
		System.out.println("le nombre de km :" +this.nbrkm);
		System.out.println("La consommation :" +this.conso);
	}
	
	public String toString(){
		//retourne les attibuts sous forme d'une chaine
		return this.immatriculation+"|"+this.modele+"|"+this.nbrkm;
	}
}