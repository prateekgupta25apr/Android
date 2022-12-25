package prateek_gupta.advanced_bar_graph

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import prateek_gupta.advanced_bar_graph.databinding.BarGraphBinding

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var values: MutableList<Int> = ArrayList()
    var triggerAnimation = false

    class ViewHolder(binding: BarGraphBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: BarGraphBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BarGraphBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (triggerAnimation) {
            startBarAnimation(values[position], 1000, holder.binding)
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun startBarAnimation(
        value: Int, duration: Long,
        barGraphBinding: BarGraphBinding
    ) {
        val barFullHeight: Float = barGraphBinding.bar.getPivotY()
        val scalingFactor = value.toFloat() / 100
        val translationHeight = barFullHeight * scalingFactor
        barGraphBinding.pin.animate().translationY(-translationHeight).duration = duration
        barGraphBinding.bar.animate().scaleY(scalingFactor).duration = duration
        barGraphBinding.value.animate().translationY(-translationHeight).duration = duration
        if ((barGraphBinding.value.text as String).toInt() == 0)
            startCountAnimation(
            value,
            barGraphBinding.value,
            duration
        )
    }

    private fun startCountAnimation(stop: Int, textView: TextView, duration: Long) {
        val animator = ValueAnimator.ofInt(0, stop)
        animator.duration = duration.toLong()
        animator.addUpdateListener { animation: ValueAnimator ->
            textView.text = animation.animatedValue.toString()
        }
        animator.start()
    }

    init {
        values.add(100)
        values.add(60)
        values.add(20)
        values.add(50)
        values.add(80)
        values.add(10)
        values.add(40)
        values.add(90)
    }
}
