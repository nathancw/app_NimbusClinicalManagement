import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JSeparator;


public class BasicInformationPanel extends JPanel {
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField middleNameField;
	private JTextField dobTextField;
	private JTextField agetextField;
	private JTextField addresstextField;
	private JTextField citytextField;
	private JTextField statetextField;
	private JTextField ziptextField;
	private JTextField patientidtextField;
	private JTextField homephonetextField;
	private JTextField mobilephonetextField;
	private JTextField emailtextField;
	private JTextField faxtextField;

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
		BasicPanel.setLayout(new MigLayout("", "[100][100][100][100,grow][100,grow][100][100,grow]", "[30][30][30]"));
		
		JLabel lblFirstNamel = new JLabel("First Name:");
		BasicPanel.add(lblFirstNamel, "cell 0 0");
		
		firstNameField = new JTextField();
		BasicPanel.add(firstNameField, "cell 1 0");
		firstNameField.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex:");
		BasicPanel.add(lblSex, "cell 2 0,alignx left");
		
		JRadioButton maleRadioButton = new JRadioButton("Male");
		BasicPanel.add(maleRadioButton, "cell 3 0,alignx left");
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		BasicPanel.add(rdbtnFemale, "cell 4 0,alignx left");
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		BasicPanel.add(lblPatientId, "cell 5 0,alignx left");
		
		patientidtextField = new JTextField();
		BasicPanel.add(patientidtextField, "cell 6 0,growx");
		patientidtextField.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		BasicPanel.add(lblMiddleName, "cell 0 1");
		
		middleNameField = new JTextField();
		BasicPanel.add(middleNameField, "cell 1 1");
		middleNameField.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		BasicPanel.add(lblDateOfBirth, "cell 2 1,alignx left");
		
		dobTextField = new JTextField();
		BasicPanel.add(dobTextField, "cell 3 1,growx");
		dobTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		BasicPanel.add(lblLastName, "cell 0 2");
		
		lastNameField = new JTextField();
		BasicPanel.add(lastNameField, "cell 1 2");
		lastNameField.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		BasicPanel.add(lblAge, "cell 2 2,alignx left");
		
		agetextField = new JTextField();
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
		
		statetextField = new JTextField();
		addressPanel.add(statetextField, "cell 1 2,growx");
		statetextField.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		addressPanel.add(lblZip, "cell 0 3,alignx left");
		
		ziptextField = new JTextField();
		addressPanel.add(ziptextField, "cell 1 3,growx");
		ziptextField.setColumns(10);
		
		JPanel contactPanel = new JPanel();
		contactPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Contact", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(contactPanel, "cell 5 5 3 5,growy");
		contactPanel.setLayout(new MigLayout("", "[100][100,grow][100]", "[30][30][30][30]"));
		
		JLabel lblPhoneNumber = new JLabel("Home Phone:");
		contactPanel.add(lblPhoneNumber, "cell 0 0,alignx left");
		
		homephonetextField = new JTextField();
		contactPanel.add(homephonetextField, "cell 1 0,growx");
		homephonetextField.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile:");
		contactPanel.add(lblMobile, "cell 0 1,alignx left");
		
		mobilephonetextField = new JTextField();
		contactPanel.add(mobilephonetextField, "cell 1 1,growx");
		mobilephonetextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		contactPanel.add(lblEmail, "cell 0 2,alignx left");
		
		emailtextField = new JTextField();
		contactPanel.add(emailtextField, "cell 1 2,growx");
		emailtextField.setColumns(10);
		
		JLabel lblFax = new JLabel("Fax:");
		contactPanel.add(lblFax, "cell 0 3,alignx left");
		
		faxtextField = new JTextField();
		contactPanel.add(faxtextField, "cell 1 3,growx");
		faxtextField.setColumns(10);
		
		//TODO: Add formats to all the text fields. EX: Phone text field only length of number. DOB field. May require deleting it ans addin as new field. Specify length for the other fields as well.
		//add more buttons to edit. can seteditable to false to grey out the text fields.

		//Set All fields uneditable
		setAllUnEditable();

	}

	public void setAllUnEditable(){
		
		  firstNameField.setEditable(false);
		  lastNameField.setEditable(false);
		  middleNameField.setEditable(false);
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
		
		
	}
	
}
