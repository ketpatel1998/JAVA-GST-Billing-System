package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DababaseConnection.DBconnection;

/**
 * Servlet implementation class TesterController
 */
@WebServlet("/TesterController")
@MultipartConfig(maxFileSize = 169999999)
public class TesterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
//		Connection con = DBconnection.getDBConnection();
//		try {
//			PreparedStatement ps = con.prepareStatement("insert into tbl_csv_upload(file_name) values(?)");
//			Part filename = request.getPart("fileuploaded");
//			InputStream inputStream = filename.getInputStream();
//			ps.setBlob(1, inputStream);
//			int jk = ps.executeUpdate();
//			if(jk>0)
//			{
//				System.out.println("Success");
//			}
//			else
//			{
//				System.out.println("Fail");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String id = request.getParameter("billnobill");
		HttpSession session = request.getSession();
		session.setAttribute("billnobill", id);
		response.sendRedirect(request.getContextPath()+"/ShowBillDetails.jsp");
	}

}
