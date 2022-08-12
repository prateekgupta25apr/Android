package prateek_gupta.foody.bindingsAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import javax.inject.Inject;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import kotlin.jvm.JvmStatic;
import prateek_gupta.foody.R;

public class RecipesRowBinding {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    public static void loadImageFromUrl(ImageView view,String url){
        ImageLoader imageLoader = Coil.imageLoader(view.getContext());
        ImageRequest request = new ImageRequest.Builder(view.getContext())
                .data(url)
                .crossfade(600)
                .target(view)
                .build();
        imageLoader.enqueue(request);

    }

    @BindingAdapter("setNumberOfLikes")
    @JvmStatic
    public static void setNumberOfLikes(TextView view, Integer likes){
        view.setText(String.valueOf(likes));
    }

    @BindingAdapter("setNumberOfMinutes")
    @JvmStatic
    public static void setNumberOfMinutes(TextView view,Integer minutes){
        view.setText(String.valueOf(minutes));
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
}
