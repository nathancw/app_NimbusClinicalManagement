import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateAccountFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountFrame frame = new CreateAccountFrame();
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
	public CreateAccountFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[100][100,grow][100,grow][100][100,grow][100][100][100][100]", "[100][100,grow][100][100][100][100][100]"));
		
		JLabel lblCreateAnAccount = new JLabel("Create an Account");
		lblCreateAnAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		mainPanel.add(lblCreateAnAccount, "cell 3 0 3 1,alignx center");
		
		JPanel bgPanel = new JPanel();
		mainPanel.add(bgPanel, "cell 2 2 5 3");
		bgPanel.setLayout(new BorderLayout());
		
		JPanel accountTypePanel = new JPanel();
		accountTypePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bgPanel.add(accountTypePanel, BorderLayout.CENTER);
		accountTypePanel.setLayout(new MigLayout("", "[100][100][100][100]", "[100][100][100]"));
		
		JLabel lblAccountType = new JLabel("What type of account would you like to set up?");
		lblAccountType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		accountTypePanel.add(lblAccountType, "cell 0 0 4 1,alignx center");
		
		JComboBox comboBoxType = new JComboBox();
		accountTypePanel.add(comboBoxType, "cell 1 1 2 1,growx");
		//comboBoxType.setForeground(Color.BLUE);
		comboBoxType.addItem(" ");
		comboBoxType.addItem("Office Employee");
		comboBoxType.addItem("Doctor");
		comboBoxType.addItem("Technical Administrator");
		
		JButton btnCancel = new JButton("Cancel");
		accountTypePanel.add(btnCancel, "cell 1 2,alignx center");
		
		JButton btnNext = new JButton("Next");
		accountTypePanel.add(btnNext, "cell 2 2,alignx center");
		
		
		btnCancel.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  //BRING UP MAIN MENU
				  dispose();
				  MainMenu menu = new MainMenu();
				  menu.setVisible(true);
			  }
			});
		
		btnNext.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  //BRING UP NEXT PANEL TO CREATE INFO
				  bgPanel.removeAll();
				  repaint();
				  
			  }
			});

	}
		
	public void employeeAccount() {
		//PANEL WITH EMPLOYEE INFO
		JPanel employeePanel = new JPanel();
		//mainPanel.add(employeePanel, )
	}
	
	public void doctorAccount() {
		//PANEL WIHT DOCTOR INFO
	}
	
	public void adminAccount() {
		//PANEL WITH ADMIN INFO
	}
}
