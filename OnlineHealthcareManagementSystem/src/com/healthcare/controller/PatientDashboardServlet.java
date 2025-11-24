// File: src/com/healthcare/controller/PatientDashboardServlet.java
package com.healthcare.controller;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.MedicalRecordDAO;
import com.healthcare.model.Appointment;
import com.healthcare.model.MedicalRecord;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PatientDashboardServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;
    private MedicalRecordDAO medicalRecordDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAO();
        medicalRecordDAO = new MedicalRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");
        int patientUserId = currentUser.getId(); // simple mapping

        // For simplicity, assume patient_id = user_id
        List<Appointment> appointments = appointmentDAO.getAppointmentsByPatient(patientUserId);
        List<MedicalRecord> records = medicalRecordDAO.getByPatient(patientUserId);

        request.setAttribute("appointments", appointments);
        request.setAttribute("records", records);
        request.setAttribute("currentUser", currentUser);

        request.getRequestDispatcher("/WEB-INF/views/patient_dashboard.jsp")
               .forward(request, response);
    }
}
