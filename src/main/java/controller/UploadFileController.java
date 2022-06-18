package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DababaseConnection.DAO;
import RoleBeans.AddStockBean;

import org.apache.commons.fileupload.FileItem;

/**
 * Servlet implementation class UploadFileController
 */
@WebServlet("/UploadFileController")
public class UploadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			
			List<FileItem> multifiles = sf.parseRequest(request);
			String filename="";
			for(FileItem item : multifiles)
			{
				item.write(new File("C:/Users/Yash/New folder/Workspace2/HackathonGstBillingSystem/UploadedCsvFiles/" + item.getName()));
				item.write(new File("C:/Users/Yash/New folder/Workspace2/HackathonGstBillingSystem/WebContent/UploadedCsvFiles/" + item.getName()));
				filename = item.getName();
				
			}
		
		
			
		
			String filepath = "C:/Users/Yash/New folder/Workspace2/HackathonGstBillingSystem/UploadedCsvFiles/"+filename;
			File file = new File(filepath);
			try {
				Scanner inputstream = new Scanner(file);
				inputstream.next();
				DAO db;
				AddStockBean asb;
				while(inputstream.hasNext())
				{
					
					String data = inputstream.next();
					//System.out.println(data+" ** ");
					String[] values = data.split(",");
					String productname = values[1];
					String deptname = values[2];
					double baseprice = Double.parseDouble(values[3]);
					double sellingprice = Double.parseDouble(values[4]);
					int quatity = Integer.parseInt(values[5]);
					double cgst = Double.parseDouble(values[6]);
					double sgst = Double.parseDouble(values[7]);
					System.out.println(productname+" "+deptname+" "+baseprice+" "+sellingprice+" "+quatity+" "+cgst+" "+sgst+" ** ");
					asb = new AddStockBean();
					asb.setPro_name(productname);
					asb.setDept_name(deptname);
					asb.setBase_price(baseprice);
					asb.setPro_price(sellingprice);
					asb.setPro_cgst(cgst);
					asb.setPro_sgst(sgst);
					asb.setPro_qty(quatity);
					
					db = new DAO();
					int kj = db.addStockPurchase(asb);
					if(kj > 0)
					{
						System.out.println("Successfileupload"+kj);
					}
					else
					{
						System.out.println("Failfileupload");
					}
					
//					System.out.println(values[0]);
				}
				inputstream.close();
				//Part uploadedfile = request.getPart("filecsv");
//					Connection con = DBconnection.getDBConnection();
//					PreparedStatement ps = con.prepareStatement("insert into tbl_csv_upload(file_name,file) values(?,?)");
//					File filehandler = new File(filepath);
//					FileInputStream inputStream = new FileInputStream(filehandler);
//					ps.setBlob(1, inputStream);
//					ps.setString(2, filename);
					DAO dao = new DAO();
					
					int jk = dao.addCsvFileToTable(filename, filepath);
					if(jk>0)
					{
						System.out.println("file add to db Success");
					}
					else
					{
						System.out.println("file added to db Fail");
					}
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			response.sendRedirect(request.getContextPath()+"/FileUploadTask.jsp");
		

//		FileInputStream file = new FileInputStream(new File("/root/eclipse-workspace/BillingSystemProject/UploadedCsvFiles/file_example_XLS_50.xls"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(1);
//		Row row;
//		for(int i=1;i<=sheet.getLastRowNum();i++)
//		{
//			row = (Row) sheet.getRow(i);
//			String id;
//			if(row.getCell(0)==null) { id = "0"; }
//			else
//			{
//				id = row.getCell(0).toString();
//			}
//			String name;
//			if(row.getCell(1)==null) { name = null; }
//			else
//			{
//				name = row.getCell(1).toString();
//			}
//			String address;
//			if(row.getCell(2)==null) { address = null; }
//			else
//			{
//				address = row.getCell(2).toString();
//			}
//			System.out.println(id+" "+name+" "+address+"**");
//		}
//		workbook.close();
//		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.print("done");
	}

}
