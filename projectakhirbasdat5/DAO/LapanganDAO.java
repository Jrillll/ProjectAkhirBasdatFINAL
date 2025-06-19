package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LapanganDAO {
    public static void lihatLapangan() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lapangan");

            System.out.println("\n===== Data Lapangan =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id_lapangan")
                        + " | Nama: " + rs.getString("nama_lapangan")
                        + " | Status: " + rs.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}