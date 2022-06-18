package controller;

import java.io.IOException;
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
import RoleBeans.BillModelBean;
import RoleBeans.BillRoleBean;

/**
 * Servlet implementation class controllerbilling
 */
@WebServlet("/controllerbilling.html")
public class controllerbilling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controllerbilling() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String btn1 = request.getParameter("nmproductaddbutton");
		HttpSession session = request.getSession();
		ResultSet rs = null;
		String bill_no="";
		
		
		String cus_name = request.getParameter("nmcustomername");
		String cus_phone = request.getParameter("nmcustomerphone");
		String cus_email = request.getParameter("nmcustomeremail");
		
		if(session.getAttribute("custo_name")==null)
		{
			BillModelBean bmb1 = new BillModelBean();
			bmb1.setC_name(cus_name);
			bmb1.setC_phone(cus_phone);
			bmb1.setC_email(cus_email);
			DAO d1=new DAO();
			String billno=(String)session.getAttribute("billno");
			
			DAO d2=new DAO();
			int zz=d2.getBillId();
			bill_no="xyz"+zz;
			session.setAttribute("billno", bill_no);
			d1.customerInsert(bmb1,bill_no);
			
			session.setAttribute("custo_name", "on");
			session.setAttribute("c_name", cus_name);
			session.setAttribute("c_email", cus_email);
			session.setAttribute("c_phone", cus_phone);
			
		}
		
		String pro_selected = request.getParameter("nmproductname");
		int pro_quantity = Integer.parseInt(request.getParameter("nmprductquantity"));
		double pro_discount = Double.parseDouble(request.getParameter("nmdiscount"));
		
		
		DecimalFormat df2 = new DecimalFormat(".##");
		
		BillRoleBean brb = new BillRoleBean();
		brb.setCus_name(cus_name);
		brb.setCus_phone(cus_phone);
		brb.setCus_email(cus_email);
		brb.setPro_selected(pro_selected);
		brb.setPro_quantity(pro_quantity);
		brb.setPro_discount(pro_discount);
		
		
		
		DAO d = new DAO();
		session.setAttribute("customerset", brb);
		rs = d.getInfoOfProduct(brb);
		if(rs!=null)
		{
			try {
				while(rs.next())
				{
						
						String p_name = rs.getString("product_name");
						String p_id = rs.getString("product_id");
						String de_name = rs.getString("dept_name");
						String de_id = rs.getString("dept_id");
						double p_cgst = rs.getDouble("cgst");
						double p_sgst = rs.getDouble("sgst");
						double p_mrp = rs.getDouble("price");
						int p_qty = brb.getPro_quantity();
						//double p_amo = rs.getDouble("grandtotal");
						double p_amo = p_mrp;
						double profit = rs.getDouble("profit")*p_qty;
						double base_price = rs.getDouble("base_price");
						double base_gst = rs.getDouble("base_gst")*p_qty;
						double profit_gst = rs.getDouble("profit_gst")*p_qty;
						double g_gst = rs.getDouble("g_gst")*p_qty;
						double pr_baseprice = rs.getDouble("price");
						double pr_baseprice2 = ((pr_baseprice/(100+p_cgst+p_sgst))*100);
						
						double p_discount = brb.getPro_discount();
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
						cus_name=(String)session.getAttribute("c_name");
						cus_email=(String)session.getAttribute("c_email");
						cus_phone=(String)session.getAttribute("c_phone");
						bmb.setC_name(cus_name);
						bmb.setC_phone(cus_phone);
						bmb.setC_email(cus_email);
						bmb.setBill_no((String)session.getAttribute("billno"));
						int k = d.setInfoToModel(bmb);
						
						if(k>0)
						{
							
							response.sendRedirect(request.getContextPath()+"/billingpage.html");
						}
						else
						{
							
							System.out.println("Fail");
						}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		else
		{
			System.out.print("solve this");
			session.setAttribute("productstatus", "empty");
			session.setAttribute("productnamenotavail", brb.getPro_selected());
			response.sendRedirect(request.getContextPath()+"/billingpage.html");
		}
			}

}
