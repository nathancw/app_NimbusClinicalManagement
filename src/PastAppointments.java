import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class PastAppointments extends JPanel {

	/**
	 * Create the panel.
	 */
	public PastAppointments() {
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[100,grow][100][100][100][100][100][100][100][100,grow]", "[100,grow][100][100,grow][100][100][100][100]"));

	}

}
