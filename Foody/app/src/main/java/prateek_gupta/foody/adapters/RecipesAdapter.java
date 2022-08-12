package prateek_gupta.foody.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.foody.databinding.RecipesRowLayoutBinding;
import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.RecipesDiffUtils;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {

    private static final String TAG = "RecipesAdapter";
    List<Result> recipes = new ArrayList<>();

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public RecipesRowLayoutBinding binding;

        public MyViewHolder(@NonNull @NotNull RecipesRowLayoutBinding binding1) {
            super(binding1.getRoot());
            binding=binding1;
        }

        public void bind(Result result){

            if (binding!=null){
                binding.setResult(result);
                binding.executePendingBindings();
            }
        }

        static MyViewHolder from(ViewGroup parent) {
            return new MyViewHolder(RecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=MyViewHolder.from(parent);
        Log.d(TAG, "onCreateViewHolder: myViewHolder object is : "+myViewHolder);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setData(FoodRecipe newData){
        Log.d(TAG, "setData: Started");
        RecipesDiffUtils recipesDiffUtils= new RecipesDiffUtils(recipes,newData.results);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(recipesDiffUtils);
        recipes=newData.results;
        diffResult.dispatchUpdatesTo(this);
        Log.d(TAG, "setData: Ended");
    }
}
