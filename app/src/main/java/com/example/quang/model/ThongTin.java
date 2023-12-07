package com.example.quang.model;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class ThongTin implements Serializable {
    int id;
    String hoVaTen;
    String tenDinhDanh;
    String sinhNhat;
    String thongTinThem;
    String gmail;
    private byte [] avt;

    public ThongTin(int id,String hoVaTen, String tenDinhDanh, String sinhNhat, String thongTinThem, String gmail, byte[] avt) {
        this.id = id;
        this.hoVaTen = hoVaTen;
        this.tenDinhDanh = tenDinhDanh;
        this.sinhNhat = sinhNhat;
        this.thongTinThem = thongTinThem;
        this.gmail = gmail;
        this.avt = avt;
    }

    public ThongTin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getTenDinhDanh() {
        return tenDinhDanh;
    }

    public void setTenDinhDanh(String tenDinhDanh) {
        this.tenDinhDanh = tenDinhDanh;
    }

    public String getSinhNhat() {
        return sinhNhat;
    }

    public void setSinhNhat(String sinhNhat) {
        this.sinhNhat = sinhNhat;
    }

    public String getThongTinThem() {
        return thongTinThem;
    }

    public void setThongTinThem(String thongTinThem) {
        this.thongTinThem = thongTinThem;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public byte[] getAvt() {
        return avt;
    }

    public void setAvt(byte[] avt) {
        this.avt = avt;
    }
}
