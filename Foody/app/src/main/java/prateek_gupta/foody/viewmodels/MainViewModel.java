package prateek_gupta.foody.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import prateek_gupta.foody.data.Repository;
import prateek_gupta.foody.models.FoodRecipe;
import prateek_gupta.foody.util.NetworkResult;
import retrofit2.Response;

@HiltViewModel
public class MainViewModel extends AndroidViewModel {

    Repository repository;
    Application application;

    MutableLiveData<NetworkResult<FoodRecipe>> recipesResponse = new MutableLiveData<>();

    @Inject
    public MainViewModel(@NonNull @NotNull Application application, Repository repository, Application application1) {
        super(application);
        this.repository = repository;
        this.application = application1;
    }

    void getRecipes(Map<String, String> queries) {
        getRecipesSafeCall(queries);
    }

    void getRecipesSafeCall(Map<String, String> queries) {
        recipesResponse.setValue(new NetworkResult.Loading<>());
        if (hasInternetConnection()) {
            try {
                Response<FoodRecipe> response = repository.getRemote().getRecipes(queries);
                recipesResponse.setValue(handleFoodRecipesResponse(response));

            } catch (Exception e) {
                recipesResponse.setValue(new NetworkResult.Error<>("Recipes not found."));
            }
        } else recipesResponse.setValue(new NetworkResult.Error<>("No Internet Connection."));
    }

    NetworkResult<FoodRecipe> handleFoodRecipesResponse(Response<FoodRecipe> response) {
        if (response.message().toString().contains("timeout"))
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
