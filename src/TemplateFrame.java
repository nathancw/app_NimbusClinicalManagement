import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;

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


public class TemplateFrame extends JPanel {

	String directoryList[] = {"Directory","Create New","Exit"};
	String[] colNames = {"Name","ID","DoB","Gender"};
	Object[][] patients = {
			{"Blank","111","080808","F"},
			{null,null,null,null,}};
	private JPanel contentPane;
	private JPanel contentPanel;	
	private JPanel bufferPanel;
	private JList categoriesList;
	private JPanel dataPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemplateFrame frame = new TemplateFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TemplateFrame() {
		
		//setTitle("Software");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		
		/*JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create New");
		fileMenu.add(mntmNewMenuItem);
		*/
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
				
		bufferPanel = new JPanel();
		contentPanel.add(bufferPanel);
		bufferPanel.setLayout(new MigLayout("", "[100,grow,left][100,grow][100][100][100][100][100][100][100]", "[50,grow][100][100,grow][100][100][300][50,grow]"));
				
				
		categoriesList = new JList(directoryList);
		categoriesList.setBorder(new LineBorder(new Color(0, 0, 0)));
				
		bufferPanel.add(categoriesList, "cell 0 0 1 7,grow");
						
		dataPanel = new JPanel();
		bufferPanel.add(dataPanel, "cell 1 0 8 7,grow");
		
		//ActionListener
		/*categoriesList.addListSelectionListener(new ListSelectionListener()
		{
		  public void valueChanged(ListSelectionEvent ev)
		  {	
			 // System.out.println("Selected:" + ev.getFirstIndex());
			 String selectedValues = (String)categoriesList.getSelectedValue();
			 System.out.println("Selected:" + selectedValues); 
			 remove(contentPanel);
			 MIGLogin login = new MIGLogin();
			 getContentPane().add(login.getContentPane());
			 setVisible(true);
			 //add(login.getContentPane());
			 revalidate();
			 repaint();
			
		
		  } 
		}); */
	}

	public JPanel getContentPanel(){
		return contentPane;
		
	}

	
}
