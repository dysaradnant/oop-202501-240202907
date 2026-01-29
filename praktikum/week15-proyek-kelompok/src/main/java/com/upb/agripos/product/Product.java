package com.upb.agripos.product;

public class Product {
    private String kode;
    private String nama;
    private String kategori;
    private double harga;
    private int stok;

    public Product(String kode, String nama, String kategori, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setStok(int stok) { this.stok = stok; }
}