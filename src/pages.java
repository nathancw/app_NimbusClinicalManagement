

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

public class pages extends JPanel {
	private JTextField txtCigna;
	private JTextField txtZgp;
	private JTextField txtHca;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtMedicare;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public pages() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100][100][100][100][100,grow]", "[100,grow][100][100,grow][100][100][100][100]"));
		
		JLabel lblInsuranceInformation = new JLabel("Insurance Information");
		lblInsuranceInformation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(lblInsuranceInformation, "cell 3 1 3 1,alignx center");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "cell 1 2 7 4,grow");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Primary Insurance", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[105][105][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblCompanyName, "cell 0 0,alignx left");
		
		txtCigna = new JTextField();
		txtCigna.setText("Cigna");
		panel_1.add(txtCigna, "cell 1 0 2 1,growx");
		txtCigna.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPhoneNumber, "cell 3 0,alignx left");
		
		textField_2 = new JTextField();
		textField_2.setText("(999) 888-7777");
		panel_1.add(textField_2, "cell 4 0 2 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPatientId, "cell 0 1,alignx left");
		
		txtZgp = new JTextField();
		txtZgp.setText("ZGP523847");
		panel_1.add(txtZgp, "cell 1 1 2 1,growx");
		txtZgp.setColumns(10);
		
		JLabel lblGroupNumber = new JLabel("Group Number:");
		lblGroupNumber.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblGroupNumber, "cell 0 2,alignx left");
		
		txtHca = new JTextField();
		txtHca.setText("HCA000");
		panel_1.add(txtHca, "cell 1 2 2 1,growx");
		txtHca.setColumns(10);
		
		JLabel lblPlanStartDate = new JLabel("Plan Start Date:");
		lblPlanStartDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanStartDate, "cell 0 3,alignx left");
		
		textField = new JTextField();
		textField.setText("01/01/2016");
		panel_1.add(textField, "cell 1 3 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblPlanEndDate = new JLabel("Plan End Date:");
		lblPlanEndDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblPlanEndDate, "cell 0 4,alignx left");
		
		textField_1 = new JTextField();
		textField_1.setText("01/01/2017");
		panel_1.add(textField_1, "cell 1 4 2 1,growx");
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Secondary Insurance", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[105][105][105][105][105][105]", "[50][50][50][50][50][50]"));
		
		JLabel lblCompanyName_1 = new JLabel("Company Name:");
		lblCompanyName_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblCompanyName_1, "cell 0 0,alignx left");
		
		txtMedicare = new JTextField();
		txtMedicare.setText("Medicare");
		panel_2.add(txtMedicare, "cell 1 0 2 1,growx");
		txtMedicare.setColumns(10);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number:");
		lblPhoneNumber_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPhoneNumber_1, "cell 3 0,alignx left");
		
		textField_7 = new JTextField();
		textField_7.setText("(817) 875-9581");
		panel_2.add(textField_7, "cell 4 0 2 1,growx");
		textField_7.setColumns(10);
		
		JLabel lblPatientIdl = new JLabel("Patient ID:");
		lblPatientIdl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPatientIdl, "cell 0 1,alignx left");
		
		textField_3 = new JTextField();
		textField_3.setText("985401");
		panel_2.add(textField_3, "cell 1 1 2 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblGroupNumber_1 = new JLabel("Group Number:");
		lblGroupNumber_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblGroupNumber_1, "cell 0 2,alignx left");
		
		textField_4 = new JTextField();
		textField_4.setText("1110");
		panel_2.add(textField_4, "cell 1 2 2 1,growx");
		textField_4.setColumns(10);
		
		JLabel lblPlanStartDate_1 = new JLabel("Plan Start Date:");
		lblPlanStartDate_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanStartDate_1, "cell 0 3,alignx left");
		
		textField_5 = new JTextField();
		textField_5.setText("01/01/2016");
		panel_2.add(textField_5, "cell 1 3 2 1,growx");
		textField_5.setColumns(10);
		
		JLabel lblPlanEndDate_1 = new JLabel("Plan End Date:");
		lblPlanEndDate_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblPlanEndDate_1, "cell 0 4,alignx left");
		
		textField_6 = new JTextField();
		textField_6.setText("01/01/2017");
		panel_2.add(textField_6, "cell 1 4 2 1,growx");
		textField_6.setColumns(10);

	}

}
