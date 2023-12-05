package cms.gui;

import java.awt.EventQueue;
import java.sql.*;
import cms.dbtask.DbConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SearchContact extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextArea txtaddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchContact frame = new SearchContact();
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
	public SearchContact() {
		setTitle("Search Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(32, 38, 87, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone No.");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(32, 112, 87, 26);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtname.setBounds(156, 37, 167, 26);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("OR");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_2.setBounds(213, 73, 57, 24);
		contentPane.add(lblNewLabel_2);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtphone.setBounds(156, 113, 167, 24);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		
		btnsearch.addActionListener(this);
		
		btnsearch.setBackground(new Color(255, 255, 255));
		btnsearch.setForeground(new Color(0, 0, 0));
		btnsearch.setFont(new Font("Calibri", Font.BOLD, 14));
		btnsearch.setBounds(325, 163, 85, 24);
		contentPane.add(btnsearch);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_3.setBounds(32, 223, 87, 24);
		contentPane.add(lblNewLabel_3);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtemail.setBounds(156, 223, 167, 24);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_4.setBounds(32, 277, 87, 24);
		contentPane.add(lblNewLabel_4);
		
		txtaddress = new JTextArea();
		txtaddress.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtaddress.setBounds(156, 276, 167, 60);
		contentPane.add(txtaddress);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String name=txtname.getText().trim();
		String phone=txtphone.getText().trim();
		if(name.isEmpty() && phone.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please enter name or phone number for searching");
		}
		else
		{
			Connection con=DbConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			try
			{
				String sql="select * from contact_details where name=? or phone1=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, name);//agar name nahi dala user ne to null aaayega
				ps.setString(2,phone);
				rs=ps.executeQuery();
				if(rs.next())//checks 1)if data is present in table according to query and 2)points the cursor at that particular row
				{
					//fetching data from db
					String cname=rs.getString("name");//resultset k sath col ka naam hota humesha
					String ph1=rs.getString("phone1");
					String email=rs.getString("email");
					String address=rs.getString("address");
					
					
					txtaddress.setText(address);
					txtemail.setText(email);
					txtname.setText(cname);
					txtphone.setText(ph1);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No such contact!");
				}
			}
			catch(SQLException se)
			{
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
					if(rs!=null)
					{
						rs.close();
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
					}
				}
			
			
		}
		
	}
}
