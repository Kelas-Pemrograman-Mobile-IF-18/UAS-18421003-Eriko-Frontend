package com.eriko.aplikasisederhana.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eriko.aplikasisederhana.R;
import com.eriko.aplikasisederhana.admin.JadwalFilmActivity;
import com.eriko.aplikasisederhana.model.ModelFilm;
import com.eriko.aplikasisederhana.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFilm extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelFilm> item;

    public AdapterFilm(Activity activity, List<ModelFilm> item) {
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
            convertView = inflater.inflate(R.layout.content_film, null);


        TextView judul         = (TextView) convertView.findViewById(R.id.txtjudulFilm);
        TextView sutradara     = (TextView) convertView.findViewById(R.id.txtsutradara);
        TextView genre         = (TextView) convertView.findViewById(R.id.txtgenre);
        TextView harga         = (TextView) convertView.findViewById(R.id.txtharga);
        TextView durasi        = (TextView) convertView.findViewById(R.id.txtdurasi);
        ImageView gambarFilm   = (ImageView) convertView.findViewById(R.id.gambarFilm);

        judul.setText(item.get(position).getJudulFilm());
        sutradara.setText(item.get(position).getSutradara());
        genre.setText(item.get(position).getGenre());
        harga.setText("Rp." + item.get(position).getHarga());
        durasi.setText(item.get(position).getDurasi());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarFilm);
        return convertView;
    }
}
