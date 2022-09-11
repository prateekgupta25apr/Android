package prateek_gupta.foody.ui.fragments.instructions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import prateek_gupta.foody.R;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;

public class InstructionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_instructions, container, false);

        if (getArguments()!=null){
            Result myBundle=getArguments().getParcelable(Constants.RECIPE_RESULT_KEY);

            WebView webView=view.findViewById(R.id.instructions_webView);
            //webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(myBundle.sourceUrl);
        }
        return view;
    }
}