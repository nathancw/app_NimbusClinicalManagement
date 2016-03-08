import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JCheckBox;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;


public class BookAppointmentPanel extends JPanel {
	private JTextField patientIDTextField;
	private NimbusDAO dao = null;
	private JTextField textField;
	private JComboBox endcomboBox;
	/**
	 * Create the panel.
	 */
	public BookAppointmentPanel() {
		try {
			dao = new NimbusDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][150][100][150][100][100]", "[100][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		JLabel lblNewLabel_2 = new JLabel("Schedule Patient Appointment");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblNewLabel_2, "cell 2 0 4 1,alignx center");
		
		JPanel TextFieldsPanel = new JPanel();
		TextFieldsPanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(TextFieldsPanel, "cell 2 1 4 5,grow");
		TextFieldsPanel.setLayout(new MigLayout("", "[80][150,grow][100][150,grow][20]", "[30][30][30][30][30]"));
		
		JLabel lblPatientID = new JLabel("Patient ID:");
		TextFieldsPanel.add(lblPatientID, "cell 0 0,alignx trailing");
		
		patientIDTextField = new JTextField();
		TextFieldsPanel.add(patientIDTextField, "cell 1 0,growx");
		patientIDTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		TextFieldsPanel.add(lblDate, "cell 2 0,alignx trailing");
		//
		JComboBox dateComboBox = new JComboBox();
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		
		TextFieldsPanel.add(datePicker, "cell 3 0,growx");
		///		
		///
		JLabel lblDoctor = new JLabel("Doctor:");
		TextFieldsPanel.add(lblDoctor, "cell 0 1,alignx trailing");
		
		JComboBox doctorComboBox = new JComboBox();
		TextFieldsPanel.add(doctorComboBox, "cell 1 1,growx");
		doctorComboBox.setModel(getDoctors());
		
		JLabel lblStartTime = new JLabel("Start Time:");
		TextFieldsPanel.add(lblStartTime, "cell 2 1,alignx trailing");
		
		JComboBox startcomboBox = new JComboBox();
		startcomboBox.setModel(getStartTimes());
		startcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Time Changed");
				String startTime = (String) ((JComboBox) e.getSource()).getSelectedItem();
				endcomboBox.addItem(startTime);
				endcomboBox.setSelectedItem(getEndTime(startTime));
			}
		});
		TextFieldsPanel.add(startcomboBox, "cell 3 1,growx");
		
		
		JLabel lblReason = new JLabel("Reason:");
		TextFieldsPanel.add(lblReason, "cell 0 2,alignx trailing");
		
		JComboBox reasoncomboBox = new JComboBox();
		TextFieldsPanel.add(reasoncomboBox, "cell 1 2,growx");
		
		JLabel lblEndTime = new JLabel("End Time:");
		TextFieldsPanel.add(lblEndTime, "cell 2 2,alignx trailing");
		
		endcomboBox = new JComboBox();
		TextFieldsPanel.add(endcomboBox, "cell 3 2,growx");
		endcomboBox.setEnabled(false);
		
		JLabel lblProcedure = new JLabel("Procedure:");
		TextFieldsPanel.add(lblProcedure, "cell 0 3,alignx trailing");
		
		JComboBox procedurecomboBox = new JComboBox();
		TextFieldsPanel.add(procedurecomboBox, "cell 1 3,growx");
		
		JCheckBox chckbxSendEmail = new JCheckBox("Send Email");
		TextFieldsPanel.add(chckbxSendEmail, "cell 3 3,alignx left");
		
		JLabel lblSpecialty = new JLabel("Specialty:");
		TextFieldsPanel.add(lblSpecialty, "cell 0 4,alignx trailing");
		
		textField = new JTextField();
		TextFieldsPanel.add(textField, "cell 1 4,growx");
		textField.setColumns(10);
		
		JCheckBox chckbxChckedIn = new JCheckBox("Checked In?");
		TextFieldsPanel.add(chckbxChckedIn, "cell 3 4,alignx left");
		
		JPanel CommentsPanel = new JPanel();
		CommentsPanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(CommentsPanel, "cell 2 7 4 5,grow");
		CommentsPanel.setLayout(new MigLayout("", "[100,grow][150][100][150]", "[30,grow][30][30][30][30]"));
		
		JTextArea textArea = new JTextArea();
		CommentsPanel.add(textArea, "cell 0 0 4 5,grow");
		
		JButton btnViewOpenAppointments = new JButton("View Open Appointments");
		btnViewOpenAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Do we want to preview a table of open appointments and have them click one easier use? It will auto fille data in the form.");
			
			}
		});
		contentPanel.add(btnViewOpenAppointments, "cell 2 12 2 1");
		
		JButton btnCancel = new JButton("Cancel");
		contentPanel.add(btnCancel, "cell 4 12,alignx right");
		
		JButton btnBookNewAppointment = new JButton("Book New Appointment");
		contentPanel.add(btnBookNewAppointment, "cell 5 12");

	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
	
	public DefaultComboBoxModel getStartTimes(){
		DefaultComboBoxModel model = null;
		ArrayList<String> times = new ArrayList<String>();
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.TimeSlot";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				times.add("");
				while(rs.next()){
					times.add(rs.getString("StartTime"));
				}
				
				model = new DefaultComboBoxModel(times.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}	
		
	public String getEndTime(String startTime){
		String endTime = null;
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.TimeSlot where StartTime = ?";
			System.out.println("SqlQUERY: " + sqlQuery + startTime);
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, startTime);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
			
				if(rs.next()){
					endTime = rs.getString("EndTime");
					System.out.println("End Time: " + endTime);
				}
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Can't get connection");
		
		return endTime;
	}
	
	public DefaultComboBoxModel getDoctors(){
		DefaultComboBoxModel model = null;
		ArrayList<String> doctors = new ArrayList<String>();
		
		if(dao!=null){
			String sqlQuery = "Select * from NCMSE.NCM.Doctor";
			ResultSet rs = null;
			try {
				
				PreparedStatement stmt = dao.getConnection().prepareStatement(sqlQuery);
				rs = stmt.executeQuery();
				ResultSetMetaData rsMeta = rs.getMetaData();
				
				doctors.add("");
				while(rs.next()){
					doctors.add(rs.getString("CombinedName"));
				}
				
				model = new DefaultComboBoxModel(doctors.toArray());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		
	
		}
		else
			System.out.println("Can't get connection");
		
		return model;
	}

}
