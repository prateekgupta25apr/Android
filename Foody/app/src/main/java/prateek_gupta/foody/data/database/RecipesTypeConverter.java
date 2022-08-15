package prateek_gupta.foody.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import prateek_gupta.foody.models.FoodRecipe;

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
}
