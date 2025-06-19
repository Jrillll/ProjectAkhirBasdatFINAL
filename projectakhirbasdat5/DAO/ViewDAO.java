package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewDAO {
    public static void lihatViewDaftarBooking() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM view_daftar_booking");

            System.out.println("\n===== Daftar Booking (VIEW) =====");
            while (rs.next()) {
                System.out.println("Booking: " + rs.getInt("id_booking")
                    + " | Pemesan: " + rs.getString("nama_pemesan")
                    + " | Lapangan: " + rs.getString("nama_lapangan")
                    + " | Olahraga: " + rs.getString("nama_olahraga")
                    + " | Tanggal: " + rs.getString("tanggal")
                    + " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
