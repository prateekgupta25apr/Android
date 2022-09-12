package prateek_gupta.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import prateek_gupta.foody.models.Result
import prateek_gupta.foody.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)