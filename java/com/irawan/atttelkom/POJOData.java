package com.irawan.atttelkom;


import java.io.Serializable;


public class POJOData implements Serializable {

    private int IDNO=0;
    private String Judul="";
    private double longf=0;
    private double latf=0;
    private String alamat="";
    private String tgl;
    private String remarks="";
    private int tagstatus=0;

    public int getTagstatus() {
        return tagstatus;
    }

    public void setTagstatus(int tagstatus) {
        this.tagstatus = tagstatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
/*
    public POJOData(int IDNO, String judul, double longf, double latf, String alamat, String tgl) {
        this.IDNO = IDNO;
        Judul = judul;
        this.longf = longf;
        this.latf = latf;
        this.alamat = alamat;
        this.tgl = tgl;
    }*/

    public int getIDNO() {
        return IDNO;
    }

    public void setIDNO(int IDNO) {
        this.IDNO = IDNO;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public double getLongf() {
        return longf;
    }

    public void setLongf(double longf) {
        this.longf = longf;
    }

    public double getLatf() {
        return latf;
    }

    public void setLatf(double latf) {
        this.latf = latf;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
