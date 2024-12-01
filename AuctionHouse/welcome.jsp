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
		<h1>Hello, <s:property value="#session.name"/></h1> <br/>
		<ul>
		<li><h2>Add item for sale</h2></li>
		<li><h2>View all items for sale</h2></li>
		<li><h2>View all users</h2></li>
		</ul>
    </body>
</html>