package com.eriko.aplikasisederhana.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.eriko.aplikasisederhana.adapter.AdapterTransaksi;
import com.eriko.aplikasisederhana.model.ModelTransaksi;
import com.eriko.aplikasisederhana.server.BaseURL;
import com.eriko.aplikasisederhana.session.PrefSetting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KeranjangActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    AdapterTransaksi adapter;
    ListView list;

    String username;

    ArrayList<ModelTransaksi> newsList = new ArrayList<ModelTransaksi>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        getSupportActionBar().setTitle("Riwayat Pesanan");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        username= PrefSetting.userName;

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterTransaksi(KeranjangActivity.this, newsList);
        list.setAdapter(adapter);
        getAllKeranjang();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(KeranjangActivity.this, HomeCustomerActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllKeranjang() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataOrderbyname + username, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data Keranjang = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelTransaksi order = new ModelTransaksi();
                                    final String _id = jsonObject.getString("_id");
                                    final String username = jsonObject.getString("username");
                                    final String judulFilm = jsonObject.getString("judulFilm");
                                    final String genre = jsonObject.getString("Genre");
                                    final String harga = jsonObject.getString("Harga");
                                    final String jumlah = jsonObject.getString("Jumlah");
                                    final String total = jsonObject.getString("Total");
                                    order.setUsername(username);
                                    order.setJudulFilm(judulFilm);
                                    order.setGenre(genre);
                                    order.setHarga(harga);
                                    order.setJumlah(jumlah);
                                    order.setTotal(total);
                                    order.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(KeranjangActivity.this, HomeCustomerActivity.class);
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("username", newsList.get(position).getUsername());
                                            a.putExtra("judulFilm", newsList.get(position).getjudulFilm());
                                            a.putExtra("Genre", newsList.get(position).getGenre());
                                            a.putExtra("Harga", newsList.get(position).getHarga());
                                            a.putExtra("Jumlah", newsList.get(position).getJumlah());
                                            a.putExtra("Total", newsList.get(position).getTotal());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(order);
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