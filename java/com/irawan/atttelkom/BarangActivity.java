package com.irawan.atttelkom;

import java.io.Serializable;

public class BarangActivity implements Serializable {

    private int id=0;

    private String kode="";

    private String nama="";

    private Double harga_jual=0.0;

    private Double harga_beli=0.0;

    private Double disc1=0.0;



    public Double getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(Double harga_beli) {
        this.harga_beli = harga_beli;
    }

    public Double getDisc1() {
        return disc1;
    }

    public void setDisc1(Double disc1) {
        this.disc1 = disc1;
    }

    public int getID() {

        return id;

    }

    public void setID(int id) {

        this.id = id;

    }


    public void setKode(String kode) {

        this.kode = kode;

    }


    public String getKode() {

        return kode;

    }



    public String getNama() {

        return nama;

    }

    public void setNama(String nama) {

        this.nama = nama;

    }


    public Double getHarga_jual() {

        return harga_jual;

    }

    public void setHarga_jual(Double harga_jual) {

        this.harga_jual = harga_jual;

    }



/*
        @Override

        public String toString() {

            return "Barang{" +
                    "id='" + id + '\'' +
                    ", kode='" + kode + '\'' +
                    ", nama='" + nama + '\'' +
                    ", harga='" + harga + '\'' +
                    ", harga_beli='" + harga_beli + '\'' +
                    '}';

        }
*/
}



