import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreatePatientFrame extends JFrame {

	private JTextField firstnametextField;
	private JTextField lastnamtextField;
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
	private JPanel contentPane;
	
	private JRadioButton maleRadioButton;

	
	private JRadioButton  femaleRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatePatientFrame frame = new CreatePatientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreatePatientFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setTitle("Create a new Patient");
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][100][100][100][100][100][100]", "[100][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		JLabel lblViewPatientInformation = new JLabel("Create a new Patient by inputting Information");
		contentPanel.add(lblViewPatientInformation, "cell 2 0 5 1,alignx center,aligny center");
		
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
		
		JLabel lblLastName = new JLabel("Last Name:");
		BasicPanel.add(lblLastName, "cell 0 2");
		
		lastnamtextField = new JTextField();
		BasicPanel.add(lastnamtextField, "cell 1 2");
		lastnamtextField.setColumns(10);
		
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
		
	
		JPanel savebtnPanel = new JPanel();
		contentPanel.add(savebtnPanel, "cell 7 11,grow");
		savebtnPanel.setLayout(new BorderLayout(0, 0));
		
		btnSave = new JButton("Create");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(updateDatabase())
				JOptionPane.showMessageDialog(new JFrame(),
					    "Changes Saved Successfully in database.");
			}
		});
		savebtnPanel.add(btnSave, BorderLayout.CENTER);
		
		//TODO: Add formats to all the text fields. EX: Phone text field only length of number. DOB field. Specify length for the other fields as well.

		//setAllUnEditable();
		setFormatting();
		
	}
	
	public Boolean updateDatabase(){
		
		String firstName = firstnametextField.getText();
		String lastName = lastnamtextField.getText();
		String middleName= middlenametextField.getText();
		String dateofbirth = dobTextField.getText(); //02/22/2016
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
		Boolean update = false;
		Boolean wasUpdated = false;
		
		try {
			dao = new NimbusDAO();
			wasUpdated = dao.changePatientData(update, 0, firstName,  middleName,  lastName,  dateofbirth,
					 age,  gender,  address,  city,  state,  zip,  homephone,  mobilephone,
					 emailtext, faxtext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wasUpdated;
	
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

}
