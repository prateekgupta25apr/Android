package prateek_gupta.foody.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(RecipesEntity recipesEntity);

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    List<RecipesEntity> readRecipes();
}
