<%-- File: WebContent/WEB-INF/views/patient_dashboard.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthcare.model.Appointment" %>
<%@ page import="com.healthcare.model.MedicalRecord" %>
<%@ page import="com.healthcare.model.User" %>
<html>
<head>
    <title>Patient Dashboard</title>
</head>
<body>
<%
    User currentUser = (User) request.getAttribute("currentUser");
%>
<h2>Patient Dashboard - Welcome <%= (currentUser != null ? currentUser.getName() : "") %></h2>
<a href="<%=request.getContextPath()%>/logout">Logout</a>

<h3>Profile Management</h3>
<form method="post" action="<%=request.getContextPath()%>/profile/update">
    Name: <input type="text" name="name" value="<%=currentUser != null ? currentUser.getName() : ""%>" required/>
    Email: <input type="email" name="email" value="<%=currentUser != null ? currentUser.getEmail() : ""%>" required/>
    <button type="submit">Update Profile</button>
</form>

<h3>Appointment History</h3>
<table border="1">
    <tr>
        <th>ID</th><th>Doctor ID</th><th>Date</th><th>Time</th><th>Status</th><th>Notes</th>
    </tr>
    <%
        List<Appointment> appts = (List<Appointment>) request.getAttribute("appointments");
        if (appts != null) {
            for (Appointment a : appts) {
    %>
    <tr>
        <td><%=a.getId()%></td>
        <td><%=a.getDoctorId()%></td>
        <td><%=a.getAppointmentDate()%></td>
        <td><%=a.getAppointmentTime()%></td>
        <td><%=a.getStatus()%></td>
        <td><%=a.getNotes()%></td>
    </tr>
    <%
            }
        }
    %>
</table>

<h3>Book New Appointment</h3>
<form method="post" action="<%=request.getContextPath()%>/patient/appointment">
    Doctor User ID: <input type="number" name="doctorId" required/><br/>
    Date (YYYY-MM-DD): <input type="text" name="appointmentDate" required/><br/>
    Time (HH:MM): <input type="text" name="appointmentTime" required/><br/>
    Notes: <input type="text" name="notes"/><br/>
    <button type="submit">Book</button>
</form>

<h3>Medical History</h3>
<table border="1">
    <tr>
        <th>ID</th><th>Doctor ID</th><th>Diagnosis</th><th>Prescription</th><th>Visit Date</th>
    </tr>
    <%
        List<MedicalRecord> records = (List<MedicalRecord>) request.getAttribute("records");
        if (records != null) {
            for (MedicalRecord r : records) {
    %>
    <tr>
        <td><%=r.getId()%></td>
        <td><%=r.getDoctorId()%></td>
        <td><%=r.getDiagnosis()%></td>
        <td><%=r.getPrescription()%></td>
        <td><%=r.getVisitDate()%></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
