public class Admin {
    private String idAdmin;
    private String namaPengguna;
    private String password;
    private String idRoleUser;

    // Constructor
    public Admin(String idAdmin, String namaPengguna, String password, String idRoleUser) {
        this.idAdmin = idAdmin;
        this.namaPengguna = namaPengguna;
        this.password = password;
        this.idRoleUser = idRoleUser;
    }

    // Getter & Setter
    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdRoleUser() {
        return idRoleUser;
    }

    public void setIdRoleUser(String idRoleUser) {
        this.idRoleUser = idRoleUser;
    }
}