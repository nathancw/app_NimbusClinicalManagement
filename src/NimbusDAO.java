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
	
	//Bad bad logic
	/*public ResultSet query(String sqlQuery) throws SQLException{
		
		Statement stmt = sqlconn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sqlQuery);
				
		return null;
		
		
		
	} */

		

}
