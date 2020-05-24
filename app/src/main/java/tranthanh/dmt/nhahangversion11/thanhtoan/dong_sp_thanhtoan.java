package tranthanh.dmt.nhahangversion11.thanhtoan;

public class dong_sp_thanhtoan {
    String anh;
    String ncc;
    String sl;
    String tongtien;

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public dong_sp_thanhtoan(String anh, String ncc, String sl, String tongtien) {
        this.anh = anh;
        this.ncc = ncc;
        this.sl = sl;
        this.tongtien = tongtien;
    }
}
