package prateek_gupta.foody.ui.fragments.overview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import prateek_gupta.foody.R;
import prateek_gupta.foody.bindingsAdapters.RecipesRowBinding;
import prateek_gupta.foody.databinding.FragmentOverviewBinding;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.util.Constants;


public class OverviewFragment extends Fragment {

    FragmentOverviewBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOverviewBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            Result myBundle=getArguments().getParcelable(Constants.RECIPE_RESULT_KEY);

            ImageLoader imageLoader = Coil.imageLoader(binding.mainImageView.getContext());
            Log.d("OverviewFragment", "onCreateView: url : "+myBundle.image);
            ImageRequest request = new ImageRequest.Builder(binding.getRoot().getContext())
                    .data(myBundle.image)
                    .target(binding.mainImageView)
                    .build();
            imageLoader.enqueue(request);

            binding.titleTextView.setText(myBundle.title);

            binding.likesTextView.setText(String.valueOf(myBundle.aggregateLikes));

            binding.timeTextView.setText(String.valueOf(myBundle.readyInMinutes));

            RecipesRowBinding.parseHtml(binding.summaryTextView,myBundle.summary);

            updateColors(myBundle.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView);
            updateColors(myBundle.vegan, binding.veganTextView, binding.veganImageView);
            updateColors(myBundle.cheap, binding.cheapTextView, binding.cheapImageView);
            updateColors(myBundle.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView);
            updateColors(myBundle.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView);
            updateColors(myBundle.veryHealthy, binding.healthyTextView, binding.healthyImageView);
        }

        return binding.getRoot();
    }

    void updateColors(Boolean stateIsOn , TextView textView, ImageView imageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}