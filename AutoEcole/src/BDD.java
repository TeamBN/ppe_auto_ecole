import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BDD {

	private String serveur, user, mdp, bdd;
	private Connection maConnexion;
	
	public BDD(String serveur, String user, String mdp, String bdd)
	{
		this.serveur = serveur;
		this.user = user;
		this.mdp = mdp;
		this.bdd = bdd;
		
	}
	
	public void chargerPilote()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch (ClassNotFoundException exp)
		{
			System.out.println("erreur de chargement du pilote");
			
		}
	}
	
	public void seConnecter ()
	{
		this.chargerPilote();
		String url = "jdbc:mysql://"+this.serveur + "/" +this.bdd;
		
		try{
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
			
		}
		catch(SQLException exp)
		{
			System.out.println("connexion impossible au serveur"+url);
		}
	}
	
	public void seDeconnecter ()
	{
		if (this.maConnexion!=null)
		{
			
		
		try{
			this.maConnexion.close();
		}
		catch(SQLException exp)
		{
			System.out.println("erreur de fermeture de la connexion");
		}
		}
	}
	
	public Connection getMaConnexion ()
	{
		return this.maConnexion;
		
	}
}
