import java.io.File;
import java.io.IOException;

public class PokeParseur
{

	private GestionnaireIO gestionnaireIO;
	
	public PokeParseur(GestionnaireIO gestionnaireIO)
	{
		this.gestionnaireIO = gestionnaireIO;
	}
	
	public void lancer()
	{
		File ensembleFichiers = new File("fichiers");

		String contenu = "";

		for (File fichier : ensembleFichiers.listFiles())
		{
			try
			{
				contenu = this.gestionnaireIO.parserFichier(fichier.getName(), Requete.BlocInformatif.toString());
			}
			catch (IOException e)
			{
				System.out.println("Problème dans le parsage !");
				e.printStackTrace();
			}

			System.out.println(contenu);
		}
	}

}
