import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.event.ListSelectionListener;


public class Lecon 
{
	private LinkedList<Moniteur> LesMoniteurs;
	private LinkedList<Voiture> LesVoitures;
	
	public Lecon()
	{
		this.LesMoniteurs = new LinkedList<Moniteur>();
		this.LesVoitures = new LinkedList<Voiture>();
	}
	
	/********* Definition des m�thodes pour le moniteur ***********/
	public void chargerMoniteurs()
	{
		String requete = "Select * from moniteur;";
		this.LesMoniteurs.clear();
		
		try
		{
			BDD uneBDD = new BDD();
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
			System.out.println("Erreur d'execution de la requ�te"+requete);
		}
	}
	
	public Moniteur selectUnMoniteur(int id)
		{
			String requete =" Select * From moniteur where idmm"+id+";";
			
			try
			{
				BDD uneBDD = new BDD();
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
				System.out.println("Erreur d'�x�cution de la requete:"+requete);
			}
			return null;
		}

		public void insererMoniteur(Moniteur unMon)
		{
			String requete ="insert into moniteur (nomm, prenomm) values ('";
			requete += unMon.getNomm()+"', '"+unMon.getPrenomm()+"');";
				try
				{
					BDD uneBDD = new BDD ();
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
		
		
		
		public void modifierMoniteur(Moniteur unMon)
		{
			String requete ="Update moniteur set nomm='"+unMon.getNomm()+"', prenomm='"+unMon.getPrenomm()+"'WHERE idm="+unMon.getIdm()+";";
				try
				{
					BDD uneBDD = new BDD ();
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
		
		public static void supprimerMoniteur (int code)
		{
			String requete ="";
				try
				{
					BDD uneBDD = new BDD ();
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
		
		public LinkedList<Moniteur> getLesMoniteurs(){
	        return this.LesMoniteurs;
		}
		
		/********* Definition des m�thodes pour la Voiture ***********/
		public void chargerVoitures()
		{
			String requete ="Select * from voiture;";
			this.LesVoitures.clear();
			
			try
			{
				BDD uneBDD = new BDD();
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				
				while (unRes.next())
				{
					int idv = unRes.getInt("idv");
					String immatriculation = unRes.getString("immatriculation");
					String modele = unRes.getString("modele");
					String dateachat = unRes.getString("dateachat");
					float nbrkm = unRes.getFloat("nbrkm");
					float conso = unRes.getFloat("conso");
					
					Voiture uneVoiture = new Voiture(idv, immatriculation, modele, dateachat, nbrkm, conso);
					this.LesVoitures.add(uneVoiture);
				}
				unRes.close();
				unStat.close();
				uneBDD.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur d'execution de la requ�te"+requete);
			}
		}
		
		public void insererVoiture(Voiture uneVoit)
		{
			String requete ="insert into voiture (immatriculation, modele, dateachat, nbrkm, conso) values ('";
			requete += uneVoit.getImmatriculation()+"', '"+uneVoit.getModele()+"', '"+uneVoit.getDateachat()+"', "+uneVoit.getNbrkm()+", "+uneVoit.getConso()+");";
				try
				{
					BDD uneBDD = new BDD ();
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
		
		public void modifierVoiture(Voiture uneVoit)
		{
			String requete ="Update voiture set immatriculation='"+uneVoit.getImmatriculation()+"', modele='"+uneVoit.getModele()+"', dateachat='"+uneVoit.getDateachat()+"', nbrkm="+uneVoit.getNbrkm()+", conso="+uneVoit.getConso()+"WHERE idv="+uneVoit.getIdv()+";";
			
			
	
				try
				{
					BDD uneBDD = new BDD ();
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
		
		public static void supprimerVoiture (int code)
		{
			String requete ="";
				try
				{
					BDD uneBDD = new BDD ();
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
			
		public LinkedList<Voiture> getLesVoitures(){
	        return this.LesVoitures;
		}
			
}