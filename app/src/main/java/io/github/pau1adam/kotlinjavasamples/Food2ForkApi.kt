package io.github.pau1adam.kotlinjavasamples

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface Food2ForkApi {

    @GET("search")
    fun search(
            @Query("key") key: String,
            @Query("q") keyword: String,
            @Query("page") page: Int?
    ): Deferred<GetSearchRecipeResponse>

    @GET("get")
    fun getRecipe(
            @Query("key") key: String,
            @Query("rId") recipeId: String
    ): Deferred<GetRecipeResponse>
}