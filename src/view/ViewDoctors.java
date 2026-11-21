/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */

import dao.DoctorDAO;
import model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewDoctors extends JFrame {

    private JTable doctorTable;
    private DefaultTableModel tableModel;

    public ViewDoctors() {

        setTitle("View Doctors");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Heading
        JLabel title = new JLabel("DOCTOR DETAILS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel headingPanel = new JPanel();
        headingPanel.add(title);

        // Table
        String[] columns = {
            "Doctor ID",
            "Doctor Name",
            "Specialization",
            "Phone",
            "Email"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        doctorTable = new JTable(tableModel);
        doctorTable.setRowHeight(25);
        doctorTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        JScrollPane scrollPane = new JScrollPane(doctorTable);

        // Buttons
        JButton btnRefresh = new JButton("Refresh");
        JButton btnBack = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnBack);

        // Add Components
        add(headingPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load Doctors
        loadDoctors();

        // Refresh Button
        btnRefresh.addActionListener(e -> loadDoctors());

        // Back Button
        btnBack.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    // LOAD DOCTORS FROM DATABASE
    private void loadDoctors() {

        tableModel.setRowCount(0);

        DoctorDAO dao = new DoctorDAO();

        List<Doctor> doctors = dao.getAllDoctors();

        for (Doctor doctor : doctors) {

            Object[] row = {
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                doctor.getSpecialization(),
                doctor.getPhone(),
                doctor.getEmail()
            };

            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ViewDoctors().setVisible(true);
        });
    }
}
