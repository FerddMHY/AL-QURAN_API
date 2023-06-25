package com.app.uas_quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.uas_quran.Model.AudioModel.Audio;
import com.app.uas_quran.Model.AyatModel.Verses;
import com.app.uas_quran.Model.AyatModel.VersesItem;
import com.app.uas_quran.Model.Terjemahan.Indo;
import com.app.uas_quran.Model.Terjemahan.TranslationsItem;
import com.app.uas_quran.Retrofit.ApiBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IndoAdapter indoAdapter;
    private final List<VersesItem> versesItemList = new ArrayList<>();
    private final List<TranslationsItem> translationsItemList = new ArrayList<>();

    List<TranslationsItem> result;
    List<VersesItem> versesItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        setUpView();
        setUpRecyclerView();
        getTranslateData(id);
    }

    private void getTranslateData(int id) {
        ApiBase.endpoint().getIndo(id).enqueue(new Callback<Indo>() {
            @Override
            public void onResponse(Call<Indo> call, Response<Indo> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.result = response.body().getTranslations();
                    getVersesData(getIntent().getIntExtra("id", 1));
                }
            }

            @Override
            public void onFailure(Call<Indo> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    private void getVersesData(int id) {
        ApiBase.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.versesItems = response.body().getVerses();
                    indoAdapter.setData(result, versesItems);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
            }
        });
    }

    private void setUpRecyclerView() {
        indoAdapter = new IndoAdapter(versesItemList, translationsItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(indoAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvAyat);
    }
}