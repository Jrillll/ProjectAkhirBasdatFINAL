import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import menu.MenuAdmin;
import menu.MenuPemesan;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean jalan = true;

        while (jalan) {
            System.out.println("\n===== MENU LOGIN =====");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Pelanggan");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = input.nextInt();
            input.nextLine(); // konsumsi enter

            switch (opsi) {
                case 1:
                    // Login Admin
                    System.out.print("Nama Admin: ");
                    String namaPengguna = input.nextLine();
                    System.out.print("Password: ");
                    String password = input.nextLine();

                    try {
                        if (DAO.AdminDAO.cekLoginAdmin(namaPengguna, password)) {
                            System.out.println("Login berhasil!");
                            MenuAdmin.tampilMenu();
                        } else {
                            System.out.println("Nama Admin atau Password salah!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // Login Pemesan
                    System.out.println("\n===== LOGIN PELANGGAN =====");
                    System.out.println("1. Registrasi Pemesan Baru");
                    System.out.println("2. Login Pakai Nama");
                    System.out.print("Pilih opsi: ");
                    int subOpsi = input.nextInt();
                    input.nextLine(); // konsumsi enter

                    if (subOpsi == 1) {
                        // Input data pemesan baru
                        System.out.print("Nama Pemesan: ");
                        String nama = input.nextLine();
                        System.out.print("No Telepon: ");
                        String telp = input.nextLine();
                        System.out.print("Email: ");
                        String email = input.nextLine();
                        System.out.print("Alamat: ");
                        String alamat = input.nextLine();

                        // Simpan ke DB
                        try {
                            Connection conn = DAO.DBConnection.getConnection();
                            String sql = "INSERT INTO pemesan (nama_pemesan, no_telepon, email, alamat, id_roleuser) VALUES (?, ?, ?, ?, 2)";
                            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                            ps.setString(1, nama);
                            ps.setString(2, telp);
                            ps.setString(3, email);
                            ps.setString(4, alamat);
                            ps.executeUpdate();

                            // Ambil id pemesan barusan
                            ResultSet rs = ps.getGeneratedKeys();
                            if (rs.next()) {
                                int idBaru = rs.getInt(1);
                                System.out.println("Data pemesan berhasil disimpan dengan ID: " + idBaru);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (subOpsi == 2) {
                        System.out.print("Masukkan Nama Pemesan: ");
                        String namaCari = input.nextLine();

                        try {
                            Connection conn = DAO.DBConnection.getConnection();
                            String sqlCek = "SELECT id_pemesan FROM pemesan WHERE nama_pemesan=?";
                            PreparedStatement ps = conn.prepareStatement(sqlCek);
                            ps.setString(1, namaCari);
                            ResultSet rs = ps.executeQuery();

                            if (rs.next()) {
                                int idPemesan = rs.getInt("id_pemesan");
                                System.out.println("Login sebagai " + namaCari + " berhasil!");
                                menu.MenuPemesan.tampilMenu(String.valueOf(idPemesan));
                            } else {
                                System.out.println("Nama Pemesan tidak ditemukan!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Pilihan tidak tersedia!");
                    }
                    break;


                case 0:
                    // Keluar program
                    jalan = false;
                    System.out.println("Keluar dari program.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia!");
            }
        }
    }
}