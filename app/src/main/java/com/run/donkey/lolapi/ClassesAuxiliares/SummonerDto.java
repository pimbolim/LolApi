package com.run.donkey.lolapi.ClassesAuxiliares;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.run.donkey.lolapi.Adapters.AdapterSummoner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nuno on 14/10/2015.
 */
//-/api/lol/{region}\v1.4/summoner/by-name/{summonerNames}
//-return map[string,SummonerDto]
// https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/pimbolim?api_key=<4e29429e-fed9-4e02-884e-596def3958c7>
public class SummonerDto {

    long id;
    String name;
    int profileIconId;
    long revisionDate;
    long summonerLevel;

    // The transient keyword in Java is used to indicate that a field should not be serialized.
    // Isto por causa de evitar problemas com a conversão de json para gson

    transient AdapterSummoner adapterS;
    transient ArrayList<SummonerDto> sum;
    transient Map<String, SummonerDto> mapa;
    transient ArrayList<Bitmap> bit;

    public SummonerDto(AdapterSummoner adpter, ArrayList<SummonerDto> arrSum, Map<String, SummonerDto> mapaSums, ArrayList<Bitmap> bits) {

        adapterS = adpter;
        sum = arrSum;
        mapa = mapaSums;
        bit = bits;

    }

    public SummonerDto() {


    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonnerLevel(long summonnerLevel) {
        this.summonerLevel = summonnerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileiconId) {
        this.profileIconId = profileiconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getSummonerData(String data) {
        new getSummonerTask().execute(data);
    }


    // ::::: TESTES :::::

    public class getSummonerTask extends AsyncTask<String, Void, String> {// AsyncTaks para download da classe SummonerDto

        OkHttpClient client = new OkHttpClient();
        String resultado;
        JSONObject jsonObject = null;
        Boolean okDados = false;
        String nomeSum;


        @Override
        protected String doInBackground(String... params) {// recebe o nome
            nomeSum = params[0];
            Request requestSummoner = new Request.Builder()
                    .url("https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/" + params[0] + "?api_key=4e29429e-fed9-4e02-884e-596def3958c7")
                    .build();

            Response responseSummoner = null;
            try {
                responseSummoner = client.newCall(requestSummoner).execute();
                resultado = responseSummoner.body().string();

                jsonObject = new JSONObject(resultado);

                if (jsonObject != null) {// Se tiver dados
                    okDados = true;
                    Type t = new TypeToken<Map<String, SummonerDto>>() {
                    }.getType();//gson convert

                    mapa = (Map<String, SummonerDto>) new Gson().fromJson(String.valueOf(jsonObject), t);

                    SummonerDto sumTemp = mapa.get(String.valueOf(params[0]));

                    sum.add(sumTemp);
                    // fazer se já existe não saca String.valueOf(jsonObject) == blabla
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (okDados)
                new getSummonerImageTask().execute(nomeSum);// Chama AsyncTaks para buscar imagem

        }
    }

    public class getSummonerImageTask extends AsyncTask<String, Void, String> {// AsyncTaks para download da imagem do summoner

        OkHttpClient client = new OkHttpClient();
        InputStream input;
        JSONObject jsonObject = null;
        Bitmap myBitmap;

        @Override
        protected String doInBackground(String... params) {

            Request requestSummoner = new Request.Builder()
                    .url("http://avatar.leagueoflegends.com/euw/" + params[0] + ".png")
                    .build();

            Response responseSummoner = null;
            try {
                responseSummoner = client.newCall(requestSummoner).execute();
                input = responseSummoner.body().byteStream();

                myBitmap = BitmapFactory.decodeStream(input);
                bit.add(myBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            adapterS.notifyDataSetChanged();
        }

    }
}