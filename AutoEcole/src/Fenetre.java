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
	/* Panel d'ajout de Moniteur */
    private JPanel PAjoutm = new JPanel();
    private JLabel Ltitre = new JLabel("Ajout d'un Moniteur");
    private JTextField Tnomm = new JTextField();
    private JTextField Tprenomm = new JTextField();
    private JButton Bannuler = new JButton("Annuler");
    private JButton Benregistrer = new JButton("Enregistrer");
    private JButton Bajouter = new JButton("Ajouter");
    private JButton Bquitter = new JButton("Quitter");
    private JComboBox Cidm = new JComboBox();
    private JLabel Lnomm = new JLabel("Nom : ");
    private JLabel Lprenomm = new JLabel("Prenom: ");
    private JLabel Lidm = new JLabel("Id Moniteur");
	
	public Fenetre()
	{
		// Definition du titre pour la fen�tre
		this.setTitle("Auto-Ecole Castellane");
		this.setLayout(null);
		// Rendre la fen�tre visible
		this.setVisible(true);
		// Definition de la taille de la fen�tre
		this.setBounds(100, 100, 400, 400);
		// Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Panel Ajout Moniteur */
		  PAjoutm.setBounds(110, 50, 300, 300);
	      PAjoutm.setLayout(null);
	      PAjoutm.setVisible(true);
	      Ltitre.setBounds(100, 20, 200, 20);
	      PAjoutm.add(Ltitre);
	      Lnomm.setBounds(10, 40, 100, 20);
	      PAjoutm.add(Lnomm);
	      Tnomm.setBounds(120, 40, 100, 20);
	      PAjoutm.add(Tnomm);
	        
	      Lprenomm.setBounds(10, 70, 100, 20);
	      PAjoutm.add(Lprenomm);
	      Tprenomm.setBounds(120, 70, 100, 20);
	      PAjoutm.add(Tprenomm);
	      
	      Lidm.setBounds(10, 130, 100, 20);
	      PAjoutm.add(Lidm);
	      Cidm.setBounds(120, 130, 100, 20);
	      PAjoutm.add(Cidm);
	        
	      Bannuler.setBounds(50, 180, 100, 20);
	      PAjoutm.add(Bannuler);
	      Benregistrer.setBounds(160, 180, 100, 20);
	      PAjoutm.add(Benregistrer);
	        
	      Bajouter.setBounds(10, 50, 100, 20);
	      PAjoutm.add(Bajouter);
	      Bquitter.setBounds(280, 280, 100, 20);
	      PAjoutm.add(Bquitter);
	      this.add(Bajouter);
	      this.add(Bquitter);
	      this.add(PAjoutm);
	      
	    // Rendre les boutons cliquables
	      Bajouter.addActionListener(this);
	      Bannuler.addActionListener(this);
	      Bquitter.addActionListener(this);
	      Benregistrer.addActionListener(this);
	      
	}
	
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
	}
	
	public void insererMoniteur ()
	    {
	        try{
	            String nomm = Tnomm.getText();
	            String prenomm = Tprenomm.getText();
	            Moniteur unMoniteur = new Moniteur(0, nomm, prenomm);
	            uneLecon.insererMoniteur(unMoniteur);
	            JOptionPane.showMessageDialog(this, "Insertion Effectu�e", "Insertion", JOptionPane.OK_OPTION);
	        }
	       catch (NumberFormatException exp){
	           JOptionPane.showMessageDialog(this, "Erreur de donn�es", "Erreur", JOptionPane.OK_OPTION);
	       }
	        
	    }
	
	 public void annuler (){
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