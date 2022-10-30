package prateek_gupta.progressbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.view.View;

import prateek_gupta.progressbutton.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    boolean showingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setting default value for showingProgress
        showingProgress=false;

        // Setting default value for text for button
        String buttonText="Log In";

        // Setting value for text for button
        binding.button.setText(buttonText);

        // Setting on click listener for button
        binding.button.setOnClickListener(view -> {
            // When processing needs to be shown
            if (!showingProgress){
                // Updating text for the button
                String updatedButtonText=buttonText+"      ";
                binding.button.setText(updatedButtonText);

                // Making progress bar visible
                binding.progress.setVisibility(View.VISIBLE);

                // Setting elevation for progress bar
                ViewCompat.setElevation(binding.progress,6.0f);

                // Reducing opacity of the button
                binding.button.setAlpha(0.75f);

                // Updating value for the showingProgress
                showingProgress=true;
            }
            // When processing need not to be shown
            else {
                // Updating text for the button
                binding.button.setText(buttonText);

                // Making progress bar invisible
                binding.progress.setVisibility(View.GONE);

                // Increasing opacity of the button
                binding.button.setAlpha(1);

                // Updating value for the showingProgress
                showingProgress=false;
            }
        });
    }
}