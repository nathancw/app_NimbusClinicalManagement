import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;


public class GridBagLogin extends JFrame {

	private JPanel contentPane;
	private JPanel GridPanel;
	private JPanel SignInPanel;
	private JTextField textField;
	private JLabel userNameLbl;
	private JLabel passwordlbl;
	private JButton btnNewButton;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridBagLogin frame = new GridBagLogin();
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
	public GridBagLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		GridPanel = new JPanel();
		contentPane.add(GridPanel, BorderLayout.CENTER);
		GridBagLayout gbl_GridPanel = new GridBagLayout();
		gbl_GridPanel.columnWidths = new int[]{50,50,50,50,50,50,50,50,50};
		gbl_GridPanel.rowHeights = new int[]{60,60,60,60,60,60};
		gbl_GridPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_GridPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		GridPanel.setLayout(gbl_GridPanel);
		
		SignInPanel = new JPanel();
		SignInPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sign In", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_SignInPanel = new GridBagConstraints();
		gbc_SignInPanel.gridheight = 4;
		gbc_SignInPanel.gridwidth = 5;
		gbc_SignInPanel.fill = GridBagConstraints.BOTH;
		gbc_SignInPanel.gridx = 2;
		gbc_SignInPanel.gridy = 1;
		GridPanel.add(SignInPanel, gbc_SignInPanel);
		GridBagLayout gbl_SignInPanel = new GridBagLayout();
		gbl_SignInPanel.columnWidths = new int[]{48,50,81,50};
		gbl_SignInPanel.rowHeights = new int[]{48, 10, 10, 10, 34, 40, 0};
		gbl_SignInPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_SignInPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		SignInPanel.setLayout(gbl_SignInPanel);
		
		userNameLbl = new JLabel("Username");
		GridBagConstraints gbc_userNameLbl = new GridBagConstraints();
		gbc_userNameLbl.anchor = GridBagConstraints.WEST;
		gbc_userNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_userNameLbl.gridx = 1;
		gbc_userNameLbl.gridy = 1;
		SignInPanel.add(userNameLbl, gbc_userNameLbl);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		SignInPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		passwordlbl = new JLabel("Password");
		GridBagConstraints gbc_passwordlbl = new GridBagConstraints();
		gbc_passwordlbl.anchor = GridBagConstraints.WEST;
		gbc_passwordlbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordlbl.gridx = 1;
		gbc_passwordlbl.gridy = 3;
		SignInPanel.add(passwordlbl, gbc_passwordlbl);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		SignInPanel.add(passwordField, gbc_passwordField);
		
		btnNewButton = new JButton("Log in");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
		SignInPanel.add(btnNewButton, gbc_btnNewButton);
	}

}
