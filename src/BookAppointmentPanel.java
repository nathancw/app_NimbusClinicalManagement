import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JCheckBox;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


public class BookAppointmentPanel extends JPanel {
	private JTextField patientIDTextField;
	private NimbusDAO dao = null;
	private JTextField specialtytextField;
	private JComboBox endcomboBox;
	private JDatePickerImpl datePicker;
	private JComboBox doctorComboBox;
	private JComboBox startcomboBox;
	private JComboBox procedurecomboBox;
	private JCheckBox chckbxSendEmail;
	private JCheckBox chckbxChckedIn;
	private	JTextArea textArea;
	private int timeID;
	private Map<String,Integer> doctorIDs;
	private Map<String,Integer> procedureIDs;
	private Map<Integer,Double> procedureCosts;
	private Map<Integer,String> specialtyName;
	private int appID;
	private JButton btnBookNewAppointment;
	private JPanel editSavePanel;
	private JPanel contentPanel;
	private UtilDateModel model;
	private JButton btnEdit;
	private JButton btnSave;
	/**
	 * Create the panel.
	 */
	public BookAppointmentPanel() {
		try {
			dao = new NimbusDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appID = 0;
		drawPanel();
	}
	
	public BookAppointmentPanel(int appointmentID){
		try {
			dao = new NimbusDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.appID = appointmentID;
		drawPanel();
		populate(appID);
		//setAllUneditable();
		//addEditSaveButtons();
	}
	
	public void drawPanel(){
		
		setLayout(new BorderLayout(0, 0));
		
		contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][150][100][150][100][100]", "[100][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		Image image;
		try {
			image = ImageIO.read(new File("Pictures\\AppointIcon.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			contentPanel.add(picLabel, "flowx,cell 0 0 2 1,alignx center,aligny top");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JLabel lblNewLabel_2 = new JLabel("Schedule Patient Appointment");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblNewLabel_2, "cell 2 0 4 1,alignx center");
		
		JPanel TextFieldsPanel = new JPanel();
		TextFieldsPanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(TextFieldsPanel, "cell 2 1 4 5,grow");
		TextFieldsPanel.setLayout(new MigLayout("", "[80][150,grow][100][150,grow][20]", "[30][30][30][30][30]"));
		
		JLabel lblPatientID = new JLabel("Patient ID:");
		TextFieldsPanel.add(lblPatientID, "cell 0 0,alignx trailing");
		
		patientIDTextField = new JTextField();
		TextFieldsPanel.add(patientIDTextField, "cell 1 0,growx");
		patientIDTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		TextFieldsPanel.add(lblDate, "cell 2 0,alignx trailing");
		//
		
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		
		TextFieldsPanel.add(datePicker, "cell 3 0,growx");
		///		
		///
		JLabel lblDoctor = new JLabel("Doctor:");
		TextFieldsPanel.add(lblDoctor, "cell 0 1,alignx trailing");
		
		doctorIDs = null;
		doctorComboBox = new JComboBox();
		doctorComboBox.setModel(getDoctors());
		doctorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Need to implement doctor procedures tied to the specialty id
				procedurecomboBox.setModel(getProcedures());
				specialtytextField.setText(specialtyName.get(doctorIDs.get(doctorComboBox.getSelectedItem()))); 
			}

		});
		TextFieldsPanel.add(doctorComboBox, "cell 1 1,growx");
		
		
		JLabel lblStartTime = new JLabel("Start Time:");
		TextFieldsPanel.add(lblStartTime, "cell 2 1,alignx trailing");
		
		timeID = 0;
		startcomboBox = new JComboBox();
		startcomboBox.setModel(getStartTimes());
		startcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Time Changed");
				String startTime = (String) ((JComboBox) e.getSource()).getSelectedItem();
				String endTime = getEndTime(startTime);
				endcomboBox.addItem(endTime);
				endcomboBox.setSelectedItem(endTime);
			}
		});
		TextFieldsPanel.add(startcomboBox, "cell 3 1,growx");
		
		JLabel lblProcedure = new JLabel("Procedure:");
		TextFieldsPanel.add(lblProcedure, "cell 0 2,alignx trailing");
		procedurecomboBox = new JComboBox();
		TextFieldsPanel.add(procedurecomboBox, "cell 1 2,growx");
		
		JLabel lblEndTime = new JLabel("End Time:");
		TextFieldsPanel.add(lblEndTime, "cell 2 2,alignx trailing");
		
		endcomboBox = new JComboBox();
		TextFieldsPanel.add(endcomboBox, "cell 3 2,growx");
		endcomboBox.setEditable(false);
		
		procedureIDs = null;
		
