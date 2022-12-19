package prateek_gupta.physical_device_tester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import prateek_gupta.physical_device_tester.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.instagram.setOnClickListener(view -> {
            launchApp("http://instagram.com/_u/gupta__prateek");
        });

        binding.linkedIn.setOnClickListener(view -> launchApp("https://www.linkedin.com/in/prateek-gupta-5a8122196/"));
        binding.github.setOnClickListener(view -> launchApp("https://github.com/prateekgupta25apr"));
        binding.hackerrank.setOnClickListener(view -> launchApp("https://www.hackerrank.com/prateekgupta2504?hr_r=1"));
        binding.stackoveflow.setOnClickListener(view -> launchApp("https://stackoverflow.com/users/13329963/prateek-gupta"));
        binding.twitter.setOnClickListener(view -> launchApp("https://twitter.com/GuPtA__PraTeeK"));
        binding.facebook.setOnClickListener(view -> launchApp("fb://facewebmodal/f?href=https://www.facebook.com/prateek.gupta.12764/"));
    }

    void launchApp(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }
}