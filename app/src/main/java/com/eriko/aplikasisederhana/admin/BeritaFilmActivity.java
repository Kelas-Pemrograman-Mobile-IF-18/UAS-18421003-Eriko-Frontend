package com.eriko.aplikasisederhana.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.eriko.aplikasisederhana.R;

public class BeritaFilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_film);
    }
    public void onBackPressed() {
        Intent i = new Intent(BeritaFilmActivity.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }
}