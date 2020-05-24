package tranthanh.dmt.nhahangversion11.chude;

public class dong_hoahoa_ofchude {
    private String anh;
    private String name;
    private String NCc;
    private String tien;

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNCc() {
        return NCc;
    }

    public void setNCc(String NCc) {
        this.NCc = NCc;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public dong_hoahoa_ofchude(String anh, String name) {
        this.anh = anh;
        this.name = name;
    }

    public dong_hoahoa_ofchude(String anh, String name, String NCc, String tien) {
        this.anh = anh;
        this.name = name;
        this.NCc = NCc;
        this.tien = tien;
    }
}
