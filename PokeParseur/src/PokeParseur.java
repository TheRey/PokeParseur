import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class PokeParseur
{
	private GestionnaireIO gestionnaireIO;
	private HashMap<String, Integer> types;

	public PokeParseur(GestionnaireIO gestionnaireIO)
	{
		this.gestionnaireIO = gestionnaireIO;
		this.types = new HashMap<String, Integer>();
		this.types.put("Acier", 1);
		this.types.put("Combat", 2);
		this.types.put("Dragon", 3);
		this.types.put("Eau", 4);
		this.types.put("Electrique", 5);
		this.types.put("Fée", 6);
		this.types.put("Feu", 7);
		this.types.put("Glace", 8);
		this.types.put("Insecte", 9);
		this.types.put("Normal", 10);
		this.types.put("Plante", 11);
		this.types.put("Poison", 12);
		this.types.put("Psy", 13);
		this.types.put("Roche", 14);
		this.types.put("Sol", 15);
		this.types.put("Spectre", 16);
		this.types.put("Ténèbres", 17);
		this.types.put("Vol", 18);
	}

	public void lancer()
	{
		File ensembleFichiers = new File("fichiers");

		String contenu = "", complet = "";

		for (File fichier : ensembleFichiers.listFiles())
		{
			try
			{
				contenu = this.gestionnaireIO.parserFichier(fichier.getName(), Requete.BlocInformatif.toString());
				complet = this.gestionnaireIO.parserFichier(fichier.getName(), Requete.Tout.toString());
			}
			catch (IOException e)
			{
				System.out.println("Problème dans le parsage ! Fichier concerné : " + fichier.getName());
				e.printStackTrace();
			}

			this.recupererDonnees(contenu, complet);
		}
	}

	private void recupererDonnees(String contenu, String complet)
	{
		String numero = this.gestionnaireIO.parserTexte(contenu, Requete.Numero.toString());
		Pokemon pokemon = new Pokemon(Integer.parseInt(numero));

		String famille = this.gestionnaireIO.parserTexte(contenu, Requete.Famille.toString());
		famille = this.gestionnaireIO.parserTexte(famille, Requete.InFamille.toString());
		pokemon.famille = famille.substring(0, famille.length() - 1);

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

		String tauxCapture = this.gestionnaireIO.parserTexte(contenu, Requete.TauxCapture.toString());
		tauxCapture = this.gestionnaireIO.parserTexte(tauxCapture, Requete.InTauxCapture.toString());
		pokemon.tauxCapture = Integer.parseInt(tauxCapture.substring(0, tauxCapture.length() - 1));

		String exp = this.gestionnaireIO.parserTexte(contenu, Requete.PointsExp.toString());
		pokemon.experience_experienceMax[0] = Integer.parseInt(this.gestionnaireIO.parserTexte(exp,Requete.InPointsExp.toString()));

		String expMax = this.gestionnaireIO.parserTexte(contenu, Requete.ExpMax.toString());
		expMax = this.gestionnaireIO.parserTexte(expMax, Requete.InExpMax.toString());
		pokemon.experience_experienceMax[1] = Integer.parseInt(expMax.replace(" ", ""));

		String types = this.gestionnaireIO.parserTexte(contenu, Requete.Type.toString());
		for (Entry<String, Integer> entree : this.types.entrySet())
		{
			if(types.contains(entree.getKey()))
			{
				pokemon.types.add(entree.getValue());
			}
		}
		
		String evolution = this.gestionnaireIO.parserTexte(complet, Requete.Evolution.toString());
		
		int prevolution = Integer.parseInt(this.gestionnaireIO.parserTexte(evolution,Requete.Prevolution.toString()));
		if (prevolution != pokemon.numero)
		{
			pokemon.idPreEvolution = prevolution;
		}
		else
		{
			pokemon.idPreEvolution = 0;
		}

		//Evo
		
		pokemon.nombrePasEclosion = Integer.parseInt(this.gestionnaireIO.parserTexte(contenu, Requete.Eclosion.toString()));

		//Groupes oeuf
		
		pokemon.nombreFormes = 0;
		
		//Taille + Poids
		
		//Stats bases
		
		//EVs
		
		//ID Cap spéciales
		
		String description = this.gestionnaireIO.parserTexte(complet, Requete.Description.toString());
		description = this.gestionnaireIO.parserTexte(description, Requete.InDescription.toString());
		pokemon.description = description.substring(0, description.length() - 1);
		
		System.out.println(pokemon.numero + "\n" + pokemon.famille + "\n" + pokemon.genre[0] + " " + pokemon.genre[1]
				+ "\n" + pokemon.tauxCapture + "\n" + pokemon.experience_experienceMax[0] + " "
				+ pokemon.experience_experienceMax[1]);
		
		for (Integer nombre : pokemon.types)
		{
			System.out.println(nombre + " ");
		}
		
		System.out.println(pokemon.idPreEvolution + "\n" + pokemon.nombrePasEclosion + "\n" + pokemon.description);
	}

}
