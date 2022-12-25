package prateek_gupta.advanced_bar_graph;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import prateek_gupta.advanced_bar_graph.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<Integer> values;
        values=new ArrayList<>();
        values.add(100);
        values.add(60);
        values.add(20);
        values.add(50);
        values.add(80);
        values.add(60);
        values.add(40);
        values.add(90);


        Adapter adapter=new Adapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(
                this,LinearLayoutManager.HORIZONTAL,false));

        binding.scrollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (binding.getRoot().getScrollY() > 550) {
                            binding.scrollView.getViewTreeObserver().
                                    removeOnScrollChangedListener(this);
                            for (int i = 0; i < binding.recyclerView.getChildCount(); i++) {
                                if (i > (((LinearLayoutManager)
                                        binding.recyclerView.getLayoutManager()).
                                        findLastCompletelyVisibleItemPosition() + 1)) {
                                    break;
                                }
                                adapter.startBarAnimation(values.get(i), 1000,
                                        ((Adapter.ViewHolder)
                                                binding.recyclerView.getChildViewHolder(
                                                        binding.recyclerView.getChildAt(i)))
                                                .binding);
                            }
                            adapter.triggerAnimation = true;
                        }
                    }
                });
    }
}