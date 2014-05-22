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
	private JMenuItem MmodifierClient = new JMenuItem("Modifier");
	private JMenuItem MsuppressionClient = new JMenuItem("Suppression");
	
	/* Sous menu Moniteur */
	private JMenuItem MlisteMoniteur = new JMenuItem("Liste");
	private JMenuItem MajouterMoniteur = new JMenuItem("Ajouter");
	private JMenuItem MsuppressionMoniteur = new JMenuItem("Suppression");
	
	/* Sous menu Voiture */
	private JMenuItem MlisteVoiture = new JMenuItem("Liste");
	private JMenuItem MajouterVoiture = new JMenuItem("Ajouter");
	private JMenuItem MmodifierVoiture = new JMenuItem("Modifier");
	private JMenuItem MsuppressionVoiture = new JMenuItem("Suppression");
	
	/* Sous menu Programme */
	private JMenuItem Mquitter = new JMenuItem ("Quitter");
	private JMenuItem Mapropos = new JMenuItem ("À propos");
	
	
	/****************************** Panel **********************************/

	/* Panel liste de Moniteur =LSTM */
	private JPanel PListem = new JPanel();
	private JLabel LtitrepnlLSTM = new JLabel ("Liste Moniteur");
    private String titreTable[] = new String  [3];
    private JTable uneTable;
	
	/* Panel d'ajout de Moniteur = AM*/
    private JPanel PAjoutm = new JPanel();
    private JLabel LtitrepnlAM = new JLabel("Moniteur");
    private JLabel LnomAM = new JLabel("Nom : ");
    private JLabel LprenomAM = new JLabel("Prenom: ");
    private JTextField TnomAM = new JTextField();
    private JTextField TprenomAM = new JTextField();
    private JButton Bannuler = new JButton("Annuler");
    private JButton Benregistrer = new JButton("Enregistrer");

    
    /* Panel modification Moniteur = MM */
    private JPanel Pmodifm = new JPanel();
    private JLabel LtitrepnlModifm = new JLabel ("Modification Moniteur");
    private JLabel TnomMM = new JLabel();
    private JLabel TprenomMM = new JLabel();
    private JTextField LnomMM = new JTextField();
    private JTextField LprenomMM = new JTextField();
    
	public Fenetre()
	{
		titreTable[0]="Idm";
    	titreTable[1]="Nomm";
    	titreTable[2]="Prenomm";
    	
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
		PListem.setBounds(20, 30, 300, 300);
		PListem.setLayout(null);
		PListem.setVisible(false);
		LtitrepnlLSTM.setBounds(140, 0, 100, 30 );
		PListem.add(LtitrepnlLSTM);
		
		
		/* Panel Ajout Moniteur */
		  PAjoutm.setBounds(110, 50, 300, 300);
	      PAjoutm.setLayout(null);
	      PAjoutm.setVisible(false);
	      LtitrepnlAM.setBounds(140, 10, 200, 20);
	      PAjoutm.add(LtitrepnlAM);
	      
	      LnomAM.setBounds(10, 40, 100, 20);
	      PAjoutm.add(LnomAM);
	      TnomAM.setBounds(120, 40, 100, 20);
	      PAjoutm.add(TnomAM);
	        
	      LprenomAM.setBounds(10, 70, 100, 20);
	      PAjoutm.add(LprenomAM);
	      TprenomAM.setBounds(120, 70, 100, 20);
	      PAjoutm.add(TprenomAM);
	        
	      Bannuler.setBounds(50, 180, 100, 20);
	      PAjoutm.add(Bannuler);
	      Benregistrer.setBounds(160, 180, 100, 20);
	      PAjoutm.add(Benregistrer);
	      
	      /* Panel Modifier Moniteur */
	      Pmodifm.setBounds(110, 50, 300, 300);
	      Pmodifm.setLayout(null);
	      Pmodifm.setVisible(false);
	      LtitrepnlModifm.setBounds(140, 10, 200, 20);
	      Pmodifm.add(LtitrepnlModifm);
	      
	      /* Ajout des panel sur le GetContent */
	      getContentPane().add(PAjoutm);
	      getContentPane().add(PListem);
	      getContentPane().add(Pmodifm);
	      
	     // Rendre les boutons cliquables
	     Bannuler.addActionListener(this);
	     Benregistrer.addActionListener(this);
	     MajouterMoniteur.addActionListener(this);
	     Mquitter.addActionListener(this);
	     MlisteMoniteur.addActionListener(this);
	      
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
	     this.MenuClient.add(MmodifierClient);
	     this.MenuClient.add(MsuppressionClient);
	     
	     // Item du menu Moniteur
	     this.MenuMoniteur.add(MlisteMoniteur);
	     this.MenuMoniteur.add(MajouterMoniteur);
	     this.MenuMoniteur.add(MsuppressionMoniteur);
	     
	     // Item du Menu Voiture
	     this.MenuVoiture.add(MlisteVoiture);
	     this.MenuVoiture.add(MajouterVoiture);
	     this.MenuVoiture.add(MmodifierVoiture);
	     this.MenuVoiture.add(MsuppressionVoiture);
	     
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
			PListem.setVisible(false);
			PAjoutm.setVisible(true);
			
		}
		else if (ae == MlisteMoniteur)
		{
			PListem.setVisible(true);
			PAjoutm.setVisible(false);
			uneLecon.chargerMoniteurs();
			
			Object [][] donnees= this.listeMoniteurs();
			uneTable = new JTable(donnees, titreTable);
			JScrollPane uneScroll = new JScrollPane(uneTable);
			 
			uneScroll.setBounds(10, 25, 280, 250);
            PListem.validate();
            PListem.add(uneScroll);
             
           /*//action sur la liste des moniteurs
             uneTable.addMouseListener(new MouseAdapter() {
   	    	 public void mouseClicked(MouseEvent e)
   	    	 {
   	    	   if (e.getClickCount() == 1) 
   	    	   { // check if a double click
   	    	       JOptionPane.showMessageDialog (getParent(), "test", "essai"+uneTable.getSelectedColumn(), JOptionPane.INFORMATION_MESSAGE);
   	    	       
   	    	       PListem.setVisible(false);
   	    	       Pmodifm.setVisible(true);
   	    	   }
   	    	   else 
   	    	    {
   	    	    	 JOptionPane.showMessageDialog (getParent(), "else", "essai"+uneTable.getSelectedColumn(), JOptionPane.INFORMATION_MESSAGE);
   	    	     
   	    	    }
   	    	   }
   	    	});*/
   	    
		}
		else if (ae == Benregistrer)
		{
			this.insererMoniteur();
			this.TnomAM.setText("");
			this.TprenomAM.setText("");
		}
		else if (ae == MsuppressionMoniteur)
		{
			PListem.setVisible(false);
			PAjoutm.setVisible(false);
		}
		else if (ae == Bannuler)
		{
			this.annulerMoniteur();
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
	
	 public void annulerMoniteur ()
	 {
	        this.TnomAM.setText("");
	        this.TprenomAM.setText("");
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