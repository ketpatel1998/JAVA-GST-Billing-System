package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import RoleBeans.CustomerBean;

/**
 * Servlet implementation class NewCutomerAdd
 */
@WebServlet("/NewCutomerAdd")
public class NewCutomerAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCutomerAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		CustomerBean cb = new CustomerBean();
		String cus_name = request.getParameter("nmcustomername");
		String cus_phone = request.getParameter("nmcustomerphone");
		String cus_email = request.getParameter("nmcustomeremail");
		cb.setC_name(cus_name);
		cb.setC_email(cus_email);
		cb.setC_phone(cus_phone);
		session.setAttribute("customerset", cb);
		session.setAttribute("custo_name", "on");
		response.sendRedirect(request.getContextPath()+"/billingpage.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
