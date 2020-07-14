<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <style>
        h2 {text-align:center;}
      </style>
                  <h2> "${login}"</h2>
     <center>
	   <a href="addcustomer.jsp"><h2>add customer</h2></a><br>
       <a href="SearchCustomer.jsp"><h2>Search the Customer Details by account number</h2></a><br>
       <a href="updatecustomerdetails1.jsp"><h2>Modify the Customer Details </h2></a><br>
       <a href="Balanceinquiry.jsp"><h2>Balance inquiry for a particular account</h2></a><br>
       <a href="CloseCustomerAccount.jsp"><h2>Close (Delete) the Customer Account from Banks System</h2></a><br>
       <a href="AdminServlet"><h2>Display all customer details</h2></a><br>
</center>



</body>
</html>