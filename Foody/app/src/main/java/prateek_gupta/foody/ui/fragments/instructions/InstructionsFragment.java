package prateek_gupta.foody.ui.fragments.instructions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import prateek_gupta.foody.databinding.FragmentInstructionsBinding;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;

public class InstructionsFragment extends Fragment {

    FragmentInstructionsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentInstructionsBinding.inflate(inflater, container, false);

        if (getArguments()!=null){
            Result myBundle=getArguments().getParcelable(Constants.RECIPE_RESULT_KEY);

            //binding.instructionsWebView.setWebViewClient(new WebViewClient());
            binding.instructionsWebView.loadUrl(myBundle.sourceUrl);
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}