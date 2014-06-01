
public class Client {
	
	protected int idc;
	protected String login, mdp;
	
	public Client ()
	{
		this.idc = 0;
		this.login="";
		this.mdp="";
	}

	public Client (int idc, String login, String mdp)
	{
		this.idc = idc;
		this.login = login;
		this.mdp = mdp;
		
	}

	public int getIdc() {
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
