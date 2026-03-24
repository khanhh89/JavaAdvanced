package sesion13.bt4.model;

public class DichVu {
    private String maDichVu;
    private String tenDichVu;

    public DichVu(String maDichVu, String tenDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
    }

    public String getMaDichVu() { return maDichVu; }
    public void setMaDichVu(String maDichVu) { this.maDichVu = maDichVu; }
    public String getTenDichVu() { return tenDichVu; }
    public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }

    @Override
    public String toString() {
        return " - [" + maDichVu + "] " + tenDichVu;
    }
}
