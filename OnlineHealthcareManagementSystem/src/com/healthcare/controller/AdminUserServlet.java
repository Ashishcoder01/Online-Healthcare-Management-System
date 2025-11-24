// File: src/com/healthcare/controller/AdminUserServlet.java
package com.healthcare.controller;

import com.healthcare.dao.UserDAO;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminUserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("create".equals(action)) {
            User u = new User();
            u.setName(request.getParameter("name"));
            u.setEmail(request.getParameter("email"));
            u.setPassword(request.getParameter("password"));
            u.setRole(request.getParameter("role"));
            u.setStatus("ACTIVE");
            userDAO.addUser(u);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userDAO.deleteUser(id);
        } else if ("update".equals(action)) {
            User u = new User();
            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setName(request.getParameter("name"));
            u.setEmail(request.getParameter("email"));
            u.setRole(request.getParameter("role"));
            u.setStatus(request.getParameter("status"));
            userDAO.updateUser(u);
        }

        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
