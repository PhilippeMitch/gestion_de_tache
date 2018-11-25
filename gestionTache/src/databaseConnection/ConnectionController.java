package databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionController {
	
	Connection connect;
	
	public ConnectionController(){
		 connect = MysqlAccess.getConnection();
		 if (connect == null) {
		     System.out.println("connection not successful");
		     System.exit(1);
		 }
	}
	
	public boolean isDbConnected(){
		try {
			 
			return !connect.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
	
	public boolean isLogin(String user, String pass) throws SQLException{
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String query="select * from user where IdUser=? and PassUser=?";
		try {
			preparedStatement =(PreparedStatement) connect.prepareStatement(query);
			preparedStatement.setString(1,user);
			preparedStatement.setString(2,pass);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			}else{
				
				return false;
				}
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}finally {
			preparedStatement.close();
			resultSet.close();
		}
		
		
		
		
	}
	
}
