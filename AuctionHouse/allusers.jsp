<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AuctionHouse - Users</title>
	</head>
	<body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1>All users</h1> <br/>
		<table border="2">
			<tr>
				<td>ID</td>
				<td>Username</td>
				<td>Email</td>
			</tr>
			<%@ page import="java.sql.*" %>
			<%
			try {
			String url="jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC";
			String username="root";
			String password="root";
			String query="SELECT user_id, username, email FROM user";
			Connection conn=DriverManager.getConnection(url,username,password);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
			%>
				<tr>
					<td><%=rs.getInt(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getString(3) %></td>
				</tr>
					<%
			}
			%>
				</table>
				<%
				rs.close();
				stmt.close();
				conn.close();
				}
			catch(Exception e) {
				e.printStackTrace();
				}
			%>
	</body>
</html> 