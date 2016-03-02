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
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;



public class SearchPatientPanel extends JPanel{

	//String directoryList[] = {"Directory","Create New"};
	String[] colNames = {"Patient_ID","FirstName","LastName","DateofBirth"};
	String[][] patients ={};
	
	private JPanel contentPanel;
	private JTextField patientIDtextField;
	private JFormattedTextField dOBtextField;
	private JTextField firstNametextField;
	private JTextField lastNametextField;
	private JTable table;
	private JLabel topLabel;
	private JLabel lblMiddle;

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
	public SearchPatientPanel() {
		setLayout(new BorderLayout(0, 0));

		
		contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][100][100][100][100][300]"));
		
		topLabel = new JLabel("Search for a patient by inputting data below.");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(topLabel, "cell 1 0 6 1,alignx center,aligny top");
	
		JLabel lblPatientID = new JLabel("Patient ID:");
		contentPanel.add(lblPatientID, "cell 0 1,alignx right,aligny center");
		
		patientIDtextField = new JTextField();
		contentPanel.add(patientIDtextField, "cell 1 1,alignx left,aligny center");
		patientIDtextField.setColumns(10);
		
		JLabel lblDOB = new JLabel("Date of Birth:");
		contentPanel.add(lblDOB, "cell 2 1,alignx right,aligny center");		
		
		dOBtextField = new JFormattedTextField();
		contentPanel.add(dOBtextField, "cell 3 1,alignx left,aligny center");
		dOBtextField.setColumns(10);
		
		MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(dOBtextField);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblFirstName = new JLabel("First Name:");
		contentPanel.add(lblFirstName, "cell 4 1,alignx right,aligny center");
		
		firstNametextField = new JTextField();
		contentPanel.add(firstNametextField, "cell 5 1,alignx left,aligny center");
		firstNametextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		contentPanel.add(lblLastName, "cell 6 1,alignx right,aligny center");
		
		lastNametextField = new JTextField();
		contentPanel.add(lastNametextField, "cell 7 1,alignx left,aligny center");
		lastNametextField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when we search, we need to update the patients list
				//But when we click on the patient name we need to QUERY AGAIN? Like damn this is going to be so long and touhg
				//Do I create a PatientDAO? 
				DefaultTableModel queryModel= search(patients);
				table.setModel(queryModel);
				System.out.println("Search button pressed!");
			}
		});
		contentPanel.add(searchButton, "cell 3 2,alignx right,aligny center");
		
		lblMiddle = new JLabel("Click on a Patient Name to pull up their information.");
		contentPanel.add(lblMiddle, "cell 1 3 6 1,alignx center");
		
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

	
	public DefaultTableModel search(String[][] patients){
		
		//Ge
		//http://stackoverflow.com/questions/22238641/create-vector-for-defaulttablemodel
		 DefaultTableModel tableModel;
		 String id = patientIDtextField.getText();
		 String firstName = firstNametextField.getText();
		 String lastName = lastNametextField.getText();
		 
		 String dateofbirth = dOBtextField.getText(); //02/22/2016
			String dobYMD = dateofbirth.substring(6,10) + "-" + dateofbirth.substring(3,5) + "-" + dateofbirth.charAt(0) + dateofbirth.charAt(1);
			
			//if(dateofbirth.length() == 9)
			//	dobYMD = dateofbirth.substring(5,8) + "-" + dateofbirth.substring(2,3) + "-" + "0" + dateofbirth.charAt(0);
			//else
			
			Date dob = null;	
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			System.out.println("at 0: " + (int)dateofbirth.charAt(0));
			try {
				if((dateofbirth.charAt(0) != 32))
				dob = df.parse(dobYMD);
				else{
					//Set to todays date if none is specified
					dob = Calendar.getInstance().getTime();;
					dob = df.parse(dobYMD);
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
		 
			String sqlQuery = "Select [Patient_ID],[FirstName],[LastName],[DateOfBirth]from [NCMSE].[NCM].[Patient]" +
					"where FirstName = ? or LastName = ? or DateOfBirth = ?";	
	
		Connection sqlconn = null;
		PreparedStatement stmt = null;
		try {
			
			//TODO: Not create a new connection every time... Also need to clean the table on inserts
			//This is terrible, terrible logic. So many statics that don't make any sense. I'm so confused rofl
			NimbusDAO DAO = new NimbusDAO();
			sqlconn = DAO.getConnection();
			////////////////////////////
			
			///Prepare and execute query
			stmt = sqlconn.prepareStatement(sqlQuery);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setDate(3, new java.sql.Date(dob.getTime()));
			ResultSet rs = stmt.executeQuery();	
		
			////////////
			
			//Get metadata and prepare columnnames, even thought this shouldnt change
			ResultSetMetaData rsMeta = rs.getMetaData();
			
			int numberOfCols = rsMeta.getColumnCount();
			Vector<String> colNames= new Vector<String>();   // your columns names
			for (int i = 1; i <= numberOfCols; i++ ){
			    colNames.add(rsMeta.getColumnName(i));
			}
			
			tableModel = new DefaultTableModel(colNames, 0);

			while (rs.next()) {
				String data0 = rs.getString("Patient_ID");	
			    String data1 = rs.getString("FirstName");
			    String data2 = rs.getString("LastName");
			    String data3 = rs.getString("DateOfBirth");
			    Object[] rowData = new Object[] {data0,data1,data2,data3};
			    tableModel.addRow(rowData);
			}
			
			stmt.close();
			sqlconn.close();
			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	
		
		
		
	}
		
}
