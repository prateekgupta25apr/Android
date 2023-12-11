package prateek_gupta.physical_device_tester;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.HashMap;

import coil.Coil;
import coil.ImageLoader;
import coil.memory.MemoryCache;
import coil.request.CachePolicy;
import coil.request.ImageRequest;
import coil.target.Target;
import prateek_gupta.physical_device_tester.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerView.setItemViewCacheSize(-1);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(new Adapter(layoutManager));
    }
}