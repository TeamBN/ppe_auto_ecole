
public class Moniteur {
	
	private Object idm;
	private String nomm;
	private String prenomm;
	
	public Moniteur()
	{
		this.idm = 0;
		this.nomm ="";
		this.prenomm="";
	}

	public Moniteur(Object idm, String nomm, String prenomm )
	{
		this.idm = idm;
		this.nomm = nomm;
		this.prenomm = prenomm;
	}
	
	public String getNomm() {
		return nomm;
	}

	public Object getIdm() {
		return idm;
	}

	public void setIdm(Object idm) {
		this.idm = idm;
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