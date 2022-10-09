package prateek_gupta.foody.bindingsAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;

import org.jsoup.Jsoup;

import javax.inject.Inject;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import kotlin.jvm.JvmStatic;
import prateek_gupta.foody.R;
import prateek_gupta.foody.models.Result;
import prateek_gupta.foody.ui.fragments.recipes.RecipesFragmentDirections;

public class RecipesRowBinding {

    @BindingAdapter("onRecipeClickListener")
    @JvmStatic
    public static void onRecipeClickListener(ConstraintLayout constraintLayout, Result result){
        constraintLayout.setOnClickListener(view -> {
            RecipesFragmentDirections.ActionRecipesFragmentToDetailsActivity action=
            RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result);
            Navigation.findNavController(constraintLayout).navigate(action);
        });

    }

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    public static void loadImageFromUrl(ImageView view,String url){
        ImageLoader imageLoader = Coil.imageLoader(view.getContext());
        ImageRequest request = new ImageRequest.Builder(view.getContext())
                .data(url)
                .crossfade(600)
                .target(view)
                .error(R.drawable.ic_error_placeholder)
                .build();
        imageLoader.enqueue(request);

    }

    @BindingAdapter("applyVeganColor")
    @JvmStatic
    public static void applyVeganColor(View view,Boolean vegan){
        if (vegan){
            if (view instanceof TextView)
                ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));
            else if (view instanceof ImageView)
                ((ImageView) view).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.green));
        }
    }

    @BindingAdapter("parseHtml")
    @JvmStatic
    public static void parseHtml(TextView textView, String description){
        if(description != null) {
            String desc = Jsoup.parse(description).text();
            textView.setText(desc);
        }
    }


}