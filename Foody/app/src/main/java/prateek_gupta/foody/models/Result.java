package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("aggregateLikes")
    public Integer aggregateLikes;
    @SerializedName("cheap")
    public Boolean cheap;
    @SerializedName("dairyFree")
    public Boolean dairyFree;
    @SerializedName("extendedIngredients")
    public List<ExtendedIngredient> extendedIngredients;
    @SerializedName("glutenFree")
    public Boolean glutenFree;
    @SerializedName("id")
    public Integer id;
    @SerializedName("image")
    public String image;
    @SerializedName("readyInMinutes")
    public Integer readyInMinutes;
    @SerializedName("sourceName")
    public String sourceName;
    @SerializedName("sourceUrl")
    public String sourceUrl;
    @SerializedName("summary")
    public String summary;
    @SerializedName("title")
    public String title;
    @SerializedName("vegan")
    public Boolean vegan;
    @SerializedName("vegetarian")
    public Boolean vegetarian;
    @SerializedName("veryHealthy")
    public Boolean veryHealthy;
}
