/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */



import dao.PatientDAO;
import javax.swing.*;
import java.awt.*;

public class DeletePatient extends JFrame {

    private JTextField txtPatientId;
    private JButton btnDelete, btnBack;

    public DeletePatient() {

        setTitle("Delete Patient");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblTitle = new JLabel("DELETE PATIENT", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblPatientId = new JLabel("Enter Patient ID:");

        txtPatientId = new JTextField();

        btnDelete = new JButton("Delete Patient");
        btnBack = new JButton("Back");

        add(lblTitle);
        add(lblPatientId);
        add(txtPatientId);
        add(btnDelete);
        add(btnBack);

        btnDelete.addActionListener(e -> deletePatient());

        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void deletePatient() {

        try {

            int patientId = Integer.parseInt(txtPatientId.getText());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this patient?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                PatientDAO dao = new PatientDAO();

                boolean result = dao.deletePatient(patientId);

                if (result) {
                    JOptionPane.showMessageDialog(this,
                            "Patient deleted successfully!");
                    txtPatientId.setText("");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Patient ID not found!");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid Patient ID!");
        }
    }
}
