package prateek_gupta.physical_device_tester;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import prateek_gupta.physical_device_tester.databinding.ItemBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    LinearLayoutManager layoutManager;


    public Adapter(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    List<Integer> colors = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN,Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN);
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.itemBinding.getRoot().setBackgroundColor(colors.get(position));
        if (position>layoutManager.findLastVisibleItemPosition())
            holder.itemBinding.getRoot().startAnimation(AnimationUtils.loadAnimation(holder.itemBinding.getRoot().getContext(), R.anim.move_from_bottom_to_center));
        else
            holder.itemBinding.getRoot().startAnimation(AnimationUtils.loadAnimation(holder.itemBinding.getRoot().getContext(), R.anim.move_down));
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder{

        ItemBinding itemBinding;

        public AdapterViewHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding=itemBinding;
        }
    }
}
