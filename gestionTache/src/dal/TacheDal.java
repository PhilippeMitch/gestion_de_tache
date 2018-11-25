package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnection.MysqlAccess;
import domaine.Operation;
import domaine.Tache;
import domaine.Tache_Membre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TacheDal {

	public static int EnregistrerTaches(Tache t, Operation o){
		
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query1="insert into Tache (idTache,nomTache,descriptionTache,dateDebutTache,dateFinTache,etatTache) VALUES (?,?,?,?,?,?)";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setInt(1, t.getIdTache());
			preSmt1.setString(2, t.getNomTache());
			preSmt1.setString(3, t.getDescriptionTache());
			preSmt1.setString(4,t.getDateDebutTache());
			preSmt1.setString(5, t.getDateFinTache());
			preSmt1.setString(6, t.getEtatTache());
			preSmt1.executeUpdate();
			
			preSmt2 = connect.prepareStatement(query2);
			preSmt2.setInt(1, o.getIdOperation());
			preSmt2.setString(2, o.getOperation());
			preSmt2.setString(3, o.getDateoperation());
			preSmt2.setInt(4, o.getIdMembre());
			preSmt2.executeUpdate();
			
			connect.commit();
			result=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}
		
		finally{
			try {
				if (preSmt1 != null) {
					preSmt1.close();
				}

				if (preSmt2 != null) {
					preSmt2.close();
				}

				if (connect != null) {
					connect.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}

	public static int addTacheToMembre(Tache_Membre t_m, Operation o){
		
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query1="insert into Tache_Membre (idMembre,idTache,dateAttribution,etatAttribution) VALUES (?,?,?,?)";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setInt(1, t_m.getIdMembre());
			preSmt1.setInt(2, t_m.getIdTache());
			preSmt1.setString(3, t_m.getDateAttribution());
			preSmt1.setString(4,t_m.getEtatAttribution());
			preSmt1.executeUpdate();
			
			preSmt2 = connect.prepareStatement(query2);
			preSmt2.setInt(1, o.getIdOperation());
			preSmt2.setString(2, o.getOperation());
			preSmt2.setString(3, o.getDateoperation());
			preSmt2.setInt(4, o.getIdMembre());
			preSmt2.executeUpdate();
			
			connect.commit();
			result=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}
		
		finally{
			try {
				if (preSmt1 != null) {
					preSmt1.close();
				}

				if (preSmt2 != null) {
					preSmt2.close();
				}

				if (connect != null) {
					connect.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}
	
	public static ObservableList<Tache> getTacheMainFlow(){
		
		ObservableList<Tache> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		
		try {
			ResultSet rs= conn.createStatement().executeQuery("select * from Tache");
			while(rs.next()){
				dt.add(new Tache(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
		
	}
	
	public static ObservableList<Tache> getTacheMembre(int idMembre){
		
		ObservableList<Tache> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		
		try {
			String sql="SELECT tache.idtache,tache.nomTache,tache.descriptionTache,tache.etatTache from tache INNER JOIN tache_membre ON tache.idTache=tache_membre.idTache AND tache_membre.idMembre = ?";
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setInt(1, idMembre);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.add(new Tache(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}

	public static int ModifierTache(Tache t, Operation o,int idTache){
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query1="update Tache set nomTache=?,descriptionTache=?,dateDebutTache=?,dateFinTache=?,etatTache=? where idTache=?)";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setString(1, t.getNomTache());
			preSmt1.setString(2, t.getDescriptionTache());
			preSmt1.setString(3,t.getDateDebutTache());
			preSmt1.setString(4, t.getDateFinTache());
			preSmt1.setString(5, t.getEtatTache());
			preSmt1.setInt(6, idTache);
			preSmt1.executeUpdate();
			
			preSmt2 = connect.prepareStatement(query2);
			preSmt2.setInt(1, o.getIdOperation());
			preSmt2.setString(2, o.getOperation());
			preSmt2.setString(3, o.getDateoperation());
			preSmt2.setInt(4, o.getIdMembre());
			preSmt2.executeUpdate();
			
			connect.commit();
			result=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}
		
		finally{
			try {
				if (preSmt1 != null) {
					preSmt1.close();
				}

				if (preSmt2 != null) {
					preSmt2.close();
				}

				if (connect != null) {
					connect.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}

	public static int SupprimerTache(Operation o,int idTache){
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		String query1="delete from Tache where idTache=?)";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setInt(1, idTache);
			preSmt1.executeUpdate();
			
			preSmt2 = connect.prepareStatement(query2);
			preSmt2.setInt(1, o.getIdOperation());
			preSmt2.setString(2, o.getOperation());
			preSmt2.setString(3, o.getDateoperation());
			preSmt2.setInt(4, o.getIdMembre());
			preSmt2.executeUpdate();
			
			connect.commit();
			result=1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
		}
		
		finally{
			try {
				if (preSmt1 != null) {
					preSmt1.close();
				}

				if (preSmt2 != null) {
					preSmt2.close();
				}

				if (connect != null) {
					connect.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		return result;
	}

	public static ObservableList<String> getListNomTache(){
		
		ObservableList<String> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		
		try {
			String sql="SELECT tache.nomTache from tache where tache.etatTache = ?";
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, "Disponible");
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.addAll(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}

	public static ObservableList<Tache> getTacheFromNomTache(String nomTache){
		
		ObservableList<Tache> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		
		try {
			String sql="select * from tache where tache.nomTache = ?";
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, nomTache);
			ResultSet rs=preSmt1.executeQuery();
			while(rs.next()){
				dt.add(new Tache(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}
}
