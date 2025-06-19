package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TransaksiDAO {
    public static void lihatTransaksi() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transaksi");

            System.out.println("\n===== Data Transaksi =====");
            while (rs.next()) {
                System.out.println("ID Transaksi: " + rs.getString("id_transaksi")
                        + " | ID Booking: " + rs.getString("id_booking")
                        + " | Jumlah Bayar: " + rs.getDouble("jumlah_bayar")
                        + " | Status Pembayaran: " + rs.getString("status_pembayaran"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void bayarBooking(String idBooking) {
    Scanner input = new Scanner(System.in);
    try {
        Connection conn = DBConnection.getConnection();

        // Cek booking
        String cekBooking = "SELECT jb.id_lapangan, l.id_jenisolahraga, jo.tarif, jb.durasi FROM jadwal_booking jb "
                          + "JOIN lapangan l ON jb.id_lapangan = l.id_lapangan "
                          + "JOIN jenis_olahraga jo ON l.id_jenisolahraga = jo.id_jenisolahraga "
                          + "WHERE jb.id_booking=? AND jb.status='booking'";
        PreparedStatement psCek = conn.prepareStatement(cekBooking);
        psCek.setString(1, idBooking);
        ResultSet rs = psCek.executeQuery();

        if (!rs.next()) {
            System.out.println("ID Booking tidak ditemukan atau sudah selesai/dibatalkan.");
            return;
        }

        double tarif = rs.getDouble("tarif");
        int durasi = rs.getInt("durasi");
        double totalBayar = tarif * (durasi / 60.0);

        System.out.println("Total yang harus dibayar: " + totalBayar);

        // Input pembayaran
        System.out.print("Jumlah Bayar: ");
        double bayar = input.nextDouble();
        input.nextLine(); // enter
        System.out.print("Metode Pembayaran: ");
        String metode = input.nextLine();
        String waktu = java.time.LocalTime.now().toString();

        // Tentukan status otomatis
        String status = (bayar >= totalBayar) ? "berhasil" : "tidak berhasil";

        // Simpan ke tabel transaksi
        String sql = "INSERT INTO transaksi (id_booking, jumlah_bayar, metode_pembayaran, status_pembayaran, waktu_transaksi) "
                   + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idBooking);
        ps.setDouble(2, bayar);
        ps.setString(3, metode);
        ps.setString(4, status);
        ps.setString(5, waktu);
        ps.executeUpdate();

        System.out.println("Pembayaran " + status + "!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}