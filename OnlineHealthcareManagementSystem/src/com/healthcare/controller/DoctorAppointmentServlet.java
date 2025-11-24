// File: src/com/healthcare/controller/DoctorAppointmentServlet.java
package com.healthcare.controller;

import com.healthcare.dao.AppointmentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class DoctorAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("appointmentId"));
        String status = request.getParameter("status"); // CONFIRMED, CANCELLED, COMPLETED
        appointmentDAO.updateStatus(id, status);
        response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
    }
}
