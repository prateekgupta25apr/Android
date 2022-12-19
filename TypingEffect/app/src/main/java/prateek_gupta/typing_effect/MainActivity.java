package prateek_gupta.typing_effect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import prateek_gupta.typing_effect.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean erasing = false;
    int characterIndex = 0;
    int textNumber = 0;
    List<String> letters;
    List<String> texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        texts = new ArrayList<>();
        texts.add("Prateek");
        texts.add("Gupta");
        letters = Arrays.asList(texts.get(textNumber).split(""));
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (binding.textView.getText().toString().length() ==
                        texts.get(textNumber).length())
                    erasing = true;
                else if (erasing && binding.textView.getText().toString().length() == 0) {
                    erasing = false;
                    updateText();
                }
                if (erasing) {
                    binding.textView.setText(binding.textView.getText().toString().
                            substring(0, binding.textView.getText().length() - 1));
                    characterIndex--;
                } else {
                    binding.textView.append(letters.get(characterIndex));
                    characterIndex++;
                }

                handler.postDelayed(this, 200);
            }
        };

        handler.post(runnable);
    }

    /**
     * Method to change the text to display
     */
    void updateText() {
        if (textNumber == (texts.size() - 1))
            textNumber = 0;
        else
            textNumber++;
        letters = Arrays.asList(texts.get(textNumber).split(""));
        characterIndex = 0;
    }
}