package com.example.adrianwong.snapcook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.model.Recipe;

import java.util.List;

import javax.inject.Inject;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeItemViewHolder> {

    private List<Recipe> mRecipeList;
    private Context mContext;

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
        Recipe recipe = mRecipeList.get(position);

        int id = recipe.getId();
        String title = recipe.getTitle();
        String imageUrl = recipe.getImageUrl();
        int usedIngredientCount = recipe.getUsedIngredientCount();
        int missedIngredientCount = recipe.getMissedIngredientCount();
        int likes = recipe.getLikes();

//        holder.mNoteTitleTv.setText(noteTitle);
//        holder.mNoteBodyTv.setText(noteBody);
//        holder.mPriorityView.setBackgroundColor(getPriorityColour(priority));
//        holder.mDateTv.setText(updatedAt);
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
