package prateek_gupta.foody.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import prateek_gupta.foody.data.Repository;
import prateek_gupta.foody.data.database.RecipesEntity;
import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.util.NetworkResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";

    Repository repository;
    Application application;

    public MutableLiveData<NetworkResult<FoodRecipe>> recipesResponse = new MutableLiveData<>();
    public LiveData<List<RecipesEntity>> readRecipes;
    @Inject
    public MainViewModel(@NonNull @NotNull Application application, Repository repository) {
        super(application);
        this.repository = repository;
        this.application = application;
    }

    public LiveData<List<RecipesEntity>> getReadRecipes() {
        readRecipes=repository.getLocal().readDatabase();
        return readRecipes;
    }

    /** ROOM DATABASE */

    private void insertRecipes(RecipesEntity recipesEntity) {
        repository.getLocal().insertRecipes(recipesEntity);
    }

    /** RETROFIT */
    public void getRecipes(Map<String, String> queries) {
        getRecipesSafeCall(queries);
    }

    void getRecipesSafeCall(Map<String, String> queries) {
        recipesResponse.setValue(new NetworkResult.Loading<>());
        if (hasInternetConnection()) {
            try {
                Call<FoodRecipe> response = repository.getRemote().getRecipes(queries);
                response.enqueue(new Callback<FoodRecipe>() {
                    @Override
                    public void onResponse(@NotNull Call<FoodRecipe> call, @NotNull Response<FoodRecipe> response) {
                        recipesResponse.setValue(handleFoodRecipesResponse(response));
                        if (recipesResponse.getValue()!=null){
                            FoodRecipe foodRecipe = recipesResponse.getValue().data;
                            offlineCacheRecipes(foodRecipe);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FoodRecipe> call, @NotNull Throwable t) {
                    }
                });


            } catch (Exception e) {
                recipesResponse.setValue(new NetworkResult.Error<>("Recipes not found. 1"));
            }
        } else recipesResponse.setValue(new NetworkResult.Error<>("No Internet Connection."));
    }

    private void offlineCacheRecipes(FoodRecipe foodRecipe) {
        RecipesEntity recipesEntity = new RecipesEntity(foodRecipe);
        insertRecipes(recipesEntity);
    }

    NetworkResult<FoodRecipe> handleFoodRecipesResponse(Response<FoodRecipe> response) {
        if (response.message().contains("timeout"))
            return new NetworkResult.Error<>("Timeout");
        else if (response.code() == 402)
            return new NetworkResult.Error<>("API Key Limited.");
        else if (response.body() != null && response.body().results.isEmpty()) {
            return new NetworkResult.Error<>("Recipes not found.");
        } else if (response.isSuccessful()) {
            FoodRecipe foodRecipe = response.body();
            return new NetworkResult.Success<>(foodRecipe);
        } else return new NetworkResult.Error<>(response.message());
    }

    boolean hasInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network activeNetwork;
            if (connectivityManager.getActiveNetwork() != null)
                activeNetwork = connectivityManager.getActiveNetwork();
            else return false;

            NetworkCapabilities capabilities;
            if (connectivityManager.getNetworkCapabilities(activeNetwork) != null)
                capabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            else return false;

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else {
            try {
                if (connectivityManager.getActiveNetworkInfo() == null) return false;
                else connectivityManager.getActiveNetworkInfo().isConnected();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}