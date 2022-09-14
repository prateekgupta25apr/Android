package prateek_gupta.foody.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.data.database.entities.RecipesEntity;


@Dao
public interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(RecipesEntity recipesEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteRecipe(FavoritesEntity favoritesEntity);

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    LiveData<List<RecipesEntity>> readRecipes();

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    List<FavoritesEntity> readFavoriteRecipes();

    @Delete
    void deleteFavoriteRecipe(FavoritesEntity favoritesEntity);

    @Query("DELETE FROM favorite_recipes_table")
    void deleteAllFavoriteRecipes();
}
