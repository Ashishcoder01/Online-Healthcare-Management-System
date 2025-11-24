// File: src/com/healthcare/controller/PatientAppointmentServlet.java
package com.healthcare.controller;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.model.Appointment;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PatientAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        LocalDate date = LocalDate.parse(request.getParameter("appointmentDate"));
        LocalTime time = LocalTime.parse(request.getParameter("appointmentTime"));
        String notes = request.getParameter("notes");

        Appointment a = new Appointment();
        a.setPatientId(currentUser.getId()); // simple mapping
        a.setDoctorId(doctorId);
        a.setAppointmentDate(date);
        a.setAppointmentTime(time);
        a.setStatus("PENDING");
        a.setNotes(notes);

        appointmentDAO.createAppointment(a);

        response.sendRedirect(request.getContextPath() + "/patient/dashboard");
    }
}
