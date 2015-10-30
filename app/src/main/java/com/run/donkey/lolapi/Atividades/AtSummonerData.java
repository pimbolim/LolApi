package com.run.donkey.lolapi.Atividades;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_overflow, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.Themes:
                Toast.makeText(getBaseContext(), "Change your theme", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;

    }

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
            public void onClick(View v) {// clickar no botão segue
                nome = buscaSum.getText().toString();

                SummonerDto summoner = new SummonerDto(adapterS, sum, mapa, bit);
                summoner.getSummonerData(buscaSum.getText().toString());



            }
        });

    }


}
