<%-- File: WebContent/WEB-INF/views/doctor_dashboard.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthcare.model.Appointment" %>
<%@ page import="com.healthcare.model.Feedback" %>
<html>
<head>
    <title>Doctor Dashboard</title>
</head>
<body>
<h2>Doctor Dashboard</h2>
<a href="<%=request.getContextPath()%>/logout">Logout</a>

<h3>Schedule Management / Appointments</h3>
<table border="1">
    <tr>
        <th>ID</th><th>Patient ID</th><th>Date</th><th>Time</th><th>Status</th><th>Notes</th><th>Action</th>
    </tr>
    <%
        List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
        if (appointments != null) {
            for (Appointment a : appointments) {
    %>
    <tr>
        <td><%=a.getId()%></td>
        <td><%=a.getPatientId()%></td>
        <td><%=a.getAppointmentDate()%></td>
        <td><%=a.getAppointmentTime()%></td>
        <td><%=a.getStatus()%></td>
        <td><%=a.getNotes()%></td>
        <td>
            <form method="post" action="<%=request.getContextPath()%>/doctor/appointment">
                <input type="hidden" name="appointmentId" value="<%=a.getId()%>"/>
                <select name="status">
                    <option value="CONFIRMED">Confirm</option>
                    <option value="CANCELLED">Cancel</option>
                    <option value="COMPLETED">Complete</option>
                </select>
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<h3>Patient Feedback</h3>
<table border="1">
    <tr>
        <th>Patient ID</th><th>Rating</th><th>Comments</th>
    </tr>
    <%
        List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
        if (feedbacks != null) {
            for (Feedback f : feedbacks) {
    %>
    <tr>
        <td><%=f.getPatientId()%></td>
        <td><%=f.getRating()%></td>
        <td><%=f.getComments()%></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
