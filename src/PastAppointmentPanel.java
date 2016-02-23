import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class PastAppointmentPanel extends JPanel {
	private JTextField txtBobSmith;

	/**
	 * Create the panel.
	 */
	public PastAppointmentPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100,grow][100][100][100][100,grow]", "[100,grow][100][100,grow][100,grow][100][100][100]"));
		
		JLabel lblNewLabel = new JLabel("Past Appointments");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(lblNewLabel, "cell 3 0 3 1,alignx center");
		
		JLabel lblPatientName = new JLabel("Patient Name:");
		lblPatientName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel.add(lblPatientName, "flowx,cell 3 1,alignx trailing");
		
		txtBobSmith = new JTextField();
		txtBobSmith.setText("Bob Smith");
		panel.add(txtBobSmith, "cell 4 1 2 1,growx");
		txtBobSmith.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 3 9 5,grow");
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[100][100][100][100][100][100][100][100]", "[50][50][50][50][50][50][50][50][50][50][50][50]"));

	}

}
