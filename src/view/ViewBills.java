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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewBills extends JFrame {

    private JTable table;

    public ViewBills() {

        setTitle("View Bills");
        setSize(850, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
            "Bill ID",
            "Patient ID",
            "Total Amount",
            "Payment Date",
            "Payment Method",
            "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        BillingDAO dao = new BillingDAO();
        List<Billing> bills = dao.getAllBills();

        for (Billing b : bills) {

            model.addRow(new Object[]{
                b.getBillId(),
                b.getPatientId(),
                b.getTotalAmount(),
                b.getPaymentDate(),
                b.getPaymentMethod(),
                b.getStatus()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewBills();
    }
}
