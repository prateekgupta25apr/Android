package prateek_gupta.foody.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import prateek_gupta.foody.R;
import prateek_gupta.foody.models.ExtendedIngredient;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.util.RecipesDiffUtils;

public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    List<ExtendedIngredient> ingredientsList=new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.ingredients_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    ImageView imageView=holder.itemView.findViewById(R.id.ingredient_imageView);
        ImageLoader imageLoader = Coil.imageLoader(imageView.getContext());
        ImageRequest request = new ImageRequest.Builder(imageView.getContext())
                .data(Constants.BASE_IMAGE_URL+ingredientsList.get(position).image)
                .crossfade(600)
                .error(R.drawable.ic_error_placeholder)
                .target(imageView)
                .build();
        imageLoader.enqueue(request);

        TextView ingredient_name=holder.itemView.findViewById(R.id.ingredient_name);
        ingredient_name.setText(ingredientsList.get(position).name);

        TextView ingredient_amount=holder.itemView.findViewById(R.id.ingredient_amount);
        ingredient_amount.setText(String.valueOf(ingredientsList.get(position).amount));

        TextView ingredient_unit=holder.itemView.findViewById(R.id.ingredient_unit);
        ingredient_unit.setText(String.valueOf(ingredientsList.get(position).unit));

        TextView ingredient_consistency=holder.itemView.findViewById(R.id.ingredient_consistency);
        ingredient_consistency.setText(String.valueOf(ingredientsList.get(position).consistency));

        TextView ingredient_original=holder.itemView.findViewById(R.id.ingredient_original);
        ingredient_original.setText(String.valueOf(ingredientsList.get(position).original));

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public void setData(List<ExtendedIngredient> newIngredients) {
        RecipesDiffUtils ingredientsDiffUtil =
                new RecipesDiffUtils(ingredientsList, newIngredients);
        DiffUtil.DiffResult diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil);
        ingredientsList = newIngredients;
        diffUtilResult.dispatchUpdatesTo(this);
    }
}
