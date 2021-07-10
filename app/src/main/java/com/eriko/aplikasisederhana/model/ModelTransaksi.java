package com.eriko.aplikasisederhana.model;

public class ModelTransaksi {
    String _id, username, judulFilm, Genre, Harga, Jumlah, Total;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getjudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) { this.judulFilm = judulFilm; }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) { this.Genre = genre; }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        Jumlah = jumlah;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
