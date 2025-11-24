<%-- File: WebContent/login.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Healthcare Login</title>
</head>
<body>
<h2>Online Healthcare Management System - Login</h2>

<form method="post" action="<%=request.getContextPath()%>/login">
    Email: <input type="email" name="email" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <button type="submit">Login</button>
</form>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%=error%></p>
<%
    }
%>

</body>
</html>
