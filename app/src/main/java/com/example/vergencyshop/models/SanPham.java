package com.example.vergencyshop.models;

import java.io.Serializable;

public class SanPham implements Serializable {

    String anhSP,giaSP,tenSP,idSP,motaSP,loaiSP,sizeSP;

    public SanPham() {
    }

    public SanPham(String anhSP, String giaSP, String tenSP, String idSP, String motaSP, String loaiSP, String sizeSP) {
        this.anhSP = anhSP;
        this.giaSP = giaSP;
        this.tenSP = tenSP;
        this.idSP = idSP;
        this.motaSP = motaSP;
        this.loaiSP = loaiSP;
        this.sizeSP = sizeSP;
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
