import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;


public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
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
	public SearchFrame() {
		setTitle("Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create New");
		fileMenu.add(mntmNewMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100,grow,left][100][100][100][100][100][100][100][100]", "[50][100][100,grow][100][100][300][50,grow]"));
		
		JLabel lblNewLabel = new JLabel("Welcome. Begin by searching for a patient");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 27));
		contentPanel.add(lblNewLabel, "cell 1 1 8 1,alignx center");
		
		
		//List area
		String directoryList[] = {"Directory","Create New"};
		//JList<String> list = new JList<String>(directoryList);
		JList list = new JList(directoryList);
		contentPanel.add(list, "cell 0 0 1 7,grow");
		
		list.addListSelectionListener(new ListSelectionListener()
		{
		  public void valueChanged(ListSelectionEvent ev)
		  {
			 // System.out.println("Selected:" + ev.getFirstIndex());
			 String selectedValues = (String)list.getSelectedValue();
			 System.out.println("Selected:" + selectedValues); 
			 
			 //((JList)ev.getSource()).getSelectedValues().toString();
		
		  } 
		});
		
		//
		JLabel lblNewLabel_3 = new JLabel("Patient ID:");
		contentPanel.add(lblNewLabel_3, "cell 1 2,alignx trailing");
		
		textField = new JTextField();
		contentPanel.add(textField, "cell 2 2,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date of Birth:");
		contentPanel.add(lblNewLabel_1, "cell 3 2,alignx trailing");
		
		textField_1 = new JTextField();
		contentPanel.add(textField_1, "cell 4 2,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("First Name:");
		contentPanel.add(lblNewLabel_2, "cell 5 2,alignx trailing");
		
		textField_2 = new JTextField();
		contentPanel.add(textField_2, "cell 6 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name:");
		contentPanel.add(lblNewLabel_4, "cell 7 2,alignx trailing");
		
		textField_3 = new JTextField();
		contentPanel.add(textField_3, "cell 8 2,growx");
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		contentPanel.add(btnNewButton, "cell 4 3,alignx center,aligny top");
		
		JPanel resultsPanel = new JPanel();
		contentPanel.add(resultsPanel, "cell 2 4 6 2,grow");
		resultsPanel.setLayout(new BorderLayout(0, 0));
		
		String[] colNames = {"Name","ID","DoB","Gender"};
		Object[][] patients = {
				{"Blank","111","080808","F"},
				{null,null,null,null,}};
		table = new JTable(patients,colNames);
		resultsPanel.add(table);
		resultsPanel.add(new JScrollPane(table));
	}

}
