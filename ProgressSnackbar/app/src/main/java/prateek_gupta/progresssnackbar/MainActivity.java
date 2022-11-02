package prateek_gupta.progresssnackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating a SnackBar
        Snackbar snackBar = Snackbar.make(findViewById(R.id.main_view),
                "Something", Snackbar.LENGTH_INDEFINITE);

        // Retrieving SnackbarLayout for the snackBar
        Snackbar.SnackbarLayout snackbarLayout =
                (Snackbar.SnackbarLayout) snackBar.getView();

        // Retrieving the default TextView of the SnackBar
        TextView textView = (TextView) snackbarLayout.findViewById(
                com.google.android.material.R.id.snackbar_text);

        // Using INVISIBLE not GONE, to keep the basic structure of the SnackBar
        textView.setVisibility(View.INVISIBLE);

        // Adding our custom view to snackBar
        snackbarLayout.addView(getLayoutInflater().inflate(R.layout.progress_snackbar,
                null));

        // Displaying snackBar
        snackBar.show();
    }
}