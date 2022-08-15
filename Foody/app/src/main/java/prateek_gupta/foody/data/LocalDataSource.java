package prateek_gupta.foody.data;

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

    public List<RecipesEntity> readDatabase(){return recipesDao.readRecipes();}

    void insertRecipes(RecipesEntity recipesEntity){
        recipesDao.insertRecipes(recipesEntity);
    }

}
