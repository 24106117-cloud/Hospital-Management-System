/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */


import dao.AppointmentDAO;
import model.Appointment;

import javax.swing.*;
import java.awt.*;

public class BookAppointment extends JFrame {

    private JTextField txtPatientId, txtDoctorId;
    private JTextField txtDate, txtTime, txtReason;
    private JComboBox<String> cmbStatus;

    public BookAppointment() {

        setTitle("Book Appointment");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Patient ID:"));
        txtPatientId = new JTextField();
        panel.add(txtPatientId);

        panel.add(new JLabel("Doctor ID:"));
        txtDoctorId = new JTextField();
        panel.add(txtDoctorId);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        txtDate = new JTextField();
        panel.add(txtDate);

        panel.add(new JLabel("Time (HH:MM:SS):"));
        txtTime = new JTextField();
        panel.add(txtTime);

        panel.add(new JLabel("Reason:"));
        txtReason = new JTextField();
        panel.add(txtReason);

        panel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(
            new String[]{"Scheduled", "Completed", "Cancelled"}
        );
        panel.add(cmbStatus);

        JButton btnBook = new JButton("Book Appointment");
        panel.add(btnBook);

        JButton btnClear = new JButton("Clear");
        panel.add(btnClear);

        btnBook.addActionListener(e -> bookAppointment());
        btnClear.addActionListener(e -> clearFields());

        add(panel);
        setVisible(true);
    }

    private void bookAppointment() {

        try {
            int patientId = Integer.parseInt(txtPatientId.getText());
            int doctorId = Integer.parseInt(txtDoctorId.getText());

            String date = txtDate.getText().trim();
            String time = txtTime.getText().trim();
            String reason = txtReason.getText().trim();
            String status = cmbStatus.getSelectedItem().toString();

            if (date.isEmpty() || time.isEmpty() || reason.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please fill all fields.");
                return;
            }

            Appointment appointment = new Appointment(
                0, patientId, doctorId, date, time, reason, status
            );

            AppointmentDAO dao = new AppointmentDAO();

            if (dao.addAppointment(appointment)) {
                JOptionPane.showMessageDialog(this,
                    "Appointment booked successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Unable to book appointment.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Patient ID and Doctor ID must be numbers.");
        }
    }

    private void clearFields() {
        txtPatientId.setText("");
        txtDoctorId.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtReason.setText("");
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new BookAppointment();
    }
}
