package prateek_gupta.foody.data;

import java.util.Map;

import javax.inject.Inject;

import prateek_gupta.foody.data.network.FoodRecipesApi;
import prateek_gupta.foody.models.FoodJoke;
import prateek_gupta.foody.models.FoodRecipe;
import retrofit2.Call;

public class RemoteDataSource {

    FoodRecipesApi foodRecipesApi;

    @Inject
    public RemoteDataSource(FoodRecipesApi foodRecipesApi) {
        this.foodRecipesApi = foodRecipesApi;
    }

    public Call<FoodRecipe> getRecipes(Map<String, String> queries){
        return foodRecipesApi.getRecipes(queries);
    }

    public Call<FoodRecipe> searchRecipes(Map<String, String> searchQuery){
        return foodRecipesApi.searchRecipes(searchQuery);
    }

    public Call<FoodJoke> getFoodJoke(String apiKey){
        return foodRecipesApi.getFoodJoke(apiKey);
    }
}
