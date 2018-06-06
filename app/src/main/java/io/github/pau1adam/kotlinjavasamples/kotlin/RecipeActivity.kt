package io.github.pau1adam.kotlinjavasamples.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import io.github.pau1adam.kotlinjavasamples.R
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.coroutines.experimental.runBlocking

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val recipe = getRecipe(intent.getStringExtra(BUNDLE_KEY_RECIPE_ID))
        loadUI(recipe)
    }

    private fun loadUI(recipe: Recipe) {
        title = recipe.title
        if (recipe.image_url != "") {
            Picasso.get().load(recipe.image_url).into(imageView)
        }
        ingerdients_listview.adapter = ArrayAdapter<String>(this@RecipeActivity,
                android.R.layout.simple_list_item_1, android.R.id.text1, recipe.ingredients)
    }

    private fun getRecipe(recipeId: String): Recipe = runBlocking {
        try { api.getRecipe(API_KEY, recipeId).await() }
        catch (e: Exception) {
            GetRecipeResponse()
        }.recipe
    }
}
