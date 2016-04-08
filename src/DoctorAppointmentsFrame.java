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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JComboBox;
import java.awt.Component;



public class DoctorAppointmentsFrame extends JFrame{

	//String directoryList[] = {"Directory","Create New"};
	String[] colNames = {"Appointment_ID","Patient_ID","FirstName","LastName","Date","StartTime","EndTime"};
	Object[][] appointments;
	
	private JPanel contentPanel;
	private JTable topTable;
	private JTable bottomTable;
	private JLabel topLabel;
	private NimbusDAO dao;
	private Map<String,Integer> procedureIDs;
	private int doctorID;
	private String name;
	private JPanel secondPanel;
	private JLabel lblAppointmentHistory;
	private JButton btnClose;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorAppointmentsFrame frame = new DoctorAppointmentsFrame(20);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DoctorAppointmentsFrame (int docID) {
		
		if(docID > 100000)
			
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setTitle("Your appointments");
		
		
		this.doctorID = docID;
		
		
		
		try {
			dao = new NimbusDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().setLayout(new BorderLayout(0, 0));

		
		contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][100][100][100][100][100][100][100]"));
	
		setContentPane(contentPanel);
		contentPanel.setVisible(true);
		
		JPanel resultsPanel = new JPanel();
		contentPanel.add(resultsPanel, "cell 0 2 8 2,alignx center,aligny top");
		
		//Make the table uneditable
		TableModel model = new DefaultTableModel(appointments, colNames)
		  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
	
		resultsPanel.setLayout(new BorderLayout(0, 0));
		topTable = new JTable(model);
		
		//Set column widths to make table bigger
		TableColumnModel columnModel = topTable.getColumnModel();
		
		topTable.setRowHeight(0,25);
		topTable.setBorder(null);
		DefaultTableModel queryModel= search(true);
		topTable.setModel(queryModel);
		
		bottomTable = new JTable(model);
		
		//Set column widths to make table bigger
		TableColumnModel columnModel2 = bottomTable.getColumnModel();
		
		bottomTable.setRowHeight(0,25);
		bottomTable.setBorder(null);
		DefaultTableModel allHistoryModel= search(false);
		bottomTable.setModel(allHistoryModel);
		
		
		
		Image image;
		try {
			image = ImageIO.read(new File("Pictures\\AppointIcon.png"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			contentPanel.add(picLabel, "flowx,cell 0 0 2 2,alignx center,aligny center");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		topLabel = new JLabel("<html>Todays Appointments for " + name + "<br> Select an appointment to view details. </html>");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(topLabel, "cell 2 1 4 1,alignx center,aligny top");
		
		resultsPanel.add(topTable,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(topTable);
		scrollPane.setPreferredSize(new Dimension(700, 200));

		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		
		lblAppointmentHistory = new JLabel("Appointment History");
		lblAppointmentHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(lblAppointmentHistory, "cell 2 4 4 1,alignx center");
		
		secondPanel = new JPanel(); 
		contentPanel.add(secondPanel, "cell 0 5 8 3,alignx center,aligny top");
		secondPanel.setLayout(new BorderLayout(0, 0));
		
		secondPanel.add(bottomTable,BorderLayout.CENTER);
		JScrollPane scrollPane2 = new JScrollPane(bottomTable);
		scrollPane2.setPreferredSize(new Dimension(700, 200));

		secondPanel.add(scrollPane2, BorderLayout.CENTER);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPanel.add(btnClose, "cell 6 8 2 1,alignx center");
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
		topTable.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    //if (e.getClickCount() == 1) {
				  	if(topTable.getSelectedColumn() == 0){
				  		
					  	int appointmentID = Integer.parseInt(topTable.getValueAt(topTable.getSelectedRow(), 0).toString());
					    System.out.println(topTable.getValueAt(topTable.getSelectedRow(), 0).toString());
					    
					    OptionMenuFrame opt = new OptionMenuFrame();
					    opt.populateAppointment(appointmentID);

				  	}
			     //}
			   }
			});
		
	
	}

	public DefaultTableModel search(boolean top){
		
		//http://stackoverflow.com/questions/22238641/create-vector-for-defaulttablemodel
		 DefaultTableModel bottomtableModel;
		 DefaultTableModel toptableModel;
			try{
			
			//Get patient detials
			ResultSet rs = dao.getAppointmentDetails(0,"", "",0,0,doctorID);
			
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
			bottomtableModel = new DefaultTableModel(colNames, 0)	{
			    public boolean isCellEditable(int row, int column)
			    {
			      return false;//This causes all cells to be not editable
			    }
			  };
			  
			 toptableModel = new DefaultTableModel(colNames, 0)	{
				    public boolean isCellEditable(int row, int column)
				    {
				      return false;//This causes all cells to be not editable
				    }
				  };

			 Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	
	         int currentDay = localCalendar.get(Calendar.DATE);
	         int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
	         int currentYear = localCalendar.get(Calendar.YEAR);

	         String currentDaycorrect = Integer.toString(currentDay);
	         if(Integer.toString(currentDay).length() == 1)
	        	 currentDaycorrect = "0" + currentDaycorrect;
	         
	         String currentMonthcorrect = Integer.toString(currentMonth);
	         if(Integer.toString(currentMonth).length() == 1)
	        	 currentMonthcorrect = "0" + currentMonthcorrect;
	         
	         String currentDate = Integer.toString(currentYear) + "-" + currentMonthcorrect + "-" + currentDaycorrect;

	        	 
			  
	         
			while (rs.next()) {
				
				System.out.println("Currentdate: " + currentDate + " and rs.getString(Date)) : " + rs.getString("Date"));
				if(rs.getString("Date").equals(currentDate)){
					this.name = rs.getString("CombinedName");
					int data0 = rs.getInt("Appointment_ID");	
				    int data1 = rs.getInt("Patient_ID");
				    String data2 = rs.getString("FirstName");
				    String data3 = rs.getString("LastName");
				    //String data4 = rs.getString("CombinedName");
				    String data5 = rs.getString("Date");
				    String data6 = rs.getString("StartTime");
				    String data7 = rs.getString("EndTime");
				    Object[] rowData = new Object[] {data0,data1,data2,data3,data5,data6,data7};
				    toptableModel.addRow(rowData);
				}
				else{
					this.name = rs.getString("CombinedName");
					int data0 = rs.getInt("Appointment_ID");	
				    int data1 = rs.getInt("Patient_ID");
				    String data2 = rs.getString("FirstName");
				    String data3 = rs.getString("LastName");
				    //String data4 = rs.getString("CombinedName");
				    String data5 = rs.getString("Date");
				    String data6 = rs.getString("StartTime");
				    String data7 = rs.getString("EndTime");
				    Object[] rowData = new Object[] {data0,data1,data2,data3,data5,data6,data7};
				    bottomtableModel.addRow(rowData);
				}
			   
			}
			
			if(top)
				return toptableModel;
			else
				return bottomtableModel;
			
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

