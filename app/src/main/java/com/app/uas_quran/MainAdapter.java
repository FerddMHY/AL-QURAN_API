package com.app.uas_quran;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.uas_quran.Model.SurahModel.ChaptersItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<ChaptersItem> results;

    public MainAdapter(List<ChaptersItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        ChaptersItem result = results.get(position);

        holder.textViewSurahLatin.setText(result.getNameSimple());
        holder.textViewSurahArab.setText(result.getNameArabic());
        holder.textViewTerjemahanSurah.setText(result.getTranslatedName().getName());
        holder.textViewSurahNomor.setText(String.valueOf(result.getId()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);

                intent.putExtra("id", result.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewSurahArab, textViewTerjemahanSurah , textViewSurahNomor;
        public MainViewHolder(@NonNull View itemView){
            super(itemView);
            textViewSurahNomor = itemView.findViewById(R.id.tvSurahNomor);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
        }
    }
    public void setData(List<ChaptersItem> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}
