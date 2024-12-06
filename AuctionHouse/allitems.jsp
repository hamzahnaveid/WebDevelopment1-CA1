<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AuctionHouse - Items for Sale</title>
	</head>
	<body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1>All items for sale</h1> <br/>
		<table border="2">
			<tr>
				<td>Item ID</td>
				<td>Name</td>
				<td>Current Bid</td>
				<td>Seller ID</td>
			</tr>
			<%@ page import="java.sql.*" %>
			<%
			try {
			String url="jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC";
			String username="root";
			String password="root";
			String query="SELECT * FROM item";
			Connection conn=DriverManager.getConnection(url,username,password);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				int itemId = rs.getInt(1);
			%>
				<tr>
					<td><%=rs.getInt(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getFloat(3) %></td>
					<td><%=rs.getInt(4) %></td>
				</tr>
				
				<tr>
					<td>
						<table border="6">
						<tr>
							<td>Bid ID</td>
							<td>Bidder ID</td>
							<td>Bid Amount</td>
						</tr>
						<%
						Statement bidStmt = conn.createStatement();
						ResultSet bidRs = bidStmt.executeQuery("SELECT bid_id, bidder_id, bid_amount FROM bid WHERE item_id = " + itemId);
						itemId++;
						while(bidRs.next()) {
						%>

						<tr>
							<td><%=bidRs.getInt(1) %></td>
							<td><%=bidRs.getInt(2) %></td>
							<td><%=bidRs.getFloat(3)%></td>
						</tr>
						<%
						}
						%>
						</table>
					</td>
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