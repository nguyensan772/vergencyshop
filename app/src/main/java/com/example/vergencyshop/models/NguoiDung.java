package com.example.vergencyshop.models;

public class NguoiDung {


    private String hoTen;
    private String gioiTinh;
    private String soDienThoai ;
    private String diaChi;
    private String anh ;
    private String taiKhoan ;
    private String matKhau ;


    public NguoiDung( String hoTen, String gioiTinh, String soDienThoai, String diaChi, String anh, String taiKhoan, String matKhau) {

        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.anh = anh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public NguoiDung() {
    }

    public NguoiDung( String taiKhoan, String matKhau) {

        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }



    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
