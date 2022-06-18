<%@page import="java.sql.ResultSet"%>
<%@page import="DababaseConnection.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">


<!-- Mirrored from dev.lorvent.com/admire/datatables.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 17 Dec 2016 17:26:56 GMT -->
<head>
    <meta charset="UTF-8">
    <title>Uploaded CSV List</title>
    <!--IE Compatibility modes-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--Mobile first-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="img/logo1.ico" />

    <!-- global styles-->
    <link type="text/css" rel="stylesheet" href="css/components.css" />
    <link type="text/css" rel="stylesheet" href="css/custom.css" />
    <!--end of global styles-->
    <!--plugin styles-->
    <link type="text/css" rel="stylesheet" href="vendors/select2/css/select2.min.css" />
    <link type="text/css" rel="stylesheet" href="vendors/datatables/css/scroller.bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="vendors/datatables/css/colReorder.bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="vendors/datatables/css/dataTables.bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="css/pages/dataTables.bootstrap.css" />
    <!-- end of plugin styles -->
    <!--Page level styles-->
    <link type="text/css" rel="stylesheet" href="css/pages/tables.css" />
    <link type="text/css" rel="stylesheet" href="#" id="skin_change" />
	<link type="text/css" rel="stylesheet" href="css/mycss.css"/>
    <!--End of page level styles-->

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
<div class="datatable_page">

                                    <div class="card-block p-t-25">
                                        <div class="">
                                            <div class="pull-sm-right">
                                                <div class="tools pull-sm-right"></div>
                                            </div>
                                        </div>
                                        
				<table class="table table-responsive table-striped table-bordered table-hover" id="sample_1" >
				<tr>
					<th>Sr no</th>
					<th>File Name</th>
					<th>Uploaded Date</th>
					<th>File</th>
				</tr>
				
				<% DAO dao = new DAO();
					ResultSet rs = dao.getAllEntryOfCsvTable();
					int k = 1;
					while(rs.next())
					{
				%>
				<tr>
					<td><%=k %></td>
					<% k++; %>
					<td><%=rs.getString("file") %></td>
					<td><%=rs.getString("entrydate") %></td>
					<td><a href='UploadedCsvFiles/<%=rs.getString("file")%>' download>Click here</a></td>
				</tr>
				<% } %>
				</table>                                        

                                    </div>
									
									    <!-- /#wrap -->
    <!-- global scripts-->
    <script type="text/javascript" src="js/components.js"></script>
    <script type="text/javascript" src="js/custom.js"></script>
    <!--end of global scripts-->
    <!--plugin scripts-->
    <script type="text/javascript" src="vendors/select2/js/select2.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/pluginjs/dataTables.tableTools.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.colReorder.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.rowReorder.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/buttons.colVis.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/buttons.bootstrap.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/buttons.print.min.js"></script>
    <script type="text/javascript" src="vendors/datatables/js/dataTables.scroller.min.js"></script>
    <!-- end of plugin scripts -->
    <!--Page level scripts-->
    <script type="text/javascript" src="js/pages/datatable.js"></script>
    <!-- end of global scripts-->
</div>
<footer class="container-fluid text-center">
  <p>copyright@2019</p>
</footer>
</body>
