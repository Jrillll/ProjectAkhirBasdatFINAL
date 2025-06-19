public class Transaksi {
    private String idTransaksi;
    private String idBooking;
    private double jumlahBayar;
    private String metodePembayaran;
    private String statusPembayaran;
    private String waktuTransaksi;
    private String buktiPembayaran;

    // Constructor
    public Transaksi(String idTransaksi, String idBooking, double jumlahBayar, String metodePembayaran, String statusPembayaran, String waktuTransaksi, String buktiPembayaran) {
        this.idTransaksi = idTransaksi;
        this.idBooking = idBooking;
        this.jumlahBayar = jumlahBayar;
        this.metodePembayaran = metodePembayaran;
        this.statusPembayaran = statusPembayaran;
        this.waktuTransaksi = waktuTransaksi;
        this.buktiPembayaran = buktiPembayaran;
    }

    // Getter & Setter
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public double getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(String waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    public String getBuktiPembayaran() {
        return buktiPembayaran;
    }

    public void setBuktiPembayaran(String buktiPembayaran) {
        this.buktiPembayaran = buktiPembayaran;
    }
}