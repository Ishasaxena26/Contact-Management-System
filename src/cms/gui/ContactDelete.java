package cms.gui;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ContactDelete extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtdelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactDelete frame = new ContactDelete();
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
	public ContactDelete() {
		setTitle("Contact Delete\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 593, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel phno = new JLabel("Phone No");
		phno.setFont(new Font("Carlito", Font.BOLD, 20));
		phno.setBackground(new Color(240, 248, 255));
		phno.setForeground(new Color(0, 0, 0));
		phno.setBounds(29, 55, 115, 25);
		contentPane.add(phno);
		
		txtdelete = new JTextField();
		txtdelete.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtdelete.setBounds(161, 52, 166, 30);
		contentPane.add(txtdelete);
		txtdelete.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		
		btndelete.addActionListener(this);
		
		btndelete.setFont(new Font("Carlito", Font.BOLD, 20));
		btndelete.setBounds(368, 52, 132, 32);
		contentPane.add(btndelete);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	 
		String phonenumber=txtdelete.getText().trim();
		if(phonenumber.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Enter the number");
		}
		else
		{
			int choice=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete "+phonenumber);//return type is int
			
			/*System.out.println(choice);
			//on pressing YES o/p is 0
			//on NO 1
			//ON CANCEL 2 */
			
			if(choice==0) {
				Connection con=DbConnection.openConnection();//database connection established
				PreparedStatement ps=null;
				
				try {
					String delete_query="delete from contact_details where phone1=?";
					//where clause is compulsory otherwise it will delete all data from the table
					ps=con.prepareStatement(delete_query);
					ps.setString(1, phonenumber);
					System.out.println(ps);
					int status=ps.executeUpdate();
					if(status>0) {
						JOptionPane.showMessageDialog(this, "Contact deleted successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(this, "No such "+phonenumber+" number exists");
					}
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				finally
				{
					 try
					 { 
						 if(ps!=null)
						 {
							 ps.close();
						 }
						 
						
						 //dont close the connection here abhi 
					 }
					 catch(SQLException se)//checked exception might be raised so catching it
					 {
						 se.printStackTrace();
					 }

				}
			}
			
		}
		
	}
}
