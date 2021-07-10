package com.eriko.aplikasisederhana.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.model.ModelTransaksi;

import java.util.List;

public class AdapterTransaksi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelTransaksi> item;

    public AdapterTransaksi(Activity activity, List<ModelTransaksi> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_order, null);


        TextView username = (TextView) convertView.findViewById(R.id.txtUsername);
        TextView judulFilm          = (TextView) convertView.findViewById(R.id.txtjudulFilm);
        TextView genre     = (TextView) convertView.findViewById(R.id.txtgenre);
        TextView harga         = (TextView) convertView.findViewById(R.id.txtharga);
        TextView jumlah         = (TextView) convertView.findViewById(R.id.txtjumlah);
        TextView total         = (TextView) convertView.findViewById(R.id.txtTotal);

        username.setText(item.get(position).getUsername());
        judulFilm.setText(item.get(position).getjudulFilm());
        genre.setText(item.get(position).getGenre());
        harga.setText("Rp." + item.get(position).getHarga());
        jumlah.setText(item.get(position).getJumlah());
        total.setText(item.get(position).getTotal());
        return convertView;
    }
}
