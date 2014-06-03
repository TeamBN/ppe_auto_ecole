
import java.awt.Component;
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
import javax.imageio.*;
import javax.swing.*;

import java.awt.Toolkit;

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
	
	/* Image des sous Menus */
	
	private Icon ImgMlisteClient = new ImageIcon(this.getClass().getResource("\\images\\icones\\listeclient.png"));
	private Icon ImgMajouterClient = new ImageIcon(this.getClass().getResource("\\images\\icones\\ajoutclient.png"));
	
	private Icon ImgMlisteMoniteur = new ImageIcon(this.getClass().getResource("\\images\\icones\\listemoniteur.png"));
	private Icon ImgMajouterMoniteur = new ImageIcon(this.getClass().getResource("\\images\\icones\\ajoutmoniteur.png"));
	
	private Icon ImgMlisteVoiture = new ImageIcon(this.getClass().getResource("\\images\\icones\\listevoiture.png"));
	private Icon ImgMajouterVoiture = new ImageIcon(this.getClass().getResource("\\images\\icones\\ajoutvoiture.png"));
	
	private Icon ImgMquitter = new ImageIcon(this.getClass().getResource("\\images\\icones\\quitter.png"));
	private Icon ImgMapropos = new ImageIcon(this.getClass().getResource("\\images\\icones\\information.png"));
	
	/* Image des Boutons */
	
	private Icon imgBannulerAM = new ImageIcon(this.getClass().getResource("\\images\\icones\\annuler.png"));
	private Icon imgBenregistrerAM = new ImageIcon(this.getClass().getResource("\\images\\icones\\valider.png"));
	
	private Icon imgBannulerMM = new ImageIcon(this.getClass().getResource("\\images\\icones\\edit_annuler.png"));
	private Icon imgBsuppressionMM = new ImageIcon(this.getClass().getResource("\\images\\icones\\delete.png"));
	private Icon imgMBmodifierMM = new ImageIcon(this.getClass().getResource("\\images\\icones\\edit_valider.png"));
	
	private Icon imgBannulerAV = new ImageIcon(this.getClass().getResource("\\images\\icones\\annuler.png"));
	private Icon imgBenregistrerAV = new ImageIcon(this.getClass().getResource("\\images\\icones\\valider.png"));
	
	private Icon imgBannulerMV = new ImageIcon(this.getClass().getResource("\\images\\icones\\edit_annuler.png"));
	private Icon imgBsuppressionMV = new ImageIcon(this.getClass().getResource("\\images\\icones\\delete.png"));
	private Icon imgMBmodifierMV = new ImageIcon(this.getClass().getResource("\\images\\icones\\edit_valider.png"));
	
	
	
	
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
    private JButton BsuppressionMM = new JButton("Suppression");
    
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
    private JPanel PModifv = new JPanel();
    private JLabel LtitrepnlModifv = new JLabel ("Modification Voiture");
    private JLabel LimmatriculationMV = new JLabel("Immatriculation : ");
    private JLabel LmodeleMV = new JLabel("Modele : ");
    private JLabel LdateachatMV = new JLabel("Date Achat : ");
    private JLabel LformatdateMV = new JLabel("AAAA-MM-JJ");
    private JLabel LnbrkmMV = new JLabel("Kilometrage : ");
    private JLabel LconsoMV = new JLabel("Consommation : ");
    private JLabel LconsoMV100 = new JLabel("L/100");
    private JTextField TimmatriculationMV = new JTextField();
    private JTextField TmodeleMV = new JTextField();
    private JTextField TdateachatMV = new JTextField();
    private JTextField TnbrkmMV = new JTextField();
    private JTextField TconsoMV = new JTextField();
    private JButton BannulerMV = new JButton("Annuler");
    private JButton BModifierMV = new JButton("Modifier");
    private JButton BsuppressionMV = new JButton("Suppression");
 
    
	public Fenetre()
	{
		setIconImage(new ImageIcon(this.getClass().getResource("images\\favicon.png")).getImage());
		
		/** Definition des colonnes des Tableaux des listes clients, moniteurs et voitures **/
		
		// Tableau Moniteurs
		titreTableM[0]="ID";
    	titreTableM[1]="Nom";
    	titreTableM[2]="Prenom";
    	
    	// Tableau Voitures
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
		// Fenêtre non redimensionnable
		this.setResizable(false);
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
		  PAjoutm.setBounds(0, 0, 550, 400);
	      PAjoutm.setLayout(null);
	      PAjoutm.setVisible(false);
	      LtitrepnlAM.setBounds(230, 5, 100, 30);
	      PAjoutm.add(LtitrepnlAM);

	      LnomAM.setBounds(150, 70, 100, 20);
	      PAjoutm.add(LnomAM);
	      TnomAM.setBounds(250, 70, 100, 20);
	      PAjoutm.add(TnomAM);

	      LprenomAM.setBounds(150, 100, 100, 20);
	      PAjoutm.add(LprenomAM);
	      TprenomAM.setBounds(250, 100, 100, 20);
	      PAjoutm.add(TprenomAM);

	      BannulerAM.setBounds(140, 180, 100, 20);
	      PAjoutm.add(BannulerAM);
	      BenregistrerAM.setBounds(300, 180, 105, 20);
	      PAjoutm.add(BenregistrerAM);
	      
	    /* Panel Modification Moniteur */
		  PModifm.setBounds(0, 0, 550, 400);
		  PModifm.setLayout(null);
		  PModifm.setVisible(false);
	      LtitrepnlModifm.setBounds(230, 10, 150, 30);
	      PModifm.add(LtitrepnlModifm);

	      LnomMM.setBounds(150, 70, 100, 20);
	      PModifm.add(LnomMM);
	      TnomMM.setBounds(250, 70, 100, 20);
	      PModifm.add(TnomMM);

	      LprenomMM.setBounds(150, 100, 100, 20);
	      PModifm.add(LprenomMM);
	      TprenomMM.setBounds(250, 100, 100, 20);
	      PModifm.add(TprenomMM);

	      BannulerMM.setBounds(140, 180, 100, 20);
	      PModifm.add(BannulerMM);
	      BModifierMM.setBounds(300, 180, 100, 20);
	      PModifm.add(BModifierMM);
	      BsuppressionMM.setBounds(10 ,320, 110, 20);
	      PModifm.add(BsuppressionMM);
	      
	      /************************ Les Panels Voiture ************************/
	      
	      /* Panel Liste Voiture */
			PListev.setBounds(0, 0, 550, 400);
			PListev.setLayout(null);
			PListev.setVisible(false);
			LtitrepnlLSTV.setBounds(230, 5, 100, 30 );
			PListev.add(LtitrepnlLSTV);
			
		/* Panel Ajout Voiture */
			
			PAjoutv.setBounds(20, 0, 550, 400);
			PAjoutv.setLayout(null);
			PAjoutv.setVisible(false);
			LtitrepnlAV.setBounds(230, 10, 150, 30 );
			PAjoutv.add(LtitrepnlAV);
			
			LimmatriculationAV.setBounds(120, 70, 100, 20);
		    PAjoutv.add(LimmatriculationAV);
		    TimmatriculationAV.setBounds(250, 70, 100, 20);
		    PAjoutv.add(TimmatriculationAV);

		    LmodeleAV.setBounds(120, 100, 100, 20);
		    PAjoutv.add(LmodeleAV);
		    TmodeleAV.setBounds(250, 100, 100, 20);
		    PAjoutv.add(TmodeleAV);
		    
		    LdateachatAV.setBounds(120, 130, 100, 20);
		    PAjoutv.add(LdateachatAV);
		    TdateachatAV.setBounds(250, 130, 100, 20);
		    PAjoutv.add(TdateachatAV);
		    LformatdateAV.setBounds(360, 130, 100, 20);
		    PAjoutv.add(LformatdateAV);
		    
		    LnbrkmAV.setBounds(120, 160, 100, 20);
		    PAjoutv.add(LnbrkmAV);
		    TnbrkmAV.setBounds(250, 160, 100, 20);
		    PAjoutv.add(TnbrkmAV);
		    
		    LconsoAV.setBounds(120, 190, 100, 20);
		    PAjoutv.add(LconsoAV);
		    TconsoAV.setBounds(250, 190, 100, 20);
		    PAjoutv.add(TconsoAV);
		    LconsoAV100.setBounds(360, 190, 100, 20);
		    PAjoutv.add(LconsoAV100);
		    
		    BannulerAV.setBounds(120 ,250, 100, 20);
		    PAjoutv.add(BannulerAV);
		    BenregistrerAV.setBounds(300, 250, 105, 20);
		    PAjoutv.add(BenregistrerAV);
		    
		  /* Panel Modification Voiture */
			PModifv.setBounds(0, 0, 550, 400);
			PModifv.setLayout(null);
			PModifv.setVisible(false);
		    LtitrepnlModifv.setBounds(230, 10, 150, 30);
		    PModifv.add(LtitrepnlModifv);

		    LimmatriculationMV.setBounds(120, 70, 100, 20);
		    PModifv.add(LimmatriculationMV);
			TimmatriculationMV.setBounds(250, 70, 100, 20);
			PModifv.add(TimmatriculationMV);

			LmodeleMV.setBounds(120, 100, 100, 20);
			PModifv.add(LmodeleMV);
			TmodeleMV.setBounds(250, 100, 100, 20);
			PModifv.add(TmodeleMV);
			    
			LdateachatMV.setBounds(120, 130, 100, 20);
			PModifv.add(LdateachatMV);
			TdateachatMV.setBounds(250, 130, 100, 20);
			PModifv.add(TdateachatMV);
			LformatdateMV.setBounds(360, 130, 100, 20);
			PModifv.add(LformatdateMV);
			    
			LnbrkmMV.setBounds(120, 160, 100, 20);
			PModifv.add(LnbrkmMV);
			TnbrkmMV.setBounds(250, 160, 100, 20);
			PModifv.add(TnbrkmMV);
			    
			LconsoMV.setBounds(120, 190, 100, 20);
			PModifv.add(LconsoMV);
			TconsoMV.setBounds(250, 190, 100, 20);
			PModifv.add(TconsoMV);
			LconsoMV100.setBounds(360, 190, 100, 20);
			PModifv.add(LconsoMV100);
			    
			BannulerMV.setBounds(120 ,250, 100, 20);
			PModifv.add(BannulerMV);
			BModifierMV.setBounds(300, 250, 100, 20);
			PModifv.add(BModifierMV);
			BsuppressionMV.setBounds(10 ,320, 110, 20);
		    PModifv.add(BsuppressionMV);
	      
	      
	    /* Ajout des panel sur le GetContent */
	      
		  getContentPane().add(PListem);
		  getContentPane().add(PAjoutm);
		  getContentPane().add(PModifm);
	     
	      getContentPane().add(PListev);
		  getContentPane().add(PAjoutv);
		  getContentPane().add(PModifv);
		  
		  /* Ajout d'image dans les Menus Items */
		  MlisteClient.setIcon(ImgMlisteClient);
		  MajouterClient.setIcon(ImgMajouterClient);
		  
		  MlisteMoniteur.setIcon(ImgMlisteMoniteur);
		  MajouterMoniteur.setIcon(ImgMajouterMoniteur);
		  
		  MlisteVoiture.setIcon(ImgMlisteVoiture);
		  MajouterVoiture.setIcon(ImgMajouterVoiture);
		  
		  Mquitter.setIcon(ImgMquitter);
		  Mapropos.setIcon(ImgMapropos);
		  
		  /* Ajout d'image sur les boutons */
		  
		  BannulerAM.setIcon(imgBannulerAM);
		  BenregistrerAM.setIcon(imgBenregistrerAM);
		  
		  BannulerMM.setIcon(imgBannulerMM);
		  BModifierMM.setIcon(imgMBmodifierMM);
		  BsuppressionMM.setIcon(imgBsuppressionMM);
		  
		  BannulerAV.setIcon(imgBannulerAV);
		  BenregistrerAV.setIcon(imgBenregistrerAV);
		  
		  BannulerMV.setIcon(imgBannulerMV);
		  BModifierMV.setIcon(imgMBmodifierMV);
		  BsuppressionMV.setIcon(imgBsuppressionMV);
		  
		  
		  /* Bouton Barre Menu */

		  MlisteClient.addActionListener(this);
		  MajouterClient.addActionListener(this);
		  
		  MlisteMoniteur.addActionListener(this);
		  MajouterMoniteur.addActionListener(this);
		  
		  MlisteVoiture.addActionListener(this);
	      MajouterVoiture.addActionListener(this);
	      
	      Mquitter.addActionListener(this);
	      Mapropos.addActionListener(this);
	      
	      /* Bouton Panel Ajout Moniteur */
	      BenregistrerAM.addActionListener(this);
	      BannulerAM.addActionListener(this);
	     
	      /* Bouton Panel Modification Moniteur*/
	      BModifierMM.addActionListener(this);
	      BannulerMM.addActionListener(this);
	      BsuppressionMM.addActionListener(this);
	      
	      /* Bouton Panel Ajout Voiture */
	      BenregistrerAV.addActionListener(this);
	      BannulerAV.addActionListener(this);
	      
	      /* Bouton Panel Modification Voiture */
	      BModifierMV.addActionListener(this);
	      BannulerMV.addActionListener(this);
	      BsuppressionMV.addActionListener(this);
	      
	      
	      BarreMenu.add(MenuClient);
	      BarreMenu.add(MenuMoniteur);
	      BarreMenu.add(MenuVoiture);
	      BarreMenu.add(MenuPlanning);
	      BarreMenu.add(MenuProgramme);
	
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
		else if (ae == MlisteMoniteur)
		{
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(false);
			PAjoutv.setVisible(false);
            
			chargerTableauMon();
             
            
   	    
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
		else if (ae == BenregistrerAM)
		{
			
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(false);
			PAjoutv.setVisible(false);
			
			this.insererMoniteur();
			
		}
		else if (ae == BannulerAM)
		{
			this.annulerMoniteur();
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
		else if (ae == BannulerMM)
		{
			this.annulerMoniteur();
			
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			
			this.TnomMM.setText("");
			this.TprenomMM.setText("");
		}
		else if (ae == BsuppressionMM)
		{
			this.supprimerMoniteur();
			
			/* Panel Moniteur */
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			this.TnomMM.setText("");
			this.TprenomMM.setText("");
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
			
			chargerTableauVoit();
   	 
		}
		else if (ae == MajouterVoiture)
		{	
			/* Panel Moniteur */
				PListem.setVisible(false);
				PAjoutm.setVisible(false);
				PModifm.setVisible(false);
				
				/* Panel Voiture */
				PListev.setVisible(false);
				PAjoutv.setVisible(true);
				PModifv.setVisible(false);
		}
		else if (ae == BenregistrerAV)
		{
			this.insererVoiture();
			
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			PModifv.setVisible(false);
			
			this.TimmatriculationAV.setText("");
			this.TmodeleAV.setText("");
			this.TdateachatAV.setText("");
			this.TnbrkmAV.setText("");
			this.TconsoAV.setText("");
		}
		else if (ae == BannulerAV)
		{
			this.annulerMoniteur();
			
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			PModifv.setVisible(false);
			
			this.TimmatriculationAV.setText("");
			this.TmodeleAV.setText("");
			this.TdateachatAV.setText("");
			this.TnbrkmAV.setText("");
			this.TconsoAV.setText("");
			
		}
		else if(ae == BModifierMV)
		{
			this.modifierVoiture();
			
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
				
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			PModifv.setVisible(false);
			
			this.TimmatriculationMV.setText("");
			this.TmodeleMV.setText("");
			this.TdateachatMV.setText("");
			this.TnbrkmMV.setText("");
			this.TconsoMV.setText("");
		}
		else if (ae == BannulerMV)
		{
			this.annulerVoiture();
			
			/* Panel Moniteur */
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
			PModifm.setVisible(false);
			
			/* Panel Voiture */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			PModifv.setVisible(false);
			
			this.TimmatriculationMV.setText("");
			this.TmodeleMV.setText("");
			this.TdateachatMV.setText("");
			this.TnbrkmMV.setText("");
			this.TconsoMV.setText("");
		}
		else if (ae == BsuppressionMV)
		{
			this.supprimerVoiture();
			
			/* Panel Moniteur */
			PListev.setVisible(true);
			PAjoutv.setVisible(false);
			PModifv.setVisible(false);
			
			this.TimmatriculationMV.setText("");
			this.TmodeleMV.setText("");
			this.TdateachatMV.setText("");
			this.TnbrkmMV.setText("");
			this.TconsoMV.setText("");
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
	
	public void chargerTableauMon()
	{
		uneLecon.chargerMoniteurs();
		
		Object [][] donnees= this.listeMoniteurs();
		uneTableMon = new JTable(donnees, titreTableM);
		JScrollPane uneScrollm = new JScrollPane(uneTableMon);
		 
		uneScrollm.setBounds(20, 50, 500, 250);
		PListem.removeAll();
        PListem.add(uneScrollm);
        
      //action sur la liste des moniteurs
        uneTableMon.addMouseListener(new MouseAdapter() {
	    	 public void mouseClicked(MouseEvent e)
	    	 {
	            
	    	   if (e.getClickCount() == 1) 
	    	   { 
	    		
	    		   JOptionPane.showMessageDialog (getParent(), "Modification", "Voiture", JOptionPane.INFORMATION_MESSAGE);
	    		   
	    		TnomMM.setText(""+uneTableMon.getValueAt(uneTableMon.getSelectedRow(),1));
	   			TprenomMM.setText(""+uneTableMon.getValueAt(uneTableMon.getSelectedRow(),2));
	    		
 	    		/* Panel Moniteur */
 				PListem.setVisible(false);
 				PAjoutm.setVisible(false);
 				PModifm.setVisible(true);
 				
	    	   }
	    	   else 
	    	    {
	    	    	 
	    	    	 	
	    	    }
	    	   }
	    	});
		
	}
	
	public void insererMoniteur ()
	    {
	        try{
	            String nomm = TnomAM.getText();
	            String prenomm = TprenomAM.getText();
	            Moniteur unMoniteur = new Moniteur(0, nomm, prenomm);
	            uneLecon.insererMoniteur(unMoniteur);
	            JOptionPane.showMessageDialog(this, "Insertion Effectuée", "Insertion", JOptionPane.INFORMATION_MESSAGE);
	            
	            chargerTableauMon();
	            
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	    }
	
	
	public void modifierMoniteur ()
    {
        try{
        	
        	Object idm = uneTableMon.getValueAt(uneTableMon.getSelectedRow(),0);
            String nomm = TnomMM.getText();
            String prenomm = TprenomMM.getText();
            Moniteur unMoniteur = new Moniteur(idm, nomm, prenomm);
            uneLecon.modifierMoniteur(unMoniteur);
            JOptionPane.showMessageDialog(this, "Modification Effectué", "Insertion", JOptionPane.INFORMATION_MESSAGE);

            chargerTableauMon();
        }
       catch (NumberFormatException exp){
           JOptionPane.showMessageDialog(this, "Modification échouée", "Erreur", JOptionPane.ERROR_MESSAGE);
       }
    }
	
	public void supprimerMoniteur()
    {
        try{
        	Object idMon = uneTableMon.getValueAt(uneTableMon.getSelectedRow(),0);
            if(uneLecon.supprimerMoniteur(idMon) )
            {
                JOptionPane.showMessageDialog(this, "Suppression Effectuée", "Suppression", JOptionPane.OK_OPTION);
            }
            else {
                JOptionPane.showMessageDialog(this, "Suppression impossible", "Suppression", JOptionPane.OK_OPTION);
            } 
        }
       catch (NumberFormatException exp){
           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.OK_OPTION);
       }
        
        chargerTableauMon();
    }
	
	public void annulerMoniteur ()
	 {
		 chargerTableauMon();

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
		
		public void chargerTableauVoit()
		{
			uneLecon.chargerVoitures();
			
			Object [][] donnees= this.listeVoitures();
			uneTableVoit = new JTable(donnees, titreTableV);
			JScrollPane uneScrollv = new JScrollPane(uneTableVoit);
			 
			uneScrollv.setBounds(20, 50, 500, 250);
			PListev.removeAll();
	        PListev.add(uneScrollv);
	        
	      //action sur la liste des voitures
            uneTableVoit.addMouseListener(new MouseAdapter() {
  	    	 public void mouseClicked(MouseEvent e)
  	    	 {
  	            
  	    	   if (e.getClickCount() == 1) 
  	    	   { 
  	    		
     	    		JOptionPane.showMessageDialog (getParent(), "Modification", "Voiture", JOptionPane.INFORMATION_MESSAGE);
     	    		
     	    		TimmatriculationMV.setText(""+uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),1));
     				TmodeleMV.setText(""+uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),2));
     				TdateachatMV.setText(""+uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),3));
     				TnbrkmMV.setText(""+uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),4));
     				TconsoMV.setText(""+uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),5));
     	    		
     	    		
     	    	
     	    		/* Panel Moniteur */
     				PListem.setVisible(false);
     				PAjoutm.setVisible(false);
     				PModifm.setVisible(false);
     				
     				/* Panel Voiture */
     				PListev.setVisible(false);
     				PAjoutv.setVisible(false);
     				PModifv.setVisible(true);
     				
  	    	   }
  	    	   else 
  	    	    {
  	    	    	 
  	    	    	 	
  	    	    }
  	    	   }
  	    	});
			
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
	            
	            chargerTableauVoit();
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	    }
	 
		public void modifierVoiture ()
	    {
	        try{
	        	
	        	 Object idv = uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),0);
	        	 String immatriculation = TimmatriculationMV.getText();
		         String modele = TmodeleMV.getText();
		         String dateachat = TdateachatMV.getText();
		         float nbrkm = Float.parseFloat(TnbrkmMV.getText());
		         float conso = Float.parseFloat(TconsoMV.getText());
		         
		         Voiture uneVoiture = new Voiture(idv, immatriculation, modele, dateachat, nbrkm, conso);
		         uneLecon.modifierVoiture(uneVoiture);
		         JOptionPane.showMessageDialog(this, "Modification Effectué", "Insertion", JOptionPane.INFORMATION_MESSAGE);
		         
		         chargerTableauVoit();
	            
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Modification échouée", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	    }
		
		public void supprimerVoiture()
	    {
	        try{
	        	Object idVoit = uneTableVoit.getValueAt(uneTableVoit.getSelectedRow(),0);
	            if(uneLecon.supprimerVoiture(idVoit) )
	            {
	                JOptionPane.showMessageDialog(this, "Suppression Effectuée", "Suppression", JOptionPane.OK_OPTION);
	            }
	            else {
	                JOptionPane.showMessageDialog(this, "Suppression impossible", "Suppression", JOptionPane.OK_OPTION);
	            } 
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.OK_OPTION);
	       }
	        
	        chargerTableauVoit();
	    }
		
		public void annulerVoiture ()
		 {
			 chargerTableauVoit();

			 PListev.setVisible(true);
			 PAjoutv.setVisible(false);
		     PModifv.setVisible(false);
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