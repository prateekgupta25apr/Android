package prateek_gupta.foody.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import prateek_gupta.foody.data.network.FoodRecipesApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static prateek_gupta.foody.util.Constants.   BASE_URL;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Singleton
    @Provides
    OkHttpClient provideHttpClient()   {
        return new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideConverterFactory()  {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofitInstance(
            OkHttpClient okHttpClient ,GsonConverterFactory gsonConverterFactory
    )  {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    FoodRecipesApi provideApiService(Retrofit retrofit )  {
        return retrofit.create(FoodRecipesApi.class);
    }
}
