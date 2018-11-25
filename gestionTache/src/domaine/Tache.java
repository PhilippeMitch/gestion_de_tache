package domaine;

import java.util.Random;

public class Tache {
	private int idTache;
	private String nomTache;
	private String descriptionTache;
	private String dateDebutTache;
	private String dateFinTache;
	private String etatTache;
	
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}
	public String getNomTache() {
		return nomTache;
	}
	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}
	public String getDescriptionTache() {
		return descriptionTache;
	}
	public void setDescriptionTache(String descriptionTache) {
		this.descriptionTache = descriptionTache;
	}
	public String getDateDebutTache() {
		return dateDebutTache;
	}
	public void setDateDebutTache(String dateDebutTache) {
		this.dateDebutTache = dateDebutTache;
	}
	public String getDateFinTache() {
		return dateFinTache;
	}
	public void setDateFinTache(String dateFinTache) {
		this.dateFinTache = dateFinTache;
	}
	public String getEtatTache() {
		return etatTache;
	}
	public void setEtatTache(String etatTache) {
		this.etatTache = etatTache;
	}
	
	public Tache(int idTache, String nomTache, String descriptionTache, String dateDebutTache, String dateFinTache,
			String etatTache) {
		super();
		this.idTache = idTache;
		this.nomTache = nomTache;
		this.descriptionTache = descriptionTache;
		this.dateDebutTache = dateDebutTache;
		this.dateFinTache = dateFinTache;
		this.etatTache = etatTache;
	}
	public Tache() {
		super();
	}
	
	public Tache(int idTache,String nomTache, String descriptionTache,String etatTache) {
		super();
		this.idTache = idTache;
		this.nomTache = nomTache;
		this.descriptionTache = descriptionTache;
		this.etatTache = etatTache;
	}
	
	public Tache(String nomTache, String descriptionTache, String dateDebutTache, String dateFinTache,
			String etatTache) {
		super();
		this.idTache = generateId();
		this.nomTache = nomTache;
		this.descriptionTache = descriptionTache;
		this.dateDebutTache = dateDebutTache;
		this.dateFinTache = dateFinTache;
		this.etatTache = etatTache;
	}
	
	public int generateId(){
		Random random = new Random();
		int index = random.nextInt(100000000);
		return index;
	}
	
	public Tache(String nomTache) {
		super();
		this.nomTache = nomTache;
	}
	
	
}
