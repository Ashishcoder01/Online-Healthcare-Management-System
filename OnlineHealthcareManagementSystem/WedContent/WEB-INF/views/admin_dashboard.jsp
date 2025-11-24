<%-- File: WebContent/WEB-INF/views/admin_dashboard.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthcare.model.User" %>
<%@ page import="com.healthcare.model.SystemSetting" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<h2>Admin Dashboard</h2>
<a href="<%=request.getContextPath()%>/logout">Logout</a>

<h3>Performance Analytics</h3>
<ul>
    <li>Total Users: <%= request.getAttribute("totalUsers") %></li>
    <li>Total Doctors: <%= request.getAttribute("totalDoctors") %></li>
    <li>Total Patients: <%= request.getAttribute("totalPatients") %></li>
    <li>Total Appointments: <%= request.getAttribute("totalAppointments") %></li>
</ul>

<h3>User Management</h3>
<form method="post" action="<%=request.getContextPath()%>/admin/user">
    <input type="hidden" name="action" value="create"/>
    Name: <input type="text" name="name" required/>
    Email: <input type="email" name="email" required/>
    Password: <input type="password" name="password" required/>
    Role:
    <select name="role">
        <option value="ADMIN">Admin</option>
        <option value="DOCTOR">Doctor</option>
        <option value="PATIENT">Patient</option>
    </select>
    <button type="submit">Add User</button>
</form>

<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Status</th><th>Delete</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getName()%></td>
        <td><%=u.getEmail()%></td>
        <td><%=u.getRole()%></td>
        <td><%=u.getStatus()%></td>
        <td>
            <form method="post" action="<%=request.getContextPath()%>/admin/user" style="display:inline;">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<h3>System Settings</h3>
<form method="post" action="<%=request.getContextPath()%>/admin/settings">
    Key: <input type="text" name="settingKey" required/>
    Value: <input type="text" name="settingValue" required/>
    <button type="submit">Save Setting</button>
</form>

<ul>
    <%
        List<SystemSetting> settings = (List<SystemSetting>) request.getAttribute("settings");
        if (settings != null) {
            for (SystemSetting s : settings) {
    %>
    <li><b><%=s.getKey()%></b> = <%=s.getValue()%></li>
    <%
            }
        }
    %>
</ul>

</body>
</html>
