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

import com.sun.mail.iap.Response;

import DababaseConnection.DAO;

/**
 * Servlet implementation class EditItemController
 */
@WebServlet("/EditItemController")
public class EditItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sr_no = Integer.parseInt(request.getParameter("rowid1"));
		
		DAO d1=new DAO();
		ResultSet rs=d1.editItem(sr_no);
	
		try {
			rs.next();
			int k=rs.getInt("product_quantity");
			String prname=rs.getString("product_name");
			d1.updateQty(k,prname);
			d1.updateSale();
			ResultSet rs1=d1.editItem(sr_no);
			HttpSession session=request.getSession();
			session.setAttribute("edit123", rs1);
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
