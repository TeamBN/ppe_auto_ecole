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
import java.util.Vector;

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener, ItemListener
{	
		
	/*Le serialVersionUID est une version universelle identifiant pour une classe Serializable*/
	private static final long serialVersionUID = 1L;
	
	/**********************************************************/
	private Lecon uneLecon = new Lecon();
	
	/**********************************************************/
	
	/***************** Barre de Menu **************************/
	private JMenuBar BarreMenu = new JMenuBar();
	private JMenu MenuClient = new JMenu("Client");
	private JMenu MenuMoniteur = new JMenu("Moniteur");
	private JMenu MenuVoiture = new JMenu("Voiture");
	private JMenu MenuPlanning = new JMenu("Planning");
	private JMenu MenuProgramme = new JMenu ("Programme");
	
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

	/* Panel liste de Moniteur =LSTM */
	private JPanel PListem = new JPanel();
	private JLabel LtitrepnlLSTM = new JLabel ("Liste Moniteur");
    private String titreTableM[] = new String  [3];
    private JTable uneTable;
    
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
    private JPanel Pmodifm = new JPanel();
    private JLabel LtitrepnlModifm = new JLabel ("Modification Moniteur");
    private JLabel LnomMM = new JLabel("Nom :");
    private JLabel LprenomMM = new JLabel("Prenom :");
    private JTextField TnomMM = new JTextField();
    private JTextField TprenomMM = new JTextField();
    private JButton BannulerMM = new JButton("Annuler");
    private JButton BModifierMM = new JButton("Modifier");
    
	public Fenetre()
	{
		titreTableM[0]="ID";
    	titreTableM[1]="Nom";
    	titreTableM[2]="Prenom";
    	
		// Definition du titre pour la fenêtre
		this.setTitle("Auto-Ecole Castellane");
		// Desactivation du Layout
		this.getContentPane().setLayout(null);
		// Rendre la fenêtre visible
		this.setVisible(true);
		// Definition de la taille de la fenêtre
		this.setBounds(100, 100, 400, 400);
		// Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Panel Liste Moniteur */
		PListem.setBounds(20, 30, 400, 400);
		PListem.setLayout(null);
		PListem.setVisible(false);
		LtitrepnlLSTM.setBounds(115, 0, 100, 30 );
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
		  Pmodifm.setBounds(50, 0, 300, 300);
		  Pmodifm.setLayout(null);
		  Pmodifm.setVisible(false);
	      LtitrepnlModifm.setBounds(100, 20, 200, 20);
	      Pmodifm.add(LtitrepnlModifm);

	      LnomMM.setBounds(20, 70, 100, 20);
	      Pmodifm.add(LnomMM);
	      TnomMM.setBounds(120, 70, 100, 20);
	      Pmodifm.add(TnomMM);

	      LprenomMM.setBounds(20, 100, 100, 20);
	      Pmodifm.add(LprenomMM);
	      TprenomMM.setBounds(120, 100, 100, 20);
	      Pmodifm.add(TprenomMM);

	      BannulerMM.setBounds(20, 180, 100, 20);
	      Pmodifm.add(BannulerMM);
	      BModifierMM.setBounds(160, 180, 100, 20);
	      Pmodifm.add(BModifierMM);
	      
	      
	      /* Ajout des panel sur le GetContent */
	      getContentPane().add(PAjoutm);
	      getContentPane().add(PListem);
	      getContentPane().add(Pmodifm);
	      
	     // Rendre les boutons cliquables
	      
	      /* Bouton Barre de Menu */
	      MajouterMoniteur.addActionListener(this);
	      MlisteMoniteur.addActionListener(this);
		  Mquitter.addActionListener(this);
	     
	      /* Bouton Panel Ajout Moniteur */
	      BenregistrerAM.addActionListener(this);
	      BannulerAM.addActionListener(this);
	     
	      /* Bouton Panel Modification Moniteur*/
	      BModifierMM.addActionListener(this);
	      BannulerMM.addActionListener(this);
	      
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
	
	// Lier les boutons à une action bien définie
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
			PAjoutm.setVisible(true);
			PListem.setVisible(false);
			Pmodifm.setVisible(false);
			
		}
		else if (ae == MlisteMoniteur)
		{
			
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			Pmodifm.setVisible(false);
			uneLecon.chargerMoniteurs();
			
			final Object [][] donnees= this.listeMoniteurs();
			uneTable = new JTable(donnees, titreTableM);
			JScrollPane uneScroll = new JScrollPane(uneTable);
			 
			uneScroll.setBounds(10, 25, 280, 250);
            PListem.validate();
            PListem.add(uneScroll);
            
             
            //action sur la liste des moniteurs
             uneTable.addMouseListener(new MouseAdapter() {
   	    	 public void mouseClicked(MouseEvent e)
   	    	 {
   	            
   	    	   if (e.getClickCount() == 1) 
   	    	   { // check if a double click
   	    	
   	    		
      	    		JOptionPane.showMessageDialog (getParent(), "Modification", "Moniteur", JOptionPane.INFORMATION_MESSAGE);
      	    		
   	    	    	
      	    	 	Pmodifm.setVisible(true);
      	    	 	PListem.setVisible(false);
      	    	 	PAjoutm.setVisible(false);
      	    	
      	    	 	//on declare un entier "ligne" qui retourne la ligne selectionné dans la table
      	    	 	int ligne=0;
      	    	 	ligne = uneTable.getSelectedRow();
   	    	       
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
			PListem.setVisible(true);
			Pmodifm.setVisible(false);
			PAjoutm.setVisible(false);
			

		}
		else if (ae == BannulerAM)
		{
			this.annuler();
		}
		else if (ae == BannulerMM)
		{
			this.annuler();
		}
	}
	
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
		int ligne = uneTable.getSelectedRow()+1;
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
	
	 public void annuler ()
	 {

		 PListem.setVisible(true);
		 PAjoutm.setVisible(false);
	     Pmodifm.setVisible(false);
	 }
	 
	public void itemStateChanged(ItemEvent arg0) 
	{
		
	}

	public Lecon getUneLecon() {
		return uneLecon;
	}

	public void setUneLecon(Lecon uneLecon) {
		this.uneLecon = uneLecon;
	}
}