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

public class AddDoctor extends JFrame {

    private JTextField txtName, txtSpecialization, txtPhone, txtEmail;

    public AddDoctor() {

        setTitle("Add Doctor");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        // Heading
        JLabel title = new JLabel("DOCTOR REGISTRATION");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel headingPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        headingPanel.add(title);

        // Form Fields
        JPanel formPanel = new JPanel(
                new GridLayout(4, 2, 10, 15)
        );

        txtName = new JTextField();
        txtSpecialization = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();

        formPanel.add(new JLabel("Doctor Name:"));
        formPanel.add(txtName);

        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(txtSpecialization);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);

        // Buttons
        JButton btnSave = new JButton("Save");
        JButton btnClear = new JButton("Clear");
        JButton btnBack = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);

        // Add Panels
        mainPanel.add(headingPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);

        // SAVE BUTTON
        btnSave.addActionListener(e -> {

            String name = txtName.getText().trim();
            String specialization = txtSpecialization.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();

            if (name.isEmpty() || specialization.isEmpty()
                    || phone.isEmpty() || email.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields!"
                );

                return;
            }

            Doctor doctor = new Doctor(
                    0, name, specialization, phone, email
            );

            DoctorDAO dao = new DoctorDAO();

            if (dao.addDoctor(doctor)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor Added Successfully!"
                );

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to Add Doctor!"
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

    // CLEAR FIELDS METHOD
    private void clearFields() {

        txtName.setText("");
        txtSpecialization.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddDoctor().setVisible(true);
        });
    }
}

