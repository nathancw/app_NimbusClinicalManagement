

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;

//Added Imports



public class InsurancePanel extends JPanel {
	private JTextField txtCompName1;
	private JTextField txtPatientId1;
	private JTextField txtGroupNum1;
	private JTextField txtPlanStart1;
	private JTextField txtPlanEnd1;
	private JTextField txtPhoneNum1;
	private JTextField txtCompName2;
	private JTextField txtPatientId2;
	private JTextField txtGroupNum2;
	private JTextField txtPlanStart2;
	private JTextField txtPlanEnd2;
	private JTextField txtPhoneNum2;
	private JTextField txttype1;
	private JTextField txttype2;
	private JTextField txttype3;
	private String firstName;
	private String lastName;


	//Pull insurance Information
	
	private  int patientID = 0;
	private  int companyID = 0;
	private  String companyName = "";
	private  String companyAddress = "";
	private  String companyCity = "";
	private  String companyState = "";
	private  int companyZip = 0;
	private  String companyPhone = "";
	private  String type = "";
	
	private  String groupNumber = "";
	private  String planStartDate= "1799";
	private  String planEndDate = "";
	
	
	//Edit & Save Insurance Information
	private JPanel contentPane;
	private JPanel contentPanel;	
	private JPanel bufferPanel;
	private JList categoriesList;
	private JPanel dataPanel;
	private JPanel removeablePanel;
	private int patient_ID;
	
	private JButton btnEdit;
	private JButton btnSave;
	
	
	
