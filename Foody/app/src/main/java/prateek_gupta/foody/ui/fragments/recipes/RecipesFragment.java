package prateek_gupta.foody.ui.fragments.recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.RecipesAdapter;
import prateek_gupta.foody.data.database.RecipesEntity;
import prateek_gupta.foody.databinding.FragmentRecipesBinding;
import prateek_gupta.foody.ui.fragments.recipes.bottomSheets.RecipesBottomSheetDirections;
import prateek_gupta.foody.util.MyExtensionFunctions;
import prateek_gupta.foody.util.NetworkResult;
import prateek_gupta.foody.viewmodels.MainViewModel;
import prateek_gupta.foody.viewmodels.RecipesViewModel;

@AndroidEntryPoint
public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";

    boolean backFromBottomSheet;

    FragmentRecipesBinding binding;

    public MainViewModel mainViewModel;

    public RecipesViewModel recipesViewModel;

    public RecipesAdapter mAdapter=new RecipesAdapter();

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        recipesViewModel=new ViewModelProvider(requireActivity()).get(RecipesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentRecipesBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);

        backFromBottomSheet=RecipesFragmentArgs.fromBundle(getArguments()).getBackFromBottomSheet();

        setupRecyclerView();
        readDatabase();


        binding.recipesFab.setOnClickListener(v->Navigation.findNavController(this.requireActivity(),R.id.fragmentContainerView).navigate(R.id.action_recipesFragment_to_recipesBottomSheet));
        return binding.getRoot();
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

    void setupRecyclerView(){
        ShimmerRecyclerView recyclerView= binding.recyclerview;
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        Log.d(TAG, "setupRecyclerView: calling show shimmer");
        showShimmerEffect();
    }

    void showShimmerEffect(){
        Log.d(TAG, "setupRecyclerView: called show shimmer");
        ShimmerRecyclerView recyclerView=binding.recyclerview;
        recyclerView.showShimmer();
    }
    void hideShimmerEffect(){
        ShimmerRecyclerView recyclerView=binding.recyclerview;
        recyclerView.hideShimmer();
    }
}