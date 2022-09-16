package prateek_gupta.foody.models;

import com.google.gson.annotations.SerializedName;

public class FoodJoke{

	@SerializedName("text")
	private String text;

	public String getText(){
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "FoodJoke{" +
				"text='" + text + '\'' +
				'}';
	}
}