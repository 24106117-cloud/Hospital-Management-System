/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package hospitalmanagementsystem;

import java.sql.Connection;
import javax.swing.SwingUtilities;

public class HospitalManagementSystem {

    public static void main(String[] args) {

        Connection con = connection.DBConnection.getConnection();

        if (con != null) {

            System.out.println("Hospital Management System Ready!");

            SwingUtilities.invokeLater(() -> {
                new view.PatientPage().setVisible(true);
            });

        }
    }
}
