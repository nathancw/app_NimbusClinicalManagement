import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.ResultSet;
import javax.swing.JPasswordField;


public class ChangePasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
			
	private String un;
	private String newpass;
	private String salt;
	private JPasswordField txtOldPass;
	private JPasswordField txtNewPass;
	private JPasswordField txtConfirmPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordFrame frame = new ChangePasswordFrame();
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
	public ChangePasswordFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100][100]", "[100][50][50][50][50][50][50][100][100]"));
		
		JLabel titleLbl = new JLabel("Change your Password");
		contentPanel.add(titleLbl, "cell 3 0 3 1,alignx center");
		
		JPanel passPanel = new JPanel();
		passPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(passPanel, "cell 2 1 5 6,grow");
		passPanel.setLayout(new MigLayout("", "[125][125][125][125]", "[25][25][25][25][25][25][25][25][25][25]"));
		
		JLabel lblUsername = new JLabel("Please enter your username:");
		passPanel.add(lblUsername, "cell 1 1 2 1,alignx center");
		
		txtUsername = new JTextField();
		passPanel.add(txtUsername, "cell 1 2 2 1,growx");
		txtUsername.setColumns(10);
		
		JLabel lblOldPass = new JLabel("Please enter your old password:");
		passPanel.add(lblOldPass, "cell 1 3 2 1,alignx center");
		
		txtOldPass = new JPasswordField();
		passPanel.add(txtOldPass, "cell 1 4 2 1,growx");
		
		JLabel lblNewPass = new JLabel("Please enter your preferred new password:");
		passPanel.add(lblNewPass, "cell 1 5 2 1,alignx center");
		
		txtNewPass = new JPasswordField();
		passPanel.add(txtNewPass, "cell 1 6 2 1,growx");
		
		JLabel lblConfirmPass = new JLabel("Confirm new Password:");
		passPanel.add(lblConfirmPass, "cell 1 7 2 1,alignx center");
		
		txtConfirmPass = new JPasswordField();
		passPanel.add(txtConfirmPass, "cell 1 8 2 1,growx");
		
		JLabel lblPassRules = new JLabel("<html>A password must be at least 8 characters and must<br>contain at least one number (0-9).</html>");
		lblPassRules.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		passPanel.add(lblPassRules, "cell 1 9 3 1,aligny top");
		
		JButton btnCancel = new JButton("Cancel");
		contentPanel.add(btnCancel, "cell 5 7");
		
		btnCancel.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  dispose();
				  MainMenu menu = new MainMenu();
				  menu.setVisible(true);
			  }
			});
		
		JButton btnSave = new JButton("Save");
		contentPanel.add(btnSave, "cell 6 7");
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(checkPassword()) {
					updateDatabase();
					dispose();
					LoginFrame create = new LoginFrame();
					create.setVisible(true);
				}
			}
		});
	}
	
	public Boolean checkPassword() {
		String username = txtUsername.getText();
		String oldPass = new String(txtOldPass.getPassword());
		String newPass = new String(txtNewPass.getPassword());
		String confirmPass = new String(txtConfirmPass.getPassword());
		Boolean digit = false;
		
		NimbusDAO dao;
		String oldPassdatabase = null;
		ResultSet rs = null;
		
		//checking that all fields are filled in
		if(username.isEmpty() || oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill out all of the fields.","Cannot Change Password",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}

		//checking for a digit in the new password
		if(newPass != null && !(newPass.isEmpty())) {
			for(int i = 0; i < newPass.length(); i++) {
				if(digit = Character.isDigit(newPass.charAt(i)))
					break;
			}
		}
		
		//getting the current password from database
		try {
			dao = new NimbusDAO();
			
			rs = dao.getAccountUsername(username);
			
			if(rs.next()) {
				//This old pass we pull from the database will be hashed
				oldPassdatabase = rs.getString("Password");
				salt = rs.getString("Salt");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "This username does not exist in the database.");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//checking if they entered the right current password
		
		//Hash the oldpasswordtext that was in the text box
		KeySpec spec = new PBEKeySpec(oldPass.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKeyFactory f;
		String oldPassHashed = null;
		try {
			
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = f.generateSecret(spec).getEncoded();
			oldPassHashed = Base64.encode(hash);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
	
			e.printStackTrace();
		}
		////////End hashing///////////////////////////////////////////
		
		if(!(oldPassdatabase.equals(oldPassHashed))) {
			JOptionPane.showMessageDialog(new JFrame(), "This password does not match the current password for " + username + ".");
			return false;
		}
		
		//checking length, digit, and confirm pass=new pass
		if(newPass.length() < 8) {
			JOptionPane.showMessageDialog(this, "The password must be at least 8 characters long.","Cannot Change Password",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!digit) {
			JOptionPane.showMessageDialog(this, "The password must contain at least one number.","Cannot Change Password",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!(newPass.equals(confirmPass))) {
			JOptionPane.showMessageDialog(this, "The passwords must match.","Cannot Change Password",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			un = username;
			newpass = newPass;
			return true;
		}
	}
	
	public void updateDatabase() {
		NimbusDAO dao;
		
		try {
			dao = new NimbusDAO();
			
			dao.changePassword(un, newpass,salt);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
