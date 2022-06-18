package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	
	{
		PrintWriter out=response.getWriter();
		String un=request.getParameter("username");
		String ps=request.getParameter("password");
		
		{
			//	private static Connection con = null;	
				String url="jdbc:mysql://localhost:3307/testfinal";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				Connection	con=DriverManager.getConnection(url, "root", "");
				Statement st =con.createStatement();
				System.out.println("sucess");
					
					Resultset rs2=(Resultset) st.executeQuery("Select * from login_data where username='"+un+"' & password='"+ps+"'");
					while(((ResultSet) rs2).next())
					{
						System.out.println("Logged");
						//response.sendRedirect("/HackathonGstBillingSystem/WebContent/BillingAddPage.jsp");
						response.sendRedirect(request.getContextPath()+"/BillingAddMainPage.jsp");
					}
					
					
					//PreparedStatement ps2 = con.prepareStatement("Select  username && password from hello" );
					//rs2 = ps2.executeQuery();
					
				
					//PreparedStatement ps = con.prepareStatement("insert into adddetail(storename,gstno,dnno,un,pass,repass,email,phoneno) values('"+storename+"','"+gstno+"','"+dnno+"','"+un+"','"+pass+"','"+repass+"','"+email+"','"+phoneno+"')");
					//PreparedStatement ps=con.prepareStatement("insert into adddetail values(?,?,?,?,?,?,?,?)");
					//PreparedStatement ps=con.prepareStatement("insert into hello (firstname,lastname,storename,gstno,dlno,username,password,email,areacode,phoneno) values('"+fn+"','"+ln+"','"+storename+"','"+gstno+"','"+dlno+"','"+un+"','"+pass+"','"+email+"','"+ac+"','"+phoneno+"')");
					//ps.setString(1,storename);
					System.out.println("sucess2");
				//	ps2.close();
					con.close();
					
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.print("Driver Loading Error");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("Connection Establishment Error:"+e);
				}


	}
	

}}
