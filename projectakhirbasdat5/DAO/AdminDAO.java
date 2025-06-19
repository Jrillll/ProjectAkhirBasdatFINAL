package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {
    // Method untuk cek login admin
    public static boolean cekLoginAdmin(String namaPengguna, String password) {
        boolean loginBerhasil = false;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM admin WHERE nama_pengguna=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namaPengguna);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loginBerhasil = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginBerhasil;
    }
}