package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {
    @SerializedName("amount")
    public Double amount ;
    @SerializedName("consistency")
    public String consistency;
    @SerializedName("image")
    public String image;
    @SerializedName("name")
    public String name;
    @SerializedName("original")
    public String original;
    @SerializedName("unit")
    public String unit;
}
