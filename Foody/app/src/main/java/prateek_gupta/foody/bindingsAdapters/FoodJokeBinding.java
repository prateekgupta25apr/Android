package prateek_gupta.foody.bindingsAdapters;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.util.List;

import kotlin.jvm.JvmStatic;
import prateek_gupta.foody.data.database.entities.FoodJokeEntity;
import prateek_gupta.foody.models.FoodJoke;
import prateek_gupta.foody.util.NetworkResult;

public class FoodJokeBinding {
    @BindingAdapter(value = {"readApiResponse3", "readDatabase3"}, requireAll = false)
    @JvmStatic
    public static void setCardAndProgressVisibility(View view, NetworkResult<FoodJoke> apiResponse, List<FoodJokeEntity> database){
        if (apiResponse instanceof NetworkResult.Loading){
            if (view instanceof ProgressBar)
                view.setVisibility(View.VISIBLE);
            else
                view.setVisibility(View.INVISIBLE);
        }
        else if (apiResponse instanceof NetworkResult.Error){
            if (view instanceof ProgressBar)
                view.setVisibility(View.INVISIBLE);
            else {
                view.setVisibility(View.VISIBLE);
                if (database!=null&&database.size()==0)
                    view.setVisibility(View.INVISIBLE);
            }
        }
        else if (apiResponse instanceof NetworkResult.Success){
            if (view instanceof ProgressBar)
                view.setVisibility(View.INVISIBLE);
            else
                view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter(value = {"readApiResponse4", "readDatabase4"}, requireAll = true)
    @JvmStatic
    public static void setErrorViewsVisibility(View view, NetworkResult<FoodJoke> apiResponse,List<FoodJokeEntity> database){
        if (database!=null && database.size()==0){
                view.setVisibility(View.VISIBLE);
                if (view instanceof TextView){
                        if (apiResponse!=null)
                            ((TextView) view).setText(apiResponse.message);
                }
        }
        if (apiResponse instanceof NetworkResult.Success)
                view.setVisibility(View.INVISIBLE);
    }
}
