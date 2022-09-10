package prateek_gupta.foody.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.foody.R;
import prateek_gupta.foody.adapters.PagerAdapter;
import prateek_gupta.foody.ui.fragments.ingredients.IngredientsFragment;
import prateek_gupta.foody.ui.fragments.instructions.InstructionsFragment;
import prateek_gupta.foody.ui.fragments.overview.OverviewFragment;

public class DetailsActivity extends AppCompatActivity {

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
        resultBundle.putParcelable("recipeBundle",DetailsActivityArgs.fromBundle(getIntent().getExtras()).getResult());

        PagerAdapter pagerAdapter=new PagerAdapter(resultBundle,fragments,titles,getSupportFragmentManager());
        ViewPager viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}