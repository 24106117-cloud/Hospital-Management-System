/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author WELCOME
 */


import connection.DBConnection;
import model.Billing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    // ADD BILL
    public boolean addBill(Billing bill) {

        String sql = "INSERT INTO bills "
                + "(patient_id, total_amount, payment_date, "
                + "payment_method, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bill.getPatientId());
            ps.setDouble(2, bill.getTotalAmount());
            ps.setString(3, bill.getPaymentDate());
            ps.setString(4, bill.getPaymentMethod());
            ps.setString(5, bill.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // VIEW ALL BILLS
    public List<Billing> getAllBills() {

        List<Billing> bills = new ArrayList<>();

        String sql = "SELECT * FROM bills";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Billing bill = new Billing(
                    rs.getInt("bill_id"),
                    rs.getInt("patient_id"),
                    rs.getDouble("total_amount"),
                    rs.getDate("payment_date").toString(),
                    rs.getString("payment_method"),
                    rs.getString("status")
                );

                bills.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    // GET BILL BY ID
    public Billing getBillById(int billId) {

        String sql = "SELECT * FROM bills WHERE bill_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Billing(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getDouble("total_amount"),
                        rs.getDate("payment_date").toString(),
                        rs.getString("payment_method"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE BILL
    public boolean updateBill(Billing bill) {

        String sql = "UPDATE bills SET patient_id=?, total_amount=?, "
                + "payment_date=?, payment_method=?, status=? "
                + "WHERE bill_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bill.getPatientId());
            ps.setDouble(2, bill.getTotalAmount());
            ps.setString(3, bill.getPaymentDate());
            ps.setString(4, bill.getPaymentMethod());
            ps.setString(5, bill.getStatus());
            ps.setInt(6, bill.getBillId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE BILL
    public boolean deleteBill(int billId) {

        String sql = "DELETE FROM bills WHERE bill_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
