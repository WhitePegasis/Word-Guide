package com.example.wordguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordguide.ApiModals.Meaning;
import com.example.wordguide.CustomModal.WordMeaningModal;
import com.example.wordguide.RecyclerViewHolder.customViewHolder;

import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<customViewHolder> {
    Context context;
    List<WordMeaningModal> meaningsList;

    public CustomAdapter(Context context, List<WordMeaningModal> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_meaning_layout,parent,false);
        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, int position) {
        holder.meaning.setText(meaningsList.get(position).getMeaning());
        holder.type.setText(meaningsList.get(position).getType());
        holder.synonym.setText(meaningsList.get(position).getSynonym());
        holder.antonym.setText(meaningsList.get(position).getAntonym());

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
