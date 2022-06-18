package DababaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static Connection con = null;
	public static Connection getDBConnection()
	{
		String url = "jdbc:mysql://localhost:3306/testfinal";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "");
			System.out.println("Success");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.print("Driver Loading Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Connection Establishment Error");
		}

		return con;
	}
	public static void main(String[] args)
	{
		Connection con = getDBConnection();
		
		
	}
	
	public static void connectionClose()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

