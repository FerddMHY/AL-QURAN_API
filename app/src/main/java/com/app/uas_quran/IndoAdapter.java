package com.app.uas_quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.uas_quran.Model.AyatModel.VersesItem;
import com.app.uas_quran.Model.Terjemahan.TranslationsItem;

import java.util.List;

public class IndoAdapter extends RecyclerView.Adapter<IndoAdapter.IndoViewHolder> {
    private List<VersesItem> ayat;
    private List<TranslationsItem> result;

    public IndoAdapter(List<VersesItem> ayat, List<TranslationsItem> results){
        this.result = results;
        this.ayat = ayat;
    }

    @NonNull
    @Override
    public IndoAdapter.IndoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IndoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull IndoAdapter.IndoViewHolder holder, int position) {
        TranslationsItem results = result.get(position);
        VersesItem ayats = ayat.get(position);
        holder.textViewAyat.setText(ayats.getTextUthmani());
        holder.textViewTerjemahanAyat.setText(results.getText());
        holder.textViewNomorAyat.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class IndoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat;
        TextView textViewTerjemahanAyat;
        TextView textViewNomorAyat;

        public IndoViewHolder(@NonNull View itemView){
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            textViewNomorAyat = itemView.findViewById(R.id.tvNomorAyat);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }
    public void setData(List<TranslationsItem> indo, List<VersesItem> ayats){
        result.clear();
        result.addAll(indo);

        ayat.clear();
        ayat.addAll(ayats);
        notifyDataSetChanged();
    }
}