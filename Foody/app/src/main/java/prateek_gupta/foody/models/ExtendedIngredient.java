package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {
    @SerializedName("amount")
    Double amount ;
    @SerializedName("consistency")
    String consistency;
    @SerializedName("image")
    String image;
    @SerializedName("name")
    String name;
    @SerializedName("original")
    String original;
    @SerializedName("unit")
    String unit;
}
