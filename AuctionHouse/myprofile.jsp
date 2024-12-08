<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AuctionHouse - My Profile</title>
	</head>
	<body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1>My profile</h1> <br/>
		<%@ page import="java.sql.*" %>
			<%
			try {
			String url="jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC";
			String username="root";
			String password="root";
			String currentuser = (String) session.getAttribute("name");
			int userId = 0;
			String email = "";
			String query="SELECT user_id, email FROM user WHERE username = '" + currentuser + "'";
			Connection conn=DriverManager.getConnection(url,username,password);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				userId = rs.getInt(1);
				email = rs.getString(2);
			}
			%>
			<p>User ID: <%=userId%></p>
			<p>Username: <%=currentuser%></p>
			<p>Email: <%=email%></p>
			<%
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			%>
	</body>
</html>