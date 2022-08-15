package prateek_gupta.foody.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import prateek_gupta.foody.data.database.RecipesDao;
import prateek_gupta.foody.data.database.RecipesDatabase;
import prateek_gupta.foody.util.Constants;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Singleton
    @Provides
    RecipesDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                RecipesDatabase.class,
                Constants.DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    RecipesDao provideDao(RecipesDatabase recipesDatabase){
        return recipesDatabase.recipesDao();
    }
}
