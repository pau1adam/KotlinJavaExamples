package io.github.pau1adam.kotlinjavasamples.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.pau1adam.kotlinjavasamples.R;
import io.github.pau1adam.kotlinjavasamples.kotlin.GetSearchRecipeResponse;
import io.github.pau1adam.kotlinjavasamples.kotlin.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<GetSearchRecipeResponse> searchRecipeCall = RetrofitAPI.api.search(RetrofitAPI.API_KEY, "potato", 1);
        searchRecipeCall.enqueue(new Callback<GetSearchRecipeResponse>() {
            @Override
            public void onResponse(Call<GetSearchRecipeResponse> call, Response<GetSearchRecipeResponse> response) {
                if (response.body() != null) {
                    final List<Recipe> recipes = response.body().getRecipes();
                    RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipes);
                    recyclerView.setAdapter(recipeListAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetSearchRecipeResponse> call, Throwable t) {
                //assume network connectivity & ignore other types of errors
            }
        });
    }
}
