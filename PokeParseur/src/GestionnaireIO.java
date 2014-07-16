
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionnaireIO
{
	
	public ArrayList<String> lireFichier(String nomFichier)
	{
		ArrayList<String> contenu = new ArrayList<String>();
		
		String filePath = "fichiers/"+nomFichier+".html";
		 
		try
		{
			BufferedReader buff = new BufferedReader(new FileReader(filePath));
			try 
			{
				String ligne;
				while ((ligne = buff.readLine()) != null) 
				{
					contenu.add(ligne);
				}
			} 
			finally 
			{
				buff.close();
			}
		} 
		catch (IOException e) 
		{
			System.out.println("Erreur avec le fichier -- " + e.toString());
		}
			
		return contenu;
	}
}

