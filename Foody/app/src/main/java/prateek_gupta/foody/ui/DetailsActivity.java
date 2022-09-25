package prateek_gupta.foody.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.PagerAdapter;
import prateek_gupta.foody.data.database.entities.FavoritesEntity;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.ui.fragments.ingredients.IngredientsFragment;
import prateek_gupta.foody.ui.fragments.instructions.InstructionsFragment;
import prateek_gupta.foody.ui.fragments.overview.OverviewFragment;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.viewmodels.MainViewModel;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {

    MainViewModel mainViewModel;

    boolean recipeSaved=false;
    int savedRecipeId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new OverviewFragment());
        fragments.add(new IngredientsFragment());
        fragments.add(new InstructionsFragment());

        List<String> titles=new ArrayList<>();
        titles.add("Overview");
        titles.add("Ingredients");
        titles.add("Instructions");

        Bundle resultBundle=new Bundle();
        resultBundle.putParcelable(Constants.RECIPE_RESULT_KEY,DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult());

        PagerAdapter pagerAdapter=new PagerAdapter(resultBundle,fragments,titles,getSupportFragmentManager());
        ViewPager viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        mainViewModel=new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.save_to_favorites_menu);
        checkSavedRecipes(menuItem);
        return true;
    }

    void checkSavedRecipes(MenuItem menuItem) {
        mainViewModel.getReadFavoriteRecipes().observe(this, favoritesEntity ->{
            try {
                for (FavoritesEntity savedRecipe : favoritesEntity) {
                    if (savedRecipe.result.id.equals(DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult().id)) {
                        System.out.println(savedRecipe.result.id+" : "+DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult().id+" : "+ menuItem.getItemId());
                        changeMenuItemColor(menuItem, R.color.yellow);
                        savedRecipeId = savedRecipe.id;
                        recipeSaved = true;
                        break;
                    } else {
                        changeMenuItemColor(menuItem, R.color.white);
                    }
                }
            } catch (Exception e) {
                Log.d("DetailsActivity", e.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.save_to_favorites_menu && !recipeSaved) {
            saveToFavorites(item);
        } else if (item.getItemId() == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item);
        }
        return super.onOptionsItemSelected(item);
    }

    void saveToFavorites(MenuItem item) {
        FavoritesEntity favoritesEntity = new
                FavoritesEntity(0,
                DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult()
                );
        mainViewModel.insertFavoriteRecipe(favoritesEntity);
        changeMenuItemColor(item, R.color.yellow);
        showSnackBar("Recipe saved.");
        recipeSaved=true;
    }

    void removeFromFavorites(MenuItem item) {
        FavoritesEntity favoritesEntity = new
                FavoritesEntity(
                        savedRecipeId,
                DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult()
                );
        mainViewModel.deleteFavoriteRecipe(favoritesEntity);
        changeMenuItemColor(item, R.color.white);
        showSnackBar("Removed from Favorites.");
        recipeSaved = false;
    }

    void showSnackBar(String message) {
        Snackbar.make(
                findViewById(R.id.detailsLayout),
                message,
                Snackbar.LENGTH_SHORT
        ).setAction("Okay", v->{}).show();
    }

    void changeMenuItemColor(MenuItem item , Integer color ) {
        System.out.println("change color called");
        item.getIcon().setTint(ContextCompat.getColor(this, color));
    }
}