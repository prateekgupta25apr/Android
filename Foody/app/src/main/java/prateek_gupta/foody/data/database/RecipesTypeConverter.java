package prateek_gupta.foody.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.models.Result;

public class RecipesTypeConverter {
    Gson gson=new Gson();

    @TypeConverter
    public String foodRecipesToString(FoodRecipe foodRecipe){
        return gson.toJson(foodRecipe);
    }

    @TypeConverter
    public FoodRecipe stringToFoodRecipe(String data){
        return gson.fromJson(data,new TypeToken<FoodRecipe>(){}.getType());
    }

    @TypeConverter
    public String resultToString(Result result){
        return gson.toJson(result);
    }

    @TypeConverter
    public Result stringToResult(String data){
        return gson.fromJson(data,new TypeToken<Result>(){}.getType());
    }
}
