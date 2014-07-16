
public enum Requete
{
	BlocInformatif("(?<=<table class=\"tableaustandard ficheinfo).+?(?=ogg<\\/a>)"),
	Tout("(?<=html).+?(?=<\\/html>)"),
	
	Numero("(?<=№ ).+?(?=<\\/span>)"),
	
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
