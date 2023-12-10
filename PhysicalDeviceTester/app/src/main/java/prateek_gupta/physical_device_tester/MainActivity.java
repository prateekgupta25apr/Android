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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.HashMap;

import coil.Coil;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.CachePolicy;
import coil.request.ImageRequest;
import coil.target.Target;
import prateek_gupta.physical_device_tester.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {

    ActivityMain2Binding binding;

    ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setOnExitAnimationListener(new SplashScreen.OnExitAnimationListener() {
            @Override
            public void onSplashScreenExit(@NonNull SplashScreenViewProvider splashScreenViewProvider) {
                TranslateAnimation rightTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,-1f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0f);
                splashScreenViewProvider.getIconAnimationDurationMillis();
                rightTranslateAnimation.setDuration(1000);
                //rightTranslateAnimation.setFillAfter(true);
                rightTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        splashScreenViewProvider.remove();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                splashScreenViewProvider.getView().startAnimation(rightTranslateAnimation);


            }
        });
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageLoader=Coil.imageLoader(this);



        imageLoader.enqueue(new ImageRequest.Builder(this)
                        .target(new Target() {
                            @Override
                            public void onStart(@Nullable Drawable drawable) {
                                binding.swipe.setRefreshing(true);
                            }

                            @Override
                            public void onError(@Nullable Drawable drawable) {

                            }

                            @Override
                            public void onSuccess(@NonNull Drawable drawable) {
                                binding.imageView4.setImageDrawable(drawable);
                                binding.swipe.setRefreshing(false);
                            }
                        })
                        .diskCachePolicy(CachePolicy.WRITE_ONLY)
                        .memoryCachePolicy(CachePolicy.WRITE_ONLY)
                        .data("https://prateekgupta.pythonanywhere.com/Projects/Gupta%20Automobiles%20Website/2.png")
                .build());



                binding.button.setOnClickListener(view -> {
                    imageLoader.enqueue(new ImageRequest.Builder(this)
                            .target(new Target() {
                                @Override
                                public void onStart(@Nullable Drawable drawable) {
                                    binding.imageView4.setImageResource(R.drawable.logo);
                                }

                                @Override
                                public void onError(@Nullable Drawable drawable) {
                                    MemoryCache.Value memoryCache= imageLoader.getMemoryCache().get(new MemoryCache.Key("https://prateekgupta.pythonanywhere.com/Projects/Gupta%20Automobiles%20Website/2.png",new HashMap<>()));
                                    if (memoryCache!=null){
                                        binding.imageView4.setImageBitmap(memoryCache.getBitmap());
                                    }
                                    else
                                        Log.d("TAG", "onError: no cache");

                                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(@NonNull Drawable drawable) {
                                    binding.imageView4.setImageDrawable(drawable);
                                }
                            })
                            .diskCachePolicy(CachePolicy.WRITE_ONLY)
                            .memoryCachePolicy(CachePolicy.WRITE_ONLY)
                            .data("https://prateekgupta.pythonanywhere.com/Projects/Gupta%20Automobiles%20Website/2.png")
                            .build());
                });

                binding.swipe.setColorSchemeColors(getResources().getColor(R.color.purple_200));
                binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        imageLoader.enqueue(new ImageRequest.Builder(MainActivity.this)
                                .target(new Target() {
                                    @Override
                                    public void onStart(@Nullable Drawable drawable) {
                                        binding.imageView4.setImageResource(R.drawable.logo);
                                        binding.swipe.setRefreshing(true);
                                    }

                                    @Override
                                    public void onError(@Nullable Drawable drawable) {
                                        MemoryCache.Value memoryCache= imageLoader.getMemoryCache().get(new MemoryCache.Key("https://prateekgupta.pythonanywhere.com/Projects/Gupta%20Automobiles%20Website/2.png",new HashMap<>()));
                                        if (memoryCache!=null){
                                            binding.imageView4.setImageBitmap(memoryCache.getBitmap());
                                        }
                                        else
                                            Log.d("TAG", "onError: no cache");

                                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onSuccess(@NonNull Drawable drawable) {
                                        binding.imageView4.setImageDrawable(drawable);
                                        binding.swipe.setRefreshing(false);
                                    }
                                })
                                .diskCachePolicy(CachePolicy.WRITE_ONLY)
                                .memoryCachePolicy(CachePolicy.WRITE_ONLY)
                                .data("https://prateekgupta.pythonanywhere.com/Projects/Gupta%20Automobiles%20Website/2.png")
                                .build());
                    }
                });
    }
}