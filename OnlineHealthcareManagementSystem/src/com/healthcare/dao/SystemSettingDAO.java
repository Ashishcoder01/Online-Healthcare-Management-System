// File: src/com/healthcare/dao/SystemSettingDAO.java
package com.healthcare.dao;

import com.healthcare.model.SystemSetting;
import com.healthcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SystemSettingDAO {

    public List<SystemSetting> getAll() {
        List<SystemSetting> list = new ArrayList<>();
        String sql = "SELECT * FROM system_settings";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SystemSetting s = new SystemSetting();
                s.setId(rs.getInt("id"));
                s.setKey(rs.getString("setting_key"));
                s.setValue(rs.getString("setting_value"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean upsert(String key, String value) {
        String sql = "INSERT INTO system_settings(setting_key, setting_value) VALUES (?,?) " +
                     "ON DUPLICATE KEY UPDATE setting_value=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, key);
            ps.setString(2, value);
            ps.setString(3, value);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
