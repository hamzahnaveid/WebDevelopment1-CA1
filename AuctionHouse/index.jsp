

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AuctionHouse</title>
    </head>
    <body>
		<h1>Login</h1> <br/>
		<s:form action="login" >
            <s:textfield name="email" label="Email" /> <br/>
			<s:textfield name="password" label="Password" /> <br/>
            <s:submit />
        </s:form> <br/>
		<h1>Register</h1> <br/>
		<s:form action="register" >
            <s:textfield name="usernameReg" label="Username" /> <br/>
			<s:textfield name="emailReg" label="Email" /> <br/>
			<s:textfield name="passwordReg" label="Password" /> <br/>
			<s:textfield name="passwordAgain" label="Re-enter Password" /> <br/>
            <s:submit />
        </s:form> <br/>
    </body>
</html>










