package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DababaseConnection.DAO;
import RoleBeans.AddStockBean;

/**
 * Servlet implementation class AddExistingController
 */
@WebServlet("/AddExistingController")
public class AddExistingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExistingController() {
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
		PrintWriter writer = response.getWriter();
		String pr_name = request.getParameter("productname");
		int qty = Integer.parseInt(request.getParameter("Qty"));

		AddStockBean asc = new AddStockBean();
		asc.setPro_name(pr_name);
		asc.setPro_qty(qty);
		
		
		DAO db = new DAO();
		int kj = db. addExistingProduct(asc);
		if(kj > 0)
		{
			response.sendRedirect(request.getContextPath()+"/AddExisting.jsp");
		}
		else
		{
			writer.println("Fail");
		}

	}

}
