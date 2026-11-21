/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */




import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Hospital Management System");
        setSize(600, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(18, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Heading
        JLabel lblTitle = new JLabel(
                "HOSPITAL MANAGEMENT SYSTEM",
                JLabel.CENTER
        );
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        // Patient Buttons
        JButton btnAdd = new JButton("Add Patient");
        JButton btnView = new JButton("View Patients");
        JButton btnUpdate = new JButton("Update Patient");
        JButton btnDelete = new JButton("Delete Patient");

        // Doctor Buttons
        JButton btnAddDoctor = new JButton("Add Doctor");
        JButton btnViewDoctor = new JButton("View Doctors");
        JButton btnUpdateDoctor = new JButton("Update Doctor");
        JButton btnDeleteDoctor = new JButton("Delete Doctor");

        // Appointment Buttons
        JButton btnBookAppointment = new JButton("Book Appointment");
        JButton btnViewAppointments = new JButton("View Appointments");
        JButton btnUpdateAppointment = new JButton("Update Appointment");
        JButton btnDeleteAppointment = new JButton("Delete Appointment");

        // Billing Buttons
        JButton btnAddBill = new JButton("Add Bill");
        JButton btnViewBills = new JButton("View Bills");
        JButton btnUpdateBill = new JButton("Update Bill");
        JButton btnDeleteBill = new JButton("Delete Bill");

        // Exit Button
        JButton btnExit = new JButton("Exit");

        // Add Components
        panel.add(lblTitle);

        panel.add(btnAdd);
        panel.add(btnView);
        panel.add(btnUpdate);
        panel.add(btnDelete);

        panel.add(btnAddDoctor);
        panel.add(btnViewDoctor);
        panel.add(btnUpdateDoctor);
        panel.add(btnDeleteDoctor);

        panel.add(btnBookAppointment);
        panel.add(btnViewAppointments);
        panel.add(btnUpdateAppointment);
        panel.add(btnDeleteAppointment);

        panel.add(btnAddBill);
        panel.add(btnViewBills);
        panel.add(btnUpdateBill);
        panel.add(btnDeleteBill);

        panel.add(btnExit);

        // Patient Actions
        btnAdd.addActionListener(e -> {
    PatientPage page = new PatientPage();
    page.setVisible(true);
});
        btnView.addActionListener(e -> new ViewPatients());
        btnUpdate.addActionListener(e -> {
    UpdatePatient page = new UpdatePatient();
    page.setVisible(true);
});
        btnDelete.addActionListener(e -> new DeletePatient());

        // Doctor Actions
btnAddDoctor.addActionListener(e -> {
    AddDoctor page = new AddDoctor();
    page.setVisible(true);
});
btnViewDoctor.addActionListener(e -> {
    ViewDoctors page = new ViewDoctors();
    page.setVisible(true);
});
btnUpdateDoctor.addActionListener(e -> {
    UpdateDoctor page = new UpdateDoctor();
    page.setVisible(true);
});
       btnDeleteDoctor.addActionListener(e -> {
    DeleteDoctor page = new DeleteDoctor();
    page.setVisible(true);
});

        // Appointment Actions
        btnBookAppointment.addActionListener(e -> new BookAppointment());
        btnViewAppointments.addActionListener(e -> new ViewAppointments());
        btnUpdateAppointment.addActionListener(e -> new UpdateAppointment());
        btnDeleteAppointment.addActionListener(e -> new DeleteAppointment());

        // Billing Actions
        btnAddBill.addActionListener(e -> new AddBill());
        btnViewBills.addActionListener(e -> new ViewBills());
        btnUpdateBill.addActionListener(e -> new UpdateBill());
        btnDeleteBill.addActionListener(e -> new DeleteBill());

        // Exit Action
        btnExit.addActionListener(e -> System.exit(0));

        add(new JScrollPane(panel));

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}


