package com.app.uas_quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.uas_quran.Model.InfoModel.ChapterInfo;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private static List<ChapterInfo> chapterInfos;

    public InfoAdapter(List<ChapterInfo> chapterInfos){
        this.chapterInfos = chapterInfos;
    }

    @NonNull
    @Override
    public InfoAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_surah, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder holder, int position) {
        ChapterInfo chapterInfo = chapterInfos.get(position);

        holder.textViewInfo.setText(chapterInfo.getText());
    }

    @Override
    public int getItemCount() {
        return chapterInfos.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInfo;
        public InfoViewHolder(View itemView){
            super(itemView);
            textViewInfo = itemView.findViewById(R.id.tvInfoSurah);
        }
    }
    public void setData(List<ChapterInfo> data){
        chapterInfos.clear();
        chapterInfos.addAll(data);
        notifyDataSetChanged();
    }
}


