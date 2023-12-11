package prateek_gupta.physical_device_tester;

import android.os.Bundle;

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
    }
}