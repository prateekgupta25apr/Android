package prateek_gupta.load_more_data

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import prateek_gupta.load_more_data.databinding.ActivityMainBinding
import prateek_gupta.load_more_data.databinding.ElementBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var i = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            if (i <= 10) {
                val elementBinding: ElementBinding = ElementBinding.inflate(layoutInflater)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(10, 10, 10, 10)
                elementBinding.root.layoutParams = params
                i++
                binding.linearLayout.addView(elementBinding.root)
                elementBinding.textView.animate().scaleY(1f).duration = 500
                elementBinding.textView.animate().scaleX(1f).duration = 500
                elementBinding.textView2.animate().scaleY(1f).duration = 500
                elementBinding.textView2.animate().scaleX(1f).duration = 500
                if (i > 10) binding.button.visibility = View.GONE
            }
        }
    }
}