		JLabel lblSpecialty = new JLabel("Specialty:");
		TextFieldsPanel.add(lblSpecialty, "cell 0 3,alignx trailing");
		
		//specialtyName = new HashMap<Integer,String>();
		specialtytextField = new JTextField();
		TextFieldsPanel.add(specialtytextField, "cell 1 3,growx");
		specialtytextField.setColumns(10);
		specialtytextField.setEditable(false);
		
		chckbxSendEmail = new JCheckBox("Send Email");
		TextFieldsPanel.add(chckbxSendEmail, "cell 3 3,alignx left");
		
		chckbxChckedIn = new JCheckBox("Checked In?");
		TextFieldsPanel.add(chckbxChckedIn, "cell 3 4,alignx left");
		
		JPanel CommentsPanel = new JPanel();
		CommentsPanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(CommentsPanel, "cell 2 7 4 5,grow");
		CommentsPanel.setLayout(new MigLayout("", "[100,grow][150][100][150]", "[30,grow][30][30][30][30]"));
		
		textArea = new JTextArea();
		CommentsPanel.add(textArea, "cell 0 0 4 5,grow");
		
		/*JButton btnViewOpenAppointments = new JButton("View Open Appointments");
		btnViewOpenAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Do we want to preview a table of open appointments and have them click one easier use? It will auto fille data in the form.");
			
			}
		});
		
		contentPanel.add(btnViewOpenAppointments, "cell 2 12 2 1");
		*/
		
		JButton btnCancel = new JButton("Cancel");
		contentPanel.add(btnCancel, "cell 4 12,alignx right");
		
