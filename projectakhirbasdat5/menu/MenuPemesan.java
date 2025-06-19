package menu;

import DAO.BookingDAO;
import DAO.TransaksiDAO;

import java.util.Scanner;

public class MenuPemesan {

    public static void tampilMenu(String idPemesan) {
        Scanner input = new Scanner(System.in);
        boolean lanjut = true;

        while (lanjut) {
            System.out.println("\n===== MENU PEMESAN =====");
            System.out.println("1. Lihat Data Booking Saya");
            System.out.println("2. Booking Lapangan");
            System.out.println("3. Lakukan Pembayaran");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");
            int pilih = input.nextInt();
            input.nextLine(); // konsumsi enter

            switch (pilih) {
                case 1:
                    BookingDAO.lihatBookingPemesan(idPemesan);
                    break;
                case 2:
                    // Tampilkan jenis olahraga
                    BookingDAO.lihatJenisOlahraga();

                    System.out.print("Pilih ID Jenis Olahraga: ");
                    int idJenis = input.nextInt();

                    // Tampilkan lapangan tersedia untuk jenis olahraga itu
                    BookingDAO.lihatLapanganByJenis(idJenis);

                    // Lanjutkan proses booking pilih lapangan, tanggal, jam, dll
                    BookingDAO.tambahBooking(idPemesan);
                    break;
                case 3:
                    System.out.print("Masukkan ID Booking yang ingin dibayar: ");
                    String idBooking = input.nextLine();
                    TransaksiDAO.bayarBooking(idBooking);
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