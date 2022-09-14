package prateek_gupta.foody.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.databinding.FavoriteRecipesRowLayoutBinding;
import prateek_gupta.foody.util.RecipesDiffUtils;

public class FavoriteRecipesAdapter extends RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder> {
    List<FavoritesEntity> favoriteRecipes=new ArrayList<>();

    static class MyViewHolder extends RecyclerView.ViewHolder{
        FavoriteRecipesRowLayoutBinding binding;
        public MyViewHolder(FavoriteRecipesRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        void bind(FavoritesEntity favoritesEntity){
            binding.setFavoritesEntity(favoritesEntity);
            binding.executePendingBindings();
        }

        static MyViewHolder from(ViewGroup parent){
            return new MyViewHolder(FavoriteRecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(favoriteRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteRecipes.size();
    }

    public void setData(List<FavoritesEntity> newFavoriteRecipes){
        RecipesDiffUtils favoriteRecipesDiffUtil = new
                RecipesDiffUtils(favoriteRecipes, newFavoriteRecipes);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil);
        favoriteRecipes = newFavoriteRecipes;
        diffUtilResult.dispatchUpdatesTo(this);
    }
}
