package controller;

import java.sql.SQLException;

import dal.MembreDal;
import domaine.Membre;
import domaine.Operation;
import javafx.collections.ObservableList;

public class MembreController {

	//Enregistrer Membre
	public int EnregistrerJeune(Membre m, Operation o) throws SQLException{
		int result=0;
			
		try {
				result=MembreDal.EnregistrerMembre(m, o);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return result;	
		}
	
	//Liste des membre dans le Dashboard
	public ObservableList<Membre> getMembre(){
			
		ObservableList<Membre>lstListMembre = null;
			
			try {
				lstListMembre=MembreDal.getMembreMainFlow();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return lstListMembre;
			
		}

	public int ModifierMembre(Membre m, Operation o,int idMembre){
		int result=0;
		try {
			result=MembreDal.ModifierMembre(m, o, idMembre);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public  int SupprimerMembre( Operation o,int idMembre){
		int result=0;
		try {
			result=MembreDal.SupprimerMembre(o, idMembre);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int VerifierEnfoMembre(String nom,String prenom,String tel,String adress,String mail){
		int result=0;
		try {
			result=MembreDal.VerificationEnfoMembre(nom, prenom, tel, adress, mail);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
