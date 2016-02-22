//package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection sqlconn = getConnection();
		
		Statement stmt = sqlconn.createStatement();
		
		String sqlQuery = "SELECT address from NCMSE.[NCM].[Patient_Test] where FirstName = 'Jason'";
		
		ResultSet rs = stmt.executeQuery(sqlQuery);
		
		//You have to add the rs.next or else it wont get the first row. I guess the result set points to the row before the first row? Normally they use while(rs.next()) 
		if(rs.next()){
		String address = rs.getString("address");
		
		System.out.println("Queried Patient_Test Table to get: " + address);
		}
		else
			System.out.println("No Rows?");
	}

	public static Connection getConnection() throws Exception{
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
	

		

}
