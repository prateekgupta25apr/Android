package prateek_gupta.foody.data.database.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import prateek_gupta.foody.models.FoodJoke;
import prateek_gupta.foody.util.Constants;

@Entity(tableName = Constants.FOOD_JOKE_TABLE)
public class FoodJokeEntity {
    @PrimaryKey(autoGenerate = false)
    public Integer id = 0;

    @Embedded
    public FoodJoke foodJoke;

    public FoodJokeEntity(FoodJoke foodJoke) {
        this.foodJoke = foodJoke;
    }

    public FoodJoke getFoodJoke() {
        return foodJoke;
    }
}
