package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BookingDAO {

    // Method admin lihat semua booking
    public static void lihatBooking() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jadwal_booking");

            System.out.println("\n===== Data Booking =====");
            while (rs.next()) {
                System.out.println("ID Booking: " + rs.getInt("id_booking")
                        + " | ID Lapangan: " + rs.getInt("id_lapangan")
                        + " | ID Pemesan: " + rs.getInt("id_pemesan")
                        + " | Tanggal: " + rs.getString("tanggal")
                        + " | Jam Mulai: " + rs.getString("jam_mulai")
                        + " | Jam Selesai: " + rs.getString("jam_selesai")
                        + " | Durasi: " + rs.getInt("durasi")
                        + " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method pemesan lihat booking miliknya
    public static void lihatBookingPemesan(String idPemesan) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM jadwal_booking WHERE id_pemesan=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPemesan);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== Data Booking Kamu =====");
            while (rs.next()) {
                System.out.println("ID Booking: " + rs.getInt("id_booking")
                        + " | Tanggal: " + rs.getString("tanggal")
                        + " | Jam Mulai: " + rs.getString("jam_mulai")
                        + " | Jam Selesai: " + rs.getString("jam_selesai")
                        + " | Durasi: " + rs.getInt("durasi")
                        + " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cek jadwal bentrok
    public static boolean cekKetersediaanLapangan(int idLapangan, String tanggal, String jamMulai, String jamSelesai) {
        boolean tersedia = true;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM jadwal_booking " +
                "WHERE id_lapangan=? AND tanggal=? AND status='booking' " +
                "AND ( (jam_mulai < ? AND jam_selesai > ?) )"
            );
            ps.setInt(1, idLapangan);
            ps.setString(2, tanggal);
            ps.setString(3, jamSelesai);
            ps.setString(4, jamMulai);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tersedia = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tersedia;
    }

    // Tambah booking baru
    public static void tambahBooking(String idPemesan) {
        Scanner input = new Scanner(System.in);
        try {
            Connection conn = DBConnection.getConnection();

            // Tampilkan jenis olahraga dulu
            lihatJenisOlahraga();
            System.out.print("Pilih ID Jenis Olahraga: ");
            int idJenis = input.nextInt();
            input.nextLine();

            // Tampilkan lapangan tersedia sesuai jenis
            lihatLapanganByJenis(idJenis);
            System.out.print("Pilih ID Lapangan: ");
            String idLapangan = input.nextLine();

            // Isi jadwal booking
            System.out.print("Tanggal (YYYY-MM-DD): ");
            String tanggal = input.nextLine();
            System.out.print("Jam Mulai (HH:MM:SS): ");
            String jamMulai = input.nextLine();
            System.out.print("Jam Selesai (HH:MM:SS): ");
            String jamSelesai = input.nextLine();
            System.out.print("Durasi (menit): ");
            int durasi = input.nextInt();
            input.nextLine(); // enter

            // Cek jadwal bentrok
            if (cekKetersediaanLapangan(Integer.parseInt(idLapangan), tanggal, jamMulai, jamSelesai)) {
                String sql = "INSERT INTO jadwal_booking (id_lapangan, id_pemesan, tanggal, jam_mulai, jam_selesai, durasi, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?, 'booking')";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, idLapangan);
                ps.setString(2, idPemesan);
                ps.setString(3, tanggal);
                ps.setString(4, jamMulai);
                ps.setString(5, jamSelesai);
                ps.setInt(6, durasi);
                ps.executeUpdate();

                System.out.println("Booking berhasil disimpan!");
            } else {
                System.out.println("Jadwal bentrok! Silakan pilih waktu lain.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tampilkan jenis olahraga
    public static void lihatJenisOlahraga() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jenis_olahraga");

            System.out.println("\n===== Data Jenis Olahraga =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_jenisolahraga")
                        + " | Nama: " + rs.getString("nama_olahraga")
                        + " | Tarif: " + rs.getDouble("tarif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tampilkan lapangan tersedia berdasarkan jenis olahraga
    public static void lihatLapanganByJenis(int idJenis) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM lapangan WHERE id_jenisolahraga=? AND status='tersedia'"
            );
            ps.setInt(1, idJenis);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== Data Lapangan Tersedia =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_lapangan")
                        + " | Nama: " + rs.getString("nama_lapangan")
                        + " | Fasilitas: " + rs.getString("fasilitas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}