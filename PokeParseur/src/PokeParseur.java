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
				System.out.println("Problème dans le parsage ! Fichier concerné : " + fichier.getName());
				e.printStackTrace();
			}

			this.recupererDonnees(contenu);
		}
	}

	private void recupererDonnees(String contenu)
	{
		String numero = this.gestionnaireIO.parserTexte(contenu, Requete.Numero.toString());
		Pokemon pokemon = new Pokemon(Integer.parseInt(numero));
		
		String famille = this.gestionnaireIO.parserTexte(contenu, Requete.Famille.toString());
		famille = this.gestionnaireIO.parserTexte(famille, Requete.InFamille.toString());
		pokemon.famille = famille.substring(0, famille.length()-1);
		
		String genre = this.gestionnaireIO.parserTexte(contenu, Requete.Genre.toString());
		if (genre.contains("Asexué"))
		{
			pokemon.genre[0] = "true";
			pokemon.genre[1] = "0";
		}
		else
		{
			pokemon.genre[0] = "false";
			pokemon.genre[1] = this.gestionnaireIO.parserTexte(genre, Requete.InGenre.toString());
		}
		
		System.out.println(pokemon.numero + "\n" + pokemon.famille + "\n" + pokemon.genre[0] + " " + pokemon.genre[1]);
	}

}
