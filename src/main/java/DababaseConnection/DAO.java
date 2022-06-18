package DababaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;

import RoleBeans.AddStockBean;
import RoleBeans.BillModelBean;
import RoleBeans.BillRoleBean;
import RoleBeans.CustomerBean;


public class DAO {
	DecimalFormat df1 = new DecimalFormat(".##");
	public ResultSet getInfoOfProduct(BillRoleBean brb)
	{
		ResultSet rs = null;
		ResultSet rs2=null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		String prid = brb.getPro_selected();
		int pqty = brb.getPro_quantity();
		try {
			PreparedStatement ps2 = con.prepareStatement("Select * from stock_available_tbl where product_name = '"+prid+"'");
			rs2 = ps2.executeQuery();
			rs2.next();
			int kl = rs2.getInt("qty_remaining");
			System.out.print(kl);
			if((kl-pqty)>=0)
			{
				try {
					
					PreparedStatement ps = con.prepareStatement("Select * from product_details where product_name = '"+prid+"'");
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rs;
		
	}
	public ResultSet getInfoOfProduct(String brb,int qty)
	{
		ResultSet rs = null;
		ResultSet rs2=null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		String prid = brb;
		int pqty = qty;
		try {
			PreparedStatement ps2 = con.prepareStatement("Select * from stock_available_tbl where product_name = '"+prid+"'");
			rs2 = ps2.executeQuery();
			rs2.next();
			int kl = rs2.getInt("qty_remaining");
			System.out.print(kl);
			if((kl-pqty)>=0)
			{
				try {
					
					PreparedStatement ps = con.prepareStatement("Select * from product_details where product_name = '"+prid+"'");
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rs;
		
	}
	public int addCsvFileToTable(String filename,String filepath)
	{
		int kl = 0;
		Connection con = DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into tbl_csv_upload(file_name,file) values(?,?)");
			File filehandler = new File(filepath);
			
			try {
				FileInputStream inputStream = new FileInputStream(filehandler);
				
				ps.setBlob(1, inputStream);
				ps.setString(2, filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			kl = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kl;
	}
	
	public ResultSet searchCustomer(String phone)
	{
		ResultSet rs=null;
		Connection con = DBconnection.getDBConnection();
		try {
			
			PreparedStatement ps = con.prepareStatement("select * from customer_tbl where c_phone='"+phone+"'");
			rs = ps.executeQuery();
			if(rs!=null)
			{
				System.out.println("ResultSet Retrieve");
				return rs;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ResultSet getAllEntryOfCsvTable()
	{
		ResultSet rs = null;
		Connection con = DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from tbl_csv_upload");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int addExistingProduct(AddStockBean brb)
	{
		ResultSet rs = null;
		int k=0;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		String prname = brb.getPro_name();
		int qty=brb.getPro_qty();
		
		try {
			
			PreparedStatement ps = con.prepareStatement("Select * from stock_available_tbl where product_name = '"+prname+"'");
			rs = ps.executeQuery();
			rs.next();
			int qty1 =rs.getInt("qty_stock");
			int qtyTotal=qty1+qty;
			int qtyRem=rs.getInt("qty_remaining");
			int qtremain=qtyRem+qty;
			PreparedStatement ps6 = con.prepareStatement("update stock_available_tbl set qty_stock = '"+qtyTotal+"',qty_remaining='"+qtremain+"' where product_name = '"+prname+"'");
			
			k = ps6.executeUpdate();
			
			PreparedStatement ps3 = con.prepareStatement("Select * from product_details where product_name = '"+prname+"'");
			rs = ps3.executeQuery();
			rs.next();
			String pid=rs.getString("product_id");
			String pname=rs.getString("product_name");
			String did=rs.getString("dept_id");
			String dname=rs.getString("dept_name");
			double bprice=rs.getDouble("base_price");
			double profit=rs.getDouble("profit");
			double bgst=rs.getDouble("base_gst");
			double pgst=rs.getDouble("profit_gst");
			double ggst=rs.getDouble("g_gst");
			double cgst=rs.getDouble("cgst");
			double sgst=rs.getDouble("sgst");
			double price=rs.getDouble("price");
			double total=rs.getDouble("grandtotal");
			
			bprice=Double.parseDouble(df1.format(bprice));
			profit=Double.parseDouble(df1.format(profit))*qty;
			bgst=Double.parseDouble(df1.format(bgst))*qty;
			pgst=Double.parseDouble(df1.format(pgst))*qty;
			ggst=Double.parseDouble(df1.format(ggst))*qty;
			cgst=Double.parseDouble(df1.format(cgst));
			sgst=Double.parseDouble(df1.format(sgst));
			price=Double.parseDouble(df1.format(price));
			total=Double.parseDouble(df1.format(total))*qty;
			
			PreparedStatement ps7 = con.prepareStatement("insert into addstock_tbl(dept_id,"
					+ "dept_name,product_id,product_name,base_price,"
					+ "profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) "
					+ "values('"+did+"','"+dname+"','"+pid+"','"+pname+"','"+bprice+"','"+profit+"','"+bgst+"','"+pgst+"','"+ggst+"','"+qty+"','"+price+"','"+cgst+"','"+sgst+"','"+total+"')");
			k = ps7.executeUpdate();
			System.out.println(k);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
		
	}
	
	public int addCustomer(CustomerBean brb)
	{
		int k= 0;
		Connection con = (Connection) DBconnection.getDBConnection();
		String c_name = brb.getC_name();
		String c_email = brb.getC_email();
		String c_phone = brb.getC_phone();
		try {
			PreparedStatement ps = con.prepareStatement("insert into customer_tbl(custo_name,custo_email,custo_phone) values('"+c_name+"','"+c_email+"','"+c_phone+"')");
			k = ps.executeUpdate();
			
			
			PreparedStatement ps1 = con.prepareStatement("insert into bill_tbl(custo_name,custo_email,custo_phone) values('"+c_name+"','"+c_email+"','"+c_phone+"')");
			k = ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public int getBillId()	{
		Connection con = (Connection) DBconnection.getDBConnection();
		int i=0;
		try {
			PreparedStatement ps = con.prepareStatement("select * from bill_tbl");
			ResultSet rs = ps.executeQuery();
			rs.last();
			
			i=rs.getInt("bill_id");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i+1;
	}
	public ResultSet getInfoByDateInSale(String todate, String fromdate)
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		try {
			
			PreparedStatement ps = con.prepareStatement("Select * from salesreport_tbl where sales_date BETWEEN '"+todate+"' and '"+fromdate+"'");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public ResultSet getInfoByDateInPurchase(String todate, String fromdate)
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		try {
			
			PreparedStatement ps = con.prepareStatement("Select * from addstock_tbl where adding_date BETWEEN '"+todate+"' and '"+fromdate+"'");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public int addStockPurchase(AddStockBean asb)
	{
		int k = 0;
		int hel;
		int hel2;
		
		String pro_name = asb.getPro_name();
		String dept_name = asb.getDept_name();
		Double base_price = asb.getBase_price();
		Double pro_price = asb.getPro_price();
		int pro_qty = asb.getPro_qty();
		Double pro_cgst = asb.getPro_cgst();
		Double pro_sgst = asb.getPro_sgst();
		double pro,ba_gst,pr_gst,g_gst,pro2,ba_gst2,pr_gst2,g_gst2,pro5;
		pro5 = pro_price-base_price;
		//ba_gst = (base_price*(pro_cgst+pro_sgst))/100;
		ba_gst = (((base_price/(100+pro_cgst+pro_sgst))*100)*(pro_cgst+pro_sgst))/100;
		//pr_gst = (pro5*(pro_cgst+pro_sgst))/100;
		//g_gst = ba_gst+pr_gst;
		g_gst = (((pro_price/(100+pro_cgst+pro_sgst))*100)*(pro_cgst+pro_sgst))/100;
		pr_gst = g_gst-ba_gst;
		ba_gst2 = ba_gst*pro_qty;
		pr_gst2 = pr_gst*pro_qty;
		pro = pro5 - pr_gst;
		pro2 = pro * pro_qty;
		g_gst2 = g_gst*pro_qty;
		
		base_price = Double.parseDouble(df1.format(base_price));
		pro_price = Double.parseDouble(df1.format(pro_price));
		pro_sgst = Double.parseDouble(df1.format(pro_sgst));
		pro_cgst = Double.parseDouble(df1.format(pro_cgst));
		pro5 = Double.parseDouble(df1.format(pro5));
		ba_gst = Double.parseDouble(df1.format(ba_gst));
		g_gst = Double.parseDouble(df1.format(g_gst));
		pr_gst = Double.parseDouble(df1.format(pr_gst));
		ba_gst2 = Double.parseDouble(df1.format(ba_gst2));
		pr_gst2 = Double.parseDouble(df1.format(pr_gst2));
		pro = Double.parseDouble(df1.format(pro));
		pro2 = Double.parseDouble(df1.format(pro2));
		g_gst2 = Double.parseDouble(df1.format(g_gst2));
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs5 = null;
		String temp;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		try {
			
			PreparedStatement ps10 = con.prepareStatement("select * from deptlist_tbl");
			rs5 = ps10.executeQuery();
			rs5.last();
			temp = rs5.getString("dept_id");			
			String sub = temp.substring(1);
			hel = Integer.parseInt(sub);
			
			PreparedStatement ps11 = con.prepareStatement("select * from product_details");
			rs5 = ps11.executeQuery();
			rs5.last();			
			temp = rs5.getString("product_id");			
			sub = temp.substring(1);
			hel2 = Integer.parseInt(sub);
			
			
			
			
			
			PreparedStatement ps = con.prepareStatement("select * from deptlist_tbl where dept_name = '"+dept_name+"'");
			rs = ps.executeQuery();
			
			if(rs.next()==false)
			{

				System.out.println("zero");
				hel = hel + 1;
				hel2 = hel2 + 1; 
				
				double grandtotal = pro_qty*(pro_price + ((pro_price)*(pro_cgst+pro_sgst))/100);
				PreparedStatement ps2 = con.prepareStatement("insert into addstock_tbl(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('d"+hel+"','"+dept_name+"','p"+hel2+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
				k = ps2.executeUpdate();
				System.out.println(k);
				
				PreparedStatement ps22 = con.prepareStatement("insert into tbl_purchase_model(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('d"+hel+"','"+dept_name+"','p"+hel2+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
				k = ps22.executeUpdate();
				System.out.println(k);
				
				PreparedStatement st = con.prepareStatement("insert into deptlist_tbl(dept_id,dept_name) values('d"+hel+"','"+dept_name+"')");
				k = st.executeUpdate();
				System.out.println(k);
				
				double grandtotal2 = (pro_price + ((pro_price)*(pro_cgst+pro_sgst))/100);
				PreparedStatement ps3 = con.prepareStatement("insert into product_details(product_id,product_name,dept_id,dept_name,base_price,profit,base_gst,profit_gst,g_gst,cgst,sgst,price,grandtotal) values('p"+hel2+"','"+pro_name+"','d"+hel+"','"+dept_name+"','"+base_price+"','"+pro+"','"+ba_gst+"','"+pr_gst+"','"+g_gst+"','"+pro_cgst+"','"+pro_sgst+"','"+pro_price+"','"+grandtotal2+"')");
				k = ps3.executeUpdate();
				System.out.println(k);
				
				PreparedStatement ps4 = con.prepareStatement("insert into stock_available_tbl(product_id,product_name,dept_id,dept_name,qty_stock,qty_sold,qty_remaining) values('p"+hel2+"','"+pro_name+"','d"+hel+"','"+dept_name+"','"+pro_qty+"','0','"+pro_qty+"')");
				k = ps4.executeUpdate();
				System.out.println(k);
				
			}
			else
			{
				String d_id = rs.getString("dept_id");
				String d_name = rs.getString("dept_name");
				PreparedStatement ps5 = con.prepareStatement("select * from product_details where product_name = '"+pro_name+"'");
				
				rs2 = ps5.executeQuery();
				if(rs2.next()==false)
				{
					System.out.println("First");
					hel2 = hel2 + 1;
					
					double grandtotal = pro_qty*(pro_price + ((pro_price)*(pro_cgst+pro_sgst))/100);
					PreparedStatement ps2 = con.prepareStatement("insert into addstock_tbl(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('"+d_id+"','"+dept_name+"','p"+hel2+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
					k = ps2.executeUpdate();
					System.out.println(k);
				
					PreparedStatement ps22 = con.prepareStatement("insert into tbl_purchase_model(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('d"+hel+"','"+dept_name+"','p"+hel2+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
					k = ps22.executeUpdate();
					System.out.println(k);
					
					double grandtotal3 = (pro_price + ((pro_price)*(pro_cgst+pro_sgst))/100);
					PreparedStatement ps7 = con.prepareStatement("insert into product_details(product_id,product_name,dept_id,dept_name,base_price,profit,base_gst,profit_gst,g_gst,cgst,sgst,price,grandtotal) values('p"+hel2+"','"+pro_name+"','"+d_id+"','"+d_name+"','"+base_price+"','"+pro+"','"+ba_gst+"','"+pr_gst+"','"+g_gst+"','"+pro_cgst+"','"+pro_sgst+"','"+pro_price+"','"+grandtotal3+"')");
					k = ps7.executeUpdate();
					System.out.println(k+"middle");
					
					PreparedStatement ps8 = con.prepareStatement("insert into stock_available_tbl(product_id,product_name,dept_id,dept_name,qty_stock,qty_sold,qty_remaining) values('p"+hel2+"','"+pro_name+"','"+d_id+"','"+d_name+"','"+pro_qty+"','0','"+pro_qty+"')");
					k = ps8.executeUpdate();
					System.out.println(k+"middle");
					
				}
				else
				{

					System.out.println("second");
					int qttotal = 0;
					int qtremain=0;
					String pr_id="",de_id="";
					double grandtotal = pro_qty*(pro_price + ((pro_price)*(pro_cgst+pro_sgst))/100);
					
					PreparedStatement ps9 = con.prepareStatement("select * from stock_available_tbl where product_name = '"+pro_name+"'");					
					rs3 = ps9.executeQuery();
					if(rs3.next())
					{
						pr_id = rs3.getString("product_id");
						de_id = rs3.getString("dept_id");
						qttotal = rs3.getInt("qty_stock");
						qtremain = rs3.getInt("qty_remaining");
					}
					qttotal = qttotal +  pro_qty;
					qtremain = qtremain + pro_qty;
					PreparedStatement ps6 = con.prepareStatement("update stock_available_tbl set qty_stock = '"+qttotal+"',qty_remaining='"+qtremain+"' where product_name = '"+pro_name+"'");
					
					k = ps6.executeUpdate();
					
					PreparedStatement ps2 = con.prepareStatement("insert into addstock_tbl(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('"+de_id+"','"+dept_name+"','"+pr_id+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
					k = ps2.executeUpdate();
					System.out.println(k);
					
					PreparedStatement ps22 = con.prepareStatement("insert into tbl_purchase_model(dept_id,dept_name,product_id,product_name,base_price,profit,base_gst,profit_gst,g_gst,qty,price,cgst,sgst,grand_total) values('d"+hel+"','"+dept_name+"','p"+hel2+"','"+pro_name+"','"+base_price+"','"+pro2+"','"+ba_gst2+"','"+pr_gst2+"','"+g_gst2+"','"+pro_qty+"','"+pro_price+"','"+pro_cgst+"','"+pro_sgst+"','"+grandtotal+"')");
					k = ps22.executeUpdate();
					System.out.println(k);
					
					System.out.println(k+"last");
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return k;
		
		
	}
	public void insertSale(BillModelBean bmb)
	{
		int k = 0;
		int qtsold2,qtremain2;
		ResultSet rs3 = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		String name=bmb.getC_name();
		String phone=bmb.getC_phone();
		String email=bmb.getC_email();
		String pr_name = bmb.getP_name();
		String pr_id = bmb.getP_id();
		String de_id = bmb.getDe_id();
		String dept_name = bmb.getDe_name();
		double pr_mrp = bmb.getP_mrp();
		int pr_qty = bmb.getP_qty();
		double pr_disc = bmb.getP_discount();
		double pr_cgst = bmb.getP_cgst();
		double pr_sgst = bmb.getP_sgst();
		double pr_amount = bmb.getP_amount();
		
		double profit = bmb.getProfit();
		double base_price = bmb.getBase_price();
		double base_gst = bmb.getBase_gst();
		double profit_gst = bmb.getProfit_gst();
		double g_gst = bmb.getG_gst();
		double pr_baseprice = bmb.getPr_baseprice();

		try {
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO salesreport_tbl (c_name,c_phone,c_email,product_name,product_id ,dept_name,dept_id, base_price, profit, base_gst, profit_gst, g_gst, qty,disc, cgst, sgst, price, grand_total) values('"+name+"','"+phone+"','"+email+"','"+pr_name+"','"+pr_id+"','"+dept_name+"','"+de_id+"','"+base_price+"','"+profit+"','"+base_gst+"','"+profit_gst+"','"+g_gst+"','"+pr_qty+"','"+pr_disc+"','"+pr_cgst+"','"+pr_sgst+"','"+pr_mrp+"','"+pr_amount+"')");
			k = ps1.executeUpdate();
			System.out.println(k);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int setInfoToModel(BillModelBean bmb)
	{
		int k = 0;
		int qtsold2,qtremain2;
		ResultSet rs3 = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		String name=bmb.getC_name();
		String phone=bmb.getC_phone();
		String email=bmb.getC_email();
		String pr_name = bmb.getP_name();
		String pr_id = bmb.getP_id();
		String de_id = bmb.getDe_id();
		String dept_name = bmb.getDe_name();
		double pr_mrp = bmb.getP_mrp();
		int pr_qty = bmb.getP_qty();
		double pr_disc = bmb.getP_discount();
		double pr_cgst = bmb.getP_cgst();
		double pr_sgst = bmb.getP_sgst();
		double pr_amount = bmb.getP_amount();
		
		double profit = bmb.getProfit();
		double base_price = bmb.getBase_price();
		double base_gst = bmb.getBase_gst();
		double profit_gst = bmb.getProfit_gst();
		double g_gst = bmb.getG_gst();
		double pr_baseprice = bmb.getPr_baseprice();
		String billno=bmb.getBill_no();
		
		System.out.println("DAO:"+name);
		System.out.println("DAO:"+phone);
		System.out.println("DAO:"+email);
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into tbl_bill_model(product_name,product_quantity,product_discount,cgst,sgst,price,base_price,grandtotal) values('"+pr_name+"','"+pr_qty+"','"+pr_disc+"','"+pr_cgst+"','"+pr_sgst+"','"+pr_mrp+"','"+pr_baseprice+"','"+pr_amount+"')");
			k = ps.executeUpdate();
			System.out.println(k);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO salesreport_tbl (bill_id,c_name,c_phone,c_email,product_name,product_id ,dept_name,dept_id, base_price, profit, base_gst, profit_gst, g_gst, qty,disc, cgst, sgst, price, grand_total) values('"+billno+"','"+name+"','"+phone+"','"+email+"','"+pr_name+"','"+pr_id+"','"+dept_name+"','"+de_id+"','"+base_price+"','"+profit+"','"+base_gst+"','"+profit_gst+"','"+g_gst+"','"+pr_qty+"','"+pr_disc+"','"+pr_cgst+"','"+pr_sgst+"','"+pr_mrp+"','"+pr_amount+"')");
			k = ps1.executeUpdate();
			System.out.println(k);
			
			
			PreparedStatement ps9 = con.prepareStatement("select * from stock_available_tbl where product_id = '"+pr_id+"'");					
			rs3 = ps9.executeQuery();
			rs3.next();
			qtsold2 = rs3.getInt("qty_sold");
			qtremain2 = rs3.getInt("qty_remaining");
			qtsold2 = qtsold2 +  pr_qty;
			qtremain2 = qtremain2 - pr_qty;
			PreparedStatement ps2 = con.prepareStatement("update stock_available_tbl set qty_sold = '"+qtsold2+"',qty_remaining = '"+qtremain2+"' where product_name = '"+pr_name+"'");
			k = ps2.executeUpdate();
			System.out.println(k);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public void customerInsert(BillModelBean bmb,String billno)
	{
		int k=0;
		Connection con = (Connection) DBconnection.getDBConnection();
		String name=bmb.getC_name();
		String phone=bmb.getC_phone();
		String email=bmb.getC_email();
		
		PreparedStatement ps3;
		try {
			ps3 = con.prepareStatement("INSERT INTO customer_tbl (c_name,c_phone,c_email) values('"+name+"','"+phone+"','"+email+"')");
			k = ps3.executeUpdate();
			if(k>0)
			{
				System.out.println("Customer inserted");
			}
			
			PreparedStatement ps4 = con.prepareStatement("insert into bill_tbl(bill_no,c_name,c_phone,c_email) values('"+billno+"','"+name+"','"+phone+"','"+email+"')");
			k = ps4.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet getDataFromModel()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		
		try {
			PreparedStatement ps = con.prepareStatement("Select * from tbl_bill_model");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getStockDetails()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		
		try {
			PreparedStatement ps = con.prepareStatement("Select * from stock_available_tbl");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getSalesReport()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from salesreport_tbl");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getSalesReportByBillNo(String billno)
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("Select * from salesreport_tbl where bill_id = '"+billno+"'");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getAddStockDetails()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		

		try {
			PreparedStatement ps = con.prepareStatement("Select * from addstock_tbl");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getBillDetails()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
	
		try {
			PreparedStatement ps = con.prepareStatement("Select * from bill_tbl");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getPurchaseDetails()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
	
		try {
			PreparedStatement ps = con.prepareStatement("Select * from tbl_purchase_model");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int clearTableModel()
	{
		int k = 0;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		
		try {
			PreparedStatement ps = con.prepareStatement("delete from tbl_bill_model");
			k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
		
	}
	public int clearPurchaseTableModel()
	{
		int k = 0;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		
		
		try {
			PreparedStatement ps = con.prepareStatement("delete from tbl_purchase_model");
			k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
		
	}
	public ResultSet getAllProducts()
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		

		try {
			PreparedStatement ps = con.prepareStatement("Select * from product_details");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
		
	}
	public ResultSet editItem(int id)
	{
		ResultSet rs = null;
		Connection con = (Connection) DBconnection.getDBConnection();
		
		String qry="Select * from tbl_bill_model where sr_no='"+id+"'";
		System.out.println("Query:"+qry);
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}
	public void deleteRowinTable(int id)
	{
		Connection con = (Connection) DBconnection.getDBConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from tbl_bill_model where sr_no = '"+id+"'");
			int i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateQty(int qty,String prnm)
	{
		Connection con = (Connection) DBconnection.getDBConnection();
		ResultSet rs=null;
		String qry="Select * from stock_available_tbl where product_name='"+prnm+"'";
		int qtysold=0,qtyrem=0;
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			rs.next();
			qtysold=rs.getInt("qty_sold");
			qtyrem=rs.getInt("qty_remaining");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry1="update stock_available_tbl set qty_sold='"+(qtysold-qty)+"',qty_remaining='"+(qtyrem+qty)+"' where product_name='"+prnm+"'";
		System.out.println("Update Stock:"+qry1);
		try {
			PreparedStatement ps = con.prepareStatement(qry1);
			int p=ps.executeUpdate();
			if(p>0)
			{
				System.out.println("Stock Update Successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void updateSale()
	{
		Connection con = (Connection) DBconnection.getDBConnection();
		ResultSet rs=null;
		int id=0;
		String qry="Select * from salesreport_tbl";
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			rs.last();
			id=rs.getInt("sr_id");
			
			PreparedStatement ps2 = con.prepareStatement("delete from salesreport_tbl where sr_id='"+id+"'");
			int k=ps2.executeUpdate();
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateQty2(int qty,String prnm)
	{
		Connection con = (Connection) DBconnection.getDBConnection();
		ResultSet rs=null;
		String qry="Select * from stock_available_tbl where product_name='"+prnm+"'";
		int qtysold=0,qtyrem=0;
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			rs.next();
			qtysold=rs.getInt("qty_sold");
			qtyrem=rs.getInt("qty_remaining");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String qry1="update stock_available_tbl set qty_sold='"+(qtysold+qty)+"',qty_remaining='"+(qtyrem-qty)+"' where product_name='"+prnm+"'";
		System.out.println("Update Stock:"+qry1);
		try {
			PreparedStatement ps = con.prepareStatement(qry1);
			int p=ps.executeUpdate();
			if(p>0)
			{
				System.out.println("Stock Update Successfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void deleteItem(int id)
	{
		
	}
}
