import java.util.ArrayList;


public class Pokemon
{
	int numero;
	String nom;
	String famille;
	String[] genre;
	int tauxCapture;
	String[] experience_experienceMax;
	String[] types;
	int idPreEvolution;
	String[] evolution;
	int nombrePasEclosion;
	String[] groupesOeuf;
	int nombreFormes;
	String[] taille_poids;
	String[] statsBase;
	String[] statsEVs;
	String[] idCapSpe;
	String description;
	ArrayList<Attaque> attaquesParNiveau;
	ArrayList attaquesParCapsules;
	ArrayList attaquesQuandIlsBaisent;
	ArrayList attaqueMaitreCapacite;
	
	public Pokemon(int id)
	{
		this.numero = id;
		this.genre = new String[2];
	}
}
