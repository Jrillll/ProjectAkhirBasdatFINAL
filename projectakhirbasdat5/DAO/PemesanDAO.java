package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PemesanDAO {
    public static String tambahPemesan() {
        Scanner input = new Scanner(System.in);
        String idPemesanBaru = "";

        try {
            Connection conn = DBConnection.getConnection();

            System.out.print("Nama Pemesan: ");
            String nama = input.nextLine();
            System.out.print("No Telepon: ");
            String nohp = input.nextLine();
            System.out.print("Email: ");
            String email = input.nextLine();
            System.out.print("Alamat: ");
            String alamat = input.nextLine();

            // Simpan data pemesan
            String sql = "INSERT INTO pemesan (id_roleuser, nama_pemesan, no_telepon, email, alamat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "R002");
            ps.setString(2, nama);
            ps.setString(3, nohp);
            ps.setString(4, email);
            ps.setString(5, alamat);
            ps.executeUpdate();

            // Ambil id_pemesan terakhir
            String sqlGetLast = "SELECT id_pemesan FROM pemesan ORDER BY id_pemesan DESC LIMIT 1";
            PreparedStatement psLast = conn.prepareStatement(sqlGetLast);
            ResultSet rsLast = psLast.executeQuery();
            if (rsLast.next()) {
                idPemesanBaru = rsLast.getString("id_pemesan");
            }

            System.out.println("Data pemesan berhasil disimpan dengan ID: " + idPemesanBaru);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idPemesanBaru; // kembalikan id_pemesan nya ke MainApp
    }
}