package prateek_gupta.foody.ui.fragments.ingredients;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.IngredientsAdapter;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;

public class IngredientsFragment extends Fragment {

    private final IngredientsAdapter mAdapter=new IngredientsAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ingredients, container, false);

        if (getArguments()!=null){
            Result myBundle=getArguments().getParcelable(Constants.RECIPE_RESULT_KEY);

            setupRecyclerView(view);
            mAdapter.setData(myBundle.extendedIngredients);

        }

        return view;
    }

    void setupRecyclerView(View view) {
        RecyclerView recyclerView=view.findViewById(R.id.ingredients_recyclerview);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}