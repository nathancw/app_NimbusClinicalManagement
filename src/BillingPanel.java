import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;


public class BillingPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BillingPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[100][100.00][100][100][100][100][100][100][100]", "[100][30,grow][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30][30]"));
		
		JPanel tablePanel = new JPanel();
		contentPanel.add(tablePanel, "cell 1 1 7 7,grow");
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel billInformation = new JPanel();
		billInformation.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Bill Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(billInformation, "cell 2 9 5 5,grow");
		billInformation.setLayout(new MigLayout("", "[25][100][125,grow][100][125,grow][25]", "[30][30][30][30,grow]"));
		
		JLabel lblChargeDate = new JLabel("Charge Date:");
		billInformation.add(lblChargeDate, "cell 1 0,alignx trailing");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		billInformation.add(formattedTextField_1, "cell 2 0,growx");
		
		JLabel lblProcedure = new JLabel("Procedure:");
		billInformation.add(lblProcedure, "cell 1 1,alignx trailing");
		
		JComboBox comboBox_1 = new JComboBox();
		billInformation.add(comboBox_1, "cell 2 1,growx");
		
		JLabel lblReason = new JLabel("Reason:");
		billInformation.add(lblReason, "cell 3 1,alignx trailing");
		
		JComboBox comboBox = new JComboBox();
		billInformation.add(comboBox, "cell 4 1,growx");
		
		JLabel lblNewLabel = new JLabel("Date Issued:");
		billInformation.add(lblNewLabel, "cell 1 2,alignx trailing");
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		billInformation.add(formattedTextField_2, "cell 2 2,growx");
		
		JLabel lblAmount = new JLabel("Amount:");
		billInformation.add(lblAmount, "cell 3 2,alignx trailing");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		billInformation.add(formattedTextField, "cell 4 2,growx");
		
		JLabel lblPaid = new JLabel("Paid:");
		billInformation.add(lblPaid, "cell 1 3");
		
		JPanel panel = new JPanel();
		billInformation.add(panel, "cell 2 3,grow");
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		panel.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		panel.add(rdbtnNo);
		
		JLabel lblDatePaid = new JLabel("Date Paid:");
		billInformation.add(lblDatePaid, "cell 3 3,alignx trailing");
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		billInformation.add(formattedTextField_3, "cell 4 3,growx");

	}

}
