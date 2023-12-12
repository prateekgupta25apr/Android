package prateek_gupta.physical_device_tester;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.Transition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import prateek_gupta.physical_device_tester.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerView.setItemViewCacheSize(-1);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(new Adapter(layoutManager));

        new Handler().postDelayed(()->{
            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
            MainActivity.this.startActivityForResult(intent, 0,
                    ActivityOptions.makeCustomAnimation(MainActivity.this,
                            R.anim.stay,R.anim.stay).toBundle());
        },1000);
    }
}