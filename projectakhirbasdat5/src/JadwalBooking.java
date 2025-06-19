public class JadwalBooking {
    private String idBooking;
    private String idLapangan;
    private String idPemesan;
    private String tanggal;
    private String jamMulai;
    private String jamSelesai;
    private int durasi;
    private String status;

    // Constructor
    public JadwalBooking(String idBooking, String idLapangan, String idPemesan, String tanggal, String jamMulai, String jamSelesai, int durasi, String status) {
        this.idBooking = idBooking;
        this.idLapangan = idLapangan;
        this.idPemesan = idPemesan;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.durasi = durasi;
        this.status = status;
    }

    // Getter & Setter
    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(String idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}