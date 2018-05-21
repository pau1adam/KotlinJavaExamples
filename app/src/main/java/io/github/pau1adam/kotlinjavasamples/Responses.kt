package io.github.pau1adam.kotlinjavasamples

import com.squareup.moshi.Json


data class GetSearchRecipeResponse(
        @Json(name = "count") val count: Int = 0,
        @Json(name = "recipes") val recipes: List<Recipe> = ArrayList()
)

data class GetRecipeResponse(
        @Json(name = "recipe") val recipe: Recipe = Recipe()
)

data class Recipe(
        @Json(name = "publisher") val publisher: String = "",
        @Json(name = "f2f_url") val f2f_url: String = "",
        @Json(name = "title") val title: String = "",
        @Json(name = "source_url") val source_url: String = "",
        @Json(name = "recipeId") val recipe_id: String = "",
        @Json(name = "image_url") val image_url: String = "",
        @Json(name = "social_rank") val social_rank: String = "",
        @Json(name = "publisher_url") val publisher_url: String = "",
        @Json(name = "ingredients") val ingredients: List<String> = ArrayList()
)
