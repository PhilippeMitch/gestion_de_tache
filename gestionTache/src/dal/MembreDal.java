package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import databaseConnection.MysqlAccess;
import domaine.Membre;
import domaine.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MembreDal {
	
	public static int EnregistrerMembre(Membre m, Operation o) {
		
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		
		String query1="insert into Membre (idMembre,nomMembre,prenomMembre,telMembre,adresseMembre,mailMembre,membreStatus) VALUES (?,?,?,?,?,?,?) ";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setInt(1, m.getIdMembre());
			preSmt1.setString(2, m.getNomMembre());
			preSmt1.setString(3, m.getPrenomMembre());
			preSmt1.setString(4,m.getTelMembre());
			preSmt1.setString(5, m.getAdresseMembre());
			preSmt1.setString(6, m.getMailMembre());
			preSmt1.setString(7,m.getStatusMembre());
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

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public static int ModifierMembre(Membre m, Operation o,int idMembre) {
		
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		
		String query1="update Membre set nomMembre=?,prenomMembre=?,telMembre=?,adresseMembre=?,mailMembre=? where idMembre=? ";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setString(1, m.getNomMembre());
			preSmt1.setString(2, m.getPrenomMembre());
			preSmt1.setString(3,m.getTelMembre());
			preSmt1.setString(4, m.getAdresseMembre());
			preSmt1.setString(5, m.getMailMembre());
			preSmt1.setInt(6, idMembre);
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
				e.printStackTrace();
			}
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

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static ObservableList<Membre> getMembreMainFlow(){
		
		ObservableList<Membre> dt;
		Connection conn=MysqlAccess.getConnection();
		dt=FXCollections.observableArrayList();
		try {
			ResultSet rs= conn.createStatement().executeQuery("select * from Membre");
			while(rs.next()){
				dt.add(new Membre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(6),rs.getString(5)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}
	
	public static int SupprimerMembre( Operation o,int idMembre) {
		
		Connection connect = null;
		PreparedStatement preSmt1=null;
		PreparedStatement preSmt2=null;
		
		int result=0;
		connect=MysqlAccess.getConnection();
		
		String query1="delete from Membre where idMembre=?";
		String query2="insert into Operation (idOperation,enfoOperation,dateOperation,idMembre) VALUES (?,?,?,?)";
		
		try {
			connect.setAutoCommit(false);
			
			preSmt1 = connect.prepareStatement(query1);
			preSmt1.setInt(1, idMembre);
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

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static int VerificationEnfoMembre(String nom,String prenom,String tel,String adress,String mail){
		int result=0;
		Connection conn=MysqlAccess.getConnection();
		String sql="select * from Membre where nomMembre=? AND prenomMembre=? AND telMembre=? AND adresseMembre=? AND mailMembre=?";
		try {
			PreparedStatement preSmt1= conn.prepareStatement(sql);
			preSmt1.setString(1, nom);
			preSmt1.setString(2, prenom);
			preSmt1.setString(3, tel);
			preSmt1.setString(4, adress);
			preSmt1.setString(5, mail);
			ResultSet rs=preSmt1.executeQuery();
			if(rs.next()){
				result=1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
