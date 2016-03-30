import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;



public class SearchAppointmentPanel extends JPanel{

	//String directoryList[] = {"Directory","Create New"};
	String[] colNames = {"Appointment_ID","Patient_ID","FirstName","LastName","Date","StartTime","EndTime"};
	Object[][] appointments;
	
	private JPanel contentPanel;
	private JTextField patientIDTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTable table;
	private JLabel topLabel;
	private JLabel lblMiddle;
	private JButton btnBookNewAppointment;
	private JComboBox procedureComboBox;
	private NimbusDAO dao;
	private Map<String,Integer> procedureIDs;
	
	public SearchAppointmentPanel() {
		try {
			dao = new NimbusDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));

		
		contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][100][100][100][100][300]"));
	
		Image image;
		try {
			image = ImageIO.read(new File("Pictures\\SearchAppointSmall.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			contentPanel.add(picLabel, "flowx,cell 0 2 2 2,alignx center,aligny center");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		topLabel = new JLabel("Search for an Appointment by inputting data below.");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(topLabel, "cell 1 0 6 1,alignx center,aligny top");
	
		JLabel lblPatientID = new JLabel("Patient ID:");
		contentPanel.add(lblPatientID, "cell 0 1,alignx right,aligny center");
		
		patientIDTextField = new JTextField();
		contentPanel.add(patientIDTextField, "cell 1 1,alignx left,aligny center");
		patientIDTextField.setColumns(10);
		
		JLabel lblProcedure = new JLabel("Procedure:");
		contentPanel.add(lblProcedure, "cell 2 1,alignx trailing,aligny center");
		
		procedureComboBox = new JComboBox();
		contentPanel.add(procedureComboBox, "cell 3 1,growx");
		procedureComboBox.setModel(getProcedures());
		
		JLabel lblFirstName = new JLabel("First Name:");
		contentPanel.add(lblFirstName, "cell 4 1,alignx right,aligny center");
		
		firstNameTextField = new JTextField();
		contentPanel.add(firstNameTextField, "cell 5 1,alignx left,aligny center");
		firstNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		contentPanel.add(lblLastName, "cell 6 1,alignx right,aligny center");
		
		lastNameTextField = new JTextField();
		contentPanel.add(lastNameTextField, "cell 7 1,alignx left,aligny center");
		lastNameTextField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel queryModel= search();
				
				table.setModel(queryModel);
				//System.out.println("Search button pressed!");
			}
		});
		contentPanel.add(searchButton, "cell 2 2 2 1,alignx center,aligny center");
		
		btnBookNewAppointment = new JButton("Book New Appointment");
		contentPanel.add(btnBookNewAppointment, "cell 4 2 2 1,alignx center");
		
		lblMiddle = new JLabel("Click on a Respective Patient ID row to pull up their information.");
		contentPanel.add(lblMiddle, "cell 2 3 4 1,alignx center");
		
		JPanel resultsPanel = new JPanel();
		contentPanel.add(resultsPanel, "cell 0 4 8 3,alignx center,aligny top");
		
		//Make the table uneditable
		TableModel model = new DefaultTableModel(appointments, colNames)
		  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
	
		resultsPanel.setLayout(new BorderLayout(0, 0));
		table = new JTable(model);
		
		//Set column widths to make table bigger
		TableColumnModel columnModel = table.getColumnModel();
		
		table.setRowHeight(0,25);
		table.setBorder(null);
		resultsPanel.add(table,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 500));

		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    //if (e.getClickCount() == 1) {
				  	if(table.getSelectedColumn() == 0){
				  		
				  	int appointmentID = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
				    
				    OptionMenuFrame opt = new OptionMenuFrame();
				    opt.populateAppointment(appointmentID);
				    //BookAppointmentPanel appPanel = new BookAppointmentPanel(appointmentID);
				    //BookAppointmentPanel appPanel = new BookAppointmentPanel(appointmentID);
				    	
			         
			   
				  	}
			     //}
			   }
			});
		
	
	}

	public DefaultTableModel search(){
		
		//http://stackoverflow.com/questions/22238641/create-vector-for-defaulttablemodel
		 DefaultTableModel tableModel;
		 int id;
		 int  procedureID;
		 String selectedItem = (String)procedureComboBox.getSelectedItem();
		 if(selectedItem.isEmpty())
			 procedureID = 0;
		 else
			 procedureID = procedureIDs.get(procedureComboBox.getSelectedItem());
		 
		 if(patientIDTextField.getText().equals(""))
			 id = 0;
		 else
			 id = Integer.parseInt(patientIDTextField.getText());
		 
		 String firstName = firstNameTextField.getText();
		 String lastName = lastNameTextField.getText();
	
			try{
			
			
			//Get patient detials
			ResultSet rs = dao.getAppointmentDetails(procedureID,firstName,lastName,id,0);
			
			//Get metadata and prepare columnnames, even thought this shouldnt change
			ResultSetMetaData rsMeta = rs.getMetaData();
			
			Vector<String> colNames= new Vector<String>();   // your columns names
			colNames.add(rsMeta.getColumnName(1));
			colNames.add(rsMeta.getColumnName(2));
			colNames.add(rsMeta.getColumnName(3));
			colNames.add(rsMeta.getColumnName(4));
			//colNames.add(rsMeta.getColumnName(5));
			colNames.add(rsMeta.getColumnName(7));
			colNames.add(rsMeta.getColumnName(8));
			colNames.add(rsMeta.getColumnName(9));
			
			//Make the cells uneditable while creating the tablemodel
			tableModel = new DefaultTableModel(colNames, 0)	{
			    public boolean isCellEditable(int row, int column)
			    {
			      return false;//This causes all cells to be not editable
			    }
			  };

			while (rs.next()) {
				int data0 = rs.getInt("Appointment_ID");	
			    int data1 = rs.getInt("Patient_ID");
			    String data2 = rs.getString("FirstName");
			    String data3 = rs.getString("LastName");
			    //String data4 = rs.getString("CombinedName");
			    String data5 = rs.getString("Date");
			    String data6 = rs.getString("StartTime");
			    String data7 = rs.getString("EndTime");
			    Object[] rowData = new Object[] {data0,data1,data2,data3,data5,data6,data7};
			    tableModel.addRow(rowData);
			}
			
			//dao.closeConnection();
			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public DefaultComboBoxModel getProcedures(){
		
		DefaultComboBoxModel model = null;
		ArrayList<String> procedures = new ArrayList<String>();
		procedureIDs = new HashMap<String,Integer>();
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.Clinical_Procedures";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				procedures.add("");
				while(rs.next()){
					procedures.add(rs.getString("ProcedureName"));
					procedureIDs.put(rs.getString("ProcedureName"),rs.getInt("Procedure_ID"));
					
				}
				
				model = new DefaultComboBoxModel(procedures.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		
	
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}
	
		
}

