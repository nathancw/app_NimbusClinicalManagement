import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnSearchForAppointment;

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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100][100][100]", "[50][50][50][50][50][50][50][50][50][50][50][50]"));
		
		//JLabel lblNewLabel_1 = new JLabel("Clinical Management");
		//lblNewLabel_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		//panel.add(lblNewLabel_1, "flowx,cell 4 1 2 1,alignx center,aligny top");
		
		Image image;
		try {
			image = ImageIO.read(new File("Pictures\\Logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			panel.add(picLabel, "flowx,cell 2 0 6 3,alignx center,aligny top");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
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
		panel.add(panel_1, "cell 3 3 2 2,grow");
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
		panel.add(panel_2, "cell 5 3 2 2,grow");
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
		panel.add(panel_3, "cell 3 5 2 2,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		btnSearchForAppointment = new JButton("Search Appointments");
		btnSearchForAppointment.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
		panel_3.add(btnSearchForAppointment, BorderLayout.CENTER);
		
		Image searchAppimage;
		try {
			searchAppimage = ImageIO.read(new File("Pictures\\SearchAppointSmallest.png"));
			btnSearchForAppointment.setIcon(new ImageIcon(searchAppimage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnSearchForAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame appSearch = new OptionMenuFrame();
				appSearch.show("Search For Appointment");
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "cell 5 5 2 2,grow");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchBilling = new JButton("Search Billing Information");
		btnSearchBilling.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		//panel_4.add(btnSearchBilling, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "cell 3 7 2 2,grow");
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
		panel_4.add(btnScheduleAppointment, BorderLayout.CENTER);
		
		btnSearchBilling.setEnabled(false);
		
		panel_5.add(btnSearchBilling, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "cell 5 7 2 2,grow");
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnPatientBilling = new JButton("Bill Patients");
		btnPatientBilling.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		btnPatientBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				OptionMenuFrame create = new OptionMenuFrame();
				create.setVisible(true);
				create.show("Billing History");
			}
		});
		btnPatientBilling.setEnabled(true);
		
		panel_6.add(btnPatientBilling, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, "cell 3 9 2 2,grow");
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
		//panel_8.add(btnViewPatientInformation, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9, "cell 5 9 2 2,grow");
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
		//panel_9.add(btnInsuranceInformation, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, "cell 4 11 2 1,grow");
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
		
		JButton btnChangePass = new JButton("Change Password");
		btnChangePass.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		panel.add(btnChangePass, "cell 8 11 2 1,alignx center");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				ChangePasswordFrame changePassword = new ChangePasswordFrame();
				changePassword.setVisible(true);
			}
		});
	}

}
