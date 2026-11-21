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

import javax.swing.*;
import java.awt.*;

public class DeleteAppointment extends JFrame {

    private JTextField txtAppointmentId;
    private AppointmentDAO dao = new AppointmentDAO();

    public DeleteAppointment() {

        setTitle("Delete Appointment");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Appointment ID:"));

        txtAppointmentId = new JTextField();
        panel.add(txtAppointmentId);

        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        panel.add(btnDelete);
        panel.add(btnClear);

        btnDelete.addActionListener(e -> deleteAppointment());
        btnClear.addActionListener(e -> txtAppointmentId.setText(""));

        add(panel);
        setVisible(true);
    }

    private void deleteAppointment() {

        try {
            int appointmentId =
                Integer.parseInt(txtAppointmentId.getText());

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this appointment?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                if (dao.deleteAppointment(appointmentId)) {
                    JOptionPane.showMessageDialog(this,
                        "Appointment deleted successfully!");
                    txtAppointmentId.setText("");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Appointment ID not found.");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid Appointment ID.");
        }
    }

    public static void main(String[] args) {
        new DeleteAppointment();
    }
}
