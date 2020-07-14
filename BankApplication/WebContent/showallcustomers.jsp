<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
				<style>
					table, tr, td{
						 border: 1px solid black;
						 width : 40%;
						 text-align: center;
					}
				</style>
</head>
<body>
					
					<table>
							<tr>
								<td>
									Account number
								</td>
								<td>Account name</td>
								<td>
								Account balance
								</td>	
							</tr>
							<c:forEach var="s" items= "${details}">
							<tr>
									<td>${s.account_no}</td>
								    <td>${s.name }</td>
								    <td>${s.account_balance }</td>
							</tr>
							</c:forEach>
							
							
					</table>
					
</body>
</html>