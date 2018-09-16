package com.example.adrianwong.snapcook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianwong.snapcook.R;
import com.example.adrianwong.snapcook.model.Recipe;
import com.example.adrianwong.snapcook.ui.recipe.RecipeActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeItemViewHolder> {

    private List<Recipe> mRecipeList;
    private Context context;
    private InteractionListener mListInteractionListener;

    @Inject
    public RecipeAdapter(Context context) {
        mRecipeList = new ArrayList<>();
        this.context = context;
        mListInteractionListener = null;
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
        Picasso.with(context).load(imageUrl).into(holder.recipeImage);
    }

    public List<Recipe> getRecipeList() {
        return mRecipeList;
    }

    public Recipe getRecipe (int id) {
        return mRecipeList.get(id);
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public interface InteractionListener {
        void onListClick(int id);
    }

    public void setListInteractionListener(InteractionListener interactionListener) {
        mListInteractionListener = interactionListener;
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        @Override
        public void onClick(View v) {
            if (mListInteractionListener != null){
                int elementId = mRecipeList.get(getAdapterPosition()).getId();
                mListInteractionListener.onListClick(elementId);
            }
        }
    }
}
