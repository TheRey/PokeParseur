
public class Lanceur
{
	/*
	 * Faire gaffe
	 * - Aux legendaires avec un gros sexe (car par défaut je l'ai ai mis tous asexué)
	 * - Aux formes, par défaut à 0
	 */

	public static void main(String[] args)
	{
		GestionnaireIO gestionnaireIO = new GestionnaireIO();
		PokeParseur application = new PokeParseur(gestionnaireIO);
		application.lancer();
	}

}
