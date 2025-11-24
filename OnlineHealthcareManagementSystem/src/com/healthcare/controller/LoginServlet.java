// File: src/com/healthcare/controller/LoginServlet.java
package com.healthcare.controller;

import com.healthcare.dao.UserDAO;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.login(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            String role = user.getRole();
            if ("ADMIN".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("DOCTOR".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/patient/dashboard");
            }
        } else {
            request.setAttribute("error", "Invalid email/password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
