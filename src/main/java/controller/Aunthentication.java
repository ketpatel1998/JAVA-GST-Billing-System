package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Servlet implementation class Aunthentication
 */
@WebServlet("/Aunthentication")
public class Aunthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aunthentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//System.out.println("In Aunthentication");
		PrintWriter out=response.getWriter();
		String fn=request.getParameter("fname");
		String ln=request.getParameter("lname");
		String storename=request.getParameter("storename"); 
		String gstno=request.getParameter("gsttin");
		String dlno=request.getParameter("dlno");
		String un=request.getParameter("username");
		String pass=request.getParameter("password");

		String ac=request.getParameter("areacode");
		String email=request.getParameter("email");
		String phoneno=request.getParameter("phoneno");

		{
			//	private static Connection con = null;	
				String url="jdbc:mysql://localhost:3307/testfinal";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				Connection	con=DriverManager.getConnection(url, "root", "");
					System.out.println("sucess");
					
				
					//PreparedStatement ps = con.prepareStatement("insert into adddetail(storename,gstno,dnno,un,pass,repass,email,phoneno) values('"+storename+"','"+gstno+"','"+dnno+"','"+un+"','"+pass+"','"+repass+"','"+email+"','"+phoneno+"')");
					//PreparedStatement ps=con.prepareStatement("insert into adddetail values(?,?,?,?,?,?,?,?)");
					PreparedStatement ps=con.prepareStatement("insert into login_data (firstname,lastname,storename,gstno,dlno,username,password,email,areacode,phoneno) values('"+fn+"','"+ln+"','"+storename+"','"+gstno+"','"+dlno+"','"+un+"','"+pass+"','"+email+"','"+ac+"','"+phoneno+"')");
					//ps.setString(1,storename);
					System.out.println("sucess2");
					int i=ps.executeUpdate();
				
					System.out.println("i"+i);
					if(i>0)  
					{
						response.sendRedirect(request.getContextPath()+"/login.jsp");	
					}
					ps.close();
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

	}
}
