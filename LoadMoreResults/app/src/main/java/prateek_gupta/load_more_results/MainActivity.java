package prateek_gupta.load_more_results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import prateek_gupta.load_more_results.databinding.ActivityMainBinding;
import prateek_gupta.load_more_results.databinding.ElementBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(view -> {
            if (i<=10){
                ElementBinding elementBinding=ElementBinding.inflate(getLayoutInflater());
                LinearLayout.LayoutParams params = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                elementBinding.getRoot().setLayoutParams(params);
                i++;
                binding.linearLayout.addView(elementBinding.getRoot());

                elementBinding.textView.animate().scaleY(1f).setDuration(500);
                elementBinding.textView.animate().scaleX(1f).setDuration(500);
                elementBinding.textView2.animate().scaleY(1f).setDuration(500);
                elementBinding.textView2.animate().scaleX(1f).setDuration(500);

                if (i>10)
                    binding.button.setVisibility(View.GONE);
            }
        });
    }
}