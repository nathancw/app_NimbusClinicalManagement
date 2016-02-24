import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginFrame extends JFrame {
	public static NimbusDAO login;
	private JPanel contentPane;
	private JTextField usernametextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					login = new NimbusDAO();
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
	public LoginFrame() {
		
		setTitle("Nimbus Clincal Management Software Login");
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
		signInpanel.setForeground(Color.WHITE);
		signInpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sign In", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(signInpanel, "cell 2 1 4 4");
		//contentPanel.add(signInpanel, "dock south");
		
		signInpanel.setLayout(new MigLayout("", "[50][100][100][50]", "[30][][30][][40]"));
		
		JLabel lblNewLabel = new JLabel("Username");
		signInpanel.add(lblNewLabel, "cell 1 0 2 1,aligny bottom");
		
		usernametextField = new JTextField();
		signInpanel.add(usernametextField, "cell 1 1 2 1,growx,aligny top");
		usernametextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		signInpanel.add(lblNewLabel_1, "cell 1 2 2 1,aligny bottom");
		
		passwordField = new JPasswordField();
		signInpanel.add(passwordField, "cell 1 3 2 1,growx,aligny top");
		
		JButton loginBtn = new JButton("Log in");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkCredintials(usernametextField.getText(),passwordField.getPassword())){
					dispose();
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
				}
			}
		});
		signInpanel.add(loginBtn, "cell 2 4,alignx right");
	}
	
	public boolean checkCredintials(String userName, char[] password){
		
		String sqlQuery = "Select * from NCMSE.dbo.Account where Username = ? and password = ?";
		
		//We are going to try to create a connection to the database using the DAO and then query it.
		//Need to create a prepared statement so you can avoid sql injection and tie the questions to variables
		//http://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
		//Security Needs to be implemented thats for sure, but i will look into that later
		
		try {
			
			PreparedStatement stmt = login.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, userName);
			stmt.setString(2, new String(password));

			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				JOptionPane.showMessageDialog(new JFrame(), "Successful Login");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(), "Unsuccessful Login");
				return false;
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
