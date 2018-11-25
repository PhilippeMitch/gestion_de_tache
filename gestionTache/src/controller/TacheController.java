package controller;

import dal.TacheDal;
import domaine.Operation;
import domaine.Tache;
import domaine.Tache_Membre;
import javafx.collections.ObservableList;

public class TacheController {
	
	public int EnregistrerTache(Tache t, Operation o){
		int result=0;
		try {
			result=TacheDal.EnregistrerTaches(t,o);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public int AddTacheToMembre(Tache_Membre t, Operation o){
		int result=0;
		try {
			result=TacheDal.addTacheToMembre(t,o);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public ObservableList<Tache> getTache(){
		
		ObservableList<Tache>  dt= null;
		try {
			dt=TacheDal.getTacheMainFlow();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}

	public ObservableList<Tache> getTacheMembre(int idMembre){
		
		ObservableList<Tache>  dt= null;
		try {
			dt=TacheDal.getTacheMembre(idMembre);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}

	public static int ModifierTache(Tache t, Operation o,int idTache){
		int result=0;
		try {
			result=TacheDal.ModifierTache(t, o, idTache);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public static int SupprimerTache(Operation o,int idTache){
		int result=0;
		try {
			result=TacheDal.SupprimerTache(o, idTache);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public static ObservableList<String> getListNomTache(){
		ObservableList<String>  dt= null;
		try {
			dt=TacheDal.getListNomTache();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}

	public ObservableList<Tache> getTacheFromNomTache(String nomTache){
		
		ObservableList<Tache>  dt= null;
		try {
			dt=TacheDal.getTacheFromNomTache(nomTache);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dt;
	}
}
