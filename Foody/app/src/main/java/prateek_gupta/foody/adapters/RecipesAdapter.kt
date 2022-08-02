package prateek_gupta.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import prateek_gupta.foody.databinding.RecipesRowLayoutBinding
import prateek_gupta.foody.models.FoodRecipe
import prateek_gupta.foody.models.Result
import prateek_gupta.foody.util.RecipesDiffUtils

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes= emptyList<Result>()
    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipes=recipes[position]
        holder.bind(currentRecipes)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData:FoodRecipe){
        val recipesDiffUtil=RecipesDiffUtils(recipes,newData.results)
        val diffUtilResult=DiffUtil.calculateDiff(recipesDiffUtil)
        recipes=newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}