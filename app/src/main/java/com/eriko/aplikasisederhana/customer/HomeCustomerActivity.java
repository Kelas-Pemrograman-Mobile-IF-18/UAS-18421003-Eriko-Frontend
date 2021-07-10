package com.eriko.aplikasisederhana.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.adapter.AdapterFilm;
import com.eriko.aplikasisederhana.admin.HomeAdminActivity;
import com.eriko.aplikasisederhana.admin.ProfileActivity;
import com.eriko.aplikasisederhana.model.ModelFilm;
import com.eriko.aplikasisederhana.server.BaseURL;
import com.eriko.aplikasisederhana.session.PrefSetting;
import com.eriko.aplikasisederhana.session.SessionManager;
import com.eriko.aplikasisederhana.users.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeCustomerActivity extends AppCompatActivity {
    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, pilihFilm, cardUser, cardKeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        getSupportActionBar().hide();

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();

        session = new SessionManager(HomeCustomerActivity.this);

        prefSetting.isLogin(session, prefs);

        cardExit = (CardView) findViewById(R.id.cardExitUser);
        pilihFilm = (CardView) findViewById(R.id.pilihFilm);
        cardUser = (CardView) findViewById(R.id.cardProfileUser);
        cardKeranjang = (CardView) findViewById(R.id.Keranjanguye);

        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeCustomerActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        pilihFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeCustomerActivity.this, PilihFilmActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeCustomerActivity.this, ProfileUserActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeCustomerActivity.this, KeranjangActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}