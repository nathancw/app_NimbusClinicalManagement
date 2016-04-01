
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
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		
		Date dob;
		//Set to nulls so it doesnt affect the query. We shouldn't ever place any nulls in the database. This is lazy but much
		//quicker than building dyanamic queries.
		if((firstName.isEmpty())){
			firstName = null;
		}
		if((lastName.isEmpty())){
			lastName = null;
		}
		
		if(dateofbirth.equals("") || dateofbirth.equals("  /  /    ")){
			dob = parseTextFieldDate("00/00/0000");
		}
		else
			dob = parseTextFieldDate(dateofbirth);
		 
		//System.out.println("FirstName: " + firstName + " LastName: " + lastName + " id: " + id + " dob: " + dob);
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
	
	public ResultSet getAppointmentDetails(int procedureID, String firstName, String lastName, int id, int appointmentID){
		
	
		//Set to nulls so it doesnt affect the query. We shouldn't ever place any nulls in the database. This is lazy but much
		//quicker than building dyanamic queries.
		if((firstName.isEmpty())){
			firstName = null;
		}
		if((lastName.isEmpty())){
			lastName = null;
		}
		
		String sqlQuery = "SELECT TOP 20 app.[Appointment_ID],app.[Patient_ID],pat.FirstName,pat.LastName,doc.CombinedName,pro.ProcedureName,app.[Date],timeSlot.StartTime,timeSlot.EndTime,app.Comments,app.SendEmail,app.CheckedIn " +
				"FROM [NCMSE].[NCM].[Appointment] app " +
				"inner join  " +
				"[NCMSE].[NCM].[Clinical_Procedures] pro on app.Procedure_ID = pro.procedure_ID " +
				"inner join " +
				"[NCMSE].[NCM].[Patient] pat on app.Patient_ID = pat.Patient_ID " +
				"inner join " +
				"[NCMSE].[NCM].[Doctor] doc on doc.Doctor_ID = app.Doctor_ID " +
				"inner join " +
				"[NCMSE].[NCM].[TimeSlot] timeSlot on timeSlot.TimeSlot_ID = app.TimeSlot_ID  " +
				"where app.Procedure_ID = ? or pat.FirstName = ? or pat.LastName = ? or app.Patient_ID = ? or app.appointment_ID = ?";
	
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		try {

			///Prepare and execute query
			stmt = conn.prepareStatement(sqlQuery);
			stmt.setInt(1,procedureID);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setInt(4,id);
			stmt.setInt(5,appointmentID);
			ResultSet rs = stmt.executeQuery();	
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	

	/* Change patient details takes in every value of patient table. This is the method to insert new values into the database
	 * or change existing patient data. The boolean update will determine what you want to do, if its false, you just want to insert
	 * into the table and not update it. This determines which query to update
	 *TODO: Implement Update feature, but you need massive error handling for blank parameters. The other option is to create an entirely new
	 * method which updates and leave this one as inserting
	 */
	public Boolean changePatientData(boolean update, int patient_ID, String firstName, String middleName, String lastName, String dateofbirth,
			String age, String gender, String address, String city, String state, String zip, String homephone, String mobilephone,
			String emailtext, String faxtext){
		
		String sqlQuery = null;
		
		if(!update){
		sqlQuery = "insert into [NCMSE].[NCM].[Patient]" +
				"(FirstName,MiddleName,LastName,DateOfBirth,Age,Sex,Address,City,State,Zip,HomePhone,Mobile,Email,Fax)" +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
		}
		else{
			sqlQuery = "update [NCMSE].[NCM].[Patient] set FirstName = ?,MiddleName = ?,LastName = ?,DateOfBirth = ?,Age = ?,Sex = ?,Address = ?,City = ?,"
			+ "State= ?,Zip= ?,HomePhone= ?,Mobile= ?,Email= ?,Fax= ? where Patient_ID = " + Integer.toString(patient_ID);
			
			
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
	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;

		
	}

	public Date parseTextFieldDate(String dateofbirth){
		
		String dobYMD = dateofbirth.substring(6,10) + "-" + dateofbirth.substring(0,2) + "-" + dateofbirth.substring(3,5);
		//System.out.println("Dobymd: " + dobYMD);
		Date dob = null;	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println("at 0: " + (int)dateofbirth.charAt(0));
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

	public ResultSet checkCredintials(String userName, char[] password){
		
		String sqlQuery = "Select * from NCMSE.dbo.Account where Username = ? and password = ?";
		ResultSet rs = null;
		//We are going to try to create a connection to the database using the DAO and then query it.
		//Need to create a prepared statement so you can avoid sql injection and tie the questions to variables
		//http://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
		//Security Needs to be implemented thats for sure, but i will look into that later
		
		try {
			
			PreparedStatement stmt = this.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, userName);
			stmt.setString(2, new String(password));

			rs = stmt.executeQuery();
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void createAccount(String fname, String lname, String username, String password, int access){
		
		String query;
		
		query = "insert into [NCMSE].[DBO].[Account]" +
		"(Username,Password,Salt,AccessLevel,FirstName,LastName)" +
		"VALUES (?,?,?,?,?,?)";
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		
		Random rand = new Random();
		
		int salt = rand.nextInt((99999 - 10000) + 1) + 10000;
		
		try {
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, username);
			stmt.setString(2,  password);
			stmt.setInt(3, salt);
			stmt.setInt(4,  access);
			stmt.setString(5,  fname);
			stmt.setString(6,  lname);
			
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addDoctor() {
		
	}
	
	public ResultSet getAccountPassword(String username) {
		String sqlQuery = "Select * from [NCMSE].[DBO].[Account] " +
				"where Username = ?";
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			stmt = conn.prepareStatement(sqlQuery);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void changePassword(String username, String password) {
		
		String sqlQuery = "update [NCMSE].[DBO].[Account] set Password = ? where Username = ?";
		
		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sqlQuery);
			
			stmt.setString(1, password);
			stmt.setString(2, username);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int changeAppointment(boolean update, String patient_ID,int doctor_ID,int procedure_ID,Date date,boolean sendEmail,boolean checkedIn,
			int timeSlot_ID,String comments, int appID){
		
		String sqlQuery = null;
		
		if(!update)
			sqlQuery = "insert into NCMSE.NCM.Appointment (Patient_ID,Doctor_ID,Procedure_ID,Date,SendEmail,CheckedIn,TimeSlot_ID,Comments)" +
					   "values (?,?,?,?,?,?,?,?)";
		else
			sqlQuery = " update NCMSE.NCM.Appointment set Patient_ID = ?, Doctor_ID = ?,Procedure_ID = ?,Date = ?,SendEmail = ?,CheckedIn = ?,TimeSlot_ID = ?,Comments = ? " +
					   "where appointment_ID  = " + appID;
			
		
		System.out.println("Values: " + patient_ID + "," + doctor_ID + "," + procedure_ID + "," + date + "," + sendEmail + "," + checkedIn + "," + timeSlot_ID + "," + comments);
		
		if(patient_ID == "" || patient_ID.isEmpty() || patient_ID.length() != 6)
			return 1;
		if(date == null)
			return 2;
		
		Connection sqlconn = this.getConnection();
		PreparedStatement stmt = null;
		try {
			
			stmt = sqlconn.prepareStatement(sqlQuery);
			
			stmt.setInt(1, Integer.parseInt(patient_ID));
			stmt.setInt(2, doctor_ID);
			stmt.setInt(3, procedure_ID);
			stmt.setDate(4, new java.sql.Date(date.getTime()));
			stmt.setBoolean(5, sendEmail);
			stmt.setBoolean(6, checkedIn);
			stmt.setInt(7, timeSlot_ID);
			stmt.setString(8, comments);
			stmt.executeUpdate();		
		
			stmt.close();
			//sqlconn.close();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 10;
		
		
	}
	
	public ResultSet getAppointmentDetails(){
	
		return null;
	}
	
	public int getMaxPatientIDNumber(){
		
		int patient_ID = 0;
		String sqlQuery = "select max(patient_ID) as Patient_ID from NCMSE.NCm.Patient";	

		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		try {

			stmt = sqlconn.prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();	
			
			if(rs.next())
			return rs.getInt("Patient_ID");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return patient_ID;
	}
	
	public void editBillingHistory(boolean update, int patient_ID,int procedure_ID, double amount, Date date){
		
		String sqlQuery = null;
		
		if(!update)
		sqlQuery = "insert into NCMSE.NCM.Billing (Patient_ID,Procedure_ID,Amount,DateIssued,ChargeDate,Paid) " +
						"values(?,?,?,?,?,0)";
		
		ResultSet rs = null;
		
		try {
			
			PreparedStatement stmt = this.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, patient_ID);
			stmt.setInt(2, procedure_ID);
			stmt.setDouble(3, amount);
			stmt.setDate(4, new java.sql.Date(date.getTime()));
			stmt.setDate(5, new java.sql.Date(date.getTime()));
			
			stmt.executeUpdate();
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getBillingHistory(int patient_ID){
		
		String sqlQuery = "Select * from NCMSE.ncm.Billing where patient_ID = ?";
		ResultSet rs = null;
		
		try {
			
			PreparedStatement stmt = this.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, patient_ID);

			rs = stmt.executeQuery();
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
}
