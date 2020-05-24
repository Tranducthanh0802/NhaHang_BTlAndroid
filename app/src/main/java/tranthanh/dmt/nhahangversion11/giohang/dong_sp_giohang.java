package tranthanh.dmt.nhahangversion11.giohang;

public class dong_sp_giohang {
    String anh;
    String Ncc;
    String name;
    String tien;

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getNcc() {
        return Ncc;
    }

    public void setNcc(String ncc) {
        Ncc = ncc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public dong_sp_giohang(String anh, String name, String ncc, String tien) {
        this.anh = anh;
        Ncc = ncc;
        this.name = name;
        this.tien = tien;
    }
}