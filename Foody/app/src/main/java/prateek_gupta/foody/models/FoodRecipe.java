package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodRecipe {
    @SerializedName("results")
   public List<Result> results;
}
