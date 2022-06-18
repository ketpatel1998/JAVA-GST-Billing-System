<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login and Registration Form</title>
	<link rel="stylesheet" href="login1.css">
	<style>
body {
	background-image: linear-gradient(rgba(0,0,0,0.6),rgba(0,0,0,0.6)),url(bag.jpg);
	height: 100vh;
	background-size: cover;
	background-position: center; 
}
.login-page{
	width: 360px;
	padding: 10% 0 0;
	margin: auto;
}
.form{
	position: relative;
	z-index: 1;
	background: #F2D7D5;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
}
.form input{
	font-family: "Roboto",sans-serif;
	outline: 1;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}
.form textarea{
	font-family: "Roboto",sans-serif;
	outline: 1;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}
.form .button{
	font-family: "Roboto",sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	cursor: pointer;
}
.form .button:hover,.form .button:active{
	background: #43A047;
}
.form .message{
	margin: 15px 0 0;
	color: blue;
	font-size: 20px;
}	
.form .message{
	color: #4CAF50;
	text-decoration: none;
}

	</style>
</head>

<body bgcolor="#E6E6FA">
	
	<div class="form">
		<form class="registration-form" method="POST" action="./Aunthentication">
			<h1>REGISTRATION FORM</h1>
			<input type="text" placeholder="Enter Name" name="fname"/></br>
			<input type="text" placeholder="Enter Last Name" name="lname"/></br>
			<input type="text" placeholder="Enter Storename" name="storename"/></br>
			<input type="text" placeholder="Enter Gst Tin No" name="gsttin"/></br>
			<input type="text" placeholder="Enter DL No" name="dlno"/></br>
			<textarea placeholder="Enter Address" rows="4" cols="21"></textarea></br>
			<input type="text" placeholder="Enter Username" name="username"/></br>		
			<input type="text" placeholder="Enter Password" name="password"/></br>
			<input type="text" placeholder="Enter 10 digit number" name="phoneno"/></br>
			<input type="text" placeholder="Enter Email" name="email"/></br>
				<input type="text" placeholder="AREACODE" name="areacode"/></br>
			
			<input type="submit" value="Register" class="button">
			<p class="message">Already Registered? <a href="login.jsp">Login</a></p>
		</form>
	</div>
		
</body>
</html>