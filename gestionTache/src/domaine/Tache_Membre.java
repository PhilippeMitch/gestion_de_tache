package domaine;

public class Tache_Membre {

	private int idMembre;
	private int idTache;
	private String dateAttribution;
	private String etatAttribution;
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}
	public String getDateAttribution() {
		return dateAttribution;
	}
	public void setDateAttribution(String dateAttribution) {
		this.dateAttribution = dateAttribution;
	}
	public String getEtatAttribution() {
		return etatAttribution;
	}
	public void setEtatAttribution(String etatAttribution) {
		this.etatAttribution = etatAttribution;
	}
	public Tache_Membre() {
		super();
	}
	public Tache_Membre(int idMembre, int idTache, String dateAttribution, String etatAttribution) {
		super();
		this.idMembre = idMembre;
		this.idTache = idTache;
		this.dateAttribution = dateAttribution;
		this.etatAttribution = etatAttribution;
	}

	
	
}
