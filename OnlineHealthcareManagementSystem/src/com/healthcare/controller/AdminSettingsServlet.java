// File: src/com/healthcare/controller/AdminSettingsServlet.java
package com.healthcare.controller;

import com.healthcare.dao.SystemSettingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminSettingsServlet extends HttpServlet {

    private SystemSettingDAO settingDAO;

    @Override
    public void init() {
        settingDAO = new SystemSettingDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("settingKey");
        String value = request.getParameter("settingValue");
        settingDAO.upsert(key, value);
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
