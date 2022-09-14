package prateek_gupta.foody.ui.fragments.favourites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.FavoriteRecipesAdapter;
import prateek_gupta.foody.databinding.FragmentFavouritesRecepiesBinding;
import prateek_gupta.foody.viewmodels.MainViewModel;
import prateek_gupta.foody.viewmodels.RecipesViewModel;

@AndroidEntryPoint
public class FavouritesRecipesFragment extends Fragment {

    MainViewModel mainViewModel;
    FavoriteRecipesAdapter mAdapter=new FavoriteRecipesAdapter();
    FragmentFavouritesRecepiesBinding binding;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFavouritesRecepiesBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);
        binding.setMAdapter(mAdapter);

        setupRecyclerView(binding.favoriteRecipesRecyclerView);

        return binding.getRoot();
    }

    void setupRecyclerView(RecyclerView recyclerView){
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}