package cms.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import cms.dbtask.DbConnection;
import net.proteanit.sql.DbUtils;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.*;
import javax.swing.JButton;
public class AllContacts extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private JScrollPane  scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllContacts frame = new AllContacts();
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
	
	//user defined method
	public void fillRecords()
	{
		PreparedStatement ps=null;//make it outside try block jisse finally me close hojaye
		ResultSet rs=null;//make it outside try block 
		try
		{
			String sql="select * from contact_details";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();//stores address of the entire data fetched
			
			//DbUtils is a bulit in class provided by the rs2xml.jar 
			//TableModel is a interface belongs to swing.....spearate package hota hai islie dubara import bhi kiya
			//resultSetToTableModel is a static method used with class name DbUtils
			TableModel t=DbUtils.resultSetToTableModel(rs);//model is being created
			table.setModel(t);//setting the model
		
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try {
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	
	public AllContacts() {
		//connection contsructor me bana re kyunki jaise he click kre humlog ko frame dekhna hai ...button k click pe dekhna hota tab action performed wale method me daalte connection
		con=DbConnection.openConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 106, 379, 344);//left top height width...already coded
		contentPane.add(scrollPane);
		
		JButton btncontacts = new JButton("Show contacts");
		
		btncontacts.setFont(new Font("Calibri", Font.BOLD, 13));
		btncontacts.addActionListener(this);
		btncontacts.setBounds(283, 480, 125, 25);
		contentPane.add(btncontacts);
		
		table = new JTable();
		
		//to fetch table header:
		JTableHeader header=table.getTableHeader();//method belongs to JTableHeader
		//Jtableheader is a built in class so import it
		
		header.setForeground(Color.white);
		// OR setForeground(new Color(200,255,200)).....this is an anonymous object 
		//OR Color c=new Color(200,255,255)
		 //      setForeground(c)
		
		header.setFont(new Font("Lucida Console",Font.BOLD,12));
		header.setBackground(Color.BLUE);
		
		
		
		//fillRecords();//calling before adding in scrollpane
		 //scrollPane.setViewportView(table);//table is added in scrollPane......yeh design se kiya hai code nhi kiya
		//agar button k click pe show krni h table to yeh do line actionPerformed me likhna
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//to show records on button click
		fillRecords();//calling before adding in scrollpane
		scrollPane.setViewportView(table);
		
	}
}
