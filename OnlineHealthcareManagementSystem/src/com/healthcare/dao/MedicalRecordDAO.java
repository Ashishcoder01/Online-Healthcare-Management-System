// File: src/com/healthcare/dao/MedicalRecordDAO.java
package com.healthcare.dao;

import com.healthcare.model.MedicalRecord;
import com.healthcare.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public boolean addRecord(MedicalRecord mr) {
        String sql = "INSERT INTO medical_records(patient_id, doctor_id, diagnosis, prescription, visit_date) " +
                "VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mr.getPatientId());
            ps.setInt(2, mr.getDoctorId());
            ps.setString(3, mr.getDiagnosis());
            ps.setString(4, mr.getPrescription());
            ps.setDate(5, Date.valueOf(mr.getVisitDate()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MedicalRecord> getByPatient(int patientId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE patient_id=? ORDER BY visit_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MedicalRecord mr = new MedicalRecord();
                mr.setId(rs.getInt("id"));
                mr.setPatientId(rs.getInt("patient_id"));
                mr.setDoctorId(rs.getInt("doctor_id"));
                mr.setDiagnosis(rs.getString("diagnosis"));
                mr.setPrescription(rs.getString("prescription"));
                Date d = rs.getDate("visit_date");
                if (d != null) mr.setVisitDate(d.toLocalDate());
                list.add(mr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
