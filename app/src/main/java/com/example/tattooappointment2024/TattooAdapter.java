package com.example.tattooappointment2024;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TattooAdapter extends RecyclerView.Adapter<TattooAdapter.TattooViewHolder> {

    private List<Tattoo> tattooList;
    private Context context;

    public TattooAdapter(List<Tattoo> tattooList, Context context) {
        this.tattooList = tattooList;
        this.context = context;
    }

    @NonNull
    @Override
    public TattooViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tattoo, parent, false);
        return new TattooViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TattooViewHolder holder, int position) {
        Tattoo tattoo = tattooList.get(position);
        holder.imageViewTattoo.setImageResource(tattoo.getImageResource());
        holder.textViewDescription.setText(tattoo.getDescription());
    }

    @Override
    public int getItemCount() {
        return tattooList.size();
    }

    public static class TattooViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewTattoo;
        TextView textViewDescription;

        public TattooViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTattoo = itemView.findViewById(R.id.imageViewTattoo);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
