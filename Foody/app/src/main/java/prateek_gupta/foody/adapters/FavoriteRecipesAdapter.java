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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.foody.R;
import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.databinding.FavoriteRecipesRowLayoutBinding;
import prateek_gupta.foody.ui.fragments.favourites.FavouritesRecipesFragmentDirections;
import prateek_gupta.foody.util.RecipesDiffUtils;
import prateek_gupta.foody.viewmodels.MainViewModel;

public class FavoriteRecipesAdapter extends
        RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder> implements ActionMode.Callback {
    List<FavoritesEntity> favoriteRecipes=new ArrayList<>();

    FragmentActivity requireActivity;

    boolean multiSelection = false;
    List<FavoritesEntity> selectedRecipes =new ArrayList<>();
    List<MyViewHolder> myViewHolders =new ArrayList<>();

    ActionMode mActionMode;
    MainViewModel mainViewModel;
    View rootView;

    public FavoriteRecipesAdapter(FragmentActivity requireActivity , MainViewModel mainViewModel) {
        this.requireActivity = requireActivity;
        this.mainViewModel=mainViewModel;
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
        rootView=holder.itemView.getRootView();
        myViewHolders.add(holder);
        holder.bind(favoriteRecipes.get(position));

        holder.binding.favoriteRecipesRowLayout.setOnClickListener(view -> {
            if (multiSelection) {
                applySelection(holder, favoriteRecipes.get(position));
            } else {
                FavouritesRecipesFragmentDirections.ActionFavouritesRecipesFragmentToDetailsActivity
                        action = FavouritesRecipesFragmentDirections.actionFavouritesRecipesFragmentToDetailsActivity(favoriteRecipes.get(position).result);
                Navigation.findNavController(holder.itemView).navigate(action);
            }
        });


        holder.binding.favoriteRecipesRowLayout.setOnLongClickListener(view -> {
            if (!multiSelection) {
                multiSelection = true;
                requireActivity.startActionMode(this);
                applySelection(holder, favoriteRecipes.get(position));
                return true;
            } else {
                multiSelection = false;
                return false;
            }
        });

    }

    void applySelection(MyViewHolder holder , FavoritesEntity currentRecipe ) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe);
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor);
        } else {
            selectedRecipes.add(currentRecipe);
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary);
        }
        applyActionModeTitle();
    }

    void changeRecipeStyle(MyViewHolder holder, Integer backgroundColor, Integer strokeColor) {
        holder.binding.favoriteRecipesRowLayout.setBackgroundColor(
                ContextCompat.getColor(requireActivity, backgroundColor)
        );
        holder.binding.favoriteRowCardView.setStrokeColor(ContextCompat.getColor(requireActivity, strokeColor));

    }

    void applyActionModeTitle() {
        switch (selectedRecipes.size()) {
            case 0 :
                mActionMode.finish();
                break;
            case 1 :
                mActionMode.setTitle(selectedRecipes.size()+" item selected");
                break;
            default :
                mActionMode.setTitle(selectedRecipes.size()+" items selected");

        }
    }

    @Override
    public int getItemCount() {
        return favoriteRecipes.size();
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.favorites_contextual_menu,menu);
        mActionMode=actionMode;
        applyStatusBarColor(R.color.contextualStatusBarColor);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.delete_favorite_recipe_menu) {
            for (FavoritesEntity favoritesEntity:selectedRecipes){
                mainViewModel.deleteFavoriteRecipe(favoritesEntity);
            }
            showSnackBar(selectedRecipes.size()+" Recipe/s removed.");

            multiSelection = false;
            selectedRecipes.clear();
            mActionMode.finish();
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        for (MyViewHolder holder:myViewHolders)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor);
        multiSelection = false;
        selectedRecipes.clear();
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

    void showSnackBar(String message ) {
        Snackbar.make(
                rootView,
                message,
                Snackbar.LENGTH_SHORT
        ).setAction("Okay",view -> {}).show();
    }

    public void clearContextualActionMode() {
        if (this.mActionMode!=null) {
            mActionMode.finish();
        }
    }
}
