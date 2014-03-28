import java.util.LinkedList;

import com.mysql.jdbc.Statement;


public class Lecon {
	
	private LinkedList<Moniteur> LesMoniteurs;
	
	public lecon
	{
		this.LesMoniteurs = new LinkedList<Moniteur>();
	}
	
	public void chargerMoniteurs()
	{
		String requete = "Select * from moniteur;";
		this.LesMoniteurs.clear();
		
		try
		{
			BDD uneBDD = new BDD("localhost","root","autoecole")
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			
			while (unRes.next())
			{
				int 
			}
		}
	}

	
}
