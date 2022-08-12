package prateek_gupta.foody.data.network;

import java.util.Map;

import prateek_gupta.foody.models.FoodRecipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FoodRecipesApi {
    @GET("/recipes/complexSearch")
    Call<FoodRecipe> getRecipes(@QueryMap Map<String, String> queries);
}
