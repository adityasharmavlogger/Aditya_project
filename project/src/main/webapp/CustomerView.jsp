<%@page import="in.co.rays.bean.CustomerBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="CustomerCtl.do" method="post">
		<%
			String msg = (String) request.getAttribute("msg");
			CustomerBean bean = (CustomerBean) request.getAttribute("bean");
		%>
		<input type="hidden" name="id" value="<%=(bean != null) ? bean.getId() : ""%>"> 
		<table>
			<tr>
				<%
					if (msg != null) {
				%>
				<%=msg%>
				<%
					}
				%>
			</tr>
			<tr>
				<th>Name :</th>
				<td><input type="text" name="name"
					value="<%=(bean != null) ? bean.getName() : ""%>"></td>
			</tr>
			<tr>
				<th>Amount :</th>
				<td><input type="text" name="amount"
					value="<%=(bean != null) ? bean.getAmount() : ""%>"></td>
			</tr>
			<tr>
				<th>Accoount Number :</th>
				<td><input type="text" name="accoountNumber"
					value="<%=(bean != null) ? bean.getAccoountNumber() : ""%>"></td>
			</tr>
			<tr>
				<th>DOB :</th>
				<td><input type="Date" name="dob"
					value="<%=(bean != null) ? bean.getDob() : ""%>"></td>
			</tr>
			<tr>
				<th> CustomerId :</th>
				<td><input type="text" name="customerId"
					value="<%=(bean != null) ? bean.getCustomerId() : ""%>"></td>
			</tr>

			<tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=(bean != null) ? "update" : "save"%>"></td>
			</tr>
			<a href="CustomerListCtl.do">Customerlist</a>
		</table>
	</form>
</body>

</html>