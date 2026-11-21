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

public class UpdateDoctor extends JFrame {

    private JTextField txtDoctorId;
    private JTextField txtName;
    private JTextField txtSpecialization;
    private JTextField txtPhone;
    private JTextField txtEmail;

    public UpdateDoctor() {

        setTitle("Update Doctor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        // Heading
        JLabel title = new JLabel("UPDATE DOCTOR");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headingPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        headingPanel.add(title);

        // Form Panel
        JPanel formPanel = new JPanel(
                new GridLayout(5, 2, 10, 15)
        );

        txtDoctorId = new JTextField();
        txtName = new JTextField();
        txtSpecialization = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();

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
        JButton btnUpdate = new JButton("Update");
        JButton btnClear = new JButton("Clear");
        JButton btnBack = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);
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
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this, "Enter a valid Doctor ID!"
                );
            }
        });

        // UPDATE BUTTON
        btnUpdate.addActionListener(e -> {

            String idText = txtDoctorId.getText().trim();
            String name = txtName.getText().trim();
            String specialization = txtSpecialization.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();

            if (idText.isEmpty() || name.isEmpty()
                    || specialization.isEmpty()
                    || phone.isEmpty() || email.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this, "Please fill all fields!"
                );

                return;
            }

            try {

                int doctorId = Integer.parseInt(idText);

                Doctor doctor = new Doctor(
                        doctorId,
                        name,
                        specialization,
                        phone,
                        email
                );

                DoctorDAO dao = new DoctorDAO();

                if (dao.updateDoctor(doctor)) {

                    JOptionPane.showMessageDialog(
                            this, "Doctor Updated Successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this, "Doctor Update Failed!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this, "Invalid Doctor ID!"
                );
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

    // CLEAR FIELDS
    private void clearFields() {

        txtDoctorId.setText("");
        txtName.setText("");
        txtSpecialization.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new UpdateDoctor().setVisible(true);
        });
    }
}

