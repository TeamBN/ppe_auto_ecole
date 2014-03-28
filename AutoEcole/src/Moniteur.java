
public class Moniteur {
	
	private int idm;
	private String nomm;
	private String prenomm;
	
	public Moniteur()
	{
		this.idm = 0;
		this.nomm ="";
		this.prenomm="";
	}

	public Moniteur(int idm, String nomm, String prenomm )
	{
		this.idm = idm;
		this.nomm = nomm;
		this.prenomm = prenomm;
	}
	
	public void saisir()
	{
		this.nomm = Console.saisirChaine("Saisir le nom du Moniteur");
		this.prenomm = Console.saisirChaine("Saisir le prénom du Moniteur");
	}

	public int getIdm() {
		return idm;
	}

	public void setIdm(int idm) {
		this.idm = idm;
	}

	public String getNomm() {
		return nomm;
	}

	public void setNomm(String nomm) {
		this.nomm = nomm;
	}

	public String getPrenomm() {
		return prenomm;
	}

	public void setPrenomm(String prenomm) {
		this.prenomm = prenomm;
	}
}