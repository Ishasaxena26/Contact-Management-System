package cms.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import java.sql.*;//for jdbc connection
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class UpdateContact extends JFrame implements ItemListener, ActionListener {//button k liye actionlistener.....aur combobox k liye itemlistner

	private JPanel contentPane;
	private JTextField txtphone1;
	private JTextField txtphone2;
	private JTextField txtemail;
	//making global variable
	private JComboBox<String>cmbname;//combo box can contain string values (collection framework)
	private JTextArea txtaddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateContact frame = new UpdateContact();
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
	public void fillCombo()//instance method
	{
		PreparedStatement ps=null;//this prepares the query
		//result set is used only when select query is to be used to retrieve the data 
		ResultSet rs=null; //hold reference(address) of the resultant data set returned by select query(database se data lera jisme saara data hai)
		//result set ek ek data nikalega database se 
		//iske methods check krenge ki agar data present hai ya nhi pehle
		try {
			String select_query="select name from contact_details";//only read name column (for every row)
			ps=con.prepareStatement(select_query);
			//no setting as database se direct aara data
			rs=ps.executeQuery();//used to fire/execute select query ONLY.Return type is ResultSet...adress aagya
			//ab dekhega ki data hai bhi present ya nhi aur loop bhi iterate karayenge sath mein
			while(rs.next()==true)
			{
				//if data present then loop me enter ho jayenge
				String cname=rs.getString("name");//fetches the value from specified col
				//name is column name
				//cname mein pehla naam aayega ..aese he har naam aata jayega 
			    
				//add items in combo box
				cmbname.addItem(cname);//this method-addItem belongs to combo box
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
				 //dont close the connection here abhi 
			 }
			 catch(SQLException se)//checked exception might be raised so catching it
			 {
				 se.printStackTrace();
			 }

		}
	}
	
	private Connection con;
	public UpdateContact() {//yeh ban k aaya tha 
		
		con=DbConnection.openConnection();//3 baar database se connection krna padega(demand hai kyunku 3 baar) islie ek he baar me constructor me daal dere isko 
		//1st time when we populate the combo
		//2nd time when the fields are automatically filled....select query
		//3rd time when we press update
		
		setTitle("Update Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelph = new JLabel("Phone 1");
		labelph.setFont(new Font("Carlito", Font.BOLD, 20));
		labelph.setBounds(58, 75, 101, 32);
		contentPane.add(labelph);
		
		cmbname = new JComboBox();
		
		cmbname.addItemListener(this);//registering listener
		
		cmbname.setModel(new DefaultComboBoxModel(new String[] {"Select Name"}));
		cmbname.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		cmbname.setBounds(204, 22, 202, 26);
		
		fillCombo();//calling method to show items in combo
		
		contentPane.add(cmbname);
		
		txtphone1 = new JTextField();
		txtphone1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtphone1.setBounds(204, 75, 202, 26);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Phone 2");
		lblNewLabel.setFont(new Font("Carlito", Font.BOLD, 20));
		lblNewLabel.setBounds(58, 120, 95, 37);
		contentPane.add(lblNewLabel);
		
		txtphone2 = new JTextField();
		txtphone2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtphone2.setBounds(204, 125, 202, 26);
		contentPane.add(txtphone2);
		txtphone2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Carlito", Font.BOLD, 20));
		lblNewLabel_1.setBounds(58, 174, 101, 32);
		contentPane.add(lblNewLabel_1);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtemail.setBounds(204, 177, 202, 26);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Carlito", Font.BOLD, 20));
		lblNewLabel_2.setBounds(58, 228, 95, 26);
		contentPane.add(lblNewLabel_2);
		
		txtaddress = new JTextArea();
		txtaddress.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		txtaddress.setBounds(204, 228, 202, 68);
		contentPane.add(txtaddress);
		
		JButton btnupdate = new JButton("Update");
		
		btnupdate.addActionListener(this);
		
		btnupdate.setFont(new Font("Carlito", Font.BOLD, 20));
		btnupdate.setBounds(119, 337, 126, 32);
		contentPane.add(btnupdate);
		
		JLabel txtname = new JLabel("Name");
		txtname.setFont(new Font("Carlito", Font.BOLD, 20));
		txtname.setBounds(60, 26, 69, 26);
		contentPane.add(txtname);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//functional interface-only 1 method in actionListener
		
		//data nikaal re sab
		String n=(String)cmbname.getSelectedItem();
		String em=txtemail.getText();
		String p1=txtphone1.getText();
		String a=txtaddress.getText();
		String p2=txtphone2.getText();
		
		if(em.isEmpty() || p1.isEmpty() || a.isEmpty() || n.equalsIgnoreCase("Select Name"))
		{
			JOptionPane.showMessageDialog(this, "Please provide meaningful data for updation");
		}
		else
		{
			PreparedStatement ps=null;
			try
			{
				String update_query="update contact_details set email=?,phone1=?,phone2=?,address=? where name=?";
				ps=con.prepareStatement(update_query);
				ps.setString(1, em);
				ps.setString(2, p1);
				ps.setString(3, p2);
				ps.setString(4, a);
				ps.setString(5, n);
				int status=ps.executeUpdate();
				if(status>0) {
					JOptionPane.showMessageDialog(this, "Contact for "+n+" is updated successfully");
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
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		//System.out.println("Item selected");//will print 2 times as combo ki 2 state hoti hai -current state and previous state
		int state=e.getStateChange();//current state value is equal to 1
		//System.out.println(state);
		if(state==1)
		{
			//fetch value from combobox:
			String cname=(String)cmbname.getSelectedItem();//to fetch the data from JComboBox.....returns the value selected by combobox
			//return type is super class Object...you have to typecast it
			System.out.println("name is "+cname);
			
			PreparedStatement ps=null;
			ResultSet rs=null;
			try
			{
				String sql="select * from contact_details where name=?";//here name is col ka naam
				ps=con.prepareStatement(sql);//query is compiled by DBMS and refrence of compiled query will be stored in ps
				ps.setString(1, cname);//1 denotes the placeholder 
				rs=ps.executeQuery();
				
				rs.next();//will point the cursor
				//FUNCTION OF next()-1.used to check if data is present
				//2.if there is data it will put the cursor on that row
				
				String em=rs.getString("email");//getString is used to fetch data from a column...read value from a col
				String ph1=rs.getString("phone1");
				String ph2=rs.getString("phone2");
				String add=rs.getString("address");
				
				txtemail.setText(em);
				txtphone1.setText(ph1);
				txtphone2.setText(ph2);
				txtaddress.setText(add);
				
				
				
				
			}
			catch(SQLException se)
			{
				System.out.println(se);
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
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		
		
	}
	
}
