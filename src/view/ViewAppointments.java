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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAppointments extends JFrame {

    private JTable table;

    public ViewAppointments() {

        setTitle("View Appointments");
        setSize(850, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
            "Appointment ID",
            "Patient ID",
            "Doctor ID",
            "Date",
            "Time",
            "Reason",
            "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> appointments = dao.getAllAppointments();

        for (Appointment a : appointments) {
            model.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getDoctorId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getReason(),
                a.getStatus()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewAppointments();
    }
}

