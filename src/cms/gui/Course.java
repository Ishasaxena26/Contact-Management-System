package cms.gui;

import java.awt.EventQueue;

import java.sql.*;

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

public class Course extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtfees;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
		setTitle("Course Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 372);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 19));
		lblNewLabel.setBounds(36, 70, 179, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course Fees");
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 19));
		lblNewLabel_1.setBounds(36, 117, 179, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Course Duration");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 19));
		lblNewLabel_2.setBounds(36, 167, 179, 20);
		contentPane.add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtname.setBounds(253, 71, 199, 27);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtfees = new JTextField();
		txtfees.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtfees.setBounds(253, 117, 199, 27);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		txtduration = new JTextField();
		txtduration.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtduration.setBounds(253, 168, 199, 27);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		
		btnsubmit.addActionListener(this);//registering listener with the source
		
		btnsubmit.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		btnsubmit.setBounds(344, 239, 108, 27);
		contentPane.add(btnsubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String cname=txtname.getText().trim();
		String fees=txtfees.getText().trim();
		String cduration=txtduration.getText().trim();
		
		if(cname.isEmpty() || fees.length()==0 || cduration.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Enter the details");
		}
		else
		{
			int cfees=Integer.parseInt(fees);
			Connection con=DbConnection.openConnection();
			PreparedStatement ps=null;
			
			try
			{
				String inser_query="insert into course_details( Course_name, Course_fees, Course_duration)values(?,?,?)";
				ps=con.prepareStatement(inser_query);
				ps.setString(1, cname);
				ps.setInt(2, cfees);
				ps.setString(3, cduration);
			
				int status=ps.executeUpdate();
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, "Data inserted successfully");
					txtname.setText("");
					txtfees.setText("");
					txtduration.setText("");
					
					
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
						ps.close();
					if(con!=null)
						con.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
	}
}
