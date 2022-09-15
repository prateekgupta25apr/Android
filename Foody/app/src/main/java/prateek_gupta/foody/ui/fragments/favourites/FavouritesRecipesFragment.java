package prateek_gupta.foody.ui.fragments.favourites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.FavoriteRecipesAdapter;
import prateek_gupta.foody.bindingsAdapters.FavoriteRecipesBinding;
import prateek_gupta.foody.databinding.FragmentFavouritesRecepiesBinding;
import prateek_gupta.foody.viewmodels.MainViewModel;
import prateek_gupta.foody.viewmodels.RecipesViewModel;

@AndroidEntryPoint
public class FavouritesRecipesFragment extends Fragment {

    MainViewModel mainViewModel;
    FavoriteRecipesAdapter mAdapter;
    FragmentFavouritesRecepiesBinding binding;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mAdapter=new FavoriteRecipesAdapter(requireActivity(),mainViewModel);
        binding=FragmentFavouritesRecepiesBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);
        binding.setMAdapter(mAdapter);

        setupRecyclerView(binding.favoriteRecipesRecyclerView);

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteAll_favorite_recipes_menu){
            mainViewModel.deleteAllFavoriteRecipes();
//            mainViewModel.getReadFavoriteRecipes();
//            FavoriteRecipesBinding.setDataAndViewVisibility(binding.getRoot().findViewById(R.id.favoriteRecipesRecyclerView),mainViewModel.readFavoriteRecipes.getValue(),mAdapter);
//            FavoriteRecipesBinding.setDataAndViewVisibility(binding.getRoot().findViewById(R.id.no_data_imageView),mainViewModel.readFavoriteRecipes.getValue(),mAdapter);
//            FavoriteRecipesBinding.setDataAndViewVisibility(binding.getRoot().findViewById(R.id.no_data_textView),mainViewModel.readFavoriteRecipes.getValue(),mAdapter);
            showSnackBar();
        }
        return super.onOptionsItemSelected(item);
    }

    void setupRecyclerView(RecyclerView recyclerView){
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clearContextualActionMode();
        binding=null;
    }

    void showSnackBar(){
        Snackbar.make(
                binding.getRoot(),
                "All recipes removed.",
                Snackbar.LENGTH_SHORT
        ).setAction("Okay",view -> {})
            .show();
    }
}