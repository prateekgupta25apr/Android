package prateek_gupta.foody.ui.fragments.overview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import prateek_gupta.foody.R;
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

            binding.summaryTextView.setText(Jsoup.parse(myBundle.summary).text());

            if (myBundle.vegetarian){
                binding.vegetarianTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.vegetarianImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.vegan){
                binding.veganTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.veganImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.glutenFree){
                binding.glutenFreeTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.glutenFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.dairyFree){
                binding.dairyFreeTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.dairyFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.veryHealthy){
                binding.healthyTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.healthyImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.cheap){
                binding.cheapTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                binding.cheapImageView.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}