public class Lapangan {
    private String idLapangan;
    private String idJenisOlahraga;
    private String namaLapangan;
    private String fasilitas;
    private String status;

    // Constructor
    public Lapangan(String idLapangan, String idJenisOlahraga, String namaLapangan, String fasilitas, String status) {
        this.idLapangan = idLapangan;
        this.idJenisOlahraga = idJenisOlahraga;
        this.namaLapangan = namaLapangan;
        this.fasilitas = fasilitas;
        this.status = status;
    }

    // Getter & Setter
    public String getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(String idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getIdJenisOlahraga() {
        return idJenisOlahraga;
    }

    public void setIdJenisOlahraga(String idJenisOlahraga) {
        this.idJenisOlahraga = idJenisOlahraga;
    }

    public String getNamaLapangan() {
        return namaLapangan;
    }

    public void setNamaLapangan(String namaLapangan) {
        this.namaLapangan = namaLapangan;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}