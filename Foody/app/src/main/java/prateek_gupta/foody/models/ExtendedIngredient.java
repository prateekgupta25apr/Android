package prateek_gupta.foody.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ExtendedIngredient implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(amount);
        parcel.writeStringArray(new String[]{consistency, image, name, original, unit});
    }

    public static final Parcelable.Creator<ExtendedIngredient> CREATOR
            = new Parcelable.Creator<ExtendedIngredient>() {
        public ExtendedIngredient createFromParcel(Parcel parcel) {
            return new ExtendedIngredient(parcel);
        }

        public ExtendedIngredient[] newArray(int size) {
            return new ExtendedIngredient[size];
        }
    };

    public ExtendedIngredient(Parcel parcel) {
        amount= parcel.readDouble();
        String[] strings = new String[5];
        parcel.readStringArray(strings);

        consistency=strings[0];
        image=strings[1];
        name=strings[2];
        original=strings[3];
        unit=strings[4];
    }
}
