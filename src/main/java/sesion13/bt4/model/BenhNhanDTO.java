package sesion13.bt4.model;

import java.util.ArrayList;
import java.util.List;

public class BenhNhanDTO {
    private String maBenhNhan;
    private String tenBenhNhan;
    private List<DichVu> dsDichVu;

    public BenhNhanDTO(String maBenhNhan, String tenBenhNhan) {
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        this.dsDichVu = new ArrayList<>();
    }

    public void addDichVu(DichVu dv) {
        this.dsDichVu.add(dv);
    }

    public String getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(String maBenhNhan) { this.maBenhNhan = maBenhNhan; }
    public String getTenBenhNhan() { return tenBenhNhan; }
    public void setTenBenhNhan(String tenBenhNhan) { this.tenBenhNhan = tenBenhNhan; }
    public List<DichVu> getDsDichVu() { return dsDichVu; }
    public void setDsDichVu(List<DichVu> dsDichVu) { this.dsDichVu = dsDichVu; }
}