// File: src/com/healthcare/dao/FeedbackDAO.java
package com.healthcare.dao;

import com.healthcare.model.Feedback;
import com.healthcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public boolean addFeedback(Feedback f) {
        String sql = "INSERT INTO feedback(patient_id, doctor_id, rating, comments) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, f.getPatientId());
            ps.setInt(2, f.getDoctorId());
            ps.setInt(3, f.getRating());
            ps.setString(4, f.getComments());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> getByDoctor(int doctorId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE doctor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id"));
                f.setPatientId(rs.getInt("patient_id"));
                f.setDoctorId(rs.getInt("doctor_id"));
                f.setRating(rs.getInt("rating"));
                f.setComments(rs.getString("comments"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
