package com.example.vergencyshop.models;

public class TopSanPham extends SanPham{
    private String soLuong ;

    public TopSanPham(String anhSP, String giaSP, String tenSP, String motaSP, String danhmucSP, String trangthaiSP, String soLuong) {
        super(anhSP, giaSP, tenSP, motaSP, danhmucSP, trangthaiSP);
        this.soLuong = soLuong;
    }

    public TopSanPham(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
