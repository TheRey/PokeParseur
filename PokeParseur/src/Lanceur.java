
public class Lanceur
{

	public static void main(String[] args)
	{
		GestionnaireIO gestionnaireIO = new GestionnaireIO();
		PokeParseur application = new PokeParseur(gestionnaireIO);
		application.lancer();
	}

}
