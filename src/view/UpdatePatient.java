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

public class UpdatePatient extends JFrame {

    private JTextField txtPatientId, txtName, txtAge;
    private JTextField txtPhone, txtAddress, txtDisease;
    private JComboBox<String> cmbGender;
    private JButton btnSearch, btnUpdate, btnClear;

    public UpdatePatient() {

        setTitle("Hospital Management System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main Background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 245, 250));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );

        // Heading
        JLabel title = new JLabel("UPDATE PATIENT DETAILS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(new Color(25, 55, 90));

        JLabel subtitle = new JLabel(
            "Search and update patient information"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel heading = new JPanel(new GridLayout(2, 1, 5, 5));
        heading.setOpaque(false);
        heading.add(title);
        heading.add(subtitle);

        mainPanel.add(heading, BorderLayout.NORTH);

        // Form Panel
        JPanel form = new JPanel(new GridLayout(0, 2, 20, 18));
        form.setBackground(Color.WHITE);
        form.setBorder(
            BorderFactory.createEmptyBorder(30, 35, 30, 35)
        );

        // Patient ID
        txtPatientId = new JTextField();
        btnSearch = new JButton("Search Patient");

        // Other Fields
        txtName = new JTextField();
        txtAge = new JTextField();

        cmbGender = new JComboBox<>(
            new String[]{"Male", "Female", "Other"}
        );

        txtPhone = new JTextField();
        txtAddress = new JTextField();
        txtDisease = new JTextField();

        addField(form, "Patient ID", txtPatientId);
        addField(form, "Search", btnSearch);
        addField(form, "Patient Name", txtName);
        addField(form, "Age", txtAge);
        addField(form, "Gender", cmbGender);
        addField(form, "Phone Number", txtPhone);
        addField(form, "Address", txtAddress);
        addField(form, "Disease", txtDisease);

        mainPanel.add(form, BorderLayout.CENTER);

        // Buttons
        btnUpdate = new JButton("Update Patient");
        btnClear = new JButton("Clear");

        styleButton(btnUpdate);
        styleButton(btnClear);

        JPanel buttons = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 15, 10)
        );
        buttons.setOpaque(false);
        buttons.add(btnClear);
        buttons.add(btnUpdate);

        mainPanel.add(buttons, BorderLayout.SOUTH);

        add(mainPanel);
        btnSearch.addActionListener(e -> searchPatient());
        btnUpdate.addActionListener(e -> updatePatient());
    }

    private void addField(JPanel panel, String name, JComponent field) {

        JLabel label = new JLabel(name);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(40, 55, 75));

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(label);
        panel.add(field);
    }

    private void styleButton(JButton button) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(35, 115, 210));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(160, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

private void searchPatient() {

    try {
       int patientId = Integer.parseInt(txtPatientId.getText().trim());
        PatientDAO dao = new PatientDAO();
        Patient patient = dao.getPatientById(patientId);

        if (patient != null) {

            txtName.setText(patient.getPatientName());
            txtAge.setText(String.valueOf(patient.getAge()));
            cmbGender.setSelectedItem(patient.getGender());
            txtPhone.setText(patient.getPhone());
            txtAddress.setText(patient.getAddress());
            txtDisease.setText(patient.getDisease());

            JOptionPane.showMessageDialog(this,
                    "Patient found successfully!");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Patient ID not found!");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Please enter a valid Patient ID!");
    }
}

private void updatePatient() {

    try {
        int patientId = Integer.parseInt(txtPatientId.getText().trim());
        String name = txtName.getText().trim();
        int age = Integer.parseInt(txtAge.getText().trim());
        String gender = cmbGender.getSelectedItem().toString();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        String disease = txtDisease.getText().trim();

        if (name.isEmpty() || phone.isEmpty()
                || address.isEmpty() || disease.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");
            return;
        }

        Patient patient = new Patient(
                patientId, name, age, gender,
                phone, address, disease
        );

        PatientDAO dao = new PatientDAO();

        if (dao.updatePatient(patient)) {

            JOptionPane.showMessageDialog(this,
                    "Patient updated successfully!");

        } else {

            JOptionPane.showMessageDialog(this,
                    "Patient update failed!");
        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Please enter valid Patient ID and Age!");
    }
}


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new UpdatePatient().setVisible(true);
        });
    }
}
