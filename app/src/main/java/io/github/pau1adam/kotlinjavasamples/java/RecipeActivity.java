package io.github.pau1adam.kotlinjavasamples.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.pau1adam.kotlinjavasamples.R;
import io.github.pau1adam.kotlinjavasamples.kotlin.GetRecipeResponse;
import io.github.pau1adam.kotlinjavasamples.kotlin.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RecipeActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ListView listView;

    private final String BASE_URL = "http://food2fork.com/api/";

    private final String API_KEY = "add api key";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();

    Food2ForkApi api;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textview);
        listView = findViewById(R.id.ingerdients_listview);

        final String recipeId = getIntent().getStringExtra("recipeid");

        api = retrofit.create(Food2ForkApi.class);

        Call<GetRecipeResponse> searchRecipeCall = api.getRecipe(API_KEY, recipeId);
        searchRecipeCall.enqueue(responseCallback);

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

    private final Callback<GetRecipeResponse> responseCallback = new Callback<GetRecipeResponse>() {
        @Override
        public void onResponse(Call<GetRecipeResponse> call, Response<GetRecipeResponse> response) {
            if (response.body() != null) {
                final Recipe recipe = response.body().getRecipe();
                loadUI(recipe);
            }
        }

        @Override
        public void onFailure(Call<GetRecipeResponse> call, Throwable t) {
            final Recipe recipe = new Recipe();
            loadUI(recipe);
        }
    };
}
