package com.example.wordguide.RecyclerViewHolder;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordguide.ApiModals.Phonetic;
import com.example.wordguide.R;

public class customViewHolder extends RecyclerView.ViewHolder {

    public EditText meaning,type,synonym,antonym;
    public customViewHolder(@NonNull View itemView) {
        super(itemView);
        meaning=itemView.findViewById(R.id.meaningField);
        type=itemView.findViewById(R.id.typeField);
        synonym=itemView.findViewById(R.id.synonymField);
        antonym=itemView.findViewById(R.id.antonymField);
    }
}
