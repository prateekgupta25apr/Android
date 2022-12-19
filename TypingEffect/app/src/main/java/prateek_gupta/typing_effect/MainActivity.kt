package prateek_gupta.typing_effect

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import prateek_gupta.typing_effect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var erasing = false
    var characterIndex = 0
    var textNumber = 0
    lateinit var letters: MutableList<String>
    lateinit var texts: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        texts = ArrayList()
        texts.add("Prateek")
        texts.add("Gupta")
        letters = texts[textNumber].split("") as MutableList<String>
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (binding.textView.text.toString().length ==
                    texts[textNumber].length
                ) erasing = true
                else if (erasing && binding.textView.text.toString().isEmpty()
                ) {
                    erasing = false
                    updateText()
                }
                if (erasing) {
                    binding.textView.text = binding.textView.text.toString()
                        .substring(0, binding.textView.text.length - 1)
                    characterIndex--
                } else {
                    binding.textView.append(letters[characterIndex])
                    characterIndex++
                }
                handler.postDelayed(this, 200)
            }
        }
        handler.post(runnable)
    }

    /**
     * Method to change the text to display
     */
    fun updateText() {
        if (textNumber == texts.size - 1)
            textNumber = 0
        else textNumber++
        letters = texts[textNumber].split("") as MutableList<String>
        characterIndex = 0
    }
}