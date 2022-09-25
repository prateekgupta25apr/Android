package prateek_gupta.foody.ui.fragments.recipes.bottomSheets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Locale;

import prateek_gupta.foody.R;
import prateek_gupta.foody.databinding.RecipesBottomSheetBinding;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.viewmodels.RecipesViewModel;


public class RecipesBottomSheet extends BottomSheetDialogFragment {
    RecipesViewModel recipesViewModel;

    RecipesBottomSheetBinding binding;

    String mealTypeChip = Constants.DEFAULT_MEAL_TYPE;
    Integer mealTypeChipId = 0;
    String dietTypeChip = Constants.DEFAULT_DIET_TYPE;
    Integer dietTypeChipId = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesViewModel= new ViewModelProvider(requireActivity()).get(RecipesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= RecipesBottomSheetBinding.inflate(inflater, container, false);

        LiveDataReactiveStreams.fromPublisher(recipesViewModel.readMealAndDietType).observe(this.getViewLifecycleOwner(),value->{
            mealTypeChip= value.getSelectedMealType();
            dietTypeChip= value.getSelectedDietType();
            updateChip(value.getSelectedMealTypeId(), binding.mealTypeChipGroup);
            updateChip(value.getSelectedDietTypeId(), binding.dietTypeChipGroup);
        });

        binding.mealTypeChipGroup.setOnCheckedStateChangeListener((group, selectedChipId) -> {
            Chip chip= group.findViewById(selectedChipId.get(0));
            mealTypeChip=chip.getText().toString().toLowerCase(Locale.ROOT);
            mealTypeChipId=selectedChipId.get(0);
        });

        binding.dietTypeChipGroup.setOnCheckedStateChangeListener((group, selectedChipId) -> {
            Chip chip= group.findViewById(selectedChipId.get(0));
            dietTypeChip=chip.getText().toString().toLowerCase(Locale.ROOT);
            dietTypeChipId=selectedChipId.get(0);
        });

        binding.applyBtn.setOnClickListener(view -> {
            recipesViewModel.saveMealAndDietType(mealTypeChip,mealTypeChipId,dietTypeChip,dietTypeChipId);
            prateek_gupta.foody.ui.fragments.recipes.bottomSheets.RecipesBottomSheetDirections.ActionRecipesBottomSheetToRecipesFragment action=RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment();
            action.setBackFromBottomSheet(true);
            Navigation.findNavController(this.requireActivity(),R.id.fragmentContainerView).navigate(action);
        });
        return binding.getRoot();
    }

    void updateChip(Integer chipId, ChipGroup chipGroup) {
        if (chipId != 0) {
            try {
                Chip chip=chipGroup.findViewById(chipId);
                chip.setChecked(true);
            } catch (Exception e ) {
                Log.d("RecipesBottomSheet", e.getMessage());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}