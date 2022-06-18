package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DababaseConnection.DAO;
import DababaseConnection.DBconnection;
import RoleBeans.BillModelBean;

/**
 * Servlet implementation class EditController2
 */
@WebServlet("/EditController2")
public class EditController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController2() {
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
		DAO dao = new DAO();
		
		int id = Integer.parseInt(request.getParameter("srno1"));
		String prname = request.getParameter("nmproductname");
		int prqty = Integer.parseInt(request.getParameter("nmprductquantity"));
		double discount = Double.parseDouble(request.getParameter("nmdiscount"));
		ResultSet rs = dao.getInfoOfProduct(prname,prqty);
		DecimalFormat df2 = new DecimalFormat(".##");
		
		
		double price=0;
		try {
				rs.next();
			
					String p_name = prname;
					String p_id = rs.getString("product_id");
					String de_name = rs.getString("dept_name");
					String de_id = rs.getString("dept_id");
					double p_cgst = rs.getDouble("cgst");
					double p_sgst = rs.getDouble("sgst");
					double p_mrp = rs.getDouble("price");
					int p_qty = prqty;
					//double p_amo = rs.getDouble("grandtotal");
					double p_amo = p_mrp;
					double profit = rs.getDouble("profit")*p_qty;
					double base_price = rs.getDouble("base_price");
					double base_gst = rs.getDouble("base_gst")*p_qty;
					double profit_gst = rs.getDouble("profit_gst")*p_qty;
					double g_gst = rs.getDouble("g_gst")*p_qty;
					double pr_baseprice = rs.getDouble("price");
					double pr_baseprice2 = ((pr_baseprice/(100+p_cgst+p_sgst))*100);
					
					double p_discount = discount;
					double p_amount = (p_amo * p_qty) - ((p_amo * p_qty)*(p_discount))/100;

					p_cgst = Double.parseDouble(df2.format(p_cgst));
					
					p_sgst = Double.parseDouble(df2.format(p_sgst));
					p_mrp = Double.parseDouble(df2.format(p_mrp));
					p_amo = Double.parseDouble(df2.format(p_amo));
					profit = Double.parseDouble(df2.format(profit));
					base_price = Double.parseDouble(df2.format(base_price));
					base_gst = Double.parseDouble(df2.format(base_gst));
					
					profit_gst = Double.parseDouble(df2.format(profit_gst));
					g_gst = Double.parseDouble(df2.format(g_gst));
					pr_baseprice = Double.parseDouble(df2.format(pr_baseprice));
					pr_baseprice2 = Double.parseDouble(df2.format(pr_baseprice2));
					p_discount = Double.parseDouble(df2.format(p_discount));
					p_amount = Double.parseDouble(df2.format(p_amount));
					HttpSession session=request.getSession();
					String cus_name=(String)session.getAttribute("c_name");
					String cus_email =(String)session.getAttribute("c_email");
					String cus_phone=(String)session.getAttribute("c_phone");
					
					price = rs.getDouble("price");
					
			BillModelBean bmb = new BillModelBean();
			
			bmb.setP_name(p_name);
			bmb.setDe_id(de_id);
			bmb.setP_id(p_id);
			bmb.setDe_name(de_name);
			bmb.setP_cgst(p_cgst);
			bmb.setP_sgst(p_sgst);
			bmb.setP_amount(p_amount);
			bmb.setP_discount(p_discount);
			bmb.setP_qty(p_qty);
			bmb.setP_mrp(p_mrp);
			bmb.setBase_price(base_price);
			bmb.setBase_gst(base_gst);
			bmb.setG_gst(g_gst);
			bmb.setG_gst(g_gst);
			bmb.setProfit_gst(profit_gst);
			bmb.setProfit(profit);
			bmb.setPr_baseprice(pr_baseprice2);
			bmb.setC_name(cus_name);
			bmb.setC_phone(cus_phone);
			bmb.setC_email(cus_email);
			
			DAO d1=new DAO();
			d1.insertSale(bmb);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double grandtotal = ((price*prqty)-((price*prqty)*discount/100));
		String sql="Update tbl_bill_model set product_name = '"+prname+"',product_quantity = '"+prqty+"',product_discount='"+discount+"', grandtotal = '"+grandtotal+"' where sr_no = '"+id+"'";

		System.out.println("Query:"+sql);
		Connection con = DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int i=ps.executeUpdate();
			
			if(i>0)
			{
				System.out.println("Update Successfully");
			}
			DAO d=new DAO();
			d.updateQty2(prqty, prname);
			HttpSession session=request.getSession();
			session.removeAttribute("edit123");
			response.sendRedirect(request.getContextPath()+"/BillingAddPage.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
