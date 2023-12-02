package com.example.vergencyshop.models;

import java.io.Serializable;

public class SanPham implements Serializable {

    String anhSP,giaSP,tenSP,idSP,motaSP,loaiSP,sizeSP,danhmuc;

    String soluongSP;

    public SanPham() {
    }


    public SanPham(String anhSP, String giaSP, String tenSP, String idSP, String motaSP, String loaiSP, String sizeSP, String soluongSP) {


        this.anhSP = anhSP;
        this.giaSP = giaSP;
        this.tenSP = tenSP;
        this.idSP = idSP;
        this.motaSP = motaSP;
        this.loaiSP = loaiSP;
        this.sizeSP = sizeSP;

        this.soluongSP = soluongSP;

        this.danhmuc = danhmuc;

    }

    public String getSoluongSP() {
        return soluongSP;
    }

    public void setSoluongSP(String soluongSP) {
        this.soluongSP = soluongSP;
    }

    public String getMotaSP() {
        return motaSP;
    }

    public void setMotaSP(String motaSP) {
        this.motaSP = motaSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getSizeSP() {
        return sizeSP;
    }

    public void setSizeSP(String sizeSP) {
        this.sizeSP = sizeSP;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }
}
