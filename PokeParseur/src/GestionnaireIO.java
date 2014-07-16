import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionnaireIO
{
	
	public String parserFichier(String nomFichier, String requete) throws IOException
	{
		Pattern motif = Pattern.compile(requete, Pattern.DOTALL); // DOTALL pour
																	// que "."
																	// puisse
																	// aussi
																	// etre un
																	// retour
																	// chariot
		File source = new File("fichiers/" + nomFichier);
		String contenu = transformerFichierEnString(source.getAbsolutePath());

		String selection = "";
		
		Matcher parseur = motif.matcher(contenu);
		while (parseur.find())
		{
			selection = parseur.group();
		}

		return selection;
	}

	private String parserTexte(String texte, String requete) throws IOException
	{

		Pattern motif = Pattern.compile(requete, Pattern.DOTALL); // DOTALL pour
																	// que "."
																	// puisse
																	// aussi
																	// etre un
																	// retour
																	// chariot

		Matcher parseur = motif.matcher(texte);
		if (parseur.find())
		{
			return parseur.group();
		}

		return null;
	}

	private String transformerFichierEnString(String cheminFichier) throws java.io.IOException
	{
		byte[] tampon = new byte[(int) new File(cheminFichier).length()];
		BufferedInputStream memoireTampon = null;
		try
		{
			memoireTampon = new BufferedInputStream(new FileInputStream(cheminFichier));
			memoireTampon.read(tampon);
		}
		finally
		{
			if (memoireTampon != null)
				try
				{
					memoireTampon.close();
				}
				catch (IOException ignored)
				{
					/**
					 * On ne fait rien
					 */
				}
		}
		return new String(tampon);
	}
	
	public void enregistrerPokemon(Pokemon pokemon) throws IOException
	{
		File fichier = new File("sortie/code.txt");
		fichier.createNewFile();
		FileWriter ecrivain = new FileWriter(fichier);

		ecrivain.write("caca");

		//ecrivain.write(System.getProperty("line.separator") + quelquechose);

		ecrivain.close();
	}
}

