<%@taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AuctionHouse - <s:property value="profileSearch" />'s Profile</title>
	</head>
	<body>
		<p>Logged in as <s:property value="#session.name"/></p>
		<p><a href="welcome.jsp">Home</a></p> <br/>
		<h1><s:property value="profileSearch" />'s profile</h1> <br/>
		<p>User ID: <s:property value="profileId" /></p>
		<p>Username: <s:property value="profileSearch" /></p>
		<p>Email: <s:property value="profileEmail" /></p>
	</body>
</html>