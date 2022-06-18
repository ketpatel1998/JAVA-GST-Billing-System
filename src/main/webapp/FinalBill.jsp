<%@page import="java.text.DecimalFormat"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="IntoWords.NumberToWordConverter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DababaseConnection.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bill Receipt</title>
  <meta charset="utf-8">
  <style type="text/css">
  	
.navbar {
      margin-bottom: 0;
      border-radius: 0;
}
.divmain{
  margin:20px;
}
nav{
  margin-bottom: 0px;
  background-color: grey;
  color: white;
}
.btngenratebill
{
  float: right;
}
input{
}
.div11{

  border :1px solid black;
  padding: 20px;
  padding-bottom: 40px;
}
.div12{
  padding: 50px;
}
.div121{
  margin: 25px;

}
.div122{
  margin: 5px;
}
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {}
    
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    


  </style>
  <!-- <link rel="stylesheet" type="text/css" href="formcss.css"> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    
  </style>
</head>
<body>

<nav style="" class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" style="color:white;font-size:20px;" href="#">GST BILLING SYSTEM</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
	<li>
      <li><a href="AddNewItem.jsp">Add New</a></li>
      <li><a href="AddExisting.jsp">Add Existing</a></li>
      </li>
         <li><a href="billingpage.html">Check Customer</a></li>
  		<li><a href="BillingAddPage.jsp">Bill</a></li>
		<li><a href="PurchaseTable.jsp">PurchaseReport</a></li>
        <li><a href="SalesTable.jsp">SalesReport</a></li>
        <li><a href="stockavl.jsp">Stock</a></li>
        <li><a href="productdetails.jsp">ProductDetails</a></li>
		<li><a href="FileUploadTask.jsp">Upload Stock</a></li>
		<li><a href="ShowUploadedStock.jsp">CSV Uploaded</a></li>	
 <li><a href="AllBillDetails.jsp">Bills</a></li>
 </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container divmain text-center">    
  <div class="row content">
    <div class="col-sm-12 col-md-12 div11">
    	<center>
		<table class="table table-hover" border="2" height="100px" width="200px">
    		<tr>
				<td colspan="6"><h2>XYZ PVT LTD</h2>
					<p>Address : 99 xyz complex, nr. pqr building, stu, rst
					<br/>Email : xyzhasxyz@xyz.com
					<br/>Phone : 7812882187</p>
				</td>
				<td colspan="5"><p>INVOICE&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CASH MEMO</p><br/><br/><br/><br/><br/><center>ORIGINAL</center></td>
				<td colspan="5">GST Tin : 55egeg5g5ejyt5as2e</br>D L NO : 20 G/A/A 5866</br>D L NO : 21 G/A/A 4666</td>
			</tr>
			<tr>
				<td colspan="9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Customer : <%=session.getAttribute("c_name") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email : <%=session.getAttribute("c_email")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Phone : <%=session.getAttribute("c_phone") %></td>
				<td colspan="5">Bill No:D-2445&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date : 12/5/2019</td>
			</tr>
		<tr>
    		<th rowspan="2">Sr</th>
			<th rowspan="2">Product Name</th>
    		<th rowspan="2">QTY 1 Price</th>
    		<th rowspan="2">Qty</th>
			<th rowspan="2">Disc%</th>
			<th rowspan="2">Base price</th>
    		<th colspan="2">SGST</th>
    		<th colspan="2">CGST</th>
    		<th rowspan="2">Discount Amount</th>
    		<th rowspan="2">Amount</th>
    	</tr>
		<tr>
			<th>Rate</th>
			<th>Value</th>
			<th>Rate</th>
			<th>Value</th>			
		</tr>
		<% DAO d = new DAO();
				DecimalFormat df4 = new DecimalFormat(".##");
    				int i = 0;
    				double total = 0;
    				ResultSet rs = d.getDataFromModel();
    				while(rs.next()) {%>
    			
    	<tr>
    		<td><%=++i%></td>
    				<td><%=rs.getString("product_name") %></td>
    				<td><%=rs.getDouble("price") %></td>
    				
    				<% 
    				double pricebacktracking = rs.getDouble("base_price")*rs.getInt("product_quantity"); 
    				double k = ((pricebacktracking*rs.getDouble("sgst"))/(100));
    				double l = ((pricebacktracking*rs.getDouble("sgst"))/(100));
    				double discountamount = (rs.getDouble("price")*rs.getInt("product_quantity")*rs.getDouble("product_discount"))/100;
    				
    				pricebacktracking = Double.parseDouble(df4.format(pricebacktracking));
    				k = Double.parseDouble(df4.format(k));
    				l = Double.parseDouble(df4.format(l)); 
    				discountamount = Double.parseDouble(df4.format(discountamount));
    				%>
    				
    				<td><%=rs.getInt("product_quantity")%></td>
    				<td><%=rs.getDouble("product_discount")%> %</td>
    				<td><%=pricebacktracking%></td>
    				<td><%=rs.getDouble("sgst")%> %</td>
    				
    				<td><%=k %></td>
    				<td><%=rs.getDouble("cgst")%> %</td>
    				
    				<td><%=l %></td>
    				
    				<td><%=discountamount%></td>
    				<% total = total+rs.getDouble("grandtotal");
    				%>
    				<td><%=rs.getDouble("grandtotal") %></td>
    	</tr>
		<% } %>
		
		<tr>
			<td colspan="11" rowspan="4"></td>
			<td colspan="5">Total = <%=total %></td>
		</tr>
		
	
		<%double round = Math.floor(total);
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
		
		
		/* String ja2 = total+"";
		String st2[] = ja2.split("\\.");
		
		String stl2  = st2[1];
		System.out.println(stl2);
		char ch2[] = stl2.toCharArray();
		char ch3 = ch2[0];
		System.out.println(ch3);
		
		/* System.out.println(ch4); */
		/* double net4;
		if(ch3>='5')
		{
			 net4 = round+1;	
		}
		else
		{
			net4 = round;
		}
		 */ 
		
		%>
		<tr>
			<td colspan="5">ROUNDED OFF = <%=round_off %></td>
		</tr>
		<tr>
			<td colspan="5">NET = <%=net4 %></td>
		</tr>
		<tr>
			<td><% 
				
			String ja = net4+"";
			String st[] = ja.split("\\.");
			String stl  = st[0];
			int uniq = Integer.parseInt(stl);
			out.println(NumberToWordConverter.numberToWord(uniq));
			
			%></td>
		</tr>
		</table>
		
    </div>
  </div>
  
<a href="./FinalBillCopy"><button style="float:right;margin:10px" class="btn btn-primary" >New Bill</button></a>
<a href="javascript:window.print()"><button style="float:right;margin:10px;" class="btn btn-success" >Print</button></a>
</div>
<footer class="container-fluid text-center">
  <p>copyright@2019</p>
</footer>

</body>
</html>
    