package com.example.adrianwong.snapcook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrianwong.snapcook.R;

import javax.inject.Inject;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeItemViewHolder> {

    @Inject
    public RecipeAdapter() {

    }

    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        public RecipeItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
