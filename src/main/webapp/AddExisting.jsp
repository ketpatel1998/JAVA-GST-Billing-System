<%@page import="java.sql.ResultSet"%>
<%@page import="DababaseConnection.DAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Existing Item</title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="formcss.css"> 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style type="text/css">
  	input{
  		width: 80px;
  	}
  	#prname{
  		width: 100%;
  	}
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
  

  </div>
</div>
<div class="raw">
	
	<div class="col-md-8">
		
	
	<form action="./AddExistingController" method="post">
		<table class="table text-center table-hover" border="2">
    			<tr>
    				<th>SR.NO</th>
    				<th>Product-Name</th>
    				<th>Qty</th>
    				
    			</tr>
    			<tr>
    				<td>1</td>
    				<td>	
    				
    				<select  id="prname" name="productname">
    				<%
    					DAO dao = new DAO();
    					ResultSet rs =  dao.getAllProducts();
    					while(rs.next()){
    				%>
    				<option><%=rs.getString("product_name") %></option>
    				<% } %>
    				
    				</select>
    			
    			
    				    			</td>
    			<td><input type="text" name="Qty" value="0"></td>
    			<td><input type="submit" value="ADD"></td>
    			</tr>
    			</table>

		</form>
	</div>
</div>
</body>
</html>