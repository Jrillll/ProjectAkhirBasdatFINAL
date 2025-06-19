public class Pemesan {
    private String idPemesan;
    private String idRoleUser;
    private String namaPemesan;
    private String noTelepon;
    private String email;
    private String alamat;

    // Constructor
    public Pemesan(String idPemesan, String idRoleUser, String namaPemesan, String noTelepon, String email, String alamat) {
        this.idPemesan = idPemesan;
        this.idRoleUser = idRoleUser;
        this.namaPemesan = namaPemesan;
        this.noTelepon = noTelepon;
        this.email = email;
        this.alamat = alamat;
    }

    // Getter & Setter
    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(String idRoleUser) {
        this.idRoleUser = idRoleUser;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}