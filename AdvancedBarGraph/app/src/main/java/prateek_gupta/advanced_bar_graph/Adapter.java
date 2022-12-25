package prateek_gupta.advanced_bar_graph;

import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.advanced_bar_graph.databinding.BarGraphBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Integer> values;

    boolean triggerAnimation =false;

    public Adapter() {
        values=new ArrayList<>();
        values.add(100);
        values.add(60);
        values.add(20);
        values.add(50);
        values.add(80);
        values.add(10);
        values.add(40);
        values.add(90);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        BarGraphBinding binding;
        public ViewHolder(BarGraphBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(BarGraphBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (triggerAnimation) {
            startBarAnimation(values.get(position), 1000, holder.binding);
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public  void startBarAnimation(int value,int duration,
                                   BarGraphBinding barGraphBinding){
        float barFullHeight=barGraphBinding.bar.getPivotY();
        float scalingFactor=(float) value/100;
        float translationHeight=barFullHeight*scalingFactor;
        barGraphBinding.pin.animate().
                translationY(-translationHeight).setDuration(duration);
        barGraphBinding.bar.animate().scaleY(scalingFactor).setDuration(duration);
        barGraphBinding.value.animate().
                translationY(-translationHeight).setDuration(duration);
        if (Integer.parseInt((String) barGraphBinding.value.getText())==0)
        startCountAnimation(value,barGraphBinding.value,duration);
    }

     void startCountAnimation(int stop, TextView textView, int duration) {
        ValueAnimator animator = ValueAnimator.ofInt(0, stop);
        animator.setDuration(duration);
        animator.addUpdateListener(animation ->
                textView.setText(animation.getAnimatedValue().toString()));
        animator.start();
    }
}
