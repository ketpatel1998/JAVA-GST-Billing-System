package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DababaseConnection.DAO;
import IntoWords.NumberToWordConverter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import DababaseConnection.DAO;
 

/*class SmtpAuthenticator extends Authenticator {
	public SmtpAuthenticator() {

	    super();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	 String username = "pyash7224@gmail.com";
	 String password = "yash1305";
	    if ((username != null) && (username.length() > 0) && (password != null) 
	      && (password.length   () > 0)) {

	        return new PasswordAuthentication(username, password);
	    }

	    return null;
	}
}
*/	

/**
 * Servlet implementation class FinalBillCopy
 */
@WebServlet("/FinalBillCopy")
public class FinalBillCopy extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.BLACK);
	static int i1 = 0;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalBillCopy() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	//	SmtpAuthenticator authentication = new SmtpAuthenticator();
		HttpSession session1=request.getSession();
		
		String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port
                 
        String sender = "prajapatidhruv736@gmail.com"; //replace this with a valid sender email address
        String recipient = (String)session1.getAttribute("c_email"); //replace this with a valid recipient email address
        String content = "Mail by XYZ PVT LTD"; //this will be the text of the email
        String subject = "Bill Invoice"; //this will be the subject of the email
         
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
       
        Session session = Session.getDefaultInstance(properties, 
        	    new javax.mail.Authenticator(){
        	        protected PasswordAuthentication getPasswordAuthentication() {
        	            return new PasswordAuthentication(
        	                "prajapatidhruv736@gmail.com", "dhruv@123");// Specify the Username and the PassWord
        	        }
        	});
        ByteArrayOutputStream outputStream = null;
         
        try {           
            //construct the text body part
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText(content);
             
            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            
        	Document document = new Document( PageSize.A4, 25, 25, 25, 25 );
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.addTitle("My first PDF");
            document.addSubject("Using iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Yash Patel");
            document.addCreator("Yash Patel");
           
            Paragraph preface = new Paragraph();
            
            for (int i = 0; i < 1; i++) {
                preface.add(new Paragraph(" "));
            }
          
            Font f=new Font(FontFamily.TIMES_ROMAN,11.0f,0/*Font.UNDERLINE,*/,BaseColor.BLACK);   
            preface = new Paragraph("99 xyz complex, nr.pqr building, stu, rst",f);
            preface.setAlignment(Element.ALIGN_RIGHT);
            document.add(preface);
           
            preface = new Paragraph("xyzhasxyz@xyz.com",f);
            preface.setAlignment(Element.ALIGN_RIGHT);
            document.add(preface);
            
            preface = new Paragraph("7812882187",f);
            preface.setAlignment(Element.ALIGN_RIGHT);
            document.add(preface);
            
            // Will create: Report generated by: _name, _date
          //  preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
            Chunk glue = new Chunk(new VerticalPositionMark());
            Font f1=new Font(FontFamily.TIMES_ROMAN,25.0f,Font.BOLD,BaseColor.BLACK);
            preface = new Paragraph("XYZ PVT LTD",f1);
            preface.add(new Chunk(glue));
            
            
            preface.add(new Phrase("Invoice",catFont));
            for (int i = 0; i < 2; i++) {
                preface.add(new Paragraph(" "));
            }
            //addEmptyLine(preface, 2);
            document.add(preface);
     		
            int x=0;
            
          
            preface = new Paragraph("Bill To :   "+session1.getAttribute("c_name"),f);
            preface.add(new Chunk(glue));
            preface.add(new Paragraph("Invoice No :                   "+(++i1)));
            document.add(preface);
            
            DateFormat df1=new SimpleDateFormat("dd/MM/yyyy");
            Date date=new Date();
            preface.setSpacingBefore(50);
            preface = new Paragraph("                 "+session1.getAttribute("c_phone"),f);
            preface.add(new Chunk(glue));
            preface.add(new Paragraph("Date :     "+df1.format(date)));
           
            for (int i = 0; i < 2; i++) {
                preface.add(new Paragraph(" "));
            }
            document.add(preface);

            PdfPTable table = new PdfPTable(11);
            table.setTotalWidth(542);
            table.setLockedWidth(true);
            table.setWidths(new float[]{1,4,2,1,2,2,1,2,1,2,3});
            PdfPCell c1 = new PdfPCell(new Phrase("Sr",f));
            table.addCell(c1);
     
            c1 = new PdfPCell(new Phrase("Description",f));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("M.R.P",f));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Qty",f));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Disc%",f));
            table.addCell(c1);
           
            c1 = new PdfPCell(new Phrase("Price",f));
            table.addCell(c1);
           
            c1 = new PdfPCell(new Phrase("SGST",f));
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("CGST",f));
            c1.setColspan(2);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Amount",f));
            table.addCell(c1);
         
            c1= new PdfPCell(new Phrase(""));
            table.addCell(c1);
     
            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Rate",f));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Value",f));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Rate",f));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Value",f));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);
           
            table.setHeaderRows(2);
            

        	DAO d = new DAO();
    		double total = 0,disc=0,price=0,sgst=0,cgst=0;
    		int qty=0;
    		int i2=0;
    		String price1="";
    		ResultSet rs = d.getDataFromModel();
    		try {
    			while(rs.next()) 
    			{
    				c1 = new PdfPCell(new Phrase(String.valueOf(++i2),f));
    	            table.addCell(c1);
    				
    				c1 = new PdfPCell(new Phrase(rs.getString("product_name"),f));
    	            table.addCell(c1);

    	        	c1 = new PdfPCell(new Phrase(String.valueOf(rs.getDouble("price")),f));
    	            table.addCell(c1);
    	            
    	            qty = qty+rs.getInt("product_quantity");
    	            c1 = new PdfPCell(new Phrase(String.valueOf(rs.getInt("product_quantity")),f));
    	            table.addCell(c1);
    	            
    	            disc=disc+rs.getDouble("product_discount");
    	        	c1 = new PdfPCell(new Phrase(String.valueOf(rs.getDouble("product_discount")),f));
    	            table.addCell(c1);
    	         

    	            DecimalFormat df3 = new DecimalFormat(".##"); 
    	    		price=price+((rs.getDouble("price")/(100+rs.getDouble("sgst")+rs.getDouble("cgst")))*100);
    	    		price1=df3.format(price);
    	    		System.out.println("Price:"+price1);
    	            
    	            c1 = new PdfPCell(new Phrase(df3.format(((rs.getDouble("price")/(100+rs.getDouble("sgst")+rs.getDouble("cgst")))*100)),f));
    	            table.addCell(c1);
    	            
    	        	c1 = new PdfPCell(new Phrase(String.valueOf(rs.getDouble("sgst")),f));
    	            table.addCell(c1);
    	            
    	            sgst=sgst+((rs.getDouble("price")*rs.getDouble("sgst"))/100);
    	        	c1 = new PdfPCell(new Phrase(String.valueOf(((rs.getDouble("price")*rs.getDouble("sgst"))/100)),f));
    	            table.addCell(c1);
    	            
    	        	c1 = new PdfPCell(new Phrase(String.valueOf(rs.getDouble("cgst")),f));
    	            table.addCell(c1);
    	            
    	        	cgst=cgst+((rs.getDouble("price")*rs.getDouble("cgst"))/100);
    	            c1 = new PdfPCell(new Phrase(String.valueOf((rs.getDouble("price")*rs.getDouble("cgst"))/100),f));
    	            table.addCell(c1);
    	            
    	            total = total+rs.getDouble("grandtotal");
    	        	c1 = new PdfPCell(new Phrase(String.valueOf(rs.getDouble("grandtotal")),f));
    	            table.addCell(c1);
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

//            document.add( Chunk.NEWLINE );
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setFixedHeight(72f);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setColspan(3);
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(String.valueOf(qty)));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(disc)));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(price1));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(sgst)));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(""));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(cgst)));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(total)));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(""));
            c1.setRowspan(4);
            c1.setColspan(7);
            table.addCell(c1);
            
            
            c1 = new PdfPCell(new Phrase("OTHER +/-                                            "+0.0,f));
            c1.setColspan(4);
            table.addCell(c1);
            
             double round = Math.floor(total);
    		 DecimalFormat df2 = new DecimalFormat(".##"); 
    		 double rounded=(Math.ceil(total)-total);
    		 String stro=df2.format(rounded);
    		 double rounded1=Double.parseDouble(stro);
    		
    		String ja2 = total+"";
    		String st2[] = ja2.split("\\.");
    		
    		String stl2  = st2[1];
    		System.out.println(stl2);
    		char ch2[] = stl2.toCharArray();
    		char ch3 = ch2[0];
    		System.out.println(ch3);
    		
    		
    		double net4;
    		double round_off;
    		if(ch3>='5')
    		{
    			 net4 = round+1;
    			 round_off=rounded1;
    		}
    		else
    		{
    			net4 = round;
    			round_off=0.0;
    		}
    		
            c1 = new PdfPCell(new Phrase("ROUNDED OFF                                     "+round_off,f));
            c1.setColspan(4);
            table.addCell(c1);
            
            Font f2=new Font(FontFamily.TIMES_ROMAN,20.0f,Font.BOLD,BaseColor.BLACK);		
            c1 = new PdfPCell(new Phrase("NET                     "+net4,f2));
            c1.setFixedHeight(30f);
            c1.setColspan(4);
            table.addCell(c1);
            

        	String ja = net4+"";
			String st[] = ja.split("\\.");
			String stl  = st[0];
			int uniq = Integer.parseInt(stl);
		
            c1 = new PdfPCell(new Phrase(NumberToWordConverter.numberToWord(uniq),f));
            c1.setColspan(4);
            table.addCell(c1);
            
            document.add(table);
            // Start a new page
            document.newPage();
            
            document.close();
            
            byte[] bytes = outputStream.toByteArray();
             
            //construct the pdf body part
