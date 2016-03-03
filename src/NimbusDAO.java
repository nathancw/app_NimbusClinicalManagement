//package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NimbusDAO {
	Connection sqlconn;
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
	
	public Connection getConnection(){
		return sqlconn;
	}
	
	public void closeConnection() throws SQLException{
		sqlconn.close();
	}
	
	/* This method is designed to getPatientdetails no matter what you are inserting
	 * So the query will use the Passed in parameters in the query. But there is still a ton of issues with
	 *querying on multiple parameters. Currently, it returns everything 
	 * May need some error handleing when the paramters are empty strings. This will need some sql knowledge
	 */
	public ResultSet getPatientDetails(int id, String firstName, String lastName, String dateofbirth){
		
				//hard coding some error handeling 
				Date dob;
				if(dateofbirth.equals(""))
					dob = parseTextFieldDate("01/01/2016");
				else
					dob = parseTextFieldDate(dateofbirth);
				 
				String sqlQuery = "Select * from [NCMSE].[NCM].[Patient]" +
							"where FirstName = ? or LastName = ? or DateOfBirth = ? or Patient_ID = ?";	
			
				Connection conn = this.getConnection();
				PreparedStatement stmt = null;
				try {

					///Prepare and execute query
					stmt = conn.prepareStatement(sqlQuery);
					stmt.setString(1, firstName);
					stmt.setString(2, lastName);
					stmt.setDate(3, new java.sql.Date(dob.getTime()));
					stmt.setInt(4, id);
					ResultSet rs = stmt.executeQuery();	
					
					return rs;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
	}

	/* Change patient details takes in every value of patient table. This is the method to insert new values into the datbase
	 * or change existing patient data. The boolean update will determine what you want to do, if its false, you just want to insert
	 * into the table and not update it. This determines which query to update
	 *TODO: Implement Update feature, but you need massive error handling for blank parameters. The other option is to create an entirely new
	 * method which updates and leave this one as inserting
	 */
	public Boolean changePatientData(boolean update, String firstName, String middleName, String lastName, String dateofbirth,
			String age, String gender, String address, String city, String state, String zip, String homephone, String mobilephone,
			String emailtext, String faxtext){
		
		String sqlQuery = null;
		
		
		if(!update){
		sqlQuery = "insert into [NCMSE].[NCM].[Patient]" +
				"(FirstName,MiddleName,LastName,DateOfBirth,Age,Sex,Address,City,State,Zip,HomePhone,Mobile,Email,Fax)" +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
		}
		Connection sqlconn = this.getConnection();
		PreparedStatement stmt = null;
		try {
			
			Date dob = parseTextFieldDate(dateofbirth);
			stmt = sqlconn.prepareStatement(sqlQuery);
			
			stmt.setString(1, firstName);
			stmt.setString(2, middleName);
			stmt.setString(3, lastName);
			stmt.setDate(4, new java.sql.Date(dob.getTime()));
			stmt.setString(5, age);
			stmt.setString(6, gender);
			stmt.setString(7, address);
			stmt.setString(8, city);
			stmt.setString(9, state);
			stmt.setString(10, zip);
			stmt.setString(11, homephone);
			stmt.setString(12, mobilephone);
			stmt.setString(13, emailtext);
			stmt.setString(14, faxtext); 
			stmt.executeUpdate();		
		
			stmt.close();
			sqlconn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;

		
	}

	public Date parseTextFieldDate(String dateofbirth){
		
		String dobYMD = dateofbirth.substring(6,10) + "-" + dateofbirth.substring(3,5) + "-" + dateofbirth.charAt(0) + dateofbirth.charAt(1);
		
		Date dob = null;	
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		System.out.println("at 0: " + (int)dateofbirth.charAt(0));
		try {
			if((dateofbirth.charAt(0) != 32))
			dob = df.parse(dobYMD);
			else{
				//Set to todays date if none is specified
				dob = Calendar.getInstance().getTime();;
				dob = df.parse(dobYMD);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		return dob;
	}
}
