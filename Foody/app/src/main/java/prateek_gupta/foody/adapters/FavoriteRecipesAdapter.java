package prateek_gupta.foody.adapters;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.foody.R;
import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.databinding.FavoriteRecipesRowLayoutBinding;
import prateek_gupta.foody.ui.fragments.favourites.FavouritesRecipesFragmentDirections;
import prateek_gupta.foody.util.RecipesDiffUtils;

public class FavoriteRecipesAdapter extends
        RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder> implements ActionMode.Callback {
    List<FavoritesEntity> favoriteRecipes=new ArrayList<>();

    FragmentActivity requireActivity;

    public FavoriteRecipesAdapter(FragmentActivity requireActivity) {
        this.requireActivity = requireActivity;
    }

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

        holder.itemView.findViewById(R.id.favoriteRecipesRowLayout).setOnClickListener(view -> {
            FavouritesRecipesFragmentDirections.ActionFavouritesRecipesFragmentToDetailsActivity
                    action=FavouritesRecipesFragmentDirections.actionFavouritesRecipesFragmentToDetailsActivity(favoriteRecipes.get(position).result);
            Navigation.findNavController(holder.itemView).navigate(action);
        });

        holder.itemView.findViewById(R.id.favoriteRecipesRowLayout).setOnLongClickListener(view -> {
            requireActivity.startActionMode(this);
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return favoriteRecipes.size();
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.favorites_contextual_menu,menu);
        applyStatusBarColor(R.color.contextualStatusBarColor);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        applyStatusBarColor(R.color.statusBarColor);
    }

    public void setData(List<FavoritesEntity> newFavoriteRecipes){
        RecipesDiffUtils favoriteRecipesDiffUtil = new
                RecipesDiffUtils(favoriteRecipes, newFavoriteRecipes);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil);
        favoriteRecipes = newFavoriteRecipes;
        diffUtilResult.dispatchUpdatesTo(this);
    }

    void applyStatusBarColor(Integer color) {
        requireActivity.getWindow().
                setStatusBarColor(ContextCompat.getColor(requireActivity, color));

    }
}
