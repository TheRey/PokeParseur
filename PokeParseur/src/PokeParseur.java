import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PokeParseur
{
	private GestionnaireIO gestionnaireIO;
	private ArrayList<String> types;
	private ArrayList<String> oeufs;
	private ArrayList<String> attaques;
	private ArrayList<String> talents;

	public PokeParseur(GestionnaireIO gestionnaireIO)
	{
		this.gestionnaireIO = gestionnaireIO;
		this.types = this.gestionnaireIO.obtenirLignes("Types");
		this.oeufs = this.gestionnaireIO.obtenirLignes("Oeufs");
		this.attaques = this.gestionnaireIO.obtenirLignes("Attaques");
		this.talents = this.gestionnaireIO.obtenirLignes("Talents");
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
		
		pokemon.nom = this.gestionnaireIO.parserTexte(complet, Requete.Nom.toString());

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
		ArrayList<Integer> nombresTypes = this.obtenirNumero(this.types, types);
		switch (nombresTypes.size())
		{
		case 0 :
			pokemon.types[0] = 0;
			pokemon.types[1] = 0;
			break;
		case 1 :
			pokemon.types[0] = nombresTypes.get(0);
			pokemon.types[1] = 0;
			break;
		case 2 :
			pokemon.types[0] = nombresTypes.get(0);
			pokemon.types[1] = nombresTypes.get(1);
			break;
		default :
			System.out.println("Problèmes sur les numéros de type !");
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

		String supevolution = this.gestionnaireIO.parserTexte(evolution, "(?<=" + pokemon.numero + Requete.Supevolution.toString());
		if (supevolution.contains("</td>"))
		{
			pokemon.evolution[0] = Integer.parseInt(this.gestionnaireIO.parserTexte(supevolution, Requete.Evolution2.toString()));
			String blocEvolution = this.gestionnaireIO.parserTexte(complet, Requete.Evolution3.toString() + pokemon.nom  + ").*");
			pokemon.evolution[2] = Integer.parseInt(this.gestionnaireIO.parserTexte(blocEvolution, Requete.Niveau.toString()));
		}
		
		pokemon.nombrePasEclosion = Integer.parseInt(this.gestionnaireIO.parserTexte(contenu, Requete.Eclosion.toString()));

		String oeufs = this.gestionnaireIO.parserTexte(contenu, Requete.Oeufs.toString());
		ArrayList<Integer> nombresOeufs = this.obtenirNumero(this.oeufs, oeufs);
		switch (nombresOeufs.size())
		{
		case 0 :
			pokemon.oeufs[0] = 0;
			pokemon.oeufs[1] = 0;
			break;
		case 1 :
			pokemon.oeufs[0] = nombresOeufs.get(0);
			pokemon.oeufs[1] = 0;
			break;
		case 2 :
			pokemon.oeufs[0] = nombresOeufs.get(0);
			pokemon.oeufs[1] = nombresOeufs.get(1);
			break;
		default :
			System.out.println("Problèmes sur les numéros d'oeufs !");
		}
		
		pokemon.nombreFormes = 0;
		
		String taille = this.gestionnaireIO.parserTexte(contenu, Requete.Taille.toString());
		pokemon.taille_poids[0] = this.gestionnaireIO.parserTexte(taille,Requete.InTaille.toString());
		
		String poids = this.gestionnaireIO.parserTexte(contenu, Requete.Poids.toString());
		pokemon.taille_poids[1] = this.gestionnaireIO.parserTexte(poids,Requete.InPoids.toString());
		
		String stats = this.gestionnaireIO.parserTexte(complet, Requete.Stats.toString());
		String PV = this.gestionnaireIO.parserTexte(stats, Requete.PV.toString());
		PV = this.gestionnaireIO.parserTexte(PV, Requete.InPV.toString());
		pokemon.statsBase[0] = PV.substring(0, PV.length() - 1);
		String ATK = this.gestionnaireIO.parserTexte(stats, Requete.Attaque.toString());
		ATK = this.gestionnaireIO.parserTexte(ATK, Requete.InAttaque.toString());
		pokemon.statsBase[1] = ATK.substring(0, ATK.length() - 1);
		String DEF = this.gestionnaireIO.parserTexte(stats, Requete.Defense.toString());
		DEF = this.gestionnaireIO.parserTexte(DEF, Requete.InDefense.toString());
		pokemon.statsBase[2] = DEF.substring(0, DEF.length() - 1);
		String ATKSPE = this.gestionnaireIO.parserTexte(stats, Requete.AtkSpe.toString());
		ATKSPE = this.gestionnaireIO.parserTexte(ATKSPE, Requete.InAtkSpe.toString());
		pokemon.statsBase[3] = ATKSPE.substring(0, ATKSPE.length() - 1);
		String DEFSPE = this.gestionnaireIO.parserTexte(stats, Requete.DefSpe.toString());
		DEFSPE = this.gestionnaireIO.parserTexte(DEFSPE, Requete.InDefSpe.toString());
		pokemon.statsBase[4] = DEFSPE.substring(0, DEFSPE.length() - 1);
		String VIT = this.gestionnaireIO.parserTexte(stats, Requete.Vitesse.toString());
		VIT = this.gestionnaireIO.parserTexte(VIT, Requete.InVitesse.toString());
		pokemon.statsBase[5] = VIT.substring(0, VIT.length() - 1);
		
		String EVs = this.gestionnaireIO.parserTexte(contenu, Requete.EVs.toString());
		int index = 0;
		for (Stats stat : Stats.values())
		{
			if (EVs.contains(stat.toString()))
			{
				pokemon.statsEVs[index] = this.gestionnaireIO.parserTexte(EVs,Requete.InEVs.toString() + stat.toString() + ")");
			}
			else
			{
				pokemon.statsEVs[index] = "0";
			}
			index++;
		}
		
		String talents = this.gestionnaireIO.parserTexte(contenu, Requete.Talents.toString());
		ArrayList<Integer> nombresTalents = this.obtenirNumero(this.talents, talents);
		switch (nombresTalents.size())
		{
		case 0 :
			pokemon.talents[0] = 0;
			pokemon.talents[1] = 0;
			break;
		case 1 :
			pokemon.talents[0] = nombresTalents.get(0);
			pokemon.talents[1] = 0;
			break;
		case 2 :
			pokemon.talents[0] = nombresTalents.get(0);
			pokemon.talents[1] = nombresTalents.get(1);
			break;
		default :
			System.out.println("Problèmes sur les numéros de talent !");
		}
		
		String description = this.gestionnaireIO.parserTexte(complet, Requete.Description.toString());
		description = this.gestionnaireIO.parserTexte(description, Requete.InDescription.toString());
		pokemon.description = description.substring(0, description.length() - 1);
		
		//Attaques
		
		System.out.println(pokemon.nom + "\n" + pokemon.numero + "\n" + pokemon.famille + "\n" + pokemon.genre[0] + " " + pokemon.genre[1]
				+ "\n" + pokemon.tauxCapture + "\n" + pokemon.experience_experienceMax[0] + " "
				+ pokemon.experience_experienceMax[1]);
		
		System.out.println("Types : " + pokemon.types[0] + " " + pokemon.types[1]);
		
		System.out.println("Oeufs : " + pokemon.oeufs[0] + " " + pokemon.oeufs[1]);
		
		System.out.println("Talents : " + pokemon.talents[0] + " " + pokemon.talents[1]);
		
		System.out.println(pokemon.idPreEvolution 
				+ "\n" + pokemon.evolution[0] + " " + pokemon.evolution[1] + " " + pokemon.evolution[2]
				+ "\n" + pokemon.nombrePasEclosion 
				+ "\n" + pokemon.taille_poids[0] + " " + pokemon.taille_poids[1]
				+ "\n" + pokemon.description);
		
		System.out.print("Stats base : ");
		for (int i = 0; i<6; i++)
		{
			System.out.print(pokemon.statsBase[i] + " ");
		}
		System.out.println();
		
		System.out.print("Stats EVs : ");
		for (int i = 0; i<6; i++)
		{
			System.out.print(pokemon.statsEVs[i] + " ");
		}
		System.out.println();
	}
	
	public ArrayList<Integer> obtenirNumero(ArrayList<String> liste, String texte)
	{
		ArrayList<Integer> nombres = new ArrayList<Integer>();
		
		for (String entree : liste)
		{
			if(texte.contains(entree))
			{
				nombres.add(liste.indexOf(entree)+1);
			}
		}
		return nombres;
	}

}
