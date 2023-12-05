package cms.gui;

import java.awt.EventQueue;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

//Here source(UserDashBoard) frame which we have to close 
public class UserDashBoard extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashBoard frame = new UserDashBoard();
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
	public UserDashBoard() {
		
		this.addWindowListener(this);//registering listener with the obj
		//first 'this' is source-UserDashBoard k obj ko
		//2nd this is for class which implements listener
		
		setExtendedState(Frame.MAXIMIZED_BOTH);//poora full screen frame aayega ....done through design
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//saare frames se bahar krdega ...whole software close krdega...coming out of whole Hotel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sirf wahi window close only...that is the current window...coming out of hotel room
		
		setBounds(100, 100, 461, 491);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnContacts = new JMenu("Contacts");
		mnContacts.setIcon(new ImageIcon(UserDashBoard.class.getResource("/cms/images/user (1).png")));
		menuBar.add(mnContacts);
		
		JMenuItem mi_add = new JMenuItem("Add");
		
		mi_add.addActionListener(this);
		
		mnContacts.add(mi_add);
		
		JMenuItem mi_update = new JMenuItem("Update");
		
		mi_update.addActionListener(this);
		
		mnContacts.add(mi_update);
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		
		mi_delete.addActionListener(this);//registering the listener
		
		mnContacts.add(mi_delete);
		
		JMenuItem mn_search = new JMenuItem("Search");
		mn_search.addActionListener(this);
		mnContacts.add(mn_search);
		
		JMenuItem mn_viewall = new JMenuItem("View all");
		mn_viewall.addActionListener(this);
		mnContacts.add(mn_viewall);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//window about to close 
		
		JOptionPane.showMessageDialog(this,"Thanku for using me");
		//isse wapas login window aajayegi
		Login login=new Login();
		login.setVisible(true);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//it will listen the click on the menu....but will not identify the which menu is being clicked
		String caption=e.getActionCommand();//getActionCommand returns the text written on the button or menuItem
		//belongs to ActionEvent class
		//System.out.println(caption);
		if(caption.equalsIgnoreCase("Add"))
		{
			Contact c=new Contact();
			c.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Update"))
		{
			UpdateContact c=new UpdateContact();
			c.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Delete"))
		{
			ContactDelete c=new ContactDelete();
			c.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Search"))
		{
			SearchContact c=new SearchContact();
			c.setVisible(true);
		}
		if(caption.equalsIgnoreCase("View all"))
		{
			AllContacts c=new AllContacts();
			c.setVisible(true);
		}
		
		
		
		
	}
}
