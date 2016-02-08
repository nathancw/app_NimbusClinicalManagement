import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;


public class Nimbus extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nimbus frame = new Nimbus();
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
	public Nimbus() {
		//pack();
		setTitle("Test Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(30, 30));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sign In", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel userNameLbl = new JLabel("Username:");
		panel.add(userNameLbl);
		
		usernameField = new JTextField();
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		panel.add(passwordLbl);
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		JPanel eastPadPanel = new JPanel();
		contentPane.add(eastPadPanel, BorderLayout.EAST);
		
		JPanel northPadPanel = new JPanel();
		contentPane.add(northPadPanel, BorderLayout.NORTH);
		
		JPanel southPadPanel = new JPanel();
		contentPane.add(southPadPanel, BorderLayout.SOUTH);
		
		JButton LogInBtn = new JButton("Log In");
		southPadPanel.add(LogInBtn);
	}

}
