// File: src/com/healthcare/controller/DoctorDashboardServlet.java
package com.healthcare.controller;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.FeedbackDAO;
import com.healthcare.dao.MedicalRecordDAO;
import com.healthcare.model.Appointment;
import com.healthcare.model.Feedback;
import com.healthcare.model.MedicalRecord;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DoctorDashboardServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;
    private FeedbackDAO feedbackDAO;
    private MedicalRecordDAO medicalRecordDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAO();
        feedbackDAO = new FeedbackDAO();
        medicalRecordDAO = new MedicalRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");
        int doctorUserId = currentUser.getId(); // simple mapping

        // For simplicity, assume doctor_id = user_id
        List<Appointment> appointments = appointmentDAO.getAppointmentsByDoctor(doctorUserId);
        List<Feedback> feedbacks = feedbackDAO.getByDoctor(doctorUserId);
        // Doctor can see all medical records they created (optional)

        request.setAttribute("appointments", appointments);
        request.setAttribute("feedbacks", feedbacks);

        request.getRequestDispatcher("/WEB-INF/views/doctor_dashboard.jsp")
               .forward(request, response);
    }
}
