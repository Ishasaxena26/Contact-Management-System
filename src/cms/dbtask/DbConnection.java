package cms.dbtask;

//objective -loads two driver class and establish connection

import java.sql.*;//sql package contains all the delegates of java
public class DbConnection {

	//method ko static banao as it is independent of class-koi instance method to hai nahi islie
	private static Connection con;
	
	public static Connection openConnection() { 
		//returning connection type variable 
		try {
			//1.LOAD THE DRIVER CLASS
			Class.forName("com.mysql.cj.jdbc.Driver");//factory method-forName
			//Class belongs to lang package
			//forname is used to create object of the class 
			
			//2.CREATE CONNECTION WITH PARTICULAR DB
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db","root","root");
			//returns connection stored in con-adress of contact_db1 
			//jdbc:mysql= Sub protocol
			
		//checked exception deta to sqlException catch kiya
			
		}
		catch(ClassNotFoundException|SQLException cse) {
			System.out.println(cse);
		}
		return con;
	}
	/*//checking if connection is working or not....TESTER
	public static void main(String[] args) {
		
		Connection c=openConnection();//static method hai islie class name sath  call kiya
		System.out.println(c);//returns hashcode
	}*/
}
