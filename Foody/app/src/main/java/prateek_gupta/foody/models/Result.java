package prateek_gupta.foody.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result implements Parcelable {
    @SerializedName("aggregateLikes")
    public Integer aggregateLikes;
    @SerializedName("id")
    public Integer id;
    @SerializedName("readyInMinutes")
    public Integer readyInMinutes;

    @SerializedName("cheap")
    public Boolean cheap;
    @SerializedName("dairyFree")
    public Boolean dairyFree;
    @SerializedName("glutenFree")
    public Boolean glutenFree;
    @SerializedName("vegan")
    public Boolean vegan;
    @SerializedName("vegetarian")
    public Boolean vegetarian;
    @SerializedName("veryHealthy")
    public Boolean veryHealthy;

    @SerializedName("extendedIngredients")
    public List<ExtendedIngredient> extendedIngredients;

    @SerializedName("image")
    public String image;
    @SerializedName("sourceName")
    public String sourceName;
    @SerializedName("sourceUrl")
    public String sourceUrl;
    @SerializedName("summary")
    public String summary;
    @SerializedName("title")
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(new int[]{aggregateLikes,id,readyInMinutes});

        parcel.writeBooleanArray(new boolean[]{cheap,dairyFree,glutenFree,vegan,
                vegetarian,veryHealthy});

        parcel.writeStringArray(new String[]{image,sourceName,sourceUrl,summary,title});

        parcel.writeTypedList(extendedIngredients);
    }

    public static final Parcelable.Creator<Result> CREATOR
            = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Result(Parcel parcel) {
        int[] ints=new int[3];
        parcel.readIntArray(ints);
        aggregateLikes=ints[0];
        id=ints[1];
        readyInMinutes=ints[2];

        boolean[] booleans=new boolean[6];
        parcel.readBooleanArray(booleans);
        cheap=booleans[0];
        dairyFree=booleans[1];
        glutenFree=booleans[2];
        vegan=booleans[3];
        vegetarian=booleans[4];
        veryHealthy=booleans[5];

        String[] strings=new String[5];
        parcel.readStringArray(strings);
        image=strings[0];
        sourceName=strings[1];
        sourceUrl=strings[2];
        summary=strings[3];
        title=strings[4];

        List<ExtendedIngredient> extendedIngredientList=new ArrayList<>();
        parcel.readTypedList(extendedIngredientList,ExtendedIngredient.CREATOR);
        extendedIngredients=extendedIngredientList;
    }
}
