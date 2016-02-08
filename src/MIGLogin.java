import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class MIGLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MIGLogin frame = new MIGLogin();
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
	public MIGLogin() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450	, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);
		
		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[50,grow][50,grow][75][75][75][75.00][50,grow][50,grow]", "[50,grow][50][50][50][50][50,grow]"));
		
		JPanel signInpanel = new JPanel();
		signInpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sign In", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(signInpanel, "cell 2 1 4 4");
		//contentPanel.add(signInpanel, "dock south");
		
		signInpanel.setLayout(new MigLayout("", "[50][100][100][50]", "[30][][30][][40]"));
		
		JLabel lblNewLabel = new JLabel("Username");
		signInpanel.add(lblNewLabel, "cell 1 0 2 1,aligny bottom");
		
		textField = new JTextField();
		signInpanel.add(textField, "cell 1 1 2 1,growx,aligny top");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		signInpanel.add(lblNewLabel_1, "cell 1 2 2 1,aligny bottom");
		
		passwordField = new JPasswordField();
		signInpanel.add(passwordField, "cell 1 3 2 1,growx,aligny top");
		
		JButton btnNewButton = new JButton("Log in");
		signInpanel.add(btnNewButton, "cell 2 4,alignx right");
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
}
