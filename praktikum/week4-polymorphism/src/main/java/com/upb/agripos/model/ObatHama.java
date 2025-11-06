package com.upb.agripos.model;

public class ObatHama extends Produk {
    private String bahanAktif;
    private String jenisHama;

    public ObatHama(String kode, String nama, double harga, int stok, String bahanAktif, String jenisHama) {
        super(kode, nama, harga, stok);
        this.bahanAktif = bahanAktif;
        this.jenisHama = jenisHama;
    }

    @Override
    public String getInfo() {
        return "Obat Hama: " + super.getInfo() +
                ", Bahan Aktif: " + bahanAktif +
                ", Untuk Hama: " + jenisHama;
    }
}
