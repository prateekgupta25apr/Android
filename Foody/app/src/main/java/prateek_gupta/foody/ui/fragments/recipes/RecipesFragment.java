package prateek_gupta.foody.ui.fragments.recipes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.todkars.shimmer.ShimmerRecyclerView;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.RecipesAdapter;
import prateek_gupta.foody.util.NetworkResult;
import prateek_gupta.foody.viewmodels.MainViewModel;
import prateek_gupta.foody.viewmodels.RecipesViewModel;

@AndroidEntryPoint
public class RecipesFragment extends Fragment {

    public MainViewModel mainViewModel;

    public RecipesViewModel recipesViewModel;

    public RecipesAdapter mAdapter=new RecipesAdapter();

    public View mView;

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
        mView=inflater.inflate(R.layout.fragment_recipes, container, false);

        setupRecyclerView();
        requestApiData();
        return mView;
    }

    void requestApiData(){
        mainViewModel.getRecipes(recipesViewModel.applyQueries());
        mainViewModel.recipesResponse.observe(this.getViewLifecycleOwner(),response->{
            if (response instanceof NetworkResult.Success){
                hideShimmerEffect();
                if (response.data!=null)mAdapter.setData(response.data);
            }
            else if (response instanceof NetworkResult.Error){
                hideShimmerEffect();
                Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if (response instanceof NetworkResult.Loading){
                hideShimmerEffect();
            }
        });
    }

    void setupRecyclerView(){
        ShimmerRecyclerView recyclerView=mView.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        showShimmerEffect();
    }

    void showShimmerEffect(){
        ShimmerRecyclerView recyclerView=mView.findViewById(R.id.recyclerview);
        recyclerView.showShimmer();
    }
    void hideShimmerEffect(){
        ShimmerRecyclerView recyclerView=mView.findViewById(R.id.recyclerview);
        recyclerView.hideShimmer();
    }
}