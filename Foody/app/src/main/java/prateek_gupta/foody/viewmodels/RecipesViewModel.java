package prateek_gupta.foody.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import prateek_gupta.foody.data.DataStoreRepository;
import prateek_gupta.foody.data.MealAndDietType;
import prateek_gupta.foody.util.Constants;

@HiltViewModel
public class RecipesViewModel extends AndroidViewModel {

    private static final String TAG = "RecipesViewModel";
    DataStoreRepository dataStoreRepository;
    Application application;
    public Flowable<MealAndDietType> readMealAndDietType;
    String mealType=Constants.DEFAULT_MEAL_TYPE;
    String dietType=Constants.DEFAULT_DIET_TYPE;

    @Inject
    public RecipesViewModel(@NonNull @NotNull Application application,DataStoreRepository dataStoreRepository) {
        super(application);
        this.application=application;
        this.dataStoreRepository=dataStoreRepository;
        readMealAndDietType=dataStoreRepository.readMealAndDietType();
    }

    public void saveMealAndDietType(String mealType,Integer mealTypeId,String dietType,Integer dietTypeId){
        dataStoreRepository.saveMealAndDietType(mealType,mealTypeId,dietType,dietTypeId);
    }

    public HashMap<String, String> applyQueries(){
        HashMap<String,String> queries=new HashMap<>();
        MealAndDietType mealAndDietType= readMealAndDietType.blockingFirst();
        mealType=mealAndDietType.selectedMealType;
        dietType=mealAndDietType.selectedDietType;

        queries.put(Constants.QUERY_NUMBER,Constants.DEFAULT_RECIPES_NUMBER);
        queries.put(Constants.QUERY_API_KEY,Constants.API_KEY);
        queries.put(Constants.QUERY_TYPE,mealType);
        queries.put(Constants.QUERY_DIET,dietType);
        queries.put(Constants.QUERY_ADD_RECIPE_INFORMATION,"true");
        queries.put(Constants.QUERY_FILL_INGREDIENTS,"true");
        return queries;
    }
}
