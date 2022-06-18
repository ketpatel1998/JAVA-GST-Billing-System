package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import RoleBeans.SetdatesBeans;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
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
		HttpSession session = request.getSession();
		String toDate = request.getParameter("toDate");
		String fromDate = request.getParameter("fromDate");
		String destination = request.getParameter("pagedisc");
		toDate += " 00:00:00";
		fromDate += " 23:59:59";
		SetdatesBeans sdb = new SetdatesBeans();
		sdb.setTodate(toDate);
		sdb.setFromdate(fromDate);
		session.setAttribute("dates", sdb);
		if(destination.equals("purchase"))
		{
			response.sendRedirect(request.getContextPath()+"/PurchaseTable2.jsp");
			session.setAttribute("sales", "off");
			session.setAttribute("purchase", "off");
		}
		else if(destination.equals("sales"))
		{
			response.sendRedirect(request.getContextPath()+"/SalesTable2.jsp");
			session.setAttribute("sales", "off");
			session.setAttribute("purchase", "off");
		}
		
//		System.out.println("Test1");
		System.out.println(toDate);
		System.out.println(fromDate);
	//	System.out.println("Test1");
//		cb.setC_name(cus_name);
//		cb.setC_email(cus_email);
//		cb.setC_phone(cus_phone);
	}

}
