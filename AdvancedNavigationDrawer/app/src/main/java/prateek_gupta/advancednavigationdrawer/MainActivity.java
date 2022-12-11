package prateek_gupta.advancednavigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;


import prateek_gupta.advancednavigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;

    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionBarDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout,
                0,0){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                binding.mainView.setTranslationX(slideOffset*400);
                binding.mainView.setTranslationY(slideOffset*10);
                binding.mainView.setScaleX(1-(slideOffset*0.27f));
                binding.mainView.setScaleY(1-(slideOffset*0.27f));
                binding.mainView.requestLayout();
                ViewCompat.setElevation(binding.mainView,27);
                ViewCompat.setElevation(binding.navigationView.getRoot(),27);
            }
        };

        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(
                ContextCompat.getColor(this,R.color.white));

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            // Syncing onOptionsItemSelected() methods of the Activity and
            // ActionBarDrawerToggle
            if (actionBarDrawerToggle.onOptionsItemSelected(item))
                return true;
            return super.onOptionsItemSelected(item);
        }
        else
            return false;
    }
}