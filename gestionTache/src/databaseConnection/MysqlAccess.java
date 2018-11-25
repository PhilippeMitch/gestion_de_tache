package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlAccess {
	
	public static Connection connect;
	
	public static Connection getConnection() {
		String userName="root";
		String passUser="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/gestionTache",userName,passUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
	}
}
