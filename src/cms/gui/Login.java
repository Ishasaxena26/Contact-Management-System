package cms.gui;

import java.awt.EventQueue;
import java.awt.event.*;//awt is package ....uske ander ek folder hai uska naam hai event 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/cms/images/work.png")));
		setTitle("LoginFrame\r\n");
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//helps to close the frame..provides closing feature unlike in AWT 
		setBounds(100, 100, 669, 403);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/cms/images/id.png")));
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 25));
		lblNewLabel.setBounds(46, 51, 67, 57);
		contentPane.add(lblNewLabel);//Label is added in panel
		
		txtid = new JTextField();
		txtid.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		txtid.setForeground(new Color(0, 0, 205));
		txtid.setBackground(new Color(169, 169, 169));
		txtid.setBounds(159, 57, 181, 38);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblNewLabel_1.setBounds(21, 127, 105, 79);
		contentPane.add(lblNewLabel_1);
		
		txtpassword = new JPasswordField();
		txtpassword.setForeground(new Color(0, 0, 255));
		txtpassword.setBackground(new Color(169, 169, 169));
		txtpassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		txtpassword.setBounds(159, 147, 181, 38);
		contentPane.add(txtpassword);
		
		JButton btnSubmit = new JButton("SUBMIT");//button class ka object ....JButton is a class
		
		//object of current class-Login as ActionListener is a interface whose object cant be made
		btnSubmit.addActionListener(this);//Run-time polymorphism with interface 
		//register the listener with button
		//button is generating event
		
		btnSubmit.setIcon(new ImageIcon(Login.class.getResource("/cms/images/Bookmark.png")));
		btnSubmit.setBackground(new Color(169, 169, 169));
		btnSubmit.setFont(new Font("Calibri Light", Font.BOLD, 20));
		btnSubmit.setBounds(159, 220, 181, 43);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/cms/images/scene.jpg")));
		lblNewLabel_2.setBounds(426, 54, 197, 244);
		contentPane.add(lblNewLabel_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //ActionListener's(interface) method is actionPerformed
		//Functional interface as only one method is present
		
		//System.out.println("Button is clicked");
		
		String id=txtid.getText().trim();//fetches value from text box in the form of string
		//trim method removes leading and trailing spaces ....but not the spaces present in between
		
		char[] pass=txtpassword.getPassword();
		String password =String.valueOf(pass).trim();//string ka static method hai
		//string conversion
		
		//System.out.println(id + password);
		
		//Validations:
		
		if( id.length()==0 || password.isEmpty() )
		{
			//2 parameters
		 JOptionPane.showMessageDialog(this,"Please provide id and password");//this refers to current class object...second parameter is message 
		 	
		}	
		else
		{
			if(id.equalsIgnoreCase("Isha")&& password.equals("123@"))
			{
				//new frame to be displayed if credentials are correct
				UserDashBoard user = new UserDashBoard();//step1
			     user.setVisible(true);//step2
			     
			     //iske baad login credentials wale frame ko band krna padega
			     this.dispose();//to dispose(close) the frame-login wala frame
			     //this refers to current obj-frame
			     
			     
			     
			}
			
			else {
				//4 parameters
				JOptionPane.showMessageDialog(this, "Invalid credentials","Login Error",JOptionPane.ERROR_MESSAGE);
				//3rd param-title of box
				//4th param-error message
				
			}
		}
		
	}
}
