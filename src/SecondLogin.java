import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class SecondLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondLogin frame = new SecondLogin();
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
	public SecondLogin() {
		setTitle("Login Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(50, 0));
		setContentPane(contentPane);
		
		
		
		JPanel west = new JPanel();
		west.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Sign In", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(west, BorderLayout.CENTER);
		west.setLayout(new BorderLayout(20, 20));
		
		JPanel panel_3 = new JPanel();
		west.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Username");
		panel_3.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		panel_3.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		west.add(panel_4, BorderLayout.WEST);
		
		JPanel panel_5 = new JPanel();
		west.add(panel_5, BorderLayout.EAST);
		
		JPanel panel_6 = new JPanel();
		west.add(panel_6, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		west.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Login");
		panel_7.add(btnNewButton);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		
		pack();
	}

}
