<%@page import="in.co.rays.bean.CustomerBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List list = (List) request.getAttribute("list");
		Iterator it = list.iterator();
	%>


	<form action="CustomerListCtl.do" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.CustomerBean"
			scope="request"></jsp:useBean>
			<h1>Customer List</h1>
			<table>
			<tr>
			<a href="CustomerListCtl.do">Customerlist</a>
			|
			<a href="CustomerView.jsp">AddCustomer</a>
			
			<br>
			<br>
			
			
				<td><input type="text" name="fname"></td>
				<td><input type="submit" name="operation" value="search"></td>
				
			</tr>
		</table>
		<table border="1">
			<tr>
				<th width="5%">Select</th>
				
				<th width="13%">Name</th>
				<th width="13%">Amount</th>
				<th width="13%">Accoount Number</th>
				<th width="13%">DOB</th>
				<th width="13%">CustomerId</th>


				<th width="5%">Edit</th>
			</tr>

			<%
				while (it.hasNext()) {

					bean = (CustomerBean) it.next();
			%>

			<tr>
				<td style="text-align: center;"><input type="checkbox"
					name="ids" value="<%=bean.getId()%>"></td>

				<td style="text-align: center;"><%=bean.getName()%></td>
				<td style="text-align: center;"><%=bean.getAmount()%></td>
				<td style="text-align: center;"><%=bean.getAccoountNumber()%></td>
				<td style="text-align: center;"><%=bean.getDob()%></td>
				<td style="text-align: center;"><%=bean.getCustomerId()%></td>

				<td style="text-align: center;"><a
					href="CustomerCtl.do?id=<%=bean.getId()%>">edit</a></td>
			</tr>

			<%
				}
			%>
		</table>
		<table style="width: 100%">
			<tr>

				
				<td align="center" style="width: 25%"><input type="submit"
					name="operation" value="delete"></td>

			</tr>
		</table>

	</form>

</body>

</html>