import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.awt.FlowLayout;


public class BillingPanel extends JPanel {

	private JFormattedTextField amountField;
	private JFormattedTextField dateIssuedField;
	private JFormattedTextField chargeDateField;
	private JFormattedTextField datePaidField;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JComboBox procedureBox;
	private JButton btnEdit;
	private JButton btnSave;
	private int patient_ID;
	private int billing_ID;
	private Map<Integer,Integer> billingIDs = new HashMap<Integer,Integer>();
	private NimbusDAO dao;
	private Map<String,Integer> procedureIDs;
	private Map<Integer,Double> procedureCosts;
	
	String[] colNames = {"Patient_ID", "Procedure", "Amount", "DateIssued", "ChargeDate, DatePaid"};
	Object[][] patients = {
			{"Blank","Blank","080808","F","F"},
			{null,null,null,null,}};
	
	JTable table;
	public BillingPanel(int pID) {
		try {
			dao = new NimbusDAO();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		this.patient_ID = pID;
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][100][100][100][100][100][100]", "[100][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		JLabel lblSelectAPatient = new JLabel("Select a Patient Bill to view its data below");
		contentPanel.add(lblSelectAPatient, "cell 2 0 5 1,alignx center");
		
		JPanel tablePanel = new JPanel();
		contentPanel.add(tablePanel, "cell 1 1 7 7,grow");
		
		JPanel billInformation = new JPanel();
		billInformation.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Bill Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(billInformation, "cell 2 9 5 5,grow");
		billInformation.setLayout(new MigLayout("", "[25][100][125][100][125][25]", "[30][30][30][30,grow]"));
		
		JLabel lblChargeDate = new JLabel("Charge Date:");
		billInformation.add(lblChargeDate, "cell 1 0,alignx right");
		
		chargeDateField = new JFormattedTextField();
		billInformation.add(chargeDateField, "cell 2 0,growx");
		
		JLabel lblProcedure = new JLabel("Procedure:");
		billInformation.add(lblProcedure, "cell 3 0,alignx right");
		
		procedureBox = new JComboBox();
		billInformation.add(procedureBox, "cell 4 0,growx");
		
		JLabel lblNewLabel = new JLabel("Date Issued:");
		billInformation.add(lblNewLabel, "cell 1 2,alignx right");
		
		dateIssuedField = new JFormattedTextField();
		billInformation.add(dateIssuedField, "cell 2 2,growx");
		
		JLabel lblAmount = new JLabel("Amount:");
		billInformation.add(lblAmount, "cell 3 2,alignx right");
	
		NumberFormat paymentFormat = NumberFormat.getCurrencyInstance();
		amountField = new JFormattedTextField(paymentFormat);

		billInformation.add(amountField, "cell 4 2,growx");
		
		JLabel lblPaid = new JLabel("Paid:");
		billInformation.add(lblPaid, "cell 1 3,alignx right");
		
		JPanel billInfoPanel = new JPanel();
		billInformation.add(billInfoPanel, "cell 2 3,grow");
		
		rdbtnYes = new JRadioButton("Yes");
		billInfoPanel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		billInfoPanel.add(rdbtnNo);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnYes);
		group.add(rdbtnNo);
		
		
		JLabel lblDatePaid = new JLabel("Date Paid:");
		billInformation.add(lblDatePaid, "cell 3 3,alignx right");
		
		datePaidField = new JFormattedTextField();
		billInformation.add(datePaidField, "cell 4 3,growx");
		
		MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(datePaidField);
			dateMask.install(dateIssuedField);
			dateMask.install(chargeDateField);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		TableModel model = new DefaultTableModel(patients, colNames)
		  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		tablePanel.setLayout(new BorderLayout(0, 0));
		table = new JTable(model);
		table.setModel(search());
		  
		//table.setRowHeight(0,30);
		table.setBorder(null);
		tablePanel.add(table,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		//NEED THE BELOW PIECE OF CODE TO LIMIT THE SIZE. Or else it gets super large for some reason.
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
		JButton btnGenerateBill = new JButton("Generate PDF Bill");
		contentPanel.add(btnGenerateBill, "cell 2 15 2 1");
		
		JPanel savebtnpanel = new JPanel();
		contentPanel.add(savebtnpanel, "cell 7 15,grow");
		savebtnpanel.setLayout(new BorderLayout(0, 0));
		//savebtnpanel.setVisible(false);
		
		btnSave = new JButton("Save");
		savebtnpanel.add(btnSave, BorderLayout.CENTER);
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean updated = updateDatabase();
				if(updated)
					JOptionPane.showMessageDialog(new JFrame(), "Changes Saved Successfully in database.");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Changes not saved successfully. An error occurred.");
				setAllUneditable();
			}
		});
		
		JPanel editbtnpanel = new JPanel();
		contentPanel.add(editbtnpanel, "cell 7 16,grow");
		editbtnpanel.setLayout(new BorderLayout(0, 0));
		//editbtnpanel.setVisible(false);
		
		btnEdit = new JButton("Edit");
		editbtnpanel.add(btnEdit, BorderLayout.CENTER);
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setAllEditable();
			}
		});
			
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {	
				  billing_ID = billingIDs.get(table.getSelectedRow());

			    	 String id = table.getValueAt(table.getSelectedRow(),0).toString();
			    	 String procedure = table.getValueAt(table.getSelectedRow(),1).toString();
			    	 String amount = table.getValueAt(table.getSelectedRow(),2).toString();
			    	 String issued = table.getValueAt(table.getSelectedRow(),3).toString();
			    	 String charged = table.getValueAt(table.getSelectedRow(),4).toString();
			    	 String datePaidDate = table.getValueAt(table.getSelectedRow(),5).toString();
			    	 
			    	 amountField.setValue(Double.parseDouble(amount));
			    	 dateIssuedField.setValue(issued);
			    	 chargeDateField.setValue(charged);
			    	 procedureBox.setModel(getProcedures());
			    	 procedureBox.setSelectedItem(procedure);
			    	 datePaidField.setValue(datePaidDate);
			    	 
			    	 if(datePaidDate.substring(6,10).equals("2999")){
			    		 rdbtnYes.setSelected(false);
			    		 rdbtnNo.setSelected(true);
			    	 }
			    	 else{
			    		 rdbtnYes.setSelected(true);
			    		 rdbtnNo.setSelected(false);
			    	 }
			    	 
			    	 
			   }
			});
		
		setAllUneditable();
		
		//End of constructor
	}
	
	
	
	public DefaultTableModel search(){
		
		//http://stackoverflow.com/questions/22238641/create-vector-for-defaulttablemodel
		 DefaultTableModel tableModel;
		 int count = 0;

			try{
				
			//Get patient detials
			ResultSet rs = dao.getBillingHistory(patient_ID);
			
			//Get metadata and prepare columnnames, even thought this shouldnt change
			ResultSetMetaData rsMeta = rs.getMetaData();
			
			Vector<String> colNames= new Vector<String>();   // your columns names
			colNames.add(rsMeta.getColumnName(1));
			colNames.add(rsMeta.getColumnName(8));
			colNames.add(rsMeta.getColumnName(3));
			colNames.add(rsMeta.getColumnName(4));
			colNames.add(rsMeta.getColumnName(5));
			colNames.add(rsMeta.getColumnName(7));
			
			//Make the cells uneditable while creating the tablemodel
			tableModel = new DefaultTableModel(colNames, 0)	{
			    public boolean isCellEditable(int row, int column)
			    {
			      return false;//This causes all cells to be not editable
			    }
			  };

			while (rs.next()) {
				String data0 = rs.getString("Patient_ID");	
			    String data1 = rs.getString("ProcedureName");
			    String data2 = rs.getString("Amount");
			    String dob = rs.getString("DateIssued");
			    String data3 = dob.substring(5, 7) + "/" + dob.substring(8,10) + "/" + dob.substring(0,4);
			    String cob = rs.getString("ChargeDate");
			    String datePaid = rs.getString("DatePaid");
			    String data4 = cob.substring(5, 7) + "/" + cob.substring(8,10) + "/" + cob.substring(0,4);
			    String data5 = datePaid.substring(5, 7) + "/" + datePaid.substring(8,10) + "/" + datePaid.substring(0,4);
			    Object[] rowData = new Object[] {data0,data1,data2,data3,data4,data5};
			  
			    billingIDs.put(count,rs.getInt("Billing_ID"));
			    count++;
			    tableModel.addRow(rowData);
			}
			
			
			return tableModel;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}

	public void setAllUneditable(){
		UIManager.put("ComboBox.disabledForeground", Color.black);
		btnSave.setEnabled(false);
		btnEdit.setEnabled(true);
		amountField.setEditable(false);
		datePaidField.setEditable(false);
		chargeDateField.setEditable(false);
		dateIssuedField.setEditable(false);
		procedureBox.setEnabled(false);
		rdbtnYes.setEnabled(false);
		rdbtnNo.setEnabled(false);
	}
	
	public void setAllEditable(){
		btnSave.setEnabled(true);
		btnEdit.setEnabled(false);
		amountField.setEditable(true);
		datePaidField.setEditable(true);
		chargeDateField.setEditable(true);
		dateIssuedField.setEditable(true);
		procedureBox.setEditable(true);
		rdbtnYes.setEnabled(true);
		rdbtnNo.setEnabled(true);
		
	}
	
	public DefaultComboBoxModel getProcedures(){
		
		DefaultComboBoxModel model = null;
		ArrayList<String> procedures = new ArrayList<String>();
		procedureIDs = new HashMap<String,Integer>();
		procedureCosts = new HashMap<Integer,Double>();
		
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
					procedureCosts.put(rs.getInt("Procedure_ID"), rs.getDouble("Cost"));
				}
				
				model = new DefaultComboBoxModel(procedures.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else
			System.out.println("Can't get database connection");
		
		return model;
	}
	
	public boolean updateDatabase(){
		
		String procedure = String.valueOf(procedureBox.getSelectedItem());
		String amount = amountField.getText().substring(1,amountField.getText().length()).replace(",","");
		int procedure_ID = procedureIDs.get(procedureBox.getSelectedItem());
		
		int paid;
		if(rdbtnYes.isSelected())
			paid = 1;
		else
			paid = 0;

		String datePaid;
	
		boolean updated = false;
		try{
			///NEED ERROR CHECKING WHEN UPDATING. Dates can be all messed up
			if(paid == 1 && datePaidField.getText().substring(6,10).equals("2999")){
				Calendar today = Calendar.getInstance();
				Date todayDate = today.getTime();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				datePaid = df.format(todayDate);
			}
			else{
				datePaid = datePaidField.getText();
			}
			if(paid==0){
				datePaid = "12/31/2999";
			}
				
			updated = dao.editBillingHistory(true, patient_ID, procedure_ID, Double.parseDouble(amount), dateIssuedField.getText(), chargeDateField.getText(), paid, datePaid,billing_ID);
			datePaidField.setText(datePaid);
			table.setModel(search());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
		return updated;
	}
	

}
