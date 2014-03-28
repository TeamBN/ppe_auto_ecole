import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextPane;

public class Fenetre extends JFrame implements ActionListener, ItemListener 
{	
	/*Le serialVersionUID est une version universelle identifiant pour une classe Serializable*/
	private static final long serialVersionUID = 1L;
	
	private JPanel Pmenu = new JPanel();
	
	private JButton bouton = new JButton("Valider");
	private JTextField Ttest = new JTextField("Texte de test");
	
	public Fenetre()
	{
		this.setLayout(null);
		// Definition du titre pour la fenêtre
		this.setTitle("Auto-Ecole Castellane");
		// Definition de la taille de la fenêtre
		this.setSize(400, 500);
		// Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Rendre la fenêtre visible
		
		
		// Panel Menu
		Pmenu.setLayout(null);
		Pmenu.setBounds(200, 200, 300, 300);
		Pmenu.setVisible(true);
		
		// Ajout du bouton
		bouton.setBounds(100, 40, 100, 20);
		Pmenu.add(bouton);
		
		// Ajout du Texte
		Ttest.setBounds(20,10 , 200, 20);
		Pmenu.add(Ttest);
		
		// On previent notre JFrame que notre JPanel sera son content pane
		this.setContentPane(Pmenu);
		this.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}