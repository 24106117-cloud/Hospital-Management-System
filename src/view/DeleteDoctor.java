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
import java.awt.*;

public class DeleteDoctor extends JFrame {

    private JTextField txtDoctorId;
    private JTextField txtName;
    private JTextField txtSpecialization;
    private JTextField txtPhone;
    private JTextField txtEmail;

    public DeleteDoctor() {

        setTitle("Delete Doctor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        // Heading
        JLabel title = new JLabel("DELETE DOCTOR");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headingPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        headingPanel.add(title);

        // Form
        JPanel formPanel = new JPanel(
                new GridLayout(5, 2, 10, 15)
        );

        txtDoctorId = new JTextField();
        txtName = new JTextField();
        txtSpecialization = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();

        txtName.setEditable(false);
        txtSpecialization.setEditable(false);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);

        formPanel.add(new JLabel("Doctor ID:"));
        formPanel.add(txtDoctorId);

        formPanel.add(new JLabel("Doctor Name:"));
        formPanel.add(txtName);

        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(txtSpecialization);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);

        // Buttons
        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");
        JButton btnBack = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Add Panels
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);

        // SEARCH BUTTON
        btnSearch.addActionListener(e -> {

            try {

                int doctorId = Integer.parseInt(
                        txtDoctorId.getText().trim()
                );

                DoctorDAO dao = new DoctorDAO();

                Doctor doctor = dao.getDoctorById(doctorId);

                if (doctor != null) {

                    txtName.setText(doctor.getDoctorName());
                    txtSpecialization.setText(doctor.getSpecialization());
                    txtPhone.setText(doctor.getPhone());
                    txtEmail.setText(doctor.getEmail());

                } else {

                    JOptionPane.showMessageDialog(
                            this, "Doctor Not Found!"
                    );

                    clearDetails();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this, "Enter a valid Doctor ID!"
                );
            }
        });

        // DELETE BUTTON
        btnDelete.addActionListener(e -> {

            if (txtDoctorId.getText().trim().isEmpty()
                    || txtName.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this, "Please search for a doctor first!"
                );

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this doctor?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                int doctorId = Integer.parseInt(
                        txtDoctorId.getText().trim()
                );

                DoctorDAO dao = new DoctorDAO();

                if (dao.deleteDoctor(doctorId)) {

                    JOptionPane.showMessageDialog(
                            this, "Doctor Deleted Successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this, "Doctor Deletion Failed!"
                    );
                }
            }
        });

        // CLEAR BUTTON
        btnClear.addActionListener(e -> clearFields());

        // BACK BUTTON
        btnBack.addActionListener(e -> {
            new Dashboard().setVisible(true);
            dispose();
        });
    }

    // CLEAR DETAILS
    private void clearDetails() {

        txtName.setText("");
        txtSpecialization.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    // CLEAR ALL FIELDS
    private void clearFields() {

        txtDoctorId.setText("");
        clearDetails();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DeleteDoctor().setVisible(true);
        });
    }
}
