
public enum Requete
{
	BlocInformatif("(?<=<table class=\"tableaustandard ficheinfo).+?(?=ogg<\\/a>)"),
	Numero("(?<=№ ).+?(?=<\\/span>)"),
	
	Famille("(?<=Famille<\\/a>).+?(?=<\\/td>)"),
	InFamille("(?<=Pokémon<\\/a> ).*"),
	
	Genre("(?<=Sexe<\\/a>).+?(?=<\\/td>)"),
	InGenre("(?<=; ).+?(?=% mâle)"),
	
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
