import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener, ItemListener
{	
		
	/*Le serialVersionUID est une version universelle identifiant pour une classe Serializable*/
	private static final long serialVersionUID = 1L;
	
	/**********************************************************/
	private Lecon uneLecon = new Lecon();
	
	
	/***************** Barre de Menu **************************/
	private JMenuBar BarreMenu = new JMenuBar();
	private JMenu MenuClient = new JMenu("Client");
	private JMenu MenuMoniteur = new JMenu("Moniteur");
	private JMenu MenuVoiture = new JMenu("Voiture");
	private JMenu MenuPlanning = new JMenu("Planning");
	private JMenu MenuProgramme = new JMenu ("Programme");
	private JMenu MenuProgrammee = new JMenu ("Programme");
	
	/* Sous menu Client */
	private JMenuItem MlisteClient = new JMenuItem("Liste");
	private JMenuItem MajouterClient = new JMenuItem("Ajouter");
	
	/* Sous menu Moniteur */
	private JMenuItem MlisteMoniteur = new JMenuItem("Liste");
	private JMenuItem MajouterMoniteur = new JMenuItem("Ajouter");
	
	/* Sous menu Voiture */
	private JMenuItem MlisteVoiture = new JMenuItem("Liste");
	private JMenuItem MajouterVoiture = new JMenuItem("Ajouter");
	
	/* Sous menu Programme */
	private JMenuItem Mquitter = new JMenuItem ("Quitter");
	private JMenuItem Mapropos = new JMenuItem ("À propos");
	
	
	/****************************** Panel **********************************/

	/************************ Les Panels Moniteur ************************/
	/* Panel liste de Moniteur = LSTM */
	private JPanel PListem = new JPanel();
	private JLabel LtitrepnlLSTM = new JLabel ("Liste Moniteur");
    private String titreTableM[] = new String  [3];
    private JTable uneTableMon;
    
	/* Panel d'ajout de Moniteur = AM*/
    private JPanel PAjoutm = new JPanel();
    private JLabel LtitrepnlAM = new JLabel("Moniteur");
    private JLabel LnomAM = new JLabel("Nom : ");
    private JLabel LprenomAM = new JLabel("Prenom: ");
    private JTextField TnomAM = new JTextField();
    private JTextField TprenomAM = new JTextField();
    private JButton BannulerAM = new JButton("Annuler");
    private JButton BenregistrerAM = new JButton("Enregistrer");

    
    /* Panel modification Moniteur = MM */
    private JPanel PModifm = new JPanel();
    private JLabel LtitrepnlModifm = new JLabel ("Modification Moniteur");
    private JLabel LnomMM = new JLabel("Nom :");
    private JLabel LprenomMM = new JLabel("Prenom :");
    private JTextField TnomMM = new JTextField();
    private JTextField TprenomMM = new JTextField();
    private JButton BannulerMM = new JButton("Annuler");
    private JButton BModifierMM = new JButton("Modifier");
    
    /************************ Les Panels Voiture  ************************/
    
    /* Panel liste de Voiture = LSTV */
	private JPanel PListev = new JPanel();
	private JLabel LtitrepnlLSTV = new JLabel ("Liste Voiture");
    private String titreTableV[] = new String  [6];
    private JTable uneTableVoit;
    
	/* Panel d'ajout de Voiture = AV*/
    private JPanel PAjoutv = new JPanel();
    private JLabel LtitrepnlAV = new JLabel("Voiture");
    private JLabel LimmatriculationAV = new JLabel("Immatriculation : ");
    private JLabel LmodeleAV = new JLabel("Modele : ");
    private JLabel LdateachatAV = new JLabel("Date Achat : ");
    private JLabel LformatdateAV = new JLabel("AAAA-MM-JJ");
    private JLabel LnbrkmAV = new JLabel("Kilometrage : ");
    private JLabel LconsoAV = new JLabel("Consommation : ");
    private JLabel LconsoAV100 = new JLabel("L/100");
    private JTextField TimmatriculationAV = new JTextField();
    private JTextField TmodeleAV = new JTextField();
    private JTextField TdateachatAV = new JTextField();
    private JTextField TnbrkmAV = new JTextField();
    private JTextField TconsoAV = new JTextField();
    private JButton BannulerAV = new JButton("Annuler");
    private JButton BenregistrerAV = new JButton("Enregistrer");

    
    /* Panel modification Voiture = MV */
    private JPanel Pmodifv = new JPanel();
    private JLabel LtitrepnlModifv = new JLabel ("Modification Voiture");
    private JLabel LmodeleMV = new JLabel("Modele : ");
    private JLabel LdateachatMV = new JLabel("Date Achat : ");
    private JLabel LnbrkmMV = new JLabel("Kilometrage : ");
    private JLabel LconsoMV = new JLabel("Consommation : ");
    private JLabel LconsoMV100 = new JLabel("L/100");
    private JTextField TimmatriculationMV = new JTextField();
    private JTextField TmodeleMV = new JTextField();
    private JTextField TdateachatMV = new JTextField();
    private JTextField TnbrkmMV = new JTextField();
    private JTextField TconsoMV = new JTextField();
    private JButton BannulerMV = new JButton("Annuler");
    private JButton BenregistrerMV = new JButton("Enregistrer");
    
	public Fenetre()
	{
		/* Definition des colonnes des Tableaux des listes clients, moniteurs et voitures */
		
		// Tableau Moniteur
		titreTableM[0]="ID";
    	titreTableM[1]="Nom";
    	titreTableM[2]="Prenom";
    	
    	// Tableau Voiture
    	titreTableV[0]="ID";
    	titreTableV[1]="Immatriculation";
    	titreTableV[2]="Modele";
    	titreTableV[3]="Achat";
    	titreTableV[4]="Kilometrage";
    	titreTableV[5]="Conso L/100";
    	
		// Definition du titre pour la fenêtre
		this.setTitle("Auto-Ecole Castellane");
		// Desactivation du Layout
		this.getContentPane().setLayout(null);
		// Rendre la fenêtre visible
		this.setVisible(true);
		// Definition de la taille de la fenêtre
		this.setBounds(0, 0, 550, 400);
		// Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/************************ Les Panels Moniteur ************************/
		/* Panel Liste Moniteur */
		PListem.setBounds(0, 0, 550, 400);
		PListem.setLayout(null);
		PListem.setVisible(false);
		LtitrepnlLSTM.setBounds(230, 5, 100, 30 );
		PListem.add(LtitrepnlLSTM);
		
		
		/* Panel Ajout Moniteur */
		  PAjoutm.setBounds(50, 0, 300, 300);
	      PAjoutm.setLayout(null);
	      PAjoutm.setVisible(false);
	      LtitrepnlAM.setBounds(120, 20, 200, 20);
	      PAjoutm.add(LtitrepnlAM);

	      LnomAM.setBounds(20, 70, 100, 20);
	      PAjoutm.add(LnomAM);
	      TnomAM.setBounds(120, 70, 100, 20);
	      PAjoutm.add(TnomAM);

	      LprenomAM.setBounds(20, 100, 100, 20);
	      PAjoutm.add(LprenomAM);
	      TprenomAM.setBounds(120, 100, 100, 20);
	      PAjoutm.add(TprenomAM);

	      BannulerAM.setBounds(20, 180, 100, 20);
	      PAjoutm.add(BannulerAM);
	      BenregistrerAM.setBounds(160, 180, 100, 20);
	      PAjoutm.add(BenregistrerAM);
	      
	    /* Panel Modification Moniteur */
		  PModifm.setBounds(50, 0, 300, 300);
		  PModifm.setLayout(null);
		  PModifm.setVisible(false);
	      LtitrepnlModifm.setBounds(100, 20, 200, 20);
	      PModifm.add(LtitrepnlModifm);

	      LnomMM.setBounds(20, 70, 100, 20);
	      PModifm.add(LnomMM);
	      TnomMM.setBounds(120, 70, 100, 20);
	      PModifm.add(TnomMM);

	      LprenomMM.setBounds(20, 100, 100, 20);
	      PModifm.add(LprenomMM);
	      TprenomMM.setBounds(120, 100, 100, 20);
	      PModifm.add(TprenomMM);

	      BannulerMM.setBounds(20, 180, 100, 20);
	      PModifm.add(BannulerMM);
	      BModifierMM.setBounds(160, 180, 100, 20);
	      PModifm.add(BModifierMM);
	      
	      /************************ Les Panels Voiture ************************/
	      
	      /* Panel Liste Voiture */
			PListev.setBounds(0, 0, 550, 400);
			PListev.setLayout(null);
			PListev.setVisible(false);
			LtitrepnlLSTV.setBounds(230, 5, 100, 30 );
			PListev.add(LtitrepnlLSTV);
			
		/* Panel Ajout Voiture */
			
			PAjoutv.setBounds(20, 0, 400, 400);
			PAjoutv.setLayout(null);
			PAjoutv.setVisible(false);
			LtitrepnlAV.setBounds(145, 10, 100, 30 );
			PAjoutv.add(LtitrepnlAV);
			
			LimmatriculationAV.setBounds(20, 70, 100, 20);
		    PAjoutv.add(LimmatriculationAV);
		    TimmatriculationAV.setBounds(120, 70, 100, 20);
		    PAjoutv.add(TimmatriculationAV);

		    LmodeleAV.setBounds(20, 100, 100, 20);
		    PAjoutv.add(LmodeleAV);
		    TmodeleAV.setBounds(120, 100, 100, 20);
		    PAjoutv.add(TmodeleAV);
		    
		    LdateachatAV.setBounds(20, 130, 100, 20);
		    PAjoutv.add(LdateachatAV);
		    TdateachatAV.setBounds(120, 130, 100, 20);
		    PAjoutv.add(TdateachatAV);
		    LformatdateAV.setBounds(230, 130, 100, 20);
		    PAjoutv.add(LformatdateAV);
		    
		    LnbrkmAV.setBounds(20, 160, 100, 20);
		    PAjoutv.add(LnbrkmAV);
		    TnbrkmAV.setBounds(120, 160, 100, 20);
		    PAjoutv.add(TnbrkmAV);
		    
		    LconsoAV.setBounds(20, 190, 100, 20);
		    PAjoutv.add(LconsoAV);
		    TconsoAV.setBounds(120, 190, 100, 20);
		    PAjoutv.add(TconsoAV);
		    LconsoAV100.setBounds(230, 190, 100, 20);
		    PAjoutv.add(LconsoAV100);
		    
		    BannulerAV.setBounds(40 ,250, 100, 20);
		    PAjoutv.add(BannulerAV);
		    BenregistrerAV.setBounds(190, 250, 100, 20);
		    PAjoutv.add(BenregistrerAV);
	      
	      
	    /* Ajout des panel sur le GetContent */
	      
		/************************ Les Panels Moniteur ************************/
		  getContentPane().add(PListem);
		  getContentPane().add(PAjoutm);
		  getContentPane().add(PModifm);
	      
	    /************************ Les Panels Voitures ************************/
	      getContentPane().add(PListev);
		  getContentPane().add(PAjoutv);
	      
	     /********* Rendre les boutons cliquables ****************************/
	      
	      /* Bouton Barre de sous Menu Moniteur */
		  MlisteMoniteur.addActionListener(this);
	      MajouterMoniteur.addActionListener(this);
	      
	      /* Bouton Barre de sous Menu Voiture */
	      MlisteVoiture.addActionListener(this);
	      MajouterVoiture.addActionListener(this);
	      
	      /* Bouton Barre de sous Menu Programme */
	      Mquitter.addActionListener(this);
	      
	      /***************** Bouton Panel Moniteur ******************************/
	     
	      /* Bouton Panel Ajout Moniteur */
	      BenregistrerAM.addActionListener(this);
	      BannulerAM.addActionListener(this);
	     
	      /* Bouton Panel Modification Moniteur*/
	      BModifierMM.addActionListener(this);
	      BannulerMM.addActionListener(this);
	      
	      /***************** Bouton Panel Voiture ******************************/
	      
	      /* Bouton Panel Ajout Voiture */
	      BenregistrerAV.addActionListener(this);
	      BannulerAV.addActionListener(this);
	      
	     /********** Barre de Menu ***************/
	     /* Ajout des différents menus dans la barre de menu */
	      BarreMenu.add(MenuClient);
	      BarreMenu.add(MenuMoniteur);
	      BarreMenu.add(MenuVoiture);
	      BarreMenu.add(MenuPlanning);
	      BarreMenu.add(MenuProgramme);
	     
	     /************ Ajout des sous-menus des menus *******************/
	     // Item du menu Client
	     this.MenuClient.add(MlisteClient);
	     this.MenuClient.add(MajouterClient);
	     
	     // Item du menu Moniteur
	     this.MenuMoniteur.add(MlisteMoniteur);
	     this.MenuMoniteur.add(MajouterMoniteur);
	     
	     // Item du Menu Voiture
	     this.MenuVoiture.add(MlisteVoiture);
	     this.MenuVoiture.add(MajouterVoiture);
	     
	     // Item du Menu Programme
	     this.MenuProgramme.add(Mquitter);
	     this.MenuProgramme.add(Mapropos);
	    
	     /* Ajout de la barre de menu sur la fenêtre */
	     this.setJMenuBar(BarreMenu);
	          
	} 
	
	/********** Lier les boutons à une action bien définie ************/
	public void actionPerformed (ActionEvent ev)
	{
		Object ae = ev.getSource();
		if(ae == Mquitter)
		{
			System.out.println("Fin du programme");
			System.exit(0);
		} 
		else if (ae == MajouterMoniteur)
		{
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(true);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(false);
			PAjoutv.setVisible(false);
			
		}
		else if (ae == MlisteMoniteur)
		{
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(false);
			PAjoutv.setVisible(false);
			
			uneLecon.chargerMoniteurs();
			
			Object [][] donnees= this.listeMoniteurs();
			uneTableMon = new JTable(donnees, titreTableM);
			JScrollPane uneScrollm = new JScrollPane(uneTableMon);
			 
			uneScrollm.setBounds(20, 50, 500, 250);
            PListem.validate();
            PListem.add(uneScrollm);
            
             
            //action sur la liste des moniteurs
             uneTableMon.addMouseListener(new MouseAdapter() {
   	    	 public void mouseClicked(MouseEvent e)
   	    	 {
   	            
   	    	   if (e.getClickCount() == 1) 
   	    	   { 
   	    		
      	    		JOptionPane.showMessageDialog (getParent(), "Modification", "Moniteur", JOptionPane.INFORMATION_MESSAGE);
      	    		
      	    		
      	    	
      	    		/* Panel Moniteur */
      				PListem.setVisible(false);
      				PAjoutm.setVisible(false);
      				PModifm.setVisible(true);
      				
      				/* Panel Voiture */
      				PAjoutv.setVisible(false);
      	    	 	
      	    	
      	    	 	// On declare un entier "ligne" qui retourne la ligne selectionné dans la table
      	    	 	int ligne=0;
      	    	 	ligne = uneTableMon.getSelectedRow();
   	    	       
   	    	   }
   	    	   else 
   	    	    {
   	    	    	 
   	    	    	 	
   	    	    }
   	    	   }
   	    	});
   	    
		}
		else if (ae == BenregistrerAM)
		{
			this.insererMoniteur();
			this.TnomAM.setText("");
			this.TprenomAM.setText("");
		}
		else if(ae == BModifierMM)
		{
			this.modifierMoniteur();
			
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PAjoutv.setVisible(false);
			
			this.TnomMM.setText("");
			this.TprenomMM.setText("");
			

		}
		else if (ae == BannulerAM)
		{
			this.annulerMoniteur();
			
			this.TnomAM.setText("");
			this.TprenomAM.setText("");
		}
		else if (ae == BannulerMM)
		{
			this.annulerMoniteur();
			
			this.TnomMM.setText("");
			this.TprenomMM.setText("");
		}
		else if (ae == MajouterVoiture)
		{	
			/* Panel Moniteur */
			PListem.setVisible(false);
			PModifm.setVisible(false);
			PAjoutm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(false);
			PAjoutv.setVisible(true);
		}
		else if (ae == MlisteVoiture)
		{
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			
			uneLecon.chargerVoitures();
			
			Object [][] donnees= this.listeVoitures();
			uneTableVoit = new JTable(donnees, titreTableV);
			JScrollPane uneScrollv = new JScrollPane(uneTableVoit);
			 
			uneScrollv.setBounds(20, 50, 500, 250);
            PListev.validate();
            PListev.add(uneScrollv);
   	 
		}
		else if (ae == BenregistrerAV)
		{
			this.insererVoiture();
			this.TimmatriculationAV.setText("");
			this.TmodeleAV.setText("");
			this.TdateachatAV.setText("");
			this.TnbrkmAV.setText("");
			this.TconsoAV.setText("");
		}	
	}
	
	/********** Methodes Moniteur ********************/
	public Object [][] listeMoniteurs()
	{
    	Object [] [] tab = new Object [this.uneLecon.getLesMoniteurs().size()] [3];
    	for (int i=0; i<this.uneLecon.getLesMoniteurs().size(); i++)
    	{
    		tab[i] [0] = ""+ this.uneLecon.getLesMoniteurs().get(i).getIdm();
    		tab[i] [1] = ""+ this.uneLecon.getLesMoniteurs().get(i).getNomm();
    		tab[i] [2] = ""+ this.uneLecon.getLesMoniteurs().get(i).getPrenomm();
   
    	}
    	return tab;
    }
	
	public void insererMoniteur ()
	    {
	        try{
	            String nomm = TnomAM.getText();
	            String prenomm = TprenomAM.getText();
	            Moniteur unMoniteur = new Moniteur(0, nomm, prenomm);
	            uneLecon.insererMoniteur(unMoniteur);
	            JOptionPane.showMessageDialog(this, "Insertion Effectuée", "Insertion", JOptionPane.INFORMATION_MESSAGE);
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	    }
	
	public void modifierMoniteur ()
    {
		int ligne = uneTableMon.getSelectedRow()+1;
        try{
        	int idmm = ligne;
            String nomm = TnomMM.getText();
            String prenomm = TprenomMM.getText();
            Moniteur unMoniteur = new Moniteur(idmm, nomm, prenomm);
            uneLecon.modifierMoniteur(unMoniteur);
            JOptionPane.showMessageDialog(this, "Modification Effectué", "Insertion", JOptionPane.INFORMATION_MESSAGE);
        }
       catch (NumberFormatException exp){
           JOptionPane.showMessageDialog(this, "Modification échouée", "Erreur", JOptionPane.ERROR_MESSAGE);
       }
    }
	
	 public void annulerMoniteur ()
	 {

		 PListem.setVisible(true);
		 PAjoutm.setVisible(false);
	     PModifm.setVisible(false);
	 }
	 
	 /********** Methodes Voiture ********************/
		public Object [][] listeVoitures()
		{
	    	Object [] [] tab = new Object [this.uneLecon.getLesVoitures().size()] [6];
	    	for (int i=0; i<this.uneLecon.getLesVoitures().size(); i++)
	    	{
	    		tab[i] [0] = ""+ this.uneLecon.getLesVoitures().get(i).getIdv();
	    		tab[i] [1] = ""+ this.uneLecon.getLesVoitures().get(i).getImmatriculation();
	    		tab[i] [2] = ""+ this.uneLecon.getLesVoitures().get(i).getModele();
	    		tab[i] [3] = ""+ this.uneLecon.getLesVoitures().get(i).getDateachat();
	    		tab[i] [4] = ""+ this.uneLecon.getLesVoitures().get(i).getNbrkm();
	    		tab[i] [5] = ""+ this.uneLecon.getLesVoitures().get(i).getConso();
	   
	    	}
	    	return tab;
	    }
		public void insererVoiture ()
	    {
	        try{
	            String immatriculation = TimmatriculationAV.getText();
	            String modele = TmodeleAV.getText();
	            String dateachat = TdateachatAV.getText();
	            float nbrkm = Float.parseFloat(TnbrkmAV.getText());
	            float conso = Float.parseFloat(TconsoAV.getText());
	            
	            
	            Voiture uneVoiture = new Voiture(0, immatriculation, modele, dateachat, nbrkm, conso);
	            uneLecon.insererVoiture(uneVoiture);
	            JOptionPane.showMessageDialog(this, "Insertion Effectuée", "Insertion", JOptionPane.INFORMATION_MESSAGE);
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	    }
	 
	public void itemStateChanged(ItemEvent arg0) 
	{ //TODO
		
	}

	public Lecon getUneLecon() {
		return uneLecon;
	}

	public void setUneLecon(Lecon uneLecon) {
		this.uneLecon = uneLecon;
	}
}