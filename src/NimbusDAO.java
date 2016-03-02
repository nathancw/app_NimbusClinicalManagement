//package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NimbusDAO {
	public static Connection sqlconn;
	public NimbusDAO() throws Exception {
		sqlconn = createConnection();
	}

	public static Connection createConnection() throws Exception{
		try {
			
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://ncmse.cnsk3lzn30dj.us-west-2.rds.amazonaws.com\\NCMSE:1433";
			String user = "CSCE3513";
			String password = "Nimbusdataserver";
			Class.forName(driver);
			
			
			Connection conn = DriverManager.getConnection(url, user, password);//
			System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}


		
		return null;
	}
	
	public static Connection getConnection(){
		return sqlconn;
		
	}
	
	//The other idea is to Create one DAO method for each query we need to execute. 
	//There should be some overlaps such as we will probably have to execute Getpatientdetails() for multiple parameters multiple times.
	//This would allow us to reuse that method. but doing this would make the DAO class really really really large. I will have to debate it a little bit.
	//Bad bad logic
	/*public ResultSet query(String sqlQuery) throws SQLException{
		
		Statement stmt = sqlconn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sqlQuery);
				
		return null;
		
		
		
	} */

		

}
