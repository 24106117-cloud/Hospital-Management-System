/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */

import dao.PatientDAO;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewPatients extends JFrame {

    public ViewPatients() {

        setTitle("View Patients");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {
            "ID", "Name", "Age", "Gender",
            "Phone", "Address", "Disease"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        PatientDAO dao = new PatientDAO();
        List<Patient> patients = dao.getAllPatients();

        for (Patient p : patients) {

            Object[] row = {
                p.getPatientId(),
                p.getPatientName(),
                p.getAge(),
                p.getGender(),
                p.getPhone(),
                p.getAddress(),
                p.getDisease()
            };

            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewPatients().setVisible(true);
    }
}
