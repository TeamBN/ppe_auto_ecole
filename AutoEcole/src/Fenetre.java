import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	/* Barre de Menu */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu MenuClient = new JMenu("Client");
	private JMenu MenuMoniteur = new JMenu("Moniteur");
	private JMenu MenuVoiture = new JMenu("Voiture");
	private JMenu MenuPlanning = new JMenu("Planning");
	
	/* Sous menu Client */
	private JMenuItem MlisteClient = new JMenuItem("Liste");
	private JMenuItem MajouterClient = new JMenuItem("Ajouter");
	private JMenuItem MmodifierClient = new JMenuItem("Modifier");
	private JMenuItem MsuppressionClient = new JMenuItem("Suppression");
	
	/* Sous menu Moniteur */
	private JMenuItem MlisteMoniteur = new JMenuItem("Liste");
	private JMenuItem MajouterMoniteur = new JMenuItem("Ajouter");
	private JMenuItem MmodifierMoniteur = new JMenuItem("Modifier");
	private JMenuItem MsuppressionMoniteur = new JMenuItem("Suppression");
	
	/* Sous menu Voiture */
	private JMenuItem MlisteVoiture = new JMenuItem("Liste");
	private JMenuItem MajouterVoiture = new JMenuItem("Ajouter");
	private JMenuItem MmodifierVoiture = new JMenuItem("Modifier");
	private JMenuItem MsuppressionVoiture = new JMenuItem("Suppression");

	
	/* Panel d'ajout de Moniteur */
    private JPanel PAjoutm = new JPanel();
    private JLabel Ltitre = new JLabel("Moniteur");
    private JTextField Tnomm = new JTextField();
    private JTextField Tprenomm = new JTextField();
    private JButton Bannuler = new JButton("Annuler");
    private JButton Benregistrer = new JButton("Enregistrer");
    private JButton Bmoniteur = new JButton("Moniteur");
    private JButton Bquitter = new JButton("Quitter");
    private JLabel Lnomm = new JLabel("Nom : ");
    private JLabel Lprenomm = new JLabel("Prenom: ");
	
	public Fenetre()
	{
		// Definition du titre pour la fenêtre
		this.setTitle("Auto-Ecole Castellane");
		// Desactivation du Layout
		this.setLayout(null);
		// Rendre la fenêtre visible
		this.setVisible(true);
		// Definition de la taille de la fenêtre
		this.setBounds(100, 100, 400, 400);
		// Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Panel Ajout Moniteur */
		  PAjoutm.setBounds(110, 50, 300, 300);
	      PAjoutm.setLayout(null);
	      PAjoutm.setVisible(true);
	      Ltitre.setBounds(140, 10, 200, 20);
	      PAjoutm.add(Ltitre);
	      Lnomm.setBounds(10, 40, 100, 20);
	      PAjoutm.add(Lnomm);
	      Tnomm.setBounds(120, 40, 100, 20);
	      PAjoutm.add(Tnomm);
	        
	      Lprenomm.setBounds(10, 70, 100, 20);
	      PAjoutm.add(Lprenomm);
	      Tprenomm.setBounds(120, 70, 100, 20);
	      PAjoutm.add(Tprenomm);
	        
	      Bannuler.setBounds(50, 180, 100, 20);
	      PAjoutm.add(Bannuler);
	      Benregistrer.setBounds(160, 180, 100, 20);
	      PAjoutm.add(Benregistrer);
	        
	      Bmoniteur.setBounds(10, 50, 100, 20);
	      PAjoutm.add(Bmoniteur);
	      Bquitter.setBounds(280, 280, 100, 20);
	      PAjoutm.add(Bquitter);
	      this.add(Bmoniteur);
	      this.add(Bquitter);
	      this.add(PAjoutm);
	      
	    // Rendre les boutons cliquables
	      Bmoniteur.addActionListener(this);
	      Bannuler.addActionListener(this);
	      Bquitter.addActionListener(this);
	      Benregistrer.addActionListener(this);
	      
	   /****************Barre de Menu ***************/
	     //Ajout des différents menus dans la barre de menu
	     menuBar.add(MenuClient);
	     menuBar.add(MenuMoniteur);
	     menuBar.add(MenuVoiture);
	     menuBar.add(MenuPlanning);
	     
	    // Ajout des sous-menus des menus
	    /************ Menu Client ************************/
	     this.MenuClient.add(MlisteClient);
	     this.MenuClient.add(MajouterClient);
	     this.MenuClient.add(MmodifierClient);
	     this.MenuClient.add(MsuppressionClient);
	     
	     /************ Menu Moniteur ************************/
	     this.MenuMoniteur.add(MlisteMoniteur);
	     this.MenuMoniteur.add(MajouterMoniteur);
	     this.MenuMoniteur.add(MmodifierMoniteur);
	     this.MenuMoniteur.add(MsuppressionMoniteur);
	     
	     /************ Menu Client ************************/
	     this.MenuVoiture.add(MlisteVoiture);
	     this.MenuVoiture.add(MajouterVoiture);
	     this.MenuVoiture.add(MmodifierVoiture);
	     this.MenuVoiture.add(MsuppressionVoiture);
	    
	     //Ajout de la barre de menu sur la fenêtre
	     this.setJMenuBar(menuBar);
	      
	}
	
	// Lier les boutons à une action bien définie
	public void actionPerformed (ActionEvent ev)
	{
		Object ae = ev.getSource();
		if(ae == Bquitter)
		{
			System.out.println("Fin du programme");
			System.exit(0);
		}
		else if (ae == Benregistrer)
		{
			this.insererMoniteur();	
		}
		else if (ae == Bannuler)
		{
			this.annuler();
		}
	}
	
	public void insererMoniteur ()
	    {
	        try{
	            String nomm = Tnomm.getText();
	            String prenomm = Tprenomm.getText();
	            Moniteur unMoniteur = new Moniteur(0, nomm, prenomm);
	            uneLecon.insererMoniteur(unMoniteur);
	            JOptionPane.showMessageDialog(this, "Insertion Effectuée", "Insertion", JOptionPane.INFORMATION_MESSAGE);
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de données", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	        
	    }
	
	 public void annuler ()
	 {
	        this.Tnomm.setText("");
	        this.Tprenomm.setText("");
	 }
	    
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public Lecon getUneLecon() {
		return uneLecon;
	}

	public void setUneLecon(Lecon uneLecon) {
		this.uneLecon = uneLecon;
	}
}