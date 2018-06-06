package io.github.pau1adam.kotlinjavasamples.java;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.pau1adam.kotlinjavasamples.R;
import io.github.pau1adam.kotlinjavasamples.kotlin.Recipe;
import io.github.pau1adam.kotlinjavasamples.kotlin.RecipeActivity;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    List<Recipe> recipes;

    public RecipeListAdapter(@NonNull List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        AppCompatImageView image;
        TextView title;
        TextView publisher;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            publisher = view.findViewById(R.id.publisher);
        }

        public void bindView(final Recipe recipe) {
            title.setText(recipe.getTitle());
            publisher.setText(recipe.getPublisher());
            Picasso.get().load(recipe.getImage_url()).fit().into(image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), RecipeActivity.class)
                            .putExtra("recipeid", recipe.getRecipe_id());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}
