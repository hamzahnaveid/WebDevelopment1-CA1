<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AuctionHouse - Home Page</title>
    </head>
    <body>
		<h1>Hello, <s:property value="#session.name"/></h1>
		<p><a href="index.jsp">Logout</a></p> <br/>
		<ul>
		<li><h2><a href="additem.jsp">Add item for sale</a></h2></li>
		<li><h2><a href="allitems.jsp">View all items for sale</a></h2></li>
		<li><h2><a href="allusers.jsp">View all users</a></h2></li>
		</ul>
    </body>
</html>