		editSavePanel = new JPanel();
	
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAllEditable();
			}
		});
		editSavePanel.add(btnEdit);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(changeAppointmentData(true))
					setAllUneditable();
			}
		});
		editSavePanel.add(btnSave);
		
		btnSave.setEnabled(false);
		
		btnBookNewAppointment = new JButton("Book New Appointment");
		btnBookNewAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeAppointmentData(false);
			}
		});
		contentPanel.add(btnBookNewAppointment, "cell 5 12");

	}
	
	public void populate(int appointmentID){
		
		contentPanel.remove(btnBookNewAppointment);
		contentPanel.add(editSavePanel, "cell 5 12,grow");
		
		 
		try {
			ResultSet rs = dao.getAppointmentDetails(0,"","",0,appointmentID);
			if(rs.next()){
				patientIDTextField.setText(rs.getString("Patient_ID"));
				
				 doctorComboBox.setSelectedItem(rs.getString("CombinedName"));
				 startcomboBox.setSelectedItem(rs.getString("StartTime"));
				 procedurecomboBox.setSelectedItem(rs.getString("ProcedureName"));
				
				
				 if(rs.getInt("SendEmail") == 1)
					 chckbxSendEmail.setSelected(true);
				 
				 if(rs.getInt("CheckedIn")==1)
					 chckbxChckedIn.setSelected(true);
				 //reasoncomboBox;
				 
				textArea.setText(rs.getString("Comments"));
				specialtytextField.setText(specialtyName.get(doctorIDs.get(doctorComboBox.getSelectedItem()))); 
				endcomboBox.setSelectedItem(rs.getString("EndTime"));
				
				//Date
				java.util.Date newDate = rs.getDate("Date");
				Calendar cal = Calendar.getInstance(TimeZone.getDefault());
				cal.setTime(newDate);
				model.setDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
				model.setSelected(true);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setAllUneditable();
	}
	
	public void setAllUneditable(){
		
		UIManager.put("ComboBox.disabledForeground", Color.black);
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		
		patientIDTextField.setEditable(false);
		doctorComboBox.setEnabled(false);
		startcomboBox.setEnabled(false);
		procedurecomboBox.setEnabled(false);
		chckbxSendEmail.setEnabled(false);
		 
		chckbxChckedIn.setEnabled(false);
		 //reasoncomboBox;
		 
		textArea.setEnabled(false);
		specialtytextField.setEditable(false); 
		datePicker.setTextEditable(false);
	}
	
	public void setAllEditable(){
		
		btnEdit.setEnabled(false);
		btnSave.setEnabled(true);
		//patientIDTextField.setEditable(true);
		doctorComboBox.setEnabled(true);
		startcomboBox.setEnabled(true);
		procedurecomboBox.setEnabled(true);
		chckbxSendEmail.setEnabled(true);
		 
		chckbxChckedIn.setEnabled(true);
		 //reasoncomboBox;
		 
		textArea.setEnabled(true);
		specialtytextField.setEditable(true); 
		datePicker.setTextEditable(true);
			
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	public DefaultComboBoxModel getStartTimes(){
		DefaultComboBoxModel model = null;
		ArrayList<String> times = new ArrayList<String>();
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.TimeSlot";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				times.add("");
				while(rs.next()){
					times.add(rs.getString("StartTime"));
				}
				
				model = new DefaultComboBoxModel(times.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}	
		
	public String getEndTime(String startTime){
		String endTime = null;
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.TimeSlot where StartTime = ?";
			System.out.println("SqlQUERY: " + sqlQuery + startTime);
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, startTime);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
			
				if(rs.next()){
					endTime = rs.getString("EndTime");
					timeID = rs.getInt("TimeSlot_ID");
					System.out.println("End Time: " + endTime);
				}
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Can't get connection");
		
		return endTime;
	}
	
	public DefaultComboBoxModel getDoctors(){
		
		DefaultComboBoxModel model = null;
		ArrayList<String> doctors = new ArrayList<String>();
		doctorIDs = new HashMap<String,Integer>();
		specialtyName = new HashMap<Integer,String>();
		
		if(dao!=null){
			String sqlQuery = "SELECT TOP 20 d.[Doctor_ID],d.[FirstName],d.[MiddleName],d.[LastName],d.[CombinedName],d.[Specialty_ID],s.SpecialtyName FROM [NCMSE].[NCM].[Doctor] d " +
					"inner join NCMSE.NCM.Doctor_Specialty s   on s.Specialty_ID = d.Specialty_ID";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				doctors.add("");
				while(rs.next()){
					doctors.add(rs.getString("CombinedName"));
					doctorIDs.put(rs.getString("CombinedName"),rs.getInt("Doctor_ID"));
					specialtyName.put(rs.getInt("Doctor_ID"), rs.getString("SpecialtyName"));
				
				}
				
				model = new DefaultComboBoxModel(doctors.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		
	
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}
	
	public DefaultComboBoxModel getProcedures(){
		
		DefaultComboBoxModel model = null;
		ArrayList<String> procedures = new ArrayList<String>();
		procedureIDs = new HashMap<String,Integer>();
		procedureCosts = new HashMap<Integer,Double>();
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.Clinical_Procedures";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				procedures.add("");
				while(rs.next()){
					procedures.add(rs.getString("ProcedureName"));
					procedureIDs.put(rs.getString("ProcedureName"),rs.getInt("Procedure_ID"));
					procedureCosts.put(rs.getInt("Procedure_ID"), rs.getDouble("Cost"));
				}
				
				model = new DefaultComboBoxModel(procedures.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}
	
	
	public boolean changeAppointmentData(boolean update){

		//Find the string value in hashmap and return the dcotorID associated with it
		String patient_ID = patientIDTextField.getText();
		
		int doctorID = 0;
		int procedureID = 0;
		Date datePickerDate= (Date)datePicker.getModel().getValue();
		double amount;
		
		if(procedureIDs == null || doctorIDs == null || datePickerDate == null || procedurecomboBox.getSelectedItem() == "" || timeID == 0){
			
			JOptionPane.showMessageDialog(this,
				    "Empty Fields. Please fill in all information.","Cannot Book Appointment.",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else{
			doctorID = doctorIDs.get(doctorComboBox.getSelectedItem());
			procedureID = procedureIDs.get(procedurecomboBox.getSelectedItem());
			amount = procedureCosts.get(procedureID);
		}
		
		String comments = textArea.getText();
		
		boolean checkedIn = false;
		if(chckbxChckedIn.isSelected())
			checkedIn = true;
		boolean sendEmail = false;
		
		if(chckbxSendEmail.isSelected())
			sendEmail = true;
		
		NimbusDAO dao;
		try {
			dao = new NimbusDAO();
			
			int changeValue = dao.changeAppointment(update,patient_ID,doctorID,procedureID,datePickerDate,sendEmail,checkedIn,
					 timeID,comments,appID);
			
			//Error messages
			if(changeValue == 1){
				JOptionPane.showMessageDialog(this,
					    "Incorrect Patient ID. Must be 6 digits and not empty.","Cannot Book Appointment",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(changeValue == 2){
				JOptionPane.showMessageDialog(this,
					    "Incorrect Date. Please enter a date.","Cannot Book Appointment",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(changeValue == 10){
				JOptionPane.showMessageDialog(this,
					    "Error code 10.","Cannot Book Appointment",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(changeValue == 0)
				JOptionPane.showMessageDialog(new JFrame(),
					    "Booked Appointment.");
		
			dao.editBillingHistory(false,Integer.parseInt(patient_ID),procedureID,amount,datePickerDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
		return true;
	}
}
