/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;
import dao.PatientDAO;
import model.Patient;

/**
 *
 * @author WELCOME
 */

import javax.swing.*;
import java.awt.*;

public class PatientPage extends JFrame {

    public PatientPage() {

        setTitle("Hospital Management System");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("PATIENT REGISTRATION");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(130, 25, 350, 40);
        panel.add(title);

        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(70, 100, 120, 30);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(220, 100, 250, 30);
        panel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(70, 150, 120, 30);
        panel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(220, 150, 250, 30);
        panel.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(70, 200, 120, 30);
        panel.add(genderLabel);

        String[] genders = {"Select", "Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setBounds(220, 200, 250, 30);
        panel.add(genderBox);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(70, 250, 120, 30);
        panel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(220, 250, 250, 30);
        panel.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(70, 300, 120, 30);
        panel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(220, 300, 250, 30);
        panel.add(addressField);

        JLabel diseaseLabel = new JLabel("Disease:");
        diseaseLabel.setBounds(70, 350, 120, 30);
        panel.add(diseaseLabel);

        JTextField diseaseField = new JTextField();
        diseaseField.setBounds(220, 350, 250, 30);
        panel.add(diseaseField);

        JButton saveButton = new JButton("Save Patient");
        saveButton.setBounds(220, 420, 150, 40);
        panel.add(saveButton);
        saveButton.addActionListener(e -> {

    try {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderBox.getSelectedItem().toString();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String disease = diseaseField.getText();

        if (name.isEmpty() || phone.isEmpty()
                || address.isEmpty() || disease.isEmpty()
                || gender.equals("Select")) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");
            return;
        }

        Patient patient = new Patient(
                0, name, age, gender, phone, address, disease
        );

        PatientDAO dao = new PatientDAO();

        if (dao.addPatient(patient)) {
            JOptionPane.showMessageDialog(this,
                    "Patient Registered Successfully!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Registration Failed!");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Please enter a valid age!");
    }
});

        add(panel);
    }

    public static void main(String[] args) {
        new PatientPage().setVisible(true);
    }
}
