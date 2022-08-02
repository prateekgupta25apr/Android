package prateek_gupta.foody;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView);
        NavController navController = null;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.recipesFragment,
                        R.id.favouritesRecipesFragment,
                        R.id.foodJokeFragment).build();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        if (navController != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
        if (navController != null) {
            NavigationUI.setupActionBarWithNavController(this, navController,
                    appBarConfiguration);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}