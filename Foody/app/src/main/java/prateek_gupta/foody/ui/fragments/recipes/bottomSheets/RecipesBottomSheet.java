package prateek_gupta.foody.ui.fragments.recipes.bottomSheets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Locale;

import prateek_gupta.foody.R;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.viewmodels.RecipesViewModel;


public class RecipesBottomSheet extends BottomSheetDialogFragment {

    private static final String TAG = "RecipesBottomSheet";

    RecipesViewModel recipesViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView= inflater.inflate(R.layout.recipes_bottom_sheet, container, false);

        LiveDataReactiveStreams.fromPublisher(recipesViewModel.readMealAndDietType).observe(this.getViewLifecycleOwner(),value->{
            mealTypeChip= value.getSelectedMealType();
            dietTypeChip= value.getSelectedDietType();
            updateChip(value.getSelectedMealTypeId(), mView.findViewById(R.id.mealType_chipGroup));
            updateChip(value.getSelectedDietTypeId(), mView.findViewById(R.id.dietType_chipGroup));
        });

        ChipGroup mealTypeChipGroup=mView.findViewById(R.id.mealType_chipGroup);
        mealTypeChipGroup.setOnCheckedStateChangeListener((group, selectedChipId) -> {
            Chip chip= group.findViewById(selectedChipId.get(0));
            mealTypeChip=chip.getText().toString().toLowerCase(Locale.ROOT);
            mealTypeChipId=selectedChipId.get(0);
        });

        ChipGroup dietTypeChipGroup=mView.findViewById(R.id.dietType_chipGroup);
        dietTypeChipGroup.setOnCheckedStateChangeListener((group, selectedChipId) -> {
            Chip chip= group.findViewById(selectedChipId.get(0));
            dietTypeChip=chip.getText().toString().toLowerCase(Locale.ROOT);
            dietTypeChipId=selectedChipId.get(0);
        });

        mView.findViewById(R.id.apply_btn).setOnClickListener(view -> {
            recipesViewModel.saveMealAndDietType(mealTypeChip,mealTypeChipId,dietTypeChip,dietTypeChipId);
        });
        return mView;
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
}