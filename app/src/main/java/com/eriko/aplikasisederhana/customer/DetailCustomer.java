package com.eriko.aplikasisederhana.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.server.BaseURL;
import com.eriko.aplikasisederhana.session.PrefSetting;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.eriko.aplikasisederhana.server.BaseURL.order;

public class DetailCustomer extends AppCompatActivity {
    EditText edtKodeFilm, edtJudulFilm, edtSutradara, edtGenre, edtDurasi, edtHarga, edtJumlahPesanan;
    ImageView imgGambarFilm;
    Button TambahPesanan;
    String  username;
    String strKodeFilm, strJudulFilm, strSutradara, strGenre, strDurasi, strHarga, strGambar, _id, strjumlah;

    private RequestQueue mRequestQueue;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        username = PrefSetting.userName;

        edtKodeFilm = (EditText) findViewById(R.id.edtKodeFilm);
        edtJudulFilm = (EditText) findViewById(R.id.edtJudulFilm);
        edtSutradara = (EditText) findViewById(R.id.edtSutradara);
        edtGenre = (EditText) findViewById(R.id.edtGenre);
        edtDurasi = (EditText) findViewById(R.id.edtDurasi);
        edtHarga = (EditText) findViewById(R.id.edtHarga);
        edtJumlahPesanan = (EditText) findViewById(R.id.edtJumlahPesanan);

        imgGambarFilm = (ImageView) findViewById(R.id.gambarFilm);
        TambahPesanan = (Button) findViewById(R.id.TambahPesanan);

        TambahPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strKodeFilm = edtKodeFilm.getText().toString();
                strJudulFilm = edtJudulFilm.getText().toString();
                strSutradara = edtSutradara.getText().toString();
                strGenre = edtGenre.getText().toString();
                strDurasi = edtDurasi.getText().toString();
                strHarga = edtHarga.getText().toString();
                strjumlah = edtJumlahPesanan.getText().toString();
                int Total = Integer.parseInt(strHarga) * Integer.parseInt(strjumlah);

                order(strJudulFilm, strGenre, strjumlah, strHarga, Total);

            }
        });

        Intent i = getIntent();

        strKodeFilm = i.getStringExtra("kodeFilm");
        strJudulFilm = i.getStringExtra("judulFilm");
        strSutradara = i.getStringExtra("sutradara");
        strGenre = i.getStringExtra("genre");
        strDurasi = i.getStringExtra("durasi");
        strHarga = i.getStringExtra("harga");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeFilm.setText(strKodeFilm);
        edtJudulFilm.setText(strJudulFilm);
        edtSutradara.setText(strSutradara);
        edtGenre.setText(strGenre);
        edtDurasi.setText(strDurasi);
        edtHarga.setText(strHarga);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambar)
                .into(imgGambarFilm);
    }
    private void order(String strJudulFilm, String strGenre, String strjumlah, String strHarga, int total) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("judulFilm", strJudulFilm);
        params.put("Genre", strGenre);
        params.put("Harga", strHarga);
        params.put("Jumlah", strjumlah);
        params.put("Total", String.valueOf(total));

        pDialog.setMessage("Mohon Tunggu");
        showDialog();
        Log.e("param", String.valueOf(params));

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.order, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status= response.getBoolean("error");
                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(DetailCustomer.this, PilihFilmActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
    }
    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}