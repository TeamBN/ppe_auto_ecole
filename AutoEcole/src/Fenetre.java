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
	public Fenetre()
	{
		// Definition du titre pour la fenêtre
		this.setTitle("Auto-Ecole Castellane");
		// Definition de la taille de la fenêtre
		this.setSize(400, 500);
		//Demande de positionnement de notre objet au centre
		this.setLocationRelativeTo(null);
		// Termine bien le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Rendre la fenêtre visible
		this.setVisible(true);
		
		JPanel pan = new JPanel();
		
		// On previent notre JFrame que notre JPanel sera son content pane
		this.setContentPane(pan);
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