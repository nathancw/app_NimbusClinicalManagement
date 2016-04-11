import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
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

import javax.swing.ImageIcon;
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
import java.io.File;
import java.io.IOException;
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
	private JButton searchButton;

	public SearchPatientPanel() {
		setLayout(new BorderLayout(0, 0));

		
		contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][100][100][100][100][300]"));
		
		Image image;
		try {
			image = ImageIO.read(new File("Pictures\\SearchPatient.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			contentPanel.add(picLabel, "flowx,cell 0 2 2 2,alignx center,aligny center");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
		
		searchButton = new JButton("Search");
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
		
		//Make the table uneditabl, default table model
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
				  		
				  	 String patient_ID = table.getValueAt(table.getSelectedRow(), 0).toString();
			    	 System.out.println(patient_ID);
			    	 PatientInformationFrame patientframe = new PatientInformationFrame(Integer.parseInt(patient_ID));
			         patientframe.show("Basic Information");
				  	}
			     //}
			   }
			});

		//Make the searchbutton the default button in the panel
	
	}

	public JButton getDefaultButton(){
		return searchButton;
	}
	public DefaultTableModel search(String[][] patients){
		
		//http://stackoverflow.com/questions/22238641/create-vector-for-defaulttablemodel
		 DefaultTableModel tableModel;
		 int id;
		 
		 if(patientIDtextField.getText().equals(""))
			 id = 0;
		 else
			 id = Integer.parseInt(patientIDtextField.getText());
		 
		 String firstName = firstNametextField.getText();
		 String lastName = lastNametextField.getText();
		 String dateofbirth = dOBtextField.getText(); //02/22/2016
	
			try{
			NimbusDAO dao = new NimbusDAO();
			
			//Get patient detials
			ResultSet rs = dao.getPatientDetails(id,firstName,lastName,dateofbirth);
			
			//Get metadata and prepare columnnames, even thought this shouldnt change
			ResultSetMetaData rsMeta = rs.getMetaData();
			
			Vector<String> colNames= new Vector<String>();   // your columns names
			colNames.add(rsMeta.getColumnName(1));
			colNames.add(rsMeta.getColumnName(2));
			colNames.add(rsMeta.getColumnName(4));
			colNames.add(rsMeta.getColumnName(5));
			
			//Make the cells uneditable while creating the tablemodel
			tableModel = new DefaultTableModel(colNames, 0)	{
			    public boolean isCellEditable(int row, int column)
			    {
			      return false;//This causes all cells to be not editable
			    }
			  };

			while (rs.next()) {
				String data0 = rs.getString("Patient_ID");	
			    String data1 = rs.getString("FirstName");
			    String data2 = rs.getString("LastName");
			    String dob = rs.getString("DateOfBirth");
			    String data3 = dob.substring(5, 7) + "/" + dob.substring(8,10) + "/" + dob.substring(0,4);
			    Object[] rowData = new Object[] {data0,data1,data2,data3};
			    tableModel.addRow(rowData);
			}
			
			dao.closeConnection();
			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
		
}