//            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
//            MimeBodyPart pdfBodyPart = new MimeBodyPart();
//            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
//            pdfBodyPart.setFileName("Invoice.pdf");
//                         
            //construct the mime multi part
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            mimeMultipart.addBodyPart(textBodyPart);
//            mimeMultipart.addBodyPart(pdfBodyPart);
//             
//            //create the sender/recipient addresses
//            InternetAddress iaSender = new InternetAddress(sender);
//            InternetAddress iaRecipient = new InternetAddress(recipient);
//             
//            //construct the mime message
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setSender(iaSender);
//            mimeMessage.setSubject(subject);
//            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
//            mimeMessage.setContent(mimeMultipart);
             
            //send off the email
//            Transport.send(mimeMessage);
//             
//            System.out.println("sent from " + sender +", to " + recipient + "; server = " + smtpHost + ", port = " + smtpPort);         
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try 
                { 
	                outputStream.close(); 
	                outputStream = null;
	            }
                catch(Exception ex) { }
            }
        }
        
		HttpSession httpsession = request.getSession();
		httpsession.removeAttribute("custo_name");
		httpsession.removeAttribute("c_name");
		httpsession.removeAttribute("c_email");
		httpsession.removeAttribute("c_phone");

		DAO d=new DAO();
		int k = d.clearTableModel();
		if(k>0) {
			response.sendRedirect("BillingAddMainPage.jsp");
		}
		else
		{
			System.out.print("Error in cleaning the model table");
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
