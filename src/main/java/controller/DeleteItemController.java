package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DababaseConnection.DAO;

/**
 * Servlet implementation class DeleteItemController
 */
@WebServlet("/DeleteItemController")
public class DeleteItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int sr_no = Integer.parseInt(request.getParameter("rowid1"));
		

		DAO d1=new DAO();
		ResultSet rs=d1.editItem(sr_no);
	
		try {
			rs.next();
			int k=rs.getInt("product_quantity");
			String prname=rs.getString("product_name");
			d1.updateQty(k,prname);
			
			
			d1.deleteRowinTable(sr_no);
			
			response.sendRedirect(request.getContextPath()+"/billingpage.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
