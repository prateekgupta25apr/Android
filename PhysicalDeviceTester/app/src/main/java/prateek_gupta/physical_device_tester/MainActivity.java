package prateek_gupta.physical_device_tester;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.splashscreen.SplashScreenViewProvider;

import prateek_gupta.physical_device_tester.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {

    ActivityMain2Binding binding;


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

//        ValueAnimator valueAnimator=ValueAnimator.ofArgb(
//                Color.parseColor("#ffcc0000"),Color.parseColor("#FF3700B3"));
//        valueAnimator.setDuration(1000);
//        valueAnimator.setRepeatCount(2);
//        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        valueAnimator.addUpdateListener(valueAnimator1 ->
//                binding.imageView3.setColorFilter((Integer) valueAnimator1.getAnimatedValue()));
//        valueAnimator.start();
//
//        Handler alphaAnimationHandler =new Handler();
//        alphaAnimationHandler.postDelayed(() -> {
//            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//            alphaAnimation.setDuration(1000);
//            alphaAnimation.setFillAfter(true);
//            binding.imageView3.startAnimation(alphaAnimation);
//        }, 2000);
//
//        Handler translateAnimationHandler=new Handler();
//        translateAnimationHandler.postDelayed(()->{
//            TranslateAnimation leftTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,-1f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0);
//            leftTranslateAnimation.setDuration(4000);
//            leftTranslateAnimation.setFillAfter(true);
//            binding.imageView.startAnimation(leftTranslateAnimation);
//
//            TranslateAnimation rightTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,1f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0);
//            rightTranslateAnimation.setDuration(4000);
//            rightTranslateAnimation.setFillAfter(true);
//            binding.imageView2.startAnimation(rightTranslateAnimation);
//        },3000);
    }
}