package io.github.pau1adam.kotlinjavasamples.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.pau1adam.kotlinjavasamples.R;
import io.github.pau1adam.kotlinjavasamples.kotlin.Recipe;

public class RecipeActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textview);
        listView = findViewById(R.id.ingerdients_listview);

        Recipe recipe = new Recipe();
        loadUI(recipe);
    }

    private void loadUI(Recipe recipe) {
        setTitle(recipe.getTitle());
        String imageUrl = recipe.getImage_url();
        if (!imageUrl.equalsIgnoreCase("")) {
            Picasso.get().load(imageUrl).into(imageView);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                recipe.getIngredients()
        );
        listView.setAdapter(adapter);
    }
}
