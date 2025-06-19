package menu;

import java.util.Scanner;

public class MenuAdmin {
    public static void tampilMenu() {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Lihat Data Lapangan");
            System.out.println("2. Lihat Data Jadwal Booking");
            System.out.println("3. Lihat Data Transaksi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    DAO.LapanganDAO.lihatLapangan();
                    break;
                case 2:
                    DAO.BookingDAO.lihatBooking();
                    break;
                case 3:
                    DAO.TransaksiDAO.lihatTransaksi();
                    break;
                case 4:
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia!");
            }
        }
    }
}