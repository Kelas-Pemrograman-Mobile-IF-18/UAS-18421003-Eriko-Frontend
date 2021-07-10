package com.eriko.aplikasisederhana.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.eriko.aplikasisederhana.model.ModelFilm;
import com.eriko.aplikasisederhana.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JadwalFilmActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterFilm adapter;
    ListView list;

    ArrayList<ModelFilm> newsList = new ArrayList<ModelFilm>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_film);

        getSupportActionBar().setTitle("Data Film");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterFilm(JadwalFilmActivity.this, newsList);
        list.setAdapter(adapter);
        getAllFilm();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(JadwalFilmActivity.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllFilm() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataFilm, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data film = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelFilm film = new ModelFilm();
                                    final String _id = jsonObject.getString("_id");
                                    final String judulFilm = jsonObject.getString("judulFilm");
                                    final String kodeFilm = jsonObject.getString("kodeFilm");
                                    final String sutradara = jsonObject.getString("sutradara");
                                    final String genre = jsonObject.getString("genre");
                                    final String harga = jsonObject.getString("harga");
                                    final String durasi = jsonObject.getString("durasi");
                                    final String gambar = jsonObject.getString("gambar");
                                    film.setKodeFilm(kodeFilm);
                                    film.setJudulFilm(judulFilm);
                                    film.setSutradara(sutradara);
                                    film.setGenre(genre);
                                    film.setHarga(harga);
                                    film.setDurasi(durasi);
                                    film.setGambar(gambar);
                                    film.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(JadwalFilmActivity.this, EditAcivity.class);
                                            a.putExtra("kodeFilm", newsList.get(position).getKodeFilm());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("judulFilm", newsList.get(position).getJudulFilm());
                                            a.putExtra("sutradara", newsList.get(position).getSutradara());
                                            a.putExtra("genre", newsList.get(position).getGenre());
                                            a.putExtra("harga", newsList.get(position).getHarga());
                                            a.putExtra("durasi", newsList.get(position).getDurasi());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(film);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}