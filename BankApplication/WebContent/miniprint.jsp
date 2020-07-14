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
									Date
								</td>
								<td>accno</td>
								<td>
								   operation
								</td>
								<td>
								amount
								</td>	
							</tr>
							<c:forEach var="s" items= "${list}">
							<tr>
									<td>${s.date}</td>
								    <td>${s.accno }</td>
								    <td>${s.operation}</td>
								    <td>${s.amount}</td>
							</tr>
							</c:forEach>
							
							
					</table>
					
</body>
</html>