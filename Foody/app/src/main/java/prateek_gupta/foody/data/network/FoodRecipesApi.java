package prateek_gupta.foody.data.network;

import java.util.Map;

import prateek_gupta.foody.models.FoodRecipe;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FoodRecipesApi {
    @GET("/recipes/complexSearch")
    Response<FoodRecipe> getRecipes(@QueryMap Map<String, String> queries);
}
