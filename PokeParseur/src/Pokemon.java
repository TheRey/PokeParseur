import java.util.Arrays;
import java.util.HashMap;


public class Pokemon
{
	int numero;
	String nom;
	String famille;
	String[] genre;
	int tauxCapture;
	int[] experience_experienceMax;
	int[] types;
	int idPreEvolution;
	int[] evolution;
	int nombrePasEclosion;
	int[] oeufs;
	int nombreFormes;
	String[] taille_poids;
	String[] statsBase;
	String[] statsEVs;
	int[] talents;
	String description;
	HashMap<Integer,Integer> attaquesParNiveau;
	/* Non utilisé
	ArrayList attaquesParCapsules;
	ArrayList attaquesParReproduction;
	ArrayList attaqueMaitreCapacite;
	*/
	String texte;
	
	public Pokemon(int id)
	{
		this.numero = id;
		this.genre = new String[2];
		this.experience_experienceMax = new int[2];
		this.types = new int[2];
		this.evolution = new int[3];
		this.taille_poids = new String[2];
		this.statsBase = new String[6];
		this.statsEVs = new String[6];
		this.oeufs = new int[2];
		this.talents = new int[2];
		this.attaquesParNiveau = new HashMap<Integer,Integer>();
		this.texte = "";
	}
}
