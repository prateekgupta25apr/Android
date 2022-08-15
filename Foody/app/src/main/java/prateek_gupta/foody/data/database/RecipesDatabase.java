package prateek_gupta.foody.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = RecipesEntity.class,
        version = 1,
        exportSchema = false
)
@TypeConverters(RecipesTypeConverter.class)
public abstract class RecipesDatabase extends RoomDatabase {
    abstract public RecipesDao recipesDao();
}
