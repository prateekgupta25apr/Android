package prateek_gupta.foody.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import prateek_gupta.foody.data.database.RecipesDao;
import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.data.database.entities.RecipesEntity;

public class LocalDataSource {
    public RecipesDao recipesDao;

    @Inject
    public LocalDataSource(RecipesDao recipesDao) {
        this.recipesDao = recipesDao;
    }

    public LiveData<List<RecipesEntity>> readRecipes(){return recipesDao.readRecipes();}

    public List<FavoritesEntity> readFavoriteRecipes(){
        return recipesDao.readFavoriteRecipes();}

    public void insertRecipes(RecipesEntity recipesEntity){
        recipesDao.insertRecipes(recipesEntity);
    }

    public void insertFavoriteRecipes(FavoritesEntity favoritesEntity){
        recipesDao.insertFavoriteRecipe(favoritesEntity);
    }

    public void deleteFavoriteRecipe(FavoritesEntity favoritesEntity){
        recipesDao.deleteFavoriteRecipe(favoritesEntity);
    }

    public void deleteAllFavoriteRecipes(){
        recipesDao.deleteAllFavoriteRecipes();
    }

}
