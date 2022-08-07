package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("aggregateLikes")
    Integer aggregateLikes;
    @SerializedName("cheap")
    Boolean cheap;
    @SerializedName("dairyFree")
    Boolean dairyFree;
    @SerializedName("extendedIngredients")
    List<ExtendedIngredient> extendedIngredients;
    @SerializedName("glutenFree")
    Boolean glutenFree;
    @SerializedName("id")
    Integer id;
    @SerializedName("image")
    String image;
    @SerializedName("readyInMinutes")
    Integer readyInMinutes;
    @SerializedName("sourceName")
    String sourceName;
    @SerializedName("sourceUrl")
    String sourceUrl;
    @SerializedName("summary")
    String summary;
    @SerializedName("title")
    String title;
    @SerializedName("vegan")
    Boolean vegan;
    @SerializedName("vegetarian")
    Boolean vegetarian;
    @SerializedName("veryHealthy")
    Boolean veryHealthy;
}
