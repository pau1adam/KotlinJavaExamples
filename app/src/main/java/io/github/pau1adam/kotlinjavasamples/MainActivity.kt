package io.github.pau1adam.kotlinjavasamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recipes = search("potato")
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = RecipeListAdapter(recipes)
    }

    private fun search(keyword: String): List<Recipe> = runBlocking {
        try { api.search(API_KEY, keyword, 1).await() }
        catch (e: Exception) { GetSearchRecipeResponse() }.recipes
    }

}
