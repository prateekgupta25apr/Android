package prateek_gupta.foody.bindingsAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kotlin.jvm.JvmStatic;
import prateek_gupta.foody.adapters.FavoriteRecipesAdapter;
import prateek_gupta.foody.data.database.entities.FavoritesEntity;

public class FavoriteRecipesBinding {

    @BindingAdapter(value = {"viewVisibility", "setData"}, requireAll = false)
    @JvmStatic
    public static void setDataAndViewVisibility(View view, List<FavoritesEntity> favoritesEntities, FavoriteRecipesAdapter adapter){
        if (favoritesEntities==null || favoritesEntities.size()==0){
            if (view instanceof ImageView)
                view.setVisibility(View.VISIBLE);
            if (view instanceof TextView)
                view.setVisibility(View.VISIBLE);
            if (view instanceof RecyclerView)
                view.setVisibility(View.INVISIBLE);
        }
        else {
            if (view instanceof ImageView)
                view.setVisibility(View.INVISIBLE);
            if (view instanceof TextView)
                view.setVisibility(View.INVISIBLE);
            if (view instanceof RecyclerView) {
                view.setVisibility(View.VISIBLE);
                adapter.setData(favoritesEntities);
            }
        }
    }
}
