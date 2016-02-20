package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
		
	}

	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://ncmse.cnsk3lzn30dj.us-west-2.rds.amazonaws.com:3306/NCMSE";
			String user = "CSCE3513";
			String password = "Nimbusdataserver";
			Class.forName(driver);
			
			
			Connection conn = DriverManager.getConnection(url, user, password);//
			System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}


		
		return null;
	}
	

		

}
