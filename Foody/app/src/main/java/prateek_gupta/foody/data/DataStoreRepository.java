package prateek_gupta.foody.data;

import android.content.Context;


import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import prateek_gupta.foody.util.Constants;

public class DataStoreRepository {
    Context context;
    RxDataStore<Preferences> dataStore;

    @Inject
    public DataStoreRepository(@ApplicationContext Context context) {
        this.context=context;
        dataStore=new RxPreferenceDataStoreBuilder(context, Constants.PREFERENCES_NAME)
                .build();
    }

    Preferences.Key<String> selectedMealType =
            PreferencesKeys.stringKey(Constants.PREFERENCES_MEAL_TYPE);
    Preferences.Key<Integer> selectedMealTypeId =
            PreferencesKeys.intKey(Constants.PREFERENCES_DIET_TYPE_ID);
    Preferences.Key<String> selectedDietType =
            PreferencesKeys.stringKey(Constants.PREFERENCES_DIET_TYPE);
    Preferences.Key<Integer> selectedDietTypeId =
            PreferencesKeys.intKey(Constants.PREFERENCES_DIET_TYPE_ID);

    void saveMealAndDietType(String mealType, Integer mealTypeId, String dietType,
                             Integer dietTypeId) {
        dataStore.updateDataAsync(preferences -> {
            MutablePreferences mutablePreferences = preferences.toMutablePreferences();
            mutablePreferences.set(selectedMealType, mealType);
            mutablePreferences.set(selectedMealTypeId, mealTypeId);
            mutablePreferences.set(selectedDietType, dietType);
            mutablePreferences.set(selectedDietTypeId, dietTypeId);
            return Single.just(mutablePreferences);
        });
    }

    Flowable<MealAndDietType> readMealAndDietType() {
        return dataStore.data().map(preferences -> new MealAndDietType(
                preferences.get(selectedMealType) != null ?
                        preferences.get(selectedMealType) : Constants.DEFAULT_MEAL_TYPE,
                preferences.get(selectedMealTypeId) != null ?
                        preferences.get(selectedMealTypeId) : 0,
                preferences.get(selectedDietType) != null ?
                        preferences.get(selectedDietType) : Constants.DEFAULT_DIET_TYPE,
                preferences.get(selectedDietTypeId) != null ?
                        preferences.get(selectedDietTypeId) : 0));
    }

}

class MealAndDietType{
    String selectedMealType;
    Integer selectedMealTypeId;
    String selectedDietType;
    Integer selectedDietTypeId;

    public MealAndDietType(String selectedMealType, Integer selectedMealTypeId,
                           String selectedDietType, Integer selectedDietTypeId) {
        this.selectedMealType = selectedMealType;
        this.selectedMealTypeId = selectedMealTypeId;
        this.selectedDietType = selectedDietType;
        this.selectedDietTypeId = selectedDietTypeId;
    }
}
