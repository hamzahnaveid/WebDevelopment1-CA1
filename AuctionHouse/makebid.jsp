<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AuctionHouse - Make A Bid</title>
    </head>
    <body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1>Make a bid</h1> <br/>
		<s:form action="makebid" >
			<s:textfield name="itemid" label="Enter Item ID" /> <br/>
			<s:textfield name="bidamnt" label="Bid Amount" /> <br/>
			<s:submit />
		</s:form> <br/>
    </body>
</html>