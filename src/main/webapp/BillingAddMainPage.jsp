<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="DababaseConnection.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">
<head>
  <title>Billing Page Main</title>
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
    		<form action="./CustomerSerachController" method="post">
    		<label class="div121">Customer Phone * </label>
	    	<input class="div122" type="text" pattern="[789][0-9]{9}" name="nmcustomerphone" required>
	    	<input type="submit" class="btn btn-primary div121" value="Search" name="btnsearch">
	    	
	    	</form>
	    </div>
	    </div>
	    </div>
    	
<footer class="container-fluid text-center" style="margin-top:280px;">
  <p>copyright@2019</p>
</footer>

</body>
</html>
    