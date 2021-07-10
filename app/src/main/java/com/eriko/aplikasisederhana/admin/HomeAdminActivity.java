package com.eriko.aplikasisederhana.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.session.PrefSetting;
import com.eriko.aplikasisederhana.session.SessionManager;
import com.eriko.aplikasisederhana.users.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView CardExit, CardProfile, CardInputFilm, CardTransaksi, CardBeritaFilm, CardJadwalFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);

        CardExit = (CardView) findViewById(R.id.CardExit);
        CardInputFilm = (CardView) findViewById(R.id.CardInputFilm);
        CardTransaksi = (CardView) findViewById(R.id.CardTransaksi);
        CardBeritaFilm =(CardView) findViewById(R.id.CardBeritaFilm);
        CardJadwalFilm =(CardView) findViewById(R.id.CardJadwalFilm);
        CardProfile =(CardView) findViewById(R.id.CardProfile);


        CardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        CardJadwalFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, JadwalFilmActivity.class);
                startActivity(i);
                finish();
            }
        });
        CardBeritaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, BeritaFilmActivity.class);
                startActivity(i);
                finish();
            }
        });
        CardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, PesananUserActivity.class);
                startActivity(i);
                finish();
            }
        });
        CardInputFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputFilmActivity.class);
                startActivity(i);
                finish();
            }
        });
        CardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}