	//
	//Changed to display insurance info - Jason Wolverton 4/5/16
	public InsurancePanel(int ID) {
		
		//System.out.print("Hello im insurance panel!");
		patientID = ID;
		
		firstName = "";
		lastName  = "";
		
		
		NimbusDAO daotest;
			try{
				daotest = new NimbusDAO();
				ResultSet rs = daotest.getPatientDetails(patientID,"","","");
				
				if(rs.next()){
					
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					
				}
			
				ResultSet rs3 = daotest.getInsuranceDetails(patientID);
					
				
					if(rs3.next()){
						
						groupNumber = rs3.getString("groupNumber");
						//System.out.print("This is my group #: " + groupNumber);
						if (groupNumber == null){
							groupNumber = "0000";
						}
						if (groupNumber == ""){
							
							groupNumber = "0000";
						}
						planStartDate = rs3.getString("planStartDate");
						planEndDate = rs3.getString("planEndDate");
						companyName = rs3.getString("Name");
						companyAddress = rs3.getString("AAddress");
						companyCity = rs3.getString("City");
						companyState = rs3.getString("CState");
						companyZip = rs3.getInt("Zip");
						companyPhone = rs3.getString("Phone");
						type = rs3.getString("TType");
						
						
				}	
				
			
			daotest.closeConnection();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		///
		/// PANELS
		///
		setLayout(new BorderLayout(0, 0));
		//System.out.print("This is also my start date: " + planStartDate);
		
		JPanel testPanel = new JPanel();
		add(testPanel);
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100][100][100][100][100,grow]", "[100,grow][100][100,grow][100][100][100][100]"));
		
		JLabel lblInsuranceInfo = new JLabel("<html>Patient Insurance Information <br><br> Patient Name: " + firstName + " " + lastName + "</html>");
		lblInsuranceInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(lblInsuranceInfo, "cell 3 0 3 2,alignx center");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "cell 1 2 7 4,grow");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Primary Insurance", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[105][105,grow][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompName1 = new JLabel("Company Name:");
		lblCompName1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblCompName1, "cell 0 0,alignx left");
		
		txtCompName1 = new JTextField();
		txtCompName1.setText(companyName);
		panel_1.add(txtCompName1, "cell 1 0 2 1,growx");
		txtCompName1.setColumns(10);
		
		JLabel lblPhoneNum1 = new JLabel("Phone Number:");
		lblPhoneNum1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPhoneNum1, "cell 3 0,alignx left");
		
		txtPhoneNum1 = new JTextField();
		txtPhoneNum1.setText(companyPhone);
		panel_1.add(txtPhoneNum1, "cell 4 0 2 1,growx");
		txtPhoneNum1.setColumns(10);
		
		JLabel lblPatientId1 = new JLabel("Patient ID:");
		lblPatientId1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPatientId1, "cell 0 1,alignx left");
		
		txtPatientId1 = new JTextField();
		
		//Convert to string to set text without error -Jason Wolverton
		String x = String.valueOf(patientID);
		txtPatientId1.setText(x);
		panel_1.add(txtPatientId1, "cell 1 1 2 1,growx");
		txtPatientId1.setColumns(10);
		
		JLabel lblGroupNum1 = new JLabel("Group Number:");
		lblGroupNum1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblGroupNum1, "cell 0 2,alignx left");
		
		txtGroupNum1 = new JTextField();
		
		String y = String.valueOf(groupNumber);
		txtGroupNum1.setText(y);
		panel_1.add(txtGroupNum1, "cell 1 2 2 1,growx");
		txtGroupNum1.setColumns(10);
		
		JLabel lblPlanStart1 = new JLabel("Plan Start Date:");
		lblPlanStart1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanStart1, "cell 0 3,alignx left");
		
		txtPlanStart1 = new JTextField();
		txtPlanStart1.setText(planStartDate);
		panel_1.add(txtPlanStart1, "cell 1 3 2 1,growx");
		txtPlanStart1.setColumns(10);
		
		JLabel lblPlanEnd1 = new JLabel("Plan End Date:");
		lblPlanEnd1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanEnd1, "cell 0 4,alignx left");
		
		txtPlanEnd1 = new JTextField();
		txtPlanEnd1.setText(planEndDate);
		panel_1.add(txtPlanEnd1, "cell 1 4 2 1,growx");
		txtPlanEnd1.setColumns(10);
		
		JLabel lbltype1 = new JLabel("Type:");
		lbltype1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lbltype1, "cell 0 5,alignx left");
		
		txttype1 = new JTextField();
		txttype1.setText(type);
		txttype1.setColumns(10);
		panel_1.add(txttype1, "cell 1 5 2 1,growx");
		
		
		
		
		///Secondary Insurance
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Secondary Insurance", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[105][105,grow][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompName2 = new JLabel("Company Name:");
		lblCompName2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblCompName2, "cell 0 0,alignx left");
		
		txtCompName2 = new JTextField();
		txtCompName2.setText("Medicare");
		panel_2.add(txtCompName2, "cell 1 0 2 1,growx");
		txtCompName2.setColumns(10);
		
		JLabel lblPhoneNum2 = new JLabel("Phone Number:");
		lblPhoneNum2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPhoneNum2, "cell 3 0,alignx left");
		
		txtPhoneNum2 = new JTextField();
		txtPhoneNum2.setText(companyPhone);
		panel_2.add(txtPhoneNum2, "cell 4 0 2 1,growx");
		txtPhoneNum2.setColumns(10);
		
		JLabel lblPatientId2 = new JLabel("Patient ID:");
		lblPatientId2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPatientId2, "cell 0 1,alignx left");
		
		txtPatientId2 = new JTextField();
		txtPatientId2.setText("985401");
		panel_2.add(txtPatientId2, "cell 1 1 2 1,growx");
		txtPatientId2.setColumns(10);
		
		JLabel lblGroupNum2 = new JLabel("Group Number:");
		lblGroupNum2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblGroupNum2, "cell 0 2,alignx left");
		
		txtGroupNum2 = new JTextField();
		txtGroupNum2.setText("1110");
		panel_2.add(txtGroupNum2, "cell 1 2 2 1,growx");
		txtGroupNum2.setColumns(10);
		
		JLabel lblPlanStart2 = new JLabel("Plan Start Date:");
		lblPlanStart2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanStart2, "cell 0 3,alignx left");
		
		txtPlanStart2 = new JTextField();
		txtPlanStart2.setText("01/01/2016");
		panel_2.add(txtPlanStart2, "cell 1 3 2 1,growx");
		txtPlanStart2.setColumns(10);
		
		JLabel lblPlanEnd2 = new JLabel("Plan End Date:");
		lblPlanEnd2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanEnd2, "cell 0 4,alignx left");
		
		txtPlanEnd2 = new JTextField();
		txtPlanEnd2.setText("01/01/2017");
		panel_2.add(txtPlanEnd2, "cell 1 4 2 1,growx");
		txtPlanEnd2.setColumns(10);
		
		JLabel lbltype2 = new JLabel("Type:");
		lbltype2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lbltype2, "cell 0 5,alignx left");
		
		txttype2 = new JTextField();
		txttype2.setText("401k");
		txttype2.setColumns(10);
		panel_2.add(txttype2, "cell 1 5 2 1,growx");
		
		
		
		///
		///Save Buttons
		///
		
		JButton button1 = new JButton("Save");
		button1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			saveChanges();
			}
		});
		
		panel_1.add(button1,"cell 6 6,growx");
		
		
		JButton button2 = new JButton("Save");
		panel_2.add(button2,"cell 6 6,growx");
		
	}
		
		
		
	public void saveChanges(){
		
		
		
		//Primary Insurance
		companyName = txtCompName1.getText();
		String patientID = txtPatientId1.getText();
		groupNumber = txtGroupNum1.getText();
		planStartDate = txtPlanStart1.getText();
		txtPlanStart1.setText(planStartDate);
		//if(planStartDate == null){
		//	planStartDate = "FAIL";
		//}
		String planEndDate = txtPlanEnd1.getText();
		String type = txttype1.getText();
		
		String phoneNumber = txtPhoneNum1.getText();

		//System.out.print("Hello, i am " + planStartDate);

		NimbusDAO dao;
		
		try {
			dao = new NimbusDAO();
			
			boolean updated = false;
			
			
			
			dao.changeInsuranceData(updated, companyName, patientID, groupNumber, planStartDate, planEndDate, type, phoneNumber);
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(new JFrame(), "Changes Saved Successfully in database.");
		
		//return updated;
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///
	/// GENERIC CONSTRUCTOR
	///
	
	public InsurancePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100][100][100][100][100,grow]", "[100,grow][100][100,grow][100][100][100][100]"));
		
		JLabel lblInsuranceInfo = new JLabel("<html>Patient Insurance Information <br><br> Patient Name: " + firstName + " " + lastName + " </html>");
		lblInsuranceInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(lblInsuranceInfo, "cell 3 0 3 2,alignx center");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "cell 1 2 7 4,grow");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Primary Insurance", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[105][105,grow][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompName1 = new JLabel("Company Name:");
		lblCompName1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblCompName1, "cell 0 0,alignx left");
		
		txtCompName1 = new JTextField();
		txtCompName1.setText("Cigna");
		panel_1.add(txtCompName1, "cell 1 0 2 1,growx");
		txtCompName1.setColumns(10);
		
		JLabel lblPhoneNum1 = new JLabel("Phone Number:");
		lblPhoneNum1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPhoneNum1, "cell 3 0,alignx left");
		
		txtPhoneNum1 = new JTextField();
		txtPhoneNum1.setText("(999) 888-7777");
		panel_1.add(txtPhoneNum1, "cell 4 0 2 1,growx");
		txtPhoneNum1.setColumns(10);
		
		JLabel lblPatientId1 = new JLabel("Patient ID:");
		lblPatientId1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPatientId1, "cell 0 1,alignx left");
		
		txtPatientId1 = new JTextField();
		txtPatientId1.setText("ZGP523847");
		panel_1.add(txtPatientId1, "cell 1 1 2 1,growx");
		txtPatientId1.setColumns(10);
		
		JLabel lblGroupNum1 = new JLabel("Group Number:");
		lblGroupNum1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblGroupNum1, "cell 0 2,alignx left");
		
		txtGroupNum1 = new JTextField();
		txtGroupNum1.setText("HCA000");
		panel_1.add(txtGroupNum1, "cell 1 2 2 1,growx");
		txtGroupNum1.setColumns(10);
		
		JLabel lblPlanStart1 = new JLabel("Plan Start Date:");
		lblPlanStart1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanStart1, "cell 0 3,alignx left");
		
		txtPlanStart1 = new JTextField();
		txtPlanStart1.setText("01/01/2016");
		panel_1.add(txtPlanStart1, "cell 1 3 2 1,growx");
		txtPlanStart1.setColumns(10);
		
		JLabel lblPlanEnd1 = new JLabel("Plan End Date:");
		lblPlanEnd1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanEnd1, "cell 0 4,alignx left");
		
		txtPlanEnd1 = new JTextField();
		txtPlanEnd1.setText("01/01/2017");
		panel_1.add(txtPlanEnd1, "cell 1 4 2 1,growx");
		txtPlanEnd1.setColumns(10);
		
		JLabel lbltype1 = new JLabel("type:");
		lbltype1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lbltype1, "cell 0 5,alignx left");
		
		txttype1 = new JTextField();
		txttype1.setText("401k");
		txttype1.setColumns(10);
		panel_1.add(txttype1, "cell 1 5 2 1,growx");
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Secondary Insurance", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[105][105,grow][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompName2 = new JLabel("Company Name:");
		lblCompName2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblCompName2, "cell 0 0,alignx left");
		
		txtCompName2 = new JTextField();
		txtCompName2.setText("Medicare");
		panel_2.add(txtCompName2, "cell 1 0 2 1,growx");
		txtCompName2.setColumns(10);
		
		JLabel lblPhoneNum2 = new JLabel("Phone Number:");
		lblPhoneNum2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPhoneNum2, "cell 3 0,alignx left");
		
		txtPhoneNum2 = new JTextField();
		txtPhoneNum2.setText("(817) 875-9581");
		panel_2.add(txtPhoneNum2, "cell 4 0 2 1,growx");
		txtPhoneNum2.setColumns(10);
		
		JLabel lblPatientId2 = new JLabel("Patient ID:");
		lblPatientId2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPatientId2, "cell 0 1,alignx left");
		
		txtPatientId2 = new JTextField();
		txtPatientId2.setText("985401");
		panel_2.add(txtPatientId2, "cell 1 1 2 1,growx");
		txtPatientId2.setColumns(10);
		
		JLabel lblGroupNum2 = new JLabel("Group Number:");
		lblGroupNum2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblGroupNum2, "cell 0 2,alignx left");
		
		txtGroupNum2 = new JTextField();
		txtGroupNum2.setText("1110");
		panel_2.add(txtGroupNum2, "cell 1 2 2 1,growx");
		txtGroupNum2.setColumns(10);
		
		JLabel lblPlanStart2 = new JLabel("Plan Start Date:");
		lblPlanStart2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanStart2, "cell 0 3,alignx left");
		
		txtPlanStart2 = new JTextField();
		txtPlanStart2.setText("01/01/2016");
		panel_2.add(txtPlanStart2, "cell 1 3 2 1,growx");
		txtPlanStart2.setColumns(10);
		
		JLabel lblPlanEnd2 = new JLabel("Plan End Date:");
		lblPlanEnd2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanEnd2, "cell 0 4,alignx left");
		
		txtPlanEnd2 = new JTextField();
		txtPlanEnd2.setText("01/01/2017");
		panel_2.add(txtPlanEnd2, "cell 1 4 2 1,growx");
		txtPlanEnd2.setColumns(10);
		
		JLabel lbltype2 = new JLabel("type:");
		lbltype2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lbltype2, "cell 0 5,alignx left");
		
		txttype2 = new JTextField();
		txttype2.setText("401k");
		txttype2.setColumns(10);
		panel_2.add(txttype2, "cell 1 5 2 1,growx");
		

	}

}
