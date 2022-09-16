package prateek_gupta.foody.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.data.database.entities.FoodJokeEntity;
import prateek_gupta.foody.data.database.entities.RecipesEntity;

@Database(
        entities = {RecipesEntity.class, FavoritesEntity.class, FoodJokeEntity.class},
        version = 1,
        exportSchema = false
)
@TypeConverters(RecipesTypeConverter.class)
public abstract class RecipesDatabase extends RoomDatabase {
    abstract public RecipesDao recipesDao();
}
