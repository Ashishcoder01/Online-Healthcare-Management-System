// File: src/com/healthcare/controller/ProfileServlet.java
package com.healthcare.controller;

import com.healthcare.dao.UserDAO;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");

        currentUser.setName(request.getParameter("name"));
        currentUser.setEmail(request.getParameter("email"));

        userDAO.updateUser(currentUser);
        session.setAttribute("currentUser", currentUser);

        if ("PATIENT".equals(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/patient/dashboard");
        } else if ("DOCTOR".equals(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }
}
