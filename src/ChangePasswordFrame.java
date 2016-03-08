import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ChangePasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100][100]", "[100][50][50][50][50][50][50][100][100]"));
		
		JLabel lblNewLabel_2 = new JLabel("Change your Password");
		panel.add(lblNewLabel_2, "cell 3 0 3 1,alignx center");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1, "cell 2 2 5 5,grow");
		panel_1.setLayout(new MigLayout("", "[100,grow][100][100][100]", "[25][25][25][25][25][25]"));
		
		JLabel lblNewLabel = new JLabel("Please enter your old password:");
		panel_1.add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		textField = new JTextField();
		panel_1.add(textField, "cell 0 1 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your preferred new password:");
		panel_1.add(lblPleaseEnterYour, "cell 0 2 2 1,alignx center");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 0 3 2 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("<html>A password must be at least <br>8 characters and must<br>contain at least one number (0-9).</html>");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		panel_1.add(lblNewLabel_1, "cell 2 3 2 1,aligny top");
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new Password:");
		panel_1.add(lblConfirmNewPassword, "cell 0 4 2 1,alignx center");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "cell 0 5 2 1,growx");
		textField_2.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel, "cell 3 7");
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave, "cell 4 7");
	}

}
