package prateek_gupta.foody.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.util.Constants;

@Entity(tableName=Constants.RECIPES_TABLE)
public class RecipesEntity {
    @PrimaryKey(autoGenerate = false)
    Integer id=0;

    FoodRecipe foodRecipe;

    public RecipesEntity(FoodRecipe foodRecipe) {
        this.foodRecipe = foodRecipe;
    }

    public FoodRecipe getFoodRecipe() {
        return foodRecipe;
    }

    public void setFoodRecipe(FoodRecipe foodRecipe) {
        this.foodRecipe = foodRecipe;
    }
}
