package prateek_gupta.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import prateek_gupta.foody.models.FoodRecipe
import prateek_gupta.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int=0
}