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

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.text.NumberFormat;
import java.text.ParseException;


import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;


public class BillingPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	
	String[] colNames = {"Name","ID","DoB","Gender"};
	Object[][] patients = {
			{"Blank","111","080808","F"},
			{null,null,null,null,}};
	
	JTable table;
	public BillingPanel() {
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
		billInformation.setLayout(new MigLayout("", "[25][100][125,grow][100][125,grow][25]", "[30][30][30][30,grow]"));
		
		JLabel lblChargeDate = new JLabel("Charge Date:");
		billInformation.add(lblChargeDate, "cell 1 0,alignx trailing");
		
		JFormattedTextField chargeDateField = new JFormattedTextField();
		billInformation.add(chargeDateField, "cell 2 0,growx");
		
		JLabel lblProcedure = new JLabel("Procedure:");
		billInformation.add(lblProcedure, "cell 1 1,alignx trailing");
		
		JComboBox procedureBox = new JComboBox();
		billInformation.add(procedureBox, "cell 2 1,growx");
		
		JLabel lblReason = new JLabel("Reason:");
		billInformation.add(lblReason, "cell 3 1,alignx trailing");
		
		JComboBox reasonBox = new JComboBox();
		billInformation.add(reasonBox, "cell 4 1,growx");
		
		JLabel lblNewLabel = new JLabel("Date Issued:");
		billInformation.add(lblNewLabel, "cell 1 2,alignx trailing");
		
		JFormattedTextField dateIssuedField = new JFormattedTextField();
		billInformation.add(dateIssuedField, "cell 2 2,growx");
		
		JLabel lblAmount = new JLabel("Amount:");
		billInformation.add(lblAmount, "cell 3 2,alignx trailing");
		
		NumberFormat paymentFormat = NumberFormat.getCurrencyInstance();
		JFormattedTextField amountField = new JFormattedTextField(paymentFormat);
		amountField.setValue(500.0);
		
		billInformation.add(amountField, "cell 4 2,growx");
		
		JLabel lblPaid = new JLabel("Paid:");
		billInformation.add(lblPaid, "cell 1 3");
		
		JPanel billInfoPanel = new JPanel();
		billInformation.add(billInfoPanel, "cell 2 3,grow");
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		billInfoPanel.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		billInfoPanel.add(rdbtnNo);
		
		JLabel lblDatePaid = new JLabel("Date Paid:");
		billInformation.add(lblDatePaid, "cell 3 3,alignx trailing");
		
		JFormattedTextField datePaidField = new JFormattedTextField();
		billInformation.add(datePaidField, "cell 4 3,growx");
		
		MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(datePaidField);
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
		  
		table.setBorder(null);
		tablePanel.add(table,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		//NEED THE BELOW PIECE OF CODE TO LIMIT THE SIZE. Or else it gets super large for some reason.
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
			
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    //if (e.getClickCount() == 1) {
				  	if(table.getSelectedColumn() == 0){
				  		
				  	//Edit on table click
			    	 System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
			    	 //PatientInformationFrame patientframe = new PatientInformationFrame();
			         //patientframe.show("Basic Information");
			         
			         
				  	}
			     //}
			   }
			});

	}

}
