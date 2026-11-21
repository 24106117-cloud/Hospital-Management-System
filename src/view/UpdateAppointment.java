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

public class UpdateAppointment extends JFrame {

    private JTextField txtAppointmentId, txtPatientId, txtDoctorId;
    private JTextField txtDate, txtTime, txtReason;
    private JComboBox<String> cmbStatus;

    private AppointmentDAO dao = new AppointmentDAO();

    public UpdateAppointment() {

        setTitle("Update Appointment");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Appointment ID:"));
        txtAppointmentId = new JTextField();
        panel.add(txtAppointmentId);

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

        JButton btnUpdate = new JButton("Update");
        panel.add(btnUpdate);

        JButton btnClear = new JButton("Clear");
        panel.add(btnClear);

        btnUpdate.addActionListener(e -> updateAppointment());
        btnClear.addActionListener(e -> clearFields());

        add(panel);
        setVisible(true);
    }

    private void updateAppointment() {

        try {
            int appointmentId =
                Integer.parseInt(txtAppointmentId.getText());

            int patientId =
                Integer.parseInt(txtPatientId.getText());

            int doctorId =
                Integer.parseInt(txtDoctorId.getText());

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
                appointmentId,
                patientId,
                doctorId,
                date,
                time,
                reason,
                status
            );

            if (dao.updateAppointment(appointment)) {
                JOptionPane.showMessageDialog(this,
                    "Appointment updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Appointment ID not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numeric IDs.");
        }
    }

    private void clearFields() {
        txtAppointmentId.setText("");
        txtPatientId.setText("");
        txtDoctorId.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtReason.setText("");
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new UpdateAppointment();
    }
}

