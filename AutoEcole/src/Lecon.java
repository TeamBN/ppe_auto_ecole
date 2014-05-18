import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.event.ListSelectionListener;


public class Lecon 
{
	private LinkedList<Moniteur> LesMoniteurs;
	
	public Lecon()
	{
		this.LesMoniteurs = new LinkedList<Moniteur>();
	}
	
	public void chargerMoniteurs()
	{
		String requete = "Select * from moniteur;";
		this.LesMoniteurs.clear();
		
		try
		{
			BDD uneBDD = new BDD("localhost","root","","autoecole");
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			
			while (unRes.next())
			{
				int idm = unRes.getInt("idm");
				String nomm = unRes.getString("nomm");
				String prenomm = unRes.getString("prenomm");
				Moniteur unMoniteur = new Moniteur(idm, prenomm, nomm);
				this.LesMoniteurs.add(unMoniteur);
			}
			unRes.close();
			unStat.close();
			uneBDD.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur d'execution de la requête"+requete);
		}
	}
	
	public Moniteur selectUnMoniteur(int id)
		{
			String requete =" Select * From moniteur where idmm"+id+";";
			
			try
			{
				BDD uneBDD = new BDD("localhost","root","","autoecole");
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				
				if (unRes.next())
				{
					int idm = unRes.getInt("idm");
					String nomm = unRes.getString("nomm");
					String prenomm = unRes.getString("prenomm");
					Moniteur unMoniteur = new Moniteur(idm, prenomm, nomm);
					return unMoniteur;
				}
				unRes.close();
				unStat.close();
				uneBDD.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur d'éxécution de la requete:"+requete);
			}
			return null;
		}

		public void insererMoniteur(Moniteur unMon)
		{
			String requete ="insert into moniteur (nomm, prenomm) values ('";
			requete += unMon.getNomm()+"', '"+unMon.getPrenomm()+"');";
				try
				{
					BDD uneBDD = new BDD ("localhost", "root","","autoecole");
					uneBDD.seConnecter();
					Statement unStat = uneBDD.getMaConnexion().createStatement();
					unStat.execute(requete);
					unStat.close();
					uneBDD.seConnecter();
				}
				catch(SQLException exp)
				{
					System.out.println("Erreur d'execution de la requete:" + requete);
				}
		}
		
		public void afficherLesMoniteurs ()
		{
			for (int i = 0; i<this.LesMoniteurs.size(); i++)
			{
				System.out.println("-----------------------");
			this.LesMoniteurs.get(i).afficher();
			}
		}
		
		public LinkedList<Moniteur> getLesMoniteurs(){
	        return this.LesMoniteurs;
		}
}