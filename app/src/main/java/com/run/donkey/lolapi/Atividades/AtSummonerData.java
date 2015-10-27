package com.run.donkey.lolapi.Atividades;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.run.donkey.lolapi.Adapters.AdapterSummoner;
import com.run.donkey.lolapi.ClassesAuxiliares.SummonerDto;
import com.run.donkey.lolapi.R;

import java.util.ArrayList;
import java.util.Map;

public class AtSummonerData extends AppCompatActivity {// Atividade

    EditText buscaSum;

    Map<String, SummonerDto> mapa;
    ListView lista;

    ArrayList<Bitmap> bit;
    AdapterSummoner adapterS;
    ArrayList<SummonerDto> sum;

    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.atsummonerdata);
        sum = new ArrayList<>();
        bit = new ArrayList<>();

        Button btn = (Button) findViewById(R.id.button);
        buscaSum = (EditText) findViewById(R.id.editSumName);

        lista = (ListView) findViewById(R.id.listView);

        adapterS = new AdapterSummoner(this, sum, bit);

        lista.setAdapter(adapterS);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = buscaSum.getText().toString();

                SummonerDto summoner = new SummonerDto(adapterS, sum, mapa, bit);
                summoner.getSummonerData(buscaSum.getText().toString());

                //      new getSummonerTask().execute(buscaSum.getText().toString());// Inicia procura

            }
        });

    }


}
