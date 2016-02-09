import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;



public class SearchPanel extends JPanel{

	//String directoryList[] = {"Directory","Create New"};
	String[] colNames = {"Name","ID","DoB","Gender"};
	Object[][] patients = {
			{"Blank","111","080808","F"},
			{null,null,null,null,}};
	
	private JPanel contentPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblClickOnA;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	} */

	/**
	 * Create the frame.
	 */
	public SearchPanel() {
		setLayout(new BorderLayout(0, 0));

		
		contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][100][100][100][100][300]"));
		
		lblNewLabel = new JLabel("Search for a patient by inputting data below.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblNewLabel, "cell 1 0 6 1,alignx center,aligny top");
	
		JLabel lblNewLabel_3 = new JLabel("Patient ID:");
		contentPanel.add(lblNewLabel_3, "cell 0 1,alignx right,aligny center");
		
		textField = new JTextField();
		contentPanel.add(textField, "cell 1 1,alignx left,aligny center");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date of Birth:");
		contentPanel.add(lblNewLabel_1, "cell 2 1,alignx right,aligny center");
		
		textField_1 = new JTextField();
		contentPanel.add(textField_1, "cell 3 1,alignx left,aligny center");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("First Name:");
		contentPanel.add(lblNewLabel_2, "cell 4 1,alignx right,aligny center");
		
		textField_2 = new JTextField();
		contentPanel.add(textField_2, "cell 5 1,alignx left,aligny center");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name:");
		contentPanel.add(lblNewLabel_4, "cell 6 1,alignx right,aligny center");
		
		textField_3 = new JTextField();
		contentPanel.add(textField_3, "cell 7 1,alignx left,aligny center");
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		contentPanel.add(btnNewButton, "cell 3 2,alignx right,aligny center");
		
		lblClickOnA = new JLabel("Click on a Patient Name to pull up their information.");
		contentPanel.add(lblClickOnA, "cell 1 3 6 1,alignx center");
		
		JPanel resultsPanel = new JPanel();
		contentPanel.add(resultsPanel, "cell 0 4 8 3,alignx center,aligny top");
		resultsPanel.setLayout(new BorderLayout(0, 0));
		
		//Make the table uneditable
		TableModel model = new DefaultTableModel(patients, colNames)
		  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		table = new JTable(model);
		  
		table.setBorder(null);
		resultsPanel.add(table);
		resultsPanel.add(new JScrollPane(table));
			
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    //if (e.getClickCount() == 1) {
				  	if(table.getSelectedColumn() == 0){
				  		
				  	
			    	 System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
			    	 PatientInformationFrame patientframe = new PatientInformationFrame();
			         patientframe.show("Basic Information");
				  	}
			     //}
			   }
			});

	
	}

	
		
}
