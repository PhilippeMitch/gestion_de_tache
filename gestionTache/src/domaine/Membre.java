package domaine;

import java.util.Random;

public class Membre {

	private int idMembre;
	private String nomMembre;
	private String prenomMembre;
	private String adresseMembre;
	private String telMembre;
	private String mailMembre;
	private String statusMembre;
	
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public String getNomMembre() {
		return nomMembre;
	}
	public void setNomMembre(String nomMembre) {
		this.nomMembre = nomMembre;
	}
	public String getPrenomMembre() {
		return prenomMembre;
	}
	public void setPrenomMembre(String prenomMembre) {
		this.prenomMembre = prenomMembre;
	}
	public String getAdresseMembre() {
		return adresseMembre;
	}
	public void setAdresseMembre(String adresseMembre) {
		this.adresseMembre = adresseMembre;
	}
	public String getTelMembre() {
		return telMembre;
	}
	public void setTelMembre(String telMembre) {
		this.telMembre = telMembre;
	}
	public String getMailMembre() {
		return mailMembre;
	}
	public void setMailMembre(String mailMembre) {
		this.mailMembre = mailMembre;
	}
	public String getStatusMembre() {
		return statusMembre;
	}
	public void setStatusMembre(String statusMembre) {
		this.statusMembre = statusMembre;
	}
	public Membre() {
		super();
	}
	
	public Membre(int idMembre, String nomMembre, String prenomMembre, String telMembre, String mailMembre, String adresseMembre,
			 String statusMembre) {
		super();
		this.idMembre = idMembre;
		this.nomMembre = nomMembre;
		this.prenomMembre = prenomMembre;
		this.telMembre = telMembre;
		this.mailMembre = mailMembre;
		this.adresseMembre = adresseMembre;
		this.statusMembre = statusMembre;
	}
	
	public Membre( String nomMembre, String prenomMembre,  String telMembre,String mailMembre,String adresseMembre,
			String statusMembre) {
		super();
		this.idMembre = generateId();
		this.nomMembre = nomMembre;
		this.prenomMembre = prenomMembre;
		this.telMembre = telMembre;
		this.mailMembre = mailMembre;
		this.adresseMembre = adresseMembre;
		this.statusMembre = statusMembre;
	}
	
	public Membre(int idMembre, String nomMembre, String prenomMembre,  String telMembre,String mailMembre,String adresseMembre) {
		super();
		this.idMembre = idMembre;
		this.nomMembre = nomMembre;
		this.prenomMembre = prenomMembre;
		this.telMembre = telMembre;
		this.mailMembre = mailMembre;
		this.adresseMembre = adresseMembre;
	}
	
	public int generateId(){
		Random random = new Random();
		int index = random.nextInt(1000000000);
		return index;
	}


}
