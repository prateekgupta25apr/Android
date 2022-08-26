package prateek_gupta.foody.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import prateek_gupta.foody.util.Constants;

public class RecipesViewModel extends AndroidViewModel {
    public RecipesViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public HashMap<String, String> applyQueries(){
        HashMap<String,String> queries=new HashMap<>();
        queries.put(Constants.QUERY_NUMBER,"50");
        queries.put(Constants.QUERY_API_KEY,Constants.API_KEY);
        queries.put(Constants.QUERY_TYPE,"snack");
        queries.put(Constants.QUERY_DIET,"vegan");
        queries.put(Constants.QUERY_ADD_RECIPE_INFORMATION,"true");
        queries.put(Constants.QUERY_FILL_INGREDIENTS,"true");
        return queries;
    }
}