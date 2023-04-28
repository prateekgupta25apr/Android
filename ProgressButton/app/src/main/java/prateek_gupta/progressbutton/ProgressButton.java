package prateek_gupta.progressbutton;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class ProgressButton {
    CardView cardView;
    ProgressBar progressBar;
    TextView textView;
    String activatedText;
    String deactivatedText;
    Animation fade_in;
    Animation fade_out;

    public ProgressButton(Context context, View view, String activatedText,String deactivatedText) {
        fade_in = AnimationUtils.loadAnimation(context,R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(context,R.anim.fade_out);
        this.activatedText=activatedText;
        this.deactivatedText=deactivatedText;
        cardView= (CardView) view;
        progressBar=view.findViewById(R.id.progress_button_progress_bar);
        textView=view.findViewById(R.id.progress_button_text);
        textView.setText(deactivatedText);
    }

    void activateProgress(){
        cardView.setAlpha(0.75f);
        progressBar.setAnimation(fade_in);
        progressBar.setVisibility(View.VISIBLE);
        textView.setAnimation(fade_in);
        textView.setText(activatedText);
    }

    void deactivateProgress(){
        cardView.setAlpha(1f);
        progressBar.setAnimation(fade_out);
        progressBar.setVisibility(View.GONE);
        textView.setAnimation(fade_in);
        textView.setText(deactivatedText);
    }
}
