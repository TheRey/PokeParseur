
public enum Requete
{
	BlocInformatif("(?<=<table class=\"tableaustandard ficheinfo).+?(?=ogg<\\/a>)");

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
