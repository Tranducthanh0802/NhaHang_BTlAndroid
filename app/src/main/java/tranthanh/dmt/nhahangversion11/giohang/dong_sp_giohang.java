package tranthanh.dmt.nhahangversion11.giohang;

import android.content.Context;

import tranthanh.dmt.nhahangversion11.trunggian;

public class dong_sp_giohang implements Comparable<dong_sp_giohang> {
    String anh;
    String Ncc;
    String name;
    String tien;
    int sl=1;
    int luotxem=0;
    Context context;

    public int getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(int luotxem) {
        this.luotxem = luotxem;
    }

    public dong_sp_giohang(String anh, String ncc, String name, String tien, int luotxem) {
        this.anh = anh;
        Ncc = ncc;
        this.name = name;
        this.tien = tien;
        this.luotxem = luotxem;
    }

    public dong_sp_giohang(String anh, String ncc, String name, String tien, int sl, int luotxem) {
        this.anh = anh;
        Ncc = ncc;
        this.name = name;
        this.tien = tien;
        this.sl = sl;
        this.luotxem = luotxem;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

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

    @Override
    public int compareTo(dong_sp_giohang dong_sp_giohang) {

        if (luotxem == dong_sp_giohang.luotxem)
            return 0;
        else if (luotxem > dong_sp_giohang.luotxem)
            return -1;
        else
            return 1;
    }
}