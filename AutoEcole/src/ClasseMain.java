import javax.swing.UIManager;


public class ClasseMain {

	public static void main (String[] args)
	{		   	   
		
		try {
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			  

			} 
		catch (Exception e) 
			{
			
			}
		
		new Fenetre();			
			
	}
}
