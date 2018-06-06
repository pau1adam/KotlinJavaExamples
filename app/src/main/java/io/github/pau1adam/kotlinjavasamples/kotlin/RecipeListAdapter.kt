package io.github.pau1adam.kotlinjavasamples.kotlin

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.pau1adam.kotlinjavasamples.R
import kotlinx.android.synthetic.main.list_item.view.*

class RecipeListAdapter(private val recipes: List<Recipe>) :
        RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(recipe: Recipe) {
            with(recipe) {
                itemView.title.text = title
                itemView.publisher.text = publisher
                Picasso.get().load(image_url).fit().into(itemView.image)
            }
            itemView.setOnClickListener {
                val intent = Intent(it.context, RecipeActivity::class.java)
                        .putExtra("recipeid", recipe.recipe_id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(recipes[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipes.size
}