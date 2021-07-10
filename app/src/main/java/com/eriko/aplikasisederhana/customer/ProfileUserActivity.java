package com.eriko.aplikasisederhana.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.admin.HomeAdminActivity;
import com.eriko.aplikasisederhana.admin.ProfileActivity;
import com.eriko.aplikasisederhana.session.PrefSetting;

public class ProfileUserActivity extends AppCompatActivity {
    TextView txtUserName, txtNamaLengkap, txtEmail, txtNotelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        getSupportActionBar().setTitle("Profile User");

        txtUserName = (TextView) findViewById(R.id.userName);
        txtNamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        txtEmail = (TextView) findViewById(R.id.email);
        txtNotelp = (TextView) findViewById(R.id.noTelp);

        txtUserName.setText(PrefSetting.userName);
        txtNamaLengkap.setText(PrefSetting.namaLengkap);
        txtEmail.setText(PrefSetting.email);
        txtNotelp.setText(PrefSetting.noTelp);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ProfileUserActivity.this, HomeCustomerActivity.class);
        startActivity(i);
        finish();
    }
}