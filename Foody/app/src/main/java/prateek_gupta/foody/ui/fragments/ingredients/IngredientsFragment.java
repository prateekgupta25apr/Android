package prateek_gupta.foody.ui.fragments.ingredients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import prateek_gupta.foody.adapters.IngredientsAdapter;
import prateek_gupta.foody.databinding.FragmentIngredientsBinding;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;

public class IngredientsFragment extends Fragment {

    private final IngredientsAdapter mAdapter=new IngredientsAdapter();

    FragmentIngredientsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentIngredientsBinding.inflate(inflater, container, false);

        if (getArguments()!=null){
            Result myBundle=getArguments().getParcelable(Constants.RECIPE_RESULT_KEY);

            setupRecyclerView();
            mAdapter.setData(myBundle.extendedIngredients);

        }

        return binding.getRoot();
    }

    void setupRecyclerView() {
        binding.ingredientsRecyclerview.setAdapter(mAdapter);
        binding.ingredientsRecyclerview.setLayoutManager(new
                LinearLayoutManager(requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}