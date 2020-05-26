package tranthanh.dmt.nhahangversion11.thanhtoan;

public class dong_sp_thanhtoan {
    String anh;
    String ncc;
    int sl;
    String tongtien;
    String name;

    public dong_sp_thanhtoan(String anh, String ncc, int sl, String tongtien, String name) {
        this.anh = anh;
        this.ncc = ncc;
        this.sl = sl;
        this.tongtien = tongtien;
        this.name = name;
    }

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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
