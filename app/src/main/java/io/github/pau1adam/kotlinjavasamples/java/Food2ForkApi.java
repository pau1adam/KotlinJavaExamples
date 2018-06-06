package io.github.pau1adam.kotlinjavasamples.java;

import io.github.pau1adam.kotlinjavasamples.kotlin.GetRecipeResponse;
import io.github.pau1adam.kotlinjavasamples.kotlin.GetSearchRecipeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Food2ForkApi {
    @GET("search")
    Call<GetSearchRecipeResponse> search(
            @Query("key") String key,
            @Query("q") String keyword,
            @Query("page") int page
    );

    @GET("get")
    Call<GetRecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipeId
    );
}
