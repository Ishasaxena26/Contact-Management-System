package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class SplashScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	//making a thread inside method with the help of class
	//inner class-classs inside class
	//class has no name-anonymous thread class

	public void loadFrame()
	{
		//we wish to create a thread using anonymous(no name) local(as it is inside a method) inner class
		Thread t=new Thread( 
				
				new Runnable()//anonymous class object .....Runnable is an interface to uska obj nhi ban skta par jis  anonymousclass me yeh impplement hora us class ka object banra(new Runnable)
				{

					@Override
					public void run() {
						try {
							Thread.sleep(4000);
							Login login=new Login();
							login.setVisible(true);
							SplashScreen.this.dispose();//outer class object
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					//anonymous class body
				}//anonymous class body closed
				
				
				
				
				);
		//object of thread class
		t.start();
		
	
		
		
	}
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CMS WELCOMES YOU");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(135, 206, 250));
		lblNewLabel.setFont(new Font("Microsoft Himalaya", Font.BOLD, 28));
		lblNewLabel.setBounds(100, 74, 326, 66);
		contentPane.add(lblNewLabel);
		
		loadFrame();//calling 
	}
}
