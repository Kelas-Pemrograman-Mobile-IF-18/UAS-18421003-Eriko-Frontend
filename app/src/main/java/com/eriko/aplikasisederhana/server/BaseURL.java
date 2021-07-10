package com.eriko.aplikasisederhana.server;

public class BaseURL {

    public static String baseUrl = "http://192.168.43.37:5050/";
    public static  String login = baseUrl + "user/login";
    public static  String register = baseUrl + "user/registrasi";


    //film
    public static  String dataFilm = baseUrl + "film/datafilm";
    public static  String editDataFilm = baseUrl + "film/ubah/";
    public static  String hapusData = baseUrl + "film/hapus/";
    public static  String inputFilm = baseUrl + "film/input";

    public static String order = baseUrl + "order/insert";
    public static String dataOrder = baseUrl + "order/lihatTransaksi/";
    public static String dataOrderbyname = baseUrl + "order/dataTransaksi/";
}
