package prateek_gupta.foody.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import kotlinx.coroutines.flow.Flow;
import prateek_gupta.foody.data.database.RecipesDao;
import prateek_gupta.foody.data.database.RecipesEntity;

public class LocalDataSource {
    public RecipesDao recipesDao;

    @Inject
    public LocalDataSource(RecipesDao recipesDao) {
        this.recipesDao = recipesDao;
    }

    public LiveData<List<RecipesEntity>> readDatabase(){return recipesDao.readRecipes();}

    public void insertRecipes(RecipesEntity recipesEntity){
        recipesDao.insertRecipes(recipesEntity);
    }

}
