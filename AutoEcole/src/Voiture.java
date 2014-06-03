public class Voiture {
	
	protected String immatriculation, modele, dateachat;
	protected Object idv;
	protected float conso, nbrkm;
	
	public Voiture ()
	{
		//initialise les attibuts de la classe aux valeurs par défaut
		this.idv = 0;
		this.immatriculation="";
		this.modele="";
		this.dateachat="";
		this.nbrkm = 0;
		this.conso = 0;
	}

	public Voiture (Object idv, String immatriculation, String modele, String dateachat, float nbrkm, float conso){
		//initialise les attributs de la classe avec les valeurs en arguments
		this.idv = idv;
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.dateachat = dateachat;
		this.nbrkm = nbrkm;
		this.conso = conso;
	}
	
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getDateachat() {
		return dateachat;
	} 

	public void setDateachat(String dateachat) {
		this.dateachat = dateachat;
	}

	public Object getIdv() {
		return idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public float getNbrkm() {
		return nbrkm;
	}

	public void setNbrkm(float nbrkm) {
		this.nbrkm = nbrkm;
	}

	public float getConso() {
		return conso;
	}

	public void setConso(float conso) {
		this.conso = conso;
	}
}