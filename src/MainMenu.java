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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[100][100][100][100,grow][100][100,grow][100][100][100][100]", "[50][50,grow][50][50,grow][50][50,grow][50][50,grow][50][50][50][50]"));
		
		JLabel lblNewLabel_1 = new JLabel("Clinical Management");
		lblNewLabel_1.setFont(new Font("MicraC", Font.BOLD, 10));
		panel.add(lblNewLabel_1, "flowx,cell 4 1 2 1,alignx center,aligny bottom");
		
		JLabel lblNewLabel = new JLabel("NIMBUS");
		lblNewLabel.setFont(new Font("MicraC", Font.BOLD, 24));
		panel.add(lblNewLabel, "cell 3 1 4 1,alignx center,aligny center");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 3 3 2 2,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Create Patient");
		btnNewButton_2.setFont(new Font("Malgun Gothic", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 5 3 2 2,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Search for Patient");
		btnNewButton_1.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_2.add(btnNewButton_1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "cell 3 5 2 2,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchForAppointment = new JButton("Search by Procedure");
		btnSearchForAppointment.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_3.add(btnSearchForAppointment, BorderLayout.CENTER);
		btnSearchForAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "cell 5 5 2 2,grow");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnSearchByTime = new JButton("Search by Time");
		btnSearchByTime.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_4.add(btnSearchByTime, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "cell 3 7 2 2,grow");
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnScheduleAppointment = new JButton("Schedule Appointment");
		btnScheduleAppointment.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_5.add(btnScheduleAppointment, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "cell 5 7 2 2,grow");
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnPatientBilling = new JButton("Patient Billing");
		btnPatientBilling.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_6.add(btnPatientBilling, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, "cell 4 9 2 1,grow");
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Micra", Font.PLAIN, 9));
		panel_7.add(btnLogout, BorderLayout.CENTER);
	}

}
