package prateek_gupta.foody.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import prateek_gupta.foody.R;
import prateek_gupta.foody.databinding.IngredientsRowLayoutBinding;
import prateek_gupta.foody.models.ExtendedIngredient;
import prateek_gupta.foody.util.Constants;
import prateek_gupta.foody.util.RecipesDiffUtils;

public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    List<ExtendedIngredient> ingredientsList=new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        IngredientsRowLayoutBinding binding;
        public MyViewHolder(IngredientsRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageLoader imageLoader = Coil.imageLoader(holder.binding.ingredientImageView.getContext());
        ImageRequest request = new ImageRequest.Builder(holder.binding.ingredientImageView.getContext())
                .data(Constants.BASE_IMAGE_URL+ingredientsList.get(position).image)
                .crossfade(600)
                .error(R.drawable.ic_error_placeholder)
                .target(holder.binding.ingredientImageView)
                .build();
        imageLoader.enqueue(request);

        holder.binding.ingredientName.setText(ingredientsList.get(position).name);

        holder.binding.ingredientAmount.setText(String.valueOf(ingredientsList.get(position).amount));

        holder.binding.ingredientUnit.setText(String.valueOf(ingredientsList.get(position).unit));

        holder.binding.ingredientConsistency.setText(String.valueOf(ingredientsList.get(position).consistency));

        holder.binding.ingredientOriginal.setText(String.valueOf(ingredientsList.get(position).original));

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
