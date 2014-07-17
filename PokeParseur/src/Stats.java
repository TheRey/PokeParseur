
public enum Stats
{
	PV("PV"),
	Att("Att."),
	Def("Déf."),
	AttSpe("Att. Spé"),
	DefSpe("Déf. Spé"),
	Vit("Vit.");
	
	private String information;

	private Stats(String information)
	{
		this.information = information;
	}

	public String toString()
	{
		return this.information;
	}
}
