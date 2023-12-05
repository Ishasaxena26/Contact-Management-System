package cms.gui;

import java.awt.EventQueue;

import java.sql.*;//contains all classes that will interact with db 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Contact extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone1;
	private JTextField txtphone2;
	private JTextArea txtarea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//every prog in swing shld contain this code
		//anonymous inner class
		EventQueue.invokeLater(new Runnable() {//anonymous class object
			public void run() { //
				try {
					Contact frame = new Contact();
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
	public Contact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contact.class.getResource("/cms/images/user.png")));
		setTitle("Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//do this in every child window
		setBounds(100, 100, 634, 464);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(240, 255, 255));
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);//center me aayega frame....to place a window in center

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Noto Serif", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(248, 248, 255));
		lblNewLabel.setBounds(51, 30, 88, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(new Color(248, 248, 255));
		lblNewLabel_1.setFont(new Font("Noto Serif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(51, 86, 88, 29);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		
		txtname.addKeyListener(this);//registering the listener.....kab key press hogi to listen krega
		
		txtname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtname.setBounds(246, 34, 253, 27);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtemail.setBounds(246, 85, 253, 30);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone 1");
		lblNewLabel_2.setForeground(new Color(248, 248, 255));
		lblNewLabel_2.setFont(new Font("Noto Serif", Font.BOLD, 20));
		lblNewLabel_2.setBounds(51, 135, 88, 30);
		contentPane.add(lblNewLabel_2);
		
		txtphone1 = new JTextField();
		
		txtphone1.addKeyListener(this);
		
		txtphone1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtphone1.setBounds(246, 135, 253, 30);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Phone 2");
		lblNewLabel_3.setForeground(new Color(248, 248, 255));
		lblNewLabel_3.setFont(new Font("Noto Serif", Font.BOLD, 20));
		lblNewLabel_3.setBounds(51, 182, 100, 30);
		contentPane.add(lblNewLabel_3);
		
		txtphone2 = new JTextField();
		
		txtphone2.addKeyListener(this);
		
		txtphone2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtphone2.setBounds(246, 187, 253, 30);
		contentPane.add(txtphone2);
		txtphone2.setColumns(10);
		
		JLabel txtaddress = new JLabel("Address");
		
		txtaddress.addKeyListener(this);//text area se bahar aane k liye 
		
		txtaddress.setForeground(new Color(248, 248, 255));
		txtaddress.setFont(new Font("Noto Serif", Font.BOLD, 20));
		txtaddress.setBounds(51, 242, 100, 30);
		contentPane.add(txtaddress);
		
		JButton btnSubmit = new JButton("Submit");
		
		btnSubmit.addActionListener(this);//registering listener with source
		btnSubmit.addKeyListener(this);
		
		btnSubmit.setBackground(new Color(240, 255, 255));
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setFont(new Font("Noto Serif", Font.BOLD, 18));
		btnSubmit.setBounds(51, 360, 122, 30);
		contentPane.add(btnSubmit);
		
		 txtarea = new JTextArea();
		 txtarea.addKeyListener(this);
		txtarea.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		txtarea.setBounds(246, 249, 253, 82);
		contentPane.add(txtarea);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/user.png")));
		lblNewLabel_5.setBounds(248, 34, -53, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel iconName = new JLabel("");
		iconName.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/user (1).png")));
		iconName.setBounds(220, 33, 29, 27);
		contentPane.add(iconName);
		
		JLabel iconEmail = new JLabel("");
		iconEmail.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/gmail.png")));
		iconEmail.setBounds(220, 94, 29, 16);
		contentPane.add(iconEmail);
		
		JLabel iconPh1 = new JLabel("");
		iconPh1.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/phone-call (1).png")));
		iconPh1.setBounds(220, 135, 29, 24);
		contentPane.add(iconPh1);
		
		JLabel iconPh2 = new JLabel("");
		iconPh2.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/phone-call (1).png")));
		iconPh2.setBounds(220, 196, 29, 16);
		contentPane.add(iconPh2);
		
		JLabel iconAddress = new JLabel("");
		iconAddress.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/location (1).png")));
		iconAddress.setBounds(220, 249, 29, 30);
		contentPane.add(iconAddress);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        
		 addData();
	}
	
	void addData() { //userdefined data
		String cname=txtname.getText().trim();
		 String email=txtemail.getText().trim();
		 String ph1=txtphone1.getText().trim(); 
		 String ph2=txtphone2.getText().trim();
		 String address=txtarea.getText().trim();
		 
		 if(cname.length()==0 || email.length()==0 || ph1.isEmpty() || address.length()==0)
		 {
			JOptionPane.showMessageDialog(this, "Enter the details");
		 }
		 else
		 {   //step 1&2
			 Connection con=DbConnection.openConnection();//static method hai openConnection toh class name(DbConnection) k sath use kiya 
			 //code reusability
			 
			 //step 3:create communication channel using prepared statement
			 PreparedStatement ps=null;
			 
			 try {
				 
				 //making query in the form string....dont make any error in the line as it will be sent to dbms wahan yeh compile hoga
				 String insert_query="insert into contact_details( name, email, phone1, phone2, address, date)values(?,?,?,?,?,?)";//isko bjhenge db paas
				 //table se direct copy paste kro saare col name ek sath
				 //remove serial number wo khudh aajayegi
				 //? are known as placeholder.....values badalti rahengi ....value replace hoke yahan aajayegi frame se
				 // number of ?=no.of cols
				 
				 //COMMUNICATION CHANNEL BANRA:
				 //ps stores the address of buffer which contains compiled query
				 ps=con.prepareStatement(insert_query);//passes the query to DBMS and DBMS compiler compiles the query and store into a buffer->referred by ps
				 //ek he baar compile hogi query
				 
				 java.util.Date d=new java.util.Date();//gives current date of java
				 
				 //conversion of util date into sql date
				 //sql contains date package
				 java.sql.Date sd=new java.sql.Date(d.getTime());//Date class contains both time and date 
				 //getTime()-returns current date to long format
				 
				 //SETTING VALUES 
				 //naam same hoskta hai col name and jisme value store hori frame se
				 //1,2,3,....jaise jaise values aayengi question mark? ki jagah replace hota jayega 
				 ps.setString(1,cname);//value fetch from control are getting set for particular column 
				 ps.setString(2,email);
				 ps.setString(3,ph1);
				 ps.setString(4,ph2);
				 ps.setString(5,address);
				 ps.setDate(6,sd);
				// System.out.println(ps);//for testing
				 
				// jab value sucessfully database me store hojayegi tab yeh execute hoga positive value deke
				 int status=ps.executeUpdate();//used to fire insert/update/delete query
				 //method returns int value
				 //If the status contains a positive+ no.then that means value is stored in table
				 
				 if(status>0) {
					 JOptionPane.showMessageDialog(this, "Contact added successfully");
					 
					 //jab contact added successfully then wahan pe fields khaali hojayengi 
					 txtname.setText("");
					 txtarea.setText("");
					 txtemail.setText("");
					 txtphone1.setText("");
					 txtphone2.setText("");
					 
					 
				 }
			 }
				 
				 
			 catch(SQLException se) 
			 {
				JOptionPane.showMessageDialog(this, ph1+" Phone number already exists"); //will not take duplicate values
			  se.printStackTrace();
			 }		 	
			 finally {
				 try
				 { 
					 if(ps!=null)
					 {
						 ps.close();
					 }
					 
					 if(con!=null)
					 {
						 con.close();
					 }
				 }
				 catch(SQLException se)//checked exception might be raised so catching it
				 {
					 se.printStackTrace();
				 }
			 
		 }
		
		
	}

	}

	//3 methods in keylistener
	
	@Override
	public void keyTyped(KeyEvent e) {//KeyEvent is a class....gives additional functionalities
		
		char c=e.getKeyChar();//gives character 
		
		System.out.println("typed character is "+c);
		
		//getSource()returns the object that is generating the event 
		if(e.getSource()==txtname)
		{
			 if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE))//to allow backspace 
					 {
				       e.consume();//yeh function galat key screen pe type he nhi hone dega
				       JOptionPane.showMessageDialog(this,"Only alphabets allowed");
					 }
		}
		
		if(e.getSource()==txtphone1 || e.getSource()==txtphone2)
		{
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE))
			 {
		       e.consume();//yeh function galat key screen pe type he nhi hone dega
		       JOptionPane.showMessageDialog(this,"Only numbers are allowed");
			 }
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //First this function works then keyTyped works
		
		//int code=e.getKeyCode();//gives ASCII value
		//System.out.println("code is "+code);
		
		if(e.getKeyCode()==10)//10 is the key code of enter
		{
		 addData();
		}
		
		if(e.getSource()==txtarea)
		{
			if(e.getKeyCode()==KeyEvent.VK_TAB)//jab user tab press krega to wahan se next component pe jaane k liye code h yeh ....next component is button in our frame
			{
				e.consume();
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				//Keyboardfocus manager belongs to awt package
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

