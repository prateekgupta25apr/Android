package prateek_gupta.foody.ui.fragments.overview;

import android.os.Bundle;

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
import prateek_gupta.foody.models.Result;


public class OverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_overview, container, false);

        if (getArguments() != null) {
            Result myBundle=getArguments().getParcelable("recipeBundle");

            ImageView mainImageView=view.findViewById(R.id.main_imageView);
            ImageLoader imageLoader = Coil.imageLoader(mainImageView.getContext());
            Log.d("OverviewFragment", "onCreateView: url : "+myBundle.image);
            ImageRequest request = new ImageRequest.Builder(view.getContext())
                    .data(myBundle.image)
                    .target(mainImageView)
                    .build();
            imageLoader.enqueue(request);

            TextView title=view.findViewById(R.id.title_textView);
            title.setText(myBundle.title);

            TextView likes=view.findViewById(R.id.likes_textView);
            likes.setText(String.valueOf(myBundle.aggregateLikes));

            TextView time=view.findViewById(R.id.time_textView);
            time.setText(String.valueOf(myBundle.readyInMinutes));

            TextView summary=view.findViewById(R.id.summary_textView);
            summary.setText(Jsoup.parse(myBundle.summary).text());

            if (myBundle.vegetarian){
                TextView vegetarian=view.findViewById(R.id.vegetarian_textView);
                vegetarian.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView vegetarianImage=view.findViewById(R.id.vegetarian_imageView);
                vegetarianImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.vegan){
                TextView vegan=view.findViewById(R.id.vegan_textView);
                vegan.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView veganImage=view.findViewById(R.id.vegan_imageView);
                veganImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.glutenFree){
                TextView glutenFree=view.findViewById(R.id.gluten_free_textView);
                glutenFree.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView glutenFreeImage=view.findViewById(R.id.gluten_free_imageView);
                glutenFreeImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.dairyFree){
                TextView dairyFree=view.findViewById(R.id.dairy_free_textView);
                dairyFree.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView dairyFreeImage=view.findViewById(R.id.dairy_free_imageView);
                dairyFreeImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.veryHealthy){
                TextView veryHealthy=view.findViewById(R.id.healthy_textView);
                veryHealthy.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView veryHealthyImage=view.findViewById(R.id.healthy_imageView);
                veryHealthyImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }

            if (myBundle.cheap){
                TextView cheap=view.findViewById(R.id.cheap_textView);
                cheap.setTextColor(ContextCompat.getColor(requireContext(),R.color.green));

                ImageView cheapImage=view.findViewById(R.id.cheap_imageView);
                cheapImage.setColorFilter(ContextCompat.getColor(requireContext(),R.color.green));
            }
        }

        return view;
    }
}