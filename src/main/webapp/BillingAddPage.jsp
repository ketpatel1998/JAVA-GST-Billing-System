<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="DababaseConnection.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">
<head>
  <title>Billing System</title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="formcss.css"> 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
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
         <li><a href="BillingAddMainPage.jsp">Check Customer</a></li>
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
    		<h2>XYZ PVT LTD</h2>
    		<p>Address : 99 xyz complex, nr. pqr building, stu, rst</p>
    		<p>Email : xyzhasxyz@xyz.com</p>
    		<p>Phone : 7812882187</p>

    	</center>
    	<hr>
    	
    	<%if(session.getAttribute("edit123")==null){ %>
	    	<form method="post" action="./controllerbilling.html">
	    	<% if(session.getAttribute("custo_name")==null) {
	    		if(session.getAttribute("cussearchinfo")==null){%>
	    	<label class="div121">Customer Name * </label>
	    	<input class="div122" type="text" name="nmcustomername">
	    	<label class="div121">Customer Phone * </label>
	    	<input class="div122" type="text" pattern="[789][0-9]{9}" name="nmcustomerphone"  >
	    	<label class="div121">Customer Email * </label>
	    	<input class="div122" type="email" name="nmcustomeremail">
	    	<% out.println("<br><span style='color:red;'>New Customer</span>");%>
	    	
	    	<% }else{
	    	ResultSet rs=(ResultSet)session.getAttribute("cussearchinfo");
	    	rs.next();%>
	    	<label class="div121">Customer Name * </label>
	    	<input class="div122" type="text" name="nmcustomername" value='<%=rs.getString("c_name")%>'>
	    	<label class="div121">Customer Phone * </label>
	    	<input class="div122" type="text" pattern="[789][0-9]{9}" name="nmcustomerphone" value='<%=rs.getString("c_phone")%>'>
	    	<label class="div121">Customer Email * </label>
	    	<input class="div122" type="email" name="nmcustomeremail" value='<%=rs.getString("c_email")%>'>
	    	<% out.println("<br><span style='color:red;'>Customer Found</span>");%>
	    	
	    	<% session.setAttribute("cussearchinfo",null);	} }%>
	    	<br>
    	<div class="div12">
    		<label class="div121"> Select Product * </label>
    		<% DAO dd  = new DAO(); %>
    		<% ResultSet rs2 = dd.getAllProducts(); %>
    			
    		<select class="div122" name="nmproductname">
    		
  				<option value="pd00">Select</option>
    		<%while(rs2.next())
    			{  %>
    		
    			<option><%=rs2.getString("product_name") %></option>
    			
    			<% } %>
    		</select>
    		<label class="div121">Quantity *</label>
    		<input class="div122" type="number" name="nmprductquantity">
    		<label class="div121">Discount * </label>
    	<input class="div122" value="0"  type="number" name="nmdiscount">
    		<input type="submit" class="btn btn-primary div121" value="Add" name="nmproductaddbutton">

    	</div>
    	</form>
    	<%}
    	else{%>
    	<form action="./EditController2" method="post">
    		<% if(session.getAttribute("custo_name")==null) { %>
	    	<label class="div121">Customer Name * </label>
	    	<input class="div122" type="text" name="nmcustomername">
	    	<label class="div121">Customer Phone * </label>
	    	<input class="div122" type="text" pattern="[789][0-9]{9}" name="nmcustomerphone">
	    	<label class="div121">Customer Email * </label>
	    	<input class="div122" type="email" name="nmcustomeremail">
	    	<% } %>
	    	<br>
    	
	    	<div class="div12">
	    	<label class="div121"> Select Product * </label>
	    	<select class="div122" name="nmproductname">
	    		<% ResultSet rs2=(ResultSet)session.getAttribute("edit123");
	    		while(rs2.next()){  %>
    			
    			<option><%=rs2.getString("product_name") %></option>
    				<% DAO dd  = new DAO();
    				 ResultSet rs3 = dd.getAllProducts(); %>
    			<% while(rs3.next()) {%>	
    			<option><%=rs3.getString("product_name") %></option>
    			<% } %>
    			
	    		</select>
	    		<input type="hidden" value='<%=rs2.getInt("sr_no") %>' name="srno1" >
	    		<label class="div121">Quantity *</label>
	    		<input class="div122" type="number" name="nmprductquantity" value='<%=rs2.getInt("product_quantity")%>' >
	    		<label class="div121">Discount * </label>
	    		<input class="div122" value="0"  type="number" name="nmdiscount" value='<%=rs2.getDouble("product_discount")%>'>
	    		<input type="submit" class="btn btn-primary div121" value="Add" name="nmproductaddbutton">
			<% } %>
    	</div>
    	</form>
    	<%} %>
    	
    	
    	<hr>
    	<%
    		String hell = (String)session.getAttribute("productnamenotavail");
    		if(session.getAttribute("productstatus")==null)
    		{
    			out.print("");
    			
    		}
    		else
    		{
    			out.println("<span style='color:red;'>Item "+hell+" is Out of Stock</span>");

        		session.setAttribute("productstatus", null);
    		}
    	%>
    	<div class="raw">
    		<div class="col-md-12 col-sm-12">
    		<table class="table text-center table-hover" border="2">
    			<tr>
    				<th>Sr</th>
    				<th>Product-Name</th>
    				<th>MRP</th>
    				<th>Qty</th>
    				<th>Disc%</th>
    				<th>Base Amount</th>
    				<th colspan="2">SGST</th>
    				<th colspan="2">CGST</th>
    				<th>Total Amount</th>
    				<th>Edit</th>
    				<th>Delete</th>
    			</tr>
    			<% DAO d = new DAO();
    				int i = 0;
    				DecimalFormat df3 = new DecimalFormat(".##");
    				ResultSet rs = d.getDataFromModel();
    				while(rs.next()) {%>
    			<tr>
    				<td><%=++i%></td>
    				<td><%=rs.getString("product_name") %></td>
    				<td><%=rs.getDouble("price")%></td>
    				<td><%=rs.getInt("product_quantity")%></td>
    				<td><%=rs.getDouble("product_discount")%> %</td>
    				<td><%=rs.getDouble("base_price") %></td>
    				<td><%=rs.getDouble("sgst")%> %</td>
    				<% double k = ((rs.getDouble("base_price")*rs.getDouble("sgst"))/(100));
    					k = Double.parseDouble(df3.format(k));
    				%>
    				<td><%=k %></td>
    				<td><%=rs.getDouble("cgst")%> %</td>
    				<% double l = ((rs.getDouble("base_price")*rs.getDouble("sgst"))/(100));
    					l = Double.parseDouble(df3.format(l));
    				%>
    				<td><%=l %></td>
    				<td><%=rs.getDouble("grandtotal") %></td><td>
    				<form method="post" action="EditItemController">
    				<input type="hidden" value='<%=rs.getInt("sr_no") %>' name="rowid1">
    				<input type="submit" value="Edit" >
    				</form></td>
    				<td><form method="post" action="DeleteItemController">
    				<input type="hidden" value='<%=rs.getInt("sr_no") %>' name="rowid1">
    				<input type="submit" value="Delete" >
    				</form></td>
    			</tr>
    			<% } %>
    		</table>
    		<!-- <a href=./billgenerate.html><button class="btn btngenratebill btn-primary">Generater Bill</button></a>     --> 		
    		<a href=./FinalBill.jsp><button class="btn btngenratebill btn-primary">Generater Bill</button></a>
    		</div>
    	</div>

    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>copyright@2019</p>
</footer>

</body>
</html>
    