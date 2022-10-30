package prateek_gupta.progressbutton

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import prateek_gupta.progressbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var showingProgress = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Setting default value for showingProgress
        showingProgress = false

        // Setting default value for text for button
        val buttonText = "Log In"

        // Setting value for text for button
        binding!!.button.text = buttonText

        // Setting on click listener for button
        binding!!.button.setOnClickListener {
            // When processing needs to be shown
            if (!showingProgress) {
                // Updating text for the button
                val updatedButtonText = "$buttonText      "
                binding!!.button.text = updatedButtonText

                // Making progress bar visible
                binding!!.progress.visibility = View.VISIBLE

                // Setting elevation for progress bar
                ViewCompat.setElevation(binding!!.progress, 6.0f)

                // Reducing opacity of the button
                binding!!.button.alpha = 0.75f

                // Updating value for the showingProgress
                showingProgress = true
            } else {
                // Updating text for the button
                binding!!.button.text = buttonText

                // Making progress bar invisible
                binding!!.progress.visibility = View.GONE

                // Increasing opacity of the button
                binding!!.button.alpha = 1F

                // Updating value for the showingProgress
                showingProgress = false
            }
        }
    }
}