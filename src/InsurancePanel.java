

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;

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
	private JTextField txtType1;
	private JTextField txtType2;

	/**
	 * Create the panel.
	 */
	public InsurancePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100][100][100][100][100,grow]", "[100,grow][100][100,grow][100][100][100][100]"));
		
		JLabel lblInsuranceInfo = new JLabel("<html>Patient Insurance Information <br><br> Patient Name: Bob Smith </html>");
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
		
		JLabel lblType1 = new JLabel("Type:");
		lblType1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblType1, "cell 0 5,alignx left");
		
		txtType1 = new JTextField();
		txtType1.setText("401k");
		txtType1.setColumns(10);
		panel_1.add(txtType1, "cell 1 5 2 1,growx");
		
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
		
		JLabel lblType2 = new JLabel("Type:");
		lblType2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblType2, "cell 0 5,alignx left");
		
		txtType2 = new JTextField();
		txtType2.setText("401k");
		txtType2.setColumns(10);
		panel_2.add(txtType2, "cell 1 5 2 1,growx");

	}

}
