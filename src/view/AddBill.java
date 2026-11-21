/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author WELCOME
 */

import dao.BillingDAO;
import model.Billing;

import javax.swing.*;
import java.awt.*;

public class AddBill extends JFrame {

    private JTextField txtPatientId, txtAmount, txtDate;
    private JComboBox<String> cmbPaymentMethod, cmbStatus;

    public AddBill() {

        setTitle("Add Bill");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Patient ID:"));
        txtPatientId = new JTextField();
        panel.add(txtPatientId);

        panel.add(new JLabel("Total Amount:"));
        txtAmount = new JTextField();
        panel.add(txtAmount);

        panel.add(new JLabel("Payment Date (YYYY-MM-DD):"));
        txtDate = new JTextField();
        panel.add(txtDate);

        panel.add(new JLabel("Payment Method:"));
        cmbPaymentMethod = new JComboBox<>(
            new String[]{"Cash", "UPI", "Card", "Net Banking"}
        );
        panel.add(cmbPaymentMethod);

        panel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(
            new String[]{"Paid", "Pending"}
        );
        panel.add(cmbStatus);

        JButton btnAdd = new JButton("Add Bill");
        JButton btnClear = new JButton("Clear");

        panel.add(btnAdd);
        panel.add(btnClear);

        btnAdd.addActionListener(e -> addBill());
        btnClear.addActionListener(e -> clearFields());

        add(panel);
        setVisible(true);
    }

    private void addBill() {

        try {
            int patientId = Integer.parseInt(txtPatientId.getText());
            double amount = Double.parseDouble(txtAmount.getText());

            String date = txtDate.getText().trim();
            String method = cmbPaymentMethod.getSelectedItem().toString();
            String status = cmbStatus.getSelectedItem().toString();

            if (date.isEmpty() || amount <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Please enter valid bill details.");
                return;
            }

            Billing bill = new Billing(
                0, patientId, amount, date, method, status
            );

            BillingDAO dao = new BillingDAO();

            if (dao.addBill(bill)) {
                JOptionPane.showMessageDialog(this,
                    "Bill added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Unable to add bill.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Enter valid Patient ID and Amount.");
        }
    }

    private void clearFields() {
        txtPatientId.setText("");
        txtAmount.setText("");
        txtDate.setText("");
        cmbPaymentMethod.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AddBill();
    }
}
