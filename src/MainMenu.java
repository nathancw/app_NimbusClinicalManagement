import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		//setVisible(true);
		setTitle("Nimbus Clincial Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100,grow][100][100,grow][100][100][100][100]", "[50][50,grow][50,grow][50,grow][50][50,grow][50][50,grow][50][50,grow][50][50]"));
		
		JLabel lblNewLabel_1 = new JLabel("Clinical Management");
		lblNewLabel_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		panel.add(lblNewLabel_1, "flowx,cell 4 1 2 1,alignx center,aligny top");
		
		JLabel lblNewLabel = new JLabel("NIMBUS");
		lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 24));
		panel.add(lblNewLabel, "cell 4 0 2 1,alignx center,aligny bottom");
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10, "cell 0 2 2 1,grow");
		panel_10.setLayout(new BorderLayout(0, 0));
		
		boolean isAdministrator = false;
		if (LoginFrame.accessLevel == 1)
		{
			isAdministrator = true;
		}
		
		
		if (isAdministrator == true) {
			JButton btnCreateNewEmployee = new JButton("Create New Employee");
			btnCreateNewEmployee.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
			btnCreateNewEmployee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent argo0) {
					dispose();
					CreateAccountFrame create = new CreateAccountFrame();
					create.setVisible(true);
				}
			});
			panel_10.add(btnCreateNewEmployee, BorderLayout.CENTER);
		}
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 3 2 2 2,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton createPatientBtn = new JButton("Create Patient");
		createPatientBtn.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		createPatientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatePatientFrame create = new CreatePatientFrame();
				create.setVisible(true);
			}
		});
		panel_1.add(createPatientBtn);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 5 2 2 2,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton searchPatientBtn = new JButton("Search for Patient");
		searchPatientBtn.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		searchPatientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Search For Patient");
		}});
		panel_2.add(searchPatientBtn, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "cell 3 4 2 2,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchForAppointment = new JButton("Search by Procedure");
		btnSearchForAppointment.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		panel_3.add(btnSearchForAppointment, BorderLayout.CENTER);
		btnSearchForAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "cell 5 4 2 2,grow");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchByTime = new JButton("Search by Time");
		btnSearchByTime.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		panel_4.add(btnSearchByTime, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "cell 3 6 2 2,grow");
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnScheduleAppointment = new JButton("Schedule Appointment");
		btnScheduleAppointment.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnScheduleAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Book New Appointment");
			}
		});
		panel_5.add(btnScheduleAppointment, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "cell 5 6 2 2,grow");
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnPatientBilling = new JButton("Patient Billing");
		btnPatientBilling.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnPatientBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Billing History");
			}
		});
		panel_6.add(btnPatientBilling, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, "cell 3 8 2 2,grow");
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JButton btnViewPatientInformation = new JButton("View Patient Information");
		btnViewPatientInformation.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnViewPatientInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Basic Information");
			}
		});
		panel_8.add(btnViewPatientInformation, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9, "cell 5 8 2 2,grow");
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JButton btnInsuranceInformation = new JButton("Insurance Information");
		btnInsuranceInformation.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnInsuranceInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Insurance Information");
			}
		});
		panel_9.add(btnInsuranceInformation, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, "cell 4 10 2 1,grow");
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginFrame create = new LoginFrame();
				create.setVisible(true);
			}
		});
		panel_7.add(btnLogout, BorderLayout.CENTER);
	}

}
