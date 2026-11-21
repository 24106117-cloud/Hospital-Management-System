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

import javax.swing.*;
import java.awt.*;

public class DeleteBill extends JFrame {

    private JTextField txtBillId;
    private BillingDAO dao = new BillingDAO();

    public DeleteBill() {

        setTitle("Delete Bill");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Bill ID:"));

        txtBillId = new JTextField();
        panel.add(txtBillId);

        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        panel.add(btnDelete);
        panel.add(btnClear);

        btnDelete.addActionListener(e -> deleteBill());
        btnClear.addActionListener(e -> txtBillId.setText(""));

        add(panel);
        setVisible(true);
    }

    private void deleteBill() {

        try {
            int billId = Integer.parseInt(txtBillId.getText());

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this bill?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                if (dao.deleteBill(billId)) {
                    JOptionPane.showMessageDialog(this,
                        "Bill deleted successfully!");
                    txtBillId.setText("");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Bill ID not found.");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid Bill ID.");
        }
    }

    public static void main(String[] args) {
        new DeleteBill();
    }
}
