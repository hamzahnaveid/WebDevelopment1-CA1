<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AuctionHouse - My Bids</title>
	</head>
	<body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1>My bids</h1> <br/>
		<table border="2">
			<tr>
				<td>Bid ID</td>
				<td>Item ID</td>
				<td>Bid Amount</td>
			</tr>
			<%@ page import="java.sql.*" %>
			<%
			try {
			String url="jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC";
			String username="root";
			String password="root";
			String currentuser = (String) session.getAttribute("name");
			int userId = 0;
			String query="SELECT user_id FROM user WHERE username = '" + currentuser + "'";
			Connection conn=DriverManager.getConnection(url,username,password);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				userId = rs.getInt(1);
			}
			Statement getBids = null;
			try {
				getBids = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet bidsRs = null;
			try {
				bidsRs = getBids.executeQuery("SELECT bid_id, item_id, bid_amount FROM bid WHERE bidder_id = " + userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(bidsRs.next()) {
			%>
				<tr>
					<td><%=bidsRs.getInt(1) %></td>
					<td><%=bidsRs.getInt(2) %></td>
					<td><%=bidsRs.getFloat(3) %></td>
				</tr>
			<%	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			catch(Exception e) {
				e.printStackTrace();
				}
			%>
	</body>
</html>