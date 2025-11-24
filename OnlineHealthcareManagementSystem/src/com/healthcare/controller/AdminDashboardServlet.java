// File: src/com/healthcare/controller/AdminDashboardServlet.java
package com.healthcare.controller;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.SystemSettingDAO;
import com.healthcare.dao.UserDAO;
import com.healthcare.model.SystemSetting;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminDashboardServlet extends HttpServlet {

    private UserDAO userDAO;
    private AppointmentDAO appointmentDAO;
    private SystemSettingDAO settingDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        appointmentDAO = new AppointmentDAO();
        settingDAO = new SystemSettingDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userDAO.getAllUsers();
        List<SystemSetting> settings = settingDAO.getAll();

        int totalUsers = userDAO.countAll();
        int totalDoctors = userDAO.countByRole("DOCTOR");
        int totalPatients = userDAO.countByRole("PATIENT");
        int totalAppointments = appointmentDAO.countAll();

        request.setAttribute("users", users);
        request.setAttribute("settings", settings);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalDoctors", totalDoctors);
        request.setAttribute("totalPatients", totalPatients);
        request.setAttribute("totalAppointments", totalAppointments);

        request.getRequestDispatcher("/WEB-INF/views/admin_dashboard.jsp")
               .forward(request, response);
    }
}
