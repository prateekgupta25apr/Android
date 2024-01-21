package prateek_gupta.physical_device_tester;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import prateek_gupta.physical_device_tester.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.frame.startAnimation(AnimationUtils.loadAnimation(this,R.anim.move_from_bottom_to_center));
    }

    @Override
    public void onBackPressed() {
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.move_from_center_to_bottom);
        animation.setFillAfter(true);
        binding.frame.startAnimation(animation);

        super.onBackPressed();
        overridePendingTransition(R.anim.stay,R.anim.stay);

    }
}