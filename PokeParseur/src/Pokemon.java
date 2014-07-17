import java.util.ArrayList;


public class Pokemon
{
	int numero;
	String nom;
	String famille;
	String[] genre;
	int tauxCapture;
	int[] experience_experienceMax;
	ArrayList<Integer> types;
	int idPreEvolution;
	int[] evolution;
	int nombrePasEclosion;
	int[] groupesOeuf;
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
		this.experience_experienceMax = new int[2];
		this.types = new ArrayList<Integer>();
		this.evolution = new int[3];
		this.taille_poids = new String[2];
		this.statsBase = new String[6];
	}
}
