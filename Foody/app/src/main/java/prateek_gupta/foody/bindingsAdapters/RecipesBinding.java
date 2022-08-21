package prateek_gupta.foody.bindingsAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.util.List;

import kotlin.jvm.JvmStatic;
import prateek_gupta.foody.data.database.RecipesEntity;
import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.util.NetworkResult;

public class RecipesBinding {
    @BindingAdapter(value = {"readApiResponse", "readDatabase"}, requireAll = true)
    @JvmStatic
    public static void errorImageViewVisibility(ImageView imageView, NetworkResult<FoodRecipe> apiResponse, List<RecipesEntity>database){
        if (apiResponse instanceof NetworkResult.Error && (database==null || database.size() == 0))
            imageView.setVisibility(View.VISIBLE);
        else if (apiResponse instanceof NetworkResult.Loading ||apiResponse instanceof NetworkResult.Success)
            imageView.setVisibility(View.INVISIBLE);
    }

    @BindingAdapter(value = {"readApiResponse2", "readDatabase2"}, requireAll = true)
    @JvmStatic
    public static void errorTextViewVisibility(TextView textView, NetworkResult<FoodRecipe> apiResponse, List<RecipesEntity>database){
        if (apiResponse instanceof NetworkResult.Error && (database== null || database.size() == 0)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(apiResponse.message);
        }
        else if (apiResponse instanceof NetworkResult.Loading ||apiResponse instanceof NetworkResult.Success)
            textView.setVisibility(View.INVISIBLE);
    }
}
