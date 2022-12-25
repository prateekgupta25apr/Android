package prateek_gupta.advanced_bar_graph

import android.os.Bundle
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import prateek_gupta.advanced_bar_graph.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val values: MutableList<Int>
        values = ArrayList()
        values.add(100)
        values.add(60)
        values.add(20)
        values.add(50)
        values.add(80)
        values.add(60)
        values.add(40)
        values.add(90)
        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener(
            object : OnScrollChangedListener {
                override fun onScrollChanged() {
                    if (binding.root.scrollY > 550) {
                        binding.scrollView.viewTreeObserver.removeOnScrollChangedListener(this)
                        for (i in 0 until binding.recyclerView.childCount) {
                            if (i > (binding.recyclerView.layoutManager as
                                        LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1) {
                                break
                            }
                            adapter.startBarAnimation(
                                values[i], 1000,
                                (binding.recyclerView.getChildViewHolder(
                                    binding.recyclerView.getChildAt(i)
                                ) as Adapter.ViewHolder).binding
                            )
                        }
                        adapter.triggerAnimation = true
                    }
                }
            })
    }
}