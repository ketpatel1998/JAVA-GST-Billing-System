<%@page import="RoleBeans.SetdatesBeans"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DababaseConnection.DAO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>
<!doctype html>
<html class="no-js" lang="en">


<!-- Mirrored from dev.lorvent.com/admire/datatables.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 17 Dec 2016 17:26:56 GMT -->
<head>
    <meta charset="UTF-8">
    <title>Purchase Report </title>
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
  									
                                        
                                        <table class="table table-responsive table-striped table-bordered table-hover" id="sample_1">
                                            <thead>
                                                <tr>
                                                  <th>Sr_no</th>
                                                    <th>Purchase Date</th>
                                                    <th>Product_name</th>
                                                    <th>Department_name</th>
                                                    <th>Base Price</th>
                                                    <th>Selling Price</th>
                                                    <th>Quantity</th>
                                                    <th>CGST</th>
                                                    <th>SGST</th>
                                                    <th>Profit</th>
                                                    <th>Base Gst</th>
                                                    
                                                    <th>Profit Gst</th>
                                                    <th>Total Gst</th>
                                             
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <% DAO dao = new DAO();
                                            SetdatesBeans sdb = (SetdatesBeans)session.getAttribute("dates");
                                            String tod = sdb.getTodate();
                                            String fromd = sdb.getFromdate();
                                            	ResultSet rs = dao.getInfoByDateInPurchase(tod,fromd);
                                            	int h = 0;
                                            	double aa = 0;
                                            	double bb = 0;
                                            	double cc = 0;
                                            	double dd = 0;
                                            	while(rs.next())
                                            	{
                                            %>
                                                <tr>
                                                    <td><%=++h %></td>
                                                   <td><%=rs.getString("adding_date") %></td>
                                                    <td><%=rs.getString("product_name") %></td>
                                                    
                                                    <td><%=rs.getString("dept_name")%></td>
                                                    <td><%=rs.getDouble("base_price")%></td>
                                                    
                                                    <td><%=rs.getDouble("price")%></td>
                                                    
                                                    <td><%=rs.getInt("qty") %></td>
                                                    <td><%=rs.getDouble("cgst") %></td>
                                                    <td><%=rs.getDouble("sgst") %></td>
                                                    <% aa = rs.getDouble("profit");
                                                    	bb += rs.getDouble("base_gst");
                                                    	cc += rs.getDouble("profit_gst");
                                                    	dd += rs.getDouble("g_gst");
                                                    %>
                                                    <td><%=rs.getDouble("profit") %></td>
                                                    <td><%=rs.getDouble("base_gst") %></td>
                                                    <td><%=rs.getDouble("profit_gst") %></td>
                                                    
                                                    <td><%=rs.getDouble("g_gst") %></td>
                                                    
                                                </tr>
                                                <%} %>
                                                <tr>
                                                	<td colspan="9">Total</td>
                                                	<td><%=aa %></td> 
                                                	<td><%=bb %></td>
                                                	<td><%=cc %></td>
                                                	<td><%=dd %></td>
                                                </tr>
                                            </tbody>
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

<!-- Mirrored from dev.lorvent.com/admire/datatables.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 17 Dec 2016 17:27:03 GMT -->
</html>
    