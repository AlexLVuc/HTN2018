package com.example.adrianwong.snapcook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.model.Recipe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeItemViewHolder> {

    private List<Recipe> mRecipeList;

    @Inject
    public RecipeAdapter() {
        mRecipeList = new ArrayList<>();
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

        String title = recipe.getTitle();
        String imageUrl = recipe.getImageUrl();
        int usedIngredientCount = recipe.getUsedIngredientCount();
        int missedIngredientCount = recipe.getMissedIngredientCount();
        int likes = recipe.getLikes();

        holder.recipeTitle.setText(title);
        holder.missingIngredientText.setText(usedIngredientCount + " used, " + missedIngredientCount + " missing");
        holder.likesCountText.setText(String.valueOf(likes));
//        holder.mNoteTitleTv.setText(noteTitle);
//        holder.mNoteBodyTv.setText(noteBody);
//        holder.mPriorityView.setBackgroundColor(getPriorityColour(priority));
//        holder.mDateTv.setText(updatedAt);

    }

    public List<Recipe> getRecipeList() {
        return mRecipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.media_image)
        ImageView recipeImage;

        @BindView(R.id.recipe_title)
        TextView recipeTitle;

        @BindView(R.id.missing_ingredient_text)
        TextView missingIngredientText;

        @BindView(R.id.likes_count)
        TextView likesCountText;

        public RecipeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
