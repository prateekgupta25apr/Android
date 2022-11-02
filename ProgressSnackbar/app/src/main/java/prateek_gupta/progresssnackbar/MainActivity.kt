package prateek_gupta.progresssnackbar

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creating a SnackBar
        val snackBar = Snackbar.make(
            findViewById(R.id.main_view),
            "Something", Snackbar.LENGTH_INDEFINITE
        )

        // Retrieving SnackbarLayout for the snackBar
        val snackbarLayout = snackBar.view as SnackbarLayout

        // Retrieving the default TextView of the SnackBar
        val textView = snackbarLayout.findViewById<View>(
            com.google.android.material.R.id.snackbar_text
        ) as TextView

        // Using INVISIBLE not GONE, to keep the basic structure of the SnackBar
        textView.visibility = View.INVISIBLE

        // Adding our custom view to snackBar
        snackbarLayout.addView(
            layoutInflater.inflate(
                R.layout.progress_snackbar,
                null
            )
        )

        // Displaying snackBar
        snackBar.show()
    }
}