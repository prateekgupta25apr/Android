package prateek_gupta.foody.ui.fragments.recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.RecipesAdapter;
import prateek_gupta.foody.data.database.entities.RecipesEntity;
import prateek_gupta.foody.databinding.FragmentRecipesBinding;
import prateek_gupta.foody.util.MyExtensionFunctions;
import prateek_gupta.foody.util.NetworkListener;
import prateek_gupta.foody.util.NetworkResult;
import prateek_gupta.foody.viewmodels.MainViewModel;
import prateek_gupta.foody.viewmodels.RecipesViewModel;

@AndroidEntryPoint
public class RecipesFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "RecipesFragment";

    boolean backFromBottomSheet;

    FragmentRecipesBinding binding;

    public MainViewModel mainViewModel;

    public RecipesViewModel recipesViewModel;

    public RecipesAdapter mAdapter=new RecipesAdapter();

    public NetworkListener networkListener;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        recipesViewModel=new ViewModelProvider(requireActivity()).get(RecipesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentRecipesBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);

        backFromBottomSheet=RecipesFragmentArgs.fromBundle(getArguments()).getBackFromBottomSheet();

        setHasOptionsMenu(true);

        setupRecyclerView();

        recipesViewModel.readBackOnline.observe(getViewLifecycleOwner(),
                value-> recipesViewModel.backOnline=value);

        networkListener=new NetworkListener();
        networkListener.checkNetworkAvailability(requireContext()).observe(getViewLifecycleOwner(),status->{
            Log.d("NetworkListener", status.toString());
            recipesViewModel.networkStatus=status;
            recipesViewModel.showNetworkStatus();
            readDatabase();
        });

        binding.recipesFab.setOnClickListener(v->{
            if (recipesViewModel.networkStatus)
                Navigation.findNavController(this.requireActivity(),R.id.fragmentContainerView).navigate(R.id.action_recipesFragment_to_recipesBottomSheet);
            else
                recipesViewModel.showNetworkStatus();
        });
        return binding.getRoot();
    }

    void setupRecyclerView(){
        binding.recyclerview.setAdapter(mAdapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        showShimmerEffect();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.recipes_menu,menu);

        MenuItem search=menu.findItem(R.id.menu_search);
        SearchView searchView= (SearchView) search.getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query!=null)
            searchApiData(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    void readDatabase(){
        new MyExtensionFunctions<List<RecipesEntity>>().observeOnce(mainViewModel.getReadRecipes(), this.getViewLifecycleOwner(), database->{
            if (database.size()>0 && !backFromBottomSheet){
                Log.d(TAG, "readDatabase: called");
                mAdapter.setData(database.get(0).getFoodRecipe());
                hideShimmerEffect();
            }
            else requestApiData();
        });
    }

    void requestApiData(){
        Log.d(TAG, "requestApiData: called");
        mainViewModel.getRecipes(recipesViewModel.applyQueries());
        mainViewModel.recipesResponse.observe(this.getViewLifecycleOwner(),response->{
            if (response instanceof NetworkResult.Success){
                hideShimmerEffect();
                if (response.data!=null)mAdapter.setData(response.data);
                recipesViewModel.saveMealAndDietType();
            }
            else if (response instanceof NetworkResult.Error){
                hideShimmerEffect();
                loadDataFromCache();
                Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if (response instanceof NetworkResult.Loading){
                showShimmerEffect();
            }
        });
    }

    void searchApiData(String searchQuery){
        showShimmerEffect();
        mainViewModel.searchRecipes(recipesViewModel.applySearchQuery(searchQuery));
        mainViewModel.searchedRecipesResponse.observe(this.getViewLifecycleOwner(),response->{
            if (response instanceof NetworkResult.Success){
                hideShimmerEffect();
                if (response.data!=null)mAdapter.setData(response.data);
            }
            else if (response instanceof NetworkResult.Error){
                hideShimmerEffect();
                loadDataFromCache();
                Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if (response instanceof NetworkResult.Loading){
                showShimmerEffect();
            }
        });
    }

    void loadDataFromCache(){
        mainViewModel.getReadRecipes().observe(this.getViewLifecycleOwner(),database->{
            if (database.size()>0){
                mAdapter.setData(database.get(0).getFoodRecipe());
            }
        });
    }

    void showShimmerEffect(){
        binding.shimmerFrameLayout.startShimmer();
        binding.shimmerFrameLayout.setVisibility(View.VISIBLE);
        binding.recyclerview.setVisibility(View.GONE);
    }
    void hideShimmerEffect(){
        binding.shimmerFrameLayout.stopShimmer();
        binding.shimmerFrameLayout.setVisibility(View.GONE);
        binding.recyclerview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}