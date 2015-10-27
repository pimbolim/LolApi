package com.run.donkey.lolapi.Adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.run.donkey.lolapi.ClassesAuxiliares.SummonerDto;
import com.run.donkey.lolapi.R;

import java.util.ArrayList;

/**
 * Created by nuno on 15/10/2015.
 */
public class AdapterSummoner extends BaseAdapter {


    /***********
     * Declare Used Variables
     *********/
    private Activity activity;

    private static LayoutInflater inflater = null;

    ArrayList<Bitmap> bitmap;
    ArrayList<SummonerDto> sum;

    int i = 0;

    public AdapterSummoner(Activity a, ArrayList<SummonerDto> map, ArrayList<Bitmap> bit) {

        /********** Take passed values **********/
        activity = a;
        sum = map;
        bitmap = bit;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public AdapterSummoner() {
        super();
    }

    @Override
    public int getCount() {
        return sum.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View vi = convertView;
        ViewHolder holder;
        Log.d("AAA", "teste3");
        if (convertView == null) {
            Log.d("AAA", "testeNULL");
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.layadaptsummoner, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.txtNome = (TextView) vi.findViewById(R.id.layAdaptTxtNome);
            holder.txtNivel = (TextView) vi.findViewById(R.id.layAdaptTxtnivel);
            holder.imagem = (ImageView) vi.findViewById(R.id.layAdaptImgV);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (sum.size() <= 0) {
            holder.txtNivel.setText("No Data");
            holder.txtNome.setText("No Data");

        } else {

            holder.imagem.setImageBitmap(bitmap.get(position));
            holder.txtNome.setText("" + sum.get(position).getName());
            holder.txtNivel.setText("" + sum.get(position).getSummonerLevel());

        }
        Log.d("AAA", "fim");
        return vi;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView txtNome;
        public TextView txtNivel;
        public ImageView imagem;


    }
}
