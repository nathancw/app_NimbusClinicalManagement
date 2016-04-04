import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class ChangePasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtOldPass;
	private JTextField txtNewPass;
	private JTextField txtConfirmPass;
	private JTextField txtUsername;
			
	private String un;
	private String newpass;

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
		passPanel.setLayout(new MigLayout("", "[100,grow][100][100][100]", "[25][25][25][25][25][25][25][25][25][25]"));
		
		JLabel lblUsername = new JLabel("Please enter your username:");
		passPanel.add(lblUsername, "cell 0 1 2 1,alignx center");
		
		txtUsername = new JTextField();
		passPanel.add(txtUsername, "cell 0 2 2 1,growx");
		txtUsername.setColumns(10);
		
		JLabel lblOldPass = new JLabel("Please enter your old password:");
		passPanel.add(lblOldPass, "cell 0 3 2 1,alignx center");
		
		txtOldPass = new JTextField();
		passPanel.add(txtOldPass, "cell 0 4 2 1,growx");
		txtOldPass.setColumns(10);
		
		JLabel lblNewPass = new JLabel("Please enter your preferred new password:");
		passPanel.add(lblNewPass, "cell 0 5 2 1,alignx center");
		
		txtNewPass = new JTextField();
		passPanel.add(txtNewPass, "cell 0 6 2 1,growx");
		txtNewPass.setColumns(10);
		
		JLabel lblPassRules = new JLabel("<html>A password must be at least <br>8 characters and must<br>contain at least one number (0-9).</html>");
		lblPassRules.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		passPanel.add(lblPassRules, "cell 2 6 2 1,aligny top");
		
		JLabel lblConfirmPass = new JLabel("Confirm new Password:");
		passPanel.add(lblConfirmPass, "cell 0 7 2 1,alignx center");
		
		txtConfirmPass = new JTextField();
		passPanel.add(txtConfirmPass, "cell 0 8 2 1,growx");
		txtConfirmPass.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		contentPanel.add(btnCancel, "cell 3 7");
		
		JButton btnSave = new JButton("Save");
		contentPanel.add(btnSave, "cell 4 7");
		
		btnCancel.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  dispose();
				  MainMenu menu = new MainMenu();
				  menu.setVisible(true);
			  }
			});
		
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
		String oldPass = txtOldPass.getText();
		String newPass = txtNewPass.getText();
		String confirmPass = txtConfirmPass.getText();
		Boolean digit = false;
		
		NimbusDAO dao;
		String oldPasstxt = null;
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
				oldPasstxt = rs.getString("Password");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "This username does not exist in the database.");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//checking if they entered the right current password
		if(!(oldPasstxt.equals(oldPass))) {
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
			
			dao.changePassword(un, newpass);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
