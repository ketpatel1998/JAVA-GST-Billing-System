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
 * Servlet implementation class AddStockContoller
 */
@WebServlet("/AddStockContoller")
public class AddStockContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStockContoller() {
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
		PrintWriter writer = response.getWriter();
		String pr_name = request.getParameter("productname");
		String dept_name = request.getParameter("deptname");
		double base_price = Double.parseDouble(request.getParameter("baseprice"));
		double pr_mrp = Double.parseDouble(request.getParameter("MRP"));
		int pr_qty = Integer.parseInt(request.getParameter("Qty"));
		double pr_cgst = Double.parseDouble(request.getParameter("CGST"));
		double pr_sgst = Double.parseDouble(request.getParameter("SGST"));
		AddStockBean asc = new AddStockBean();
		asc.setPro_name(pr_name);
		asc.setDept_name(dept_name);
		asc.setPro_qty(pr_qty);
		asc.setPro_cgst(pr_cgst);
		asc.setBase_price(base_price);
		asc.setPro_price(pr_mrp);
		asc.setPro_sgst(pr_sgst);
		
		DAO db = new DAO();
		int kj = db.addStockPurchase(asc);
		if(kj > 0)
		{
			response.sendRedirect(request.getContextPath()+"/AddNewItem.jsp");
		}
		else
		{
			writer.println("Fail");
		}
		
	}

}
