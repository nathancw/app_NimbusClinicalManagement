import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BasicInformationPanel extends JPanel {
	private JTextField firstnametextField;
	private JTextField lastnametextField;
	private JTextField middlenametextField;
	private JFormattedTextField dobTextField;
	private JFormattedTextField agetextField;
	private JTextField addresstextField;
	private JTextField citytextField;
	private JFormattedTextField statetextField;
	private JFormattedTextField ziptextField;
	private JTextField patientidtextField;
	private JFormattedTextField homephonetextField;
	private JFormattedTextField mobilephonetextField;
	private JTextField emailtextField;
	private JFormattedTextField faxtextField;
	private JButton btnEdit;
	private JButton btnSave;
	
	private JRadioButton maleRadioButton;

	
	private JRadioButton  femaleRadioButton;
	

	/**
	 * Create the panel.
	 */
	public BasicInformationPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][100][100][100][100][100][100]", "[100][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		JLabel lblViewPatientInformation = new JLabel("View Patient Information");
		contentPanel.add(lblViewPatientInformation, "cell 3 0 3 1,aligny top");
		
		JPanel BasicPanel = new JPanel();
		BasicPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.add(BasicPanel, "cell 1 1 7 3,grow");
		BasicPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100]", "[30][30][30]"));
		
		JLabel lblFirstNamel = new JLabel("First Name:");
		BasicPanel.add(lblFirstNamel, "cell 0 0");
		
		firstnametextField = new JTextField();
		BasicPanel.add(firstnametextField, "cell 1 0");
		firstnametextField.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		BasicPanel.add(lblSex, "cell 2 0,alignx left");
		
		maleRadioButton = new JRadioButton("Male");
		BasicPanel.add(maleRadioButton, "cell 3 0,alignx left");
		
		femaleRadioButton = new JRadioButton("Female");
		BasicPanel.add(femaleRadioButton, "cell 4 0,alignx left");
		
		//Button group so only one can be checked at once
		ButtonGroup group = new ButtonGroup();
		group.add(maleRadioButton);
		group.add(femaleRadioButton);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		BasicPanel.add(lblPatientId, "cell 5 0,alignx left");
		
		patientidtextField = new JTextField();
		BasicPanel.add(patientidtextField, "cell 6 0,growx");
		patientidtextField.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		BasicPanel.add(lblMiddleName, "cell 0 1");
		
		middlenametextField = new JTextField();
		BasicPanel.add(middlenametextField, "cell 1 1");
		middlenametextField.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		BasicPanel.add(lblDateOfBirth, "cell 2 1,alignx left");
		
		dobTextField = new JFormattedTextField();
		BasicPanel.add(dobTextField, "cell 3 1,growx");
		dobTextField.setColumns(10);
		
		MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install((JFormattedTextField) dobTextField);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Testing
		//JButton button = new JButton("Edit");
		//BasicPanel.add(button);
		
		
		JLabel lblLastName = new JLabel("Last Name:");
		BasicPanel.add(lblLastName, "cell 0 2");
		
		lastnametextField = new JTextField();
		BasicPanel.add(lastnametextField, "cell 1 2");
		lastnametextField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		BasicPanel.add(lblAge, "cell 2 2,alignx left");
		
		agetextField = new JFormattedTextField();
		BasicPanel.add(agetextField, "cell 3 2,growx");
		agetextField.setColumns(10);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(addressPanel, "cell 1 5 3 5");
		addressPanel.setLayout(new MigLayout("", "[100][100][100]", "[30][30][30][30]"));
		
		JLabel lblAddress = new JLabel("Address:");
		addressPanel.add(lblAddress, "cell 0 0,alignx left");
		
		addresstextField = new JTextField();
		addressPanel.add(addresstextField, "cell 1 0 2 1,growx");
		addresstextField.setColumns(10);
		addresstextField.setText("Test");
		//addresstextField.setEnabled(false);
		addresstextField.setEditable(false);
		
		JLabel lblCity = new JLabel("City:");
		addressPanel.add(lblCity, "cell 0 1,alignx left");
		
		citytextField = new JTextField();
		addressPanel.add(citytextField, "cell 1 1,growx");
		citytextField.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		addressPanel.add(lblState, "cell 0 2,alignx left");
		
		statetextField = new JFormattedTextField();
		addressPanel.add(statetextField, "cell 1 2,growx");
		statetextField.setColumns(2);
		
		JLabel lblZip = new JLabel("Zip:");
		addressPanel.add(lblZip, "cell 0 3,alignx left");
		
		ziptextField = new JFormattedTextField();
		addressPanel.add(ziptextField, "cell 1 3,growx");
		ziptextField.setColumns(5);
		
		JPanel contactPanel = new JPanel();
		contactPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Contact", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(contactPanel, "cell 5 5 3 5,growy");
		contactPanel.setLayout(new MigLayout("", "[100][100][100]", "[30][30][30][30]"));
		
		JLabel lblPhoneNumber = new JLabel("Home Phone:");
		contactPanel.add(lblPhoneNumber, "cell 0 0,alignx left");
		
		homephonetextField = new JFormattedTextField();
		contactPanel.add(homephonetextField, "cell 1 0,growx");
		homephonetextField.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile:");
		contactPanel.add(lblMobile, "cell 0 1,alignx left");
		
		mobilephonetextField = new JFormattedTextField();
		contactPanel.add(mobilephonetextField, "cell 1 1,growx");
		mobilephonetextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		contactPanel.add(lblEmail, "cell 0 2,alignx left");
		
		emailtextField = new JTextField();
		contactPanel.add(emailtextField, "cell 1 2,growx");
		emailtextField.setColumns(10);
		
		JLabel lblFax = new JLabel("Fax:");
		contactPanel.add(lblFax, "cell 0 3,alignx left");
		
		faxtextField = new JFormattedTextField();
		contactPanel.add(faxtextField, "cell 1 3,growx");
		faxtextField.setColumns(10);
		
		JPanel editbtnPanel = new JPanel();
		contentPanel.add(editbtnPanel, "cell 6 11,grow");
		editbtnPanel.setLayout(new BorderLayout(0, 0));
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAllEditable();
			}
		});
		editbtnPanel.add(btnEdit, BorderLayout.CENTER);
		
		JPanel savebtnPanel = new JPanel();
		contentPanel.add(savebtnPanel, "cell 7 11,grow");
		savebtnPanel.setLayout(new BorderLayout(0, 0));
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean updated = updateDatabase();
				setAllUnEditable();
				if(updated)
					JOptionPane.showMessageDialog(new JFrame(), "Changes Saved Successfully in database.");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Changes not saved successfully. An error occured.");
			}
		});
		savebtnPanel.add(btnSave, BorderLayout.CENTER);
		
		//TODO: Add formats to all the text fields. EX: Phone text field only length of number. DOB field. Specify length for the other fields as well.

		//setAllUnEditable();
		setFormatting();
	}

	public void setAllUnEditable(){
		  btnSave.setEnabled(false);
		  btnEdit.setEnabled(true);
		  firstnametextField.setEditable(false);
		  lastnametextField.setEditable(false);
		  middlenametextField.setEditable(false);
		  dobTextField.setEditable(false);
		  agetextField.setEditable(false);
		  addresstextField.setEditable(false);
		  citytextField.setEditable(false);
		  statetextField.setEditable(false);
		  ziptextField.setEditable(false);
		  patientidtextField.setEditable(false);
		  homephonetextField.setEditable(false);
		  mobilephonetextField.setEditable(false);
		  emailtextField.setEditable(false);
		  faxtextField.setEditable(false);
		
		  //Radio buttons?
		
		  maleRadioButton.setEnabled(false);
		  femaleRadioButton.setEnabled(false);
		
	}
	
	public void setAllEditable(){
		  btnEdit.setEnabled(false);
		  btnSave.setEnabled(true);
		  firstnametextField.setEditable(true);
		  lastnametextField.setEditable(true);
		  middlenametextField.setEditable(true);
		  dobTextField.setEditable(true);
		  agetextField.setEditable(true);
		  addresstextField.setEditable(true);
		  citytextField.setEditable(true);
		  statetextField.setEditable(true);
		  ziptextField.setEditable(true);
		  patientidtextField.setEditable(true);
		  homephonetextField.setEditable(true);
		  mobilephonetextField.setEditable(true);
		  emailtextField.setEditable(true);
		  faxtextField.setEditable(true);
		
		  //Radio buttons?
		  maleRadioButton.setEnabled(false);
		  femaleRadioButton.setEnabled(false);
		
	}
	
	public boolean updateDatabase(){
		
		String firstName = firstnametextField.getText();
		String lastName = lastnametextField.getText();
		String middleName= middlenametextField.getText();
		
		//Sting to date conversion will need to be done
		String dob = dobTextField.getText();
		
		String age = agetextField.getText();
		String address = addresstextField.getText();
		String city = citytextField.getText();
		String state = statetextField.getText();
		String  zip = ziptextField.getText();
		String  patientid = patientidtextField.getText();
		String homephone= homephonetextField.getText();
		String  mobilephone = mobilephonetextField.getText();
		String emailtext = emailtextField.getText();
		String faxtext = faxtextField.getText();
		
		 String gender;
		if(maleRadioButton.isSelected()){
			gender = "M";
		}
		else
			gender = "F";
		
		NimbusDAO dao;
		boolean updated = false;
		try {
			dao = new NimbusDAO();
			updated=  dao.changePatientData(true, Integer.parseInt(patientid), firstName,  middleName,  lastName,  dob,
					 age,  gender,  address,  city,  state,  zip,  homephone,  mobilephone,
					 emailtext, faxtext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return updated;
		
	}
	
	public void setFormatting(){
	
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			MaskFormatter ageText  = new MaskFormatter("##");
			MaskFormatter stateText = new MaskFormatter("UU");
			MaskFormatter zipText = new MaskFormatter("#####");
			
			//Todo: Fix phone formatting in both this and create a patient frame
			//MaskFormatter phone = new MaskFormatter("###/###/###");
			//phone.install(homephonetextField);
			//phone.install(mobilephonetextField);
			//phone.install(faxtextField);
			
			dateMask.install(dobTextField);
			ageText.install(agetextField);
			stateText.install(statetextField);
			zipText.install(ziptextField);
			
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void populate(int Patient_ID){
		setAllUnEditable();
		
		NimbusDAO dao;
		try {
			dao = new NimbusDAO();
			ResultSet rs = dao.getPatientDetails(Patient_ID,"","","");
			
			if(rs.next()){
				
				firstnametextField.setText(rs.getString("FirstName"));
				lastnametextField.setText(rs.getString("LastName"));
				patientidtextField.setText(rs.getString("Patient_ID"));
				middlenametextField.setText(rs.getString("MiddleName"));
				
				agetextField.setText(rs.getString("Age"));
				addresstextField.setText(rs.getString("Address"));
				citytextField.setText(rs.getString("City"));;
				statetextField.setText(rs.getString("State"));;
				ziptextField.setText(rs.getString("Zip"));;
			
				homephonetextField.setText(rs.getString("HomePhone"));;
				mobilephonetextField.setText(rs.getString("Mobile"));;
				emailtextField.setText(rs.getString("Email"));;
				faxtextField.setText(rs.getString("Fax"));;
				
				//FixThis
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date dob = rs.getDate("DateOfBirth");  
				String formattedDate = df.format(dob);

				dobTextField.setText(formattedDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
