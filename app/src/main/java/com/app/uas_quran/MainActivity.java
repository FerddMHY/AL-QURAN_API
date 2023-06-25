package com.app.uas_quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.uas_quran.Model.AudioModel.Audio;
import com.app.uas_quran.Model.AudioModel.AudioFilesItem;
import com.app.uas_quran.Model.SurahModel.Chapters;
import com.app.uas_quran.Model.SurahModel.ChaptersItem;
import com.app.uas_quran.Retrofit.ApiBase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainAdapter mainAdapter;
    private AudioAdapter audioAdapter;
    private RecyclerView recyclerView, recyclerView_1;
    private RecyclerView.LayoutManager layoutManager1, layoutManager2;
    private List<AudioFilesItem> audio = new ArrayList<>();
    private List<ChaptersItem> hasil = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromApi();
        getDataFromApiAudio();
        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
//        For Surah
        mainAdapter = new MainAdapter(hasil);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(mainAdapter);
//        For Audio
        audioAdapter = new AudioAdapter(audio);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView_1.setLayoutManager(layoutManager2);
        recyclerView_1.setAdapter(audioAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvSurah);
        recyclerView_1 = findViewById(R.id.rvAudio);
    }
    private void getDataFromApiAudio() {
        ApiBase.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    List<AudioFilesItem> audio = response.body().getAudioFiles();
                    audioAdapter.setData(audio);
                }
            }
            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        });
    }

    private void getDataFromApi() {
        ApiBase.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    List<ChaptersItem> hasil = response.body().getChapters();
                    Log.d("Main", hasil.toString());
                    mainAdapter.setData(hasil);
                }
            }
            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
}