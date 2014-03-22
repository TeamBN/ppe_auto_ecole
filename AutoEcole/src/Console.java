import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Console
{
	
	//définir un buffer : zone mémoire de lecture de données entre
	//le clavier et le programme user se trouvant en RAM
	
	static BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
	
	public static int saisirEntier(String message)
	{
		int nb =0;
		String chaine ="";
		System.out.println(message);
		boolean OK = true;
		do {
		
		try{
		chaine = clavier.readLine();
		nb = Integer.parseInt(chaine);
		}
		catch (IOException exp)
		{
		System.out.println("erreur de lecture sur le clavier!");
		exp.printStackTrace();
		}
		catch (NumberFormatException exp)
		{
			OK = false;
			System.out.println("erreur sur le format du nombre");
			exp.printStackTrace();
		}
		}while(OK == false);
		
		return nb;
		
	}

	public static float saisirReel(String message)
	{
		float nb =0;
		String chaine ="";
		System.out.println(message);
		boolean OK = true;
		do {
		
		try{
			OK = true;
		chaine = clavier.readLine();
		nb = Float.parseFloat(chaine);
		}
		catch (IOException exp)
		{
		System.out.println("erreur de lecture sur le clavier!");
		exp.printStackTrace();
		}
		catch (NumberFormatException exp)
		{
			OK = false;
			System.out.println("erreur sur le format du nombre");
			exp.printStackTrace();
		}
		}while(OK == false);
		
		return nb;
		
	}

	public static String saisirChaine(String message)
	{
		
		String chaine ="";
		System.out.println(message);
		

		try{
		
		chaine = clavier.readLine();
	
		}
		catch (IOException exp)
		{
		System.out.println("erreur de lecture sur le clavier!");
	
		}
		
		return chaine;
		
	}
	
	public static char saisirCaractere(String message)
	{
		
		String chaine ="";
		System.out.println(message);
		

		try{
		
		chaine = clavier.readLine();
	
		}
		catch (IOException exp)
		{
		System.out.println("erreur de lecture sur le clavier!");
	
		}
		
		return chaine.charAt(0);
		
	}
}