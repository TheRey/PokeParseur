
public enum Requete
{
	BlocInformatif("(?<=<table class=\"tableaustandard ficheinfo).+?(?=ogg<\\/a>)"),
	Tout("(?<=html).+?(?=<\\/html>)"),
	
	Numero("(?<=№ ).+?(?=<\\/span>)"),
	Nom("(?<=<title>).+?(?= - Poképédia<\\/title>)"),
	
	Famille("(?<=Famille<\\/a>).+?(?=<\\/td>)"),
	InFamille("(?<=Pokémon<\\/a> ).*"),
	
	Genre("(?<=Sexe<\\/a>).+?(?=<\\/td>)"),
	InGenre("(?<=; ).+?(?=% mâle)"),
	
	TauxCapture("(?<=Taux de capture<\\/a>).+?(?=<\\/td>)"),
	InTauxCapture("(?<=\"3\"> ).*"),
	
	PointsExp("(?<=Points exp.<\\/a>).+?(?=<\\/td>)"),
	InPointsExp("(?<=\"3\"> ).+?(?= exp)"),
	
	ExpMax("(?<=Exp. au niveau 100<\\/a>).+?(?=<\\/td>)"),
	InExpMax("(?<=\"3\"> ).+?(?= exp)"),
	
	Type("(?<=Type<\\/a>).+?(?=<\\/td>)"),
	
	Eclosion("(?<=cycles - ).+?(?= pas)"),
	
	Description("(?<=Pokémon X<\\/a>).+?(?=<\\/dd>)"),
	InDescription("(?<=<dd>).*"),
	
	Evolution("(?<=<td style=\"padding-top:0px;padding-bottom:0px;\">).+?(?=<\\/tr>)"),
	Prevolution(".+?(?=<\\/td>)"),
	Supevolution("<\\/td>).*"),
	Evolution2("(?<=<td style=\"padding-top:0px;padding-bottom:0px;\">).+?(?=<\\/td>)"),
	Evolution3("(?<=<br \\/><strong class=\"selflink\">"),
	Niveau("(?<=Niveau ).+?(?=  &#9660;)"),
	
	Taille("(?<=Taille<\\/a>).+?(?=<\\/td>)"),
	InTaille("(?<=\"3\"> ).+?(?=m, )"),
	
	Poids("(?<=Poids<\\/a>).+?(?=<\\/td>)"),
	InPoids("(?<=\"3\"> ).+?(?=kg, )"),
	
	Stats("(?<=Statistique<\\/a>).+?(?=Somme des statistiques)"),
	PV("(?<=PV<\\/a>).*"),
	InPV("(?<=<td> ).+?(?=<\\/td>)"),
	Attaque("(?<=Attaque<\\/a>).*"),
	InAttaque("(?<=<td> ).+?(?=<\\/td>)"),
	Defense("(?<=Défense<\\/a>).*"),
	InDefense("(?<=<td> ).+?(?=<\\/td>)"),
	AtkSpe("(?<=Attaque spéciale<\\/a>).*"),
	InAtkSpe("(?<=<td> ).+?(?=<\\/td>)"),
	DefSpe("(?<=Défense spéciale<\\/a>).*"),
	InDefSpe("(?<=<td> ).+?(?=<\\/td>)"),
	Vitesse("(?<=Vitesse<\\/a>).*"),
	InVitesse("(?<=<td> ).+?(?=<\\/td>)"),

	
	Modele("(?<=).+?(?=)");
	
	private String information;

	private Requete(String information)
	{
		this.information = information;
	}

	public String toString()
	{
		return this.information;
	}
}
