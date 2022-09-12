package prateek_gupta.foody.data.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;

@Entity(tableName = Constants.FAVORITE_RECIPES_TABLE)
public class FavoritesEntity {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public Result result;

    public FavoritesEntity(Integer id, Result result) {
        this.id = id;
        this.result = result;
    }
}
